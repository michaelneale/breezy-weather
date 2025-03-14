package org.breezyweather

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Bundle

class WeatherMCP : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        when (intent?.action) {
            "com.example.ACTION_MMCP_DISCOVERY" -> {
                val bundle = Bundle().apply {
                    putString("instructions", "Provides weather information.")
                    putStringArray("tools", arrayOf("getWeather"))
                    putString("getWeather.description", "Returns current weather for given location.")
                    putString("getWeather.parameters", "{\"location\": \"string\"}")
                }
                setResultExtras(bundle)
            }

            "com.example.ACTION_MMCP_INVOKE" -> {
                val tool = intent.getStringExtra("tool")
                val params = intent.getStringExtra("params") // JSON string

                val result = when (tool) {
                    "getWeather" -> "Weather is sunny, 25°C"
                    else -> "Unknown tool"
                }

                setResultData(result)
            }
        }
    }
}
