package com.example.permissiondemo


import android.Manifest
import android.os.Bundle
import android.widget.Toast
import com.karumi.dexter.listener.multi.CompositeMultiplePermissionsListener

class MainActivity : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        checkPermission(
            listOf(
                Manifest.permission.CAMERA,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ),
            CompositeMultiplePermissionsListener(
                viewModel.permissionListenersCameraStorage(
                    this,
                    onAccepted = {
                        Toast.makeText(this, "yes", Toast.LENGTH_SHORT).show()
                    })
            )
        )
    }
}