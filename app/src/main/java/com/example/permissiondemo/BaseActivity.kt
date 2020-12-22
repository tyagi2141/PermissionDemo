package com.example.permissiondemo

import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.widget.ProgressBar
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.karumi.dexter.Dexter
import com.karumi.dexter.listener.multi.CompositeMultiplePermissionsListener
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Created by Rahul on 22/12/20.
 */
open class BaseActivity(val enabled: Boolean = false) :AppCompatActivity(){

    // lateinit var viewModel: BaseViewModel
    var progressBar: ProgressBar? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        progressBar = this.progress_bar

       // viewModel = ViewModelProvider(this).get(BaseViewModel::class.java)

    }


    fun checkPermission(
        permissions: List<String>,
        permissionsListeners: CompositeMultiplePermissionsListener
    ) {

        Dexter.withActivity(this)
            .withPermissions(permissions).withListener(permissionsListeners)
            .check()
    }

    inner class NoInternetLiveData : Observer<Boolean> {

        override fun onChanged(noInternet: Boolean) {
            if (noInternet) {

                ToastDialog(
                    this@BaseActivity,
                    "No Internet Available"
                ).show()

            }
        }
    }

    inner class LoadingLiveDataObserver : Observer<Boolean> {

        override fun onChanged(loading: Boolean) {

            if (loading) {
                show()

            } else {
                hide()
            }
        }
    }

    fun show() {
        progressBar?.let {


            if (!(it.isVisible) && !(it.isShown)) {
                it.show()
                this?.window?.setFlags(
                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
                );
            }
        }

        val callback: OnBackPressedCallback = object : OnBackPressedCallback(enabled) {
            override fun handleOnBackPressed() {
                hide()
                remove()
                onBackPressed()
            }
        }
        this.onBackPressedDispatcher.addCallback(callback)
    }


    fun hide() {
        progressBar?.let {
            if ((it.isVisible) && (it.isShown)) {
                it.hide()
                this.window?.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            }
        }
    }

}