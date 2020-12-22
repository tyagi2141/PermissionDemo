package com.example.permissiondemo

import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import timber.log.Timber
import java.util.*

operator fun CompositeDisposable?.plus(disposable: Disposable?): CompositeDisposable? {
	disposable?.let {
		this?.add(it)
	}
	return this
}


inline fun View.hide() {
	this.visibility = View.GONE
}

inline fun View.show() {
	this.visibility = View.VISIBLE
}

inline fun ImageView.loadImage(url: String?) {
	Glide.with(this).load(url).into(this)
}

inline fun <T> List<T>?.dataAvailable(onAvailable: () -> Unit, onNotAvailable: () -> Unit) {
	if (this != null && size > 0) {
		onAvailable()
	} else {
		onNotAvailable()
	}
}

inline fun <A : Any?, B : Any?, R> let2(v1: A?, v2: B?, callback: (A, B) -> R): R? {
	return v1?.let { v1Verified ->
		v2?.let { v2Verified ->
			callback(v1Verified, v2Verified)
		}
	}
}

fun Fragment.shortToast(text: String) {
	Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
}

fun Timber.start(text: String): Date {
	val date = Date()
	Timber.d("""Time:${date}""")
	Timber.d("""Time:${date}""")
	return date
}

fun <T> List<T>.replace(newValue: T, block: (T) -> Boolean): List<T> {
	return map {
		if (block(it)) newValue else it
	}
}

fun <T> List<T>.replaceOrAdd(newValue: T, block: (T) -> Boolean): List<T> {
	var newList = this.toMutableList()
	var contains = false
	test@ for (element in this) {
		if (block(element)) {
			contains = true
			break@test
		} else {
			contains = false
		}
	}
	if (contains) {
		newList = replace(newValue, block).toMutableList()
	} else {
		newList.add(newValue)
	}
	return newList
}
