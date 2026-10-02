import React from 'react';
import {
  CheckCircle2,
  CreditCard,
  Calendar,
  Building2,
  Repeat,
  Banknote,
  Hourglass,
} from 'lucide-react';
import { TopBar } from '../components/TopBar';
import { RenewalPlanDataSource, PaymentMethodDataSource } from '../data';

interface RenewalRequestConfirmationScreenProps {
  planId?: string;
  methodId?: string;
  requestNumber?: string;
  onReturnHome: () => void;
}

export const RenewalRequestConfirmationScreen: React.FC<RenewalRequestConfirmationScreenProps> = ({
  planId = 'plan_1_month',
  methodId = 'kuraimi',
  requestNumber = 'ASA-2026-000001',
  onReturnHome,
}) => {
  const plan =
    RenewalPlanDataSource.plans.find((p) => p.id === planId) ||
    RenewalPlanDataSource.plans[0];

  const method =
    PaymentMethodDataSource.paymentMethods.find((m) => m.id === methodId) ||
    PaymentMethodDataSource.paymentMethods[0];

  return (
    <div
      className="flex flex-col min-h-screen bg-transparent text-[#E5E7EB] pb-28"
      data-testid="confirmation_screen_scaffold"
    >
      <TopBar
        title="تم استلام الطلب"
        onBack={onReturnHome}
        testTag="confirmation_screen_title"
        backTestTag="confirmation_back_button"
      />

      <div
        className="px-5 py-4 space-y-6 max-w-2xl mx-auto w-full flex flex-col items-center"
        data-testid="confirmation_content_column"
      >
        {/* 1. Success Icon and Header Messages */}
        <div className="flex flex-col items-center text-center space-y-3 pt-3">
          <div className="w-24 h-24 rounded-3xl bg-emerald-500/15 border-2 border-emerald-400/50 flex items-center justify-center shadow-2xl shadow-emerald-500/20">
            <CheckCircle2
              className="w-14 h-14 text-emerald-400"
              data-testid="confirmation_success_icon"
            />
          </div>
          <div>
            <h1
              className="text-2xl font-black text-white"
              data-testid="confirmation_main_message"
            >
              تم استلام طلب التجديد
            </h1>
            <p
              className="text-xs text-[#9CA3AF] mt-1.5 max-w-sm mx-auto leading-relaxed"
              data-testid="confirmation_secondary_message"
            >
              سيتم التحقق من بيانات الدفع قبل تنفيذ تجديد الاشتراك.
            </p>
          </div>
        </div>

        {/* 2. Request Identifier and Status Card */}
        <div className="w-full p-4 rounded-2xl bg-[#0B1739]/85 backdrop-blur-xl border border-[#27BDE3]/30 flex items-center justify-between shadow-xl">
          <div>
            <p className="text-[11px] text-[#9CA3AF]">رقم الطلب</p>
            <p
              className="font-mono text-base font-bold text-[#27BDE3] tracking-wider"
              data-testid="confirmation_request_number"
            >
              {requestNumber}
            </p>
          </div>

          <div
            className="flex items-center gap-1.5 px-3 py-1.5 rounded-full bg-[#C8A45D]/15 border border-[#C8A45D]/40 text-[#C8A45D] text-xs font-bold"
            data-testid="confirmation_status_badge"
          >
            <Hourglass className="w-3.5 h-3.5" />
            <span>قيد المراجعة</span>
          </div>
        </div>

        {/* 3. Compact Request Summary Card */}
        <div
          className="w-full rounded-3xl bg-[#0B1739]/85 backdrop-blur-xl border border-[#27BDE3]/20 p-5 space-y-3.5 shadow-xl"
          data-testid="confirmation_summary_card"
        >
          <h2 className="text-xs font-bold text-[#27BDE3]">تفاصيل الطلب</h2>

          <div className="grid grid-cols-2 gap-3">
            <div className="flex items-center gap-2.5">
              <div className="w-9 h-9 rounded-xl bg-[#27BDE3]/15 border border-[#27BDE3]/25 flex items-center justify-center shrink-0">
                <CreditCard className="w-5 h-5 text-[#27BDE3]" />
              </div>
              <div>
                <p className="text-[11px] text-[#9CA3AF]">البطاقة الذكية</p>
                <p className="text-sm font-bold text-white font-mono" data-testid="confirmation_smart_card">
                  **** 4587
                </p>
              </div>
            </div>

            <div className="flex items-center gap-2.5">
              <div className="w-9 h-9 rounded-xl bg-[#27BDE3]/15 border border-[#27BDE3]/25 flex items-center justify-center shrink-0">
                <Calendar className="w-5 h-5 text-[#27BDE3]" />
              </div>
              <div>
                <p className="text-[11px] text-[#9CA3AF]">مدة التجديد</p>
                <p className="text-sm font-bold text-white" data-testid="confirmation_duration">
                  {plan.durationLabel}
                </p>
              </div>
            </div>
          </div>

          <div className="h-px bg-[#27BDE3]/15" />

          <div className="grid grid-cols-2 gap-3 items-center">
            <div className="flex items-center gap-2.5">
              <div className="w-9 h-9 rounded-xl bg-[#27BDE3]/15 border border-[#27BDE3]/25 flex items-center justify-center shrink-0">
                {method.accountNumber ? (
                  <Building2 className="w-5 h-5 text-[#27BDE3]" />
                ) : (
                  <Repeat className="w-5 h-5 text-[#27BDE3]" />
                )}
              </div>
              <div>
                <p className="text-[11px] text-[#9CA3AF]">وسيلة الدفع</p>
                <p className="text-sm font-bold text-white" data-testid="confirmation_provider">
                  {method.providerName}
                </p>
              </div>
            </div>

            <div className="flex items-center gap-2.5">
              <div className="w-9 h-9 rounded-xl bg-[#C8A45D]/15 border border-[#C8A45D]/25 flex items-center justify-center shrink-0">
                <Banknote className="w-5 h-5 text-[#C8A45D]" />
              </div>
              <div>
                <p className="text-[11px] text-[#9CA3AF]">المبلغ المطلوب</p>
                <p className="text-sm font-extrabold text-[#C8A45D]" data-testid="confirmation_amount">
                  {RenewalPlanDataSource.formatPrice(plan.price, plan.currency)}
                </p>
              </div>
            </div>
          </div>
        </div>
      </div>

      {/* Primary Action Button */}
      <div className="fixed bottom-0 inset-x-0 bg-[#0B1739]/95 backdrop-blur-2xl border-t border-[#27BDE3]/20 p-4 z-40 shadow-[0_-8px_32px_rgba(0,0,0,0.65)]">
        <div className="max-w-2xl mx-auto">
          <button
            onClick={onReturnHome}
            className="w-full py-4 px-6 rounded-2xl bg-gradient-to-r from-[#27BDE3] to-[#1DA6CA] hover:from-[#36c7ec] hover:to-[#27BDE3] text-[#0B1739] font-black text-sm tracking-wide shadow-lg shadow-[#27BDE3]/25 transition-all active:scale-[0.99]"
            data-testid="confirmation_return_home_button"
          >
            العودة إلى الرئيسية
          </button>
        </div>
      </div>
    </div>
  );
};
