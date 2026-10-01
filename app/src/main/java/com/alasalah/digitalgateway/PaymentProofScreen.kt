package com.alasalah.digitalgateway

import android.net.Uri
import androidx.activity.compose.BackHandler
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.automirrored.outlined.Send
import androidx.compose.material.icons.outlined.AccountBalance
import androidx.compose.material.icons.outlined.AddPhotoAlternate
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.CreditCard
import androidx.compose.material.icons.outlined.DeleteOutline
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.EditNote
import androidx.compose.material.icons.outlined.Numbers
import androidx.compose.material.icons.outlined.Paid
import androidx.compose.material.icons.outlined.SwapHoriz
import androidx.compose.material.icons.outlined.VerifiedUser
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaymentProofScreen(
    planId: String = "plan_1_month",
    methodId: String = "kuraimi",
    onBackClick: () -> Unit = {},
    onNavigateToConfirmation: (planId: String, methodId: String) -> Unit = { _, _ -> }
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

    var transferReference by remember { mutableStateOf("") }
    var noteText by remember { mutableStateOf("") }
    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }

    val photoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia()
    ) { uri: Uri? ->
        if (uri != null) {
            selectedImageUri = uri
        }
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
                    .testTag("payment_proof_scaffold"),
                containerColor = Color.Transparent,
                topBar = {
                    TopAppBar(
                        title = {
                            Text(
                                text = "إثبات الدفع",
                                style = MaterialTheme.typography.titleLarge.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFFF8FAFC),
                                    fontSize = 20.sp
                                ),
                                modifier = Modifier.testTag("payment_proof_title")
                            )
                        },
                        navigationIcon = {
                            IconButton(
                                onClick = onBackClick,
                                modifier = Modifier.testTag("payment_proof_back_button")
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
                    // Fixed primary action button: "إرسال الطلب" (does not submit anything yet)
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
                                    onNavigateToConfirmation(plan.id, method.id)
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(50.dp)
                                    .testTag("submit_proof_button"),
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
                                        imageVector = Icons.AutoMirrored.Outlined.Send,
                                        contentDescription = null,
                                        modifier = Modifier.size(18.dp)
                                    )
                                    Text(
                                        text = "إرسال الطلب",
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
                        .testTag("payment_proof_content_column"),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Spacer(modifier = Modifier.height(2.dp))

                    // 1. Compact Summary Card (Smart Card, Duration, Amount, Payment Provider)
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("payment_proof_summary_card"),
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
                            // Row 1: Smart Card & Duration
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(12.dp)
                            ) {
                                ProofSummaryDetailItem(
                                    icon = Icons.Outlined.CreditCard,
                                    iconTint = Color(0xFF7DD3FC),
                                    label = "البطاقة الذكية",
                                    value = "**** 4587",
                                    modifier = Modifier.weight(1f),
                                    tag = "proof_summary_smart_card"
                                )
                                ProofSummaryDetailItem(
                                    icon = Icons.Outlined.CalendarMonth,
                                    iconTint = Color(0xFF67E8F9),
                                    label = "مدة التجديد",
                                    value = plan.durationLabel,
                                    modifier = Modifier.weight(1f),
                                    tag = "proof_summary_duration"
                                )
                            }

                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(1.dp)
                                    .background(Color(0x24FFFFFF))
                            )

                            // Row 2: Payment Provider & Required Amount (Visually prominent)
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(12.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                ProofSummaryDetailItem(
                                    icon = if (method.accountNumber.isNullOrBlank()) Icons.Outlined.SwapHoriz else Icons.Outlined.AccountBalance,
                                    iconTint = Color(0xFF34D399),
                                    label = "وسيلة الدفع",
                                    value = method.providerName,
                                    modifier = Modifier.weight(1f),
                                    tag = "proof_summary_provider"
                                )
                                ProofSummaryDetailItem(
                                    icon = Icons.Outlined.Paid,
                                    iconTint = Color(0xFF38BDF8),
                                    label = "المبلغ المطلوب",
                                    value = RenewalPlanDataSource.formatPrice(plan.price, plan.currency),
                                    modifier = Modifier.weight(1f),
                                    valueColor = Color(0xFF38BDF8),
                                    isBoldAmount = true,
                                    tag = "proof_summary_amount"
                                )
                            }
                        }
                    }

                    // 2. Input Area 1: Transfer Reference ("رقم عملية التحويل")
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color(0x26FFFFFF)
                        ),
                        border = BorderStroke(1.dp, Color(0x29FFFFFF))
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Outlined.Numbers,
                                    contentDescription = null,
                                    tint = Color(0xFF38BDF8),
                                    modifier = Modifier.size(18.dp)
                                )
                                Text(
                                    text = "رقم عملية التحويل",
                                    style = MaterialTheme.typography.titleSmall.copy(
                                        fontWeight = FontWeight.Bold,
                                        color = Color(0xFFF1F5F9),
                                        fontSize = 14.sp
                                    )
                                )
                            }

                            OutlinedTextField(
                                value = transferReference,
                                onValueChange = { transferReference = it },
                                placeholder = {
                                    Text(
                                        text = "أدخل رقم العملية إن وجد",
                                        style = MaterialTheme.typography.bodyMedium.copy(
                                            color = Color(0xFF94A3B8),
                                            fontSize = 13.sp
                                        )
                                    )
                                },
                                singleLine = true,
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .testTag("transfer_reference_input"),
                                shape = RoundedCornerShape(12.dp),
                                colors = OutlinedTextFieldDefaults.colors(
                                    focusedTextColor = Color.White,
                                    unfocusedTextColor = Color.White,
                                    focusedBorderColor = Color(0xFF38BDF8),
                                    unfocusedBorderColor = Color(0x33FFFFFF),
                                    focusedContainerColor = Color(0x24000000),
                                    unfocusedContainerColor = Color(0x1A000000)
                                )
                            )
                        }
                    }

                    // 3. Input Area 2: Proof Image Area (Distinct Violet / Teal accent)
                    val proofAreaGradient = Brush.linearGradient(
                        colors = listOf(
                            Color(0x388B5CF6), // Subtle violet accent
                            Color(0x260D9488)  // Subtle teal accent
                        )
                    )

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("proof_image_card"),
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color.Transparent
                        ),
                        border = BorderStroke(1.5.dp, Color(0x808B5CF6))
                    ) {
                        if (selectedImageUri == null) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(proofAreaGradient)
                                    .padding(18.dp),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.spacedBy(10.dp)
                            ) {
                                Surface(
                                    shape = CircleShape,
                                    color = Color(0x33A78BFA),
                                    border = BorderStroke(1.dp, Color(0x66A78BFA)),
                                    modifier = Modifier.size(52.dp)
                                ) {
                                    Box(
                                        contentAlignment = Alignment.Center,
                                        modifier = Modifier.fillMaxSize()
                                    ) {
                                        Icon(
                                            imageVector = Icons.Outlined.AddPhotoAlternate,
                                            contentDescription = null,
                                            tint = Color(0xFFC4B5FD),
                                            modifier = Modifier.size(28.dp)
                                        )
                                    }
                                }

                                Text(
                                    text = "صورة الإيصال",
                                    style = MaterialTheme.typography.titleMedium.copy(
                                        fontWeight = FontWeight.Bold,
                                        color = Color(0xFFF8FAFC),
                                        fontSize = 15.sp
                                    ),
                                    modifier = Modifier.testTag("proof_image_title")
                                )

                                Text(
                                    text = "أرفق صورة واضحة لإيصال أو إثبات التحويل",
                                    style = MaterialTheme.typography.bodySmall.copy(
                                        color = Color(0xFFCBD5E1),
                                        fontSize = 12.sp
                                    ),
                                    modifier = Modifier.testTag("proof_image_description")
                                )

                                Spacer(modifier = Modifier.height(2.dp))

                                OutlinedButton(
                                    onClick = {
                                        photoPickerLauncher.launch(
                                            PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                                        )
                                    },
                                    shape = RoundedCornerShape(10.dp),
                                    colors = ButtonDefaults.outlinedButtonColors(
                                        contentColor = Color(0xFFE0E7FF)
                                    ),
                                    border = BorderStroke(1.dp, Color(0xFFA78BFA)),
                                    modifier = Modifier.testTag("attach_receipt_button")
                                ) {
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                                    ) {
                                        Icon(
                                            imageVector = Icons.Outlined.AddPhotoAlternate,
                                            contentDescription = null,
                                            modifier = Modifier.size(18.dp),
                                            tint = Color(0xFFC4B5FD)
                                        )
                                        Text(
                                            text = "إرفاق صورة",
                                            style = MaterialTheme.typography.labelMedium.copy(
                                                fontWeight = FontWeight.Bold,
                                                fontSize = 13.sp,
                                                color = Color(0xFFF1F5F9)
                                            )
                                        )
                                    }
                                }
                            }
                        } else {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(proofAreaGradient)
                                    .padding(16.dp),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.spacedBy(12.dp)
                            ) {
                                Text(
                                    text = "صورة الإيصال المرفقة",
                                    style = MaterialTheme.typography.titleSmall.copy(
                                        fontWeight = FontWeight.Bold,
                                        color = Color(0xFFF8FAFC),
                                        fontSize = 14.sp
                                    ),
                                    modifier = Modifier.testTag("proof_image_title")
                                )

                                // Compact Preview of the Selected Receipt Image
                                Surface(
                                    shape = RoundedCornerShape(12.dp),
                                    border = BorderStroke(1.5.dp, Color(0xFFA78BFA)),
                                    shadowElevation = 4.dp,
                                    modifier = Modifier.size(width = 140.dp, height = 140.dp)
                                ) {
                                    AsyncImage(
                                        model = selectedImageUri,
                                        contentDescription = "معاينة صورة الإيصال",
                                        contentScale = ContentScale.Crop,
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .clip(RoundedCornerShape(12.dp))
                                            .testTag("receipt_image_preview")
                                    )
                                }

                                // Actions: "تغيير الصورة" and "إزالة"
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.Center,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    OutlinedButton(
                                        onClick = {
                                            photoPickerLauncher.launch(
                                                PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                                            )
                                        },
                                        shape = RoundedCornerShape(8.dp),
                                        colors = ButtonDefaults.outlinedButtonColors(
                                            contentColor = Color(0xFFC4B5FD)
                                        ),
                                        border = BorderStroke(1.dp, Color(0xFFA78BFA)),
                                        modifier = Modifier.testTag("change_receipt_button")
                                    ) {
                                        Row(
                                            verticalAlignment = Alignment.CenterVertically,
                                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                                        ) {
                                            Icon(
                                                imageVector = Icons.Outlined.Edit,
                                                contentDescription = null,
                                                modifier = Modifier.size(16.dp),
                                                tint = Color(0xFFC4B5FD)
                                            )
                                            Text(
                                                text = "تغيير الصورة",
                                                style = MaterialTheme.typography.labelSmall.copy(
                                                    fontWeight = FontWeight.Bold,
                                                    fontSize = 12.sp,
                                                    color = Color(0xFFF1F5F9)
                                                )
                                            )
                                        }
                                    }

                                    Spacer(modifier = Modifier.size(12.dp))

                                    OutlinedButton(
                                        onClick = {
                                            selectedImageUri = null
                                        },
                                        shape = RoundedCornerShape(8.dp),
                                        colors = ButtonDefaults.outlinedButtonColors(
                                            contentColor = Color(0xFFFCA5A5)
                                        ),
                                        border = BorderStroke(1.dp, Color(0xFFF87171)),
                                        modifier = Modifier.testTag("remove_receipt_button")
                                    ) {
                                        Row(
                                            verticalAlignment = Alignment.CenterVertically,
                                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                                        ) {
                                            Icon(
                                                imageVector = Icons.Outlined.DeleteOutline,
                                                contentDescription = null,
                                                modifier = Modifier.size(16.dp),
                                                tint = Color(0xFFFCA5A5)
                                            )
                                            Text(
                                                text = "إزالة",
                                                style = MaterialTheme.typography.labelSmall.copy(
                                                    fontWeight = FontWeight.Bold,
                                                    fontSize = 12.sp,
                                                    color = Color(0xFFFCA5A5)
                                                )
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }

                    // 4. Input Area 3: Optional Note ("ملاحظة")
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color(0x26FFFFFF)
                        ),
                        border = BorderStroke(1.dp, Color(0x29FFFFFF))
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Outlined.EditNote,
                                    contentDescription = null,
                                    tint = Color(0xFF38BDF8),
                                    modifier = Modifier.size(18.dp)
                                )
                                Text(
                                    text = "ملاحظة",
                                    style = MaterialTheme.typography.titleSmall.copy(
                                        fontWeight = FontWeight.Bold,
                                        color = Color(0xFFF1F5F9),
                                        fontSize = 14.sp
                                    )
                                )
                            }

                            OutlinedTextField(
                                value = noteText,
                                onValueChange = { noteText = it },
                                placeholder = {
                                    Text(
                                        text = "أضف ملاحظة عند الحاجة",
                                        style = MaterialTheme.typography.bodyMedium.copy(
                                            color = Color(0xFF94A3B8),
                                            fontSize = 13.sp
                                        )
                                    )
                                },
                                minLines = 2,
                                maxLines = 4,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .testTag("optional_note_input"),
                                shape = RoundedCornerShape(12.dp),
                                colors = OutlinedTextFieldDefaults.colors(
                                    focusedTextColor = Color.White,
                                    unfocusedTextColor = Color.White,
                                    focusedBorderColor = Color(0xFF38BDF8),
                                    unfocusedBorderColor = Color(0x33FFFFFF),
                                    focusedContainerColor = Color(0x24000000),
                                    unfocusedContainerColor = Color(0x1A000000)
                                )
                            )
                        }
                    }

                    // 5. Important Info Card: "مراجعة العملية"
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("review_info_card"),
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
                                        imageVector = Icons.Outlined.VerifiedUser,
                                        contentDescription = null,
                                        tint = Color(0xFF38BDF8),
                                        modifier = Modifier.size(20.dp)
                                    )
                                }
                            }

                            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                                Text(
                                    text = "مراجعة العملية",
                                    style = MaterialTheme.typography.titleSmall.copy(
                                        fontWeight = FontWeight.Bold,
                                        color = Color(0xFFF1F5F9),
                                        fontSize = 14.sp
                                    ),
                                    modifier = Modifier.testTag("review_info_title")
                                )
                                Text(
                                    text = "سيتم التحقق من عملية الدفع قبل تنفيذ تجديد الاشتراك.",
                                    style = MaterialTheme.typography.bodySmall.copy(
                                        color = Color(0xFF94A3B8),
                                        fontSize = 12.sp,
                                        lineHeight = 18.sp
                                    ),
                                    modifier = Modifier.testTag("review_info_text")
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
private fun ProofSummaryDetailItem(
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
