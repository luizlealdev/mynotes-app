package dev.luizleal.mynotes.domain.model

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.lerp

enum class FolderColor(val hex: Long) {

    RED(0xFFFF383C),
    ORANGE(0xFFFF8D28),
    YELLOW(0xFFFFCC00),
    GREEN(0xFF34C759),
    MINT(0xFF00C8B3),
    BLUE(0xFF2E6DEE),
    INDIGO(0xFF6155F5),
    PURPLE(0xFFFF2D55),
    BROWN(0xFFAC7F5E);

    fun darken() = lerp(Color.Black, Color(hex), 0.75f)
}