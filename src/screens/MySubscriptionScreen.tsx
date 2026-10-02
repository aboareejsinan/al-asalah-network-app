import React from 'react';
import { CreditCard, Calendar, Clock, RefreshCw, CheckCircle2 } from 'lucide-react';
import { TopBar } from '../components/TopBar';

interface MySubscriptionScreenProps {
  onBackClick: () => void;
  onNavigateToRenewal: () => void;
}

export const MySubscriptionScreen: React.FC<MySubscriptionScreenProps> = ({
  onBackClick,
  onNavigateToRenewal,
}) => {
  return (
    <div className="min-h-screen bg-[#0B1739] pb-12 text-[#E5E7EB]">
      <TopBar
        title="اشتراكي"
        onBack={onBackClick}
        testTag="subscription_screen_title"
        backTestTag="subscription_back_button"
      />

      <div className="px-5 py-4 space-y-4 max-w-lg mx-auto" data-testid="subscription_content_column">
        {/* Prominent Subscription Information Card */}
        <div
          data-testid="subscription_info_card"
          className="rounded-3xl p-6 bg-gradient-to-br from-[#12224d] via-[#0B1739] to-[#081026] border border-[#C8A45D]/40 shadow-2xl space-y-5 relative overflow-hidden"
        >
          {/* Subtle cyan ambient background glow */}
          <div className="absolute -top-10 -left-10 w-40 h-40 bg-[#27BDE3]/10 rounded-full blur-2xl pointer-events-none" />

          {/* Header Row: Package name + Green Status Indicator */}
          <div className="flex items-center justify-between relative z-10">
            <div className="flex items-center gap-3">
              <div className="w-12 h-12 rounded-2xl bg-[#C8A45D]/15 border border-[#C8A45D]/30 flex items-center justify-center">
                <CreditCard className="w-6 h-6 text-[#C8A45D]" />
              </div>
              <div className="space-y-0.5">
                <h2 className="text-lg font-bold text-white tracking-tight" data-testid="subscription_package_name">
                  الباقة الأساسية
                </h2>
                <p className="text-xs text-[#9CA3AF]" data-testid="subscription_package_type">
                  البث التلفزيوني الرقمي
                </p>
              </div>
            </div>

            {/* Status indicator in green */}
            <div
              data-testid="subscription_status_badge"
              className="flex items-center gap-1.5 px-3 py-1.5 rounded-full bg-emerald-500/20 border border-emerald-500/40 text-emerald-400 text-xs font-bold"
            >
              <span className="w-2 h-2 rounded-full bg-emerald-400 animate-pulse" />
              <span>نشط</span>
            </div>
          </div>

          <div className="border-t border-[#27BDE3]/15" />

          {/* Smart Card container */}
          <div
            data-testid="smart_card_container"
            className="flex items-center justify-between p-3.5 rounded-2xl bg-[#0B1739]/70 border border-[#27BDE3]/20"
          >
            <div className="flex items-center gap-2.5">
              <CreditCard className="w-5 h-5 text-[#27BDE3]" />
              <span className="text-sm font-medium text-[#9CA3AF]">البطاقة الذكية</span>
            </div>
            <span className="text-sm font-bold text-white tracking-wider" data-testid="smart_card_number">
              **** 4587
            </span>
          </div>

          {/* Dates & Remaining Details Grid */}
          <div className="space-y-2.5">
            <div className="grid grid-cols-2 gap-2.5">
              {/* Start Date */}
              <div
                data-testid="subscription_start_date_card"
                className="p-3 rounded-2xl bg-[#0B1739]/80 border border-[#27BDE3]/15 space-y-1"
              >
                <div className="flex items-center gap-1.5 text-xs text-[#9CA3AF]">
                  <Calendar className="w-3.5 h-3.5 text-[#27BDE3]" />
                  <span>تاريخ البدء</span>
                </div>
                <p className="text-sm font-semibold text-white" data-testid="subscription_start_date_value">
                  15 أغسطس 2026
                </p>
              </div>

              {/* Expiry Date */}
              <div
                data-testid="subscription_expiry_date_card"
                className="p-3 rounded-2xl bg-[#0B1739]/80 border border-[#27BDE3]/15 space-y-1"
              >
                <div className="flex items-center gap-1.5 text-xs text-[#9CA3AF]">
                  <Calendar className="w-3.5 h-3.5 text-[#C8A45D]" />
                  <span>تاريخ الانتهاء</span>
                </div>
                <p className="text-sm font-semibold text-white" data-testid="subscription_expiry_date_value">
                  15 أكتوبر 2026
                </p>
              </div>
            </div>

            {/* Remaining Period */}
            <div
              data-testid="subscription_remaining_card"
              className="flex items-center justify-between p-3 rounded-2xl bg-[#C8A45D]/10 border border-[#C8A45D]/30"
            >
              <div className="flex items-center gap-2 text-xs text-[#C8A45D]">
                <Clock className="w-4 h-4 text-[#C8A45D]" />
                <span>الفترة المتبقية</span>
              </div>
              <span className="text-sm font-bold text-[#C8A45D]" data-testid="subscription_remaining_value">
                متبقي 16 يوم
              </span>
            </div>
          </div>

          {/* Action button */}
          <button
            onClick={onNavigateToRenewal}
            data-testid="renew_subscription_button"
            className="w-full h-12 rounded-xl bg-[#C8A45D] hover:bg-[#d9b66f] active:scale-[0.99] font-black text-[#0B1739] text-sm shadow-lg shadow-[#C8A45D]/25 flex items-center justify-center gap-2 transition-all"
          >
            <RefreshCw className="w-4 h-4" />
            <span>تجديد الاشتراك</span>
          </button>
        </div>
      </div>
    </div>
  );
};
