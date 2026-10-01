package com.alasalah.digitalgateway

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Autorenew
import androidx.compose.material.icons.outlined.CalendarToday
import androidx.compose.material.icons.outlined.Campaign
import androidx.compose.material.icons.outlined.CardMembership
import androidx.compose.material.icons.outlined.Chat
import androidx.compose.material.icons.outlined.ChevronLeft
import androidx.compose.material.icons.outlined.CreditCard
import androidx.compose.material.icons.outlined.HeadsetMic
import androidx.compose.material.icons.outlined.HelpOutline
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Newspaper
import androidx.compose.material.icons.outlined.NotificationAdd
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Payments
import androidx.compose.material.icons.outlined.Public
import androidx.compose.material.icons.outlined.Sensors
import androidx.compose.material.icons.outlined.Tv
import androidx.compose.material.icons.outlined.WarningAmber
import androidx.compose.material.icons.outlined.Widgets
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
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

@Composable
fun HomeScreen(
    onNavigateToSubscription: () -> Unit = {},
    onNavigateToRenewal: () -> Unit = {},
    onNavigateToTransactions: () -> Unit = {},
    onNavigateToAccount: () -> Unit = {},
    onNavigateToNotifications: () -> Unit = {},
    onNavigateToChannels: () -> Unit = {},
    onNavigateToReceptionGuide: () -> Unit = {},
    onNavigateToFrequencies: () -> Unit = {},
    onNavigateToCoverage: () -> Unit = {},
    onNavigateToReceiverCardInfo: () -> Unit = {},
    onNavigateToSupport: () -> Unit = {},
    onNavigateToFaq: () -> Unit = {}
) {
    // Modern mid-tone petroleum blue with subtle transition to muted teal / digital blue
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
                    .testTag("home_screen_scaffold"),
                containerColor = Color.Transparent,
                bottomBar = {
                    HomeBottomNavigationBar(
                        onAccountClick = onNavigateToAccount,
                        onChannelsClick = onNavigateToChannels,
                        onNavigateToSupport = onNavigateToSupport
                    )
                }
            ) { innerPadding ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                        .verticalScroll(rememberScrollState())
                        .testTag("home_content_column")
                ) {
                    // Top Header Area
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp, vertical = 16.dp)
                            .testTag("top_header_bar"),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Right in RTL: Network Title & Subtitle
                        Column(
                            verticalArrangement = Arrangement.spacedBy(4.dp),
                            modifier = Modifier.testTag("header_title_column")
                        ) {
                            Text(
                                text = "شبكة الأصالة",
                                style = MaterialTheme.typography.titleLarge.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFFF7FAFC)
                                ),
                                modifier = Modifier.testTag("app_title_text")
                            )
                            Text(
                                text = "البث الرقمي",
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    fontWeight = FontWeight.Normal,
                                    color = Color(0xFF9ECBD8)
                                ),
                                modifier = Modifier.testTag("app_subtitle_text")
                            )
                        }

                        // Left in RTL: Notification and Profile icons
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(10.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.testTag("header_actions_row")
                        ) {
                            IconButton(
                                onClick = onNavigateToNotifications,
                                modifier = Modifier
                                    .size(44.dp)
                                    .background(
                                        color = Color(0x29FFFFFF),
                                        shape = CircleShape
                                    )
                                    .testTag("notification_button")
                            ) {
                                Icon(
                                    imageVector = Icons.Outlined.Notifications,
                                    contentDescription = "الإشعارات",
                                    tint = Color(0xFFF0F5F8)
                                )
                            }

                            IconButton(
                                onClick = onNavigateToAccount,
                                modifier = Modifier
                                    .size(44.dp)
                                    .background(
                                        color = Color(0x29FFFFFF),
                                        shape = CircleShape
                                    )
                                    .testTag("profile_button")
                            ) {
                                Icon(
                                    imageVector = Icons.Outlined.AccountCircle,
                                    contentDescription = "الحساب",
                                    tint = Color(0xFFF0F5F8)
                                )
                            }
                        }
                    }

                    // ONE Subscription Status Card directly below header
                    SubscriptionStatusCard(
                        onRenewClick = onNavigateToRenewal,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp, vertical = 8.dp)
                    )

                    // ONE compact important alert card directly below subscription card
                    ImportantAlertCard(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp, vertical = 4.dp)
                    )

                    // ONE Quick Services section directly below alert card
                    QuickServicesSection(
                        onSubscriptionClick = onNavigateToSubscription,
                        onRenewalClick = onNavigateToRenewal,
                        onTransactionsClick = onNavigateToTransactions,
                        onFrequenciesClick = onNavigateToFrequencies,
                        onCoverageClick = onNavigateToCoverage,
                        onSupportClick = onNavigateToSupport,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp, vertical = 12.dp)
                    )

                    // ONE What's New section directly below Quick Services section
                    WhatsNewSection(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp, vertical = 8.dp)
                    )

                    // ONE Latest News section directly below What's New section
                    LatestNewsSection(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp, vertical = 8.dp)
                    )

                    // ONE Upcoming Events section directly below Latest News section
                    UpcomingEventsSection(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp, vertical = 8.dp)
                    )

                    // ONE Network Channels section directly below Upcoming Events section
                    NetworkChannelsSection(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                    )

                    // ONE Broadcast Reception Guide section directly below Network Channels section
                    ReceptionGuideSection(
                        onGuideClick = onNavigateToReceptionGuide,
                        onFrequenciesClick = onNavigateToFrequencies,
                        onCoverageClick = onNavigateToCoverage,
                        onReceiverInfoClick = onNavigateToReceiverCardInfo,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp, vertical = 8.dp)
                    )

                    // ONE Support and Contact section directly below Broadcast Reception Guide section
                    SupportAndContactSection(
                        onFaqClick = onNavigateToFaq,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp, vertical = 8.dp)
                    )

                    Spacer(modifier = Modifier.height(24.dp))
                }
            }
        }
    }
}

