package com.alasalah.digitalgateway

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

sealed interface Screen {
    data object Home : Screen
    data object Channels : Screen
    data object Media : Screen
    data object Services : Screen
    data object Subscription : Screen
    data object Account : Screen
    data object Renewal : Screen
    data object Transactions : Screen
    data object Notifications : Screen
    data object Coverage : Screen
    data object Frequencies : Screen
    data object ReceptionGuide : Screen
    data object ReceiverCardInfo : Screen
    data object Support : Screen
    data object Faq : Screen
}

enum class BottomNavItem(
    val title: String,
    val icon: ImageVector,
    val screen: Screen
) {
    HOME("الرئيسية", Icons.Default.Home, Screen.Home),
    SERVICES("الخدمات", Icons.Default.List, Screen.Services),
    MEDIA("الإعلام", Icons.Default.Tv, Screen.Media),
    ACCOUNT("حسابي", Icons.Default.Person, Screen.Account)
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainApp()
        }
    }
}

@Composable
fun MainApp() {
    var currentScreen by remember { mutableStateOf<Screen>(Screen.Home) }

    Scaffold(
        bottomBar = {
            NavigationBar(
                containerColor = Color(0xFF1B3D4F),
                contentColor = Color.White
            ) {
                BottomNavItem.entries.forEach { item ->
                    val selected = currentScreen == item.screen
                    NavigationBarItem(
                        selected = selected,
                        onClick = {
                            currentScreen = item.screen
                        },
                        icon = { Icon(item.icon, contentDescription = item.title) },
                        label = { Text(item.title) },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = Color(0xFF38BDF8),
                            selectedTextColor = Color(0xFF38BDF8),
                            unselectedIconColor = Color(0xFF94A3B8),
                            unselectedTextColor = Color(0xFF94A3B8),
                            indicatorColor = Color(0xFF174758)
                        )
                    )
                }
            }
        }
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            color = Color(0xFF1B3D4F)
        ) {
            when (currentScreen) {
                is Screen.Home -> {
                    HomeScreen(
                        onNavigateToChannels = { currentScreen = Screen.Channels },
                        onNavigateToSubscription = { currentScreen = Screen.Subscription },
                        onNavigateToRenewal = { currentScreen = Screen.Renewal },
                        onNavigateToTransactions = { currentScreen = Screen.Transactions },
                        onNavigateToNotifications = { currentScreen = Screen.Notifications },
                        onNavigateToAccount = { currentScreen = Screen.Account },
                        onNavigateToCoverage = { currentScreen = Screen.Coverage },
                        onNavigateToFrequencies = { currentScreen = Screen.Frequencies },
                        onNavigateToReceptionGuide = { currentScreen = Screen.ReceptionGuide },
                        onNavigateToReceiverCardInfo = { currentScreen = Screen.ReceiverCardInfo },
                        onNavigateToSupport = { currentScreen = Screen.Support },
                        onNavigateToFaq = { currentScreen = Screen.Faq },
                        onNavigateToMedia = { currentScreen = Screen.Media }
                    )
                }
                is Screen.Services -> {
                    ServicesScreen(
                        onMySubscription = {
                            currentScreen = Screen.Subscription
                        },
                        onRenewal = {
                            currentScreen = Screen.Renewal
                        },
                        onTransactions = {
                            currentScreen = Screen.Transactions
                        },
                        onFrequencies = {
                            currentScreen = Screen.Frequencies
                        },
                        onCoverage = {
                            currentScreen = Screen.Coverage
                        },
                        onSupport = {
                            currentScreen = Screen.Support
                        },
                        onBack = {
                            currentScreen = Screen.Home
                        }
                    )
                }
                is Screen.Channels -> {
                    ChannelsScreen(
                        onBack = { currentScreen = Screen.Home }
                    )
                }
                is Screen.Media -> {
                    MediaScreen(
                        onBack = { currentScreen = Screen.Home }
                    )
                }
                is Screen.Subscription -> {
                    MySubscriptionScreen(
                        onBack = { currentScreen = Screen.Home },
                        onRenewClick = { currentScreen = Screen.Renewal }
                    )
                }
                is Screen.Account -> {
                    AccountScreen(
                        onBack = { currentScreen = Screen.Home }
                    )
                }
                is Screen.Renewal -> {
                    RenewalScreen(
                        onBack = { currentScreen = Screen.Home }
                    )
                }
                is Screen.Transactions -> {
                    TransactionsScreen(
                        onBack = { currentScreen = Screen.Home }
                    )
                }
                is Screen.Notifications -> {
                    NotificationsScreen(
                        onBack = { currentScreen = Screen.Home }
                    )
                }
                is Screen.Coverage -> {
                    CoverageScreen(
                        onBack = { currentScreen = Screen.Home }
                    )
                }
                is Screen.Frequencies -> {
                    FrequenciesScreen(
                        onBack = { currentScreen = Screen.Home }
                    )
                }
                is Screen.ReceptionGuide -> {
                    ReceptionGuideScreen(
                        onBack = { currentScreen = Screen.Home }
                    )
                }
                is Screen.ReceiverCardInfo -> {
                    ReceiverCardInfoScreen(
                        onBack = { currentScreen = Screen.Home }
                    )
                }
                is Screen.Support -> {
                    SupportScreen(
                        onBack = { currentScreen = Screen.Home }
                    )
                }
                is Screen.Faq -> {
                    FaqScreen(
                        onBack = { currentScreen = Screen.Home }
                    )
                }
            }
        }
    }
}
