package com.sajib.torch

import android.content.pm.PackageManager
import android.hardware.camera2.CameraManager
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.sajib.torch.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    var cameraflash = false
    var flashon = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        cameraflash = packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)


        binding.imageButton.setOnClickListener {
            if (cameraflash) {
                if (flashon) {
                    flashon = false
                    binding.imageButton.setImageResource(R.drawable.lightoff)

                    flashlightoff()

                } else {
                    flashon = true
                    binding.imageButton.setImageResource(R.drawable.lighton)
                    flashlightOn()

                }
            }
        }
    }

    private fun flashlightoff() {
        val cameraManager: CameraManager = getSystemService(CAMERA_SERVICE) as CameraManager

        var cameraID: String
        try {

            cameraID = cameraManager.cameraIdList[0]
            cameraManager.setTorchMode(cameraID, false)
            binding.textvid.text = "OFF"
        } catch (e: Exception) {

        }
    }

    private fun flashlightOn() {
        val cameraManager: CameraManager = getSystemService(CAMERA_SERVICE) as CameraManager

        var cameraID: String
        try {

            cameraID = cameraManager.cameraIdList[0]
            cameraManager.setTorchMode(cameraID, true)
            binding.textvid.text = "ON"
        } catch (e: Exception) {

        }
    }
}