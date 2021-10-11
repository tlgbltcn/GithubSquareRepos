package com.tlgbltcn.githubsquarerepos.util

import androidx.compose.ui.graphics.Color

fun String.languageColor(): Color {
    return when(this) {
        "Kotlin" -> Color(0xFF8053D1)
        "Java" -> Color(0xFFBB883E)
        "Ruby" -> Color(0xFF6B090A)
        "Objective-C" -> Color(0xFF2976E9)
        "JavaScript" -> Color(0xfff1e05a)
        "CSS" -> Color(0xFF2D1047)
        "Shell" -> Color(0xff89e051)
        "Swift" -> Color(0xFFE21123)
        else -> Color(0xFF52C1E2)
    }
}