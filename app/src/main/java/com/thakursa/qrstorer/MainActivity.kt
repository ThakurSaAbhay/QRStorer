package com.thakursa.qrstorer

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanIntentResult
import com.journeyapps.barcodescanner.ScanOptions


class MainActivity : AppCompatActivity() {
    lateinit var firestore: FirebaseFirestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val sbut=findViewById<Button>(R.id.Start)
        val options = ScanOptions()
        options.setOrientationLocked(false)
        options.setPrompt("Scan a QRCode");
        options.setBarcodeImageEnabled(true);
        sbut.setOnClickListener{

            barcodeLauncher.launch(options)
        }
        firestore= FirebaseFirestore.getInstance()
    }

    private val barcodeLauncher = registerForActivityResult(
        ScanContract()
    ) { result: ScanIntentResult ->
        if (result.contents == null) {
            Toast.makeText(this@MainActivity, "Cancelled", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(
                this@MainActivity,
                "Scanned: " + result.contents,
                Toast.LENGTH_LONG
            ).show()
            val userMap= hashMapOf(
                "QR interpretation" to result.contents
            )
            firestore.collection("User").add(userMap)
                .addOnSuccessListener {
                    Toast.makeText(this, "Result stored in firestore", Toast.LENGTH_SHORT)
                        .show()
                }
                .addOnFailureListener{
                    Toast.makeText(this,"Sorry! can't store the result",Toast.LENGTH_SHORT)
                        .show()
                }
        }
    }
}