@Composable
fun SubscriptionStatusCard(
    onRenewClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    // Coordinated deep blue-indigo / muted violet-blue gradient
    val cardGradient = Brush.linearGradient(
        colors = listOf(
            Color(0xFF2D3B68), // Deep blue-indigo
            Color(0xFF38315E)  // Muted violet-blue
        )
    )

    Card(
        modifier = modifier.testTag("subscription_status_card"),
        shape = RoundedCornerShape(22.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        ),
        border = BorderStroke(1.dp, Color(0x38A5B4FC))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(cardGradient)
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Header row inside card: Status Indicator + Title & Subtitle, and Smart Card on opposite side
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        // Status indicator dot for active subscription
                        Box(
                            modifier = Modifier
                                .size(9.dp)
                                .background(Color(0xFF34D399), CircleShape)
                                .testTag("active_status_indicator")
                        )
                        Text(
                            text = "اشتراكك فعال",
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFFF8FAFC)
                            ),
                            modifier = Modifier.testTag("subscription_title_text")
                        )
                    }
                    Text(
                        text = "الباقة الأساسية",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = Color(0xFFC7D2FE),
                            fontWeight = FontWeight.Medium
                        ),
                        modifier = Modifier.testTag("subscription_subtitle_text")
                    )
                }

                // Smart Card pill badge
                Surface(
                    color = Color(0x2EFFFFFF),
                    shape = RoundedCornerShape(10.dp),
                    border = BorderStroke(1.dp, Color(0x33A5B4FC))
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.CreditCard,
                            contentDescription = null,
                            tint = Color(0xFFA5B4FC),
                            modifier = Modifier.size(16.dp)
                        )
                        Text(
                            text = "**** 4587",
                            style = MaterialTheme.typography.labelMedium.copy(
                                color = Color(0xFFF1F5F9),
                                fontWeight = FontWeight.SemiBold,
                                letterSpacing = 1.sp
                            ),
                            modifier = Modifier.testTag("subscription_smart_card_text")
                        )
                    }
                }
            }

            HorizontalDivider(
                color = Color(0x26FFFFFF),
                thickness = 1.dp
            )

            // Expiry info row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
                    Text(
                        text = "ينتهي في",
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = Color(0xFFA5B4FC)
                        ),
                        modifier = Modifier.testTag("subscription_expiry_label")
                    )
                    Text(
                        text = "15 أكتوبر 2026",
                        style = MaterialTheme.typography.bodyLarge.copy(
                            color = Color(0xFFF8FAFC),
                            fontWeight = FontWeight.SemiBold
                        ),
                        modifier = Modifier.testTag("subscription_expiry_date")
                    )
                }

                // Remaining days pill badge
                Surface(
                    color = Color(0x2E34D399),
                    shape = RoundedCornerShape(8.dp),
                    border = BorderStroke(1.dp, Color(0x4D34D399))
                ) {
                    Text(
                        text = "متبقي 16 يوم",
                        style = MaterialTheme.typography.labelMedium.copy(
                            color = Color(0xFF86EFAC),
                            fontWeight = FontWeight.Bold
                        ),
                        modifier = Modifier
                            .padding(horizontal = 10.dp, vertical = 5.dp)
                            .testTag("subscription_remaining_badge")
                    )
                }
            }

            // Primary action button: تجديد الآن with brighter cyan/blue accent
            Button(
                onClick = onRenewClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .testTag("renew_now_button"),
                shape = RoundedCornerShape(14.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF0EA5E9), // Bright cyan/blue accent
                    contentColor = Color.White
                )
            ) {
                Text(
                    text = "تجديد الآن",
                    style = MaterialTheme.typography.labelLarge.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp
                    )
                )
            }
        }
    }
}

@Composable
fun ImportantAlertCard(
    modifier: Modifier = Modifier
) {
    // Warm amber / burnt orange mid-tone gradient
    val alertGradient = Brush.linearGradient(
        colors = listOf(
            Color(0xFF7A3E1D), // Warm burnt orange mid-tone
            Color(0xFF8C4722)  // Subtle amber-orange mid-tone
        )
    )

    Card(
        modifier = modifier.testTag("important_alert_card"),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        ),
        border = BorderStroke(1.dp, Color(0x59FDBA74))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(alertGradient)
                .padding(horizontal = 16.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Small alert icon in subtle rounded container
            Surface(
                color = Color(0x33000000),
                shape = CircleShape,
                modifier = Modifier.size(36.dp)
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Icon(
                        imageVector = Icons.Outlined.WarningAmber,
                        contentDescription = "تنبيه",
                        tint = Color(0xFFFDE047),
                        modifier = Modifier
                            .size(20.dp)
                            .testTag("alert_icon")
                    )
                }
            }

            // RTL details column: Title, Message, Secondary text
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(2.dp)
            ) {
                Text(
                    text = "تنبيه مهم",
                    style = MaterialTheme.typography.labelLarge.copy(
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFFFEF3C7)
                    ),
                    modifier = Modifier.testTag("alert_title_text")
                )
                Text(
                    text = "أعمال صيانة مجدولة على الشبكة",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.Medium,
                        color = Color(0xFFFFFFFF)
                    ),
                    modifier = Modifier.testTag("alert_message_text")
                )
                Text(
                    text = "قد تتأثر بعض المناطق مؤقتاً",
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = Color(0xFFFED7AA),
                        fontSize = 12.sp
                    ),
                    modifier = Modifier.testTag("alert_secondary_text")
                )
            }
        }
    }
}

