package com.hansoolabs.hlog

import android.util.Log

@Suppress("MemberVisibilityCanBePrivate", "unused")
object HLog {

    var defaultTag: String = "HLog"

    private val level = Level.VERBOSE

    enum class Level {
        NONE,
        ERROR,
        WARNING,
        INFO,
        DEBUG,
        VERBOSE
    }

    @JvmStatic
    fun e(tag: String? = null, className: String, e: Throwable?) {
        e(tag ?: defaultTag, className, Log.getStackTraceString(e))
    }

    @JvmStatic
    fun e(tag: String? = null, className: String, msg: String? = null) {
        if (Level.ERROR.ordinal <= level.ordinal) {
            val thr = Thread.currentThread().name
            val text = "[$thr] $className ${msg ?: ""}"
            Log.e(tag ?: defaultTag, text)
        }
    }

    @JvmStatic
    fun w(tag: String? = null, className: String, vararg args: Any?) {
        if (Level.WARNING.ordinal <= level.ordinal) {
            val thr = Thread.currentThread().name
            val msg = args.joinToString()
            val text = "[$thr] $className $msg"
            Log.w(tag ?: defaultTag, text)
        }
    }

    @JvmStatic
    fun i(tag: String? = null, className: String, msg: Any? = null) {
        val thr = Thread.currentThread().name
        val text = "[$thr] $className $msg"
        Log.i(tag ?: defaultTag, text)
    }

    @JvmStatic
    fun i(tag: String? = null, className: String, vararg args: Any?) {
        val thr = Thread.currentThread().name
        val msg = args.joinToString()
        val text = "[$thr] $className $msg"
        Log.i(tag ?: defaultTag, text)
    }

    @JvmStatic
    @JvmOverloads
    fun d(tag: String? = null, className: String, msg: Any? = null) {
        if (Level.DEBUG.ordinal <= level.ordinal) {
            val the = Thread.currentThread().name
            val text = "[$the] $className ${msg?.toString() ?: ""}"
            Log.d(tag ?: defaultTag, text)
        }
    }

    @JvmStatic
    fun d(tag: String? = null, className: String, vararg args: Any?) {
        if (Level.DEBUG.ordinal <= level.ordinal) {
            val the = Thread.currentThread().name
            val msg = args.joinToString()
            val text = "[$the] $className $msg"
            Log.d(tag ?: defaultTag, text)
        }
    }

    @JvmStatic
    fun v(tag: String? = null, className: String, msg: Any? = null) {
        if (Level.VERBOSE.ordinal <= level.ordinal) {
            val thr = Thread.currentThread().name
            val text = "[$thr] $className ${msg?.toString() ?: ""}"
            Log.v(tag ?: defaultTag, text)
        }
    }

    @JvmStatic
    fun v(tag: String? = null, className: String, vararg args: Any?) {
        if (Level.VERBOSE.ordinal <= level.ordinal) {
            val thr = Thread.currentThread().name
            val msg = args.joinToString()
            val text = "[$thr] $className $msg"
            Log.v(tag ?: defaultTag, text)
        }
    }
}



fun Any.hLogError(tag: String, b: () -> Throwable?) {
    val className = this::class.java.simpleName + "@" + Integer.toHexString(hashCode())
    HLog.e(tag, className, b.invoke())
}

fun Any.hLogError(tag: String, b: ()-> String?) {
    val className = this::class.java.simpleName + "@" + Integer.toHexString(hashCode())
    HLog.e(tag, className, b.invoke())
}

fun Any.hLog(tag: String, b: ()-> String?) {
    val className = this::class.java.simpleName + "@" + Integer.toHexString(hashCode())
    HLog.d(tag, className, b.invoke())
}
