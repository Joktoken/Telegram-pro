package com.joktoken.telegrampro.ui.theme

import androidx.compose.foundation.isSystemInDarkMode
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// Primary Colors
private val PrimaryLight = Color(0xFF0088CC)
private val PrimaryDark = Color(0xFF0077B5)
private val SecondaryLight = Color(0xFF00BFA5)
private val SecondaryDark = Color(0xFF00897B)

// Accent Colors
private val AccentLight = Color(0xFFFF5722)
private val AccentDark = Color(0xFFE64A19)

// Neutral Colors
private val BackgroundLight = Color(0xFFFAFAFA)
private val BackgroundDark = Color(0xFF121212)
private val SurfaceLight = Color(0xFFFFFFFF)
private val SurfaceDark = Color(0xFF1E1E1E)

// Text Colors
private val TextPrimaryLight = Color(0xFF212121)
private val TextPrimaryDark = Color(0xFFFFFFFF)
private val TextSecondaryLight = Color(0xFF757575)
private val TextSecondaryDark = Color(0xFFBDBDBD)

// Light Color Scheme
private val LightColors = lightColorScheme(
    primary = PrimaryLight,
    onPrimary = Color.White,
    primaryContainer = Color(0xFFE3F2FD),
    onPrimaryContainer = PrimaryDark,
    secondary = SecondaryLight,
    onSecondary = Color.White,
    secondaryContainer = Color(0xFFE0F2F1),
    onSecondaryContainer = SecondaryDark,
    tertiary = AccentLight,
    onTertiary = Color.White,
    tertiaryContainer = Color(0xFFFFEBEE),
    onTertiaryContainer = AccentDark,
    error = Color(0xFFB3261E),
    onError = Color.White,
    errorContainer = Color(0xFFF9DEDC),
    onErrorContainer = Color(0xFF8C0D0D),
    background = BackgroundLight,
    onBackground = TextPrimaryLight,
    surface = SurfaceLight,
    onSurface = TextPrimaryLight,
    surfaceVariant = Color(0xFFF5F5F5),
    onSurfaceVariant = TextSecondaryLight,
    outline = Color(0xFF79747E),
    outlineVariant = Color(0xFFCAC7D0),
    scrim = Color.Black,
    inverseSurface = Color(0xFF313033),
    inverseOnSurface = Color(0xFFF1F0F1),
    inversePrimary = Color(0xFF80DEEA)
)

// Dark Color Scheme
private val DarkColors = darkColorScheme(
    primary = PrimaryLight,
    onPrimary = Color(0xFF003D6B),
    primaryContainer = Color(0xFF005A96),
    onPrimaryContainer = Color(0xFFB3E5FC),
    secondary = SecondaryLight,
    onSecondary = Color(0xFF003D35),
    secondaryContainer = Color(0xFF00584F),
    onSecondaryContainer = Color(0xFF4FFBEA),
    tertiary = AccentLight,
    onTertiary = Color(0xFF5E2817),
    tertiaryContainer = Color(0xFF8B3A1B),
    onTertiaryContainer = Color(0xFFFFDBCB),
    error = Color(0xFFF2B8B5),
    onError = Color(0xFF601410),
    errorContainer = Color(0xFF8C0D0D),
    onErrorContainer = Color(0xFFF9DEDC),
    background = BackgroundDark,
    onBackground = TextPrimaryDark,
    surface = SurfaceDark,
    onSurface = TextPrimaryDark,
    surfaceVariant = Color(0xFF49454F),
    onSurfaceVariant = TextSecondaryDark,
    outline = Color(0xFF938F96),
    outlineVariant = Color(0xFF49454F),
    scrim = Color.Black,
    inverseSurface = Color(0xFFF1F0F1),
    inverseOnSurface = Color(0xFF313033),
    inversePrimary = Color(0xFF0077B5)
)

@Composable
fun TelegramProTheme(
    darkTheme: Boolean = isSystemInDarkMode(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) DarkColors else LightColors

    MaterialTheme(
        colorScheme = colors,
        typography = TelegramProTypography,
        content = content
    )
}
