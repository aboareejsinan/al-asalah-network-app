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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.outlined.CellTower
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.LockOpen
import androidx.compose.material.icons.outlined.Sensors
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
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private data class PackageFrequencyData(
    val packageName: String,
    val isEncrypted: Boolean,
    val frequency: String,
    val polarization: String,
    val symbolRate: String
)

private data class TowerData(
    val id: String,
    val towerName: String,
    val location: String,
    val packages: List<PackageFrequencyData>
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FrequenciesScreen(
    onBackClick: () -> Unit = {}
) {
    BackHandler {
        onBackClick()
    }

    val broadcastBackground = Brush.verticalGradient(
        colors = listOf(
            Color(0xFF1B3D4F),
            Color(0xFF174758),
            Color(0xFF1A3B4D)
        )
    )

    val towersList = listOf(
        TowerData(
            id = "samdah",
            towerName = "برج الصمدة (الرئيسي)",
            location = "الوادي - حصون آل جلال - منطقة الصمدة",
            packages = listOf(
                PackageFrequencyData(
                    packageName = "الباقة المشفرة",
                    isEncrypted = true,
                    frequency = "12450",
                    polarization = "أفقي",
                    symbolRate = "32000"
                ),
                PackageFrequencyData(
                    packageName = "الباقة المفتوحة",
                    isEncrypted = false,
                    frequency = "12226",
                    polarization = "أفقي",
                    symbolRate = "32000"
                )
            )
        ),
        TowerData(
            id = "haiat",
            towerName = "برج الهيئة",
            location = "المدينة - غرباً - أمام مستشفى الهيئة الطبي",
            packages = listOf(
                PackageFrequencyData(
                    packageName = "الباقة المشفرة",
                    isEncrypted = true,
                    frequency = "12500",
                    polarization = "أفقي",
                    symbolRate = "32000"
                ),
                PackageFrequencyData(
                    packageName = "الباقة المفتوحة",
                    isEncrypted = false,
                    frequency = "12300",
                    polarization = "أفقي",
                    symbolRate = "32000"
                )
            )
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
                    .testTag("frequencies_screen_scaffold"),
                containerColor = Color.Transparent,
                topBar = {
                    TopAppBar(
                        title = {
                            Text(
                                text = "الترددات",
                                style = MaterialTheme.typography.titleLarge.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFFF8FAFC),
                                    fontSize = 20.sp
                                ),
                                modifier = Modifier.testTag("frequencies_screen_title")
                            )
                        },
                        navigationIcon = {
                            IconButton(
                                onClick = onBackClick,
                                modifier = Modifier.testTag("frequencies_back_button")
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

                    // Intro Card
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("frequencies_intro_card"),
                        shape = RoundedCornerShape(18.dp),
                        colors = CardDefaults.cardColors(containerColor = Color(0x260284C7)),
                        border = BorderStroke(1.dp, Color(0x3D38BDF8))
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            Surface(
                                shape = RoundedCornerShape(12.dp),
                                color = Color(0x33FBBF24),
                                modifier = Modifier.size(44.dp)
                            ) {
                                Box(
                                    contentAlignment = Alignment.Center,
                                    modifier = Modifier.fillMaxSize()
                                ) {
                                    Icon(
                                        imageVector = Icons.Outlined.Sensors,
                                        contentDescription = null,
                                        tint = Color(0xFFFBBF24),
                                        modifier = Modifier.size(24.dp)
                                    )
                                }
                            }
                            Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
                                Text(
                                    text = "بيانات الترددات الرسمية",
                                    style = MaterialTheme.typography.titleMedium.copy(
                                        fontWeight = FontWeight.Bold,
                                        color = Color(0xFFF8FAFC),
                                        fontSize = 15.sp
                                    )
                                )
                                Text(
                                    text = "ترددات الاستقبال المعتمدة لأبراج بث شبكة الأصالة",
                                    style = MaterialTheme.typography.bodySmall.copy(
                                        color = Color(0xFF94A3B8),
                                        fontSize = 12.sp
                                    )
                                )
                            }
                        }
                    }

                    // Towers and Packages
                    towersList.forEachIndexed { towerIndex, tower ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .testTag("tower_card_${tower.id}"),
                            shape = RoundedCornerShape(18.dp),
                            colors = CardDefaults.cardColors(containerColor = Color(0x381E3A8A)),
                            border = BorderStroke(1.dp, Color(0x4DFBBF24))
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),
                                verticalArrangement = Arrangement.spacedBy(14.dp)
                            ) {
                                // Tower Header
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                                ) {
                                    Surface(
                                        shape = RoundedCornerShape(10.dp),
                                        color = Color(0x33FBBF24),
                                        modifier = Modifier.size(36.dp)
                                    ) {
                                        Box(
                                            contentAlignment = Alignment.Center,
                                            modifier = Modifier.fillMaxSize()
                                        ) {
                                            Icon(
                                                imageVector = Icons.Outlined.CellTower,
                                                contentDescription = null,
                                                tint = Color(0xFFFBBF24),
                                                modifier = Modifier.size(20.dp)
                                            )
                                        }
                                    }
                                    Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
                                        Text(
                                            text = tower.towerName,
                                            style = MaterialTheme.typography.titleMedium.copy(
                                                fontWeight = FontWeight.Bold,
                                                color = Color(0xFFF8FAFC),
                                                fontSize = 16.sp
                                            ),
                                            modifier = Modifier.testTag("tower_name_${tower.id}")
                                        )
                                        Row(
                                            verticalAlignment = Alignment.CenterVertically,
                                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                                        ) {
                                            Icon(
                                                imageVector = Icons.Outlined.LocationOn,
                                                contentDescription = null,
                                                tint = Color(0xFF38BDF8),
                                                modifier = Modifier.size(14.dp)
                                            )
                                            Text(
                                                text = tower.location,
                                                style = MaterialTheme.typography.bodySmall.copy(
                                                    color = Color(0xFF94A3B8),
                                                    fontSize = 12.sp
                                                ),
                                                modifier = Modifier.testTag("tower_location_${tower.id}")
                                            )
                                        }
                                    }
                                }

                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(1.dp)
                                        .background(Color(0x24FFFFFF))
                                )

                                // Packages for this tower
                                tower.packages.forEachIndexed { pkgIndex, pkg ->
                                    val packageTag = "pkg_${tower.id}_${if (pkg.isEncrypted) "encrypted" else "open"}"
                                    Card(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .testTag(packageTag),
                                        shape = RoundedCornerShape(14.dp),
                                        colors = CardDefaults.cardColors(containerColor = Color(0x260F172A)),
                                        border = BorderStroke(1.dp, Color(0x2638BDF8))
                                    ) {
                                        Column(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(14.dp),
                                            verticalArrangement = Arrangement.spacedBy(10.dp)
                                        ) {
                                            Row(
                                                modifier = Modifier.fillMaxWidth(),
                                                horizontalArrangement = Arrangement.SpaceBetween,
                                                verticalAlignment = Alignment.CenterVertically
                                            ) {
                                                Text(
                                                    text = pkg.packageName,
                                                    style = MaterialTheme.typography.titleSmall.copy(
                                                        fontWeight = FontWeight.Bold,
                                                        color = Color(0xFFF1F5F9),
                                                        fontSize = 14.sp
                                                    )
                                                )
                                                Surface(
                                                    shape = RoundedCornerShape(8.dp),
                                                    color = if (pkg.isEncrypted) Color(0x33EF4444) else Color(0x3310B981)
                                                ) {
                                                    Row(
                                                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                                                        verticalAlignment = Alignment.CenterVertically,
                                                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                                                    ) {
                                                        Icon(
                                                            imageVector = if (pkg.isEncrypted) Icons.Outlined.Lock else Icons.Outlined.LockOpen,
                                                            contentDescription = null,
                                                            tint = if (pkg.isEncrypted) Color(0xFFFCA5A5) else Color(0xFF6EE7B7),
                                                            modifier = Modifier.size(12.dp)
                                                        )
                                                        Text(
                                                            text = if (pkg.isEncrypted) "مشفرة" else "مفتوحة",
                                                            style = MaterialTheme.typography.labelSmall.copy(
                                                                color = if (pkg.isEncrypted) Color(0xFFFCA5A5) else Color(0xFF6EE7B7),
                                                                fontWeight = FontWeight.Bold,
                                                                fontSize = 11.sp
                                                            )
                                                        )
                                                    }
                                                }
                                            }

                                            Box(
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .height(1.dp)
                                                    .background(Color(0x14FFFFFF))
                                            )

                                            Row(
                                                modifier = Modifier.fillMaxWidth(),
                                                horizontalArrangement = Arrangement.SpaceBetween
                                            ) {
                                                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                                                    Text(
                                                        text = "التردد",
                                                        style = MaterialTheme.typography.labelSmall.copy(
                                                            color = Color(0xFF94A3B8),
                                                            fontSize = 11.sp
                                                        )
                                                    )
                                                    Text(
                                                        text = pkg.frequency,
                                                        style = MaterialTheme.typography.bodyLarge.copy(
                                                            fontWeight = FontWeight.Bold,
                                                            color = Color(0xFF38BDF8),
                                                            fontSize = 16.sp
                                                        )
                                                    )
                                                }
                                                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                                                    Text(
                                                        text = "الاستقطاب",
                                                        style = MaterialTheme.typography.labelSmall.copy(
                                                            color = Color(0xFF94A3B8),
                                                            fontSize = 11.sp
                                                        )
                                                    )
                                                    Text(
                                                        text = pkg.polarization,
                                                        style = MaterialTheme.typography.bodyMedium.copy(
                                                            fontWeight = FontWeight.Bold,
                                                            color = Color(0xFF34D399),
                                                            fontSize = 14.sp
                                                        )
                                                    )
                                                }
                                                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                                                    Text(
                                                        text = "معدل الترميز",
                                                        style = MaterialTheme.typography.labelSmall.copy(
                                                            color = Color(0xFF94A3B8),
                                                            fontSize = 11.sp
                                                        )
                                                    )
                                                    Text(
                                                        text = pkg.symbolRate,
                                                        style = MaterialTheme.typography.bodyMedium.copy(
                                                            fontWeight = FontWeight.Bold,
                                                            color = Color(0xFFF1F5F9),
                                                            fontSize = 14.sp
                                                        )
                                                    )
                                                }
                                            }
                                        }
                                    }
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
