package com.alasalah.digitalgateway

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material.icons.filled.Headset
import androidx.compose.material.icons.filled.Public
import androidx.compose.material.icons.filled.Radio
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Wallet
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ServicesScreen(
    onMySubscription: () -> Unit,
    onRenewal: () -> Unit,
    onTransactions: () -> Unit,
    onFrequencies: () -> Unit,
    onCoverage: () -> Unit,
    onSupport: () -> Unit,
    onBack: () -> Unit
) {
    val backgroundBrush = Brush.verticalGradient(
        colors = listOf(
            Color(0xFF1B3D4F),
            Color(0xFF174758),
            Color(0xFF1A3B4D)
        )
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "الخدمات",
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "رجوع",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF1B3D4F)
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(backgroundBrush)
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp, vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            // 1. اشتراكي
            ServiceItemCard(
                title = "اشتراكي",
                icon = Icons.Default.CreditCard,
                iconTint = Color(0xFFA5B4FC),
                iconBgColor = Color(0x336366F1),
                onClick = onMySubscription
            )

            // 2. التجديد
            ServiceItemCard(
                title = "التجديد",
                icon = Icons.Default.Refresh,
                iconTint = Color(0xFF6EE7B7),
                iconBgColor = Color(0x3310B981),
                onClick = onRenewal
            )

            // 3. الدفع
            ServiceItemCard(
                title = "الدفع",
                icon = Icons.Default.Wallet,
                iconTint = Color(0xFF7DD3FC),
                iconBgColor = Color(0x330284C7),
                onClick = onTransactions
            )

            // 4. الترددات
            ServiceItemCard(
                title = "الترددات",
                icon = Icons.Default.Radio,
                iconTint = Color(0xFFFDE047),
                iconBgColor = Color(0x33D97706),
                onClick = onFrequencies
            )

            // 5. التغطية
            ServiceItemCard(
                title = "التغطية",
                icon = Icons.Default.Public,
                iconTint = Color(0xFFFDA4AF),
                iconBgColor = Color(0x33E11D48),
                onClick = onCoverage
            )

            // 6. الدعم
            ServiceItemCard(
                title = "الدعم",
                icon = Icons.Default.Headset,
                iconTint = Color(0xFF93C5FD),
                iconBgColor = Color(0x332563EB),
                onClick = onSupport
            )
        }
    }
}

@Composable
private fun ServiceItemCard(
    title: String,
    icon: ImageVector,
    iconTint: Color,
    iconBgColor: Color,
    onClick: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent),
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .background(
                color = Color(0x26FFFFFF),
                shape = RoundedCornerShape(20.dp)
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(iconBgColor),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = iconTint,
                    modifier = Modifier.size(24.dp)
                )
            }

            Text(
                text = title,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
    }
}
