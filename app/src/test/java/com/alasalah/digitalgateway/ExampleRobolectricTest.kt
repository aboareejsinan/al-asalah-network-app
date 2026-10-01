package com.alasalah.digitalgateway

import android.content.Context
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollTo
import androidx.compose.ui.unit.LayoutDirection
import androidx.test.core.app.ApplicationProvider
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [36])
class ExampleRobolectricTest {

  @get:Rule
  val composeTestRule = createComposeRule()

  @Test
  fun `read string from context`() {
    val context = ApplicationProvider.getApplicationContext<Context>()
    val appName = context.getString(R.string.app_name)
    assertEquals("شبكة الأصالة", appName)
  }

  @Test
  fun `navigating to subscription screen and returning to home screen`() {
    composeTestRule.setContent {
      CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        var currentScreen by remember { mutableStateOf<Screen>(Screen.Home) }
        when (currentScreen) {
          is Screen.Home -> {
            HomeScreen(
              onNavigateToSubscription = {
                currentScreen = Screen.MySubscription
              }
            )
          }
          is Screen.MySubscription -> {
            MySubscriptionScreen(
              onBackClick = {
                currentScreen = Screen.Home
              }
            )
          }
          is Screen.Renewal -> {
            RenewalScreen(
              onBackClick = {
                currentScreen = Screen.MySubscription
              }
            )
          }
          else -> {}
        }
      }
    }

    // Verify Home screen is displayed
    composeTestRule.onNodeWithTag("home_screen_scaffold").assertExists()
    composeTestRule.onNodeWithTag("service_card_اشتراكي").assertExists()

    // Tap "اشتراكي"
    composeTestRule.onNodeWithTag("service_card_اشتراكي").performScrollTo().performClick()

    // Verify MySubscriptionScreen is displayed
    composeTestRule.onNodeWithTag("my_subscription_scaffold").assertExists()
    composeTestRule.onNodeWithTag("subscription_screen_title").assertExists()

    // Tap back button
    composeTestRule.onNodeWithTag("subscription_back_button").performClick()

