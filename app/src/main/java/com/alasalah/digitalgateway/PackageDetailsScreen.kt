package com.alasalah.digitalgateway

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
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
import androidx.compose.material.icons.outlined.Celebration
import androidx.compose.material.icons.outlined.CellTower
import androidx.compose.material.icons.outlined.ChildCare
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Mosque
import androidx.compose.material.icons.outlined.Movie
import androidx.compose.material.icons.outlined.Newspaper
import androidx.compose.material.icons.outlined.Public
import androidx.compose.material.icons.outlined.SportsSoccer
import androidx.compose.material.icons.outlined.Tag
import androidx.compose.material.icons.outlined.TheaterComedy
import androidx.compose.material.icons.outlined.Tv
import androidx.compose.material.icons.outlined.VerifiedUser
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

/**
 * Reusable model for package content categories.
 */
data class ContentCategory(
    val title: String,
    val icon: ImageVector,
    val accentColor: Color
)

/**
 * Reusable package detail model containing package metadata and categories.
 */
data class PackageDetailInfo(
    val id: String,
    val packageName: String,
    val channelCount: String,
    val accessType: String,
    val description: String,
    val categories: List<ContentCategory>,
    val headerIcon: ImageVector = Icons.Outlined.Tv,
    val accentColor: Color = Color(0xFF38BDF8),
    val isEncrypted: Boolean = false
)

object PackageDetailRepository {
    val encryptedPackage = PackageDetailInfo(
        id = "encrypted_package",
        packageName = "الباقة المشفرة",
        channelCount = "20 قناة",
        accessType = "للمشتركين فقط",
        description = "تضم مجموعة من القنوات الرياضية المشفرة بالإضافة إلى قنوات الأفلام والدراما والترفيه.",
        categories = listOf(
            ContentCategory(title = "رياضة", icon = Icons.Outlined.SportsSoccer, accentColor = Color(0xFF38BDF8)),
            ContentCategory(title = "أفلام", icon = Icons.Outlined.Movie, accentColor = Color(0xFFF472B6)),
            ContentCategory(title = "دراما", icon = Icons.Outlined.TheaterComedy, accentColor = Color(0xFFA78BFA)),
            ContentCategory(title = "ترفيه", icon = Icons.Outlined.Tv, accentColor = Color(0xFFFBBF24))
        ),
        headerIcon = Icons.Outlined.Lock,
        accentColor = Color(0xFF38BDF8),
        isEncrypted = true
    )

    val openPackage = PackageDetailInfo(
        id = "open_package",
        packageName = "الباقة المفتوحة",
        channelCount = "20 قناة",
        accessType = "متاحة للجميع",
        description = "مجموعة قنوات متنوعة ومتاحة بدون اشتراك.",
        categories = listOf(
            ContentCategory(title = "أخبار", icon = Icons.Outlined.Newspaper, accentColor = Color(0xFF38BDF8)),
            ContentCategory(title = "عامة", icon = Icons.Outlined.Public, accentColor = Color(0xFF34D399)),
            ContentCategory(title = "دينية", icon = Icons.Outlined.Mosque, accentColor = Color(0xFFFBBF24)),
            ContentCategory(title = "أطفال", icon = Icons.Outlined.ChildCare, accentColor = Color(0xFFFB923C)),
            ContentCategory(title = "ترفيه", icon = Icons.Outlined.Celebration, accentColor = Color(0xFFA855F7)),
            ContentCategory(title = "قنوات محلية", icon = Icons.Outlined.CellTower, accentColor = Color(0xFF2DD4BF))
        ),
        headerIcon = Icons.Outlined.CellTower,
        accentColor = Color(0xFF34D399),
        isEncrypted = false
    )

