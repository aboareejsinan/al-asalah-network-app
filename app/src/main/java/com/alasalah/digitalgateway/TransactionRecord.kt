package com.alasalah.digitalgateway

/**
 * Local mock data model for transaction records.
 * Uses existing flow data without database or backend persistence.
 */
data class TransactionRecord(
    val requestNumber: String = "ASA-2026-000001",
    val operationType: String = "تجديد اشتراك",
    val smartCardNumber: String = "**** 4587",
    val planId: String = "plan_1_month",
    val methodId: String = "kuraimi",
    val status: String = "قيد المراجعة",
    val requestDate: String = "29 سبتمبر 2026"
)
