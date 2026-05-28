package com.joktoken.telegrampro

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.joktoken.telegrampro.ui.theme.TelegramProTheme

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TelegramProTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LoginScreen(
                        onLoginSuccess = {
                            startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                            finish()
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun LoginScreen(onLoginSuccess: () -> Unit) {
    var currentStep by remember { mutableStateOf(LoginStep.PHONE_INPUT) }
    var phoneNumber by remember { mutableStateOf(\"\") }
    var verificationCode by remember { mutableStateOf(\"\") }
    var isLoading by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf(\"\") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Logo/Title
        Text(
            text = \"Telegram Pro\",
            fontSize = 32.sp,
            fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
            color = Color(0xFF0088CC),
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text(
            text = \"مرحباً بك\",
            fontSize = 16.sp,
            color = Color(0xFF757575),
            modifier = Modifier.padding(bottom = 32.dp)
        )

        // Error Message
        if (errorMessage.isNotEmpty()) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFFFEBEE)
                ),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(
                    text = errorMessage,
                    color = Color(0xFFB3261E),
                    modifier = Modifier.padding(12.dp),
                    fontSize = 14.sp
                )
            }
        }

        when (currentStep) {
            LoginStep.PHONE_INPUT -> {
                PhoneInputStep(
                    phoneNumber = phoneNumber,
                    onPhoneChange = { phoneNumber = it },
                    isLoading = isLoading,
                    onNext = {
                        if (phoneNumber.isNotEmpty()) {
                            isLoading = true
                            // محاكاة طلب API
                            android.os.Handler(android.os.Looper.getMainLooper()).postDelayed({
                                isLoading = false
                                currentStep = LoginStep.CODE_INPUT
                                errorMessage = \"\"
                            }, 1000)
                        } else {
                            errorMessage = \"الرجاء إدخال رقم الهاتف\"
                        }
                    }
                )
            }

            LoginStep.CODE_INPUT -> {
                CodeInputStep(
                    verificationCode = verificationCode,
                    onCodeChange = { verificationCode = it },
                    isLoading = isLoading,
                    onVerify = {
                        if (verificationCode.isNotEmpty() && verificationCode.length >= 4) {
                            isLoading = true
                            // محاكاة التحقق
                            android.os.Handler(android.os.Looper.getMainLooper()).postDelayed({
                                isLoading = false
                                onLoginSuccess()
                            }, 1500)
                        } else {
                            errorMessage = \"الرجاء إدخال كود التحقق الصحيح\"
                        }
                    },
                    onBack = {
                        currentStep = LoginStep.PHONE_INPUT
                        verificationCode = \"\"
                        errorMessage = \"\"
                    }
                )
            }
        }
    }
}

@Composable
private fun PhoneInputStep(
    phoneNumber: String,
    onPhoneChange: (String) -> Unit,
    isLoading: Boolean,
    onNext: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)
    ) {
        OutlinedTextField(
            value = phoneNumber,
            onValueChange = onPhoneChange,
            label = { Text(\"رقم الهاتف\") },
            leadingIcon = {
                Icon(Icons.Filled.Phone, contentDescription = null)
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp),
            shape = RoundedCornerShape(8.dp)
        )

        Button(
            onClick = onNext,
            enabled = !isLoading && phoneNumber.isNotEmpty(),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(8.dp)
        ) {
            if (isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.size(24.dp),
                    color = Color.White,
                    strokeWidth = 2.dp
                )
            } else {
                Text(
                    \"التالي\",
                    fontSize = 16.sp,
                    fontWeight = androidx.compose.ui.text.font.FontWeight.Bold
                )
            }
        }

        Text(
            text = \"سيتم إرسال كود التحقق عبر رسالة نصية\",
            fontSize = 12.sp,
            color = Color(0xFF757575),
            modifier = Modifier.padding(top = 16.dp),
            textAlign = androidx.compose.ui.text.style.TextAlign.Center
        )
    }
}

@Composable
private fun CodeInputStep(
    verificationCode: String,
    onCodeChange: (String) -> Unit,
    isLoading: Boolean,
    onVerify: () -> Unit,
    onBack: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)
    ) {
        OutlinedTextField(
            value = verificationCode,
            onValueChange = { if (it.length <= 6) onCodeChange(it) },
            label = { Text(\"كود التحقق\") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp),
            shape = RoundedCornerShape(8.dp)
        )

        Button(
            onClick = onVerify,
            enabled = !isLoading && verificationCode.isNotEmpty(),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(8.dp)
        ) {
            if (isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.size(24.dp),
                    color = Color.White,
                    strokeWidth = 2.dp
                )
            } else {
                Text(
                    \"التحقق\",
                    fontSize = 16.sp,
                    fontWeight = androidx.compose.ui.text.font.FontWeight.Bold
                )
            }
        }

        TextButton(
            onClick = onBack,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        ) {
            Text(
                \"رجوع\",
                color = Color(0xFF0088CC),
                fontSize = 14.sp
            )
        }
    }
}

enum class LoginStep {
    PHONE_INPUT,
    CODE_INPUT
}
