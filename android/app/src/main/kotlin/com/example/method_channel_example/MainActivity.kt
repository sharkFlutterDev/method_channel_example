package com.example.method_channel_example

import androidx.annotation.NonNull
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel

class MainActivity: FlutterActivity() {
    private val CHANNEL = "samples_counter"

    override fun configureFlutterEngine(@NonNull flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)
        MethodChannel(flutterEngine.dartExecutor.binaryMessenger, CHANNEL).setMethodCallHandler {
                call, result ->
            if (call.method == "incrementCounter") {
                val hashMap = call.arguments as HashMap<*,*>

                val counter = hashMap["counter"] as Int?

                if (counter != null) {
                    result.success(counter+1)
                } else {
                    result.error("UNAVAILABLE", "Battery level not available.", null)
                }
            }
        }
    }
}
