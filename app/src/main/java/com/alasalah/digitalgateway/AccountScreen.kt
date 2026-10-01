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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.automirrored.outlined.ArrowForward
import androidx.compose.material.icons.automirrored.outlined.ReceiptLong
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.CalendarToday
import androidx.compose.material.icons.outlined.CardMembership
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.CreditCard
import androidx.compose.material.icons.outlined.Event
import androidx.compose.material.icons.outlined.Schedule
import androidx.compose.material.icons.outlined.Verified
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountScreen(
    onBackClick: () -> Unit = {},
    onNavigateToSubscription: () -> Unit = {},
    onNavigateToSubscriptionDetails: () -> Unit = onNavigateToSubscription,
    onNavigateToTransactions: () -> Unit = {}
) {
    BackHandler {
        onBackClick()
    }

    // App identity petroleum blue vertical gradient
    val broadcastBackground = Brush.verticalGradient(
        colors = listOf(
            Color(0xFF1B3D4F), // Medium petroleum blue
            Color(0xFF174758), // Subtle muted teal
            Color(0xFF1A3B4D)  // Digital petroleum blue
        )
    )

    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(broadcastBackground)
        ) {
            Scaffold(
                modifier = Modifier
                    .fillMaxSize()
                    .testTag("account_screen_scaffold"),
                containerColor = Color.Transparent,
                topBar = {
                    TopAppBar(
                        title = {
                            Text(
                                text = "حسابي",
                                style = MaterialTheme.typography.titleLarge.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFFF8FAFC),
                                    fontSize = 20.sp
                                ),
                                modifier = Modifier.testTag("account_screen_title")
                            )
                        },
                        navigationIcon = {
                            IconButton(
                                onClick = onBackClick,
                                modifier = Modifier.testTag("account_back_button")
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
                        .testTag("account_content_column"),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Spacer(modifier = Modifier.height(4.dp))

                    // 1. Account Summary Card (Local Mock Data)
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("account_summary_card"),
                        shape = RoundedCornerShape(22.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color(0x381E3A8A)
                        ),
                        border = BorderStroke(1.2.dp, Color(0x4D38BDF8))
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(20.dp),
                            verticalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            // User Info Row
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(14.dp)
                            ) {
                                // Avatar circle
                                Surface(
                                    color = Color(0x2E38BDF8),
                                    shape = CircleShape,
                                    modifier = Modifier.size(54.dp),
                                    border = BorderStroke(1.5.dp, Color(0x6638BDF8))
                                ) {
                                    Box(
                                        contentAlignment = Alignment.Center,
                                        modifier = Modifier.fillMaxSize()
                                    ) {
                                        Icon(
                                            imageVector = Icons.Outlined.AccountCircle,
                                            contentDescription = null,
                                            tint = Color(0xFF38BDF8),
                                            modifier = Modifier.size(34.dp)
                                        )
                                    }
                                }

                                Column(
                                    verticalArrangement = Arrangement.spacedBy(4.dp),
                                    modifier = Modifier.weight(1f)
                                ) {
                                    Text(
                                        text = "مشترك شبكة الأصالة",
                                        style = MaterialTheme.typography.titleMedium.copy(
                                            fontWeight = FontWeight.Bold,
                                            color = Color(0xFFF8FAFC),
                                            fontSize = 17.sp
                                        ),
                                        modifier = Modifier.testTag("account_user_name")
                                    )
                                    Text(
                                        text = "المعرف الرقمي للمشترك",
                                        style = MaterialTheme.typography.bodySmall.copy(
                                            color = Color(0xFF94A3B8),
                                            fontSize = 12.sp
                                        )
                                    )
                                }
                            }

                            // Divider
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(1.dp)
                                    .background(Color(0x24FFFFFF))
                            )

                            // Smart Card & Status Row
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                // Smart Card Detail
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                                ) {
                                    Surface(
                                        color = Color(0x2638BDF8),
                                        shape = RoundedCornerShape(8.dp),
                                        modifier = Modifier.size(32.dp)
                                    ) {
                                        Box(
                                            contentAlignment = Alignment.Center,
                                            modifier = Modifier.fillMaxSize()
                                        ) {
                                            Icon(
                                                imageVector = Icons.Outlined.CreditCard,
                                                contentDescription = null,
                                                tint = Color(0xFF7DD3FC),
                                                modifier = Modifier.size(16.dp)
                                            )
                                        }
                                    }
                                    Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
                                        Text(
                                            text = "البطاقة الذكية",
                                            style = MaterialTheme.typography.labelSmall.copy(
                                                color = Color(0xFF94A3B8),
                                                fontSize = 11.sp
                                            )
                                        )
                                        Text(
                                            text = "**** 4587",
                                            style = MaterialTheme.typography.bodyMedium.copy(
                                                fontWeight = FontWeight.Bold,
                                                color = Color(0xFFF1F5F9),
                                                letterSpacing = 1.sp
                                            ),
                                            modifier = Modifier.testTag("account_smart_card")
                                        )
                                    }
                                }

                                // Status Badge: "نشط"
                                Surface(
                                    color = Color(0x2E34D399),
                                    shape = RoundedCornerShape(8.dp),
                                    border = BorderStroke(1.dp, Color(0x4D34D399))
                                ) {
                                    Row(
                                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                                    ) {
                                        Icon(
                                            imageVector = Icons.Outlined.CheckCircle,
                                            contentDescription = null,
                                            tint = Color(0xFF34D399),
                                            modifier = Modifier.size(14.dp)
                                        )
                                        Text(
                                            text = "نشط",
                                            style = MaterialTheme.typography.labelMedium.copy(
                                                color = Color(0xFF86EFAC),
                                                fontWeight = FontWeight.Bold,
                                                fontSize = 12.sp
                                            ),
                                            modifier = Modifier.testTag("account_status_badge")
                                        )
                                    }
                                }
                            }
                        }
                    }

                    // Section Title: "ملخص الاشتراك"
                    Text(
                        text = "ملخص الاشتراك",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFFF8FAFC),
                            fontSize = 16.sp
                        ),
                        modifier = Modifier.testTag("account_subscription_summary_title")
                    )

                    // Modern glass-style information card for Subscription Summary
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("account_subscription_summary_card"),
                        shape = RoundedCornerShape(20.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color(0x381E3A8A)
                        ),
                        border = BorderStroke(1.2.dp, Color(0x4D38BDF8))
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(18.dp),
                            verticalArrangement = Arrangement.spacedBy(14.dp)
                        ) {
                            // Header Row: Package Icon & Name + Status Badge
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                                ) {
                                    Surface(
                                        shape = RoundedCornerShape(10.dp),
                                        color = Color(0x33818CF8),
                                        border = BorderStroke(1.dp, Color(0x4D818CF8)),
                                        modifier = Modifier.size(38.dp)
                                    ) {
                                        Box(
                                            contentAlignment = Alignment.Center,
                                            modifier = Modifier.fillMaxSize()
                                        ) {
                                            Icon(
                                                imageVector = Icons.Outlined.CardMembership,
                                                contentDescription = null,
                                                tint = Color(0xFFA5B4FC),
                                                modifier = Modifier.size(20.dp)
                                            )
                                        }
                                    }
                                    Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
                                        Text(
                                            text = "الباقة الحالية",
                                            style = MaterialTheme.typography.labelSmall.copy(
                                                color = Color(0xFF94A3B8),
                                                fontSize = 11.sp
                                            )
                                        )
                                        Text(
                                            text = "الباقة الأساسية",
                                            style = MaterialTheme.typography.titleMedium.copy(
                                                fontWeight = FontWeight.Bold,
                                                color = Color(0xFFF1F5F9),
                                                fontSize = 15.sp
                                            ),
                                            modifier = Modifier.testTag("summary_package_name")
                                        )
                                    }
                                }

                                // Status Badge: "نشط" with verified icon & green accent
                                Surface(
                                    shape = RoundedCornerShape(8.dp),
                                    color = Color(0x2E34D399),
                                    border = BorderStroke(1.dp, Color(0x4D34D399))
                                ) {
                                    Row(
                                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp),
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.spacedBy(5.dp)
                                    ) {
                                        Icon(
                                            imageVector = Icons.Outlined.Verified,
                                            contentDescription = null,
                                            tint = Color(0xFF34D399),
                                            modifier = Modifier.size(14.dp)
                                        )
                                        Text(
                                            text = "نشط",
                                            style = MaterialTheme.typography.labelMedium.copy(
                                                color = Color(0xFF86EFAC),
                                                fontWeight = FontWeight.Bold,
                                                fontSize = 12.sp
                                            ),
                                            modifier = Modifier.testTag("summary_status_badge")
                                        )
                                    }
                                }
                            }

                            // Divider
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(1.dp)
                                    .background(Color(0x24FFFFFF))
                            )

                            // Start Date & Expiry Date Row
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(12.dp)
                            ) {
                                SummaryDetailItem(
                                    icon = Icons.Outlined.CalendarToday,
                                    iconTint = Color(0xFF67E8F9),
                                    label = "تاريخ البدء",
                                    value = "15 أغسطس 2026",
                                    modifier = Modifier.weight(1f),
                                    tag = "summary_start_date"
                                )
                                SummaryDetailItem(
                                    icon = Icons.Outlined.Event,
                                    iconTint = Color(0xFFF87171),
                                    label = "تاريخ الانتهاء",
                                    value = "15 أكتوبر 2026",
                                    modifier = Modifier.weight(1f),
                                    tag = "summary_expiry_date"
                                )
                            }

                            // Remaining Duration Row
                            SummaryDetailItem(
                                icon = Icons.Outlined.Schedule,
                                iconTint = Color(0xFFFBBF24),
                                label = "المدة المتبقية",
                                value = "16 يوم",
                                modifier = Modifier.fillMaxWidth(),
                                tag = "summary_remaining_days"
                            )

                            Spacer(modifier = Modifier.height(4.dp))

                            // Action Button: "عرض تفاصيل الاشتراك"
                            Button(
                                onClick = onNavigateToSubscriptionDetails,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(48.dp)
                                    .testTag("btn_view_subscription_details"),
                                shape = RoundedCornerShape(12.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(0x3338BDF8),
                                    contentColor = Color(0xFFE0F2FE)
                                ),
                                border = BorderStroke(1.dp, Color(0x6638BDF8))
                            ) {
                                Text(
                                    text = "عرض تفاصيل الاشتراك",
                                    style = MaterialTheme.typography.labelLarge.copy(
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 14.sp
                                    )
                                )
                            }
                        }
                    }

                    // Section Title: "إدارة الحساب"
                    Text(
                        text = "إدارة الحساب",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFFF8FAFC),
                            fontSize = 16.sp
                        ),
                        modifier = Modifier.testTag("account_actions_section_title")
                    )

                    // 2. Action Card 1: "اشتراكي"
                    AccountActionCard(
                        icon = Icons.Outlined.CardMembership,
                        iconTint = Color(0xFFA5B4FC),
                        iconBg = Color(0x336366F1),
                        title = "اشتراكي",
                        subtitle = "عرض تفاصيل باقة البث وصلاحية الاشتراك",
                        onClick = onNavigateToSubscription,
                        tag = "account_action_subscription"
                    )

                    // 3. Action Card 2: "سجل العمليات"
                    AccountActionCard(
                        icon = Icons.AutoMirrored.Outlined.ReceiptLong,
                        iconTint = Color(0xFF38BDF8),
                        iconBg = Color(0x330284C7),
                        title = "سجل العمليات",
                        subtitle = "متابعة طلبات التجديد وحالة الدفع السابقة",
                        onClick = onNavigateToTransactions,
                        tag = "account_action_transactions"
                    )

                    Spacer(modifier = Modifier.height(20.dp))
                }
            }
        }
    }
}

