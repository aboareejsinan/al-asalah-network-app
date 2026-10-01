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
import androidx.compose.material.icons.outlined.Build
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Sensors
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.Tv
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

private data class GuideTopic(
    val title: String,
    val icon: ImageVector,
    val accentColor: Color,
    val points: List<String>,
    val tag: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReceptionGuideScreen(
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

    val guideTopics = listOf(
        GuideTopic(
            title = "كيف تستقبل شبكة الأصالة؟",
            icon = Icons.Outlined.Sensors,
            accentColor = Color(0xFF34D399), // Emerald green
            points = listOf(
                "يعتمد استقبال شبكة الأصالة على موقع المشترك وقوة الإشارة.",
                "يمكن استقبال البث مباشرة باستخدام رأس استقبال إشارة يدعم DVB-T2 / UHF وتوجيهه نحو برج البث المحلي.",
                "يفضل تركيب وسيلة الاستقبال خارج المبنى وعلى السطح للحصول على استقرار أفضل للإشارة.",
                "يمكن استخدام الاستقبال الداخلي عند القرب من برج البث وتوفر إشارة مناسبة.",
                "يمكن أيضاً استخدام طبق استقبال مع رأس استقبال مناسب عند الحاجة لتحسين الإشارة حسب موقع المشترك والعوائق المحيطة."
            ),
            tag = "guide_topic_reception"
        ),
        GuideTopic(
            title = "الأجهزة المطلوبة",
            icon = Icons.Outlined.Tv,
            accentColor = Color(0xFF38BDF8), // Cyan / Sky blue
            points = listOf(
                "جهاز تلفزيون أو رسيفر يدعم DVB-T2.",
                "رسيفر خارجي عند الحاجة.",
                "قارئ بطاقة ذكية Smart Card Reader / CA Slot لاستقبال القنوات المشفرة.",
                "دعم Multi-CAS والتوافق مع بطاقة DRE-Crypt للقنوات المشفرة.",
                "كابل Coaxial RG6 للتوصيل."
            ),
            tag = "guide_topic_devices"
        ),
        GuideTopic(
            title = "طريقة التركيب",
            icon = Icons.Outlined.Build,
            accentColor = Color(0xFFFBBF24), // Amber
            points = listOf(
                "تثبيت وسيلة الاستقبال في مكان مناسب.",
                "توجيهها نحو برج شبكة الأصالة.",
                "توصيل كابل الاستقبال بالجهاز.",
                "يتم التوصيل عبر ANTENNA / AIR / DTV IN في التلفزيون أو RF IN / ANT IN في الرسيفر الخارجي.",
                "عند استخدام رسيفر خارجي يتم توصيله بالتلفزيون عبر HDMI."
            ),
            tag = "guide_topic_installation"
        ),
        GuideTopic(
            title = "طريقة البحث والضبط",
            icon = Icons.Outlined.Settings,
            accentColor = Color(0xFFA78BFA), // Violet / Purple
            points = listOf(
                "فتح إعدادات القنوات أو البث في الجهاز.",
                "اختيار Air / Antenna / DVB-T2.",
                "يمكن استخدام البحث اليدوي وإدخال بيانات التردد المعتمدة للشبكة.",
                "يمكن أيضاً استخدام البحث التلقائي DTV.",
                "مراقبة قوة وجودة الإشارة أثناء الضبط.",
                "حفظ القنوات بعد اكتمال البحث."
            ),
            tag = "guide_topic_setup"
        ),
        GuideTopic(
            title = "معلومات عن DVB-T2",
            icon = Icons.Outlined.Info,
            accentColor = Color(0xFF2DD4BF), // Teal
            points = listOf(
                "DVB-T2 هو معيار للبث التلفزيوني الرقمي الأرضي عبر أبراج البث.",
                "يوفر جودة أفضل للصورة والصوت.",
                "لا يحتاج إلى اتصال بالإنترنت لاستقبال البث.",
                "جودة الاستقبال تعتمد على قوة الإشارة وموقع المشترك."
            ),
            tag = "guide_topic_info"
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
                    .testTag("reception_guide_screen_scaffold"),
                containerColor = Color.Transparent,
                topBar = {
                    TopAppBar(
                        title = {
                            Text(
                                text = "دليل استقبال البث",
                                style = MaterialTheme.typography.titleLarge.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFFF8FAFC),
                                    fontSize = 20.sp
                                ),
                                modifier = Modifier.testTag("reception_guide_screen_title")
                            )
                        },
                        navigationIcon = {
                            IconButton(
                                onClick = onBackClick,
                                modifier = Modifier.testTag("reception_guide_back_button")
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
                        .verticalScroll(rememberScrollState()),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Spacer(modifier = Modifier.height(4.dp))

                    guideTopics.forEach { topic ->
                        GuideTopicCard(topic = topic)
                    }

                    Spacer(modifier = Modifier.height(20.dp))
                }
            }
        }
    }
}

@Composable
private fun GuideTopicCard(
    topic: GuideTopic
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .testTag(topic.tag),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0x381E3A8A)
        ),
        border = BorderStroke(1.2.dp, topic.accentColor.copy(alpha = 0.4f))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            // Header Row: Topic Icon + Title
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = topic.accentColor.copy(alpha = 0.18f),
                    border = BorderStroke(1.dp, topic.accentColor.copy(alpha = 0.4f)),
                    modifier = Modifier.size(44.dp)
                ) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Icon(
                            imageVector = topic.icon,
                            contentDescription = null,
                            tint = topic.accentColor,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }

                Text(
                    text = topic.title,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFFF1F5F9),
                        fontSize = 17.sp
                    ),
                    modifier = Modifier.testTag("${topic.tag}_title")
                )
            }

            // Divider
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(Color(0x24FFFFFF))
            )

            // Points list
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                topic.points.forEachIndexed { index, point ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp),
                        verticalAlignment = Alignment.Top
                    ) {
                        Surface(
                            shape = CircleShape,
                            color = topic.accentColor.copy(alpha = 0.2f),
                            border = BorderStroke(1.dp, topic.accentColor.copy(alpha = 0.5f)),
                            modifier = Modifier
                                .padding(top = 3.dp)
                                .size(20.dp)
                        ) {
                            Box(
                                contentAlignment = Alignment.Center,
                                modifier = Modifier.fillMaxSize()
                            ) {
                                Icon(
                                    imageVector = Icons.Outlined.Check,
                                    contentDescription = null,
                                    tint = topic.accentColor,
                                    modifier = Modifier.size(12.dp)
                                )
                            }
                        }

                        Text(
                            text = point,
                            style = MaterialTheme.typography.bodyMedium.copy(
                                color = Color(0xFFCBD5E1),
                                fontSize = 13.sp,
                                lineHeight = 20.sp
                            ),
                            modifier = Modifier.testTag("${topic.tag}_point_$index")
                        )
                    }
                }
            }
        }
    }
}