private data class QuickServiceItem(
    val title: String,
    val icon: ImageVector,
    val accentColor: Color,
    val iconBgColor: Color
)

@Composable
fun QuickServicesSection(
    onSubscriptionClick: () -> Unit = {},
    onRenewalClick: () -> Unit = {},
    onTransactionsClick: () -> Unit = {},
    onFrequenciesClick: () -> Unit = {},
    onCoverageClick: () -> Unit = {},
    onSupportClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    val services = listOf(
        QuickServiceItem(
            title = "اشتراكي",
            icon = Icons.Outlined.CardMembership,
            accentColor = Color(0xFFA5B4FC), // Soft indigo
            iconBgColor = Color(0x336366F1)
        ),
        QuickServiceItem(
            title = "التجديد",
            icon = Icons.Outlined.Autorenew,
            accentColor = Color(0xFF6EE7B7), // Soft emerald
            iconBgColor = Color(0x3310B981)
        ),
        QuickServiceItem(
            title = "الدفع",
            icon = Icons.Outlined.Payments,
            accentColor = Color(0xFF7DD3FC), // Soft sky blue
            iconBgColor = Color(0x330284C7)
        ),
        QuickServiceItem(
            title = "الترددات",
            icon = Icons.Outlined.Sensors,
            accentColor = Color(0xFFFDE047), // Soft amber/yellow
            iconBgColor = Color(0x33D97706)
        ),
        QuickServiceItem(
            title = "التغطية",
            icon = Icons.Outlined.Public,
            accentColor = Color(0xFFFDA4AF), // Soft rose/coral
            iconBgColor = Color(0x33E11D48)
        ),
        QuickServiceItem(
            title = "الدعم",
            icon = Icons.Outlined.HeadsetMic,
            accentColor = Color(0xFF93C5FD), // Soft blue
            iconBgColor = Color(0x332563EB)
        )
    )

    Column(
        modifier = modifier.testTag("quick_services_section"),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // Section Title: "الخدمات السريعة"
        Text(
            text = "الخدمات السريعة",
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Bold,
                color = Color(0xFFF8FAFC)
            ),
            modifier = Modifier.testTag("quick_services_title")
        )

        // Row 1: items 0, 1, 2
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            // "اشتراكي" item 0: connected to subscription screen
            QuickServiceCard(
                item = services[0],
                onClick = onSubscriptionClick,
                modifier = Modifier.weight(1f)
            )
            // "التجديد" item 1: connected to renewal screen
            QuickServiceCard(
                item = services[1],
                onClick = onRenewalClick,
                modifier = Modifier.weight(1f)
            )
            // "الدفع" item 2: connected to transactions screen
            QuickServiceCard(
                item = services[2],
                onClick = onTransactionsClick,
                modifier = Modifier.weight(1f)
            )
        }

        // Row 2: items 3 (الترددات), 4 (التغطية), 5 (الدعم)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            // "الترددات" item 3
            QuickServiceCard(
                item = services[3],
                onClick = onFrequenciesClick,
                modifier = Modifier.weight(1f)
            )
            // "التغطية" item 4
            QuickServiceCard(
                item = services[4],
                onClick = onCoverageClick,
                modifier = Modifier.weight(1f)
            )
            // "الدعم" item 5
            QuickServiceCard(
                item = services[5],
                onClick = onSupportClick,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
private fun QuickServiceCard(
    item: QuickServiceItem,
    onClick: (() -> Unit)? = null,
    modifier: Modifier = Modifier
) {
    if (onClick != null) {
        Card(
            onClick = onClick,
            modifier = modifier.testTag("service_card_${item.title}"),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                // Visually lighter than subscription card, translucent petroleum tone
                containerColor = Color(0x2EFFFFFF)
            ),
            border = BorderStroke(1.dp, Color(0x29FFFFFF))
        ) {
            QuickServiceCardContent(item = item)
        }
    } else {
        Card(
            modifier = modifier.testTag("service_card_${item.title}"),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                // Visually lighter than subscription card, translucent petroleum tone
                containerColor = Color(0x2EFFFFFF)
            ),
            border = BorderStroke(1.dp, Color(0x29FFFFFF))
        ) {
            QuickServiceCardContent(item = item)
        }
    }
}

@Composable
private fun QuickServiceCardContent(
    item: QuickServiceItem
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 14.dp, horizontal = 6.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Surface(
            color = item.iconBgColor,
            shape = CircleShape,
            modifier = Modifier.size(42.dp)
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                Icon(
                    imageVector = item.icon,
                    contentDescription = item.title,
                    tint = item.accentColor,
                    modifier = Modifier.size(22.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = item.title,
            style = MaterialTheme.typography.bodyMedium.copy(
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFFF1F5F9),
                fontSize = 13.sp
            ),
            maxLines = 1,
            modifier = Modifier.testTag("service_label_${item.title}")
        )
    }
}

