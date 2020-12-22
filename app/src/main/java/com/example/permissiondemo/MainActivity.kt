package com.example.permissiondemo


import android.Manifest
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.karumi.dexter.listener.multi.CompositeMultiplePermissionsListener

class MainActivity : BaseActivity() {

       lateinit var viewModel: FirstViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(FirstViewModel::class.java)
       viewModel.loadingDataLiveData.observe(this, LoadingLiveDataObserver())

        viewModel.noInternetLiveData.observe(this, NoInternetLiveData())
viewModel.showprogress()
        show()

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
                        //hide()
                        Toast.makeText(this, "yes", Toast.LENGTH_SHORT).show()
                    })
            )
        )
    }
}