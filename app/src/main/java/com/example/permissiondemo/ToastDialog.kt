package com.example.permissiondemo

import android.app.Activity
import android.app.Dialog
import android.graphics.Color
import android.graphics.Point
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.Window
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.dialog_toast.*


class ToastDialog(
	val activity: Activity,
	val message: String,
	val OnOkListener: () -> Unit = {},
	val title: String? = null,
	val cancellable :Boolean = false
) :
	Dialog(activity) {

	companion object {
		public const val TAG = "ToastDialog"

	}

	override fun onCreate(savedInstanceState: Bundle?) {
		requestWindowFeature(Window.FEATURE_NO_TITLE)
		super.onCreate(savedInstanceState)
		setContentView(R.layout.dialog_toast)

		prepareDialog()
		initialize()


	}

	private fun initialize() {
		clickHandler()

		if (title != null) {
			tvTitle.text = title
			tvTitle.show()
		}

		tvMessage.text = message
	}


	private fun clickHandler() {
		btnOk.setOnClickListener {
			dismiss()
			OnOkListener()
		}
	}

	private fun prepareDialog() {
		var width = 0
		var height = 0
		val size = Point()
		val w = activity.windowManager
		w.defaultDisplay.getSize(size)
		width = size.x - 40
		height = size.y

		setCancelable(cancellable)
		setCanceledOnTouchOutside(cancellable)
		window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
		window!!.setLayout(width, LinearLayout.LayoutParams.WRAP_CONTENT)
	}

}