@Composable
fun WhatsNewSection(
    modifier: Modifier = Modifier
) {
    // Fresh teal / turquoise medium-tone gradient
    val tealCardGradient = Brush.linearGradient(
        colors = listOf(
            Color(0xFF0F5B63), // Medium-tone deep teal
            Color(0xFF146A72)  // Fresh turquoise tone
        )
    )

    Column(
        modifier = modifier.testTag("whats_new_section"),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // Section Title: "ما الجديد في شبكة الأصالة"
        Text(
            text = "ما الجديد في شبكة الأصالة",
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Bold,
                color = Color(0xFFF8FAFC)
            ),
            modifier = Modifier.testTag("whats_new_title")
        )

        // ONE Feature Card
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .testTag("whats_new_card"),
            shape = RoundedCornerShape(18.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.Transparent
            ),
            border = BorderStroke(1.dp, Color(0x4D2DD4BF))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(tealCardGradient)
                    .padding(18.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // Top row: Badge ("جديد") + Announcement icon
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Surface(
                        color = Color(0x3D2DD4BF),
                        shape = RoundedCornerShape(6.dp),
                        border = BorderStroke(1.dp, Color(0x662DD4BF))
                    ) {
                        Text(
                            text = "جديد",
                            style = MaterialTheme.typography.labelSmall.copy(
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF5EEAD4)
                            ),
                            modifier = Modifier
                                .padding(horizontal = 10.dp, vertical = 4.dp)
                                .testTag("whats_new_badge")
                        )
                    }

                    Surface(
                        color = Color(0x262DD4BF),
                        shape = CircleShape,
                        modifier = Modifier.size(34.dp)
                    ) {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier.fillMaxSize()
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.Campaign,
                                contentDescription = null,
                                tint = Color(0xFF5EEAD4),
                                modifier = Modifier.size(18.dp)
                            )
                        }
                    }
                }

                // Title: "تحديث جديد في خدمات الشبكة"
                Text(
                    text = "تحديث جديد في خدمات الشبكة",
                    style = MaterialTheme.typography.titleSmall.copy(
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFFF0FDFA),
                        fontSize = 15.sp
                    ),
                    modifier = Modifier.testTag("whats_new_card_title")
                )

                // Description: "تابع آخر الإضافات والتحديثات التي تقدمها شبكة الأصالة للمشتركين."
                Text(
                    text = "تابع آخر الإضافات والتحديثات التي تقدمها شبكة الأصالة للمشتركين.",
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = Color(0xFFCCFBF1),
                        fontSize = 13.sp,
                        lineHeight = 18.sp
                    ),
                    modifier = Modifier.testTag("whats_new_description")
                )

                // Action text: "عرض التفاصيل"
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    modifier = Modifier.testTag("whats_new_action_row")
                ) {
                    Text(
                        text = "عرض التفاصيل",
                        style = MaterialTheme.typography.labelMedium.copy(
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF2DD4BF),
                            fontSize = 13.sp
                        ),
                        modifier = Modifier.testTag("whats_new_action_text")
                    )
                    Icon(
                        imageVector = Icons.Outlined.ChevronLeft,
                        contentDescription = null,
                        tint = Color(0xFF2DD4BF),
                        modifier = Modifier.size(16.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun LatestNewsSection(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.testTag("latest_news_section"),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // Section Header Row: Title on right, "عرض الكل" on left
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "آخر الأخبار",
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFF8FAFC)
                ),
                modifier = Modifier.testTag("latest_news_title")
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(2.dp),
                modifier = Modifier.testTag("latest_news_see_all")
            ) {
                Text(
                    text = "عرض الكل",
                    style = MaterialTheme.typography.labelMedium.copy(
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFF38BDF8),
                        fontSize = 13.sp
                    )
                )
                Icon(
                    imageVector = Icons.Outlined.ChevronLeft,
                    contentDescription = null,
                    tint = Color(0xFF38BDF8),
                    modifier = Modifier.size(16.dp)
                )
            }
        }

        // News Card 1
        NewsCardItem(
            category = "أخبار الشبكة",
            title = "توسعة جديدة في خدمات شبكة الأصالة",
            summary = "تواصل الشبكة تطوير خدماتها وتحسين تجربة المشتركين.",
            gradientColors = listOf(Color(0xFF1E3A5F), Color(0xFF1A4971)),
            borderColor = Color(0x3838BDF8),
            badgeColor = Color(0x3338BDF8),
            badgeTextColor = Color(0xFF7DD3FC),
            testTagPrefix = "news_1"
        )

        // News Card 2
        NewsCardItem(
            category = "تحديث",
            title = "تحسينات جديدة في منظومة البث الرقمي",
            summary = "تحديثات مستمرة لدعم استقرار وجودة خدمات الشبكة.",
            gradientColors = listOf(Color(0xFF184363), Color(0xFF155375)),
            borderColor = Color(0x3822D3EE),
            badgeColor = Color(0x3322D3EE),
            badgeTextColor = Color(0xFF67E8F9),
            testTagPrefix = "news_2"
        )
    }
}

