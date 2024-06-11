package com.example.myscannerapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.google.android.gms.tasks.Task
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.codescanner.GmsBarcodeScanner
import com.google.mlkit.vision.codescanner.GmsBarcodeScannerOptions
import com.google.mlkit.vision.codescanner.GmsBarcodeScanning

const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    private lateinit var scanner: GmsBarcodeScanner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val options = GmsBarcodeScannerOptions.Builder()
            .setBarcodeFormats(
                Barcode.FORMAT_QR_CODE,
                Barcode.FORMAT_AZTEC)
            .build()

        scanner = GmsBarcodeScanning.getClient(this, options)
        val scanBtn = findViewById<Button>(R.id.btn)
        scanBtn.setOnClickListener {
            startScanning()
        }



    }

   private fun startScanning() {
        val scanTask: Task<Barcode> = scanner.startScan()
        scanTask.addOnSuccessListener { barcode ->
            // Handle the scanned barcode
            val rawValue = barcode.rawValue
            Log.d(TAG,"$rawValue")
        }.addOnFailureListener {e->
            // Handle the error
           e.printStackTrace()
        }
    }

}