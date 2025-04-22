package com.example.traffic_light

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var redLight: View
    private lateinit var yellowLight: View
    private lateinit var greenLight: View
    private lateinit var startStopButton: Button
    private lateinit var changeLightButton: Button

    private var currentLight = 0 // 0 - red, 1 - yellow, 2 - green
    private var isRunning = false

    @SuppressLint("MissingInflatedId", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        redLight = findViewById(R.id.redLight)
        yellowLight = findViewById(R.id.yellowLight)
        greenLight = findViewById(R.id.greenLight)
        startStopButton = findViewById(R.id.startStopButton)
        changeLightButton = findViewById(R.id.changeLightButton)

        startStopButton.setOnClickListener {
            if (isRunning) {
                stopTrafficLight()
            } else {
                startTrafficLight()
            }
        }

        changeLightButton.setOnClickListener {
            changeTrafficLight()
        }

        if (savedInstanceState != null) {
            currentLight = savedInstanceState.getInt("currentLight")
            isRunning = savedInstanceState.getBoolean("isRunning")
            if (isRunning) {
                startStopButton.text = "Stop"
                updateLights()
            } else {
                startStopButton.text = "Start"
                resetLights()
            }
        } else {
            isRunning = false
            startStopButton.text = "Start"
            resetLights()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("currentLight", currentLight)
        outState.putBoolean("isRunning", isRunning)
    }

    @SuppressLint("SetTextI18n")
    private fun startTrafficLight() {
        isRunning = true
        startStopButton.text = "Stop"
        startStopButton.setBackgroundColor(Color.RED)
        updateLights()
    }

    @SuppressLint("SetTextI18n")
    private fun stopTrafficLight() {
        isRunning = false
        startStopButton.text = "Start"
        startStopButton.setBackgroundColor(Color.GREEN)
        resetLights()
    }


    private fun changeTrafficLight() {
        if (isRunning) {
            currentLight = (currentLight + 1) % 4
            updateLights()
        }
    }

    private fun updateLights() {
        when (currentLight) {
            0 -> {
                redLight.setBackgroundColor(Color.RED)
                yellowLight.setBackgroundColor(Color.GRAY)
                greenLight.setBackgroundColor(Color.GRAY)
            }
            1 -> {
                redLight.setBackgroundColor(Color.GRAY)
                yellowLight.setBackgroundColor(Color.YELLOW)
                greenLight.setBackgroundColor(Color.GRAY)
            }
            2 -> {
                redLight.setBackgroundColor(Color.GRAY)
                yellowLight.setBackgroundColor(Color.GRAY)
                greenLight.setBackgroundColor(Color.GREEN)
            }
            3 -> {
                redLight.setBackgroundColor(Color.GRAY)
                yellowLight.setBackgroundColor(Color.YELLOW)
                greenLight.setBackgroundColor(Color.GRAY)
            }
        }
    }

    private fun resetLights() {
        redLight.setBackgroundColor(Color.GRAY)
        yellowLight.setBackgroundColor(Color.GRAY)
        greenLight.setBackgroundColor(Color.GRAY)
    }
}
