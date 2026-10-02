import React from 'react';
import {
  CreditCard,
  Calendar,
  Clock,
  CheckCircle2,
  RefreshCw,
  Award,
  Check,
} from 'lucide-react';
import { TopBar } from '../components/TopBar';

interface SubscriptionDetailsScreenProps {
  onBackClick: () => void;
  onNavigateToRenewal: () => void;
}

export const SubscriptionDetailsScreen: React.FC<SubscriptionDetailsScreenProps> = ({
  onBackClick,
  onNavigateToRenewal,
}) => {
  const packageFeatures = [
    'بث رقمي عالي الجودة Full HD',
    'تحديثات فورية ومستمرة للقنوات',
    'دعم فني هندسي على مدار الساعة',
  ];

  return (
    <div
      className="flex flex-col min-h-screen bg-transparent text-[#E5E7EB] pb-20"
      data-testid="subscription_details_scaffold"
    >
      <TopBar
        title="تفاصيل الاشتراك"
        onBack={onBackClick}
        testTag="subscription_details_title"
        backTestTag="subscription_details_back_button"
      />

      <div className="px-5 py-4 space-y-4 max-w-2xl mx-auto w-full">
        {/* Main Details Card */}
        <div
          className="rounded-3xl bg-[#0B1739]/85 backdrop-blur-xl border border-[#C8A45D]/30 p-5 space-y-4 shadow-2xl relative overflow-hidden"
          data-testid="subscription_details_card"
        >
          {/* Subtle cyan glow accent */}
          <div className="absolute top-0 right-0 w-36 h-36 bg-[#27BDE3]/10 rounded-full blur-2xl pointer-events-none" />

          {/* Header Row */}
          <div className="flex items-center justify-between relative z-10">
            <div className="flex items-center gap-3">
              <div className="w-12 h-12 rounded-2xl bg-[#C8A45D]/15 border border-[#C8A45D]/30 flex items-center justify-center text-[#C8A45D] shrink-0">
                <Award className="w-6 h-6" />
              </div>
              <div>
                <p className="text-xs text-[#9CA3AF]">الباقة الحالية</p>
                <h2
                  className="text-base font-bold text-white tracking-tight"
                  data-testid="subscription_details_package"
                >
                  الباقة الأساسية
                </h2>
              </div>
            </div>

            <span
              className="inline-flex items-center gap-1.5 px-3 py-1 rounded-full bg-emerald-500/15 border border-emerald-500/30 text-emerald-400 text-xs font-bold"
              data-testid="subscription_details_status"
            >
              <CheckCircle2 className="w-3.5 h-3.5" />
              <span>نشط</span>
            </span>
          </div>

          <div className="h-px bg-[#27BDE3]/15" />

          {/* Details Rows */}
          <div className="space-y-3 divide-y divide-[#27BDE3]/10">
            {/* Smart card */}
            <div className="flex items-center justify-between pt-1">
              <div className="flex items-center gap-2.5">
                <div className="w-8 h-8 rounded-xl bg-[#27BDE3]/15 border border-[#27BDE3]/25 flex items-center justify-center text-[#27BDE3] shrink-0">
                  <CreditCard className="w-4 h-4" />
                </div>
                <span className="text-xs text-[#9CA3AF]">رقم البطاقة الذكية</span>
              </div>
              <span
                className="font-mono text-xs font-bold text-white tracking-wider"
                data-testid="subscription_details_smart_card"
              >
                **** 4587
              </span>
            </div>

            {/* Start date */}
            <div className="flex items-center justify-between pt-3">
              <div className="flex items-center gap-2.5">
                <div className="w-8 h-8 rounded-xl bg-[#27BDE3]/15 border border-[#27BDE3]/25 flex items-center justify-center text-[#27BDE3] shrink-0">
                  <Calendar className="w-4 h-4" />
                </div>
                <span className="text-xs text-[#9CA3AF]">تاريخ البدء</span>
              </div>
              <span
                className="text-xs font-bold text-white"
                data-testid="subscription_details_start_date"
              >
                15 أغسطس 2026
              </span>
            </div>

            {/* Expiry date */}
            <div className="flex items-center justify-between pt-3">
              <div className="flex items-center gap-2.5">
                <div className="w-8 h-8 rounded-xl bg-[#C8A45D]/15 border border-[#C8A45D]/25 flex items-center justify-center text-[#C8A45D] shrink-0">
                  <Calendar className="w-4 h-4" />
                </div>
                <span className="text-xs text-[#9CA3AF]">تاريخ الانتهاء</span>
              </div>
              <span
                className="text-xs font-bold text-[#C8A45D]"
                data-testid="subscription_details_expiry_date"
              >
                15 أكتوبر 2026
              </span>
            </div>

            {/* Remaining duration */}
            <div className="flex items-center justify-between pt-3">
              <div className="flex items-center gap-2.5">
                <div className="w-8 h-8 rounded-xl bg-[#27BDE3]/15 border border-[#27BDE3]/25 flex items-center justify-center text-[#27BDE3] shrink-0">
                  <Clock className="w-4 h-4" />
                </div>
                <span className="text-xs text-[#9CA3AF]">المدة المتبقية</span>
              </div>
              <span
                className="text-xs font-bold text-white"
                data-testid="subscription_details_remaining"
              >
                16 يوم
              </span>
            </div>
          </div>
        </div>

        {/* Package Features Glass Card */}
        <div
          className="rounded-2xl bg-[#0B1739]/70 backdrop-blur-md border border-[#27BDE3]/20 p-5 space-y-3.5 shadow-lg"
          data-testid="subscription_features_card"
        >
          <h3
            className="text-sm font-bold text-white flex items-center gap-2"
            data-testid="subscription_features_title"
          >
            <span className="w-1.5 h-4 rounded-full bg-[#27BDE3]" />
            <span>مزايا الباقة</span>
          </h3>

          <div className="space-y-2.5">
            {packageFeatures.map((feature, index) => (
              <div
                key={index}
                className="flex items-center gap-2.5"
                data-testid={`subscription_feature_${index}`}
              >
                <div className="w-6 h-6 rounded-full bg-emerald-500/15 border border-emerald-400/30 flex items-center justify-center text-emerald-400 shrink-0">
                  <Check className="w-3.5 h-3.5" />
                </div>
                <span className="text-xs text-[#E5E7EB]">{feature}</span>
              </div>
            ))}
          </div>
        </div>

        {/* Primary Renew Button */}
        <button
          onClick={onNavigateToRenewal}
          className="w-full py-4 px-6 rounded-2xl bg-gradient-to-r from-[#27BDE3] to-[#1DA6CA] hover:from-[#32c5eb] hover:to-[#27BDE3] text-[#0B1739] font-black text-sm tracking-wide shadow-lg shadow-[#27BDE3]/25 transition-all flex items-center justify-center gap-2 mt-4 active:scale-[0.99]"
          data-testid="subscription_details_renew_button"
        >
          <RefreshCw className="w-5 h-5 text-[#0B1739]" />
          <span>تجديد الاشتراك الآن</span>
        </button>
      </div>
    </div>
  );
};
