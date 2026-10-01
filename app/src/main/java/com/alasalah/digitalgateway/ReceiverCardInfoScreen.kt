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
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.CreditCard
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Tv
import androidx.compose.material.icons.outlined.VerifiedUser
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
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReceiverCardInfoScreen(
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

    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(broadcastBackground)
        ) {
            Scaffold(
                modifier = Modifier
                    .fillMaxSize()
                    .testTag("receiver_card_info_scaffold"),
                containerColor = Color.Transparent,
                topBar = {
                    TopAppBar(
                        title = {
                            Text(
                                text = "معلومات الرسيفر والبطاقة",
                                style = MaterialTheme.typography.titleLarge.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFFF8FAFC),
                                    fontSize = 19.sp
                                ),
                                modifier = Modifier.testTag("receiver_card_info_title")
                            )
                        },
                        navigationIcon = {
                            IconButton(
                                onClick = onBackClick,
                                modifier = Modifier.testTag("receiver_card_info_back_button")
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

                    // SECTION 1: مواصفات جهاز الاستقبال
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("card_receiver_specs"),
                        shape = RoundedCornerShape(20.dp),
                        colors = CardDefaults.cardColors(containerColor = Color(0x381E3A8A)),
                        border = BorderStroke(1.2.dp, Color(0x4D38BDF8))
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(18.dp),
                            verticalArrangement = Arrangement.spacedBy(14.dp)
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(10.dp)
                            ) {
                                Surface(
                                    shape = RoundedCornerShape(12.dp),
                                    color = Color(0x3338BDF8),
                                    border = BorderStroke(1.dp, Color(0x4D38BDF8)),
                                    modifier = Modifier.size(42.dp)
                                ) {
                                    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                                        Icon(
                                            imageVector = Icons.Outlined.Tv,
                                            contentDescription = null,
                                            tint = Color(0xFF38BDF8),
                                            modifier = Modifier.size(24.dp)
                                        )
                                    }
                                }
                                Text(
                                    text = "مواصفات جهاز الاستقبال",
                                    style = MaterialTheme.typography.titleMedium.copy(
                                        fontWeight = FontWeight.Bold,
                                        color = Color(0xFFF1F5F9),
                                        fontSize = 16.sp
                                    )
                                )
                            }

                            Box(modifier = Modifier.fillMaxWidth().height(1.dp).background(Color(0x24FFFFFF)))

                            InfoItem(text = "دعم معيار البث الأرضي DVB-T2.")
                            InfoItem(text = "وجود قارئ بطاقة ذكية Smart Card Reader / CA Slot للقنوات المشفرة.")
                            InfoItem(text = "دعم نظام Multi-CAS.")
                            InfoItem(text = "التوافق مع بطاقة DRE-Crypt.")

                            // Note
                            Card(
                                modifier = Modifier.fillMaxWidth(),
                                shape = RoundedCornerShape(12.dp),
                                colors = CardDefaults.cardColors(containerColor = Color(0x260284C7)),
                                border = BorderStroke(1.dp, Color(0x3D38BDF8))
                            ) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(12.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.Outlined.Info,
                                        contentDescription = null,
                                        tint = Color(0xFF38BDF8),
                                        modifier = Modifier.size(18.dp)
                                    )
                                    Text(
                                        text = "الجهاز ليس خاصاً بشبكة الأصالة، ويمكن استخدام أي جهاز متوافق مع المتطلبات.",
                                        style = MaterialTheme.typography.bodySmall.copy(
                                            color = Color(0xFFE2E8F0),
                                            fontSize = 12.sp,
                                            lineHeight = 18.sp
                                        )
                                    )
                                }
                            }
                        }
                    }

                    // SECTION 2: أجهزة متوافقة
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("card_compatible_devices"),
                        shape = RoundedCornerShape(20.dp),
                        colors = CardDefaults.cardColors(containerColor = Color(0x381E3A8A)),
                        border = BorderStroke(1.2.dp, Color(0x4DFBBF24))
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(18.dp),
                            verticalArrangement = Arrangement.spacedBy(14.dp)
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(10.dp)
                            ) {
                                Surface(
                                    shape = RoundedCornerShape(12.dp),
                                    color = Color(0x33FBBF24),
                                    border = BorderStroke(1.dp, Color(0x4DFBBF24)),
                                    modifier = Modifier.size(42.dp)
                                ) {
                                    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                                        Icon(
                                            imageVector = Icons.Outlined.VerifiedUser,
                                            contentDescription = null,
                                            tint = Color(0xFFFBBF24),
                                            modifier = Modifier.size(24.dp)
                                        )
                                    }
                                }
                                Text(
                                    text = "أجهزة متوافقة",
                                    style = MaterialTheme.typography.titleMedium.copy(
                                        fontWeight = FontWeight.Bold,
                                        color = Color(0xFFF1F5F9),
                                        fontSize = 16.sp
                                    )
                                )
                            }

                            Box(modifier = Modifier.fillMaxWidth().height(1.dp).background(Color(0x24FFFFFF)))

                            // Ali Processors
                            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                                Text(
                                    text = "معالجات Ali",
                                    style = MaterialTheme.typography.titleSmall.copy(
                                        fontWeight = FontWeight.Bold,
                                        color = Color(0xFFFDE68A),
                                        fontSize = 14.sp
                                    )
                                )
                                Text(
                                    text = "Ali 3510 / Ali 3821",
                                    style = MaterialTheme.typography.bodyMedium.copy(
                                        color = Color(0xFFE2E8F0),
                                        fontSize = 13.sp
                                    )
                                )
                            }

                            // GX Processors
                            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                                Text(
                                    text = "معالجات GX",
                                    style = MaterialTheme.typography.titleSmall.copy(
                                        fontWeight = FontWeight.Bold,
                                        color = Color(0xFFFDE68A),
                                        fontSize = 14.sp
                                    )
                                )
                                Text(
                                    text = "GX6605S",
                                    style = MaterialTheme.typography.bodyMedium.copy(
                                        color = Color(0xFFE2E8F0),
                                        fontSize = 13.sp
                                    )
                                )
                                Text(
                                    text = "يعمل عند توفر دعم Multi-CAS المناسب.",
                                    style = MaterialTheme.typography.bodySmall.copy(
                                        color = Color(0xFF94A3B8),
                                        fontSize = 12.sp
                                    )
                                )
                            }

                            // Other compatible brands
                            Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                                Text(
                                    text = "علامات تجارية متوافقة أخرى:",
                                    style = MaterialTheme.typography.titleSmall.copy(
                                        fontWeight = FontWeight.Bold,
                                        color = Color(0xFFFDE68A),
                                        fontSize = 14.sp
                                    )
                                )
                                Row(
                                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    BrandTag(name = "Tiger")
                                    BrandTag(name = "Starsat")
                                    BrandTag(name = "Geant")
                                }
                            }

                            // Note
                            Card(
                                modifier = Modifier.fillMaxWidth(),
                                shape = RoundedCornerShape(12.dp),
                                colors = CardDefaults.cardColors(containerColor = Color(0x26FBBF24)),
                                border = BorderStroke(1.dp, Color(0x4DFBBF24))
                            ) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(12.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.Outlined.Info,
                                        contentDescription = null,
                                        tint = Color(0xFFFBBF24),
                                        modifier = Modifier.size(18.dp)
                                    )
                                    Text(
                                        text = "يشترط دعم DVB-T2 وفتحة البطاقة والتوافق مع نظام البطاقة.",
                                        style = MaterialTheme.typography.bodySmall.copy(
                                            color = Color(0xFFFEF08A),
                                            fontSize = 12.sp,
                                            lineHeight = 18.sp
                                        )
                                    )
                                }
                            }
                        }
                    }

                    // SECTION 3: البطاقة الذكية والتفعيل
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("card_smart_card_info"),
                        shape = RoundedCornerShape(20.dp),
                        colors = CardDefaults.cardColors(containerColor = Color(0x381E3A8A)),
                        border = BorderStroke(1.2.dp, Color(0x4DA78BFA))
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(18.dp),
                            verticalArrangement = Arrangement.spacedBy(14.dp)
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(10.dp)
                            ) {
                                Surface(
                                    shape = RoundedCornerShape(12.dp),
                                    color = Color(0x33A78BFA),
                                    border = BorderStroke(1.dp, Color(0x4DA78BFA)),
                                    modifier = Modifier.size(42.dp)
                                ) {
                                    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                                        Icon(
                                            imageVector = Icons.Outlined.CreditCard,
                                            contentDescription = null,
                                            tint = Color(0xFFA78BFA),
                                            modifier = Modifier.size(24.dp)
                                        )
                                    }
                                }
                                Text(
                                    text = "البطاقة الذكية والتفعيل",
                                    style = MaterialTheme.typography.titleMedium.copy(
                                        fontWeight = FontWeight.Bold,
                                        color = Color(0xFFF1F5F9),
                                        fontSize = 16.sp
                                    )
                                )
                            }

                            Box(modifier = Modifier.fillMaxWidth().height(1.dp).background(Color(0x24FFFFFF)))

                            InfoItem(text = "بعد شراء البطاقة وتفعيلها يتم التفعيل تلقائياً.")
                            InfoItem(text = "يتم إدخال البطاقة في جهاز استقبال متوافق.")
                            InfoItem(text = "في حال عدم عمل الخدمة يتم التواصل مع الدعم الفني.")
                        }
                    }

                    Spacer(modifier = Modifier.height(20.dp))
                }
            }
        }
    }
}

@Composable
private fun InfoItem(text: String) {
    Row(
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Icon(
            imageVector = Icons.Outlined.CheckCircle,
            contentDescription = null,
            tint = Color(0xFF34D399),
            modifier = Modifier.size(16.dp).padding(top = 2.dp)
        )
        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium.copy(
                color = Color(0xFFE2E8F0),
                fontSize = 13.sp,
                lineHeight = 20.sp
            )
        )
    }
}

@Composable
private fun BrandTag(name: String) {
    Surface(
        shape = RoundedCornerShape(8.dp),
        color = Color(0x3338BDF8),
        border = BorderStroke(1.dp, Color(0x4D38BDF8))
    ) {
        Text(
            text = name,
            style = MaterialTheme.typography.labelMedium.copy(
                color = Color(0xFFE0F2FE),
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp
            ),
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
        )
    }
}
