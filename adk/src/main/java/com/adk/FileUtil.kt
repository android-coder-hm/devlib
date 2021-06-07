package com.adk

import android.content.Intent
import androidx.core.content.FileProvider
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

/**
 * 文件存储
 */

/**
 * 获取 SDCard/Android/data/你的应用的包名/files/ 目录，一般放一些长时间保存的数据
 */
fun getAppFileRootFile(dirName: String): File {
    val rootFile = File(GlobalConfig.getAppContext().getExternalFilesDir(null), "${File.separator}${dirName}")
    if (!rootFile.exists()) {
        rootFile.mkdir()
    }
    return rootFile
}

/**
 * 获取 SDCard/Android/data/你的应用包名/cache/目录，一般存放临时缓存数据
 */
fun getAppCacheRootFile(dirName: String): File {
    val rootFile = File(GlobalConfig.getAppContext().externalCacheDir, "${File.separator}${dirName}")
    if (!rootFile.exists()) {
        rootFile.mkdir()
    }
    return rootFile
}

/**
 * 调用系统分享文件
 */
fun shareFileBySystem(filePath: String) {
    val intent = Intent(Intent.ACTION_SEND)
    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
    val uri = FileProvider.getUriForFile(GlobalConfig.getAppContext(), "${GlobalConfig.getAppContext().packageName}.fileprovider", File(filePath))
    intent.putExtra(Intent.EXTRA_STREAM, uri)
    intent.type = "*/*"
    GlobalConfig.getAppContext().startActivity(intent)
}

/**
 * 调用系统分享文件
 */
fun shareTextBySystem(textData: String) {
    val intent = Intent(Intent.ACTION_SEND)
    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
    intent.putExtra(Intent.EXTRA_TEXT, textData) // 分享的内容
    intent.type = "text/plain"
    GlobalConfig.getAppContext().startActivity(intent)
}

/**
 * 时间格式化
 */
fun formatDate(mills: Long, format: String): String {
    val simpleDateFormat = SimpleDateFormat(format, Locale.CHINA)
    return simpleDateFormat.format(Date(mills))
}



