package com.adk

import android.app.Activity
import android.content.Context
import android.graphics.Point
import android.util.Log
import android.util.Size
import android.view.View
import android.view.WindowManager
import android.widget.Toast

/**
 * 显示toast信息
 */
fun showToast(msg: String) {
    Toast.makeText(GlobalConfig.getAppContext(), msg, Toast.LENGTH_LONG).show()
}

/**
 * 设置状态栏颜色
 */
fun setStatusBarColor(activity: Activity?, colorId: Int) {
    activity?.let {
        val window = it.window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = getColorRes(colorId)
    }

}

/**
 * 设置状态栏字体颜色
 */
@Suppress("DEPRECATION")
fun setStatusBarFontColor(activity: Activity?, isLight: Boolean = true) {
    activity?.let {
        val decor = it.window.decorView
        if (isLight) {
            decor.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        } else {
            decor.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        }
    }
}

/**
 * 获取屏幕大小
 */
@Suppress("DEPRECATION")
fun getScreenSize(): Size {
    val windowManager = GlobalConfig.getAppContext().getSystemService(Context.WINDOW_SERVICE) as WindowManager
    val display = windowManager.defaultDisplay
    val sizePoint = Point()
    display.getSize(sizePoint)
    return Size(sizePoint.x, sizePoint.y)
}

/**
 * 将sp转化为px
 */
fun sp2px(spValue: Float): Int {
    val scale = GlobalConfig.getAppContext().resources.displayMetrics.density
    return (spValue / scale + 0.5f).toInt()
}

fun sp2px(spValue: Int): Int {
    val scale = GlobalConfig.getAppContext().resources.displayMetrics.density
    return (spValue / scale + 0.5f).toInt()
}

/**
 * dp转换为px
 */
fun dp2px(dpValue: Float): Int {
    val scale = GlobalConfig.getAppContext().resources.displayMetrics.density
    return (dpValue * scale + 0.5f).toInt()
}

fun dp2px(dpValue: Int): Int {
    val scale = GlobalConfig.getAppContext().resources.displayMetrics.density
    return (dpValue * scale + 0.5f).toInt()
}


/**
 * 打印调试日志信息 受是否是debug模式控制 release模式下不会打印
 */
fun logInfo(tag: String? = null, msg: String) {
    if (GlobalConfig.isDebugModel()) {
        Log.d(tag ?: "Log", msg)
    }
}

/**
 * 打印错误信息 不受控制 完全打印
 */
fun logError(tag: String, msg: String) {
    Log.e(tag, "出错信息:$msg")
}