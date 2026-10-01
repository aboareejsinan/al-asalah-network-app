package com.alasalah.digitalgateway

import android.content.Intent
import android.net.Uri
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.outlined.AccessTime
import androidx.compose.material.icons.outlined.Chat
import androidx.compose.material.icons.outlined.ConfirmationNumber
import androidx.compose.material.icons.outlined.HeadsetMic
import androidx.compose.material.icons.outlined.HelpOutline
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Phone
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SupportScreen(
    onOpenTicket: () -> Unit = {},
    onOpenFaq: () -> Unit = {},
    onBack: () -> Unit = {}
) {
    BackHandler {
        onBack()
    }

    val context = LocalContext.current

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
                    .testTag("support_screen_scaffold"),
                containerColor = Color.Transparent,
                topBar = {
                    TopAppBar(
                        title = {
                            Text(
                                text = "الدعم",
                                style = MaterialTheme.typography.titleLarge.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFFF8FAFC),
                                    fontSize = 20.sp
                                ),
                                modifier = Modifier.testTag("support_screen_title")
                            )
                        },
                        navigationIcon = {
                            IconButton(
                                onClick = onBack,
                                modifier = Modifier.testTag("support_back_button")
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

                    // SECTION 1: خدمات الدعم (Support Services)
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("support_services_section"),
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
                                    modifier = Modifier.size(40.dp)
                                ) {
                                    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                                        Icon(
                                            imageVector = Icons.Outlined.HeadsetMic,
                                            contentDescription = null,
                                            tint = Color(0xFF38BDF8),
                                            modifier = Modifier.size(22.dp)
                                        )
                                    }
                                }
                                Text(
                                    text = "خدمات الدعم",
                                    style = MaterialTheme.typography.titleMedium.copy(
                                        fontWeight = FontWeight.Bold,
                                        color = Color(0xFFF1F5F9),
                                        fontSize = 16.sp
                                    )
                                )
                            }

                            Box(modifier = Modifier.fillMaxWidth().height(1.dp).background(Color(0x24FFFFFF)))

                            // Action Card 1: فتح بلاغ
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable(onClick = onOpenTicket)
                                    .testTag("action_open_ticket"),
                                shape = RoundedCornerShape(14.dp),
                                colors = CardDefaults.cardColors(containerColor = Color(0x260284C7)),
                                border = BorderStroke(1.dp, Color(0x3D38BDF8))
                            ) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(14.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                                ) {
                                    Surface(
                                        shape = RoundedCornerShape(10.dp),
                                        color = Color(0x3338BDF8),
                                        modifier = Modifier.size(42.dp)
                                    ) {
                                        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                                            Icon(
                                                imageVector = Icons.Outlined.ConfirmationNumber,
                                                contentDescription = null,
                                                tint = Color(0xFF38BDF8),
                                                modifier = Modifier.size(22.dp)
                                            )
                                        }
                                    }
                                    Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
                                        Text(
                                            text = "فتح بلاغ",
                                            style = MaterialTheme.typography.titleSmall.copy(
                                                fontWeight = FontWeight.Bold,
                                                color = Color(0xFFF8FAFC),
                                                fontSize = 15.sp
                                            )
                                        )
                                        Text(
                                            text = "إرسال مشكلة أو طلب دعم",
                                            style = MaterialTheme.typography.bodySmall.copy(
                                                color = Color(0xFF94A3B8),
                                                fontSize = 12.sp
                                            )
                                        )
                                    }
                                }
                            }

                            // Action Card 2: الأسئلة الشائعة
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable(onClick = onOpenFaq)
                                    .testTag("action_open_faq"),
                                shape = RoundedCornerShape(14.dp),
                                colors = CardDefaults.cardColors(containerColor = Color(0x260284C7)),
                                border = BorderStroke(1.dp, Color(0x3D38BDF8))
                            ) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(14.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                                ) {
                                    Surface(
                                        shape = RoundedCornerShape(10.dp),
                                        color = Color(0x332DD4BF),
                                        modifier = Modifier.size(42.dp)
                                    ) {
                                        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                                            Icon(
                                                imageVector = Icons.Outlined.HelpOutline,
                                                contentDescription = null,
                                                tint = Color(0xFF2DD4BF),
                                                modifier = Modifier.size(22.dp)
                                            )
                                        }
                                    }
                                    Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
                                        Text(
                                            text = "الأسئلة الشائعة",
                                            style = MaterialTheme.typography.titleSmall.copy(
                                                fontWeight = FontWeight.Bold,
                                                color = Color(0xFFF8FAFC),
                                                fontSize = 15.sp
                                            )
                                        )
                                        Text(
                                            text = "إجابات عن أكثر الاستفسارات شيوعاً",
                                            style = MaterialTheme.typography.bodySmall.copy(
                                                color = Color(0xFF94A3B8),
                                                fontSize = 12.sp
                                            )
                                        )
                                    }
                                }
                            }
                        }
                    }

                    // SECTION 2: تواصل معنا (Contact Information)
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("contact_info_section"),
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
                                    modifier = Modifier.size(40.dp)
                                ) {
                                    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                                        Icon(
                                            imageVector = Icons.Outlined.Phone,
                                            contentDescription = null,
                                            tint = Color(0xFFFBBF24),
                                            modifier = Modifier.size(22.dp)
                                        )
                                    }
                                }
                                Text(
                                    text = "تواصل معنا",
                                    style = MaterialTheme.typography.titleMedium.copy(
                                        fontWeight = FontWeight.Bold,
                                        color = Color(0xFFF1F5F9),
                                        fontSize = 16.sp
                                    )
                                )
                            }

                            Box(modifier = Modifier.fillMaxWidth().height(1.dp).background(Color(0x24FFFFFF)))

                            // Address
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(10.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Outlined.LocationOn,
                                    contentDescription = null,
                                    tint = Color(0xFF38BDF8),
                                    modifier = Modifier.size(20.dp)
                                )
                                Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
                                    Text(
                                        text = "العنوان",
                                        style = MaterialTheme.typography.labelSmall.copy(
                                            color = Color(0xFF94A3B8),
                                            fontSize = 11.sp
                                        )
                                    )
                                    Text(
                                        text = "مأرب - الوادي الصمدة",
                                        style = MaterialTheme.typography.bodyMedium.copy(
                                            color = Color(0xFFF8FAFC),
                                            fontWeight = FontWeight.Medium,
                                            fontSize = 14.sp
                                        )
                                    )
                                }
                            }

                            Box(modifier = Modifier.fillMaxWidth().height(1.dp).background(Color(0x14FFFFFF)))

                            // Phone 1
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.Outlined.Chat,
                                        contentDescription = "واتساب",
                                        tint = Color(0xFF34D399),
                                        modifier = Modifier
                                            .size(20.dp)
                                            .clickable {
                                                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://wa.me/967770775252"))
                                                context.startActivity(intent)
                                            }
                                    )
                                    Text(
                                        text = "770775252",
                                        style = MaterialTheme.typography.bodyLarge.copy(
                                            fontWeight = FontWeight.Bold,
                                            color = Color(0xFFF8FAFC),
                                            fontSize = 16.sp
                                        )
                                    )
                                }
                                Surface(
                                    shape = RoundedCornerShape(8.dp),
                                    color = Color(0x3334D399)
                                ) {
                                    Text(
                                        text = "واتساب مباشر",
                                        style = MaterialTheme.typography.labelSmall.copy(
                                            color = Color(0xFF6EE7B7),
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 12.sp
                                        ),
                                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                                    )
                                }
                            }

                            // Phone 2
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.Outlined.Phone,
                                        contentDescription = "اتصال",
                                        tint = Color(0xFF38BDF8),
                                        modifier = Modifier
                                            .size(20.dp)
                                            .clickable {
                                                val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:711115252"))
                                                context.startActivity(intent)
                                            }
                                    )
                                    Text(
                                        text = "711115252",
                                        style = MaterialTheme.typography.bodyLarge.copy(
                                            fontWeight = FontWeight.Bold,
                                            color = Color(0xFFF8FAFC),
                                            fontSize = 16.sp
                                        )
                                    )
                                }
                                Surface(
                                    shape = RoundedCornerShape(8.dp),
                                    color = Color(0x3338BDF8)
                                ) {
                                    Text(
                                        text = "اتصال مباشر",
                                        style = MaterialTheme.typography.labelSmall.copy(
                                            color = Color(0xFFBAE6FD),
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 12.sp
                                        ),
                                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                                    )
                                }
                            }

                            // Helper Text
                            Text(
                                text = "اضغط على الأيقونة للتواصل مباشرة",
                                style = MaterialTheme.typography.bodySmall.copy(
                                    color = Color(0xFF94A3B8),
                                    fontSize = 12.sp
                                ),
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 4.dp)
                            )
                        }
                    }

                    // SECTION 3: أوقات العمل (Working Hours)
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("working_hours_section"),
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
                                    modifier = Modifier.size(40.dp)
                                ) {
                                    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                                        Icon(
                                            imageVector = Icons.Outlined.AccessTime,
                                            contentDescription = null,
                                            tint = Color(0xFFA78BFA),
                                            modifier = Modifier.size(22.dp)
                                        )
                                    }
                                }
                                Text(
                                    text = "أوقات العمل",
                                    style = MaterialTheme.typography.titleMedium.copy(
                                        fontWeight = FontWeight.Bold,
                                        color = Color(0xFFF1F5F9),
                                        fontSize = 16.sp
                                    )
                                )
                            }

                            Box(modifier = Modifier.fillMaxWidth().height(1.dp).background(Color(0x24FFFFFF)))

                            // Item 1: الدوام الرسمي
                            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                                Text(
                                    text = "الدوام الرسمي",
                                    style = MaterialTheme.typography.titleSmall.copy(
                                        fontWeight = FontWeight.Bold,
                                        color = Color(0xFFDDD6FE),
                                        fontSize = 14.sp
                                    )
                                )
                                Text(
                                    text = "من الساعة 12 ظهراً إلى الساعة 12 صباحاً",
                                    style = MaterialTheme.typography.bodyMedium.copy(
                                        color = Color(0xFFF1F5F9),
                                        fontSize = 13.sp
                                    )
                                )
                            }

                            Box(modifier = Modifier.fillMaxWidth().height(1.dp).background(Color(0x14FFFFFF)))

                            // Item 2: الأحداث الرياضية المهمة
                            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                                Text(
                                    text = "الأحداث الرياضية المهمة",
                                    style = MaterialTheme.typography.titleSmall.copy(
                                        fontWeight = FontWeight.Bold,
                                        color = Color(0xFFDDD6FE),
                                        fontSize = 14.sp
                                    )
                                )
                                Text(
                                    text = "على مدار 24 ساعة",
                                    style = MaterialTheme.typography.bodyMedium.copy(
                                        color = Color(0xFF34D399),
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 13.sp
                                    )
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(20.dp))
                }
            }
        }
    }
}
