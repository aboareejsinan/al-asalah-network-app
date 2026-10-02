import React, { useState } from 'react';
import {
  CreditCard,
  Calendar,
  Building2,
  Copy,
  Check,
  Info,
  Receipt,
  Award,
  Repeat,
} from 'lucide-react';
import { TopBar } from '../components/TopBar';
import { RenewalPlanDataSource, PaymentMethodDataSource } from '../data';

interface PaymentInstructionsScreenProps {
  planId?: string;
  methodId?: string;
  onBackClick: () => void;
  onNavigateToProof: (planId: string, methodId: string) => void;
}

export const PaymentInstructionsScreen: React.FC<PaymentInstructionsScreenProps> = ({
  planId = 'plan_1_month',
  methodId = 'kuraimi',
  onBackClick,
  onNavigateToProof,
}) => {
  const plan =
    RenewalPlanDataSource.plans.find((p) => p.id === planId) ||
    RenewalPlanDataSource.plans[0];

  const method =
    PaymentMethodDataSource.paymentMethods.find((m) => m.id === methodId) ||
    PaymentMethodDataSource.paymentMethods[0];

  const [isCopied, setIsCopied] = useState(false);

  const handleCopy = () => {
    if (method.accountNumber) {
      navigator.clipboard.writeText(method.accountNumber);
      setIsCopied(true);
      setTimeout(() => setIsCopied(false), 2000);
    }
  };

  return (
    <div
      className="flex flex-col min-h-screen bg-transparent text-[#E5E7EB] pb-28"
      data-testid="payment_instructions_scaffold"
    >
      <TopBar
        title="إتمام الدفع"
        onBack={onBackClick}
        testTag="payment_instructions_title"
        backTestTag="payment_instructions_back_button"
      />

      <div
        className="px-5 py-3 space-y-5 max-w-2xl mx-auto w-full"
        data-testid="payment_instructions_content_column"
      >
        {/* 1. Compact Payment Summary Card */}
        <div
          className="rounded-3xl bg-gradient-to-br from-[#122452]/90 via-[#0E1D44]/95 to-[#0B1739]/95 backdrop-blur-xl border border-[#C8A45D]/35 p-5 space-y-4 shadow-xl"
          data-testid="instructions_summary_card"
        >
          <div className="flex items-center justify-between">
            <span className="text-xs font-bold text-[#27BDE3]">تفاصيل عملية التجديد</span>
            <span className="text-[11px] font-bold text-[#C8A45D] bg-[#C8A45D]/15 border border-[#C8A45D]/30 px-2.5 py-0.5 rounded-full">طلب جديد</span>
          </div>

          <div className="grid grid-cols-2 gap-3 pt-1">
            <div className="flex items-center gap-2.5">
              <div className="w-9 h-9 rounded-xl bg-[#27BDE3]/15 border border-[#27BDE3]/30 flex items-center justify-center shrink-0">
                <Award className="w-5 h-5 text-[#27BDE3]" />
              </div>
              <div>
                <p className="text-[11px] text-[#9CA3AF]">الباقة</p>
                <p className="text-sm font-bold text-white" data-testid="instructions_package_name">
                  الباقة الأساسية
                </p>
              </div>
            </div>

            <div className="flex items-center gap-2.5">
              <div className="w-9 h-9 rounded-xl bg-[#C8A45D]/15 border border-[#C8A45D]/30 flex items-center justify-center shrink-0">
                <CreditCard className="w-5 h-5 text-[#C8A45D]" />
              </div>
              <div>
                <p className="text-[11px] text-[#9CA3AF]">البطاقة الذكية</p>
                <p className="text-sm font-bold text-white font-mono" data-testid="instructions_smart_card">
                  **** 4587
                </p>
              </div>
            </div>
          </div>

          <div className="h-px bg-white/10" />

          <div className="grid grid-cols-2 gap-3">
            <div className="flex items-center gap-2.5">
              <div className="w-9 h-9 rounded-xl bg-[#27BDE3]/15 border border-[#27BDE3]/30 flex items-center justify-center shrink-0">
                <Calendar className="w-5 h-5 text-[#27BDE3]" />
              </div>
              <div>
                <p className="text-[11px] text-[#9CA3AF]">مدة التجديد</p>
                <p className="text-sm font-bold text-white" data-testid="instructions_duration">
                  {plan.durationLabel}
                </p>
              </div>
            </div>

            <div className="flex items-center gap-2.5">
              <div className="w-9 h-9 rounded-xl bg-[#C8A45D]/15 border border-[#C8A45D]/30 flex items-center justify-center shrink-0">
                {method.accountNumber ? (
                  <Building2 className="w-5 h-5 text-[#C8A45D]" />
                ) : (
                  <Repeat className="w-5 h-5 text-[#C8A45D]" />
                )}
              </div>
              <div>
                <p className="text-[11px] text-[#9CA3AF]">وسيلة الدفع</p>
                <p className="text-sm font-bold text-white" data-testid="instructions_provider_name">
                  {method.providerName}
                </p>
              </div>
            </div>
          </div>
        </div>

        {/* 2. Payment Destination Card */}
        <div
          className="rounded-3xl border border-[#27BDE3]/40 bg-gradient-to-br from-[#0B214C]/95 via-[#0C2758]/90 to-[#07132F]/95 backdrop-blur-xl p-5 space-y-4 shadow-xl relative overflow-hidden"
          data-testid="payment_destination_card"
        >
          {/* Subtle cyan glow */}
          <div className="absolute top-0 right-0 w-32 h-32 bg-[#27BDE3]/15 rounded-full blur-2xl pointer-events-none" />

          {/* Amount Display */}
          <div className="text-center space-y-1 relative z-10">
            <p className="text-xs font-bold text-[#27BDE3]">المبلغ المطلوب تحويله</p>
            <p
              className="text-2xl sm:text-3xl font-black text-[#C8A45D] tracking-tight"
              data-testid="instructions_amount_value"
            >
              {RenewalPlanDataSource.formatPrice(plan.price, plan.currency)}
            </p>
          </div>

          <div className="h-px bg-white/10" />

          {/* Payment Destination Details */}
          {method.accountNumber ? (
            <div className="space-y-3 relative z-10">
              <div className="flex items-center justify-between">
                <span className="text-xs text-[#9CA3AF]">جهة التحويل:</span>
                <span className="text-sm font-bold text-white" data-testid="destination_provider_name">
                  {method.providerName}
                </span>
              </div>

              {/* Account Number Box with Copy Action */}
              <div className="p-3.5 bg-[#07112B] rounded-2xl border border-[#27BDE3]/30 flex items-center justify-between gap-3">
                <div>
                  <p className="text-[11px] text-[#9CA3AF]">رقم الحساب المعتمد</p>
                  <p
                    className="font-mono text-base font-bold text-white tracking-widest"
                    data-testid="destination_account_number"
                  >
                    {method.accountNumber}
                  </p>
                </div>

                <button
                  type="button"
                  onClick={handleCopy}
                  className="flex items-center gap-1.5 px-3 py-1.5 rounded-xl border border-[#27BDE3]/50 text-[#27BDE3] hover:bg-[#27BDE3]/15 transition-all text-xs font-bold active:scale-95"
                  data-testid="copy_account_button"
                >
                  {isCopied ? <Check className="w-4 h-4 text-emerald-400" /> : <Copy className="w-4 h-4" />}
                  <span>{isCopied ? 'تم النسخ' : 'نسخ الرقم'}</span>
                </button>
              </div>
            </div>
          ) : (
            <div className="space-y-2 relative z-10">
              <div className="flex items-center justify-between">
                <span className="text-xs text-[#9CA3AF]">جهة التحويل:</span>
                <span className="text-sm font-bold text-white" data-testid="destination_provider_name">
                  {method.providerName}
                </span>
              </div>

              <div className="p-3.5 bg-[#07112B] rounded-2xl border border-[#27BDE3]/30 flex items-center gap-2.5">
                <Info className="w-5 h-5 text-[#27BDE3] shrink-0" />
                <p className="text-xs font-medium text-[#E5E7EB]" data-testid="destination_method_note">
                  {method.note || 'إرسال قيمة الاشتراك عبر شبكة الحوالات الموحدة'}
                </p>
              </div>
            </div>
          )}
        </div>

        {/* 3. Important Info Card: بعد إتمام التحويل */}
        <div
          className="rounded-2xl bg-[#0B1739]/85 backdrop-blur-xl border border-[#27BDE3]/20 p-4 flex items-start gap-3 shadow-md"
          data-testid="after_transfer_info_card"
        >
          <div className="w-9 h-9 rounded-xl bg-[#27BDE3]/15 border border-[#27BDE3]/30 flex items-center justify-center shrink-0">
            <Info className="w-5 h-5 text-[#27BDE3]" />
          </div>
          <div className="space-y-1">
            <h3 className="text-sm font-bold text-white" data-testid="after_transfer_info_title">
              بعد إتمام التحويل
            </h3>
            <p className="text-xs text-[#9CA3AF] leading-relaxed" data-testid="after_transfer_info_text">
              احتفظ بإيصال أو رقم عملية التحويل لإكمال طلب التجديد وإرسال إثبات السداد.
            </p>
          </div>
        </div>
      </div>

      {/* Bottom Fixed Action Bar */}
      <div className="fixed bottom-0 inset-x-0 bg-[#0B1739]/95 backdrop-blur-2xl border-t border-[#27BDE3]/20 p-4 z-40 shadow-[0_-8px_32px_rgba(0,0,0,0.65)]">
        <div className="max-w-2xl mx-auto">
          <button
            onClick={() => onNavigateToProof(plan.id, method.id)}
            className="w-full py-3.5 px-6 rounded-xl bg-gradient-to-r from-[#C8A45D] via-[#E2C37D] to-[#C8A45D] hover:from-[#dfbf79] hover:to-[#dfbf79] text-[#0B1739] font-black text-base shadow-lg shadow-[#C8A45D]/30 transition-all flex items-center justify-center gap-2 active:scale-[0.99]"
            data-testid="send_payment_proof_button"
          >
            <Receipt className="w-5 h-5" />
            <span>إرسال إثبات الدفع</span>
          </button>
        </div>
      </div>
    </div>
  );
};
