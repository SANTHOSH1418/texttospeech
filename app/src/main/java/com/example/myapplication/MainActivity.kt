package com.example.myapplication
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.widget.Button
import android.widget.EditText
import java.util.*

class MainActivity : AppCompatActivity() , TextToSpeech.OnInitListener {

    private lateinit var etspeak:EditText
    private lateinit var btspeak: Button

    private lateinit var tts : TextToSpeech

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etspeak = findViewById(R.id.ettxt)
        btspeak = findViewById(R.id.button)

        btspeak.isEnabled=false
        tts = TextToSpeech(this,this)

        btspeak.setOnClickListener{ speakOut()}
    }

    private fun speakOut() {
        val text =etspeak.text.toString()

        tts.speak(text, TextToSpeech.QUEUE_FLUSH,null,"")

    }
    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            val result = tts.setLanguage(Locale.US)
            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                // Handle the case where the language is not available or not supported
            } else {
                // The TextToSpeech engine has been initialized successfully
//                btspeak.isEnabled = true // Enable the speak button
            }
        } else {
            btspeak.isEnabled = true
            // Handle the case where TextToSpeech engine initialization failed
            // You could display an error message or do some other action here
        }
    }
}
