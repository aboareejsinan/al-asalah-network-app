import React, { useState } from 'react';
import {
  Bell,
  User,
  CreditCard,
  AlertTriangle,
  CardSim,
  RefreshCw,
  Wallet,
  Radio,
  Globe,
  Headset,
  Megaphone,
  ChevronLeft,
  Newspaper,
  Calendar,
  MapPin,
  Tv,
  HelpCircle,
  MessageCircle,
} from 'lucide-react';
import { homeMockChannels } from '../data';

interface HomeScreenProps {
  onNavigateToSubscription: () => void;
  onNavigateToRenewal: () => void;
  onNavigateToTransactions: () => void;
  onNavigateToAccount: () => void;
  onNavigateToNotifications: () => void;
  onNavigateToChannels: () => void;
  onNavigateToReceptionGuide: () => void;
  onNavigateToFrequencies: () => void;
  onNavigateToCoverage: () => void;
  onNavigateToReceiverCardInfo: () => void;
  onNavigateToSupport: () => void;
  onNavigateToFaq: () => void;
  onNavigateToSupportTicket?: () => void;
  onTransactionClick?: (tx: any) => void;
  onPackageClick?: (pkg: any) => void;
}

export const HomeScreen: React.FC<HomeScreenProps> = ({
  onNavigateToSubscription,
  onNavigateToRenewal,
  onNavigateToTransactions,
  onNavigateToAccount,
  onNavigateToNotifications,
  onNavigateToChannels,
  onNavigateToReceptionGuide,
  onNavigateToFrequencies,
  onNavigateToCoverage,
  onNavigateToReceiverCardInfo,
  onNavigateToSupport,
  onNavigateToFaq,
}) => {
  const [selectedChannelFilter, setSelectedChannelFilter] = useState('الكل');
  const [reminded, setReminded] = useState(false);

  const filterLabels = ['الكل', 'المجانية', 'المشفرة', 'جديد'];

  const quickServices = [
    {
      title: 'اشتراكي',
      icon: CreditCard,
      accentColor: '#A5B4FC',
      bgColor: 'rgba(99, 102, 241, 0.2)',
      onClick: onNavigateToSubscription,
    },
    {
      title: 'التجديد',
      icon: RefreshCw,
      accentColor: '#6EE7B7',
      bgColor: 'rgba(16, 185, 129, 0.2)',
      onClick: onNavigateToRenewal,
    },
    {
      title: 'الدفع',
      icon: Wallet,
      accentColor: '#7DD3FC',
      bgColor: 'rgba(2, 132, 199, 0.2)',
      onClick: onNavigateToTransactions,
    },
    {
      title: 'الترددات',
      icon: Radio,
      accentColor: '#FDE047',
      bgColor: 'rgba(217, 119, 6, 0.2)',
      onClick: onNavigateToFrequencies,
    },
    {
      title: 'التغطية',
      icon: Globe,
      accentColor: '#FDA4AF',
      bgColor: 'rgba(225, 29, 72, 0.2)',
      onClick: onNavigateToCoverage,
    },
    {
      title: 'الدعم',
      icon: Headset,
      accentColor: '#93C5FD',
      bgColor: 'rgba(37, 99, 235, 0.2)',
      onClick: onNavigateToSupport,
    },
  ];

  return (
    <div className="min-h-screen bg-transparent pb-24 text-[#E5E7EB]">
      {/* Top Header Bar */}
      <div className="flex items-center justify-between px-5 pt-5 pb-3" data-testid="top_header_bar">
        <div className="flex flex-col" data-testid="header_title_column">
          <h1 className="text-xl font-bold text-white tracking-tight" data-testid="app_title_text">
            شبكة الأصالة الرقمية
          </h1>
          <span className="text-xs font-medium text-[#9CA3AF]" data-testid="app_subtitle_text">
            البوابة الرقمية لخدمات المشتركين
          </span>
        </div>

        <div className="flex items-center gap-2.5" data-testid="header_actions_row">
          <button
            onClick={onNavigateToNotifications}
            data-testid="notification_button"
            className="w-11 h-11 rounded-full bg-white/10 flex items-center justify-center text-slate-100 hover:bg-white/20 active:scale-95 transition-all relative"
            aria-label="الإشعارات"
          >
            <Bell className="w-5 h-5 text-slate-100" />
            <span className="absolute top-2.5 right-2.5 w-2 h-2 rounded-full bg-[#27BDE3] ring-2 ring-[#0B1739]" />
          </button>
          <button
            onClick={onNavigateToAccount}
            data-testid="profile_button"
            className="w-11 h-11 rounded-full bg-white/10 flex items-center justify-center text-slate-100 hover:bg-white/20 active:scale-95 transition-all"
            aria-label="الحساب"
          >
            <User className="w-5 h-5 text-slate-100" />
          </button>
        </div>
      </div>

      <div className="px-5 space-y-4 pt-1">
        {/* Subscription Status Card */}
        <div
          data-testid="subscription_status_card"
          className="rounded-3xl p-5 bg-gradient-to-r from-[#2D3B68] to-[#38315E] border border-indigo-300/25 shadow-xl shadow-indigo-950/30"
        >
          <div className="flex items-start justify-between">
            <div className="space-y-1">
              <div className="flex items-center gap-2">
                <span className="w-2.5 h-2.5 rounded-full bg-emerald-400 inline-block animate-pulse" data-testid="active_status_indicator" />
                <h3 className="text-base font-bold text-slate-50" data-testid="subscription_title_text">
                  اشتراكك فعال
                </h3>
              </div>
              <p className="text-sm font-medium text-indigo-200" data-testid="subscription_subtitle_text">
                الباقة الأساسية
              </p>
            </div>

            <div className="flex items-center gap-1.5 px-2.5 py-1.5 rounded-xl bg-white/15 border border-indigo-200/25 text-xs text-indigo-100 font-semibold">
              <CreditCard className="w-3.5 h-3.5 text-indigo-300" />
              <span data-testid="subscription_smart_card_text">**** 4587</span>
            </div>
          </div>

          <div className="my-4 border-t border-white/15" />

          <div className="flex items-center justify-between">
            <div>
              <p className="text-xs text-indigo-200" data-testid="subscription_expiry_label">
                ينتهي في
              </p>
              <p className="text-sm font-semibold text-white mt-0.5" data-testid="subscription_expiry_date">
                15 أكتوبر 2026
              </p>
            </div>
            <span
              className="px-2.5 py-1 rounded-lg bg-emerald-500/20 border border-emerald-500/30 text-emerald-300 text-xs font-bold"
              data-testid="subscription_remaining_badge"
            >
              متبقي 16 يوم
            </span>
          </div>

          <button
            onClick={onNavigateToRenewal}
            data-testid="renew_now_button"
            className="w-full mt-4 h-12 rounded-xl bg-sky-500 hover:bg-sky-400 active:scale-[0.99] font-bold text-white text-sm shadow-md shadow-sky-900/40 transition-all flex items-center justify-center gap-2"
          >
            تجديد الآن
          </button>
        </div>

        {/* Important Alert Card */}
        <div
          data-testid="important_alert_card"
          className="rounded-2xl p-4 bg-gradient-to-r from-[#7A3E1D] to-[#8C4722] border border-orange-300/35 flex items-center gap-3.5"
        >
          <div className="w-10 h-10 rounded-full bg-black/25 flex items-center justify-center shrink-0">
            <AlertTriangle className="w-5 h-5 text-yellow-300" data-testid="alert_icon" />
          </div>
          <div className="flex-1 space-y-0.5">
            <h4 className="text-xs font-bold text-amber-100" data-testid="alert_title_text">
              تنبيه مهم
            </h4>
            <p className="text-sm font-semibold text-white" data-testid="alert_message_text">
              أعمال صيانة مجدولة على الشبكة
            </p>
            <p className="text-xs text-orange-200" data-testid="alert_secondary_text">
              قد تتأثر بعض المناطق مؤقتاً
            </p>
          </div>
        </div>

        {/* Quick Services Section */}
        <div className="pt-2 space-y-3" data-testid="quick_services_section">
          <h2 className="text-base font-bold text-slate-100" data-testid="quick_services_title">
            الخدمات السريعة
          </h2>
          <div className="grid grid-cols-3 gap-2.5">
            {quickServices.map((service) => {
              const IconComp = service.icon;
              return (
                <button
                  key={service.title}
                  onClick={service.onClick}
                  data-testid={`service_card_${service.title}`}
                  className="rounded-2xl p-3 bg-white/10 hover:bg-white/15 border border-white/15 active:scale-95 transition-all flex flex-col items-center justify-center gap-2 text-center"
                >
                  <div
                    className="w-11 h-11 rounded-full flex items-center justify-center shadow-inner"
                    style={{ backgroundColor: service.bgColor }}
                  >
                    <IconComp className="w-5 h-5" style={{ color: service.accentColor }} />
                  </div>
                  <span
                    className="text-xs font-semibold text-slate-100"
                    data-testid={`service_label_${service.title}`}
                  >
                    {service.title}
                  </span>
                </button>
              );
            })}
          </div>
        </div>

        {/* What's New Section */}
        <div className="pt-2 space-y-3" data-testid="whats_new_section">
          <h2 className="text-base font-bold text-slate-100" data-testid="whats_new_title">
            ما الجديد في شبكة الأصالة
          </h2>
          <div
            data-testid="whats_new_card"
            className="rounded-2xl p-4.5 bg-gradient-to-r from-[#0F5B63] to-[#146A72] border border-teal-400/35 space-y-3 shadow-lg"
          >
            <div className="flex items-center justify-between">
              <span
                data-testid="whats_new_badge"
                className="px-2.5 py-1 rounded-md bg-teal-400/25 border border-teal-400/40 text-teal-200 text-xs font-bold"
              >
                جديد
              </span>
              <div className="w-8 h-8 rounded-full bg-teal-400/20 flex items-center justify-center">
                <Megaphone className="w-4 h-4 text-teal-300" />
              </div>
            </div>

            <div>
              <h3 className="text-sm font-bold text-teal-50" data-testid="whats_new_card_title">
                تحديث جديد في خدمات الشبكة
              </h3>
              <p className="text-xs text-teal-100/90 mt-1 leading-relaxed" data-testid="whats_new_description">
                تابع آخر الإضافات والتحديثات التي تقدمها شبكة الأصالة للمشتركين.
              </p>
            </div>

            <button
              onClick={onNavigateToNotifications}
              data-testid="whats_new_action_row"
              className="flex items-center gap-1 text-xs font-bold text-teal-300 hover:text-teal-200 transition-colors"
            >
              <span data-testid="whats_new_action_text">عرض التفاصيل</span>
              <ChevronLeft className="w-4 h-4" />
            </button>
          </div>
        </div>

        {/* Latest News Section */}
        <div className="pt-2 space-y-3" data-testid="latest_news_section">
          <div className="flex items-center justify-between">
            <h2 className="text-base font-bold text-slate-100" data-testid="latest_news_title">
              آخر الأخبار
            </h2>
            <button
              onClick={onNavigateToNotifications}
              data-testid="latest_news_see_all"
              className="flex items-center gap-0.5 text-xs font-semibold text-sky-400 hover:text-sky-300 transition-colors"
            >
              <span>عرض الكل</span>
              <ChevronLeft className="w-4 h-4" />
            </button>
          </div>

          <div className="space-y-2.5">
            {/* News 1 */}
            <div
              data-testid="news_1"
              className="rounded-2xl p-4 bg-gradient-to-r from-[#1E3A5F] to-[#1A4971] border border-sky-400/25 space-y-2"
            >
              <div className="flex items-center justify-between">
                <span
                  data-testid="news_1_category"
                  className="px-2 py-0.5 rounded-md bg-sky-400/20 border border-sky-400/30 text-sky-300 text-[11px] font-bold"
                >
                  أخبار الشبكة
                </span>
                <div className="w-7 h-7 rounded-full bg-white/10 flex items-center justify-center">
                  <Newspaper className="w-3.5 h-3.5 text-sky-300" />
                </div>
              </div>
              <h3 className="text-sm font-bold text-sky-50" data-testid="news_1_title">
                توسعة جديدة في خدمات شبكة الأصالة
              </h3>
              <p className="text-xs text-sky-200/90 leading-relaxed" data-testid="news_1_summary">
                تواصل الشبكة تطوير خدماتها وتحسين تجربة المشتركين.
              </p>
            </div>

            {/* News 2 */}
            <div
              data-testid="news_2"
              className="rounded-2xl p-4 bg-gradient-to-r from-[#184363] to-[#155375] border border-cyan-400/25 space-y-2"
            >
              <div className="flex items-center justify-between">
                <span
                  data-testid="news_2_category"
                  className="px-2 py-0.5 rounded-md bg-cyan-400/20 border border-cyan-400/30 text-cyan-300 text-[11px] font-bold"
                >
                  تحديث
                </span>
                <div className="w-7 h-7 rounded-full bg-white/10 flex items-center justify-center">
                  <Newspaper className="w-3.5 h-3.5 text-cyan-300" />
                </div>
              </div>
              <h3 className="text-sm font-bold text-cyan-50" data-testid="news_2_title">
                تحسينات جديدة في منظومة البث الرقمي
              </h3>
              <p className="text-xs text-cyan-200/90 leading-relaxed" data-testid="news_2_summary">
                تحديثات مستمرة لدعم استقرار وجودة خدمات الشبكة.
              </p>
            </div>
          </div>
        </div>

        {/* Upcoming Events Section */}
        <div className="pt-2 space-y-3" data-testid="upcoming_events_section">
          <h2 className="text-base font-bold text-slate-100" data-testid="upcoming_events_title">
            الفعاليات القادمة
          </h2>
          <div
            data-testid="upcoming_event_card"
            className="rounded-2xl p-4.5 bg-gradient-to-r from-[#752B28] to-[#8B3632] border border-rose-400/35 space-y-3 shadow-lg"
          >
            <div className="flex items-center justify-between">
              <span
                data-testid="event_badge"
                className="px-2.5 py-1 rounded-md bg-rose-400/25 border border-rose-400/40 text-rose-200 text-xs font-bold"
              >
                فعالية
              </span>
              <button
                onClick={() => setReminded(!reminded)}
                data-testid="event_action_pill"
                className={`flex items-center gap-1.5 px-3 py-1 rounded-full border text-xs font-bold transition-all ${
                  reminded
                    ? 'bg-rose-500 border-rose-300 text-white'
                    : 'bg-black/25 border-rose-400/40 text-rose-100 hover:bg-black/40'
                }`}
              >
                <Bell className="w-3.5 h-3.5 text-rose-300" />
                <span data-testid="event_action_text">{reminded ? 'تم التذكير' : 'ذكّرني'}</span>
              </button>
            </div>

            <h3 className="text-sm font-bold text-rose-50" data-testid="event_title">
              فعالية شبكة الأصالة
            </h3>

            <div className="flex items-center gap-4 text-xs font-medium text-rose-100">
              <div className="flex items-center gap-1.5" data-testid="event_date_row">
                <Calendar className="w-3.5 h-3.5 text-rose-300" />
                <span data-testid="event_date_text">12 أكتوبر 2026</span>
              </div>
              <div className="flex items-center gap-1" data-testid="event_location_row">
                <MapPin className="w-3.5 h-3.5 text-rose-300" />
                <span data-testid="event_location_text">مأرب</span>
              </div>
            </div>

            <p className="text-xs text-rose-200/90 leading-relaxed" data-testid="event_description">
              تابع آخر الفعاليات والأنشطة المرتبطة بشبكة الأصالة.
            </p>
          </div>
        </div>

        {/* Network Channels Section */}
        <div className="pt-2 space-y-3" data-testid="network_channels_section">
          <div className="flex items-center justify-between">
            <h2 className="text-base font-bold text-slate-100" data-testid="network_channels_title">
              قنوات شبكة الأصالة
            </h2>
            <button
              onClick={onNavigateToChannels}
              className="text-xs font-semibold text-sky-400 hover:text-sky-300 transition-colors"
            >
              عرض الكل
            </button>
          </div>

          {/* Filter Pills */}
          <div className="flex gap-2 overflow-x-auto pb-1" data-testid="channels_filters_row">
            {filterLabels.map((filter) => {
              const isSelected = selectedChannelFilter === filter;
              return (
                <button
                  key={filter}
                  onClick={() => setSelectedChannelFilter(filter)}
                  data-testid={`channel_filter_${filter}`}
                  className={`px-3.5 py-1.5 rounded-full text-xs font-semibold whitespace-nowrap transition-all ${
                    isSelected
                      ? 'bg-sky-600 border border-sky-400 text-white shadow-sm'
                      : 'bg-white/15 border border-white/20 text-slate-300 hover:bg-white/20'
                  }`}
                >
                  {filter}
                </button>
              );
            })}
          </div>

          {/* Channels horizontal scroll list */}
          <div className="flex gap-3 overflow-x-auto pb-2" data-testid="channels_horizontal_scroll_row">
            {homeMockChannels.map((item) => (
              <div
                key={item.name}
                onClick={onNavigateToChannels}
                data-testid={`channel_card_${item.name}`}
                className="w-28 shrink-0 rounded-2xl p-3 bg-white/10 hover:bg-white/15 border border-white/15 cursor-pointer flex flex-col items-center gap-2 text-center transition-all"
              >
                <div
                  className="w-12 h-12 rounded-xl flex items-center justify-center border"
                  style={{
                    backgroundColor: item.iconBgColor,
                    borderColor: `${item.accentColor}55`,
                  }}
                  data-testid={`channel_logo_placeholder_${item.name}`}
                >
                  <Tv className="w-6 h-6" style={{ color: item.accentColor }} />
                </div>
                <div className="space-y-0.5">
                  <h4 className="text-xs font-bold text-white break-words leading-tight" data-testid={`channel_name_${item.name}`}>
                    {item.name}
                  </h4>
                  <span
                    className="text-[10px] font-medium block"
                    style={{ color: item.accentColor }}
                    data-testid={`channel_type_${item.name}`}
                  >
                    {item.type}
                  </span>
                </div>
              </div>
            ))}
          </div>
        </div>

        {/* Reception Guide Section */}
        <div className="pt-2 space-y-3" data-testid="reception_guide_section">
          <h2 className="text-base font-bold text-slate-100" data-testid="reception_guide_section_title">
            دليل استقبال البث
          </h2>
          <div
            onClick={onNavigateToReceptionGuide}
            data-testid="reception_guide_card"
            className="rounded-2xl p-5 bg-gradient-to-r from-[#134E4A] to-[#0F766E] border border-emerald-400/35 space-y-3 cursor-pointer shadow-lg hover:border-emerald-400/60 transition-all"
          >
            <div className="flex items-center justify-between">
              <div className="w-11 h-11 rounded-xl bg-emerald-400/20 border border-emerald-400/30 flex items-center justify-center">
                <Radio className="w-6 h-6 text-emerald-400" />
              </div>
              <span
                data-testid="reception_guide_badge"
                className="px-2.5 py-1 rounded-md bg-emerald-400/20 border border-emerald-400/30 text-emerald-300 text-xs font-bold"
              >
                دليل شامل
              </span>
            </div>

            <div>
              <h3 className="text-base font-bold text-emerald-50" data-testid="reception_guide_title">
                دليل استقبال البث
              </h3>
              <p className="text-xs text-emerald-200/90 mt-1 leading-relaxed" data-testid="reception_guide_description">
                المرجع الشامل لاستقبال شبكة الأصالة: إعداد الهوائي، متطلبات الرسيفر والبطاقة، وطريقة البحث وضبط القنوات.
              </p>
            </div>

            <div className="flex items-center gap-1 text-xs font-bold text-emerald-300" data-testid="reception_guide_action_row">
              <span data-testid="reception_guide_action_text">عرض الدليل بالكامل</span>
              <ChevronLeft className="w-4 h-4" />
            </div>

            <div className="border-t border-white/20 pt-3">
              <div className="grid grid-cols-3 gap-2" data-testid="reception_guide_shortcuts_row">
                <button
                  onClick={(e) => {
                    e.stopPropagation();
                    onNavigateToFrequencies();
                  }}
                  data-testid="reception_shortcut_frequencies"
                  className="p-2 rounded-xl bg-black/25 border border-yellow-400/30 hover:bg-black/40 flex items-center justify-center gap-1.5 text-[11px] font-semibold text-slate-100 transition-all"
                >
                  <Radio className="w-3.5 h-3.5 text-yellow-300" />
                  <span>الترددات</span>
                </button>

                <button
                  onClick={(e) => {
                    e.stopPropagation();
                    onNavigateToCoverage();
                  }}
                  data-testid="reception_shortcut_coverage"
                  className="p-2 rounded-xl bg-black/25 border border-rose-400/30 hover:bg-black/40 flex items-center justify-center gap-1.5 text-[11px] font-semibold text-slate-100 transition-all"
                >
                  <Globe className="w-3.5 h-3.5 text-rose-300" />
                  <span>التغطية</span>
                </button>

                <button
                  onClick={(e) => {
                    e.stopPropagation();
                    onNavigateToReceiverCardInfo();
                  }}
                  data-testid="reception_shortcut_receiver_info"
                  className="p-2 rounded-xl bg-black/25 border border-sky-400/30 hover:bg-black/40 flex items-center justify-center gap-1.5 text-[11px] font-semibold text-slate-100 transition-all"
                >
                  <Tv className="w-3.5 h-3.5 text-sky-300" />
                  <span>معلومات الرسيفر</span>
                </button>
              </div>
            </div>
          </div>
        </div>

        {/* Support and Contact Section */}
        <div className="pt-2 space-y-3" data-testid="support_contact_section">
          <h2 className="text-base font-bold text-slate-100" data-testid="support_contact_title">
            الدعم والتواصل
          </h2>

          <div className="space-y-2.5">
            {/* 1. فتح بلاغ */}
            <div
              onClick={onNavigateToSupport}
              data-testid="support_card_ticket"
              className="rounded-2xl p-4 bg-gradient-to-r from-[#162A45] to-[#132238] border border-sky-400/25 flex items-center justify-between cursor-pointer hover:border-sky-400/50 transition-all"
            >
              <div className="flex items-center gap-3.5">
                <div className="w-11 h-11 rounded-xl bg-sky-500/20 border border-sky-400/30 flex items-center justify-center">
                  <Headset className="w-5 h-5 text-sky-400" />
                </div>
                <div>
                  <h4 className="text-sm font-bold text-white">فتح بلاغ</h4>
                  <p className="text-xs text-slate-400">أرسل مشكلة أو طلب دعم</p>
                </div>
              </div>
              <ChevronLeft className="w-4 h-4 text-sky-400" />
            </div>

            {/* 2. الأسئلة الشائعة */}
            <div
              onClick={onNavigateToFaq}
              data-testid="support_card_faq"
              className="rounded-2xl p-4 bg-gradient-to-r from-[#231C42] to-[#1C1635] border border-violet-400/25 flex items-center justify-between cursor-pointer hover:border-violet-400/50 transition-all"
            >
              <div className="flex items-center gap-3.5">
                <div className="w-11 h-11 rounded-xl bg-violet-500/20 border border-violet-400/30 flex items-center justify-center">
                  <HelpCircle className="w-5 h-5 text-violet-400" />
                </div>
                <div>
                  <h4 className="text-sm font-bold text-white">الأسئلة الشائعة</h4>
                  <p className="text-xs text-slate-400">إجابات سريعة للمشكلات المتكررة</p>
                </div>
              </div>
              <ChevronLeft className="w-4 h-4 text-violet-400" />
            </div>

            {/* 3. تواصل معنا */}
            <div
              onClick={onNavigateToSupport}
              data-testid="support_card_contact"
              className="rounded-2xl p-4 bg-gradient-to-r from-[#103634] to-[#0C2B29] border border-teal-400/25 flex items-center justify-between cursor-pointer hover:border-teal-400/50 transition-all"
            >
              <div className="flex items-center gap-3.5">
                <div className="w-11 h-11 rounded-xl bg-teal-500/20 border border-teal-400/30 flex items-center justify-center">
                  <MessageCircle className="w-5 h-5 text-teal-400" />
                </div>
                <div>
                  <h4 className="text-sm font-bold text-white">تواصل معنا</h4>
                  <p className="text-xs text-slate-400">قنوات التواصل الرسمية</p>
                </div>
              </div>
              <ChevronLeft className="w-4 h-4 text-teal-400" />
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};
