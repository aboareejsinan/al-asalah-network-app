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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.outlined.AutoAwesome
import androidx.compose.material.icons.outlined.Bolt
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.CardMembership
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.CreditCard
import androidx.compose.material.icons.outlined.Diamond
import androidx.compose.material.icons.outlined.RadioButtonUnchecked
import androidx.compose.material.icons.outlined.Stars
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
fun RenewalScreen(
    onBackClick: () -> Unit = {},
    onNavigateToPaymentMethod: (planId: String) -> Unit = {}
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

    // Read enabled plans from centralized data source
    val plans = remember { RenewalPlanDataSource.getEnabledPlans() }
    var selectedPlanId by remember { mutableStateOf(plans.firstOrNull()?.id ?: "") }

    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(broadcastBackground)
        ) {
            Scaffold(
                modifier = Modifier
                    .fillMaxSize()
                    .testTag("renewal_screen_scaffold"),
                containerColor = Color.Transparent,
                topBar = {
                    TopAppBar(
                        title = {
                            Text(
                                text = "تجديد الاشتراك",
                                style = MaterialTheme.typography.titleLarge.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFFF8FAFC),
                                    fontSize = 20.sp
                                ),
                                modifier = Modifier.testTag("renewal_screen_title")
                            )
                        },
                        navigationIcon = {
                            IconButton(
                                onClick = onBackClick,
                                modifier = Modifier.testTag("renewal_back_button")
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
                        ),
                        modifier = Modifier.testTag("renewal_top_bar")
                    )
                },
                bottomBar = {
                    // Fixed primary action button: "متابعة"
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
                                    onNavigateToPaymentMethod(selectedPlanId)
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(50.dp)
                                    .testTag("renewal_continue_button"),
                                shape = RoundedCornerShape(14.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(0xFF2563EB),
                                    contentColor = Color.White
                                )
                            ) {
                                Text(
                                    text = "متابعة",
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
                        .testTag("renewal_content_column"),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Spacer(modifier = Modifier.height(4.dp))

                    // Subscriber Info Card: Current Package & Smart Card
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("renewal_current_info_card"),
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color(0x332563EB)
                        ),
                        border = BorderStroke(1.dp, Color(0x4D38BDF8))
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            // Package Info
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(10.dp)
                            ) {
                                Surface(
                                    shape = CircleShape,
                                    color = Color(0x3338BDF8),
                                    modifier = Modifier.size(40.dp)
                                ) {
                                    Box(
                                        contentAlignment = Alignment.Center,
                                        modifier = Modifier.fillMaxSize()
                                    ) {
                                        Icon(
                                            imageVector = Icons.Outlined.CardMembership,
                                            contentDescription = null,
                                            tint = Color(0xFF38BDF8),
                                            modifier = Modifier.size(20.dp)
                                        )
                                    }
                                }
                                Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
                                    Text(
                                        text = "الباقة الحالية",
                                        style = MaterialTheme.typography.labelSmall.copy(
                                            color = Color(0xFF94A3B8),
                                            fontSize = 11.sp
                                        )
                                    )
                                    Text(
                                        text = "الباقة الأساسية",
                                        style = MaterialTheme.typography.bodyMedium.copy(
                                            fontWeight = FontWeight.Bold,
                                            color = Color(0xFFF1F5F9),
                                            fontSize = 14.sp
                                        ),
                                        modifier = Modifier.testTag("renewal_current_package")
                                    )
                                }
                            }

                            // Smart Card Info
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Outlined.CreditCard,
                                    contentDescription = null,
                                    tint = Color(0xFFA5B4FC),
                                    modifier = Modifier.size(18.dp)
                                )
                                Column(
                                    horizontalAlignment = Alignment.End,
                                    verticalArrangement = Arrangement.spacedBy(2.dp)
                                ) {
                                    Text(
                                        text = "البطاقة الذكية",
                                        style = MaterialTheme.typography.labelSmall.copy(
                                            color = Color(0xFF94A3B8),
                                            fontSize = 11.sp
                                        )
                                    )
                                    Text(
                                        text = "**** 4587",
                                        style = MaterialTheme.typography.bodyMedium.copy(
                                            fontWeight = FontWeight.Bold,
                                            color = Color(0xFFFFFFFF),
                                            fontSize = 13.sp
                                        ),
                                        modifier = Modifier.testTag("renewal_smart_card")
                                    )
                                }
                            }
                        }
                    }

                    // Section Heading
                    Text(
                        text = "اختر مدة التجديد",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFFF8FAFC)
                        ),
                        modifier = Modifier.testTag("renewal_plans_section_title")
                    )

                    // Renewal Plans List
                    Column(
                        verticalArrangement = Arrangement.spacedBy(10.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        plans.forEach { plan ->
                            val isSelected = plan.id == selectedPlanId
                            RenewalPlanCard(
                                plan = plan,
                                isSelected = isSelected,
                                onClick = { selectedPlanId = plan.id }
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
private fun RenewalPlanCard(
    plan: RenewalPlan,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    // Tasteful varied accent colors based on plan duration
    val accentColor = when (plan.durationValue) {
        1 -> Color(0xFFF59E0B) // Amber for 1 month
        2 -> Color(0xFF38BDF8) // Sky blue for 2 months
        3 -> Color(0xFFA78BFA) // Purple for 3 months
        6 -> Color(0xFF34D399) // Emerald for 6 months
        12 -> Color(0xFFFBBF24) // Gold for 1 year
        else -> Color(0xFF38BDF8)
    }

    // Expressive Material icon for each duration
    val planIcon: ImageVector = when (plan.durationValue) {
        1 -> Icons.Outlined.Bolt
        2 -> Icons.Outlined.CalendarMonth
        3 -> Icons.Outlined.Stars
        6 -> Icons.Outlined.Diamond
        12 -> Icons.Outlined.AutoAwesome
        else -> Icons.Outlined.CalendarMonth
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
            .testTag("renewal_plan_card_${plan.id}"),
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
            // Right (RTL start): Radio selection indicator + Expressive Icon + Duration Label
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // Radio indicator
                Icon(
                    imageVector = if (isSelected) Icons.Outlined.CheckCircle else Icons.Outlined.RadioButtonUnchecked,
                    contentDescription = null,
                    tint = if (isSelected) accentColor else Color(0xFF94A3B8),
                    modifier = Modifier.size(22.dp)
                )

                // Expressive Icon badge
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = accentColor.copy(alpha = 0.18f),
                    border = BorderStroke(1.dp, accentColor.copy(alpha = 0.35f)),
                    modifier = Modifier.size(42.dp)
                ) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Icon(
                            imageVector = planIcon,
                            contentDescription = plan.durationLabel,
                            tint = accentColor,
                            modifier = Modifier.size(22.dp)
                        )
                    }
                }

                // Duration Label
                Text(
                    text = plan.durationLabel,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.SemiBold,
                        color = Color(0xFFF8FAFC),
                        fontSize = 15.sp
                    ),
                    modifier = Modifier.testTag("plan_duration_${plan.id}")
                )
            }

            // Left (RTL end): Formatted Price & Currency read from centralized data source
            Text(
                text = RenewalPlanDataSource.formatPrice(plan.price, plan.currency),
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold,
                    color = if (isSelected) accentColor else Color(0xFFE2E8F0),
                    fontSize = 15.sp
                ),
                modifier = Modifier.testTag("plan_price_${plan.id}")
            )
        }
    }
}
