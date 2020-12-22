package com.example.permissiondemo

import android.content.Context
import androidx.lifecycle.ViewModel
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.DialogOnAnyDeniedMultiplePermissionsListener
import com.karumi.dexter.listener.multi.MultiplePermissionsListener


class FirstViewModel(
) : ViewModel() {



    fun permissionListenersCameraStorage(
        activityContext: Context,
        onAccepted: () -> Unit
    ): MutableList<MultiplePermissionsListener> {

        val list = mutableListOf<MultiplePermissionsListener>()

        list.add(
            DialogOnAnyDeniedMultiplePermissionsListener.Builder
                .withContext(activityContext)
                .withTitle("Camera & Storage")
                .withMessage("Both camera and storage permission are required to proceed")
                .withButtonText(android.R.string.ok)
                .withIcon(R.mipmap.ic_launcher)
                .build()
        )

        list.add(object : MultiplePermissionsListener {
            override fun onPermissionsChecked(report: MultiplePermissionsReport?) {
                report?.let {
                    if (report.areAllPermissionsGranted()) {
                        onAccepted()
                    }
                }
            }

            override fun onPermissionRationaleShouldBeShown(
                permissions: MutableList<PermissionRequest>?,
                token: PermissionToken?
            ) {
                token?.continuePermissionRequest()
            }
        })

        return list
    }

    fun permissionListenersLocation(
        activityContext: Context,
        onAccepted: () -> Unit
    ): MutableList<MultiplePermissionsListener> {
        val list = mutableListOf<MultiplePermissionsListener>()
        list.add(
            DialogOnAnyDeniedMultiplePermissionsListener.Builder
                .withContext(activityContext)
                .withTitle("Location")
                .withMessage("Location permission required to proceed")
                .withButtonText(android.R.string.ok)
                .withIcon(R.mipmap.ic_launcher)
                .build()

        )
        list.add(object : MultiplePermissionsListener {
            override fun onPermissionsChecked(report: MultiplePermissionsReport?) {
                report?.let {
                    if (report.areAllPermissionsGranted()) {
                        onAccepted()
                    }
                }
            }

            override fun onPermissionRationaleShouldBeShown(
                permissions: MutableList<PermissionRequest>?,
                token: PermissionToken?
            ) {
                token?.continuePermissionRequest()
            }
        })
        return list
    }


}
