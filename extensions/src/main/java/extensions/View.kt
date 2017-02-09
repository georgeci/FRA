package extensions

import android.support.annotation.LayoutRes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import io.reactivex.functions.Consumer

fun ViewGroup.inflate(@LayoutRes layoutId: Int): View = LayoutInflater.from(context).inflate(layoutId, this, false)

fun TextView.changeText(): Consumer<String> = Consumer {
    if (text.toString() != it) {
        text = it
    }
}

fun View.showToast(): Consumer<String> = Consumer {
    Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
}