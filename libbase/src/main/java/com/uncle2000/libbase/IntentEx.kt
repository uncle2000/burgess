package com.uncle2000.libbase

import android.content.Intent
import android.os.Bundle
import java.io.Serializable

/**
 * Created by dengjun on 2018/3/23/023.
 */


// Intent extensions
fun Intent.putSerializableList(key: String, list: List<Serializable>?) = list?.let { putExtra(key, SerializableListHolder(list)) }

fun Intent.getSerializableList(key: String) = (getSerializableExtra(key) as SerializableListHolder?)?.list


// Bundle extensions
fun Bundle.putSerializableList(key: String, list: List<Serializable>?) = list?.let { putSerializable(key, SerializableListHolder(list)) }

fun Bundle.getSerializableList(key: String) = (getSerializable(key) as SerializableListHolder?)?.list

data class SerializableListHolder(var list: List<Serializable>? = null) : Serializable