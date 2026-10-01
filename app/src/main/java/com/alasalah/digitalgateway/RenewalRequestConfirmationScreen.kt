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
import androidx.compose.material.icons.outlined.AccountBalance
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.CreditCard
import androidx.compose.material.icons.outlined.HourglassEmpty
import androidx.compose.material.icons.outlined.Paid
import androidx.compose.material.icons.outlined.ReceiptLong
import androidx.compose.material.icons.outlined.SwapHoriz
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RenewalRequestConfirmationScreen(
    planId: String = "plan_1_month",
    methodId: String = "kuraimi",
    onReturnHome: () -> Unit = {}
) {
    BackHandler {
        onReturnHome()
    }

    // App identity petroleum blue vertical gradient
    val broadcastBackground = Brush.verticalGradient(
        colors = listOf(
            Color(0xFF1B3D4F), // Medium petroleum blue
            Color(0xFF174758), // Subtle muted teal
            Color(0xFF1A3B4D)  // Digital petroleum blue
        )
    )

    // Dynamically retrieve selected plan and payment method from centralized data sources
    val plan = remember(planId) {
        RenewalPlanDataSource.plans.find { it.id == planId }
            ?: RenewalPlanDataSource.plans.first()
    }
    val method = remember(methodId) {
        PaymentMethodDataSource.paymentMethods.find { it.id == methodId }
            ?: PaymentMethodDataSource.paymentMethods.first()
    }

    // Local temporary mock request number (generated locally for UI flow)
    val mockRequestNumber = remember { "ASA-2026-000001" }

    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(broadcastBackground)
        ) {
            Scaffold(
                modifier = Modifier
                    .fillMaxSize()
                    .testTag("confirmation_screen_scaffold"),
                containerColor = Color.Transparent,
                topBar = {
                    TopAppBar(
                        title = {
                            Text(
                                text = "تم استلام الطلب",
                                style = MaterialTheme.typography.titleLarge.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFFF8FAFC),
                                    fontSize = 20.sp
                                ),
                                modifier = Modifier.testTag("confirmation_screen_title")
                            )
                        },
                        navigationIcon = {
                            IconButton(
                                onClick = onReturnHome,
                                modifier = Modifier.testTag("confirmation_back_button")
                            ) {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Outlined.ArrowBack,
                                    contentDescription = "العودة إلى الرئيسية",
                                    tint = Color(0xFFF8FAFC)
                                )
                            }
                        },
                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = Color.Transparent
                        )
                    )
                },
                bottomBar = {
                    // Primary action button: "العودة إلى الرئيسية"
                    Surface(
                        color = Color(0xFF102636),
                        border = BorderStroke(1.dp, Color(0x2638BDF8)),
                        tonalElevation = 8.dp
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 20.dp, vertical = 14.dp)
                        ) {
                            Button(
                                onClick = onReturnHome,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(50.dp)
                                    .testTag("confirmation_return_home_button"),
                                shape = RoundedCornerShape(14.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(0xFF2563EB),
                                    contentColor = Color.White
                                )
                            ) {
                                Text(
                                    text = "العودة إلى الرئيسية",
                                    style = MaterialTheme.typography.labelLarge.copy(
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 16.sp
                                    )
                                )
                            }
                        }
                    }
                }
            ) { innerPadding ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                        .padding(horizontal = 20.dp)
                        .verticalScroll(rememberScrollState())
                        .testTag("confirmation_content_column"),
                    verticalArrangement = Arrangement.spacedBy(18.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.height(10.dp))

                    // 1. Success Icon and Header Messages (Positive Green / Teal)
                    val successCircleGradient = Brush.radialGradient(
                        colors = listOf(
                            Color(0x3310B981), // Emerald glow
                            Color(0x1A059669),
                            Color(0x00047857)
                        )
                    )

                    Surface(
                        shape = CircleShape,
                        color = Color(0x1F10B981),
                        border = BorderStroke(2.dp, Color(0xFF34D399)),
                        modifier = Modifier.size(92.dp)
                    ) {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .fillMaxSize()
                                .background(successCircleGradient)
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.CheckCircle,
                                contentDescription = null,
                                tint = Color(0xFF34D399),
                                modifier = Modifier
                                    .size(52.dp)
                                    .testTag("confirmation_success_icon")
                            )
                        }
                    }

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Text(
                            text = "تم استلام طلب التجديد",
                            style = MaterialTheme.typography.headlineSmall.copy(
                                fontWeight = FontWeight.ExtraBold,
                                color = Color(0xFFF8FAFC),
                                fontSize = 22.sp
                            ),
                            modifier = Modifier.testTag("confirmation_main_message")
                        )

                        Text(
                            text = "سيتم التحقق من بيانات الدفع قبل تنفيذ تجديد الاشتراك.",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                color = Color(0xFF94A3B8),
                                fontSize = 13.sp,
                                lineHeight = 19.sp
                            ),
                            modifier = Modifier
                                .padding(horizontal = 12.dp)
                                .testTag("confirmation_secondary_message")
                        )
                    }

                    // 2. Request Identifier and Status Card
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color(0x2E1E3A8A)
                        ),
                        border = BorderStroke(1.5.dp, Color(0x4D38BDF8))
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 18.dp, vertical = 14.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
                                Text(
                                    text = "رقم الطلب",
                                    style = MaterialTheme.typography.labelSmall.copy(
                                        color = Color(0xFF94A3B8),
                                        fontSize = 11.sp
                                    )
                                )
                                Text(
                                    text = mockRequestNumber,
                                    style = MaterialTheme.typography.titleMedium.copy(
                                        fontWeight = FontWeight.Bold,
                                        color = Color(0xFF38BDF8),
                                        fontSize = 16.sp,
                                        letterSpacing = 1.sp
                                    ),
                                    modifier = Modifier.testTag("confirmation_request_number")
                                )
                            }

                            // Status badge: "قيد المراجعة"
                            Surface(
                                shape = RoundedCornerShape(8.dp),
                                color = Color(0x33F59E0B),
                                border = BorderStroke(1.dp, Color(0xFFF59E0B))
                            ) {
                                Row(
                                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.Outlined.HourglassEmpty,
                                        contentDescription = null,
                                        tint = Color(0xFFFBBF24),
                                        modifier = Modifier.size(14.dp)
                                    )
                                    Text(
                                        text = "قيد المراجعة",
                                        style = MaterialTheme.typography.labelMedium.copy(
                                            color = Color(0xFFFBBF24),
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 12.sp
                                        ),
                                        modifier = Modifier.testTag("confirmation_status_badge")
                                    )
                                }
                            }
                        }
                    }

                    // 3. Compact Request Summary Card
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("confirmation_summary_card"),
                        shape = RoundedCornerShape(18.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color(0x381E3A8A)
                        ),
                        border = BorderStroke(1.dp, Color(0x4D38BDF8))
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            Text(
                                text = "تفاصيل الطلب",
                                style = MaterialTheme.typography.labelMedium.copy(
                                    color = Color(0xFF38BDF8),
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 13.sp
                                )
                            )

                            // Row 1: Smart Card & Renewal duration
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(12.dp)
                            ) {
                                ConfirmationSummaryDetailItem(
                                    icon = Icons.Outlined.CreditCard,
                                    iconTint = Color(0xFF7DD3FC),
                                    label = "البطاقة الذكية",
                                    value = "**** 4587",
                                    modifier = Modifier.weight(1f),
                                    tag = "confirmation_smart_card"
                                )
                                ConfirmationSummaryDetailItem(
                                    icon = Icons.Outlined.CalendarMonth,
                                    iconTint = Color(0xFF67E8F9),
                                    label = "مدة التجديد",
                                    value = plan.durationLabel,
                                    modifier = Modifier.weight(1f),
                                    tag = "confirmation_duration"
                                )
                            }

                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(1.dp)
                                    .background(Color(0x24FFFFFF))
                            )

                            // Row 2: Payment Provider & Required Amount
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(12.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                ConfirmationSummaryDetailItem(
                                    icon = if (method.accountNumber.isNullOrBlank()) Icons.Outlined.SwapHoriz else Icons.Outlined.AccountBalance,
                                    iconTint = Color(0xFF34D399),
                                    label = "وسيلة الدفع",
                                    value = method.providerName,
                                    modifier = Modifier.weight(1f),
                                    tag = "confirmation_provider"
                                )
                                ConfirmationSummaryDetailItem(
                                    icon = Icons.Outlined.Paid,
                                    iconTint = Color(0xFF38BDF8),
                                    label = "المبلغ المطلوب",
                                    value = RenewalPlanDataSource.formatPrice(plan.price, plan.currency),
                                    modifier = Modifier.weight(1f),
                                    valueColor = Color(0xFF38BDF8),
                                    isBoldAmount = true,
                                    tag = "confirmation_amount"
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }
}

@Composable
private fun ConfirmationSummaryDetailItem(
    icon: ImageVector,
    iconTint: Color,
    label: String,
    value: String,
    modifier: Modifier = Modifier,
    valueColor: Color = Color(0xFFF1F5F9),
    isBoldAmount: Boolean = false,
    tag: String? = null
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
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
                    contentDescription = label,
                    tint = iconTint,
                    modifier = Modifier.size(18.dp)
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
                    fontWeight = if (isBoldAmount) FontWeight.ExtraBold else FontWeight.Bold,
                    color = valueColor,
                    fontSize = if (isBoldAmount) 14.sp else 13.sp
                ),
                maxLines = 1,
                modifier = if (tag != null) Modifier.testTag(tag) else Modifier
            )
        }
    }
}
