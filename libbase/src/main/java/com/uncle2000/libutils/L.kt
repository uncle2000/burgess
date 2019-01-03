package com.uncle2000.libutils

import android.os.Environment
import android.os.Process
import android.util.Log
import com.uncle2000.App

import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.HashMap
import java.util.Locale

/**
 * 打印日志的工具类,可以打印到文件,按日期,模块分文件存储
 * Created by danger on 16/8/17.
 */
class L private constructor(private val module: String) {

    /**
     * %d{MM-dd} 大括号中设置日期格式
     * %L level 日志级别
     * %t 当前线程号
     * %p 当前进程号
     * %T tag
     * %M module
     * %m log content
     */
    internal var LOG_FILE_NAME_FORMAT = "%M%d{MM-dd}.txt" // "%T%d{MM-dd}.txt";
    internal var LOG_CONTENT_FORMAT = "%d{HH:mm:ss} [%L][%T][tid:%t][pid:%p] %m\r\n"
    //    int logcatLevel = BuildConfig.forTest ? Level.DEBUG : Level.CLOSED;
    internal var logcatLevel = Level.DEBUG
    //    int fileLevel = BuildConfig.forTest ? Level.DEBUG : Level.CLOSED;
    internal var fileLevel = Level.DEBUG
    private val logFileName: String
    private var logFile: File? = null
    private var fos: FileOutputStream? = null


    init {

        this.logFileName = getFormattedString(LOG_FILE_NAME_FORMAT, module, null, -1, null)
    }

    fun v(msg: String) {
        if (logcatLevel <= Level.VERBOSE)
            Log.v(module, msg)

        if (fileLevel <= Level.VERBOSE)
            printLogToFile(null, Level.VERBOSE, msg)
    }

    fun v(tag: String, msg: String) {
        if (logcatLevel <= Level.VERBOSE)
            Log.v(tag, msg)

        if (fileLevel <= Level.VERBOSE)
            printLogToFile(tag, Level.VERBOSE, msg)
    }


    // 下面四个是默认tag的函数
    fun i(msg: String) {
        if (logcatLevel <= Level.INFO)
            Log.i(module, msg)

        if (fileLevel <= Level.INFO)
            printLogToFile(null, Level.INFO, msg)
    }

    // 下面是传入自定义tag的函数
    fun i(tag: String, msg: String) {
        if (logcatLevel <= Level.INFO)
            Log.i(tag, msg)

        if (fileLevel <= Level.INFO)
            printLogToFile(tag, Level.INFO, msg)
    }

    fun d(msg: String) {
        if (logcatLevel <= Level.DEBUG)
            Log.d(module, msg)

        if (fileLevel <= Level.DEBUG)
            printLogToFile(null, Level.DEBUG, msg)
    }

    fun d(tag: String, msg: String) {
        if (logcatLevel <= Level.DEBUG)
            Log.d(tag, msg)

        if (fileLevel <= Level.DEBUG)
            printLogToFile(tag, Level.DEBUG, msg)
    }

    fun w(msg: String) {
        if (logcatLevel <= Level.WARN)
            Log.w(module, msg)

        if (fileLevel <= Level.WARN)
            printLogToFile(null, Level.WARN, msg)
    }

    fun w(tag: String, msg: String) {
        if (logcatLevel <= Level.WARN)
            Log.w(tag, msg)

        if (fileLevel <= Level.WARN)
            printLogToFile(tag, Level.WARN, msg)
    }

    fun e(msg: String) {
        if (logcatLevel <= Level.ERROR)
            Log.e(module, msg)

        if (fileLevel <= Level.ERROR)
            printLogToFile(null, Level.ERROR, msg)
    }

    @JvmOverloads
    fun e(tag: String, msg: String, throwable: Throwable? = null) {
        if (logcatLevel <= Level.ERROR)
            Log.e(tag, msg, throwable)

        if (fileLevel <= Level.ERROR) {
            printLogToFile(tag, Level.ERROR, msg + "\n" + Log.getStackTraceString(throwable))
        }
    }

