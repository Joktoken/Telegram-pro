package com.joktoken.telegrampro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.joktoken.telegrampro.ui.theme.TelegramProTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TelegramProTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    HomeScreen()
                }
            }
        }
    }
}

@Composable
fun HomeScreen() {
    var showMenu by remember { mutableStateOf(false) }
    var showPostDialog by remember { mutableStateOf(false) }

    Scaffold(
        bottomBar = {
            BottomAppBar(
                modifier = Modifier.height(70.dp),
                containerColor = MaterialTheme.colorScheme.surface,
                contentPadding = PaddingValues(horizontal = 16.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(70.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Settings Button (Left)
                    IconButton(
                        onClick = { /* Settings */ },
                        modifier = Modifier.size(50.dp)
                    ) {
                        Icon(
                            Icons.Filled.Settings,
                            contentDescription = "الإعدادات",
                            tint = Color(0xFF0088CC),
                            modifier = Modifier.size(28.dp)
                        )
                    }

                    Spacer(modifier = Modifier.weight(1f))

                    // Wallet Button (Center - Large)
                    Button(
                        onClick = { /* Open Wallet */ },
                        modifier = Modifier
                            .size(65.dp)
                            .clip(CircleShape),
                        shape = CircleShape,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF0088CC)
                        ),
                        elevation = ButtonDefaults.buttonElevation(
                            defaultElevation = 8.dp
                        )
                    ) {
                        Icon(
                            Icons.Filled.Wallet,
                            contentDescription = "المحفظة",
                            tint = Color.White,
                            modifier = Modifier.size(32.dp)
                        )
                    }

                    Spacer(modifier = Modifier.weight(1f))

                    // Post Button (Right)
                    IconButton(
                        onClick = { showPostDialog = true },
                        modifier = Modifier.size(50.dp)
                    ) {
                        Icon(
                            Icons.Filled.Edit,
                            contentDescription = "نشر",
                            tint = Color(0xFF0088CC),
                            modifier = Modifier.size(28.dp)
                        )
                    }
                }
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            // Header
            HeaderSection()

            // Feed
            FeedSection()
        }
    }

    // Side Menu
    if (showMenu) {
        SideMenuOverlay(
            onDismiss = { showMenu = false }
        )
    }
}

@Composable
fun HeaderSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface)
            .padding(16.dp)
    ) {
        Text(
            text = "Telegram Pro",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF0088CC)
        )
        Text(
            text = "آخر التحديثات والمنشورات",
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
    Divider(
        color = MaterialTheme.colorScheme.surfaceVariant,
        thickness = 1.dp
    )
}

@Composable
fun FeedSection() {
    val posts = listOf(
        Post(
            id = 1,
            author = "مسؤول القناة",
            content = "مرحباً بك في Telegram Pro! تطبيق جديد وحديث لتجربة أفضل.",
            timestamp = "قبل دقيقة",
            likes = 123,
            comments = 45
        ),
        Post(
            id = 2,
            author = "مستخدم نشط",
            content = "يعجبني هذا التطبيق الجديد، واجهة رائعة وسهلة الاستخدام!",
            timestamp = "قبل 5 دقائق",
            likes = 89,
            comments = 23
        ),
        Post(
            id = 3,
            author = "فريق التطوير",
            content = "نعمل على إضافة ميزات جديدة مثل الحافظ الرقمية والتحويلات السريعة.",
            timestamp = "قبل ساعة",
            likes = 234,
            comments = 67
        ),
        Post(
            id = 4,
            author = "مجتمع Telegram",
            content = "انضم إلى مجتمعنا الآن واستمتع بأفضل التجارب!",
            timestamp = "قبل 3 ساعات",
            likes = 456,
            comments = 120
        )
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentPadding = PaddingValues(bottom = 16.dp)
    ) {
        items(posts) { post ->
            PostCard(post)
        }
    }
}

@Composable
fun PostCard(post: Post) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 8.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            // Author
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    modifier = Modifier
                        .size(44.dp)
                        .clip(CircleShape),
                    color = Color(0xFF0088CC)
                ) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Text(
                            text = post.author.first().toString(),
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )
                    }
                }

                Column(
                    modifier = Modifier
                        .padding(start = 12.dp)
                        .weight(1f)
                ) {
                    Text(
                        text = post.author,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface,
                        fontSize = 14.sp
                    )
                    Text(
                        text = post.timestamp,
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                IconButton(onClick = {}) {
                    Icon(
                        Icons.Filled.MoreVert,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            // Content
            Text(
                text = post.content,
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = 14.sp,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Actions
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                ActionButton(
                    icon = Icons.Filled.ThumbUp,
                    text = "${post.likes}",
                    modifier = Modifier.weight(1f)
                )
                ActionButton(
                    icon = Icons.Filled.ChatBubble,
                    text = "${post.comments}",
                    modifier = Modifier.weight(1f)
                )
                ActionButton(
                    icon = Icons.Filled.Share,
                    text = "مشاركة",
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

@Composable
fun ActionButton(
    icon: androidx.compose.material.icons.Icons.Filled,
    text: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .height(36.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(MaterialTheme.colorScheme.surfaceVariant),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            icon,
            contentDescription = null,
            tint = Color(0xFF0088CC),
            modifier = Modifier.size(16.dp)
        )
        Text(
            text = text,
            fontSize = 12.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(start = 6.dp, end = 8.dp)
        )
    }
}

@Composable
fun SideMenuOverlay(onDismiss: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.5f))
    ) {
        Card(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .fillMaxHeight()
                .width(250.dp),
            shape = RoundedCornerShape(topStart = 20.dp, bottomStart = 20.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Top
            ) {
                Text(
                    text = "القائمة",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 24.dp)
                )

                MenuItem("💬 الرسائل")
                MenuItem("🤖 البوتات")
                MenuItem("📄 الصفحات")
                MenuItem("👥 المجموعات")
            }
        }
    }
}

@Composable
fun MenuItem(label: String) {
    Text(
        text = label,
        fontSize = 16.sp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp, horizontal = 8.dp)
    )
    Divider()
}

data class Post(
    val id: Int,
    val author: String,
    val content: String,
    val timestamp: String,
    val likes: Int,
    val comments: Int
)