    // Verify Home screen is displayed again
    composeTestRule.onNodeWithTag("home_screen_scaffold").assertExists()
  }

  @Test
  fun `navigating to renewal screen from subscription and returning`() {
    composeTestRule.setContent {
      CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        var currentScreen by remember { mutableStateOf<Screen>(Screen.MySubscription) }
        when (currentScreen) {
          is Screen.Home -> {
            HomeScreen()
          }
          is Screen.MySubscription -> {
            MySubscriptionScreen(
              onBackClick = {
                currentScreen = Screen.Home
              },
              onNavigateToRenewal = {
                currentScreen = Screen.Renewal
              }
            )
          }
          is Screen.Renewal -> {
            RenewalScreen(
              onBackClick = {
                currentScreen = Screen.MySubscription
              },
              onNavigateToPaymentMethod = { planId ->
                currentScreen = Screen.PaymentMethod(planId)
              }
            )
          }
          is Screen.PaymentMethod -> {
            PaymentMethodScreen(
              planId = (currentScreen as Screen.PaymentMethod).planId,
              onBackClick = {
                currentScreen = Screen.Renewal
              }
            )
          }
          else -> {}
        }
      }
    }

    // Verify MySubscriptionScreen is displayed
    composeTestRule.onNodeWithTag("my_subscription_scaffold").assertExists()
    composeTestRule.onNodeWithTag("renew_subscription_button").assertExists()

    // Tap "تجديد الاشتراك"
    composeTestRule.onNodeWithTag("renew_subscription_button").performScrollTo().performClick()

    // Verify RenewalScreen is displayed
    composeTestRule.onNodeWithTag("renewal_screen_scaffold").assertExists()
    composeTestRule.onNodeWithTag("renewal_screen_title").assertExists()
    composeTestRule.onNodeWithTag("renewal_continue_button").assertExists()

    // Verify plan cards exist
    composeTestRule.onNodeWithTag("renewal_plan_card_plan_1_month").assertExists()

    // Tap back button
    composeTestRule.onNodeWithTag("renewal_back_button").performClick()

    // Verify MySubscriptionScreen is displayed again
    composeTestRule.onNodeWithTag("my_subscription_scaffold").assertExists()
  }

  @Test
  fun `navigating to payment method screen and returning`() {
    composeTestRule.setContent {
      CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        var currentScreen by remember { mutableStateOf<Screen>(Screen.Renewal) }
        when (currentScreen) {
          is Screen.Home -> {
            HomeScreen()
          }
          is Screen.MySubscription -> {
            MySubscriptionScreen()
          }
          is Screen.Renewal -> {
            RenewalScreen(
              onBackClick = {
                currentScreen = Screen.MySubscription
              },
              onNavigateToPaymentMethod = { planId ->
                currentScreen = Screen.PaymentMethod(planId)
              }
            )
          }
          is Screen.PaymentMethod -> {
            PaymentMethodScreen(
              planId = (currentScreen as Screen.PaymentMethod).planId,
              onBackClick = {
                currentScreen = Screen.Renewal
              },
              onNavigateToInstructions = { planId, methodId ->
                currentScreen = Screen.PaymentInstructions(planId, methodId)
              }
            )
          }
          else -> {}
        }
      }
    }

    // Verify RenewalScreen is displayed
    composeTestRule.onNodeWithTag("renewal_screen_scaffold").assertExists()
    composeTestRule.onNodeWithTag("renewal_continue_button").assertExists()

    // Tap "متابعة"
    composeTestRule.onNodeWithTag("renewal_continue_button").performClick()

    // Verify PaymentMethodScreen is displayed
    composeTestRule.onNodeWithTag("payment_method_screen_scaffold").assertExists()
    composeTestRule.onNodeWithTag("payment_method_screen_title").assertExists()
    composeTestRule.onNodeWithTag("payment_method_plan_summary_card").assertExists()
    composeTestRule.onNodeWithTag("summary_package_name").assertExists()
    composeTestRule.onNodeWithTag("summary_smart_card").assertExists()
    composeTestRule.onNodeWithTag("summary_plan_duration").assertExists()
    composeTestRule.onNodeWithTag("summary_plan_price").assertExists()
    composeTestRule.onNodeWithTag("payment_method_continue_button").assertExists()

    // Verify payment methods exist
    composeTestRule.onNodeWithTag("payment_method_card_kuraimi").assertExists()
    composeTestRule.onNodeWithTag("payment_method_card_unified_transfer_network").assertExists()

    // Tap back button
    composeTestRule.onNodeWithTag("payment_method_back_button").performClick()

    // Verify RenewalScreen is displayed again
    composeTestRule.onNodeWithTag("renewal_screen_scaffold").assertExists()
  }

  @Test
  fun `navigating to payment instructions screen and returning`() {
    composeTestRule.setContent {
      CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        var currentScreen by remember { mutableStateOf<Screen>(Screen.PaymentMethod("plan_3_months")) }
        when (currentScreen) {
          is Screen.Home -> {
            HomeScreen()
          }
          is Screen.MySubscription -> {
            MySubscriptionScreen()
          }
          is Screen.Renewal -> {
            RenewalScreen()
          }
          is Screen.PaymentMethod -> {
            val methodScreen = currentScreen as Screen.PaymentMethod
            PaymentMethodScreen(
              planId = methodScreen.planId,
              onBackClick = {
                currentScreen = Screen.Renewal
              },
              onNavigateToInstructions = { planId, methodId ->
                currentScreen = Screen.PaymentInstructions(planId, methodId)
              }
            )
          }
          is Screen.PaymentInstructions -> {
            val instructionsScreen = currentScreen as Screen.PaymentInstructions
            PaymentInstructionsScreen(
              planId = instructionsScreen.planId,
              methodId = instructionsScreen.methodId,
              onBackClick = {
                currentScreen = Screen.PaymentMethod(instructionsScreen.planId)
              },
              onNavigateToProof = { planId, methodId ->
                currentScreen = Screen.PaymentProof(planId, methodId)
              }
            )
          }
          else -> {}
        }
      }
    }

    // Verify PaymentMethodScreen is displayed
    composeTestRule.onNodeWithTag("payment_method_screen_scaffold").assertExists()
    composeTestRule.onNodeWithTag("payment_method_continue_button").assertExists()

    // Tap "متابعة الدفع"
    composeTestRule.onNodeWithTag("payment_method_continue_button").performClick()

    // Verify PaymentInstructionsScreen is displayed
    composeTestRule.onNodeWithTag("payment_instructions_scaffold").assertExists()
    composeTestRule.onNodeWithTag("payment_instructions_title").assertExists()
    composeTestRule.onNodeWithTag("instructions_summary_card").assertExists()
    composeTestRule.onNodeWithTag("instructions_package_name").assertExists()
    composeTestRule.onNodeWithTag("instructions_smart_card").assertExists()
    composeTestRule.onNodeWithTag("instructions_duration").assertExists()
    composeTestRule.onNodeWithTag("instructions_provider_name").assertExists()

    // Destination card & amount
    composeTestRule.onNodeWithTag("payment_destination_card").assertExists()
    composeTestRule.onNodeWithTag("instructions_amount_value").assertExists()

    // Info card & button
    composeTestRule.onNodeWithTag("after_transfer_info_card").assertExists()
    composeTestRule.onNodeWithTag("send_payment_proof_button").assertExists()

    // Tap back button
    composeTestRule.onNodeWithTag("payment_instructions_back_button").performClick()

    // Verify PaymentMethodScreen is displayed again
    composeTestRule.onNodeWithTag("payment_method_screen_scaffold").assertExists()
  }

  @Test
  fun `navigating to payment proof screen and returning`() {
    composeTestRule.setContent {
      CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        var currentScreen by remember {
          mutableStateOf<Screen>(Screen.PaymentInstructions("plan_6_months", "kuraimi"))
        }
        when (currentScreen) {
          is Screen.Home -> {
            HomeScreen()
          }
          is Screen.MySubscription -> {
            MySubscriptionScreen()
          }
          is Screen.Renewal -> {
            RenewalScreen()
          }
          is Screen.PaymentMethod -> {
            PaymentMethodScreen()
          }
          is Screen.PaymentInstructions -> {
            val instructionsScreen = currentScreen as Screen.PaymentInstructions
            PaymentInstructionsScreen(
              planId = instructionsScreen.planId,
              methodId = instructionsScreen.methodId,
              onBackClick = {
                currentScreen = Screen.PaymentMethod(instructionsScreen.planId)
              },
              onNavigateToProof = { planId, methodId ->
                currentScreen = Screen.PaymentProof(planId, methodId)
              }
            )
          }
          is Screen.PaymentProof -> {
            val proofScreen = currentScreen as Screen.PaymentProof
            PaymentProofScreen(
              planId = proofScreen.planId,
              methodId = proofScreen.methodId,
              onBackClick = {
                currentScreen = Screen.PaymentInstructions(proofScreen.planId, proofScreen.methodId)
              },
              onNavigateToConfirmation = { planId, methodId ->
                currentScreen = Screen.RenewalConfirmation(planId, methodId)
              }
            )
          }
          else -> {}
        }
      }
    }

    // Verify PaymentInstructionsScreen is displayed
    composeTestRule.onNodeWithTag("payment_instructions_scaffold").assertExists()
    composeTestRule.onNodeWithTag("send_payment_proof_button").assertExists()

    // Tap "إرسال إثبات الدفع"
    composeTestRule.onNodeWithTag("send_payment_proof_button").performClick()

    // Verify PaymentProofScreen is displayed
    composeTestRule.onNodeWithTag("payment_proof_scaffold").assertExists()
    composeTestRule.onNodeWithTag("payment_proof_title").assertExists()
    composeTestRule.onNodeWithTag("payment_proof_summary_card").assertExists()
    composeTestRule.onNodeWithTag("proof_summary_smart_card").assertExists()
    composeTestRule.onNodeWithTag("proof_summary_duration").assertExists()
    composeTestRule.onNodeWithTag("proof_summary_amount").assertExists()
    composeTestRule.onNodeWithTag("proof_summary_provider").assertExists()

    // Input areas & cards
    composeTestRule.onNodeWithTag("transfer_reference_input").assertExists()
    composeTestRule.onNodeWithTag("proof_image_card").assertExists()
    composeTestRule.onNodeWithTag("attach_receipt_button").assertExists()
    composeTestRule.onNodeWithTag("optional_note_input").assertExists()
    composeTestRule.onNodeWithTag("review_info_card").assertExists()
    composeTestRule.onNodeWithTag("submit_proof_button").assertExists()

    // Tap back button
    composeTestRule.onNodeWithTag("payment_proof_back_button").performClick()

    // Verify PaymentInstructionsScreen is displayed again
    composeTestRule.onNodeWithTag("payment_instructions_scaffold").assertExists()
  }

  @Test
  fun `navigating to renewal confirmation screen and returning home`() {
    composeTestRule.setContent {
      CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        var currentScreen by remember {
          mutableStateOf<Screen>(Screen.PaymentProof("plan_12_months", "kuraimi"))
        }
        when (currentScreen) {
          is Screen.Home -> {
            HomeScreen()
          }
          is Screen.MySubscription -> {
            MySubscriptionScreen()
          }
          is Screen.Renewal -> {
            RenewalScreen()
          }
          is Screen.PaymentMethod -> {
            PaymentMethodScreen()
          }
          is Screen.PaymentInstructions -> {
            PaymentInstructionsScreen()
          }
          is Screen.PaymentProof -> {
            val proofScreen = currentScreen as Screen.PaymentProof
            PaymentProofScreen(
              planId = proofScreen.planId,
              methodId = proofScreen.methodId,
              onNavigateToConfirmation = { planId, methodId ->
                currentScreen = Screen.RenewalConfirmation(planId, methodId)
              }
            )
          }
          is Screen.RenewalConfirmation -> {
            val confirmationScreen = currentScreen as Screen.RenewalConfirmation
            RenewalRequestConfirmationScreen(
              planId = confirmationScreen.planId,
              methodId = confirmationScreen.methodId,
              onReturnHome = {
                currentScreen = Screen.Home
              }
            )
          }
          else -> {}
        }
      }
    }

    // Verify PaymentProofScreen is displayed
    composeTestRule.onNodeWithTag("payment_proof_scaffold").assertExists()
    composeTestRule.onNodeWithTag("submit_proof_button").assertExists()

    // Tap "إرسال الطلب"
    composeTestRule.onNodeWithTag("submit_proof_button").performClick()

    // Verify RenewalRequestConfirmationScreen is displayed
    composeTestRule.onNodeWithTag("confirmation_screen_scaffold").assertExists()
    composeTestRule.onNodeWithTag("confirmation_screen_title").assertExists()
    composeTestRule.onNodeWithTag("confirmation_success_icon").assertExists()
    composeTestRule.onNodeWithTag("confirmation_main_message").assertExists()
    composeTestRule.onNodeWithTag("confirmation_secondary_message").assertExists()
    composeTestRule.onNodeWithTag("confirmation_request_number").assertExists()
    composeTestRule.onNodeWithTag("confirmation_status_badge").assertExists()

    // Verify summary card details
    composeTestRule.onNodeWithTag("confirmation_summary_card").assertExists()
    composeTestRule.onNodeWithTag("confirmation_smart_card").assertExists()
    composeTestRule.onNodeWithTag("confirmation_duration").assertExists()
    composeTestRule.onNodeWithTag("confirmation_amount").assertExists()
    composeTestRule.onNodeWithTag("confirmation_provider").assertExists()

    // Verify Return to Home button exists and click it
    composeTestRule.onNodeWithTag("confirmation_return_home_button").assertExists()
    composeTestRule.onNodeWithTag("confirmation_return_home_button").performClick()

    // Verify HomeScreen is displayed
    composeTestRule.onNodeWithTag("home_screen_scaffold").assertExists()
  }

  @Test
  fun `navigating to renewal from home subscription card button and returning to home`() {
    composeTestRule.setContent {
      CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        var currentScreen by remember { mutableStateOf<Screen>(Screen.Home) }
        var renewalOrigin by remember { mutableStateOf<Screen>(Screen.Home) }

        when (currentScreen) {
          is Screen.Home -> {
            HomeScreen(
              onNavigateToSubscription = {
                currentScreen = Screen.MySubscription
              },
              onNavigateToRenewal = {
                renewalOrigin = Screen.Home
                currentScreen = Screen.Renewal
              }
            )
          }
          is Screen.MySubscription -> {
            MySubscriptionScreen()
          }
          is Screen.Renewal -> {
            RenewalScreen(
              onBackClick = {
                currentScreen = renewalOrigin
              }
            )
          }
          else -> {}
        }
      }
    }

    // Verify HomeScreen is displayed
    composeTestRule.onNodeWithTag("home_screen_scaffold").assertExists()
    composeTestRule.onNodeWithTag("renew_now_button").assertExists()

    // Tap "تجديد الآن" on main subscription card
    composeTestRule.onNodeWithTag("renew_now_button").performClick()

    // Verify RenewalScreen is displayed
    composeTestRule.onNodeWithTag("renewal_screen_scaffold").assertExists()
    composeTestRule.onNodeWithTag("renewal_screen_title").assertExists()

    // Tap back button
    composeTestRule.onNodeWithTag("renewal_back_button").performClick()

    // Verify returned to HomeScreen
    composeTestRule.onNodeWithTag("home_screen_scaffold").assertExists()
  }

  @Test
  fun `navigating to renewal from home quick services and returning to home`() {
    composeTestRule.setContent {
      CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        var currentScreen by remember { mutableStateOf<Screen>(Screen.Home) }
        var renewalOrigin by remember { mutableStateOf<Screen>(Screen.Home) }

        when (currentScreen) {
          is Screen.Home -> {
            HomeScreen(
              onNavigateToSubscription = {
                currentScreen = Screen.MySubscription
              },
              onNavigateToRenewal = {
                renewalOrigin = Screen.Home
                currentScreen = Screen.Renewal
              }
            )
          }
          is Screen.MySubscription -> {
            MySubscriptionScreen()
          }
          is Screen.Renewal -> {
            RenewalScreen(
              onBackClick = {
                currentScreen = renewalOrigin
              }
            )
          }
          else -> {}
        }
      }
    }

    // Verify HomeScreen is displayed
    composeTestRule.onNodeWithTag("home_screen_scaffold").assertExists()
    composeTestRule.onNodeWithTag("service_card_التجديد").assertExists()

    // Tap "التجديد" quick service card
    composeTestRule.onNodeWithTag("service_card_التجديد").performScrollTo().performClick()

    // Verify RenewalScreen is displayed
    composeTestRule.onNodeWithTag("renewal_screen_scaffold").assertExists()
    composeTestRule.onNodeWithTag("renewal_screen_title").assertExists()

    // Tap back button
    composeTestRule.onNodeWithTag("renewal_back_button").performClick()

    // Verify returned to HomeScreen
    composeTestRule.onNodeWithTag("home_screen_scaffold").assertExists()
  }

  @Test
  fun `navigating to transactions screen from home quick services and returning to home`() {
    composeTestRule.setContent {
      CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        var currentScreen by remember { mutableStateOf<Screen>(Screen.Home) }

        when (currentScreen) {
          is Screen.Home -> {
            HomeScreen(
              onNavigateToTransactions = {
                currentScreen = Screen.Transactions("plan_6_months", "kuraimi")
              }
            )
          }
          is Screen.MySubscription -> {}
          is Screen.Renewal -> {}
          is Screen.PaymentMethod -> {}
          is Screen.PaymentInstructions -> {}
          is Screen.PaymentProof -> {}
          is Screen.RenewalConfirmation -> {}
          is Screen.Transactions -> {
            val txScreen = currentScreen as Screen.Transactions
            TransactionsScreen(
              planId = txScreen.planId,
              methodId = txScreen.methodId,
              onBackClick = {
                currentScreen = Screen.Home
              }
            )
          }
          else -> {}
        }
      }
    }

    // Verify HomeScreen is displayed
    composeTestRule.onNodeWithTag("home_screen_scaffold").assertExists()
    composeTestRule.onNodeWithTag("service_card_الدفع").assertExists()

    // Tap "الدفع" quick service card
    composeTestRule.onNodeWithTag("service_card_الدفع").performScrollTo().performClick()

    // Verify TransactionsScreen is displayed
    composeTestRule.onNodeWithTag("transactions_screen_scaffold").assertExists()
    composeTestRule.onNodeWithTag("transactions_screen_title").assertExists()
    composeTestRule.onNodeWithTag("transaction_card_ASA-2026-000001").assertExists()
    composeTestRule.onNodeWithTag("transaction_request_number", useUnmergedTree = true).assertExists()
    composeTestRule.onNodeWithTag("transaction_status_badge", useUnmergedTree = true).assertExists()
    composeTestRule.onNodeWithTag("transaction_type", useUnmergedTree = true).assertExists()
    composeTestRule.onNodeWithTag("transaction_smart_card", useUnmergedTree = true).assertExists()
    composeTestRule.onNodeWithTag("transaction_duration", useUnmergedTree = true).assertExists()
    composeTestRule.onNodeWithTag("transaction_amount", useUnmergedTree = true).assertExists()
    composeTestRule.onNodeWithTag("transaction_payment_provider", useUnmergedTree = true).assertExists()

    // Tap back button
    composeTestRule.onNodeWithTag("transactions_back_button").performClick()

    // Verify returned to HomeScreen
    composeTestRule.onNodeWithTag("home_screen_scaffold").assertExists()
  }

  @Test
  fun `navigating from home to account screen and back`() {
    composeTestRule.setContent {
      CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        var currentScreen by remember { mutableStateOf<Screen>(Screen.Home) }

        when (currentScreen) {
          is Screen.Home -> {
            HomeScreen(
              onNavigateToAccount = {
                currentScreen = Screen.Account
              }
            )
          }
          is Screen.Account -> {
            AccountScreen(
              onBackClick = {
                currentScreen = Screen.Home
              }
            )
          }
          else -> {}
        }
      }
    }

    // Verify HomeScreen is displayed
    composeTestRule.onNodeWithTag("home_screen_scaffold").assertExists()
    composeTestRule.onNodeWithTag("nav_item_profile").assertExists()

    // Click bottom nav item "حسابي"
    composeTestRule.onNodeWithTag("nav_item_profile").performClick()

    // Verify AccountScreen is displayed
    composeTestRule.onNodeWithTag("account_screen_scaffold").assertExists()
    composeTestRule.onNodeWithTag("account_screen_title").assertExists()
    composeTestRule.onNodeWithTag("account_user_name").assertExists()
    composeTestRule.onNodeWithTag("account_smart_card").assertExists()
    composeTestRule.onNodeWithTag("account_status_badge").assertExists()
    composeTestRule.onNodeWithTag("account_action_subscription").assertExists()
    composeTestRule.onNodeWithTag("account_action_transactions").assertExists()

    // Click back button
    composeTestRule.onNodeWithTag("account_back_button").performClick()

    // Verify returned to HomeScreen
    composeTestRule.onNodeWithTag("home_screen_scaffold").assertExists()
  }

  @Test
  fun `navigating from account screen to transactions and to transaction details and back`() {
    composeTestRule.setContent {
      CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        var currentScreen by remember { mutableStateOf<Screen>(Screen.Account) }
        var transactionsOrigin by remember { mutableStateOf<Screen>(Screen.Account) }

        when (currentScreen) {
          is Screen.Home -> {
            HomeScreen()
          }
          is Screen.Account -> {
            AccountScreen(
              onBackClick = {
                currentScreen = Screen.Home
              },
              onNavigateToTransactions = {
                transactionsOrigin = Screen.Account
                currentScreen = Screen.Transactions()
              }
            )
          }
          is Screen.Transactions -> {
            TransactionsScreen(
              onBackClick = {
                currentScreen = transactionsOrigin
              },
              onTransactionClick = { transaction ->
                currentScreen = Screen.TransactionDetails(transaction)
              }
            )
          }
          is Screen.TransactionDetails -> {
            val detailsScreen = currentScreen as Screen.TransactionDetails
            TransactionDetailsScreen(
              transaction = detailsScreen.transaction,
              onBackClick = {
                currentScreen = Screen.Transactions()
              }
            )
          }
          else -> {}
        }
      }
    }

    // Verify AccountScreen is displayed
    composeTestRule.onNodeWithTag("account_screen_scaffold").assertExists()
    composeTestRule.onNodeWithTag("account_action_transactions").assertExists()

    // Tap "سجل العمليات"
    composeTestRule.onNodeWithTag("account_action_transactions").performScrollTo().performClick()

    // Verify TransactionsScreen is displayed
    composeTestRule.onNodeWithTag("transactions_screen_scaffold").assertExists()
    composeTestRule.onNodeWithTag("transaction_card_ASA-2026-000001").assertExists()

    // Tap transaction card
    composeTestRule.onNodeWithTag("transaction_card_ASA-2026-000001").performScrollTo().performClick()

    // Verify TransactionDetailsScreen is displayed
    composeTestRule.onNodeWithTag("transaction_details_scaffold").assertExists()
    composeTestRule.onNodeWithTag("transaction_details_title").assertExists()
    composeTestRule.onNodeWithTag("transaction_details_request_number").assertExists()
    composeTestRule.onNodeWithTag("transaction_details_status").assertExists()
    composeTestRule.onNodeWithTag("transaction_details_operation_type").assertExists()
    composeTestRule.onNodeWithTag("transaction_details_smart_card").assertExists()
    composeTestRule.onNodeWithTag("transaction_details_duration").assertExists()
    composeTestRule.onNodeWithTag("transaction_details_amount").assertExists()
    composeTestRule.onNodeWithTag("transaction_details_provider").assertExists()
    composeTestRule.onNodeWithTag("transaction_details_date").assertExists()

    // Tap back button from TransactionDetailsScreen
    composeTestRule.onNodeWithTag("transaction_details_back_button").performClick()

    // Verify returned to TransactionsScreen
    composeTestRule.onNodeWithTag("transactions_screen_scaffold").assertExists()

    // Tap back button from TransactionsScreen
    composeTestRule.onNodeWithTag("transactions_back_button").performClick()

    // Verify returned to AccountScreen
    composeTestRule.onNodeWithTag("account_screen_scaffold").assertExists()
  }

  @Test
  fun `verifying subscription summary card on account screen and navigating to subscription details`() {
    composeTestRule.setContent {
      CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        var currentScreen by remember { mutableStateOf<Screen>(Screen.Account) }

        when (currentScreen) {
          is Screen.Home -> {
            HomeScreen()
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
              }
            )
          }
          is Screen.SubscriptionDetails -> {
            SubscriptionDetailsScreen(
              onBackClick = {
                currentScreen = Screen.Account
              },
              onNavigateToRenewal = {
                currentScreen = Screen.Renewal
              }
            )
          }
          is Screen.Renewal -> {
            RenewalScreen(
              onBackClick = {
                currentScreen = Screen.SubscriptionDetails
              }
            )
          }
          else -> {}
        }
      }
    }

    // Verify AccountScreen is displayed
    composeTestRule.onNodeWithTag("account_screen_scaffold").assertExists()

    // Verify Subscription Summary Card and its elements
    composeTestRule.onNodeWithTag("account_subscription_summary_title").performScrollTo().assertExists()
    composeTestRule.onNodeWithTag("account_subscription_summary_card").assertExists()
    composeTestRule.onNodeWithTag("summary_package_name").assertExists()
    composeTestRule.onNodeWithTag("summary_status_badge").assertExists()
    composeTestRule.onNodeWithTag("summary_start_date").assertExists()
    composeTestRule.onNodeWithTag("summary_expiry_date").assertExists()
    composeTestRule.onNodeWithTag("summary_remaining_days").assertExists()

    // Verify Button "عرض تفاصيل الاشتراك" and click it
    composeTestRule.onNodeWithTag("btn_view_subscription_details").performScrollTo().performClick()

    // Verify SubscriptionDetailsScreen is displayed
    composeTestRule.onNodeWithTag("subscription_details_scaffold").assertExists()
    composeTestRule.onNodeWithTag("subscription_details_title").assertExists()
    composeTestRule.onNodeWithTag("subscription_details_package").assertExists()
    composeTestRule.onNodeWithTag("subscription_details_status").assertExists()
    composeTestRule.onNodeWithTag("subscription_details_smart_card").assertExists()
    composeTestRule.onNodeWithTag("subscription_details_start_date").assertExists()
    composeTestRule.onNodeWithTag("subscription_details_expiry_date").assertExists()
    composeTestRule.onNodeWithTag("subscription_details_remaining").assertExists()
    composeTestRule.onNodeWithTag("subscription_features_card").assertExists()

    // Verify "تجديد الاشتراك" button navigates to RenewalScreen
    composeTestRule.onNodeWithTag("subscription_details_renew_button").performScrollTo().performClick()
    composeTestRule.onNodeWithTag("renewal_screen_scaffold").assertExists()

    // Verify back navigation returns to SubscriptionDetailsScreen
    composeTestRule.onNodeWithTag("renewal_back_button").performClick()
    composeTestRule.onNodeWithTag("subscription_details_scaffold").assertExists()

    // Verify back navigation returns to AccountScreen
    composeTestRule.onNodeWithTag("subscription_details_back_button").performClick()
    composeTestRule.onNodeWithTag("account_screen_scaffold").assertExists()

    // Verify back navigation from AccountScreen returns to HomeScreen
    composeTestRule.onNodeWithTag("account_back_button").performClick()
    composeTestRule.onNodeWithTag("home_screen_scaffold").assertExists()
  }

  @Test
  fun `navigating from home bell icon to notifications screen and back`() {
    composeTestRule.setContent {
      CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        var currentScreen by remember { mutableStateOf<Screen>(Screen.Home) }

        when (currentScreen) {
          is Screen.Home -> {
            HomeScreen(
              onNavigateToNotifications = {
                currentScreen = Screen.Notifications
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
          else -> {}
        }
      }
    }

    // Verify HomeScreen is displayed
    composeTestRule.onNodeWithTag("home_screen_scaffold").assertExists()
    composeTestRule.onNodeWithTag("notification_button").assertExists()

    // Tap notification bell icon
    composeTestRule.onNodeWithTag("notification_button").performClick()

    // Verify NotificationsScreen is displayed
    composeTestRule.onNodeWithTag("notifications_screen_scaffold").assertExists()
    composeTestRule.onNodeWithTag("notifications_screen_title").assertExists()

    // Verify 4 local mock notifications are displayed
    composeTestRule.onNodeWithTag("notification_item_1").assertExists()
    composeTestRule.onNodeWithTag("notification_title_notif_1").assertExists()
    composeTestRule.onNodeWithTag("notification_desc_notif_1").assertExists()
    composeTestRule.onNodeWithTag("notification_status_notif_1").assertExists()

    composeTestRule.onNodeWithTag("notification_item_2").assertExists()
    composeTestRule.onNodeWithTag("notification_title_notif_2").assertExists()

    composeTestRule.onNodeWithTag("notification_item_3").assertExists()
    composeTestRule.onNodeWithTag("notification_title_notif_3").assertExists()

    composeTestRule.onNodeWithTag("notification_item_4").assertExists()
    composeTestRule.onNodeWithTag("notification_title_notif_4").assertExists()

    // Tap back button
    composeTestRule.onNodeWithTag("notifications_back_button").performClick()

    // Verify returned to HomeScreen
    composeTestRule.onNodeWithTag("home_screen_scaffold").assertExists()
  }

  @Test
  fun `navigating from bottom nav media to channels screen and verifying packages`() {
    composeTestRule.setContent {
      CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        var currentScreen by remember { mutableStateOf<Screen>(Screen.Home) }

        when (currentScreen) {
          is Screen.Home -> {
            HomeScreen(
              onNavigateToChannels = {
                currentScreen = Screen.Channels
              }
            )
          }
          is Screen.Channels -> {
            ChannelsScreen(
              onBackClick = {
                currentScreen = Screen.Home
              }
            )
          }
          else -> {}
        }
      }
    }

    // Verify HomeScreen is displayed
    composeTestRule.onNodeWithTag("home_screen_scaffold").assertExists()
    composeTestRule.onNodeWithTag("nav_item_media").assertExists()

    // Tap "الإعلام" in bottom navigation
    composeTestRule.onNodeWithTag("nav_item_media").performClick()

    // Verify ChannelsScreen is displayed
    composeTestRule.onNodeWithTag("channels_screen_scaffold").assertExists()
    composeTestRule.onNodeWithTag("channels_screen_title").assertExists()
    composeTestRule.onNodeWithTag("channels_intro_card").assertExists()

    // Verify Package 1: "الباقة المشفرة"
    composeTestRule.onNodeWithTag("package_card_encrypted_package").assertExists()
    composeTestRule.onNodeWithTag("package_title_encrypted_package").assertExists()
    composeTestRule.onNodeWithTag("package_channels_count_encrypted_package").assertExists()
    composeTestRule.onNodeWithTag("package_access_encrypted_package").assertExists()
    composeTestRule.onNodeWithTag("package_desc_encrypted_package").assertExists()
    composeTestRule.onNodeWithTag("package_category_encrypted_package_رياضة").assertExists()
    composeTestRule.onNodeWithTag("package_category_encrypted_package_أفلام").assertExists()
    composeTestRule.onNodeWithTag("package_category_encrypted_package_دراما").assertExists()
    composeTestRule.onNodeWithTag("package_category_encrypted_package_ترفيه").assertExists()
    composeTestRule.onNodeWithTag("package_action_encrypted_package").assertExists()

    // Verify Package 2: "الباقة المفتوحة"
    composeTestRule.onNodeWithTag("package_card_open_package").performScrollTo().assertExists()
    composeTestRule.onNodeWithTag("package_title_open_package").assertExists()
    composeTestRule.onNodeWithTag("package_channels_count_open_package").assertExists()
    composeTestRule.onNodeWithTag("package_access_open_package").assertExists()
    composeTestRule.onNodeWithTag("package_desc_open_package").assertExists()
    composeTestRule.onNodeWithTag("package_category_open_package_أخبار").assertExists()
    composeTestRule.onNodeWithTag("package_category_open_package_عامة").assertExists()
    composeTestRule.onNodeWithTag("package_category_open_package_دينية").assertExists()
    composeTestRule.onNodeWithTag("package_category_open_package_أطفال").assertExists()
    composeTestRule.onNodeWithTag("package_category_open_package_ترفيه").assertExists()
    composeTestRule.onNodeWithTag("package_category_open_package_محلية").assertExists()
    composeTestRule.onNodeWithTag("package_action_open_package").assertExists()

    // Verify back navigation returns to HomeScreen
    composeTestRule.onNodeWithTag("channels_back_button").performClick()
    composeTestRule.onNodeWithTag("home_screen_scaffold").assertExists()
  }

  @Test
  fun `navigating from channels screen to package details screen for encrypted and open packages and back`() {
    composeTestRule.setContent {
      CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        var currentScreen by remember { mutableStateOf<Screen>(Screen.Channels) }

        when (currentScreen) {
          is Screen.Channels -> {
            ChannelsScreen(
              onBackClick = {},
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
          else -> {}
        }
      }
    }

    // Verify ChannelsScreen is displayed
    composeTestRule.onNodeWithTag("channels_screen_scaffold").assertExists()

    // Tap "تفاصيل الباقة" on Encrypted Package
    composeTestRule.onNodeWithTag("package_action_encrypted_package").performScrollTo().performClick()

    // Verify PackageDetailsScreen is displayed
    composeTestRule.onNodeWithTag("package_details_scaffold").assertExists()
    composeTestRule.onNodeWithTag("package_details_title").assertExists()
    composeTestRule.onNodeWithTag("package_details_name").assertExists()
    composeTestRule.onNodeWithTag("package_details_channels_count").assertExists()
    composeTestRule.onNodeWithTag("package_details_access").assertExists()
    composeTestRule.onNodeWithTag("package_details_description").assertExists()

    // Verify content categories for Encrypted Package
    composeTestRule.onNodeWithTag("category_item_رياضة").assertExists()
    composeTestRule.onNodeWithTag("category_item_أفلام").assertExists()
    composeTestRule.onNodeWithTag("category_item_دراما").assertExists()
    composeTestRule.onNodeWithTag("category_item_ترفيه").assertExists()

    // Tap back button
    composeTestRule.onNodeWithTag("package_details_back_button").performClick()
    composeTestRule.onNodeWithTag("channels_screen_scaffold").assertExists()

    // Tap "تفاصيل الباقة" on Open Package
    composeTestRule.onNodeWithTag("package_action_open_package").performScrollTo().performClick()

    // Verify PackageDetailsScreen for Open Package
    composeTestRule.onNodeWithTag("package_details_scaffold").assertExists()
    composeTestRule.onNodeWithTag("package_details_name").assertExists()
    composeTestRule.onNodeWithTag("package_details_access").assertExists()

    // Verify content categories for Open Package
    composeTestRule.onNodeWithTag("category_item_أخبار").assertExists()
    composeTestRule.onNodeWithTag("category_item_عامة").assertExists()
    composeTestRule.onNodeWithTag("category_item_دينية").assertExists()
    composeTestRule.onNodeWithTag("category_item_أطفال").assertExists()
    composeTestRule.onNodeWithTag("category_item_ترفيه").assertExists()
    composeTestRule.onNodeWithTag("category_item_قنوات محلية").assertExists()

    // Tap back button
    composeTestRule.onNodeWithTag("package_details_back_button").performClick()
    composeTestRule.onNodeWithTag("channels_screen_scaffold").assertExists()
  }

  @Test
  fun `navigating from home reception guide card and shortcuts to reception screens and back`() {
    composeTestRule.setContent {
      CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        var currentScreen by remember { mutableStateOf<Screen>(Screen.Home) }

        when (currentScreen) {
          is Screen.Home -> {
            HomeScreen(
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
          else -> {}
        }
      }
    }

    // Verify HomeScreen is displayed
    composeTestRule.onNodeWithTag("home_screen_scaffold").assertExists()

    // 1. Verify Reception Guide Card and open ReceptionGuideScreen
    composeTestRule.onNodeWithTag("reception_guide_card").performScrollTo().assertExists()
    composeTestRule.onNodeWithTag("reception_guide_title", useUnmergedTree = true).assertExists()
    composeTestRule.onNodeWithTag("reception_guide_description", useUnmergedTree = true).assertExists()
    composeTestRule.onNodeWithTag("reception_guide_card").performClick()

    // Verify ReceptionGuideScreen
    composeTestRule.onNodeWithTag("reception_guide_screen_scaffold").assertExists()
    composeTestRule.onNodeWithTag("reception_guide_screen_title").assertExists()
    composeTestRule.onNodeWithTag("guide_topic_reception").assertExists()
    composeTestRule.onNodeWithTag("guide_topic_devices").assertExists()
    composeTestRule.onNodeWithTag("guide_topic_installation").assertExists()
    composeTestRule.onNodeWithTag("guide_topic_setup").assertExists()
    composeTestRule.onNodeWithTag("guide_topic_info").assertExists()

    // Back to HomeScreen
    composeTestRule.onNodeWithTag("reception_guide_back_button").performClick()
    composeTestRule.onNodeWithTag("home_screen_scaffold").assertExists()

    // 2. Open Frequencies shortcut
    composeTestRule.onNodeWithTag("reception_shortcut_frequencies", useUnmergedTree = true).performScrollTo().performClick()
    composeTestRule.onNodeWithTag("frequencies_screen_scaffold").assertExists()
    composeTestRule.onNodeWithTag("frequencies_screen_title").assertExists()
    composeTestRule.onNodeWithTag("frequencies_back_button").performClick()
    composeTestRule.onNodeWithTag("home_screen_scaffold").assertExists()

    // 3. Open Coverage shortcut
    composeTestRule.onNodeWithTag("reception_shortcut_coverage", useUnmergedTree = true).performScrollTo().performClick()
    composeTestRule.onNodeWithTag("coverage_screen_scaffold").assertExists()
    composeTestRule.onNodeWithTag("coverage_screen_title").assertExists()
    composeTestRule.onNodeWithTag("coverage_back_button").performClick()
    composeTestRule.onNodeWithTag("home_screen_scaffold").assertExists()

    // 4. Open Receiver & Card Info shortcut
    composeTestRule.onNodeWithTag("reception_shortcut_receiver_info", useUnmergedTree = true).performScrollTo().performClick()
    composeTestRule.onNodeWithTag("receiver_card_info_scaffold").assertExists()
    composeTestRule.onNodeWithTag("receiver_card_info_title").assertExists()
    composeTestRule.onNodeWithTag("receiver_card_info_back_button").performClick()
    composeTestRule.onNodeWithTag("home_screen_scaffold").assertExists()
  }
}


