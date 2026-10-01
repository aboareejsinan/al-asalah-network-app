package com.alasalah.digitalgateway

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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.outlined.AccountBalance
import androidx.compose.material.icons.outlined.AccountBalanceWallet
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.CardMembership
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.CreditCard
import androidx.compose.material.icons.outlined.LocalAtm
import androidx.compose.material.icons.outlined.Paid
import androidx.compose.material.icons.outlined.Payments
import androidx.compose.material.icons.outlined.RadioButtonUnchecked
import androidx.compose.material.icons.outlined.Savings
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
fun PaymentMethodScreen(
    planId: String = "plan_1_month",
    onBackClick: () -> Unit = {},
    onNavigateToInstructions: (planId: String, methodId: String) -> Unit = { _, _ -> }
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

    // Lookup selected renewal plan details from centralized source
    val selectedPlan = remember(planId) {
        RenewalPlanDataSource.plans.find { it.id == planId }
            ?: RenewalPlanDataSource.plans.first()
    }

    // Read payment methods from centralized data source
    val paymentMethods = remember { PaymentMethodDataSource.getEnabledPaymentMethods() }
    var selectedMethodId by remember { mutableStateOf(paymentMethods.firstOrNull()?.id ?: "") }

    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(broadcastBackground)
        ) {
            Scaffold(
                modifier = Modifier
                    .fillMaxSize()
                    .testTag("payment_method_screen_scaffold"),
                containerColor = Color.Transparent,
                topBar = {
                    TopAppBar(
                        title = {
                            Text(
                                text = "اختيار وسيلة الدفع",
                                style = MaterialTheme.typography.titleLarge.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFFF8FAFC),
                                    fontSize = 20.sp
                                ),
                                modifier = Modifier.testTag("payment_method_screen_title")
                            )
                        },
                        navigationIcon = {
                            IconButton(
                                onClick = onBackClick,
                                modifier = Modifier.testTag("payment_method_back_button")
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
                    // Fixed primary action button: "متابعة الدفع"
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
                                    onNavigateToInstructions(selectedPlan.id, selectedMethodId)
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(50.dp)
                                    .testTag("payment_method_continue_button"),
                                shape = RoundedCornerShape(14.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(0xFF2563EB),
                                    contentColor = Color.White
                                )
                            ) {
                                Text(
                                    text = "متابعة الدفع",
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
                        .testTag("payment_method_content_column"),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Spacer(modifier = Modifier.height(2.dp))

                    // Compact Summary Card of the Selected Renewal Plan
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("payment_method_plan_summary_card"),
                        shape = RoundedCornerShape(18.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color(0x381E3A8A) // Blue-indigo container
                        ),
                        border = BorderStroke(1.5.dp, Color(0x6638BDF8)) // Cyan accent border
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            verticalArrangement = Arrangement.spacedBy(14.dp)
                        ) {
                            // Header badge: "ملخص التجديد"
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                                ) {
                                    Surface(
                                        shape = CircleShape,
                                        color = Color(0xFF38BDF8),
                                        modifier = Modifier.size(7.dp)
                                    ) {}
                                    Text(
                                        text = "ملخص التجديد",
                                        style = MaterialTheme.typography.labelMedium.copy(
                                            color = Color(0xFF38BDF8),
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 12.sp
                                        )
                                    )
                                }

                                Surface(
                                    shape = RoundedCornerShape(6.dp),
                                    color = Color(0x2638BDF8),
                                    border = BorderStroke(1.dp, Color(0x4038BDF8))
                                ) {
                                    Text(
                                        text = "جاهز للدفع",
                                        style = MaterialTheme.typography.labelSmall.copy(
                                            color = Color(0xFF7DD3FC),
                                            fontWeight = FontWeight.Medium,
                                            fontSize = 10.sp
                                        ),
                                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                    )
                                }
                            }

                            // 2x2 grid of details: Row 1 (Package & Smart Card)
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(12.dp)
                            ) {
                                SummaryDetailItem(
                                    icon = Icons.Outlined.CardMembership,
                                    iconTint = Color(0xFFA5B4FC), // Soft indigo
                                    label = "الباقة",
                                    value = "الباقة الأساسية",
                                    modifier = Modifier.weight(1f),
                                    tag = "summary_package_name"
                                )

                                SummaryDetailItem(
                                    icon = Icons.Outlined.CreditCard,
                                    iconTint = Color(0xFF7DD3FC), // Sky blue
                                    label = "البطاقة الذكية",
                                    value = "**** 4587",
                                    modifier = Modifier.weight(1f),
                                    tag = "summary_smart_card"
                                )
                            }

                            // Divider
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(1.dp)
                                    .background(Color(0x24FFFFFF))
                            )

                            // Row 2 (Renewal Duration & Amount)
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(12.dp)
                            ) {
                                SummaryDetailItem(
                                    icon = Icons.Outlined.CalendarMonth,
                                    iconTint = Color(0xFF67E8F9), // Cyan
                                    label = "مدة التجديد",
                                    value = selectedPlan.durationLabel,
                                    modifier = Modifier.weight(1f),
                                    tag = "summary_plan_duration"
                                )

                                SummaryDetailItem(
                                    icon = Icons.Outlined.Paid,
                                    iconTint = Color(0xFF38BDF8), // Bright cyan
                                    label = "المبلغ المطلوب",
                                    value = RenewalPlanDataSource.formatPrice(selectedPlan.price, selectedPlan.currency),
                                    modifier = Modifier.weight(1f),
                                    valueColor = Color(0xFF38BDF8),
                                    tag = "summary_plan_price"
                                )
                            }
                        }
                    }

                    // Section Heading
                    Text(
                        text = "اختر وسيلة الدفع المناسبة",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFFF8FAFC)
                        ),
                        modifier = Modifier.testTag("payment_methods_section_title")
                    )

                    // Enabled Payment Methods List
                    Column(
                        verticalArrangement = Arrangement.spacedBy(10.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        paymentMethods.forEach { method ->
                            val isSelected = method.id == selectedMethodId
                            PaymentMethodCard(
                                method = method,
                                isSelected = isSelected,
                                onClick = { selectedMethodId = method.id }
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
private fun PaymentMethodCard(
    method: PaymentMethod,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    // Tasteful varied accent colors for each payment provider
    val accentColor = when (method.id) {
        "kuraimi" -> Color(0xFF10B981) // Emerald / Teal for Kuraimi
        "alsharq" -> Color(0xFF38BDF8) // Sky blue for Al Sharq
        "qutaibi" -> Color(0xFF818CF8) // Indigo for Qutaibi
        "alinma" -> Color(0xFFF59E0B) // Amber for Alinma
        "alsalam" -> Color(0xFFA78BFA) // Violet for Alsalam
        "al_doha" -> Color(0xFFFB7185) // Rose for Al Doha
        "qutaibi_shilling" -> Color(0xFF22D3EE) // Cyan for Qutaibi Shilling
        "unified_transfer_network" -> Color(0xFFFB923C) // Orange for Unified Network
        else -> Color(0xFF38BDF8)
    }

    // Expressive Material icon for each payment provider
    val methodIcon: ImageVector = when (method.id) {
        "kuraimi" -> Icons.Outlined.AccountBalance
        "alsharq" -> Icons.Outlined.AccountBalanceWallet
        "qutaibi" -> Icons.Outlined.Savings
        "alinma" -> Icons.Outlined.Paid
        "alsalam" -> Icons.Outlined.CreditCard
        "al_doha" -> Icons.Outlined.Payments
        "qutaibi_shilling" -> Icons.Outlined.LocalAtm
        "unified_transfer_network" -> Icons.Outlined.SwapHoriz
        else -> Icons.Outlined.AccountBalance
    }

    val cardBorder = if (isSelected) {
        BorderStroke(2.dp, accentColor)
    } else {
        BorderStroke(1.dp, Color(0x29FFFFFF))
    }

    val containerGradient = if (isSelected) {
        listOf(
            accentColor.copy(alpha = 0.22f),
            Color(0xFF162E3F)
        )
    } else {
        listOf(
            Color(0x24FFFFFF),
            Color(0x14FFFFFF)
        )
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .testTag("payment_method_card_${method.id}"),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        ),
        border = cardBorder
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Brush.linearGradient(containerGradient))
                .padding(horizontal = 16.dp, vertical = 14.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Right (RTL start): Radio indicator + Expressive Provider Icon + Provider Name & Details
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.weight(1f)
            ) {
                // Radio indicator
                Icon(
                    imageVector = if (isSelected) Icons.Outlined.CheckCircle else Icons.Outlined.RadioButtonUnchecked,
                    contentDescription = null,
                    tint = if (isSelected) accentColor else Color(0xFF94A3B8),
                    modifier = Modifier.size(22.dp)
                )

                // Expressive Provider Icon
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = accentColor.copy(alpha = 0.18f),
                    border = BorderStroke(1.dp, accentColor.copy(alpha = 0.35f)),
                    modifier = Modifier.size(44.dp)
                ) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Icon(
                            imageVector = methodIcon,
                            contentDescription = method.providerName,
                            tint = accentColor,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }

                // Provider Name, Account Number, and Note
                Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
                    Text(
                        text = method.providerName,
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.SemiBold,
                            color = Color(0xFFF8FAFC),
                            fontSize = 15.sp
                        ),
                        modifier = Modifier.testTag("provider_name_${method.id}")
                    )

                    // Account Number (where available) or Note
                    if (!method.accountNumber.isNullOrBlank()) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Text(
                                text = "رقم الحساب:",
                                style = MaterialTheme.typography.labelSmall.copy(
                                    color = Color(0xFF94A3B8),
                                    fontSize = 11.sp
                                )
                            )
                            Text(
                                text = method.accountNumber,
                                style = MaterialTheme.typography.bodySmall.copy(
                                    fontWeight = FontWeight.Medium,
                                    color = if (isSelected) accentColor else Color(0xFFE2E8F0),
                                    fontSize = 12.sp
                                ),
                                modifier = Modifier.testTag("account_number_${method.id}")
                            )
                        }
                    } else if (!method.note.isNullOrBlank()) {
                        Text(
                            text = method.note,
                            style = MaterialTheme.typography.bodySmall.copy(
                                color = Color(0xFFCBD5E1),
                                fontSize = 11.sp
                            ),
                            modifier = Modifier.testTag("method_note_${method.id}")
                        )
                    }
                }
            }

            // Left (RTL end): Short payment type badge
            Surface(
                shape = RoundedCornerShape(8.dp),
                color = accentColor.copy(alpha = 0.18f),
                border = BorderStroke(1.dp, accentColor.copy(alpha = 0.3f))
            ) {
                Text(
                    text = method.methodType,
                    style = MaterialTheme.typography.labelSmall.copy(
                        color = accentColor,
                        fontWeight = FontWeight.Bold,
                        fontSize = 11.sp
                    ),
                    modifier = Modifier
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                        .testTag("method_type_${method.id}")
                )
            }
        }
    }
}

@Composable
private fun SummaryDetailItem(
    icon: ImageVector,
    iconTint: Color,
    label: String,
    value: String,
    modifier: Modifier = Modifier,
    valueColor: Color = Color(0xFFF1F5F9),
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
                    color = valueColor,
                    fontSize = 13.sp
                ),
                maxLines = 1,
                modifier = if (tag != null) Modifier.testTag(tag) else Modifier
            )
        }
    }
}
