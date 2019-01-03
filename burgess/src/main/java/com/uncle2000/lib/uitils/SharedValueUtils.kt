//package com.uncle2000.lib.uitils
//
//import android.content.Context
//import com.google.gson.Gson
//import com.tencent.mmkv.MMKV
//
//object SharedValueUtils {
//
//    fun init(context: Context) {
//        MMKV.initialize(context)
//    }
//
//    fun putAny(key: String, value: Any) {
//        val kv = MMKV.defaultMMKV()
//        when (value) {
//            is Boolean -> kv.encode(key, value)
//            is Int -> kv.encode(key, value)
//            is Float -> kv.encode(key, value)
//            is Long -> kv.encode(key, value)
//            is String -> kv.encode(key, value)
//            is ToJsonStringInterface -> kv.encode(key, value.toJson())
//        }
//    }
//
//    fun getAny(key: String, defaultValue: Any): Any {
//        val kv = MMKV.defaultMMKV()
//        return if (kv.contains(key)) {
//            when (defaultValue) {
//                is Boolean -> kv.decodeBool(key)
//                is Int -> kv.decodeInt(key)
//                is Float -> kv.decodeFloat(key)
//                is Long -> kv.decodeLong(key)
//                is String -> kv.decodeString(key)
//                else -> defaultValue
//            }
//        } else {
//            defaultValue
//        }
//    }
//
//    fun removeValue(key: String) {
//        val kv = MMKV.defaultMMKV()
//        kv.removeValueForKey(key)
//    }
//
//    fun <T>  getObj(key: String, classOfT: Class<T>): T? {
//        val kv = MMKV.defaultMMKV()
//        val jsonString = kv.decodeString(key)
//        if (jsonString.isNullOrEmpty())
//            return null
//        return Gson().fromJson<T>(jsonString, classOfT)
//    }
//
//    fun getBoolean(key: String): Boolean? {
//        val kv = MMKV.defaultMMKV()
//        return kv.decodeBool(key)
//    }
//
//    fun getBoolean(key: String, defaultValue: Boolean): Boolean {
//        val kv = MMKV.defaultMMKV()
//        return kv.decodeBool(key, defaultValue)
//    }
//
//    fun getString(key: String): String? {
//        val kv = MMKV.defaultMMKV()
//        return kv.decodeString(key)
//    }
//
//    fun getString(key: String, defaultValue: String): String {
//        val kv = MMKV.defaultMMKV()
//        return kv.decodeString(key, defaultValue)
//    }
//
//    fun getInt(key: String): Int? {
//        val kv = MMKV.defaultMMKV()
//        return kv.decodeInt(key)
//    }
//
//    fun getInt(key: String, defaultValue: Int): Int {
//        val kv = MMKV.defaultMMKV()
//        return kv.decodeInt(key, defaultValue)
//    }
//
//    fun getLong(key: String): Long? {
//        val kv = MMKV.defaultMMKV()
//        return kv.decodeLong(key)
//    }
//
//    fun getLong(key: String, defaultValue: Long): Long {
//        val kv = MMKV.defaultMMKV()
//        return kv.decodeLong(key, defaultValue)
//    }
//}
//
//interface ToJsonStringInterface {
//    fun toJson(): String
//}
