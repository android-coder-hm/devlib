package com.adk

import android.graphics.Color
import android.graphics.drawable.Drawable
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat

/**
 * 获取字符串资源
 * @param stringResId 字符资源ID
 */
fun getStringRes(@StringRes stringResId: Int): String = GlobalConfig.getAppContext().getString(stringResId)


/**
 * 获取颜色资源
 * @param colorResId 颜色资源ID
 */
fun getColorRes(@ColorRes colorResId: Int) = ContextCompat.getColor(GlobalConfig.getAppContext(), colorResId)


/**
 * 获取颜色资源 通过颜色字符串值
 */
fun getColorByString(colorString: String) = Color.parseColor(colorString)


/**
 * 获取drawable资源
 * @param drawableRes drawable资源ID
 */
fun getDrawableRes(@DrawableRes drawableRes: Int): Drawable? =
    ContextCompat.getDrawable(GlobalConfig.getAppContext(), drawableRes)

