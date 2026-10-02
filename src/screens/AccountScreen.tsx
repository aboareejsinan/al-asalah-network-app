import React from 'react';
import {
  User,
  CreditCard,
  CheckCircle2,
  Calendar,
  Clock,
  ChevronLeft,
  Tv,
  Receipt,
  Award,
} from 'lucide-react';
import { TopBar } from '../components/TopBar';

interface AccountScreenProps {
  onBackClick?: () => void;
  onNavigateToSubscription: () => void;
  onNavigateToSubscriptionDetails: () => void;
  onNavigateToTransactions: () => void;
}

export const AccountScreen: React.FC<AccountScreenProps> = ({
  onBackClick,
  onNavigateToSubscription,
  onNavigateToSubscriptionDetails,
  onNavigateToTransactions,
}) => {
  return (
    <div
      className="flex flex-col min-h-screen bg-transparent text-[#E5E7EB] pb-20"
      data-testid="account_screen_scaffold"
    >
      <TopBar
        title="حسابي"
        onBack={onBackClick}
        testTag="account_screen_title"
        backTestTag="account_back_button"
      />

      <div
        className="px-5 py-4 space-y-5 max-w-2xl mx-auto w-full"
        data-testid="account_content_column"
      >
        {/* 1. Account Summary Card */}
        <div
          className="rounded-3xl bg-gradient-to-br from-[#122452]/90 via-[#0E1D44]/95 to-[#0B1739]/95 backdrop-blur-xl border border-[#C8A45D]/35 p-5 space-y-4 shadow-xl relative overflow-hidden"
          data-testid="account_summary_card"
        >
          {/* Subtle cyan ambient glow */}
          <div className="absolute -top-10 -left-10 w-36 h-36 bg-[#27BDE3]/10 rounded-full blur-2xl pointer-events-none" />

          {/* User Info Row */}
          <div className="flex items-center gap-3.5 relative z-10">
            <div className="w-14 h-14 rounded-2xl bg-gradient-to-br from-[#C8A45D]/25 to-[#C8A45D]/10 border-2 border-[#C8A45D]/40 flex items-center justify-center shrink-0 text-[#C8A45D] shadow-md">
              <User className="w-7 h-7" />
            </div>
            <div>
              <h2
                className="text-base font-bold text-white"
                data-testid="account_user_name"
              >
                مشترك شبكة الأصالة
              </h2>
              <p className="text-xs text-[#9CA3AF] mt-0.5">البوابة الرقمية لخدمات المشتركين</p>
            </div>
          </div>

          <div className="h-px bg-white/10" />

          {/* Smart Card & Status Row */}
          <div className="flex items-center justify-between relative z-10">
            <div className="flex items-center gap-2.5">
              <div className="w-8 h-8 rounded-lg bg-[#C8A45D]/15 border border-[#C8A45D]/30 flex items-center justify-center text-[#C8A45D]">
                <CreditCard className="w-4 h-4" />
              </div>
              <div>
                <p className="text-[11px] text-[#9CA3AF]">البطاقة الذكية</p>
                <p
                  className="font-mono text-sm font-bold text-white tracking-wider"
                  data-testid="account_smart_card"
                >
                  **** 4587
                </p>
              </div>
            </div>

            <span
              className="inline-flex items-center gap-1.5 px-3 py-1 rounded-full bg-emerald-500/20 border border-emerald-500/30 text-emerald-400 text-xs font-bold"
              data-testid="account_status_badge"
            >
              <CheckCircle2 className="w-3.5 h-3.5" />
              <span>نشط</span>
            </span>
          </div>
        </div>

        {/* Section Title: ملخص الاشتراك */}
        <h3
          className="text-base font-bold text-white"
          data-testid="account_subscription_summary_title"
        >
          ملخص الاشتراك
        </h3>

        {/* Subscription Summary Card */}
        <div
          className="rounded-3xl bg-gradient-to-br from-[#0F214B]/90 to-[#0B1739]/95 backdrop-blur-xl border border-[#27BDE3]/25 p-5 space-y-4 shadow-xl"
          data-testid="account_subscription_summary_card"
        >
          {/* Header Row */}
          <div className="flex items-center justify-between">
            <div className="flex items-center gap-2.5">
              <div className="w-9 h-9 rounded-xl bg-[#27BDE3]/15 border border-[#27BDE3]/30 flex items-center justify-center text-[#27BDE3] shrink-0">
                <Award className="w-5 h-5" />
              </div>
              <div>
                <p className="text-[11px] text-[#9CA3AF]">الباقة الحالية</p>
                <p
                  className="text-sm font-bold text-white"
                  data-testid="summary_package_name"
                >
                  الباقة الأساسية
                </p>
              </div>
            </div>

            <span
              className="inline-flex items-center gap-1 px-2.5 py-0.5 rounded-full bg-emerald-500/20 border border-emerald-500/30 text-emerald-400 text-xs font-bold"
              data-testid="summary_status_badge"
            >
              <CheckCircle2 className="w-3 h-3" />
              <span>نشط</span>
            </span>
          </div>

          <div className="h-px bg-white/10" />

          {/* Dates grid */}
          <div className="grid grid-cols-2 gap-3">
            <div className="flex items-center gap-2.5">
              <div className="w-8 h-8 rounded-lg bg-[#27BDE3]/15 border border-[#27BDE3]/30 flex items-center justify-center text-[#27BDE3] shrink-0">
                <Calendar className="w-4 h-4" />
              </div>
              <div>
                <p className="text-[11px] text-[#9CA3AF]">تاريخ البدء</p>
                <p
                  className="text-xs font-bold text-white"
                  data-testid="summary_start_date"
                >
                  15 أغسطس 2026
                </p>
              </div>
            </div>

            <div className="flex items-center gap-2.5">
              <div className="w-8 h-8 rounded-lg bg-[#C8A45D]/15 border border-[#C8A45D]/30 flex items-center justify-center text-[#C8A45D] shrink-0">
                <Calendar className="w-4 h-4" />
              </div>
              <div>
                <p className="text-[11px] text-[#9CA3AF]">تاريخ الانتهاء</p>
                <p
                  className="text-xs font-bold text-white"
                  data-testid="summary_expiry_date"
                >
                  15 أكتوبر 2026
                </p>
              </div>
            </div>
          </div>

          <div className="flex items-center gap-2.5 pt-1">
            <div className="w-8 h-8 rounded-lg bg-[#C8A45D]/15 border border-[#C8A45D]/30 flex items-center justify-center text-[#C8A45D] shrink-0">
              <Clock className="w-4 h-4" />
            </div>
            <div>
              <p className="text-[11px] text-[#9CA3AF]">المدة المتبقية</p>
              <p
                className="text-xs font-bold text-white"
                data-testid="summary_remaining_days"
              >
                16 يوم
              </p>
            </div>
          </div>

          {/* View Details Button */}
          <button
            onClick={onNavigateToSubscriptionDetails}
            className="w-full py-2.5 px-4 rounded-xl bg-[#0B1739] hover:bg-[#142857] border border-[#27BDE3]/35 hover:border-[#27BDE3]/60 text-white text-xs font-bold transition-all mt-2 active:scale-[0.98] shadow-sm"
            data-testid="btn_view_subscription_details"
          >
            عرض تفاصيل الاشتراك
          </button>
        </div>

        {/* Section Title: إدارة الحساب */}
        <h3
          className="text-base font-bold text-white"
          data-testid="account_actions_section_title"
        >
          إدارة الحساب
        </h3>

        {/* Action Cards */}
        <div className="space-y-3">
          {/* Action Card 1: اشتراكي */}
          <div
            onClick={onNavigateToSubscription}
            className="p-4 rounded-2xl bg-[#0B1739]/85 backdrop-blur-xl border border-[#C8A45D]/25 hover:border-[#C8A45D]/50 hover:bg-[#12234e] transition-all cursor-pointer flex items-center justify-between shadow-md active:scale-[0.99] group"
            data-testid="account_action_subscription"
          >
            <div className="flex items-center gap-3.5">
              <div className="w-10 h-10 rounded-xl bg-[#C8A45D]/15 border border-[#C8A45D]/30 text-[#C8A45D] flex items-center justify-center shrink-0 group-hover:scale-105 transition-transform">
                <Tv className="w-5 h-5" />
              </div>
              <div>
                <h4 className="text-sm font-bold text-white group-hover:text-[#C8A45D] transition-colors">اشتراكي</h4>
                <p className="text-xs text-[#9CA3AF] mt-0.5">
                  عرض تفاصيل باقة البث وصلاحية الاشتراك
                </p>
              </div>
            </div>
            <div className="w-8 h-8 rounded-full bg-[#0B1739] border border-[#C8A45D]/20 flex items-center justify-center text-[#9CA3AF] group-hover:text-[#C8A45D] transition-colors">
              <ChevronLeft className="w-4 h-4" />
            </div>
          </div>

          {/* Action Card 2: سجل العمليات */}
          <div
            onClick={onNavigateToTransactions}
            className="p-4 rounded-3xl bg-gradient-to-r from-[#12163b]/85 to-[#0B1739]/95 backdrop-blur-xl border border-[#8B5CF6]/30 hover:border-[#8B5CF6]/60 hover:bg-[#161a47] transition-all cursor-pointer flex items-center justify-between shadow-lg shadow-[#8B5CF6]/5 active:scale-[0.99] group"
            data-testid="account_action_transactions"
          >
            <div className="flex items-center gap-3.5">
              <div className="w-11 h-11 rounded-2xl bg-[#8B5CF6]/15 border border-[#8B5CF6]/35 text-[#A78BFA] flex items-center justify-center shrink-0 group-hover:scale-105 transition-transform shadow-xs">
                <Receipt className="w-5 h-5" />
              </div>
              <div>
                <h4 className="text-sm font-bold text-white group-hover:text-[#A78BFA] transition-colors">سجل العمليات</h4>
                <p className="text-xs text-[#9CA3AF] mt-0.5">
                  متابعة طلبات التجديد وحالة الدفع السابقة
                </p>
              </div>
            </div>
            <div className="w-8 h-8 rounded-full bg-[#0B1739] border border-[#8B5CF6]/25 flex items-center justify-center text-[#9CA3AF] group-hover:text-[#A78BFA] transition-colors">
              <ChevronLeft className="w-4 h-4" />
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};