    fun getPackageById(id: String): PackageDetailInfo {
        return if (id == "open_package") openPackage else encryptedPackage
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun PackageDetailsScreen(
    packageInfo: PackageDetailInfo = PackageDetailRepository.encryptedPackage,
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

    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(broadcastBackground)
        ) {
            Scaffold(
                modifier = Modifier
                    .fillMaxSize()
                    .testTag("package_details_scaffold"),
                containerColor = Color.Transparent,
                topBar = {
                    TopAppBar(
                        title = {
                            Text(
                                text = "تفاصيل الباقة",
                                style = MaterialTheme.typography.titleLarge.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFFF8FAFC),
                                    fontSize = 20.sp
                                ),
                                modifier = Modifier.testTag("package_details_title")
                            )
                        },
                        navigationIcon = {
                            IconButton(
                                onClick = onBackClick,
                                modifier = Modifier.testTag("package_details_back_button")
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

                    // Package Overview Card
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("package_details_summary_card"),
                        shape = RoundedCornerShape(22.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color(0x381E3A8A)
                        ),
                        border = BorderStroke(1.2.dp, packageInfo.accentColor.copy(alpha = 0.45f))
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(20.dp),
                            verticalArrangement = Arrangement.spacedBy(14.dp)
                        ) {
                            // Header Row
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                                ) {
                                    Surface(
                                        shape = RoundedCornerShape(12.dp),
                                        color = packageInfo.accentColor.copy(alpha = 0.18f),
                                        border = BorderStroke(1.dp, packageInfo.accentColor.copy(alpha = 0.4f)),
                                        modifier = Modifier.size(46.dp)
                                    ) {
                                        Box(
                                            contentAlignment = Alignment.Center,
                                            modifier = Modifier.fillMaxSize()
                                        ) {
                                            Icon(
                                                imageVector = packageInfo.headerIcon,
                                                contentDescription = null,
                                                tint = packageInfo.accentColor,
                                                modifier = Modifier.size(24.dp)
                                            )
                                        }
                                    }
                                    Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
                                        Text(
                                            text = packageInfo.packageName,
                                            style = MaterialTheme.typography.titleMedium.copy(
                                                fontWeight = FontWeight.Bold,
                                                color = Color(0xFFF1F5F9),
                                                fontSize = 18.sp
                                            ),
                                            modifier = Modifier.testTag("package_details_name")
                                        )
                                        Row(
                                            verticalAlignment = Alignment.CenterVertically,
                                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                                        ) {
                                            Icon(
                                                imageVector = Icons.Outlined.Tag,
                                                contentDescription = null,
                                                tint = Color(0xFF94A3B8),
                                                modifier = Modifier.size(13.dp)
                                            )
                                            Text(
                                                text = packageInfo.channelCount,
                                                style = MaterialTheme.typography.bodySmall.copy(
                                                    fontWeight = FontWeight.SemiBold,
                                                    color = packageInfo.accentColor,
                                                    fontSize = 12.sp
                                                ),
                                                modifier = Modifier.testTag("package_details_channels_count")
                                            )
                                        }
                                    }
                                }

                                // Access badge
                                Surface(
                                    shape = RoundedCornerShape(8.dp),
                                    color = if (packageInfo.isEncrypted) Color(0x33F59E0B) else Color(0x3310B981),
                                    border = BorderStroke(
                                        1.dp,
                                        if (packageInfo.isEncrypted) Color(0x66F59E0B) else Color(0x6610B981)
                                    )
                                ) {
                                    Row(
                                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp),
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.spacedBy(5.dp)
                                    ) {
                                        Icon(
                                            imageVector = if (packageInfo.isEncrypted) Icons.Outlined.Lock else Icons.Outlined.VerifiedUser,
                                            contentDescription = null,
                                            tint = if (packageInfo.isEncrypted) Color(0xFFFBBF24) else Color(0xFF34D399),
                                            modifier = Modifier.size(13.dp)
                                        )
                                        Text(
                                            text = packageInfo.accessType,
                                            style = MaterialTheme.typography.labelMedium.copy(
                                                color = if (packageInfo.isEncrypted) Color(0xFFFDE68A) else Color(0xFF86EFAC),
                                                fontWeight = FontWeight.Bold,
                                                fontSize = 11.sp
                                            ),
                                            modifier = Modifier.testTag("package_details_access")
                                        )
                                    }
                                }
                            }

                            // Description
                            Text(
                                text = packageInfo.description,
                                style = MaterialTheme.typography.bodySmall.copy(
                                    color = Color(0xFFC7D2FE),
                                    fontSize = 13.sp,
                                    lineHeight = 19.sp
                                ),
                                modifier = Modifier.testTag("package_details_description")
                            )
                        }
                    }

                    // Content Categories Card
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("package_details_categories_card"),
                        shape = RoundedCornerShape(22.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color(0x2EFFFFFF)
                        ),
                        border = BorderStroke(1.dp, Color(0x29FFFFFF))
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(18.dp),
                            verticalArrangement = Arrangement.spacedBy(14.dp)
                        ) {
                            Text(
                                text = "تصنيفات المحتوى",
                                style = MaterialTheme.typography.titleMedium.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFFF8FAFC),
                                    fontSize = 16.sp
                                ),
                                modifier = Modifier.testTag("package_details_categories_title")
                            )

                            // Responsive FlowRow of category cards
                            FlowRow(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(10.dp),
                                verticalArrangement = Arrangement.spacedBy(10.dp)
                            ) {
                                packageInfo.categories.forEach { category ->
                                    CategoryItemCard(category = category)
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(20.dp))
                }
            }
        }
    }
}

@Composable
private fun CategoryItemCard(
    category: ContentCategory
) {
    Surface(
        shape = RoundedCornerShape(14.dp),
        color = Color(0x331E293B),
        border = BorderStroke(1.dp, category.accentColor.copy(alpha = 0.4f)),
        modifier = Modifier.testTag("category_card_${category.title}")
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 14.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Surface(
                shape = RoundedCornerShape(10.dp),
                color = category.accentColor.copy(alpha = 0.2f),
                border = BorderStroke(0.8.dp, category.accentColor.copy(alpha = 0.45f)),
                modifier = Modifier.size(34.dp)
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Icon(
                        imageVector = category.icon,
                        contentDescription = null,
                        tint = category.accentColor,
                        modifier = Modifier.size(18.dp)
                    )
                }
            }
            Text(
                text = category.title,
                style = MaterialTheme.typography.labelLarge.copy(
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFF1F5F9),
                    fontSize = 14.sp
                ),
                modifier = Modifier.testTag("category_item_${category.title}")
            )
        }
    }
}
