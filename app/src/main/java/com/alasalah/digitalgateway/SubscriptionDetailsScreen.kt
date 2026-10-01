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
import androidx.compose.material.icons.outlined.Check
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
fun SubscriptionDetailsScreen(
    onBackClick: () -> Unit = {},
    onNavigateToRenewal: () -> Unit = {}
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

    val packageFeatures = listOf(
        "بث رقمي عالي الجودة",
        "تحديثات مستمرة",
        "دعم فني"
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
                    .testTag("subscription_details_scaffold"),
                containerColor = Color.Transparent,
                topBar = {
                    TopAppBar(
                        title = {
                            Text(
                                text = "تفاصيل الاشتراك",
                                style = MaterialTheme.typography.titleLarge.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFFF8FAFC),
                                    fontSize = 20.sp
                                ),
                                modifier = Modifier.testTag("subscription_details_title")
                            )
                        },
                        navigationIcon = {
                            IconButton(
                                onClick = onBackClick,
                                modifier = Modifier.testTag("subscription_details_back_button")
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
                        .verticalScroll(rememberScrollState()),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Spacer(modifier = Modifier.height(4.dp))

                    // Main Glass Card for Subscription Details
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("subscription_details_card"),
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
                            // Header Row: Package name + Status badge
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                                ) {
                                    Surface(
                                        shape = RoundedCornerShape(12.dp),
                                        color = Color(0x33818CF8),
                                        border = BorderStroke(1.dp, Color(0x4D818CF8)),
                                        modifier = Modifier.size(46.dp)
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
                                                fontSize = 17.sp
                                            ),
                                            modifier = Modifier.testTag("subscription_details_package")
                                        )
                                    }
                                }

                                // Status Badge: "نشط"
                                Surface(
                                    shape = RoundedCornerShape(8.dp),
                                    color = Color(0x2E34D399),
                                    border = BorderStroke(1.dp, Color(0x4D34D399))
                                ) {
                                    Row(
                                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.spacedBy(6.dp)
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
                                            modifier = Modifier.testTag("subscription_details_status")
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

                            // Smart Card row
                            DetailInfoRow(
                                icon = Icons.Outlined.CreditCard,
                                iconTint = Color(0xFF38BDF8),
                                label = "رقم البطاقة الذكية",
                                value = "**** 4587",
                                tag = "subscription_details_smart_card"
                            )

                            // Start Date
                            DetailInfoRow(
                                icon = Icons.Outlined.CalendarToday,
                                iconTint = Color(0xFF67E8F9),
                                label = "تاريخ البدء",
                                value = "15 أغسطس 2026",
                                tag = "subscription_details_start_date"
                            )

                            // Expiry Date
                            DetailInfoRow(
                                icon = Icons.Outlined.Event,
                                iconTint = Color(0xFFF87171),
                                label = "تاريخ الانتهاء",
                                value = "15 أكتوبر 2026",
                                tag = "subscription_details_expiry_date"
                            )

                            // Remaining Duration
                            DetailInfoRow(
                                icon = Icons.Outlined.Schedule,
                                iconTint = Color(0xFFFBBF24),
                                label = "المدة المتبقية",
                                value = "16 يوم",
                                tag = "subscription_details_remaining"
                            )
                        }
                    }

                    // Package Features Glass Card
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("subscription_features_card"),
                        shape = RoundedCornerShape(20.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color(0x2EFFFFFF)
                        ),
                        border = BorderStroke(1.dp, Color(0x29FFFFFF))
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(18.dp),
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            Text(
                                text = "مزايا الباقة",
                                style = MaterialTheme.typography.titleMedium.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFFF8FAFC),
                                    fontSize = 15.sp
                                ),
                                modifier = Modifier.testTag("subscription_features_title")
                            )

                            packageFeatures.forEachIndexed { index, feature ->
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                                ) {
                                    Surface(
                                        shape = CircleShape,
                                        color = Color(0x3334D399),
                                        border = BorderStroke(1.dp, Color(0x6634D399)),
                                        modifier = Modifier.size(24.dp)
                                    ) {
                                        Box(
                                            contentAlignment = Alignment.Center,
                                            modifier = Modifier.fillMaxSize()
                                        ) {
                                            Icon(
                                                imageVector = Icons.Outlined.Check,
                                                contentDescription = null,
                                                tint = Color(0xFF34D399),
                                                modifier = Modifier.size(14.dp)
                                            )
                                        }
                                    }
                                    Text(
                                        text = feature,
                                        style = MaterialTheme.typography.bodyMedium.copy(
                                            color = Color(0xFFF1F5F9),
                                            fontSize = 13.sp
                                        ),
                                        modifier = Modifier.testTag("subscription_feature_$index")
                                    )
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    // Primary Button: "تجديد الاشتراك"
                    Button(
                        onClick = onNavigateToRenewal,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(52.dp)
                            .testTag("subscription_details_renew_button"),
                        shape = RoundedCornerShape(14.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF0284C7),
                            contentColor = Color.White
                        ),
                        elevation = ButtonDefaults.buttonElevation(defaultElevation = 4.dp)
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
                                style = MaterialTheme.typography.titleMedium.copy(
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 16.sp
                                )
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(20.dp))
                }
            }
        }
    }
}

@Composable
private fun DetailInfoRow(
    icon: ImageVector,
    iconTint: Color,
    label: String,
    value: String,
    tag: String
) {
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
                shape = RoundedCornerShape(8.dp),
                color = iconTint.copy(alpha = 0.16f),
                modifier = Modifier.size(34.dp)
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        tint = iconTint,
                        modifier = Modifier.size(18.dp)
                    )
                }
            }
            Text(
                text = label,
                style = MaterialTheme.typography.bodySmall.copy(
                    color = Color(0xFF94A3B8),
                    fontSize = 12.sp
                )
            )
        }
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