@Composable
private fun NewsCardItem(
    category: String,
    title: String,
    summary: String,
    gradientColors: List<Color>,
    borderColor: Color,
    badgeColor: Color,
    badgeTextColor: Color,
    testTagPrefix: String
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .testTag(testTagPrefix),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        ),
        border = BorderStroke(1.dp, borderColor)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Brush.linearGradient(gradientColors))
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            // Header Row of the card: category badge + newspaper icon
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    color = badgeColor,
                    shape = RoundedCornerShape(6.dp),
                    border = BorderStroke(1.dp, borderColor)
                ) {
                    Text(
                        text = category,
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontWeight = FontWeight.Bold,
                            color = badgeTextColor
                        ),
                        modifier = Modifier
                            .padding(horizontal = 8.dp, vertical = 3.dp)
                            .testTag("${testTagPrefix}_category")
                    )
                }

                Surface(
                    color = Color(0x26FFFFFF),
                    shape = CircleShape,
                    modifier = Modifier.size(32.dp)
                ) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Newspaper,
                            contentDescription = null,
                            tint = badgeTextColor,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }
            }

            // Title
            Text(
                text = title,
                style = MaterialTheme.typography.titleSmall.copy(
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFF0F9FF),
                    fontSize = 14.sp
                ),
                modifier = Modifier.testTag("${testTagPrefix}_title")
            )

            // Summary
            Text(
                text = summary,
                style = MaterialTheme.typography.bodySmall.copy(
                    color = Color(0xFFBAE6FD),
                    fontSize = 12.sp,
                    lineHeight = 17.sp
                ),
                modifier = Modifier.testTag("${testTagPrefix}_summary")
            )
        }
    }
}

@Composable
fun UpcomingEventsSection(
    modifier: Modifier = Modifier
) {
    // Warm coral / soft orange-red medium-tone gradient
    val coralCardGradient = Brush.linearGradient(
        colors = listOf(
            Color(0xFF752B28), // Deep warm coral / soft terracotta-red
            Color(0xFF8B3632)  // Soft orange-red tone
        )
    )

    Column(
        modifier = modifier.testTag("upcoming_events_section"),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // Section Title: "الفعاليات القادمة"
        Text(
            text = "الفعاليات القادمة",
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Bold,
                color = Color(0xFFF8FAFC)
            ),
            modifier = Modifier.testTag("upcoming_events_title")
        )

        // ONE Mock Event Card
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .testTag("upcoming_event_card"),
            shape = RoundedCornerShape(18.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.Transparent
            ),
            border = BorderStroke(1.dp, Color(0x4DFB7185))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(coralCardGradient)
                    .padding(18.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // Top row: Badge ("فعالية") on right (RTL start) + Action pill ("ذكّرني") on left (RTL end)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Badge: "فعالية"
                    Surface(
                        color = Color(0x3DFB7185),
                        shape = RoundedCornerShape(6.dp),
                        border = BorderStroke(1.dp, Color(0x66FB7185))
                    ) {
                        Text(
                            text = "فعالية",
                            style = MaterialTheme.typography.labelSmall.copy(
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFFFECDD3)
                            ),
                            modifier = Modifier
                                .padding(horizontal = 10.dp, vertical = 4.dp)
                                .testTag("event_badge")
                        )
                    }

                    // Action pill: "ذكّرني" with reminder icon
                    Surface(
                        color = Color(0x33000000),
                        shape = RoundedCornerShape(20.dp),
                        border = BorderStroke(1.dp, Color(0x4DFB7185))
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(6.dp),
                            modifier = Modifier
                                .padding(horizontal = 12.dp, vertical = 6.dp)
                                .testTag("event_action_pill")
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.NotificationAdd,
                                contentDescription = null,
                                tint = Color(0xFFFDA4AF),
                                modifier = Modifier.size(16.dp)
                            )
                            Text(
                                text = "ذكّرني",
                                style = MaterialTheme.typography.labelMedium.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFFFFF1F2),
                                    fontSize = 13.sp
                                ),
                                modifier = Modifier.testTag("event_action_text")
                            )
                        }
                    }
                }

                // Title: "فعالية شبكة الأصالة"
                Text(
                    text = "فعالية شبكة الأصالة",
                    style = MaterialTheme.typography.titleSmall.copy(
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFFFFF1F2),
                        fontSize = 15.sp
                    ),
                    modifier = Modifier.testTag("event_title")
                )

                // Date & Location Info Row
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Date: "12 أكتوبر 2026"
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp),
                        modifier = Modifier.testTag("event_date_row")
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.CalendarToday,
                            contentDescription = null,
                            tint = Color(0xFFFDA4AF),
                            modifier = Modifier.size(16.dp)
                        )
                        Text(
                            text = "12 أكتوبر 2026",
                            style = MaterialTheme.typography.bodySmall.copy(
                                color = Color(0xFFFFE4E6),
                                fontWeight = FontWeight.Medium,
                                fontSize = 13.sp
                            ),
                            modifier = Modifier.testTag("event_date_text")
                        )
                    }

                    // Location: "مأرب"
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp),
                        modifier = Modifier.testTag("event_location_row")
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.LocationOn,
                            contentDescription = null,
                            tint = Color(0xFFFDA4AF),
                            modifier = Modifier.size(16.dp)
                        )
                        Text(
                            text = "مأرب",
                            style = MaterialTheme.typography.bodySmall.copy(
                                color = Color(0xFFFFE4E6),
                                fontWeight = FontWeight.Medium,
                                fontSize = 13.sp
                            ),
                            modifier = Modifier.testTag("event_location_text")
                        )
                    }
                }

                // Description: "تابع آخر الفعاليات والأنشطة المرتبطة بشبكة الأصالة."
                Text(
                    text = "تابع آخر الفعاليات والأنشطة المرتبطة بشبكة الأصالة.",
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = Color(0xFFFECDD3),
                        fontSize = 12.sp,
                        lineHeight = 17.sp
                    ),
                    modifier = Modifier.testTag("event_description")
                )
            }
        }
    }
}