    fun getFormattedString(formatter: String, module: String?, tag: String?, level: Int, content: String?): String {
        val b = StringBuilder()
        var i = 0
        while (i < formatter.length) {
            val c = formatter[i]
            if (c != '%') {
                b.append(c)
                i++
                continue
            }

            val c1 = formatter[i + 1]
            when (c1) {
                'd'//  %d{MM-dd} 大括号中设置日期格式
                -> {
                    val end = formatter.indexOf('}', i + 1)
                    if (end != -1) {
                        val dateFormat = SimpleDateFormat(formatter.substring(i + 3, end), Locale.ENGLISH)
                        b.append(dateFormat.format(Date()))
                    }
                    i = end - 1
                }
                'L' // %L level 日志级别
                -> if (level <= Level.ALL) {
                    b.append("ALL")
                } else if (level == Level.VERBOSE) {
                    b.append("VERBOSE")
                } else if (level == Level.DEBUG) {
                    b.append("DEBUG")
                } else if (level == Level.INFO) {
                    b.append("INFO")
                } else if (level == Level.ERROR) {
                    b.append("ERROR")
                } else if (level >= Level.ALL) {
                    b.append("ALL")
                }
                't'// %t 当前线程号
                -> b.append(Thread.currentThread().id)
                'p' // * %p 当前进程号
                -> b.append(Process.myPid())
                'T' // T tag
                -> if (tag != null) b.append(tag)
                'M' //  %M module
                -> if (module != null) b.append(module)
                'm' // * %m log content
                -> if (content != null) b.append(content)
            }
            i++
            i++
        }

        return b.toString()
    }

    private fun printLogToFile(tag: String?, level: Int, content: String) {

        try {
            if (fos == null) {
                logFile = prepareFile(logFileName)
                if (logFile == null) {
                    return
                }
                // logFile = new RandomAccessFile(f, "rws");
                // logFile.seek(logFile.length());
                fos = FileOutputStream(logFile, true)
            }

            val fullContent = getFormattedString(LOG_CONTENT_FORMAT, module, tag, level, content)
            fos!!.write(fullContent.toByteArray(charset("utf-8")))
            fos!!.flush()

            if (!logFile!!.exists()) {
                throw Exception()
            }
            // logFile.write(fullContent.getBytes("utf-8"));
            // logFile.getFD().sync();

        } catch (e: Exception) {
            // e.printStackTrace();
            if (fos != null) {
                try {
                    fos!!.close()
                } catch (e1: IOException) {
                    // e1.printStackTrace();
                }

            }
            fos = null
        }

    }

    private interface Level {
        companion object {
            val ALL = 0
            val VERBOSE = 1
            val DEBUG = 2
            val INFO = 3
            val WARN = 4
            val ERROR = 5
            val CLOSED = 10
        }
    }

    companion object {

        val KEEP_LOG_IN_DAYS = 7 // 只保留7天的日志,7天外的自动删除,前提是文件名以日期命名
        private val instances = HashMap<String, L>()
        private val l = L("default")

        init {
            // 删除过早无用日志
            val logDir = prepareFile(null)
            if (logDir != null) {
                val logFiles = logDir.listFiles()
                if (logFiles != null) {


                    for (f in logFiles) {
                        if (System.currentTimeMillis() - f.lastModified() > KEEP_LOG_IN_DAYS.toLong() * 24L * 3600000) {
                            f.delete()
                        }
                    }
                }
            }
        }

        fun get(): L {

            return l
        }

        operator fun get(module: String?): L {
            if (module == null || module.isEmpty()) {
                return l
            }
            var l = instances[module]
            if (l == null) {
                l = L(module)
                instances[module] = l
            }
            return l
        }

        private fun prepareFile(name: String?): File? {
            val sdStatus = Environment.getExternalStorageState()
            if (sdStatus != Environment.MEDIA_MOUNTED) {
                return null
            }

            val sdcardDir = Environment.getExternalStorageDirectory()
            val logDir = File(sdcardDir.toString() + "/Android/data/" + App.getInstance().packageName + "/log")
            if (!logDir.exists()) {
                if (!logDir.mkdirs()) {
                    return null
                }
            }

            return if (name == null) {
                logDir
            } else File(logDir, name)
        }
    }
}
