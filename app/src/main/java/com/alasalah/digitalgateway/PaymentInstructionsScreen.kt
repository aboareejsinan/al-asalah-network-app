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
import androidx.compose.material.icons.outlined.CardMembership
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material.icons.outlined.ContentCopy
import androidx.compose.material.icons.outlined.CreditCard
import androidx.compose.material.icons.outlined.Info
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
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaymentInstructionsScreen(
    planId: String = "plan_1_month",
    methodId: String = "kuraimi",
    onBackClick: () -> Unit = {},
    onNavigateToProof: (planId: String, methodId: String) -> Unit = { _, _ -> }
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

    // Dynamically retrieve selected plan and payment method from centralized data sources
    val plan = remember(planId) {
        RenewalPlanDataSource.plans.find { it.id == planId }
            ?: RenewalPlanDataSource.plans.first()
    }
    val method = remember(methodId) {
        PaymentMethodDataSource.paymentMethods.find { it.id == methodId }
            ?: PaymentMethodDataSource.paymentMethods.first()
    }

    val clipboardManager = LocalClipboardManager.current
    var isCopied by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(broadcastBackground)
        ) {
            Scaffold(
                modifier = Modifier
                    .fillMaxSize()
                    .testTag("payment_instructions_scaffold"),
                containerColor = Color.Transparent,
                snackbarHost = { SnackbarHost(snackbarHostState) },
                topBar = {
                    TopAppBar(
                        title = {
                            Text(
                                text = "إتمام الدفع",
                                style = MaterialTheme.typography.titleLarge.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFFF8FAFC),
                                    fontSize = 20.sp
                                ),
                                modifier = Modifier.testTag("payment_instructions_title")
                            )
                        },
                        navigationIcon = {
                            IconButton(
                                onClick = onBackClick,
                                modifier = Modifier.testTag("payment_instructions_back_button")
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
                },
                bottomBar = {
                    // Fixed primary action button: "إرسال إثبات الدفع" (does not open anything yet)
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
                                onClick = {
                                    onNavigateToProof(plan.id, method.id)
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(50.dp)
                                    .testTag("send_payment_proof_button"),
                                shape = RoundedCornerShape(14.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(0xFF2563EB),
                                    contentColor = Color.White
                                )
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.Outlined.ReceiptLong,
                                        contentDescription = null,
                                        modifier = Modifier.size(20.dp)
                                    )
                                    Text(
                                        text = "إرسال إثبات الدفع",
                                        style = MaterialTheme.typography.labelLarge.copy(
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 16.sp
                                        )
                                    )
                                }
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
                        .testTag("payment_instructions_content_column"),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Spacer(modifier = Modifier.height(2.dp))

                    // 1. Compact Payment Summary Card
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("instructions_summary_card"),
                        shape = RoundedCornerShape(18.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color(0x381E3A8A)
                        ),
                        border = BorderStroke(1.5.dp, Color(0x6638BDF8))
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            // Section header
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "تفاصيل عملية التجديد",
                                    style = MaterialTheme.typography.labelMedium.copy(
                                        color = Color(0xFF38BDF8),
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 13.sp
                                    )
                                )
                                Text(
                                    text = "طلب جديد",
                                    style = MaterialTheme.typography.labelSmall.copy(
                                        color = Color(0xFF94A3B8),
                                        fontSize = 11.sp
                                    )
                                )
                            }

                            // Package & Smart Card
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(12.dp)
                            ) {
                                InstructionSummaryItem(
                                    icon = Icons.Outlined.CardMembership,
                                    iconTint = Color(0xFFA5B4FC),
                                    label = "الباقة",
                                    value = "الباقة الأساسية",
                                    modifier = Modifier.weight(1f),
                                    tag = "instructions_package_name"
                                )
                                InstructionSummaryItem(
                                    icon = Icons.Outlined.CreditCard,
                                    iconTint = Color(0xFF7DD3FC),
                                    label = "البطاقة الذكية",
                                    value = "**** 4587",
                                    modifier = Modifier.weight(1f),
                                    tag = "instructions_smart_card"
                                )
                            }

                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(1.dp)
                                    .background(Color(0x24FFFFFF))
                            )

                            // Duration & Payment Provider
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(12.dp)
                            ) {
                                InstructionSummaryItem(
                                    icon = Icons.Outlined.CalendarMonth,
                                    iconTint = Color(0xFF67E8F9),
                                    label = "مدة التجديد",
                                    value = plan.durationLabel,
                                    modifier = Modifier.weight(1f),
                                    tag = "instructions_duration"
                                )
                                InstructionSummaryItem(
                                    icon = if (method.accountNumber.isNullOrBlank()) Icons.Outlined.SwapHoriz else Icons.Outlined.AccountBalance,
                                    iconTint = Color(0xFF34D399),
                                    label = "وسيلة الدفع",
                                    value = method.providerName,
                                    modifier = Modifier.weight(1f),
                                    tag = "instructions_provider_name"
                                )
                            }
                        }
                    }

                    // 2. Distinct Warm Teal / Blue-Green Payment Destination Card
                    val tealGradient = Brush.linearGradient(
                        colors = listOf(
                            Color(0xFF0F4C5C), // Warm deep teal
                            Color(0xFF13505B), // Blue-green
                            Color(0xFF0D3B47)  // Deep petroleum teal
                        )
                    )

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("payment_destination_card"),
                        shape = RoundedCornerShape(18.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color.Transparent
                        ),
                        border = BorderStroke(1.5.dp, Color(0xFF2DD4BF))
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(tealGradient)
                                .padding(18.dp),
                            verticalArrangement = Arrangement.spacedBy(14.dp)
                        ) {
                            // Amount Display (Visually Prominent)
                            Column(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                Text(
                                    text = "المبلغ المطلوب تحويله",
                                    style = MaterialTheme.typography.labelSmall.copy(
                                        color = Color(0xFF99F6E4),
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.Medium
                                    )
                                )
                                Text(
                                    text = RenewalPlanDataSource.formatPrice(plan.price, plan.currency),
                                    style = MaterialTheme.typography.headlineMedium.copy(
                                        fontWeight = FontWeight.ExtraBold,
                                        color = Color(0xFF5EEAD4), // Bright warm teal
                                        fontSize = 24.sp
                                    ),
                                    modifier = Modifier.testTag("instructions_amount_value")
                                )
                            }

                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(1.dp)
                                    .background(Color(0x332DD4BF))
                            )

                            // Payment Destination Details
                            if (!method.accountNumber.isNullOrBlank()) {
                                // Provider & Account Number with Copy Button
                                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Text(
                                            text = "جهة التحويل:",
                                            style = MaterialTheme.typography.labelMedium.copy(
                                                color = Color(0xFFCCFBF1),
                                                fontSize = 13.sp
                                            )
                                        )
                                        Text(
                                            text = method.providerName,
                                            style = MaterialTheme.typography.titleMedium.copy(
                                                fontWeight = FontWeight.Bold,
                                                color = Color(0xFFFFFFFF),
                                                fontSize = 15.sp
                                            ),
                                            modifier = Modifier.testTag("destination_provider_name")
                                        )
                                    }

                                    // Account Number Box with Copy Action
                                    Surface(
                                        shape = RoundedCornerShape(12.dp),
                                        color = Color(0x33000000),
                                        border = BorderStroke(1.dp, Color(0x4D2DD4BF)),
                                        modifier = Modifier.fillMaxWidth()
                                    ) {
                                        Row(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(horizontal = 14.dp, vertical = 10.dp),
                                            horizontalArrangement = Arrangement.SpaceBetween,
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
                                                Text(
                                                    text = "رقم الحساب",
                                                    style = MaterialTheme.typography.labelSmall.copy(
                                                        color = Color(0xFF99F6E4),
                                                        fontSize = 11.sp
                                                    )
                                                )
                                                Text(
                                                    text = method.accountNumber,
                                                    style = MaterialTheme.typography.titleMedium.copy(
                                                        fontWeight = FontWeight.Bold,
                                                        color = Color(0xFFFFFFFF),
                                                        fontSize = 16.sp,
                                                        letterSpacing = 1.sp
                                                    ),
                                                    modifier = Modifier.testTag("destination_account_number")
                                                )
                                            }

                                            // Copy Button labeled "نسخ الرقم"
                                            OutlinedButton(
                                                onClick = {
                                                    clipboardManager.setText(AnnotatedString(method.accountNumber))
                                                    isCopied = true
                                                    coroutineScope.launch {
                                                        delay(2000)
                                                        isCopied = false
                                                    }
                                                },
                                                shape = RoundedCornerShape(8.dp),
                                                colors = ButtonDefaults.outlinedButtonColors(
                                                    contentColor = Color(0xFF5EEAD4)
                                                ),
                                                border = BorderStroke(1.dp, Color(0xFF2DD4BF)),
                                                modifier = Modifier.testTag("copy_account_button")
                                            ) {
                                                Row(
                                                    verticalAlignment = Alignment.CenterVertically,
                                                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                                                ) {
                                                    Icon(
                                                        imageVector = if (isCopied) Icons.Outlined.Check else Icons.Outlined.ContentCopy,
                                                        contentDescription = null,
                                                        modifier = Modifier.size(16.dp),
                                                        tint = Color(0xFF5EEAD4)
                                                    )
                                                    Text(
                                                        text = if (isCopied) "تم النسخ" else "نسخ الرقم",
                                                        style = MaterialTheme.typography.labelSmall.copy(
                                                            fontWeight = FontWeight.Bold,
                                                            fontSize = 12.sp,
                                                            color = Color(0xFF5EEAD4)
                                                        )
                                                    )
                                                }
                                            }
                                        }
                                    }
                                }
                            } else {
                                // No fixed account number (e.g. Unified Transfer Network) -> Show note / instructions
                                Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Text(
                                            text = "جهة التحويل:",
                                            style = MaterialTheme.typography.labelMedium.copy(
                                                color = Color(0xFFCCFBF1),
                                                fontSize = 13.sp
                                            )
                                        )
                                        Text(
                                            text = method.providerName,
                                            style = MaterialTheme.typography.titleMedium.copy(
                                                fontWeight = FontWeight.Bold,
                                                color = Color(0xFFFFFFFF),
                                                fontSize = 15.sp
                                            ),
                                            modifier = Modifier.testTag("destination_provider_name")
                                        )
                                    }

                                    Surface(
                                        shape = RoundedCornerShape(12.dp),
                                        color = Color(0x33000000),
                                        border = BorderStroke(1.dp, Color(0x4D2DD4BF)),
                                        modifier = Modifier.fillMaxWidth()
                                    ) {
                                        Row(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(14.dp),
                                            horizontalArrangement = Arrangement.spacedBy(10.dp),
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            Icon(
                                                imageVector = Icons.Outlined.Info,
                                                contentDescription = null,
                                                tint = Color(0xFF5EEAD4),
                                                modifier = Modifier.size(20.dp)
                                            )
                                            Text(
                                                text = method.note ?: "إرسال قيمة الاشتراك عبر شبكة الحوالات الموحدة",
                                                style = MaterialTheme.typography.bodyMedium.copy(
                                                    color = Color(0xFFE2E8F0),
                                                    fontSize = 13.sp,
                                                    fontWeight = FontWeight.Medium
                                                ),
                                                modifier = Modifier.testTag("destination_method_note")
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }

                    // 3. Important Info Card: "بعد إتمام التحويل"
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("after_transfer_info_card"),
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color(0x2E1E3A8A)
                        ),
                        border = BorderStroke(1.dp, Color(0x3D38BDF8))
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            horizontalArrangement = Arrangement.spacedBy(12.dp),
                            verticalAlignment = Alignment.Top
                        ) {
                            Surface(
                                shape = CircleShape,
                                color = Color(0x3338BDF8),
                                modifier = Modifier.size(36.dp)
                            ) {
                                Box(
                                    contentAlignment = Alignment.Center,
                                    modifier = Modifier.fillMaxSize()
                                ) {
                                    Icon(
                                        imageVector = Icons.Outlined.Info,
                                        contentDescription = null,
                                        tint = Color(0xFF38BDF8),
                                        modifier = Modifier.size(20.dp)
                                    )
                                }
                            }

                            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                                Text(
                                    text = "بعد إتمام التحويل",
                                    style = MaterialTheme.typography.titleSmall.copy(
                                        fontWeight = FontWeight.Bold,
                                        color = Color(0xFFF1F5F9),
                                        fontSize = 14.sp
                                    ),
                                    modifier = Modifier.testTag("after_transfer_info_title")
                                )
                                Text(
                                    text = "احتفظ بإيصال أو رقم عملية التحويل لإكمال طلب التجديد.",
                                    style = MaterialTheme.typography.bodySmall.copy(
                                        color = Color(0xFF94A3B8),
                                        fontSize = 12.sp,
                                        lineHeight = 18.sp
                                    ),
                                    modifier = Modifier.testTag("after_transfer_info_text")
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
private fun InstructionSummaryItem(
    icon: ImageVector,
    iconTint: Color,
    label: String,
    value: String,
    modifier: Modifier = Modifier,
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
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFF1F5F9),
                    fontSize = 13.sp
                ),
                maxLines = 1,
                modifier = if (tag != null) Modifier.testTag(tag) else Modifier
            )
        }
    }
}