private data class ChannelMockItem(
    val name: String,
    val type: String,
    val accentColor: Color,
    val iconBgColor: Color
)

@Composable
fun NetworkChannelsSection(
    modifier: Modifier = Modifier
) {
    val filterLabels = listOf("الكل", "المجانية", "المشفرة", "جديد")

    val channels = listOf(
        ChannelMockItem(
            name = "الأصالة 1",
            type = "عامة",
            accentColor = Color(0xFF38BDF8),
            iconBgColor = Color(0x330284C7)
        ),
        ChannelMockItem(
            name = "الأصالة دراما",
            type = "دراما",
            accentColor = Color(0xFFA78BFA),
            iconBgColor = Color(0x337C3AED)
        ),
        ChannelMockItem(
            name = "الأصالة رياضة",
            type = "رياضة",
            accentColor = Color(0xFF34D399),
            iconBgColor = Color(0x33059669)
        ),
        ChannelMockItem(
            name = "الأصالة وثائقية",
            type = "وثائقي",
            accentColor = Color(0xFFFBBF24),
            iconBgColor = Color(0x33D97706)
        ),
        ChannelMockItem(
            name = "الأصالة إخبارية",
            type = "إخبارية",
            accentColor = Color(0xFFFB7185),
            iconBgColor = Color(0x33E11D48)
        ),
        ChannelMockItem(
            name = "الأصالة أطفال",
            type = "أطفال",
            accentColor = Color(0xFF2DD4BF),
            iconBgColor = Color(0x330D9488)
        )
    )

    Column(
        modifier = modifier.testTag("network_channels_section"),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // Section Title: "قنوات شبكة الأصالة"
        Text(
            text = "قنوات شبكة الأصالة",
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Bold,
                color = Color(0xFFF8FAFC)
            ),
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .testTag("network_channels_title")
        )

        // Filter labels above channel items
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState())
                .padding(horizontal = 20.dp)
                .testTag("channels_filters_row"),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            filterLabels.forEachIndexed { index, filter ->
                val isSelected = index == 0
                Surface(
                    color = if (isSelected) Color(0xFF0284C7) else Color(0x24FFFFFF),
                    shape = RoundedCornerShape(16.dp),
                    border = BorderStroke(
                        1.dp,
                        if (isSelected) Color(0xFF38BDF8) else Color(0x2BFFFFFF)
                    )
                ) {
                    Text(
                        text = filter,
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                            color = if (isSelected) Color(0xFFF0F9FF) else Color(0xFFCBD5E1),
                            fontSize = 12.sp
                        ),
                        modifier = Modifier
                            .padding(horizontal = 14.dp, vertical = 6.dp)
                            .testTag("channel_filter_$filter")
                    )
                }
            }
        }

        // Horizontal scroll for channel items
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState())
                .padding(horizontal = 20.dp)
                .testTag("channels_horizontal_scroll_row"),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            channels.forEach { item ->
                Card(
                    modifier = Modifier
                        .width(106.dp)
                        .testTag("channel_card_${item.name}"),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0x29FFFFFF)
                    ),
                    border = BorderStroke(1.dp, Color(0x26FFFFFF))
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 12.dp, horizontal = 6.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        // Rounded-square placeholder logo area
                        Surface(
                            shape = RoundedCornerShape(14.dp),
                            color = item.iconBgColor,
                            border = BorderStroke(1.dp, item.accentColor.copy(alpha = 0.45f)),
                            modifier = Modifier
                                .size(48.dp)
                                .testTag("channel_logo_placeholder_${item.name}")
                        ) {
                            Box(
                                contentAlignment = Alignment.Center,
                                modifier = Modifier.fillMaxSize()
                            ) {
                                Icon(
                                    imageVector = Icons.Outlined.Tv,
                                    contentDescription = null,
                                    tint = item.accentColor,
                                    modifier = Modifier.size(24.dp)
                                )
                            }
                        }

                        // Short mock channel name
                        Text(
                            text = item.name,
                            style = MaterialTheme.typography.bodySmall.copy(
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFFF1F5F9),
                                fontSize = 12.sp
                            ),
                            maxLines = 1,
                            modifier = Modifier.testTag("channel_name_${item.name}")
                        )

                        // Channel category/type label
                        Text(
                            text = item.type,
                            style = MaterialTheme.typography.labelSmall.copy(
                                color = item.accentColor,
                                fontWeight = FontWeight.Medium,
                                fontSize = 10.sp
                            ),
                            maxLines = 1,
                            modifier = Modifier.testTag("channel_type_${item.name}")
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ReceptionGuideSection(
    onGuideClick: () -> Unit = {},
    onFrequenciesClick: () -> Unit = {},
    onCoverageClick: () -> Unit = {},
    onReceiverInfoClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    // Coordinated green-teal medium-tone gradient
    val guideCardGradient = Brush.linearGradient(
        colors = listOf(
            Color(0xFF134E4A), // Deep emerald-teal
            Color(0xFF0F766E)  // Rich vibrant green-teal
        )
    )

    Column(
        modifier = modifier.testTag("reception_guide_section"),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // Section Title: "دليل استقبال البث"
        Text(
            text = "دليل استقبال البث",
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Bold,
                color = Color(0xFFF8FAFC)
            ),
            modifier = Modifier.testTag("reception_guide_section_title")
        )

        // ONE Guide Card - clicking opens reception guide screen
        Card(
            onClick = onGuideClick,
            modifier = Modifier
                .fillMaxWidth()
                .testTag("reception_guide_card"),
            shape = RoundedCornerShape(18.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.Transparent
            ),
            border = BorderStroke(1.dp, Color(0x4D34D399))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(guideCardGradient)
                    .padding(18.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // Top row: Signal / antenna icon area
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Surface(
                        color = Color(0x3334D399),
                        shape = RoundedCornerShape(12.dp),
                        border = BorderStroke(1.dp, Color(0x4D34D399)),
                        modifier = Modifier.size(42.dp)
                    ) {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier.fillMaxSize()
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.Sensors,
                                contentDescription = null,
                                tint = Color(0xFF34D399),
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    }

                    Surface(
                        color = Color(0x2634D399),
                        shape = RoundedCornerShape(6.dp),
                        border = BorderStroke(1.dp, Color(0x3834D399))
                    ) {
                        Text(
                            text = "دليل شامل",
                            style = MaterialTheme.typography.labelSmall.copy(
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF6EE7B7)
                            ),
                            modifier = Modifier
                                .padding(horizontal = 8.dp, vertical = 3.dp)
                                .testTag("reception_guide_badge")
                        )
                    }
                }

                // Title: "دليل استقبال البث"
                Text(
                    text = "دليل استقبال البث",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFFECFDF5),
                        fontSize = 16.sp
                    ),
                    modifier = Modifier.testTag("reception_guide_title")
                )

                // Description
                Text(
                    text = "المرجع الشامل لاستقبال شبكة الأصالة: إعداد الهوائي، متطلبات الرسيفر والبطاقة، وطريقة البحث وضبط القنوات.",
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = Color(0xFFA7F3D0),
                        fontSize = 13.sp,
                        lineHeight = 18.sp
                    ),
                    modifier = Modifier.testTag("reception_guide_description")
                )

                // Action text: "عرض الدليل" with chevron
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    modifier = Modifier.testTag("reception_guide_action_row")
                ) {
                    Text(
                        text = "عرض الدليل بالكامل",
                        style = MaterialTheme.typography.labelMedium.copy(
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF34D399),
                            fontSize = 13.sp
                        ),
                        modifier = Modifier.testTag("reception_guide_action_text")
                    )
                    Icon(
                        imageVector = Icons.Outlined.ChevronLeft,
                        contentDescription = null,
                        tint = Color(0xFF34D399),
                        modifier = Modifier.size(16.dp)
                    )
                }

                // Divider inside card
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(Color(0x2EFFFFFF))
                )

                // Small icon shortcuts:
                // 📡 الترددات
                // 🗺️ التغطية
                // 📺 معلومات الرسيفر والبطاقة
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("reception_guide_shortcuts_row"),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    ReceptionShortcutItem(
                        icon = Icons.Outlined.Sensors,
                        iconTint = Color(0xFFFDE047),
                        title = "الترددات",
                        tag = "reception_shortcut_frequencies",
                        onClick = onFrequenciesClick,
                        modifier = Modifier.weight(1f)
                    )
                    ReceptionShortcutItem(
                        icon = Icons.Outlined.Public,
                        iconTint = Color(0xFFFDA4AF),
                        title = "التغطية",
                        tag = "reception_shortcut_coverage",
                        onClick = onCoverageClick,
                        modifier = Modifier.weight(1f)
                    )
                    ReceptionShortcutItem(
                        icon = Icons.Outlined.Tv,
                        iconTint = Color(0xFF7DD3FC),
                        title = "معلومات الرسيفر",
                        tag = "reception_shortcut_receiver_info",
                        onClick = onReceiverInfoClick,
                        modifier = Modifier.weight(1.2f)
                    )
                }
            }
        }
    }
}

