package com.example.permissiondemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.karumi.dexter.Dexter
import com.karumi.dexter.listener.multi.CompositeMultiplePermissionsListener

/**
 * Created by Rahul on 22/12/20.
 */
open class BaseActivity :AppCompatActivity(){

     lateinit var viewModel: FirstViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this).get(FirstViewModel::class.java)

    }


    fun checkPermission(
        permissions: List<String>,
        permissionsListeners: CompositeMultiplePermissionsListener
    ) {

        Dexter.withActivity(this)
            .withPermissions(permissions).withListener(permissionsListeners)
            .check()
    }
}