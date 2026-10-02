import React, { useState } from 'react';
import {
  CreditCard,
  Calendar,
  Building2,
  Wallet,
  PiggyBank,
  Banknote,
  Coins,
  Repeat,
  CheckCircle2,
  Circle,
  Award,
} from 'lucide-react';
import { TopBar } from '../components/TopBar';
import { RenewalPlanDataSource, PaymentMethodDataSource } from '../data';
import { PaymentMethod } from '../types';

interface PaymentMethodScreenProps {
  planId?: string;
  onBackClick: () => void;
  onNavigateToInstructions: (planId: string, methodId: string) => void;
}

export const PaymentMethodScreen: React.FC<PaymentMethodScreenProps> = ({
  planId = 'plan_1_month',
  onBackClick,
  onNavigateToInstructions,
}) => {
  const selectedPlan =
    RenewalPlanDataSource.plans.find((p) => p.id === planId) ||
    RenewalPlanDataSource.plans[0];

  const paymentMethods = PaymentMethodDataSource.getEnabledPaymentMethods();
  const [selectedMethodId, setSelectedMethodId] = useState<string>(
    paymentMethods[0]?.id || ''
  );

  const getMethodStyle = (id: string) => {
    switch (id) {
      case 'kuraimi':
        return {
          icon: <Building2 className="w-6 h-6 text-[#27BDE3]" />,
          color: '#27BDE3',
          iconBg: 'bg-[#27BDE3]/15 border-[#27BDE3]/30',
        };
      case 'alsharq':
        return {
          icon: <Wallet className="w-6 h-6 text-[#C8A45D]" />,
          color: '#C8A45D',
          iconBg: 'bg-[#C8A45D]/15 border-[#C8A45D]/30',
        };
      case 'qutaibi':
        return {
          icon: <PiggyBank className="w-6 h-6 text-[#8B5CF6]" />,
          color: '#8B5CF6',
          iconBg: 'bg-[#8B5CF6]/15 border-[#8B5CF6]/30',
        };
      case 'alinma':
        return {
          icon: <Banknote className="w-6 h-6 text-[#38BDF8]" />,
          color: '#38BDF8',
          iconBg: 'bg-[#38BDF8]/15 border-[#38BDF8]/30',
        };
      case 'alsalam':
        return {
          icon: <CreditCard className="w-6 h-6 text-[#C8A45D]" />,
          color: '#C8A45D',
          iconBg: 'bg-[#C8A45D]/15 border-[#C8A45D]/30',
        };
      case 'al_doha':
        return {
          icon: <Coins className="w-6 h-6 text-[#8B5CF6]" />,
          color: '#8B5CF6',
          iconBg: 'bg-[#8B5CF6]/15 border-[#8B5CF6]/30',
        };
      case 'qutaibi_shilling':
        return {
          icon: <Banknote className="w-6 h-6 text-[#27BDE3]" />,
          color: '#27BDE3',
          iconBg: 'bg-[#27BDE3]/15 border-[#27BDE3]/30',
        };
      case 'unified_transfer_network':
        return {
          icon: <Repeat className="w-6 h-6 text-[#E0B865]" />,
          color: '#E0B865',
          iconBg: 'bg-[#C8A45D]/15 border-[#C8A45D]/30',
        };
      default:
        return {
          icon: <Building2 className="w-6 h-6 text-[#27BDE3]" />,
          color: '#27BDE3',
          iconBg: 'bg-[#27BDE3]/15 border-[#27BDE3]/30',
        };
    }
  };

  const getMethodIcon = (id: string) => {
    return getMethodStyle(id).icon;
  };

  const getAccentBorder = (_id: string, isSelected: boolean) => {
    if (!isSelected) return 'border-[#27BDE3]/15 bg-[#0B1739]/80 hover:border-[#27BDE3]/40 hover:bg-[#0e214d]';
    return 'border-[#27BDE3] bg-[#0E224D]/95 shadow-[0_4px_20px_rgba(39,189,227,0.2)]';
  };

  return (
    <div className="flex flex-col min-h-screen bg-transparent text-[#E5E7EB] pb-28" data-testid="payment_method_screen_scaffold">
      <TopBar
        title="اختيار وسيلة الدفع"
        onBack={onBackClick}
        testTag="payment_method_screen_title"
        backTestTag="payment_method_back_button"
      />

      <div className="px-5 py-3 space-y-5 max-w-2xl mx-auto w-full" data-testid="payment_method_content_column">
        {/* Compact Summary Card */}
        <div
          className="rounded-3xl bg-gradient-to-br from-[#122452]/90 via-[#0E1D44]/95 to-[#0B1739]/95 backdrop-blur-xl border border-[#C8A45D]/35 p-5 space-y-4 shadow-xl"
          data-testid="payment_method_plan_summary_card"
        >
          <div className="flex items-center justify-between">
            <div className="flex items-center gap-2">
              <span className="w-2 h-2 rounded-full bg-[#27BDE3] animate-pulse"></span>
              <span className="text-xs font-bold text-[#27BDE3]">ملخص التجديد</span>
            </div>
            <span className="text-[11px] font-bold text-[#C8A45D] bg-[#C8A45D]/15 border border-[#C8A45D]/30 px-2.5 py-0.5 rounded-full">
              جاهز للدفع
            </span>
          </div>

          <div className="grid grid-cols-2 gap-3 pt-1">
            <div className="flex items-center gap-2.5">
              <div className="w-9 h-9 rounded-xl bg-[#27BDE3]/15 border border-[#27BDE3]/30 flex items-center justify-center shrink-0">
                <Award className="w-5 h-5 text-[#27BDE3]" />
              </div>
              <div>
                <p className="text-[11px] text-[#9CA3AF]">الباقة</p>
                <p className="text-sm font-bold text-white" data-testid="summary_package_name">الباقة الأساسية</p>
              </div>
            </div>

            <div className="flex items-center gap-2.5">
              <div className="w-9 h-9 rounded-xl bg-[#C8A45D]/15 border border-[#C8A45D]/30 flex items-center justify-center shrink-0">
                <CreditCard className="w-5 h-5 text-[#C8A45D]" />
              </div>
              <div>
                <p className="text-[11px] text-[#9CA3AF]">البطاقة الذكية</p>
                <p className="text-sm font-bold text-white font-mono" data-testid="summary_smart_card">**** 4587</p>
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
                <p className="text-sm font-bold text-white" data-testid="summary_plan_duration">{selectedPlan.durationLabel}</p>
              </div>
            </div>

            <div className="flex items-center gap-2.5">
              <div className="w-9 h-9 rounded-xl bg-[#C8A45D]/15 border border-[#C8A45D]/30 flex items-center justify-center shrink-0">
                <Banknote className="w-5 h-5 text-[#C8A45D]" />
              </div>
              <div>
                <p className="text-[11px] text-[#9CA3AF]">المبلغ المطلوب</p>
                <p className="text-sm font-extrabold text-[#C8A45D]" data-testid="summary_plan_price">
                  {RenewalPlanDataSource.formatPrice(selectedPlan.price, selectedPlan.currency)}
                </p>
              </div>
            </div>
          </div>
        </div>

        {/* Section Heading */}
        <div className="flex items-center justify-between">
          <h2 className="text-base font-bold text-white" data-testid="payment_methods_section_title">
            اختر وسيلة الدفع المناسبة
          </h2>
          <span className="text-xs text-[#9CA3AF]">البنوك والمحافظ المعتمدة</span>
        </div>

        {/* Enabled Payment Methods List */}
        <div className="space-y-3">
          {paymentMethods.map((method: PaymentMethod) => {
            const isSelected = method.id === selectedMethodId;
            return (
              <div
                key={method.id}
                onClick={() => setSelectedMethodId(method.id)}
                className={`p-3.5 rounded-2xl border transition-all cursor-pointer flex items-center justify-between gap-3 backdrop-blur-xl ${getAccentBorder(
                  method.id,
                  isSelected
                )}`}
                data-testid={`payment_method_card_${method.id}`}
              >
                <div className="flex items-center gap-3">
                  {isSelected ? (
                    <CheckCircle2 className="w-5 h-5 text-[#27BDE3] shrink-0" />
                  ) : (
                    <Circle className="w-5 h-5 text-[#9CA3AF] shrink-0" />
                  )}

                  <div className={`w-11 h-11 rounded-2xl ${getMethodStyle(method.id).iconBg} border flex items-center justify-center shrink-0 shadow-xs`}>
                    {getMethodIcon(method.id)}
                  </div>

                  <div>
                    <h3 className="text-sm font-bold text-white" data-testid={`provider_name_${method.id}`}>
                      {method.providerName}
                    </h3>
                    {method.accountNumber ? (
                      <p className="text-xs text-[#E5E7EB] mt-0.5">
                        <span className="text-[#9CA3AF]">رقم الحساب: </span>
                        <span className="font-mono font-medium text-[#27BDE3]" data-testid={`account_number_${method.id}`}>{method.accountNumber}</span>
                      </p>
                    ) : method.note ? (
                      <p className="text-[11px] text-[#9CA3AF] mt-0.5" data-testid={`method_note_${method.id}`}>
                        {method.note}
                      </p>
                    ) : null}
                  </div>
                </div>

                <span
                  className="text-xs font-semibold px-2.5 py-1 rounded-full bg-[#0B1739] text-[#9CA3AF] border border-[#27BDE3]/20 shrink-0"
                  data-testid={`method_type_${method.id}`}
                >
                  {method.methodType}
                </span>
              </div>
            );
          })}
        </div>
      </div>

      {/* Bottom Sticky Action Bar */}
      <div className="fixed bottom-0 inset-x-0 bg-[#0B1739]/95 backdrop-blur-2xl border-t border-[#27BDE3]/20 p-4 z-40 shadow-[0_-8px_32px_rgba(0,0,0,0.65)]">
        <div className="max-w-2xl mx-auto">
          <button
            onClick={() => onNavigateToInstructions(selectedPlan.id, selectedMethodId)}
            className="w-full py-3.5 px-6 rounded-xl bg-gradient-to-r from-[#C8A45D] via-[#E2C37D] to-[#C8A45D] hover:from-[#dfbf79] hover:to-[#dfbf79] text-[#0B1739] font-black text-base shadow-lg shadow-[#C8A45D]/30 transition-all active:scale-[0.99]"
            data-testid="payment_method_continue_button"
          >
            متابعة الدفع
          </button>
        </div>
      </div>
    </div>
  );
};
