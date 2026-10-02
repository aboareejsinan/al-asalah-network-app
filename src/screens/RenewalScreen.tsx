import React, { useState } from 'react';
import {
  CreditCard,
  Zap,
  Calendar,
  Sparkles,
  Gem,
  Award,
  CheckCircle2,
  Circle,
} from 'lucide-react';
import { TopBar } from '../components/TopBar';
import { RenewalPlanDataSource } from '../data';

interface RenewalScreenProps {
  onBackClick: () => void;
  onNavigateToPaymentMethod: (planId: string) => void;
}

export const RenewalScreen: React.FC<RenewalScreenProps> = ({
  onBackClick,
  onNavigateToPaymentMethod,
}) => {
  const plans = RenewalPlanDataSource.getEnabledPlans();
  const [selectedPlanId, setSelectedPlanId] = useState(plans[0]?.id || 'plan_1_month');

  const getPlanDetails = (durationValue: number) => {
    switch (durationValue) {
      case 1:
        return { color: '#27BDE3', icon: Zap, labelColor: 'text-[#38BDF8]' };
      case 2:
        return { color: '#38BDF8', icon: Calendar, labelColor: 'text-[#38BDF8]' };
      case 3:
        return { color: '#8B5CF6', icon: Sparkles, labelColor: 'text-[#C4B5FD]' };
      case 6:
        return { color: '#C8A45D', icon: Gem, labelColor: 'text-[#E0B865]' };
      case 12:
        return { color: '#EAB308', icon: Award, labelColor: 'text-[#FDE047]' };
      default:
        return { color: '#27BDE3', icon: Calendar, labelColor: 'text-[#38BDF8]' };
    }
  };

  return (
    <div className="min-h-screen bg-transparent pb-28 text-[#E5E7EB]">
      <TopBar
        title="تجديد الاشتراك"
        onBack={onBackClick}
        testTag="renewal_screen_title"
        backTestTag="renewal_back_button"
      />

      <div className="px-5 py-4 space-y-4 max-w-lg mx-auto" data-testid="renewal_content_column">
        {/* Subscriber Info Card: Current Package & Smart Card */}
        <div
          data-testid="renewal_current_info_card"
          className="rounded-2xl p-4 bg-[#0B1739]/85 backdrop-blur-md border border-[#27BDE3]/30 flex items-center justify-between shadow-lg"
        >
          <div className="flex items-center gap-3">
            <div className="w-10 h-10 rounded-full bg-[#27BDE3]/15 border border-[#27BDE3]/30 flex items-center justify-center">
              <CreditCard className="w-5 h-5 text-[#27BDE3]" />
            </div>
            <div>
              <span className="text-[11px] text-[#9CA3AF] block">الباقة الحالية</span>
              <span className="text-sm font-bold text-white" data-testid="renewal_current_package">
                الباقة الأساسية
              </span>
            </div>
          </div>

          <div className="text-left space-y-0.5">
            <span className="text-[11px] text-[#9CA3AF] block">البطاقة الذكية</span>
            <span className="text-sm font-bold text-white tracking-wide" data-testid="renewal_smart_card">
              **** 4587
            </span>
          </div>
        </div>

        {/* Section Heading */}
        <h2 className="text-base font-bold text-white pt-1" data-testid="renewal_plans_section_title">
          اختر مدة التجديد
        </h2>

        {/* Plans List */}
        <div className="space-y-2.5">
          {plans.map((plan) => {
            const isSelected = plan.id === selectedPlanId;
            const { color, icon: PlanIcon } = getPlanDetails(plan.durationValue);

            return (
              <div
                key={plan.id}
                onClick={() => setSelectedPlanId(plan.id)}
                data-testid={`renewal_plan_card_${plan.id}`}
                className={`rounded-2xl p-4 cursor-pointer transition-all border flex items-center justify-between ${
                  isSelected
                    ? 'border-2 shadow-lg bg-gradient-to-r from-[#12224d] to-[#0B1739]'
                    : 'border-[#27BDE3]/15 bg-[#0B1739]/80 hover:bg-[#12224d]/60'
                }`}
                style={{
                  borderColor: isSelected ? color : 'rgba(39, 189, 227, 0.2)',
                }}
              >
                {/* Radio + Icon + Label */}
                <div className="flex items-center gap-3">
                  {isSelected ? (
                    <CheckCircle2 className="w-5 h-5" style={{ color }} />
                  ) : (
                    <Circle className="w-5 h-5 text-[#9CA3AF]" />
                  )}

                  <div
                    className="w-11 h-11 rounded-xl flex items-center justify-center border"
                    style={{
                      backgroundColor: `${color}20`,
                      borderColor: `${color}40`,
                    }}
                  >
                    <PlanIcon className="w-5 h-5" style={{ color }} />
                  </div>

                  <div>
                    <div className="flex items-center gap-2">
                      <span
                        className="text-sm font-bold text-white"
                        data-testid={`plan_duration_${plan.id}`}
                      >
                        {plan.durationLabel}
                      </span>
                      {plan.badgeLabel && (
                        <span
                          className="px-2 py-0.5 rounded-full text-[10px] font-bold"
                          style={{
                            backgroundColor: `${color}25`,
                            color: color,
                          }}
                        >
                          {plan.badgeLabel}
                        </span>
                      )}
                    </div>
                  </div>
                </div>

                {/* Price */}
                <span
                  className="text-sm font-bold"
                  style={{ color: isSelected ? color : '#E5E7EB' }}
                  data-testid={`plan_price_${plan.id}`}
                >
                  {RenewalPlanDataSource.formatPrice(plan.price, plan.currency)}
                </span>
              </div>
            );
          })}
        </div>
      </div>

      {/* Fixed bottom bar */}
      <div className="fixed bottom-0 left-0 right-0 z-30 p-4 bg-[#0B1739]/95 backdrop-blur-md border-t border-[#27BDE3]/20 shadow-2xl">
        <div className="max-w-lg mx-auto">
          <button
            onClick={() => onNavigateToPaymentMethod(selectedPlanId)}
            data-testid="renewal_continue_button"
            className="w-full h-12 rounded-xl bg-[#C8A45D] hover:bg-[#d9b66f] active:scale-[0.99] font-black text-[#0B1739] text-base shadow-lg shadow-[#C8A45D]/25 transition-all flex items-center justify-center"
          >
            متابعة
          </button>
        </div>
      </div>
    </div>
  );
};
