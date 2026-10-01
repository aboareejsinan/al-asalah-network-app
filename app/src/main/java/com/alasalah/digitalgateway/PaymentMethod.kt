package com.alasalah.digitalgateway

/**
 * Centralized data model for payment methods.
 * Account details and providers are decoupled from UI composables so they
 * can be dynamically updated by administration or backend systems in the future.
 */
data class PaymentMethod(
    val id: String,
    val providerName: String,
    val accountNumber: String?,
    val methodType: String,
    val isEnabled: Boolean = true,
    val note: String? = null
)

/**
 * Centralized local mock data source for payment methods.
 * All payment accounts and provider details are stored in one single place.
 */
object PaymentMethodDataSource {
    val paymentMethods: List<PaymentMethod> = listOf(
        PaymentMethod(
            id = "kuraimi",
            providerName = "بنك الكريمي",
            accountNumber = "3059909117",
            methodType = "حساب بنكي",
            isEnabled = true,
            note = null
        ),
        PaymentMethod(
            id = "alsharq",
            providerName = "بنك الشرق",
            accountNumber = "411560754",
            methodType = "حساب بنكي",
            isEnabled = true,
            note = null
        ),
        PaymentMethod(
            id = "qutaibi",
            providerName = "بنك القطيبي",
            accountNumber = "411059188",
            methodType = "حساب بنكي",
            isEnabled = true,
            note = null
        ),
        PaymentMethod(
            id = "alinma",
            providerName = "بنك الإنماء",
            accountNumber = "1010230616110",
            methodType = "حساب بنكي",
            isEnabled = true,
            note = null
        ),
        PaymentMethod(
            id = "alsalam",
            providerName = "بنك السلام",
            accountNumber = "1010018468110",
            methodType = "حساب بنكي",
            isEnabled = true,
            note = null
        ),
        PaymentMethod(
            id = "al_doha",
            providerName = "الإيداع الدوحة",
            accountNumber = "2317076",
            methodType = "إيداع نقدي",
            isEnabled = true,
            note = null
        ),
        PaymentMethod(
            id = "qutaibi_shilling",
            providerName = "القطيبي شلن",
            accountNumber = "778147490",
            methodType = "حساب شلن",
            isEnabled = true,
            note = null
        ),
        PaymentMethod(
            id = "unified_transfer_network",
            providerName = "شبكة الحوالات الموحدة",
            accountNumber = null,
            methodType = "شبكة حوالات",
            isEnabled = true,
            note = "إرسال قيمة الاشتراك عبر شبكة الحوالات الموحدة"
        )
    )

    fun getEnabledPaymentMethods(): List<PaymentMethod> =
        paymentMethods.filter { it.isEnabled }
}
