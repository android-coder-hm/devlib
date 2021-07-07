package com.adk

import android.content.Intent
import android.media.MediaMetadataRetriever
import android.net.Uri
import android.os.Build
import androidx.core.content.FileProvider
import java.io.File
import java.lang.Exception
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
    val shareFile = File(filePath)
    if (!shareFile.exists()) {
        return
    }
    val intent = Intent(Intent.ACTION_SEND)
    val uri: Uri?
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        uri = FileProvider.getUriForFile(GlobalConfig.getAppContext(), "${GlobalConfig.getAppContext().packageName}.fileprovider", shareFile)
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
    } else {
        uri = Uri.fromFile(shareFile)
    }

    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
    intent.putExtra(Intent.EXTRA_STREAM, uri)
    intent.type = getMimeType(filePath)
    GlobalConfig.getAppContext().startActivity(intent)
}

private fun getMimeType(filePath: String): String {
    val mediaMetadataRetriever = MediaMetadataRetriever()
    var mime = "*/*"
    return try {
        mediaMetadataRetriever.setDataSource(filePath)
        mime = mediaMetadataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_MIMETYPE) ?: "*/*"
        mime
    } catch (e: Exception) {
        mime
    }
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