@Composable
private fun ReceptionShortcutItem(
    icon: ImageVector,
    iconTint: Color,
    title: String,
    tag: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        onClick = onClick,
        modifier = modifier.testTag(tag),
        shape = RoundedCornerShape(10.dp),
        color = Color(0x33000000),
        border = BorderStroke(1.dp, iconTint.copy(alpha = 0.35f))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = iconTint,
                modifier = Modifier.size(16.dp)
            )
            Text(
                text = title,
                style = MaterialTheme.typography.labelSmall.copy(
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFFF1F5F9),
                    fontSize = 11.sp
                ),
                maxLines = 1
            )
        }
    }
}

@Composable
fun SupportAndContactSection(
    onFaqClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.testTag("support_contact_section"),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // Section Title: "الدعم والتواصل"
        Text(
            text = "الدعم والتواصل",
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Bold,
                color = Color(0xFFF8FAFC)
            ),
            modifier = Modifier.testTag("support_contact_title")
        )

        // 1. "فتح بلاغ" — Muted Blue
        SupportActionCardItem(
            title = "فتح بلاغ",
            subtitle = "أرسل مشكلة أو طلب دعم",
            icon = Icons.Outlined.HeadsetMic,
            accentColor = Color(0xFF38BDF8),
            iconBgColor = Color(0x330284C7),
            cardBgGradient = listOf(Color(0xFF162A45), Color(0xFF132238)),
            borderColor = Color(0x3D38BDF8),
            testTag = "support_card_ticket"
        )

        // 2. "الأسئلة الشائعة" — Violet / Indigo
        SupportActionCardItem(
            title = "الأسئلة الشائعة",
            subtitle = "إجابات سريعة للمشكلات المتكررة",
            icon = Icons.Outlined.HelpOutline,
            accentColor = Color(0xFFA78BFA),
            iconBgColor = Color(0x337C3AED),
            cardBgGradient = listOf(Color(0xFF231C42), Color(0xFF1C1635)),
            borderColor = Color(0x3DA78BFA),
            testTag = "support_card_faq",
            onClick = onFaqClick
        )

        // 3. "تواصل معنا" — Green-Teal
        SupportActionCardItem(
            title = "تواصل معنا",
            subtitle = "قنوات التواصل الرسمية",
            icon = Icons.Outlined.Chat,
            accentColor = Color(0xFF2DD4BF),
            iconBgColor = Color(0x330D9488),
            cardBgGradient = listOf(Color(0xFF103634), Color(0xFF0C2B29)),
            borderColor = Color(0x3D2DD4BF),
            testTag = "support_card_contact"
        )
    }
}

