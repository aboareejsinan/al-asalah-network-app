import React from 'react';
import {
  CreditCard,
  Calendar,
  Building2,
  Repeat,
  Banknote,
  Hourglass,
  RefreshCw,
  Hash,
  CalendarDays,
} from 'lucide-react';
import { TopBar } from '../components/TopBar';
import { RenewalPlanDataSource, PaymentMethodDataSource } from '../data';
import { TransactionRecord } from '../types';

interface TransactionDetailsScreenProps {
  transaction: TransactionRecord;
  onBackClick: () => void;
}

export const TransactionDetailsScreen: React.FC<TransactionDetailsScreenProps> = ({
  transaction,
  onBackClick,
}) => {
  const plan =
    RenewalPlanDataSource.plans.find((p) => p.id === transaction.planId) ||
    RenewalPlanDataSource.plans[0];

  const method =
    PaymentMethodDataSource.paymentMethods.find((m) => m.id === transaction.methodId) ||
    PaymentMethodDataSource.paymentMethods[0];

  return (
    <div
      className="flex flex-col min-h-screen bg-transparent text-[#E5E7EB] pb-20"
      data-testid="transaction_details_scaffold"
    >
      <TopBar
        title="تفاصيل العملية"
        onBack={onBackClick}
        testTag="transaction_details_title"
        backTestTag="transaction_details_back_button"
      />

      <div
        className="px-5 py-4 space-y-5 max-w-2xl mx-auto w-full"
        data-testid="transaction_details_content"
      >
        {/* Prominent Header Card with Request Number and Status */}
        <div
          className="rounded-3xl bg-gradient-to-br from-[#122452]/90 via-[#0E1D44]/95 to-[#0B1739]/95 backdrop-blur-xl border border-[#C8A45D]/35 p-6 flex flex-col items-center text-center space-y-4 shadow-xl relative overflow-hidden"
          data-testid="transaction_details_header_card"
        >
          {/* Subtle cyan ambient glow */}
          <div className="absolute -top-12 -left-12 w-40 h-40 bg-[#27BDE3]/10 rounded-full blur-2xl pointer-events-none" />

          {/* Status badge with warm amber accent */}
          <div
            className="flex items-center gap-2 px-3.5 py-1.5 rounded-full bg-amber-500/15 border border-amber-400/40 text-amber-400 text-sm font-bold relative z-10"
            data-testid="transaction_details_status"
          >
            <Hourglass className="w-4 h-4" />
            <span>{transaction.status}</span>
          </div>

          <div className="space-y-1 relative z-10">
            <div className="flex items-center justify-center gap-1.5 text-[#9CA3AF] text-xs font-medium">
              <Hash className="w-3.5 h-3.5 text-[#27BDE3]" />
              <span>رقم الطلب الرسمي</span>
            </div>
            <p
              className="font-mono text-2xl font-black text-[#27BDE3] tracking-widest"
              data-testid="transaction_details_request_number"
            >
              {transaction.requestNumber}
            </p>
          </div>
        </div>

        {/* Detailed Operation Info Card */}
        <div
          className="rounded-3xl bg-[#0B1739]/85 backdrop-blur-xl border border-[#27BDE3]/20 p-5 space-y-4 shadow-xl"
          data-testid="transaction_details_info_card"
        >
          <div className="flex items-center justify-between">
            <h2 className="text-base font-bold text-white">بيانات العملية</h2>
            <span className="text-xs text-[#9CA3AF]">تفاصيل الفاتورة الإلكترونية</span>
          </div>

          <div className="space-y-3.5 divide-y divide-white/5">
            {/* Detail 1: Operation type */}
            <div className="flex items-center justify-between pt-1">
              <div className="flex items-center gap-2.5">
                <div className="w-8 h-8 rounded-lg bg-[#C8A45D]/15 border border-[#C8A45D]/30 flex items-center justify-center shrink-0">
                  <RefreshCw className="w-4 h-4 text-[#C8A45D]" />
                </div>
                <span className="text-xs text-[#9CA3AF]">نوع العملية</span>
              </div>
              <span className="text-xs font-bold text-white" data-testid="transaction_details_operation_type">
                {transaction.operationType}
              </span>
            </div>

            {/* Detail 2: Smart Card */}
            <div className="flex items-center justify-between pt-3">
              <div className="flex items-center gap-2.5">
                <div className="w-8 h-8 rounded-lg bg-[#27BDE3]/15 border border-[#27BDE3]/30 flex items-center justify-center shrink-0">
                  <CreditCard className="w-4 h-4 text-[#27BDE3]" />
                </div>
                <span className="text-xs text-[#9CA3AF]">البطاقة الذكية</span>
              </div>
              <span className="text-xs font-bold text-white" data-testid="transaction_details_smart_card">
                {transaction.smartCardNumber}
              </span>
            </div>

            {/* Detail 3: Duration */}
            <div className="flex items-center justify-between pt-3">
              <div className="flex items-center gap-2.5">
                <div className="w-8 h-8 rounded-lg bg-[#27BDE3]/15 border border-[#27BDE3]/30 flex items-center justify-center shrink-0">
                  <Calendar className="w-4 h-4 text-[#27BDE3]" />
                </div>
                <span className="text-xs text-[#9CA3AF]">مدة التجديد</span>
              </div>
              <span className="text-xs font-bold text-white" data-testid="transaction_details_duration">
                {plan.durationLabel}
              </span>
            </div>

            {/* Detail 4: Amount */}
            <div className="flex items-center justify-between pt-3">
              <div className="flex items-center gap-2.5">
                <div className="w-8 h-8 rounded-lg bg-[#C8A45D]/15 border border-[#C8A45D]/30 flex items-center justify-center shrink-0">
                  <Banknote className="w-4 h-4 text-[#C8A45D]" />
                </div>
                <span className="text-xs text-[#9CA3AF]">المبلغ المطلوب</span>
              </div>
              <span className="text-sm font-extrabold text-[#C8A45D]" data-testid="transaction_details_amount">
                {RenewalPlanDataSource.formatPrice(plan.price, plan.currency)}
              </span>
            </div>

            {/* Detail 5: Payment Provider */}
            <div className="flex items-center justify-between pt-3">
              <div className="flex items-center gap-2.5">
                <div className="w-8 h-8 rounded-lg bg-[#27BDE3]/15 border border-[#27BDE3]/30 flex items-center justify-center shrink-0">
                  {method.accountNumber ? (
                    <Building2 className="w-4 h-4 text-[#27BDE3]" />
                  ) : (
                    <Repeat className="w-4 h-4 text-[#27BDE3]" />
                  )}
                </div>
                <span className="text-xs text-[#9CA3AF]">وسيلة الدفع</span>
              </div>
              <span className="text-xs font-bold text-white" data-testid="transaction_details_provider">
                {method.providerName}
              </span>
            </div>

            {/* Detail 6: Request Date */}
            <div className="flex items-center justify-between pt-3">
              <div className="flex items-center gap-2.5">
                <div className="w-8 h-8 rounded-lg bg-[#27BDE3]/15 border border-[#27BDE3]/30 flex items-center justify-center shrink-0">
                  <CalendarDays className="w-4 h-4 text-[#27BDE3]" />
                </div>
                <span className="text-xs text-[#9CA3AF]">تاريخ الطلب</span>
              </div>
              <span className="text-xs font-bold text-white" data-testid="transaction_details_date">
                {transaction.requestDate}
              </span>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};
