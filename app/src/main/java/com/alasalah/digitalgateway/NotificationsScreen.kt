package com.alasalah.digitalgateway

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.automirrored.outlined.ReceiptLong
import androidx.compose.material.icons.outlined.Campaign
import androidx.compose.material.icons.outlined.Schedule
import androidx.compose.material.icons.outlined.Verified
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Notification model representing a single notification item.
 * Purely local mock data matching the application identity.
 */
data class NotificationItem(
    val id: String,
    val title: String,
    val description: String,
    val type: String,
    val status: String? = null,
    val icon: ImageVector,
    val accentColor: Color,
    val testTag: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotificationsScreen(
    onBackClick: () -> Unit = {}
) {
    BackHandler {
        onBackClick()
    }

    val broadcastBackground = Brush.verticalGradient(
        colors = listOf(
            Color(0xFF1B3D4F), // Medium petroleum blue
            Color(0xFF174758), // Subtle muted teal
            Color(0xFF1A3B4D)  // Digital petroleum blue
        )
    )

    val notifications = remember {
        listOf(
            NotificationItem(
                id = "notif_1",
                title = "تم تفعيل الاشتراك",
                description = "تم تحديث حالة اشتراكك بنجاح",
                type = "اشتراك",
                status = "جديد",
                icon = Icons.Outlined.Verified,
                accentColor = Color(0xFF34D399), // Green: successful actions
                testTag = "notification_item_1"
            ),
            NotificationItem(
                id = "notif_2",
                title = "تذكير بالتجديد",
                description = "متبقي 16 يوم على انتهاء الاشتراك",
                type = "تنبيه",
                icon = Icons.Outlined.Schedule,
                accentColor = Color(0xFFFBBF24), // Amber: warnings/reminders
                testTag = "notification_item_2"
            ),
            NotificationItem(
                id = "notif_3",
                title = "تحديث جديد في شبكة الأصالة",
                description = "تابع آخر الأخبار والخدمات الجديدة",
                type = "أخبار",
                icon = Icons.Outlined.Campaign,
                accentColor = Color(0xFF38BDF8), // Blue: news
                testTag = "notification_item_3"
            ),
            NotificationItem(
                id = "notif_4",
                title = "طلب تجديد قيد المراجعة",
                description = "تم استلام إثبات الدفع وجاري مراجعة الطلب",
                type = "عمليات",
                icon = Icons.AutoMirrored.Outlined.ReceiptLong,
                accentColor = Color(0xFFA855F7), // Purple: operations
                testTag = "notification_item_4"
            )
        )
    }

    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(broadcastBackground)
        ) {
            Scaffold(
                modifier = Modifier
                    .fillMaxSize()
                    .testTag("notifications_screen_scaffold"),
                containerColor = Color.Transparent,
                topBar = {
                    TopAppBar(
                        title = {
                            Text(
                                text = "الإشعارات",
                                style = MaterialTheme.typography.titleLarge.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFFF8FAFC),
                                    fontSize = 20.sp
                                ),
                                modifier = Modifier.testTag("notifications_screen_title")
                            )
                        },
                        navigationIcon = {
                            IconButton(
                                onClick = onBackClick,
                                modifier = Modifier.testTag("notifications_back_button")
                            ) {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Outlined.ArrowBack,
                                    contentDescription = "الرجوع",
                                    tint = Color(0xFFF8FAFC)
                                )
                            }
                        },
                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = Color.Transparent
                        )
                    )
                }
            ) { innerPadding ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                        .padding(horizontal = 20.dp)
                        .verticalScroll(rememberScrollState())
                        .testTag("notifications_content_column"),
                    verticalArrangement = Arrangement.spacedBy(14.dp)
                ) {
                    Spacer(modifier = Modifier.height(4.dp))

                    notifications.forEach { item ->
                        NotificationCard(item = item)
                    }

                    Spacer(modifier = Modifier.height(20.dp))
                }
            }
        }
    }
}

@Composable
private fun NotificationCard(
    item: NotificationItem
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .testTag(item.testTag),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0x2EFFFFFF)
        ),
        border = BorderStroke(1.dp, item.accentColor.copy(alpha = 0.35f))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            // Icon container with accent color
            Surface(
                shape = RoundedCornerShape(12.dp),
                color = item.accentColor.copy(alpha = 0.16f),
                border = BorderStroke(1.dp, item.accentColor.copy(alpha = 0.35f)),
                modifier = Modifier.size(46.dp)
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = null,
                        tint = item.accentColor,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }

            // Notification body
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                // Header row: Type badge + Status badge (if available)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Small category label badge
                    Surface(
                        shape = RoundedCornerShape(6.dp),
                        color = item.accentColor.copy(alpha = 0.18f),
                        border = BorderStroke(0.8.dp, item.accentColor.copy(alpha = 0.4f))
                    ) {
                        Text(
                            text = item.type,
                            style = MaterialTheme.typography.labelSmall.copy(
                                color = item.accentColor,
                                fontWeight = FontWeight.Bold,
                                fontSize = 11.sp
                            ),
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                        )
                    }

                    if (item.status != null) {
                        Surface(
                            shape = RoundedCornerShape(6.dp),
                            color = Color(0x3334D399),
                            border = BorderStroke(0.8.dp, Color(0x6634D399))
                        ) {
                            Text(
                                text = item.status,
                                style = MaterialTheme.typography.labelSmall.copy(
                                    color = Color(0xFF86EFAC),
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 11.sp
                                ),
                                modifier = Modifier
                                    .padding(horizontal = 8.dp, vertical = 3.dp)
                                    .testTag("notification_status_${item.id}")
                            )
                        }
                    }
                }

                // Notification Title
                Text(
                    text = item.title,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFFF8FAFC),
                        fontSize = 15.sp
                    ),
                    modifier = Modifier.testTag("notification_title_${item.id}")
                )

                // Notification Description
                Text(
                    text = item.description,
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = Color(0xFFC7D2FE),
                        fontSize = 13.sp,
                        lineHeight = 18.sp
                    ),
                    modifier = Modifier.testTag("notification_desc_${item.id}")
                )
            }
        }
    }
}
