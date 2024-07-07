package com.gobinda.compose.multiplatform.sample.utils



private val EMAIL_PATTERN = Regex(
    "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

private val  PASSWORD_PATTERN = Regex(
    "((?=.*\\d)(?=.*[A-Z])(?=.*[a-z]).{6,8})$")


val String?.isValidEmail get() = this != null && EMAIL_PATTERN.matches(this)

val String?.isValidPassword get() = this !=null && PASSWORD_PATTERN.matches(this)