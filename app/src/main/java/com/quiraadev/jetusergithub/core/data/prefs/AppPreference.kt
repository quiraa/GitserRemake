package com.quiraadev.jetusergithub.core.data.prefs

import com.chibatching.kotpref.KotprefModel

object AppPreference : KotprefModel() {
    var darkMode by booleanPref(false)
}