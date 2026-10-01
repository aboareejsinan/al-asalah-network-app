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
import androidx.compose.material.icons.outlined.Autorenew
import androidx.compose.material.icons.outlined.CalendarToday
import androidx.compose.material.icons.outlined.CardMembership
import androidx.compose.material.icons.outlined.CreditCard
import androidx.compose.material.icons.outlined.Schedule
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
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
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MySubscriptionScreen(
    onBackClick: () -> Unit = {},
    onNavigateToRenewal: () -> Unit = {}
) {
    BackHandler {
        onBackClick()
    }

    // Modern mid-tone petroleum blue vertical gradient matching app identity
    val broadcastBackground = Brush.verticalGradient(
        colors = listOf(
            Color(0xFF1B3D4F), // Medium petroleum blue
            Color(0xFF174758), // Subtle muted teal
            Color(0xFF1A3B4D)  // Digital petroleum blue
        )
    )

    // Coordinated deep blue-indigo / muted violet-blue gradient consistent with HomeScreen subscription card
    val cardGradient = Brush.linearGradient(
        colors = listOf(
            Color(0xFF2D3B68), // Deep blue-indigo
            Color(0xFF38315E)  // Muted violet-blue
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
                    .testTag("my_subscription_scaffold"),
                containerColor = Color.Transparent,
                topBar = {
                    TopAppBar(
                        title = {
                            Text(
                                text = "اشتراكي",
                                style = MaterialTheme.typography.titleLarge.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFFF8FAFC),
                                    fontSize = 20.sp
                                ),
                                modifier = Modifier.testTag("subscription_screen_title")
                            )
                        },
                        navigationIcon = {
                            IconButton(
                                onClick = onBackClick,
                                modifier = Modifier.testTag("subscription_back_button")
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
                        ),
                        modifier = Modifier.testTag("subscription_top_bar")
                    )
                }
            ) { innerPadding ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                        .padding(horizontal = 20.dp, vertical = 12.dp)
                        .verticalScroll(rememberScrollState())
                        .testTag("subscription_content_column"),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    // Prominent Subscription Information Card
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("subscription_info_card"),
                        shape = RoundedCornerShape(20.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color.Transparent
                        ),
                        border = BorderStroke(1.dp, Color(0x4D818CF8)),
                        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(cardGradient)
                                .padding(20.dp),
                            verticalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            // Header Row: Package name + Green Status Indicator
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                // Right (RTL start): Package Icon & Name
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                                ) {
                                    Surface(
                                        shape = RoundedCornerShape(12.dp),
                                        color = Color(0x33818CF8),
                                        border = BorderStroke(1.dp, Color(0x4D818CF8)),
                                        modifier = Modifier.size(44.dp)
                                    ) {
                                        Box(
                                            contentAlignment = Alignment.Center,
                                            modifier = Modifier.fillMaxSize()
                                        ) {
                                            Icon(
                                                imageVector = Icons.Outlined.CardMembership,
                                                contentDescription = null,
                                                tint = Color(0xFFA5B4FC),
                                                modifier = Modifier.size(24.dp)
                                            )
                                        }
                                    }

                                    Column(
                                        verticalArrangement = Arrangement.spacedBy(2.dp)
                                    ) {
                                        Text(
                                            text = "الباقة الأساسية",
                                            style = MaterialTheme.typography.titleMedium.copy(
                                                fontWeight = FontWeight.Bold,
                                                color = Color(0xFFF1F5F9),
                                                fontSize = 17.sp
                                            ),
                                            modifier = Modifier.testTag("subscription_package_name")
                                        )
                                        Text(
                                            text = "البث التلفزيوني الرقمي",
                                            style = MaterialTheme.typography.bodySmall.copy(
                                                color = Color(0xFFC7D2FE),
                                                fontSize = 12.sp
                                            ),
                                            modifier = Modifier.testTag("subscription_package_type")
                                        )
                                    }
                                }

                                // Left (RTL end): Status Indicator in Green ("نشط")
                                Surface(
                                    color = Color(0x2610B981),
                                    shape = RoundedCornerShape(20.dp),
                                    border = BorderStroke(1.dp, Color(0x4D10B981)),
                                    modifier = Modifier.testTag("subscription_status_badge")
                                ) {
                                    Row(
                                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                                    ) {
                                        Box(
                                            modifier = Modifier
                                                .size(8.dp)
                                                .background(Color(0xFF34D399), shape = CircleShape)
                                        )
                                        Text(
                                            text = "نشط",
                                            style = MaterialTheme.typography.labelMedium.copy(
                                                fontWeight = FontWeight.Bold,
                                                color = Color(0xFF34D399),
                                                fontSize = 13.sp
                                            )
                                        )
                                    }
                                }
                            }

                            HorizontalDivider(
                                color = Color(0x26FFFFFF),
                                thickness = 1.dp
                            )

                            // Smart Card Item
                            Surface(
                                shape = RoundedCornerShape(12.dp),
                                color = Color(0x1FFFFFFF),
                                border = BorderStroke(1.dp, Color(0x26FFFFFF)),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .testTag("smart_card_container")
                            ) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 14.dp, vertical = 12.dp),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                                    ) {
                                        Icon(
                                            imageVector = Icons.Outlined.CreditCard,
                                            contentDescription = null,
                                            tint = Color(0xFFA5B4FC),
                                            modifier = Modifier.size(20.dp)
                                        )
                                        Text(
                                            text = "البطاقة الذكية",
                                            style = MaterialTheme.typography.bodyMedium.copy(
                                                color = Color(0xFFE2E8F0),
                                                fontSize = 13.sp
                                            )
                                        )
                                    }

                                    Text(
                                        text = "**** 4587",
                                        style = MaterialTheme.typography.bodyMedium.copy(
                                            fontWeight = FontWeight.Bold,
                                            color = Color(0xFFFFFFFF),
                                            fontSize = 14.sp
                                        ),
                                        modifier = Modifier.testTag("smart_card_number")
                                    )
                                }
                            }

                            // Dates & Remaining Details Grid
                            Column(
                                verticalArrangement = Arrangement.spacedBy(10.dp)
                            ) {
                                // Start Date & Expiry Date Row
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                                ) {
                                    // Start date
                                    Surface(
                                        modifier = Modifier
                                            .weight(1f)
                                            .testTag("subscription_start_date_card"),
                                        shape = RoundedCornerShape(12.dp),
                                        color = Color(0x1A000000),
                                        border = BorderStroke(1.dp, Color(0x24FFFFFF))
                                    ) {
                                        Column(
                                            modifier = Modifier.padding(12.dp),
                                            verticalArrangement = Arrangement.spacedBy(4.dp)
                                        ) {
                                            Row(
                                                verticalAlignment = Alignment.CenterVertically,
                                                horizontalArrangement = Arrangement.spacedBy(6.dp)
                                            ) {
                                                Icon(
                                                    imageVector = Icons.Outlined.CalendarToday,
                                                    contentDescription = null,
                                                    tint = Color(0xFF93C5FD),
                                                    modifier = Modifier.size(15.dp)
                                                )
                                                Text(
                                                    text = "تاريخ البدء",
                                                    style = MaterialTheme.typography.labelSmall.copy(
                                                        color = Color(0xFF94A3B8),
                                                        fontSize = 11.sp
                                                    )
                                                )
                                            }
                                            Text(
                                                text = "15 أغسطس 2026",
                                                style = MaterialTheme.typography.bodySmall.copy(
                                                    fontWeight = FontWeight.SemiBold,
                                                    color = Color(0xFFF1F5F9),
                                                    fontSize = 13.sp
                                                ),
                                                modifier = Modifier.testTag("subscription_start_date_value")
                                            )
                                        }
                                    }

                                    // Expiry date
                                    Surface(
                                        modifier = Modifier
                                            .weight(1f)
                                            .testTag("subscription_expiry_date_card"),
                                        shape = RoundedCornerShape(12.dp),
                                        color = Color(0x1A000000),
                                        border = BorderStroke(1.dp, Color(0x24FFFFFF))
                                    ) {
                                        Column(
                                            modifier = Modifier.padding(12.dp),
                                            verticalArrangement = Arrangement.spacedBy(4.dp)
                                        ) {
                                            Row(
                                                verticalAlignment = Alignment.CenterVertically,
                                                horizontalArrangement = Arrangement.spacedBy(6.dp)
                                            ) {
                                                Icon(
                                                    imageVector = Icons.Outlined.CalendarToday,
                                                    contentDescription = null,
                                                    tint = Color(0xFFFDA4AF),
                                                    modifier = Modifier.size(15.dp)
                                                )
                                                Text(
                                                    text = "تاريخ الانتهاء",
                                                    style = MaterialTheme.typography.labelSmall.copy(
                                                        color = Color(0xFF94A3B8),
                                                        fontSize = 11.sp
                                                    )
                                                )
                                            }
                                            Text(
                                                text = "15 أكتوبر 2026",
                                                style = MaterialTheme.typography.bodySmall.copy(
                                                    fontWeight = FontWeight.SemiBold,
                                                    color = Color(0xFFF1F5F9),
                                                    fontSize = 13.sp
                                                ),
                                                modifier = Modifier.testTag("subscription_expiry_date_value")
                                            )
                                        }
                                    }
                                }

                                // Remaining Period Row: "متبقي 16 يوم"
                                Surface(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .testTag("subscription_remaining_card"),
                                    shape = RoundedCornerShape(12.dp),
                                    color = Color(0x2438BDF8),
                                    border = BorderStroke(1.dp, Color(0x4D38BDF8))
                                ) {
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(horizontal = 14.dp, vertical = 10.dp),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Row(
                                            verticalAlignment = Alignment.CenterVertically,
                                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                                        ) {
                                            Icon(
                                                imageVector = Icons.Outlined.Schedule,
                                                contentDescription = null,
                                                tint = Color(0xFF38BDF8),
                                                modifier = Modifier.size(18.dp)
                                            )
                                            Text(
                                                text = "الفترة المتبقية",
                                                style = MaterialTheme.typography.bodySmall.copy(
                                                    color = Color(0xFFE0F2FE),
                                                    fontSize = 13.sp
                                                )
                                            )
                                        }

                                        Text(
                                            text = "متبقي 16 يوم",
                                            style = MaterialTheme.typography.bodyMedium.copy(
                                                fontWeight = FontWeight.Bold,
                                                color = Color(0xFF38BDF8),
                                                fontSize = 14.sp
                                            ),
                                            modifier = Modifier.testTag("subscription_remaining_value")
                                        )
                                    }
                                }
                            }

                            Spacer(modifier = Modifier.height(4.dp))

                            // Primary Action: "تجديد الاشتراك"
                            Button(
                                onClick = onNavigateToRenewal,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(48.dp)
                                    .testTag("renew_subscription_button"),
                                shape = RoundedCornerShape(12.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(0xFF2563EB),
                                    contentColor = Color(0xFFFFFFFF)
                                )
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.Outlined.Autorenew,
                                        contentDescription = null,
                                        modifier = Modifier.size(20.dp)
                                    )
                                    Text(
                                        text = "تجديد الاشتراك",
                                        style = MaterialTheme.typography.labelLarge.copy(
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 15.sp
                                        )
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
