package com.alasalah.digitalgateway

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HomeScreen(
    onNavigateToChannels: () -> Unit = {},
    onNavigateToSubscription: () -> Unit = {},
    onNavigateToRenewal: () -> Unit = {},
    onNavigateToTransactions: () -> Unit = {},
    onNavigateToNotifications: () -> Unit = {},
    onNavigateToAccount: () -> Unit = {},
    onNavigateToCoverage: () -> Unit = {},
    onNavigateToFrequencies: () -> Unit = {},
    onNavigateToReceptionGuide: () -> Unit = {},
    onNavigateToReceiverCardInfo: () -> Unit = {},
    onNavigateToSupport: () -> Unit = {},
    onNavigateToFaq: () -> Unit = {},
    onNavigateToMedia: () -> Unit = {}
) {
    val backgroundBrush = Brush.verticalGradient(
        colors = listOf(
            Color(0xFF1B3D4F),
            Color(0xFF174758),
            Color(0xFF1A3B4D)
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundBrush)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 20.dp, vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Header
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "شبكة الأصالة",
                    color = Color.White,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "البث الرقمي",
                    color = Color(0xFF9ECBD8),
                    fontSize = 13.sp
                )
            }
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                IconButton(
                    onClick = onNavigateToNotifications,
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(Color(0x26FFFFFF))
                ) {
                    Icon(
                        imageVector = Icons.Default.Notifications,
                        contentDescription = "الإشعارات",
                        tint = Color.White
                    )
                }
                IconButton(
                    onClick = onNavigateToAccount,
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(Color(0x26FFFFFF))
                ) {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "حسابي",
                        tint = Color.White
                    )
                }
            }
        }

        // Subscription Status Card
        Card(
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(containerColor = Color.Transparent),
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(Color(0xFF2D3B68), Color(0xFF38315E))
                    ),
                    shape = RoundedCornerShape(24.dp)
                )
        ) {
            Column(
                modifier = Modifier.padding(18.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(8.dp)
                                    .clip(CircleShape)
                                    .background(Color(0xFF34D399))
                            )
                            Text(
                                text = "اشتراكك فعال",
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                fontSize = 15.sp
                            )
                        }
                        Text(
                            text = "الباقة الأساسية",
                            color = Color(0xFFC7D2FE),
                            fontSize = 13.sp
                        )
                    }
                    Surface(
                        shape = RoundedCornerShape(10.dp),
                        color = Color(0x33FFFFFF)
                    ) {
                        Text(
                            text = "**** 4587",
                            color = Color.White,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)
                        )
                    }
                }

                HorizontalDivider(color = Color(0x26FFFFFF))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "ينتهي في",
                            color = Color(0xFFC7D2FE),
                            fontSize = 11.sp
                        )
                        Text(
                            text = "15 أكتوبر 2026",
                            color = Color.White,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = Color(0x3334D399)
                    ) {
                        Text(
                            text = "متبقي 16 يوم",
                            color = Color(0xFF6EE7B7),
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                        )
                    }
                }

                Button(
                    onClick = onNavigateToRenewal,
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0EA5E9))
                ) {
                    Text(
                        text = "تجديد الآن",
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }

        // Quick Services
        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
            Text(
                text = "الخدمات السريعة",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Surface(
                    shape = RoundedCornerShape(14.dp),
                    color = Color(0x1AFFFFFF),
                    modifier = Modifier
                        .weight(1f)
                        .clickable { onNavigateToSubscription() }
                ) {
                    Column(
                        modifier = Modifier.padding(12.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Icon(Icons.Default.CreditCard, contentDescription = null, tint = Color(0xFFA5B4FC))
                        Text(text = "اشتراكي", color = Color.White, fontSize = 12.sp)
                    }
                }
                Surface(
                    shape = RoundedCornerShape(14.dp),
                    color = Color(0x1AFFFFFF),
                    modifier = Modifier
                        .weight(1f)
                        .clickable { onNavigateToRenewal() }
                ) {
                    Column(
                        modifier = Modifier.padding(12.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Icon(Icons.Default.Refresh, contentDescription = null, tint = Color(0xFF6EE7B7))
                        Text(text = "التجديد", color = Color.White, fontSize = 12.sp)
                    }
                }
                Surface(
                    shape = RoundedCornerShape(14.dp),
                    color = Color(0x1AFFFFFF),
                    modifier = Modifier
                        .weight(1f)
                        .clickable { onNavigateToFrequencies() }
                ) {
                    Column(
                        modifier = Modifier.padding(12.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Icon(Icons.Default.Radio, contentDescription = null, tint = Color(0xFFFDE047))
                        Text(text = "الترددات", color = Color.White, fontSize = 12.sp)
                    }
                }
            }
        }

        // 1. ما الجديد في شبكة الأصالة
        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
            Text(
                text = "ما الجديد في شبكة الأصالة",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            Card(
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = Color.Transparent),
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        brush = Brush.horizontalGradient(
                            colors = listOf(Color(0xFF0F5B63), Color(0xFF146A72))
                        ),
                        shape = RoundedCornerShape(20.dp)
                    )
            ) {
                Column(
                    modifier = Modifier.padding(18.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Surface(
                            shape = RoundedCornerShape(6.dp),
                            color = Color(0x402DD4BF)
                        ) {
                            Text(
                                text = "جديد",
                                color = Color(0xFF99F6E4),
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                            )
                        }

                        Box(
                            modifier = Modifier
                                .size(36.dp)
                                .clip(CircleShape)
                                .background(Color(0x332DD4BF)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Campaign,
                                contentDescription = null,
                                tint = Color(0xFF5EEAD4),
                                modifier = Modifier.size(20.dp)
                            )
                        }
                    }

                    Text(
                        text = "تحديث جديد في خدمات الشبكة",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )

                    Text(
                        text = "تابع آخر الإضافات والتحديثات التي تقدمها شبكة الأصالة للمشتركين.",
                        fontSize = 13.sp,
                        color = Color(0xFFCCFBF1),
                        lineHeight = 20.sp
                    )

                    Row(
                        modifier = Modifier.clickable { onNavigateToMedia() },
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Text(
                            text = "عرض التفاصيل",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF5EEAD4)
                        )
                        Icon(
                            imageVector = Icons.Default.ChevronLeft,
                            contentDescription = null,
                            tint = Color(0xFF5EEAD4),
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }
            }
        }

        // 2. آخر الأخبار
        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "آخر الأخبار",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )

                TextButton(onClick = { onNavigateToMedia() }) {
                    Text(
                        text = "عرض الكل",
                        color = Color(0xFF38BDF8),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            // News Card
            Card(
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = Color.Transparent),
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        brush = Brush.horizontalGradient(
                            colors = listOf(Color(0xFF1E3A5F), Color(0xFF1A4971))
                        ),
                        shape = RoundedCornerShape(20.dp)
                    )
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Surface(
                            shape = RoundedCornerShape(6.dp),
                            color = Color(0x3338BDF8)
                        ) {
                            Text(
                                text = "أخبار الشبكة",
                                color = Color(0xFF7DD3FC),
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                            )
                        }

                        Box(
                            modifier = Modifier
                                .size(30.dp)
                                .clip(CircleShape)
                                .background(Color(0x26FFFFFF)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Newspaper,
                                contentDescription = null,
                                tint = Color(0xFF7DD3FC),
                                modifier = Modifier.size(16.dp)
                            )
                        }
                    }

                    Text(
                        text = "توسعة جديدة في خدمات شبكة الأصالة",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )

                    Text(
                        text = "تواصل الشبكة تطوير خدماتها وتحسين تجربة المشتركين.",
                        fontSize = 12.sp,
                        color = Color(0xFFBAE6FD),
                        lineHeight = 18.sp
                    )
                }
            }
        }

        // 3. الفعاليات القادمة
        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
            Text(
                text = "الفعاليات القادمة",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            Card(
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = Color.Transparent),
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        brush = Brush.horizontalGradient(
                            colors = listOf(Color(0xFF752B28), Color(0xFF8B3632))
                        ),
                        shape = RoundedCornerShape(20.dp)
                    )
            ) {
                Column(
                    modifier = Modifier.padding(18.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Surface(
                            shape = RoundedCornerShape(6.dp),
                            color = Color(0x40FB7185)
                        ) {
                            Text(
                                text = "فعالية",
                                color = Color(0xFFFECDD3),
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                            )
                        }

                        Surface(
                            shape = RoundedCornerShape(20.dp),
                            color = Color(0x40000000),
                            modifier = Modifier.clickable { onNavigateToMedia() }
                        ) {
                            Row(
                                modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(6.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Notifications,
                                    contentDescription = null,
                                    tint = Color(0xFFFDA4AF),
                                    modifier = Modifier.size(14.dp)
                                )
                                Text(
                                    text = "ذكّرني",
                                    color = Color(0xFFFFE4E6),
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }

                    Text(
                        text = "فعالية شبكة الأصالة",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Event,
                                contentDescription = null,
                                tint = Color(0xFFFDA4AF),
                                modifier = Modifier.size(15.dp)
                            )
                            Text(
                                text = "12 أكتوبر 2026",
                                fontSize = 12.sp,
                                color = Color(0xFFFFE4E6)
                            )
                        }

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.LocationOn,
                                contentDescription = null,
                                tint = Color(0xFFFDA4AF),
                                modifier = Modifier.size(15.dp)
                            )
                            Text(
                                text = "مأرب",
                                fontSize = 12.sp,
                                color = Color(0xFFFFE4E6)
                            )
                        }
                    }

                    Text(
                        text = "تابع آخر الفعاليات والأنشطة المرتبطة بشبكة الأصالة.",
                        fontSize = 13.sp,
                        color = Color(0xFFFECDD3),
                        lineHeight = 20.sp
                    )
                }
            }
        }

        // قنوات شبكة الأصالة
        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "قنوات شبكة الأصالة",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )

                TextButton(onClick = onNavigateToChannels) {
                    Text(
                        text = "عرض الكل",
                        color = Color(0xFF38BDF8),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                listOf("الأصالة 1", "الأصالة دراما", "الأصالة سبورت").forEach { channelName ->
                    Surface(
                        shape = RoundedCornerShape(16.dp),
                        color = Color(0x1AFFFFFF),
                        modifier = Modifier
                            .width(110.dp)
                            .clickable { onNavigateToChannels() }
                    ) {
                        Column(
                            modifier = Modifier.padding(12.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(44.dp)
                                    .clip(RoundedCornerShape(10.dp))
                                    .background(Color(0x330284C7)),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Tv,
                                    contentDescription = null,
                                    tint = Color(0xFF38BDF8)
                                )
                            }
                            Text(
                                text = channelName,
                                color = Color.White,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }
    }
}
