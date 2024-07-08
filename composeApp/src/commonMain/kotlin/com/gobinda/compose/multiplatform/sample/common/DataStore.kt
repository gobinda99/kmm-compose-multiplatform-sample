package com.gobinda.compose.multiplatform.sample.common

expect suspend fun Context.putData(key: String, `object`: String)

expect suspend fun Context.getData(key: String): String?

expect suspend fun Context.putData(key: String, `object`: Boolean)

expect suspend fun Context.getBoolean(key: String): Boolean?

