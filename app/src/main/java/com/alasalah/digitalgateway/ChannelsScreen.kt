package com.alasalah.digitalgateway

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
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
import androidx.compose.material.icons.outlined.CellTower
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Tag
import androidx.compose.material.icons.outlined.Tv
import androidx.compose.material.icons.outlined.VerifiedUser
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
 * Data structure for DVB-T2 broadcasting channel packages.
 * Designed for future replacement with real channel data when available.
 */
data class ChannelPackage(
    val id: String,
    val name: String,
    val icon: ImageVector,
    val channelCount: String,
    val accessType: String,
    val description: String,
    val categories: List<String>,
    val accentColor: Color,
    val isEncrypted: Boolean
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChannelsScreen(
    onBackClick: () -> Unit = {},
    onPackageClick: (ChannelPackage) -> Unit = {}
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

    // Data-driven local package structure
    val packages = remember {
        listOf(
            ChannelPackage(
                id = "encrypted_package",
                name = "الباقة المشفرة",
                icon = Icons.Outlined.Lock,
                channelCount = "20 قناة",
                accessType = "للمشتركين فقط",
                description = "تضم قنوات رياضية مشفرة بالإضافة إلى قنوات الأفلام والدراما والترفيه.",
                categories = listOf("رياضة", "أفلام", "دراما", "ترفيه"),
                accentColor = Color(0xFF38BDF8), // Teal / cyan accent
                isEncrypted = true
            ),
            ChannelPackage(
                id = "open_package",
                name = "الباقة المفتوحة",
                icon = Icons.Outlined.CellTower,
                channelCount = "20 قناة",
                accessType = "متاحة للجميع",
                description = "مجموعة قنوات متنوعة متاحة بدون اشتراك.",
                categories = listOf("أخبار", "عامة", "دينية", "أطفال", "ترفيه", "محلية"),
                accentColor = Color(0xFF34D399), // Emerald / green accent
                isEncrypted = false
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
                    .testTag("channels_screen_scaffold"),
                containerColor = Color.Transparent,
                topBar = {
                    TopAppBar(
                        title = {
                            Text(
                                text = "القنوات",
                                style = MaterialTheme.typography.titleLarge.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFFF8FAFC),
                                    fontSize = 20.sp
                                ),
                                modifier = Modifier.testTag("channels_screen_title")
                            )
                        },
                        navigationIcon = {
                            IconButton(
                                onClick = onBackClick,
                                modifier = Modifier.testTag("channels_back_button")
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

                    // Intro TV Network Banner
                    TVNetworkIntroCard()

                    // Render both packages dynamically from the data structure
                    packages.forEach { pkg ->
                        ChannelPackageCard(
                            pkg = pkg,
                            onActionClick = { onPackageClick(pkg) }
                        )
                    }

                    Spacer(modifier = Modifier.height(20.dp))
                }
            }
        }
    }
}

@Composable
private fun TVNetworkIntroCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .testTag("channels_intro_card"),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0x260284C7)
        ),
        border = BorderStroke(1.dp, Color(0x3D38BDF8))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            Surface(
                shape = RoundedCornerShape(12.dp),
                color = Color(0x3338BDF8),
                border = BorderStroke(1.dp, Color(0x4D38BDF8)),
                modifier = Modifier.size(44.dp)
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Tv,
                        contentDescription = null,
                        tint = Color(0xFF7DD3FC),
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
            Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
                Text(
                    text = "بث رقمي DVB-T2",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFFF8FAFC),
                        fontSize = 15.sp
                    )
                )
                Text(
                    text = "بث أرضي عالي الوضوح لشبكة الأصالة الرقمية",
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = Color(0xFF94A3B8),
                        fontSize = 12.sp
                    )
                )
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun ChannelPackageCard(
    pkg: ChannelPackage,
    onActionClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .testTag("package_card_${pkg.id}"),
        shape = RoundedCornerShape(22.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0x381E3A8A)
        ),
        border = BorderStroke(1.2.dp, pkg.accentColor.copy(alpha = 0.45f))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            // Header Row: Package Icon & Name + Access Badge
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
                        color = pkg.accentColor.copy(alpha = 0.18f),
                        border = BorderStroke(1.dp, pkg.accentColor.copy(alpha = 0.4f)),
                        modifier = Modifier.size(46.dp)
                    ) {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier.fillMaxSize()
                        ) {
                            Icon(
                                imageVector = pkg.icon,
                                contentDescription = null,
                                tint = pkg.accentColor,
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    }
                    Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
                        Text(
                            text = pkg.name,
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFFF1F5F9),
                                fontSize = 17.sp
                            ),
                            modifier = Modifier.testTag("package_title_${pkg.id}")
                        )
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.Tag,
                                contentDescription = null,
                                tint = Color(0xFF94A3B8),
                                modifier = Modifier.size(13.dp)
                            )
                            Text(
                                text = pkg.channelCount,
                                style = MaterialTheme.typography.bodySmall.copy(
                                    fontWeight = FontWeight.SemiBold,
                                    color = pkg.accentColor,
                                    fontSize = 12.sp
                                ),
                                modifier = Modifier.testTag("package_channels_count_${pkg.id}")
                            )
                        }
                    }
                }

                // Access badge (e.g., للمشتركين فقط / متاحة للجميع)
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = if (pkg.isEncrypted) Color(0x33F59E0B) else Color(0x3310B981),
                    border = BorderStroke(
                        1.dp,
                        if (pkg.isEncrypted) Color(0x66F59E0B) else Color(0x6610B981)
                    )
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(5.dp)
                    ) {
                        Icon(
                            imageVector = if (pkg.isEncrypted) Icons.Outlined.Lock else Icons.Outlined.VerifiedUser,
                            contentDescription = null,
                            tint = if (pkg.isEncrypted) Color(0xFFFBBF24) else Color(0xFF34D399),
                            modifier = Modifier.size(13.dp)
                        )
                        Text(
                            text = pkg.accessType,
                            style = MaterialTheme.typography.labelMedium.copy(
                                color = if (pkg.isEncrypted) Color(0xFFFDE68A) else Color(0xFF86EFAC),
                                fontWeight = FontWeight.Bold,
                                fontSize = 11.sp
                            ),
                            modifier = Modifier.testTag("package_access_${pkg.id}")
                        )
                    }
                }
            }

            // Description
            Text(
                text = pkg.description,
                style = MaterialTheme.typography.bodySmall.copy(
                    color = Color(0xFFC7D2FE),
                    fontSize = 13.sp,
                    lineHeight = 19.sp
                ),
                modifier = Modifier.testTag("package_desc_${pkg.id}")
            )

            // Divider
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(Color(0x24FFFFFF))
            )

            // Categories Header & Tags
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(
                    text = "التصنيفات:",
                    style = MaterialTheme.typography.labelSmall.copy(
                        color = Color(0xFF94A3B8),
                        fontWeight = FontWeight.Medium,
                        fontSize = 12.sp
                    )
                )

                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    pkg.categories.forEach { category ->
                        Surface(
                            shape = RoundedCornerShape(8.dp),
                            color = Color(0x2EFFFFFF),
                            border = BorderStroke(0.8.dp, Color(0x38FFFFFF))
                        ) {
                            Text(
                                text = category,
                                style = MaterialTheme.typography.labelSmall.copy(
                                    color = Color(0xFFE2E8F0),
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 12.sp
                                ),
                                modifier = Modifier
                                    .padding(horizontal = 10.dp, vertical = 4.dp)
                                    .testTag("package_category_${pkg.id}_$category")
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(2.dp))

            // Action Button: "تفاصيل الباقة"
            Button(
                onClick = onActionClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .testTag("package_action_${pkg.id}"),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = pkg.accentColor.copy(alpha = 0.22f),
                    contentColor = Color(0xFFF8FAFC)
                ),
                border = BorderStroke(1.dp, pkg.accentColor.copy(alpha = 0.5f))
            ) {
                Text(
                    text = "تفاصيل الباقة",
                    style = MaterialTheme.typography.labelLarge.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp
                    )
                )
            }
        }
    }
}
