package com.ms.quizapp.utils


import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import android.view.View
import android.view.animation.AlphaAnimation
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.ms.quizapp.data.models.Question

@SuppressLint("MissingPermission")
fun Context.isNetworkConnected(): Boolean {
    val connectivityManager =
        this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    if (connectivityManager != null) {
        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        if (capabilities != null) {
            if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                Log.i("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
                return true
            } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                Log.i("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
                return true
            } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                Log.i("Internet", "NetworkCapabilities.TRANSPORT_ETHERNET")
                return true
            }
        }
    }
    return false
}

// Disables the view for specified duration after click to prevent double click
inline fun View.onSafeClick(delayInClick: Long = 500L, crossinline listener: (View) -> Unit) {
    val enableAgain = Runnable { isEnabled = true }

    setOnClickListener {
        if (isEnabled) {
            isEnabled = false
            postDelayed(enableAgain, delayInClick)
            listener(it)
        }
    }
}

fun Int.isColorDark(): Boolean {
    val darkness =
        1 - (0.299 * Color.red(this) + 0.587 * Color.green(this) + 0.114 * Color.blue(this)) / 255
    return darkness >= 0.5
}

val FragmentManager.currentNavigationFragment: Fragment?
    get() = primaryNavigationFragment?.childFragmentManager?.fragments?.first()


fun Activity.hideKeyBoard() {
    try {
        val view: View? = this.findViewById(android.R.id.content)
        view?.let {
            val inputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
        }
    } catch (e: Exception) {
    }
}

fun View.visible(animate: Boolean = false, animationDuration: Long = 300) {
    if (animate) {
        this.startAnimation(AlphaAnimation(0F, 1F).apply {
            duration = animationDuration
            fillAfter = true
            if (visibility != View.VISIBLE) {
                visibility = View.VISIBLE
            }
        })
    } else {
        visibility = View.VISIBLE
    }
}

/** Set the View visibility to INVISIBLE and eventually animate view alpha till 0% */
fun View.invisible(animate: Boolean = false, animationDuration: Long = 300) {
    if (animate) {
        this.startAnimation(AlphaAnimation(1F, 0F).apply {
            duration = animationDuration
            fillAfter = true
            if (visibility != View.INVISIBLE) {
                visibility = View.INVISIBLE
            }
        })
    } else {
        visibility = View.INVISIBLE
    }
}

/** Set the View visibility to Gone and eventually animate view alpha till 0% */
fun View.gone(animate: Boolean = false, animationDuration: Long = 300) {
    if (animate) {
        this.startAnimation(AlphaAnimation(1F, 0F).apply {
            duration = animationDuration
            fillAfter = true
            if (visibility != View.GONE) {
                visibility = View.GONE
            }
        })
    } else {
        visibility = View.GONE
    }
}

/** Convenient method that chooses between View.visible() or View.invisible() methods */
fun View.visibleOrInvisible(show: Boolean, animate: Boolean = false) {
    if (show) visible(animate) else invisible(animate)
}

fun View.visibleOrGone(show: Boolean, animate: Boolean = false) {
    if (show) visible(animate) else gone()
}

fun Context.showToast(msg:String){
    Toast.makeText(this,msg,Toast.LENGTH_SHORT).show()
}

fun Question.toEntityModel(): Question {
    return Question(
        id = id,
        question = question,
        questionImageUri = questionImageUri,
        option1 = option1,
        option2 = option2,
        option3 = option3,
        correctAnswer = correctAnswer
    )
}

