package com.example.lab3

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import java.io.File

class MainActivity : AppCompatActivity() {
    private lateinit var outputFragment: OutputFragment
    private var sh = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        outputFragment = supportFragmentManager.findFragmentById(R.id.outputFragment) as OutputFragment

        val inputFragment = supportFragmentManager.findFragmentById(R.id.inputFragment) as InputFragment
        inputFragment.setOnTextEnteredListener { text ->
            if (text.isNotBlank()) {
                val file = File(this.filesDir, "passwords.txt")
                file.appendText("$text\n")
            }
        }

        inputFragment.setOnButtonListClickedListener {
            val file = File(this.filesDir, "passwords.txt")
            if (sh) {
                sh = !sh
                if (file.exists()) {
                    val passwords = file.readLines().toMutableList()
                    if (passwords.isNotEmpty()) {
                        outputFragment.showList(passwords)
                    } else {
                        sh = !sh
                    }
                }
            } else {
                sh = !sh
                outputFragment.hideList()
            }
        }
    }
}
