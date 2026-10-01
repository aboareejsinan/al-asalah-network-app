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
import androidx.compose.material.icons.outlined.HourglassEmpty
import androidx.compose.material.icons.outlined.Paid
import androidx.compose.material.icons.outlined.ReceiptLong
import androidx.compose.material.icons.outlined.SwapHoriz
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
fun TransactionsScreen(
    planId: String = "plan_1_month",
    methodId: String = "kuraimi",
    onBackClick: () -> Unit = {},
    onTransactionClick: (TransactionRecord) -> Unit = {}
) {
    BackHandler {
        onBackClick()
    }

    // App identity petroleum blue vertical gradient
    val broadcastBackground = Brush.verticalGradient(
        colors = listOf(
            Color(0xFF1B3D4F), // Medium petroleum blue
            Color(0xFF174758), // Subtle muted teal
            Color(0xFF1A3B4D)  // Digital petroleum blue
        )
    )

    // Retrieve plan and payment method from existing flow data
    val plan = remember(planId) {
        RenewalPlanDataSource.plans.find { it.id == planId }
            ?: RenewalPlanDataSource.plans.first()
    }
    val method = remember(methodId) {
        PaymentMethodDataSource.paymentMethods.find { it.id == methodId }
            ?: PaymentMethodDataSource.paymentMethods.first()
    }

    // Local mock transaction record (temporary UI data only)
    val transaction = remember(plan, method) {
        TransactionRecord(
            requestNumber = "ASA-2026-000001",
            operationType = "تجديد اشتراك",
            smartCardNumber = "**** 4587",
            planId = plan.id,
            methodId = method.id,
            status = "قيد المراجعة",
            requestDate = "29 سبتمبر 2026"
        )
    }
    val mockRequestNumber = transaction.requestNumber

    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(broadcastBackground)
        ) {
            Scaffold(
                modifier = Modifier
                    .fillMaxSize()
                    .testTag("transactions_screen_scaffold"),
                containerColor = Color.Transparent,
                topBar = {
                    TopAppBar(
                        title = {
                            Text(
                                text = "سجل العمليات",
                                style = MaterialTheme.typography.titleLarge.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFFF8FAFC),
                                    fontSize = 20.sp
                                ),
                                modifier = Modifier.testTag("transactions_screen_title")
                            )
                        },
                        navigationIcon = {
                            IconButton(
                                onClick = onBackClick,
                                modifier = Modifier.testTag("transactions_back_button")
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
                        .testTag("transactions_content_column"),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Spacer(modifier = Modifier.height(4.dp))

                    // Section subtitle / indicator
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "العمليات الحالية",
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFFF8FAFC),
                                fontSize = 16.sp
                            ),
                            modifier = Modifier.testTag("transactions_section_title")
                        )

                        Surface(
                            color = Color(0x2938BDF8),
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Text(
                                text = "عملية واحدة",
                                style = MaterialTheme.typography.labelSmall.copy(
                                    color = Color(0xFF7DD3FC),
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 12.sp
                                ),
                                modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                            )
                        }
                    }

                    // ONE LOCAL MOCK OPERATION CARD
                    Card(
                        onClick = { onTransactionClick(transaction) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("transaction_card_$mockRequestNumber"),
                        shape = RoundedCornerShape(20.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color(0x381E3A8A)
                        ),
                        border = BorderStroke(1.2.dp, Color(0x4D38BDF8))
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(18.dp),
                            verticalArrangement = Arrangement.spacedBy(14.dp)
                        ) {
                            // Header Row: Request Number & Warm Amber Status Badge
                            Row(
                                modifier = Modifier.fillMaxWidth(),
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
                                        modifier = Modifier.testTag("transaction_request_number")
                                    )
                                }

                                // Status badge: "قيد المراجعة" with warm amber accent
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
                                            modifier = Modifier.testTag("transaction_status_badge")
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

                            // Row 1: Operation type & Smart card
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(12.dp)
                            ) {
                                TransactionDetailItem(
                                    icon = Icons.Outlined.Autorenew,
                                    iconTint = Color(0xFF34D399),
                                    label = "نوع العملية",
                                    value = "تجديد اشتراك",
                                    modifier = Modifier.weight(1f),
                                    tag = "transaction_type"
                                )
                                TransactionDetailItem(
                                    icon = Icons.Outlined.CreditCard,
                                    iconTint = Color(0xFF7DD3FC),
                                    label = "البطاقة الذكية",
                                    value = "**** 4587",
                                    modifier = Modifier.weight(1f),
                                    tag = "transaction_smart_card"
                                )
                            }

                            // Row 2: Duration & Amount
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(12.dp)
                            ) {
                                TransactionDetailItem(
                                    icon = Icons.Outlined.CalendarMonth,
                                    iconTint = Color(0xFF67E8F9),
                                    label = "مدة التجديد",
                                    value = plan.durationLabel,
                                    modifier = Modifier.weight(1f),
                                    tag = "transaction_duration"
                                )
                                TransactionDetailItem(
                                    icon = Icons.Outlined.Paid,
                                    iconTint = Color(0xFF38BDF8),
                                    label = "المبلغ المطلوب",
                                    value = RenewalPlanDataSource.formatPrice(plan.price, plan.currency),
                                    modifier = Modifier.weight(1f),
                                    valueColor = Color(0xFF38BDF8),
                                    isBoldAmount = true,
                                    tag = "transaction_amount"
                                )
                            }

                            // Row 3: Payment Provider
                            TransactionDetailItem(
                                icon = if (method.accountNumber.isNullOrBlank()) Icons.Outlined.SwapHoriz else Icons.Outlined.AccountBalance,
                                iconTint = Color(0xFFA5B4FC),
                                label = "وسيلة الدفع",
                                value = method.providerName,
                                modifier = Modifier.fillMaxWidth(),
                                tag = "transaction_payment_provider"
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }
}

@Composable
private fun TransactionDetailItem(
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
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Surface(
            shape = RoundedCornerShape(10.dp),
            color = iconTint.copy(alpha = 0.16f),
            modifier = Modifier.size(36.dp)
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
