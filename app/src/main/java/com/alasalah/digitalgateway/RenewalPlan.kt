package com.alasalah.digitalgateway

/**
 * Centralized data model for subscription renewal plans.
 * Prices and durations are decoupled from the UI composables so they can be
 * easily replaced by dynamic backend/admin services in the future.
 */
data class RenewalPlan(
    val id: String,
    val durationLabel: String,
    val durationValue: Int, // duration in months
    val price: Int,
    val currency: String = "ريال",
    val isEnabled: Boolean = true,
    val badgeLabel: String? = null
)

/**
 * Centralized local mock data source for renewal plans.
 * All prices and plans are defined here in one single place.
 */
object RenewalPlanDataSource {
    val plans: List<RenewalPlan> = listOf(
        RenewalPlan(
            id = "plan_1_month",
            durationLabel = "شهر واحد",
            durationValue = 1,
            price = 3000,
            currency = "ريال",
            isEnabled = true,
            badgeLabel = null
        ),
        RenewalPlan(
            id = "plan_2_months",
            durationLabel = "شهران",
            durationValue = 2,
            price = 6000,
            currency = "ريال",
            isEnabled = true,
            badgeLabel = null
        ),
        RenewalPlan(
            id = "plan_3_months",
            durationLabel = "3 أشهر",
            durationValue = 3,
            price = 9000,
            currency = "ريال",
            isEnabled = true,
            badgeLabel = null
        ),
        RenewalPlan(
            id = "plan_6_months",
            durationLabel = "6 أشهر",
            durationValue = 6,
            price = 16000,
            currency = "ريال",
            isEnabled = true,
            badgeLabel = "الأكثر طلباً"
        ),
        RenewalPlan(
            id = "plan_1_year",
            durationLabel = "سنة",
            durationValue = 12,
            price = 30000,
            currency = "ريال",
            isEnabled = true,
            badgeLabel = "أفضل قيمة"
        )
    )

    fun getEnabledPlans(): List<RenewalPlan> = plans.filter { it.isEnabled }

    fun formatPrice(price: Int, currency: String): String {
        val formatted = "%,d".format(java.util.Locale.US, price)
        return "$formatted $currency"
    }
}
