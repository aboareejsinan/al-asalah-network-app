package com.alasalah.digitalgateway

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.outlined.ExpandMore
import androidx.compose.material.icons.outlined.HelpOutline
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
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private data class FaqItem(
    val question: String,
    val answer: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FaqScreen(
    onBack: () -> Unit = {}
) {
    BackHandler {
        onBack()
    }

    val faqList = listOf(
        FaqItem(
            question = "هل يحتاج استقبال شبكة الأصالة إلى الإنترنت؟",
            answer = "لا. استقبال البث الرقمي الأرضي عبر شبكة الأصالة لا يحتاج إلى اتصال بالإنترنت."
        ),
        FaqItem(
            question = "كيف يمكن استقبال بث شبكة الأصالة؟",
            answer = "يمكن استقبال البث مباشرة باستخدام وسيلة استقبال تدعم DVB-T2 / UHF وموجهة نحو برج البث، ويمكن أيضاً استخدام طبق استقبال مع رأس مناسب عند الحاجة حسب الموقع وقوة الإشارة."
        ),
        FaqItem(
            question = "ما مواصفات جهاز الاستقبال المطلوب؟",
            answer = "يجب أن يدعم الجهاز DVB-T2، وللقنوات المشفرة يجب توفر قارئ بطاقة ذكية Smart Card Reader / CA Slot ودعم Multi-CAS والتوافق مع بطاقة DRE-Crypt."
        ),
        FaqItem(
            question = "هل يجب استخدام رسيفر خاص بشبكة الأصالة؟",
            answer = "لا. يمكن استخدام أي جهاز استقبال متوافق مع متطلبات الشبكة."
        ),
        FaqItem(
            question = "ماذا أفعل إذا لم تعمل البطاقة الذكية؟",
            answer = "تأكد من إدخال البطاقة في جهاز استقبال متوافق. وإذا استمرت المشكلة يتم التواصل مع الدعم الفني."
        ),
        FaqItem(
            question = "أين تتوفر تغطية شبكة الأصالة؟",
            answer = "التغطية الحالية في محافظة مأرب وتشمل مدينة مأرب ومديريات الوادي، والشبكة قابلة للتوسع مستقبلاً."
        ),
        FaqItem(
            question = "أين أجد ترددات الشبكة؟",
            answer = "تتوفر الترددات المعتمدة داخل قسم الترددات في التطبيق، ويتم اختيار بيانات البرج المناسب لموقع المشترك."
        ),
        FaqItem(
            question = "ما أوقات عمل الدعم الفني؟",
            answer = "الدوام الرسمي من الساعة 12 ظهراً إلى الساعة 12 صباحاً، وخلال الأحداث الرياضية المهمة يتوفر الدعم على مدار 24 ساعة."
        )
    )

    val expandedStates = remember { mutableStateMapOf<Int, Boolean>() }

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
                    .testTag("faq_screen_scaffold"),
                containerColor = Color.Transparent,
                topBar = {
                    TopAppBar(
                        title = {
                            Text(
                                text = "الأسئلة الشائعة",
                                style = MaterialTheme.typography.titleLarge.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFFF8FAFC),
                                    fontSize = 20.sp
                                ),
                                modifier = Modifier.testTag("faq_screen_title")
                            )
                        },
                        navigationIcon = {
                            IconButton(
                                onClick = onBack,
                                modifier = Modifier.testTag("faq_back_button")
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
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                        .padding(horizontal = 20.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    item {
                        Spacer(modifier = Modifier.height(4.dp))
                    }

                    itemsIndexed(faqList) { index, item ->
                        val isExpanded = expandedStates[index] ?: false
                        val rotationState by animateFloatAsState(
                            targetValue = if (isExpanded) 180f else 0f,
                            label = "faq_expand_arrow"
                        )

                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    expandedStates[index] = !isExpanded
                                }
                                .testTag("faq_card_$index"),
                            shape = RoundedCornerShape(16.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = if (isExpanded) Color(0x381E3A8A) else Color(0x2EFFFFFF)
                            ),
                            border = BorderStroke(
                                1.dp,
                                if (isExpanded) Color(0x4D38BDF8) else Color(0x2638BDF8)
                            )
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),
                                verticalArrangement = Arrangement.spacedBy(12.dp)
                            ) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                                ) {
                                    Surface(
                                        shape = RoundedCornerShape(10.dp),
                                        color = if (isExpanded) Color(0x3338BDF8) else Color(0x2238BDF8),
                                        modifier = Modifier.size(36.dp)
                                    ) {
                                        Box(
                                            contentAlignment = Alignment.Center,
                                            modifier = Modifier.fillMaxSize()
                                        ) {
                                            Icon(
                                                imageVector = Icons.Outlined.HelpOutline,
                                                contentDescription = null,
                                                tint = Color(0xFF38BDF8),
                                                modifier = Modifier.size(20.dp)
                                            )
                                        }
                                    }

                                    Text(
                                        text = item.question,
                                        style = MaterialTheme.typography.titleMedium.copy(
                                            fontWeight = FontWeight.Bold,
                                            color = Color(0xFFF8FAFC),
                                            fontSize = 15.sp,
                                            lineHeight = 22.sp
                                        ),
                                        modifier = Modifier.weight(1f)
                                    )

                                    Icon(
                                        imageVector = Icons.Outlined.ExpandMore,
                                        contentDescription = if (isExpanded) "إغلاق" else "عرض الإجابة",
                                        tint = Color(0xFF94A3B8),
                                        modifier = Modifier
                                            .size(22.dp)
                                            .rotate(rotationState)
                                    )
                                }

                                AnimatedVisibility(visible = isExpanded) {
                                    Column(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(top = 4.dp),
                                        verticalArrangement = Arrangement.spacedBy(8.dp)
                                    ) {
                                        Box(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .height(1.dp)
                                                .background(Color(0x24FFFFFF))
                                        )
                                        Text(
                                            text = item.answer,
                                            style = MaterialTheme.typography.bodyMedium.copy(
                                                color = Color(0xFFE2E8F0),
                                                fontSize = 14.sp,
                                                lineHeight = 22.sp,
                                                fontWeight = FontWeight.Normal
                                            ),
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(horizontal = 4.dp, vertical = 4.dp)
                                                .testTag("faq_answer_$index")
                                        )
                                    }
                                }
                            }
                        }
                    }

                    item {
                        Spacer(modifier = Modifier.height(20.dp))
                    }
                }
            }
        }
    }
}