@Composable
private fun SupportActionCardItem(
    title: String,
    subtitle: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    accentColor: Color,
    iconBgColor: Color,
    cardBgGradient: List<Color>,
    borderColor: Color,
    testTag: String,
    onClick: () -> Unit = {}
) {
    Card(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .testTag(testTag),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        ),
        border = BorderStroke(1.dp, borderColor)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Brush.linearGradient(cardBgGradient))
                .padding(horizontal = 16.dp, vertical = 14.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            // Icon Container
            Surface(
                shape = RoundedCornerShape(12.dp),
                color = iconBgColor,
                border = BorderStroke(1.dp, borderColor),
                modifier = Modifier.size(42.dp)
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        tint = accentColor,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }

            // Title and Subtitle Column
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(2.dp)
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleSmall.copy(
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFFF8FAFC),
                        fontSize = 14.sp
                    )
                )
                Text(
                    text = subtitle,
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = Color(0xFF94A3B8),
                        fontSize = 12.sp
                    )
                )
            }

            // Chevron indicator
            Icon(
                imageVector = Icons.Outlined.ChevronLeft,
                contentDescription = null,
                tint = accentColor.copy(alpha = 0.8f),
                modifier = Modifier.size(18.dp)
            )
        }
    }
}

private data class BottomNavItem(
    val label: String,
    val icon: ImageVector,
    val isSelected: Boolean,
    val testTag: String
)

@Composable
fun HomeBottomNavigationBar(
    onAccountClick: () -> Unit = {},
    onChannelsClick: () -> Unit = {},
    onNavigateToSupport: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    val items = listOf(
        BottomNavItem(
            label = "الرئيسية",
            icon = Icons.Outlined.Home,
            isSelected = true,
            testTag = "nav_item_home"
        ),
        BottomNavItem(
            label = "الخدمات",
            icon = Icons.Outlined.Widgets,
            isSelected = false,
            testTag = "nav_item_services"
        ),
        BottomNavItem(
            label = "الإعلام",
            icon = Icons.Outlined.Tv,
            isSelected = false,
            testTag = "nav_item_media"
        ),
        BottomNavItem(
            label = "الدعم",
            icon = Icons.Outlined.HeadsetMic,
            isSelected = false,
            testTag = "nav_item_support"
        ),
        BottomNavItem(
            label = "حسابي",
            icon = Icons.Outlined.AccountCircle,
            isSelected = false,
            testTag = "nav_item_profile"
        )
    )

    Surface(
        modifier = modifier.testTag("home_bottom_navigation_bar"),
        color = Color(0xFF102636), // Darkened petroleum / blue surface (not black)
        border = BorderStroke(1.dp, Color(0x2638BDF8)),
        tonalElevation = 8.dp
    ) {
        NavigationBar(
            containerColor = Color.Transparent,
            tonalElevation = 0.dp
        ) {
            val navItemColors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color(0xFF38BDF8),
                selectedTextColor = Color(0xFF38BDF8),
                unselectedIconColor = Color(0xFF8EA9B5),
                unselectedTextColor = Color(0xFF8EA9B5),
                indicatorColor = Color(0x2938BDF8)
            )

            items.forEach { item ->
                NavigationBarItem(
                    selected = item.isSelected,
                    onClick = {
                        if (item.testTag == "nav_item_profile") {
                            onAccountClick()
                        } else if (item.testTag == "nav_item_media") {
                            onChannelsClick()
                        } else if (item.testTag == "nav_item_support") {
                            onNavigateToSupport()
                        }
                    },
                    icon = {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = item.label,
                            modifier = Modifier.size(22.dp)
                        )
                    },
                    label = {
                        Text(
                            text = item.label,
                            style = MaterialTheme.typography.labelSmall.copy(
                                fontWeight = if (item.isSelected) FontWeight.Bold else FontWeight.Normal,
                                fontSize = 11.sp
                            )
                        )
                    },
                    colors = navItemColors,
                    modifier = Modifier.testTag(item.testTag)
                )
            }
        }
    }
}