@Composable
private fun AccountActionCard(
    icon: ImageVector,
    iconTint: Color,
    iconBg: Color,
    title: String,
    subtitle: String,
    onClick: () -> Unit,
    tag: String
) {
    Card(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .testTag(tag),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0x2EFFFFFF)
        ),
        border = BorderStroke(1.dp, Color(0x29FFFFFF))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 14.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            Surface(
                color = iconBg,
                shape = CircleShape,
                modifier = Modifier.size(42.dp)
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        tint = iconTint,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(2.dp)
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFFF8FAFC),
                        fontSize = 15.sp
                    )
                )
                Text(
                    text = subtitle,
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = Color(0xFFC7D2FE),
                        fontSize = 12.sp
                    )
                )
            }

            Icon(
                imageVector = Icons.AutoMirrored.Outlined.ArrowForward,
                contentDescription = null,
                tint = Color(0xFF94A3B8),
                modifier = Modifier.size(18.dp)
            )
        }
    }
}

@Composable
private fun SummaryDetailItem(
    icon: ImageVector,
    iconTint: Color,
    label: String,
    value: String,
    modifier: Modifier = Modifier,
    tag: String
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Surface(
            shape = RoundedCornerShape(8.dp),
            color = iconTint.copy(alpha = 0.16f),
            modifier = Modifier.size(32.dp)
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = iconTint,
                    modifier = Modifier.size(16.dp)
                )
            }
        }
        Column(verticalArrangement = Arrangement.spacedBy(1.dp)) {
            Text(
                text = label,
                style = MaterialTheme.typography.labelSmall.copy(
                    color = Color(0xFF94A3B8),
                    fontSize = 11.sp
                )
            )
            Text(
                text = value,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFF1F5F9),
                    fontSize = 13.sp
                ),
                modifier = Modifier.testTag(tag)
            )
        }
    }
}
