package com.alasalah.digitalgateway

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import com.alasalah.digitalgateway.ui.theme.MyApplicationTheme

sealed interface Screen {
    data object Home : Screen
    data object MySubscription : Screen
    data object Renewal : Screen
    data class PaymentMethod(val planId: String) : Screen
    data class PaymentInstructions(val planId: String, val methodId: String) : Screen
    data class PaymentProof(val planId: String, val methodId: String) : Screen
    data class RenewalConfirmation(val planId: String, val methodId: String) : Screen
    data class Transactions(val planId: String = "plan_1_month", val methodId: String = "kuraimi") : Screen
    data object Account : Screen
    data class TransactionDetails(val transaction: TransactionRecord = TransactionRecord()) : Screen
    data object Notifications : Screen
    data object SubscriptionDetails : Screen
    data object Channels : Screen
    data class PackageDetails(val packageInfo: PackageDetailInfo = PackageDetailRepository.encryptedPackage) : Screen
    data object ReceptionGuide : Screen
    data object Frequencies : Screen
    data object Coverage : Screen
    data object ReceiverCardInfo : Screen
    data object Support : Screen
    data object SupportTicket : Screen
    data object Faq : Screen
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
                MyApplicationTheme {
                    var currentScreen by remember { mutableStateOf<Screen>(Screen.Home) }
                    var renewalOrigin by remember { mutableStateOf<Screen>(Screen.MySubscription) }
                    var transactionsOrigin by remember { mutableStateOf<Screen>(Screen.Home) }
                    var lastRenewalPlanId by remember { mutableStateOf("plan_1_month") }
                    var lastPaymentMethodId by remember { mutableStateOf("kuraimi") }

                    when (currentScreen) {
                        is Screen.Home -> {
                            HomeScreen(
                                onNavigateToSubscription = {
                                    currentScreen = Screen.MySubscription
                                },
                                onNavigateToRenewal = {
                                    renewalOrigin = Screen.Home
                                    currentScreen = Screen.Renewal
                                },
                                onNavigateToTransactions = {
                                    transactionsOrigin = Screen.Home
                                    currentScreen = Screen.Transactions(lastRenewalPlanId, lastPaymentMethodId)
                                },
                                onNavigateToAccount = {
                                    currentScreen = Screen.Account
                                },
                                onNavigateToNotifications = {
                                    currentScreen = Screen.Notifications
                                },
                                onNavigateToChannels = {
                                    currentScreen = Screen.Channels
                                },
                                onNavigateToReceptionGuide = {
                                    currentScreen = Screen.ReceptionGuide
                                },
                                onNavigateToFrequencies = {
                                    currentScreen = Screen.Frequencies
                                },
                                onNavigateToCoverage = {
                                    currentScreen = Screen.Coverage
                                },
                                onNavigateToReceiverCardInfo = {
                                    currentScreen = Screen.ReceiverCardInfo
                                },
                                onNavigateToSupport = {
                                    currentScreen = Screen.Support
                                },
                                onNavigateToFaq = {
                                    currentScreen = Screen.Faq
                                }
                            )
                        }
                        is Screen.MySubscription -> {
                            MySubscriptionScreen(
                                onBackClick = {
                                    currentScreen = Screen.Home
                                },
                                onNavigateToRenewal = {
                                    renewalOrigin = Screen.MySubscription
                                    currentScreen = Screen.Renewal
                                }
                            )
                        }
                        is Screen.Renewal -> {
                            RenewalScreen(
                                onBackClick = {
                                    currentScreen = renewalOrigin
                                },
                                onNavigateToPaymentMethod = { planId ->
                                    currentScreen = Screen.PaymentMethod(planId)
                                }
                            )
                        }
                        is Screen.PaymentMethod -> {
                            val methodScreen = currentScreen as Screen.PaymentMethod
                            lastRenewalPlanId = methodScreen.planId
                            PaymentMethodScreen(
                                planId = methodScreen.planId,
                                onBackClick = {
                                    currentScreen = Screen.Renewal
                                },
                                onNavigateToInstructions = { planId, methodId ->
                                    lastRenewalPlanId = planId
                                    lastPaymentMethodId = methodId
                                    currentScreen = Screen.PaymentInstructions(planId, methodId)
                                }
                            )
                        }
                        is Screen.PaymentInstructions -> {
                            val instructionsScreen = currentScreen as Screen.PaymentInstructions
                            lastRenewalPlanId = instructionsScreen.planId
                            lastPaymentMethodId = instructionsScreen.methodId
                            PaymentInstructionsScreen(
                                planId = instructionsScreen.planId,
                                methodId = instructionsScreen.methodId,
                                onBackClick = {
                                    currentScreen = Screen.PaymentMethod(instructionsScreen.planId)
                                },
                                onNavigateToProof = { planId, methodId ->
                                    lastRenewalPlanId = planId
                                    lastPaymentMethodId = methodId
                                    currentScreen = Screen.PaymentProof(planId, methodId)
                                }
                            )
                        }
                        is Screen.PaymentProof -> {
                            val proofScreen = currentScreen as Screen.PaymentProof
                            lastRenewalPlanId = proofScreen.planId
                            lastPaymentMethodId = proofScreen.methodId
                            PaymentProofScreen(
                                planId = proofScreen.planId,
                                methodId = proofScreen.methodId,
                                onBackClick = {
                                    currentScreen = Screen.PaymentInstructions(proofScreen.planId, proofScreen.methodId)
                                },
                                onNavigateToConfirmation = { planId, methodId ->
                                    lastRenewalPlanId = planId
                                    lastPaymentMethodId = methodId
                                    currentScreen = Screen.RenewalConfirmation(planId, methodId)
                                }
                            )
                        }
                        is Screen.RenewalConfirmation -> {
                            val confirmationScreen = currentScreen as Screen.RenewalConfirmation
                            lastRenewalPlanId = confirmationScreen.planId
                            lastPaymentMethodId = confirmationScreen.methodId
                            RenewalRequestConfirmationScreen(
                                planId = confirmationScreen.planId,
                                methodId = confirmationScreen.methodId,
                                onReturnHome = {
                                    currentScreen = Screen.Home
                                }
                            )
                        }
                        is Screen.Transactions -> {
                            val txScreen = currentScreen as Screen.Transactions
                            TransactionsScreen(
                                planId = txScreen.planId,
                                methodId = txScreen.methodId,
                                onBackClick = {
                                    currentScreen = transactionsOrigin
                                },
                                onTransactionClick = { transaction ->
                                    currentScreen = Screen.TransactionDetails(transaction)
                                }
                            )
                        }
                        is Screen.Account -> {
                            AccountScreen(
                                onBackClick = {
                                    currentScreen = Screen.Home
                                },
                                onNavigateToSubscription = {
                                    currentScreen = Screen.MySubscription
                                },
                                onNavigateToSubscriptionDetails = {
                                    currentScreen = Screen.SubscriptionDetails
                                },
                                onNavigateToTransactions = {
                                    transactionsOrigin = Screen.Account
                                    currentScreen = Screen.Transactions(lastRenewalPlanId, lastPaymentMethodId)
                                }
                            )
                        }
                        is Screen.SubscriptionDetails -> {
                            SubscriptionDetailsScreen(
                                onBackClick = {
                                    currentScreen = Screen.Account
                                },
                                onNavigateToRenewal = {
                                    renewalOrigin = Screen.SubscriptionDetails
                                    currentScreen = Screen.Renewal
                                }
                            )
                        }
                        is Screen.TransactionDetails -> {
                            val detailsScreen = currentScreen as Screen.TransactionDetails
                            TransactionDetailsScreen(
                                transaction = detailsScreen.transaction,
                                onBackClick = {
                                    currentScreen = Screen.Transactions(lastRenewalPlanId, lastPaymentMethodId)
                                }
                            )
                        }
                        is Screen.Notifications -> {
                            NotificationsScreen(
                                onBackClick = {
                                    currentScreen = Screen.Home
                                }
                            )
                        }
                        is Screen.Channels -> {
                            ChannelsScreen(
                                onBackClick = {
                                    currentScreen = Screen.Home
                                },
                                onPackageClick = { pkg ->
                                    currentScreen = Screen.PackageDetails(
                                        PackageDetailRepository.getPackageById(pkg.id)
                                    )
                                }
                            )
                        }
                        is Screen.PackageDetails -> {
                            val detailsScreen = currentScreen as Screen.PackageDetails
                            PackageDetailsScreen(
                                packageInfo = detailsScreen.packageInfo,
                                onBackClick = {
                                    currentScreen = Screen.Channels
                                }
                            )
                        }
                        is Screen.ReceptionGuide -> {
                            ReceptionGuideScreen(
                                onBackClick = {
                                    currentScreen = Screen.Home
                                }
                            )
                        }
                        is Screen.Frequencies -> {
                            FrequenciesScreen(
                                onBackClick = {
                                    currentScreen = Screen.Home
                                }
                            )
                        }
                        is Screen.Coverage -> {
                            CoverageScreen(
                                onBackClick = {
                                    currentScreen = Screen.Home
                                }
                            )
                        }
                        is Screen.ReceiverCardInfo -> {
                            ReceiverCardInfoScreen(
                                onBackClick = {
                                    currentScreen = Screen.Home
                                }
                            )
                        }
                        is Screen.Support -> {
                            SupportScreen(
                                onOpenTicket = {
                                    currentScreen = Screen.SupportTicket
                                },
                                onOpenFaq = {
                                    currentScreen = Screen.Faq
                                },
                                onBack = {
                                    currentScreen = Screen.Home
                                }
                            )
                        }
                        is Screen.SupportTicket -> {
                            SupportTicketScreen(
                                onSubmitTicket = { _, _, _ -> },
                                onBack = {
                                    currentScreen = Screen.Support
                                }
                            )
                        }
                        is Screen.Faq -> {
                            FaqScreen(
                                onBack = {
                                    currentScreen = Screen.Support
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

