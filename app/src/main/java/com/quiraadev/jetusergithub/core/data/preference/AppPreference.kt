package com.quiraadev.jetusergithub.core.data.preference

import com.chibatching.kotpref.KotprefModel

object AppPreference : KotprefModel() {
    var darkMode by booleanPref(false)
}