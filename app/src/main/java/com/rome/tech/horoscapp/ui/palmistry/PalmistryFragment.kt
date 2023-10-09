package com.rome.tech.horoscapp.ui.palmistry

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.CameraSelector
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import androidx.core.content.PermissionChecker
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.google.common.util.concurrent.ListenableFuture
import com.rome.tech.horoscapp.databinding.FragmentPalmistryBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint // Aqui le indico a dagger que esta clase recibirá dependencias
class PalmistryFragment : Fragment() {

    companion object {
        private const val CAMERA_PERMISSION = android.Manifest.permission.CAMERA
    }

    private var _binding: FragmentPalmistryBinding? = null
    private val binding get() = _binding!!

    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                // Otorgó permiso
                // StartCamera
                startCamera()
            } else {
                // Rechazó permisos
                Toast.makeText(
                    requireContext(), "Aprueba el permiso de uso de camara", Toast.LENGTH_LONG
                ).show()
            }
        }

    private fun startCamera() {
        Toast.makeText(
            requireContext(), "Preparando la cámara", Toast.LENGTH_LONG
        ).show()

        val cameraProviderFuture: ListenableFuture<ProcessCameraProvider> =
            ProcessCameraProvider.getInstance(/* context = */ requireContext())
        cameraProviderFuture.addListener({
            val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()

            val preview: Preview = Preview.Builder().build().also {
                it.setSurfaceProvider(binding.viewFinder.surfaceProvider)
            }
            val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

            try {
                cameraProvider.unbindAll()  // limpio bindieados anteriores si existiesen
                cameraProvider.bindToLifecycle(this, cameraSelector, preview)

                // Aqui tendría disponible el preview
                startCameraOk()

            } catch (e: Exception) {
                StartCameraNotOk()
                Log.e("START-CAMERA", "Algo ha fallado ${e.message}")
            }
        }, ContextCompat.getMainExecutor(requireContext()))

    }

    private fun startCameraOk() {

        val appearAnimation = AlphaAnimation(0.0f, 1.0f)
        appearAnimation.duration = 1500

        appearAnimation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {
                binding.pbProgress.isVisible = false
                binding.ivTemplate.isVisible = true
            }

            override fun onAnimationEnd(animation: Animation?) {}
            override fun onAnimationRepeat(animation: Animation?) {}
        })

        binding.ivTemplate.startAnimation(appearAnimation)

    }

    private fun StartCameraNotOk() {
        binding.pbProgress.isVisible = false
        binding.ivTemplate.isVisible = true
        Toast.makeText(
            requireContext(), "Problemas para usar la camara", Toast.LENGTH_LONG
        ).show()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (checkCameraPermission()) {
            // Con permiso de camara
            // StartCamera
            startCamera()
        } else {
            requestPermissionLauncher.launch(CAMERA_PERMISSION)
        }

    }

    private fun checkCameraPermission(): Boolean {
        return PermissionChecker.checkSelfPermission(
            requireContext(), CAMERA_PERMISSION
        ) == PermissionChecker.PERMISSION_GRANTED
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        _binding = FragmentPalmistryBinding.inflate(layoutInflater, container, false)
        return binding.root

    }

}