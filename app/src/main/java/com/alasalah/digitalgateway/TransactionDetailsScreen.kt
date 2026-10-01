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
import androidx.compose.material.icons.outlined.AccountBalance
import androidx.compose.material.icons.outlined.Autorenew
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.CreditCard
import androidx.compose.material.icons.outlined.Event
import androidx.compose.material.icons.outlined.HourglassEmpty
import androidx.compose.material.icons.outlined.Paid
import androidx.compose.material.icons.outlined.SwapHoriz
import androidx.compose.material.icons.outlined.Tag
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
fun TransactionDetailsScreen(
    transaction: TransactionRecord = TransactionRecord(),
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

    // Read renewal plan and payment method from existing data sources using the record's IDs
    val plan = remember(transaction.planId) {
        RenewalPlanDataSource.plans.find { it.id == transaction.planId }
            ?: RenewalPlanDataSource.plans.first()
    }
    val method = remember(transaction.methodId) {
        PaymentMethodDataSource.paymentMethods.find { it.id == transaction.methodId }
            ?: PaymentMethodDataSource.paymentMethods.first()
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
                    .testTag("transaction_details_scaffold"),
                containerColor = Color.Transparent,
                topBar = {
                    TopAppBar(
                        title = {
                            Text(
                                text = "تفاصيل العملية",
                                style = MaterialTheme.typography.titleLarge.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFFF8FAFC),
                                    fontSize = 20.sp
                                ),
                                modifier = Modifier.testTag("transaction_details_title")
                            )
                        },
                        navigationIcon = {
                            IconButton(
                                onClick = onBackClick,
                                modifier = Modifier.testTag("transaction_details_back_button")
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
                        .testTag("transaction_details_content"),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Spacer(modifier = Modifier.height(4.dp))

                    // Prominent Header Card with Request Number and Status
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("transaction_details_header_card"),
                        shape = RoundedCornerShape(22.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color(0x381E3A8A)
                        ),
                        border = BorderStroke(1.5.dp, Color(0x6638BDF8))
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(20.dp),
                            verticalArrangement = Arrangement.spacedBy(16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            // Status badge with warm amber accent
                            Surface(
                                shape = RoundedCornerShape(10.dp),
                                color = Color(0x33F59E0B),
                                border = BorderStroke(1.2.dp, Color(0xFFF59E0B))
                            ) {
                                Row(
                                    modifier = Modifier.padding(horizontal = 14.dp, vertical = 7.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.Outlined.HourglassEmpty,
                                        contentDescription = null,
                                        tint = Color(0xFFFBBF24),
                                        modifier = Modifier.size(16.dp)
                                    )
                                    Text(
                                        text = transaction.status,
                                        style = MaterialTheme.typography.labelLarge.copy(
                                            color = Color(0xFFFBBF24),
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 14.sp
                                        ),
                                        modifier = Modifier.testTag("transaction_details_status")
                                    )
                                }
                            }

                            // Prominent Request Number
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.Outlined.Tag,
                                        contentDescription = null,
                                        tint = Color(0xFF94A3B8),
                                        modifier = Modifier.size(14.dp)
                                    )
                                    Text(
                                        text = "رقم الطلب",
                                        style = MaterialTheme.typography.labelMedium.copy(
                                            color = Color(0xFF94A3B8),
                                            fontSize = 13.sp
                                        )
                                    )
                                }
                                Text(
                                    text = transaction.requestNumber,
                                    style = MaterialTheme.typography.headlineMedium.copy(
                                        fontWeight = FontWeight.ExtraBold,
                                        color = Color(0xFF38BDF8),
                                        letterSpacing = 2.sp
                                    ),
                                    modifier = Modifier.testTag("transaction_details_request_number")
                                )
                            }
                        }
                    }

                    // Detailed Operation Info Card
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("transaction_details_info_card"),
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
                            verticalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            Text(
                                text = "بيانات العملية",
                                style = MaterialTheme.typography.titleMedium.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFFF8FAFC),
                                    fontSize = 16.sp
                                )
                            )

                            // Detail 1: Operation type
                            DetailRow(
                                icon = Icons.Outlined.Autorenew,
                                iconTint = Color(0xFF34D399),
                                label = "نوع العملية",
                                value = transaction.operationType,
                                tag = "transaction_details_operation_type"
                            )

                            DetailDivider()

                            // Detail 2: Smart Card
                            DetailRow(
                                icon = Icons.Outlined.CreditCard,
                                iconTint = Color(0xFF7DD3FC),
                                label = "البطاقة الذكية",
                                value = transaction.smartCardNumber,
                                tag = "transaction_details_smart_card"
                            )

                            DetailDivider()

                            // Detail 3: Renewal Duration
                            DetailRow(
                                icon = Icons.Outlined.CalendarMonth,
                                iconTint = Color(0xFF67E8F9),
                                label = "مدة التجديد",
                                value = plan.durationLabel,
                                tag = "transaction_details_duration"
                            )

                            DetailDivider()

                            // Detail 4: Amount
                            DetailRow(
                                icon = Icons.Outlined.Paid,
                                iconTint = Color(0xFF38BDF8),
                                label = "المبلغ المطلوب",
                                value = RenewalPlanDataSource.formatPrice(plan.price, plan.currency),
                                isAccent = true,
                                tag = "transaction_details_amount"
                            )

                            DetailDivider()

                            // Detail 5: Payment Provider
                            DetailRow(
                                icon = if (method.accountNumber.isNullOrBlank()) Icons.Outlined.SwapHoriz else Icons.Outlined.AccountBalance,
                                iconTint = Color(0xFFA5B4FC),
                                label = "وسيلة الدفع",
                                value = method.providerName,
                                tag = "transaction_details_provider"
                            )

                            DetailDivider()

                            // Detail 6: Request Date
                            DetailRow(
                                icon = Icons.Outlined.Event,
                                iconTint = Color(0xFFFBBF24),
                                label = "تاريخ الطلب",
                                value = transaction.requestDate,
                                tag = "transaction_details_date"
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
private fun DetailRow(
    icon: ImageVector,
    iconTint: Color,
    label: String,
    value: String,
    isAccent: Boolean = false,
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
            Text(
                text = label,
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = Color(0xFF94A3B8),
                    fontSize = 13.sp
                )
            )
        }
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium.copy(
                fontWeight = if (isAccent) FontWeight.ExtraBold else FontWeight.Bold,
                color = if (isAccent) Color(0xFF38BDF8) else Color(0xFFF1F5F9),
                fontSize = if (isAccent) 15.sp else 14.sp
            ),
            modifier = Modifier.testTag(tag)
        )
    }
}

@Composable
private fun DetailDivider() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
            .background(Color(0x1AFFFFFF))
    )
}
