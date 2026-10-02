import React, { useState, useEffect } from 'react';
import {
  CreditCard,
  RefreshCw,
  Receipt,
  Radio,
  MapPin,
  Headphones,
  ChevronLeft,
  User,
  Bell,
  Tv,
  BookOpen,
  Calendar,
  Sparkles,
  HelpCircle,
  AlertCircle,
  MessageSquare,
  Phone,
  Layers,
  Signal,
  ShieldCheck,
  Flame,
  Cpu,
} from 'lucide-react';
import { MySubscriptionScreen } from './screens/MySubscriptionScreen';
import { RenewalScreen } from './screens/RenewalScreen';
import { PaymentMethodScreen } from './screens/PaymentMethodScreen';
import { PaymentInstructionsScreen } from './screens/PaymentInstructionsScreen';
import { PaymentProofScreen } from './screens/PaymentProofScreen';
import { RenewalRequestConfirmationScreen } from './screens/RenewalRequestConfirmationScreen';
import { TransactionsScreen } from './screens/TransactionsScreen';
import { TransactionDetailsScreen } from './screens/TransactionDetailsScreen';
import { ChannelsScreen } from './screens/ChannelsScreen';
import { PackageDetailsScreen } from './screens/PackageDetailsScreen';
import { ReceiverCardInfoScreen } from './screens/ReceiverCardInfoScreen';
import { CoverageScreen } from './screens/CoverageScreen';
import { FrequenciesScreen } from './screens/FrequenciesScreen';
import { ReceptionGuideScreen } from './screens/ReceptionGuideScreen';
import { SupportScreen } from './screens/SupportScreen';
import { SupportTicketScreen } from './screens/SupportTicketScreen';
import { FaqScreen } from './screens/FaqScreen';
import { NotificationsScreen } from './screens/NotificationsScreen';
import { AccountScreen } from './screens/AccountScreen';
import { SubscriptionDetailsScreen } from './screens/SubscriptionDetailsScreen';
import { MediaScreen } from './screens/MediaScreen';
import { BottomNav } from './components/BottomNav';
import { TransactionRecord, ChannelPackage, PackageDetailInfo } from './types';
import { PackageDetailRepository } from './data';

type Screen =
  | 'home'
  | 'services'
  | 'media'
  | 'my_subscription'
  | 'subscription_details'
  | 'renewal'
  | 'payment_method'
  | 'payment_instructions'
  | 'payment_proof'
  | 'renewal_confirmation'
  | 'transactions'
  | 'transaction_details'
  | 'channels'
  | 'package_details'
  | 'receiver_card_info'
  | 'coverage'
  | 'frequencies'
  | 'reception_guide'
  | 'support'
  | 'support_ticket'
  | 'faq'
  | 'notifications'
  | 'account';

interface ServicesViewProps {
  onNavigateToSubscription: () => void;
  onNavigateToRenewal: () => void;
  onNavigateToPayment: () => void;
  onNavigateToFrequencies: () => void;
  onNavigateToCoverage: () => void;
  onNavigateToSupport: () => void;
}

function ServicesView({
  onNavigateToSubscription,
  onNavigateToRenewal,
  onNavigateToPayment,
  onNavigateToFrequencies,
  onNavigateToCoverage,
  onNavigateToSupport,
}: ServicesViewProps) {
  const services = [
    {
      title: 'اشتراكي',
      desc: 'بيانات باقة الاشتراك الحالية وتفاصيل البطاقة الذكية',
      category: 'اشتراكات',
      icon: CreditCard,
      accent: 'cyan',
      onClick: onNavigateToSubscription,
    },
    {
      title: 'التجديد',
      desc: 'تجديد فوري للاشتراك واختيار الباقة المناسبة',
      category: 'اشتراكات',
      icon: RefreshCw,
      accent: 'gold',
      onClick: onNavigateToRenewal,
    },
    {
      title: 'الدفع',
      desc: 'سجل العمليات ووسائل الدفع والتحويل المعتمدة',
      category: 'اشتراكات',
      icon: Receipt,
      accent: 'cyan',
      onClick: onNavigateToPayment,
    },
    {
      title: 'الترددات',
      desc: 'بيانات ترددات البث الرقمي وأبراج الإرسال المعتمدة',
      category: 'بث واستقبال',
      icon: Radio,
      accent: 'cyan',
      onClick: onNavigateToFrequencies,
    },
    {
      title: 'التغطية',
      desc: 'مناطق التغطية ونطاق استقبال البث في المحافظة',
      category: 'بث واستقبال',
      icon: MapPin,
      accent: 'cyan',
      onClick: onNavigateToCoverage,
    },
    {
      title: 'الدعم',
      desc: 'قنوات الدعم الفني المباشر وحلول المشكلات والاستفسارات',
      category: 'دعم ومساعدة',
      icon: Headphones,
      accent: 'cyan',
      onClick: onNavigateToSupport,
    },
  ];

  return (
    <div className="w-full max-w-md mx-auto pb-24 px-4 pt-6 text-right" dir="rtl">
      {/* Header */}
      <div className="mb-6 relative">
        <div className="flex items-center gap-2 mb-1.5">
          <span className="w-2 h-2 rounded-full bg-[#27BDE3] animate-pulse" />
          <span className="text-[11px] font-bold text-[#27BDE3] tracking-wider">بوابة المشتركين الرقمية</span>
        </div>
        <h1 className="text-2xl font-bold text-[#FFFFFF] tracking-tight">الخدمات</h1>
        <p className="text-xs text-[#9CA3AF] mt-1 leading-relaxed">خدمات شبكة الأصالة الرقمية للمشتركين</p>
      </div>

      {/* 6 Services List */}
      <div className="grid grid-cols-1 gap-3.5">
        {services.map((service) => {
          const isGold = service.accent === 'gold';
          return (
            <button
              key={service.title}
              onClick={service.onClick}
              className={`w-full flex items-center justify-between p-4 rounded-2xl backdrop-blur-xl border transition-all duration-200 text-right group shadow-lg active:scale-[0.99] ${
                isGold
                  ? 'bg-gradient-to-r from-[#11234c]/90 to-[#0B1739]/95 border-[#C8A45D]/30 hover:border-[#C8A45D]/60 hover:shadow-[0_8px_28px_-6px_rgba(200,164,93,0.22)]'
                  : 'bg-gradient-to-r from-[#0e244b]/90 to-[#0B1739]/95 border-[#27BDE3]/20 hover:border-[#27BDE3]/55 hover:shadow-[0_8px_28px_-6px_rgba(39,189,227,0.22)]'
              }`}
            >
              <div className="flex items-start gap-3.5 flex-1 min-w-0">
                <div
                  className={`w-12 h-12 rounded-xl flex items-center justify-center shrink-0 mt-0.5 transition-transform duration-200 group-hover:scale-105 ${
                    isGold
                      ? 'bg-gradient-to-br from-[#C8A45D]/25 to-[#C8A45D]/10 text-[#C8A45D] border border-[#C8A45D]/40 shadow-[0_0_12px_rgba(200,164,93,0.15)]'
                      : 'bg-gradient-to-br from-[#27BDE3]/20 to-[#27BDE3]/5 text-[#27BDE3] border border-[#27BDE3]/35 shadow-[0_0_12px_rgba(39,189,227,0.15)]'
                  }`}
                >
                  <service.icon className="w-6 h-6" />
                </div>
                <div className="flex-1 min-w-0">
                  <div className="flex items-center gap-2 flex-wrap">
                    <h3 className={`font-bold text-base transition-colors ${
                      isGold ? 'text-[#FFFFFF] group-hover:text-[#C8A45D]' : 'text-[#FFFFFF] group-hover:text-[#27BDE3]'
                    }`}>
                      {service.title}
                    </h3>
                    <span className="text-[10px] px-2 py-0.5 rounded-full bg-[#0B1739] border border-white/10 text-[#9CA3AF]">
                      {service.category}
                    </span>
                  </div>
                  <p className="text-xs text-[#9CA3AF] mt-1 font-normal leading-relaxed break-words">
                    {service.desc}
                  </p>
                </div>
              </div>
              <div className={`w-8 h-8 rounded-full bg-[#0B1739] border flex items-center justify-center shrink-0 mr-2 transition-all ${
                isGold
                  ? 'border-[#C8A45D]/25 text-[#9CA3AF] group-hover:text-[#C8A45D] group-hover:border-[#C8A45D]/60'
                  : 'border-[#27BDE3]/20 text-[#9CA3AF] group-hover:text-[#27BDE3] group-hover:border-[#27BDE3]/55'
              }`}>
                <ChevronLeft className="w-4 h-4 transition-transform group-hover:-translate-x-0.5" />
              </div>
            </button>
          );
        })}
      </div>
    </div>
  );
}

interface OrganizedHomeViewProps {
  onNavigateToSubscription: () => void;
  onNavigateToSubscriptionDetails: () => void;
  onNavigateToRenewal: () => void;
  onNavigateToPayment: () => void;
  onNavigateToTransactions: () => void;
  onNavigateToChannels: () => void;
  onNavigateToMedia: () => void;
  onNavigateToCoverage: () => void;
  onNavigateToFrequencies: () => void;
  onNavigateToReceptionGuide: () => void;
  onNavigateToReceiverCardInfo: () => void;
  onNavigateToNotifications: () => void;
  onNavigateToSupport: () => void;
  onNavigateToAccount: () => void;
  onNavigateToFaq: () => void;
  onNavigateToSupportTicket: () => void;
}

function OrganizedHomeView({
  onNavigateToSubscription,
  onNavigateToSubscriptionDetails,
  onNavigateToRenewal,
  onNavigateToPayment,
  onNavigateToTransactions,
  onNavigateToChannels,
  onNavigateToMedia,
  onNavigateToCoverage,
  onNavigateToFrequencies,
  onNavigateToReceptionGuide,
  onNavigateToReceiverCardInfo,
  onNavigateToNotifications,
  onNavigateToSupport,
  onNavigateToAccount,
  onNavigateToFaq,
  onNavigateToSupportTicket,
}: OrganizedHomeViewProps) {
  return (
    <div className="w-full max-w-md mx-auto pb-24 px-4 pt-4 text-right" dir="rtl">
      {/* 1. Header with Title, Profile Icon & Notification Icon */}
      <header className="flex items-center justify-between py-3 border-b border-[#27BDE3]/15 mb-4" data-testid="home_header">
        <div className="flex items-center gap-3">
          <div className="w-11 h-11 rounded-2xl bg-gradient-to-br from-[#162955] via-[#0B1739] to-[#0A1430] border border-[#C8A45D]/40 flex items-center justify-center shadow-lg shadow-[#C8A45D]/15 text-[#C8A45D] font-black text-xl relative group">
            <span className="absolute -top-1 -right-1 w-3 h-3 rounded-full bg-emerald-400 border-2 border-[#0B1739]" />
            أ
          </div>
          <div>
            <h1 className="text-lg font-bold text-white leading-tight tracking-tight">شبكة الأصالة الرقمية</h1>
            <p className="text-xs text-[#9CA3AF] mt-0.5 font-normal">البوابة الرقمية لخدمات المشتركين</p>
          </div>
        </div>
        <div className="flex items-center gap-2">
          <button
            onClick={onNavigateToNotifications}
            className="w-10 h-10 rounded-xl bg-[#0B1739]/90 border border-[#27BDE3]/25 hover:border-[#27BDE3]/60 hover:bg-[#27BDE3]/10 flex items-center justify-center text-[#E5E7EB] hover:text-white transition-all relative active:scale-95 shadow-sm"
            aria-label="التنبيهات"
            data-testid="home_notifications_btn"
          >
            <Bell className="w-4 h-4" />
            <span className="absolute top-2 right-2 w-2 h-2 rounded-full bg-[#27BDE3] ring-2 ring-[#0B1739]" />
          </button>
          <button
            onClick={onNavigateToAccount}
            className="w-10 h-10 rounded-xl bg-[#0B1739]/90 border border-[#27BDE3]/25 hover:border-[#27BDE3]/60 hover:bg-[#27BDE3]/10 flex items-center justify-center text-[#E5E7EB] hover:text-white transition-all active:scale-95 shadow-sm"
            aria-label="الملف الشخصي"
            data-testid="home_account_btn"
          >
            <User className="w-4 h-4" />
          </button>
        </div>
      </header>

      {/* 2. Main Subscription Summary / Status Card */}
      <section className="mb-6 rounded-3xl bg-gradient-to-br from-[#162D63] via-[#0E1D44] to-[#071129] border border-[#C8A45D]/35 p-5 shadow-[0_14px_45px_-12px_rgba(0,0,0,0.85)] relative overflow-hidden group" data-testid="subscription_summary_card">
        {/* Subtle cyan laser spotlight & gold warmth */}
        <div className="absolute -top-24 -left-24 w-60 h-60 bg-gradient-to-br from-[#27BDE3]/15 via-[#27BDE3]/5 to-transparent rounded-full blur-2xl pointer-events-none" />
        <div className="absolute -bottom-20 -right-20 w-52 h-52 bg-[#C8A45D]/10 rounded-full blur-2xl pointer-events-none" />
        
        {/* Top Smart Card Header Row */}
        <div className="flex items-center justify-between mb-4 relative z-10">
          <div className="flex items-center gap-2">
            <span className="w-2 h-2 rounded-full bg-[#C8A45D]" />
            <span className="text-xs text-[#C8A45D] font-bold tracking-wide">بطاقة المشترك</span>
          </div>

          <div className="flex items-center gap-1.5 px-3 py-1 rounded-full bg-emerald-500/15 border border-emerald-500/30 text-emerald-400 text-xs font-bold">
            <span className="w-2 h-2 rounded-full bg-emerald-400" />
            <span>نشط</span>
          </div>
        </div>

        {/* Package Title and Card Number */}
        <div className="mb-4 relative z-10">
          <h2 className="text-xl font-black text-white tracking-tight mb-1">الباقة الأساسية</h2>
          <div className="flex items-center gap-2 text-xs text-[#9CA3AF] font-mono flex-wrap">
            <span>رقم البطاقة:</span>
            <span className="text-white font-bold tracking-wider">**** **** **** 4587</span>
          </div>
        </div>

        {/* Expiry Row */}
        <div className="flex items-center justify-between text-xs py-2.5 px-3 rounded-xl bg-[#0B1739]/70 border border-white/5 mb-4 relative z-10 flex-wrap gap-2">
          <span className="text-[#9CA3AF]">تاريخ انتهاء الاشتراك:</span>
          <span className="font-bold text-[#FFFFFF] flex items-center gap-1.5">
            <Calendar className="w-3.5 h-3.5 text-[#C8A45D]" />
            15 أكتوبر 2026
          </span>
        </div>

        {/* CTA Buttons */}
        <div className="grid grid-cols-2 gap-2.5 relative z-10">
          <button
            onClick={onNavigateToRenewal}
            className="py-2.5 px-3 rounded-xl bg-gradient-to-r from-[#C8A45D] via-[#E2C37D] to-[#C8A45D] hover:from-[#dfbf79] hover:to-[#dfbf79] text-[#0B1739] font-black text-xs flex items-center justify-center gap-1.5 shadow-lg shadow-[#C8A45D]/25 active:scale-[0.98] transition-all"
          >
            <RefreshCw className="w-3.5 h-3.5 text-[#0B1739]" />
            <span>تجديد الآن</span>
          </button>
          <button
            onClick={onNavigateToSubscriptionDetails}
            className="py-2.5 px-3 rounded-xl bg-[#0B1739]/80 hover:bg-[#132759] border border-[#27BDE3]/35 hover:border-[#27BDE3]/65 text-[#FFFFFF] font-bold text-xs flex items-center justify-center gap-1.5 active:scale-[0.98] transition-all"
          >
            <span>تفاصيل الاشتراك</span>
            <ChevronLeft className="w-3.5 h-3.5 text-[#27BDE3]" />
          </button>
        </div>
      </section>

      {/* Module 1: خدمات المشترك (Refined Gold only for key action) */}
      <section className="mb-6">
        <div className="flex items-center justify-between mb-3">
          <div className="flex items-center gap-2">
            <div className="w-8 h-8 rounded-xl bg-[#27BDE3]/15 border border-[#27BDE3]/30 flex items-center justify-center text-[#27BDE3] shadow-sm">
              <Layers className="w-4 h-4" />
            </div>
            <div>
              <h3 className="text-base font-bold text-[#FFFFFF]">خدمات المشترك</h3>
              <p className="text-[11px] text-[#9CA3AF]">الاشتراكات والتجديد والدفع</p>
            </div>
          </div>
        </div>

        <div className="grid grid-cols-3 gap-2.5">
          {/* اشتراكي - Cyan highlight */}
          <button
            onClick={onNavigateToSubscription}
            className="flex flex-col items-center justify-center p-3 rounded-2xl bg-gradient-to-b from-[#0e2249]/85 to-[#0B1739]/95 backdrop-blur-md border border-[#27BDE3]/20 hover:border-[#27BDE3]/55 hover:shadow-[0_6px_20px_rgba(39,189,227,0.18)] active:scale-[0.97] transition-all text-center group"
          >
            <div className="w-10 h-10 rounded-xl bg-[#27BDE3]/15 text-[#27BDE3] border border-[#27BDE3]/30 flex items-center justify-center mb-1.5 group-hover:scale-105 transition-all">
              <CreditCard className="w-5 h-5" />
            </div>
            <span className="text-xs font-bold text-[#FFFFFF] group-hover:text-[#27BDE3] leading-snug break-words">اشتراكي</span>
            <span className="text-[10px] text-[#9CA3AF] mt-0.5 leading-tight break-words">بيانات الباقة</span>
          </button>

          {/* التجديد - Key Action: Gold Highlight */}
          <button
            onClick={onNavigateToRenewal}
            className="flex flex-col items-center justify-center p-3 rounded-2xl bg-gradient-to-b from-[#13254e]/90 to-[#0B1739]/95 backdrop-blur-md border border-[#C8A45D]/35 hover:border-[#C8A45D]/70 hover:shadow-[0_6px_20px_rgba(200,164,93,0.22)] active:scale-[0.97] transition-all text-center group"
          >
            <div className="w-10 h-10 rounded-xl bg-gradient-to-br from-[#C8A45D]/25 to-[#C8A45D]/10 text-[#C8A45D] border border-[#C8A45D]/40 flex items-center justify-center mb-1.5 group-hover:scale-110 group-hover:shadow-[0_0_12px_rgba(200,164,93,0.35)] transition-all">
              <RefreshCw className="w-5 h-5" />
            </div>
            <span className="text-xs font-bold text-[#FFFFFF] group-hover:text-[#C8A45D] leading-snug break-words">التجديد</span>
            <span className="text-[10px] text-[#C8A45D]/90 mt-0.5 leading-tight break-words">تمديد فوري</span>
          </button>

          {/* الدفع - Cyan highlight */}
          <button
            onClick={onNavigateToPayment}
            className="flex flex-col items-center justify-center p-3 rounded-2xl bg-gradient-to-b from-[#0e2249]/85 to-[#0B1739]/95 backdrop-blur-md border border-[#27BDE3]/20 hover:border-[#27BDE3]/55 hover:shadow-[0_6px_20px_rgba(39,189,227,0.18)] active:scale-[0.97] transition-all text-center group"
          >
            <div className="w-10 h-10 rounded-xl bg-[#27BDE3]/15 text-[#27BDE3] border border-[#27BDE3]/30 flex items-center justify-center mb-1.5 group-hover:scale-105 transition-all">
              <Receipt className="w-5 h-5" />
            </div>
            <span className="text-xs font-bold text-[#FFFFFF] group-hover:text-[#27BDE3] leading-snug break-words">الدفع</span>
            <span className="text-[10px] text-[#9CA3AF] mt-0.5 leading-tight break-words">سجل العمليات</span>
          </button>
        </div>
      </section>

      {/* Module 2: البث والاستقبال (Cyan Accents, No Text Truncation) */}
      <section className="mb-6">
        <div className="flex items-center justify-between mb-3">
          <div className="flex items-center gap-2">
            <div className="w-8 h-8 rounded-xl bg-[#27BDE3]/15 border border-[#27BDE3]/35 flex items-center justify-center text-[#27BDE3] shadow-sm">
              <Radio className="w-4 h-4" />
            </div>
            <div>
              <h3 className="text-base font-bold text-[#FFFFFF]">البث والاستقبال</h3>
              <p className="text-[11px] text-[#9CA3AF]">الترددات ومناطق التغطية والأجهزة</p>
            </div>
          </div>
        </div>

        <div className="grid grid-cols-2 gap-2.5">
          <button
            onClick={onNavigateToReceptionGuide}
            className="flex items-start gap-2.5 p-3 rounded-2xl bg-gradient-to-b from-[#0e2249]/85 to-[#0B1739]/95 backdrop-blur-md border border-[#27BDE3]/20 hover:border-[#27BDE3]/55 hover:shadow-[0_6px_20px_rgba(39,189,227,0.18)] active:scale-[0.98] transition-all text-right group"
          >
            <div className="w-9 h-9 rounded-xl bg-[#27BDE3]/15 text-[#27BDE3] border border-[#27BDE3]/30 flex items-center justify-center shrink-0 group-hover:scale-105 transition-transform mt-0.5">
              <BookOpen className="w-4 h-4" />
            </div>
            <div className="flex-1 min-w-0">
              <span className="text-xs font-bold text-[#FFFFFF] block group-hover:text-[#27BDE3] leading-snug break-words">دليل استقبال البث</span>
              <span className="text-[10px] text-[#9CA3AF] block leading-snug mt-0.5 break-words">إرشادات ضبط الإشارة</span>
            </div>
          </button>

          <button
            onClick={onNavigateToFrequencies}
            className="flex items-start gap-2.5 p-3 rounded-2xl bg-gradient-to-b from-[#0e2249]/85 to-[#0B1739]/95 backdrop-blur-md border border-[#27BDE3]/20 hover:border-[#27BDE3]/55 hover:shadow-[0_6px_20px_rgba(39,189,227,0.18)] active:scale-[0.98] transition-all text-right group"
          >
            <div className="w-9 h-9 rounded-xl bg-[#27BDE3]/15 text-[#27BDE3] border border-[#27BDE3]/30 flex items-center justify-center shrink-0 group-hover:scale-105 transition-transform mt-0.5">
              <Radio className="w-4 h-4" />
            </div>
            <div className="flex-1 min-w-0">
              <span className="text-xs font-bold text-[#FFFFFF] block group-hover:text-[#27BDE3] leading-snug break-words">الترددات</span>
              <span className="text-[10px] text-[#9CA3AF] block leading-snug mt-0.5 break-words">بيانات البث والأبراج</span>
            </div>
          </button>

          <button
            onClick={onNavigateToCoverage}
            className="flex items-start gap-2.5 p-3 rounded-2xl bg-gradient-to-b from-[#0e2249]/85 to-[#0B1739]/95 backdrop-blur-md border border-[#27BDE3]/20 hover:border-[#27BDE3]/55 hover:shadow-[0_6px_20px_rgba(39,189,227,0.18)] active:scale-[0.98] transition-all text-right group"
          >
            <div className="w-9 h-9 rounded-xl bg-[#27BDE3]/15 text-[#27BDE3] border border-[#27BDE3]/30 flex items-center justify-center shrink-0 group-hover:scale-105 transition-transform mt-0.5">
              <MapPin className="w-4 h-4" />
            </div>
            <div className="flex-1 min-w-0">
              <span className="text-xs font-bold text-[#FFFFFF] block group-hover:text-[#27BDE3] leading-snug break-words">التغطية</span>
              <span className="text-[10px] text-[#9CA3AF] block leading-snug mt-0.5 break-words">نطاق وخريطة البث</span>
            </div>
          </button>

          <button
            onClick={onNavigateToChannels}
            className="flex items-start gap-2.5 p-3 rounded-2xl bg-gradient-to-b from-[#0e2249]/85 to-[#0B1739]/95 backdrop-blur-md border border-[#27BDE3]/20 hover:border-[#27BDE3]/55 hover:shadow-[0_6px_20px_rgba(39,189,227,0.18)] active:scale-[0.98] transition-all text-right group"
          >
            <div className="w-9 h-9 rounded-xl bg-[#27BDE3]/15 text-[#27BDE3] border border-[#27BDE3]/30 flex items-center justify-center shrink-0 group-hover:scale-105 transition-transform mt-0.5">
              <Tv className="w-4 h-4" />
            </div>
            <div className="flex-1 min-w-0">
              <span className="text-xs font-bold text-[#FFFFFF] block group-hover:text-[#27BDE3] leading-snug break-words">قنوات شبكة الأصالة</span>
              <span className="text-[10px] text-[#9CA3AF] block leading-snug mt-0.5 break-words">الباقات والقنوات</span>
            </div>
          </button>

          <button
            onClick={onNavigateToReceiverCardInfo}
            className="col-span-2 flex items-start gap-2.5 p-3 rounded-2xl bg-gradient-to-b from-[#0e2249]/85 to-[#0B1739]/95 backdrop-blur-md border border-[#27BDE3]/20 hover:border-[#27BDE3]/55 hover:shadow-[0_6px_20px_rgba(39,189,227,0.18)] active:scale-[0.98] transition-all text-right group"
            data-testid="home_receiver_card_info_btn"
          >
            <div className="w-9 h-9 rounded-xl bg-[#27BDE3]/15 text-[#27BDE3] border border-[#27BDE3]/30 flex items-center justify-center shrink-0 group-hover:scale-105 transition-transform mt-0.5">
              <Cpu className="w-4 h-4" />
            </div>
            <div className="flex-1 min-w-0">
              <span className="text-xs font-bold text-[#FFFFFF] block group-hover:text-[#27BDE3] leading-snug break-words">معلومات الرسيفر</span>
              <span className="text-[10px] text-[#9CA3AF] block leading-snug mt-0.5 break-words">مواصفات الجهاز والبطاقة الذكية</span>
            </div>
          </button>
        </div>
      </section>

      {/* Module 3: الإعلام والمجتمع (With #8B5CF6 supporting media accent) */}
      <section className="mb-6">
        <div className="flex items-center justify-between mb-3">
          <div className="flex items-center gap-2">
            <div className="w-8 h-8 rounded-xl bg-[#8B5CF6]/15 border border-[#8B5CF6]/35 flex items-center justify-center text-[#8B5CF6] shadow-sm">
              <Sparkles className="w-4 h-4" />
            </div>
            <div>
              <h3 className="text-base font-bold text-[#FFFFFF]">الإعلام والمجتمع</h3>
              <p className="text-[11px] text-[#9CA3AF]">مستجدات البث والفعاليات الرياضية</p>
            </div>
          </div>
        </div>

        <div className="space-y-3">
          {/* ما الجديد في شبكة الأصالة */}
          <div className="p-4 rounded-2xl bg-gradient-to-r from-[#112756]/90 via-[#0B1739]/95 to-[#0F224D]/90 backdrop-blur-md border border-[#27BDE3]/25 flex flex-col gap-2 shadow-md relative overflow-hidden">
            <div className="absolute top-0 right-0 w-32 h-32 bg-[#8B5CF6]/10 rounded-full blur-xl pointer-events-none" />
            
            <div className="flex items-center justify-between relative z-10 flex-wrap gap-2">
              <div className="flex items-center gap-2">
                <span className="text-[10px] font-bold px-2 py-0.5 rounded-full bg-[#8B5CF6]/20 border border-[#8B5CF6]/40 text-[#8B5CF6]">
                  تحديث
                </span>
                <h4 className="text-xs font-bold text-[#FFFFFF]">ما الجديد في شبكة الأصالة</h4>
              </div>
              <button
                onClick={onNavigateToMedia}
                className="text-[11px] font-bold text-[#27BDE3] hover:text-[#8B5CF6] flex items-center gap-0.5 transition-colors"
                data-testid="home_media_details_btn"
              >
                <span>عرض التفاصيل</span>
                <ChevronLeft className="w-3 h-3" />
              </button>
            </div>
            <p className="text-xs text-[#E5E7EB] leading-relaxed relative z-10 break-words">
              تحديث منظومة البث الرقمي وتوسيع باقات القنوات لتوفير أفضل تجربة مشاهدة للمشتركين.
            </p>
          </div>
        </div>
      </section>

      {/* Module 4: الدعم والتواصل (Clean hierarchy and full text wrapping) */}
      <section className="mb-4">
        <div className="flex items-center justify-between mb-3">
          <div className="flex items-center gap-2">
            <div className="w-8 h-8 rounded-xl bg-[#27BDE3]/15 border border-[#27BDE3]/35 flex items-center justify-center text-[#27BDE3] shadow-sm">
              <Headphones className="w-4 h-4" />
            </div>
            <div>
              <h3 className="text-base font-bold text-[#FFFFFF]">الدعم والتواصل</h3>
              <p className="text-[11px] text-[#9CA3AF]">فريق خدمة المشتركين جاهز لمساعدتكم 24/7</p>
            </div>
          </div>
        </div>

        <div className="grid grid-cols-2 gap-2.5 mb-2.5">
          <button
            onClick={onNavigateToSupportTicket}
            className="flex items-start gap-2.5 p-3 rounded-2xl bg-gradient-to-b from-[#10224d]/85 to-[#0B1739]/95 backdrop-blur-md border border-[#27BDE3]/20 hover:border-[#27BDE3]/50 hover:shadow-[0_6px_20px_rgba(39,189,227,0.15)] active:scale-[0.98] transition-all text-right group"
          >
            <div className="w-9 h-9 rounded-xl bg-[#27BDE3]/15 text-[#27BDE3] border border-[#27BDE3]/30 flex items-center justify-center shrink-0 mt-0.5">
              <AlertCircle className="w-4 h-4" />
            </div>
            <div className="flex-1 min-w-0">
              <span className="text-xs font-bold text-[#FFFFFF] block group-hover:text-[#27BDE3] leading-snug break-words">فتح بلاغ</span>
              <span className="text-[10px] text-[#9CA3AF] block leading-snug mt-0.5 break-words">إرسال تذكرة دعم فني</span>
            </div>
          </button>

          <button
            onClick={onNavigateToFaq}
            className="flex items-start gap-2.5 p-3 rounded-2xl bg-gradient-to-b from-[#10224d]/85 to-[#0B1739]/95 backdrop-blur-md border border-[#27BDE3]/20 hover:border-[#27BDE3]/50 hover:shadow-[0_6px_20px_rgba(39,189,227,0.15)] active:scale-[0.98] transition-all text-right group"
          >
            <div className="w-9 h-9 rounded-xl bg-[#27BDE3]/15 text-[#27BDE3] border border-[#27BDE3]/30 flex items-center justify-center shrink-0 mt-0.5">
              <HelpCircle className="w-4 h-4" />
            </div>
            <div className="flex-1 min-w-0">
              <span className="text-xs font-bold text-[#FFFFFF] block group-hover:text-[#27BDE3] leading-snug break-words">الأسئلة الشائعة</span>
              <span className="text-[10px] text-[#9CA3AF] block leading-snug mt-0.5 break-words">حلول المشاكل المتكررة</span>
            </div>
          </button>
        </div>

        {/* خدمة العملاء المباشرة Card */}
        <div className="p-4 rounded-2xl bg-[#0B1739]/90 backdrop-blur-md border border-[#27BDE3]/20 flex items-center justify-between gap-3 shadow-md flex-wrap">
          <div className="min-w-0 flex-1">
            <span className="text-xs font-bold text-[#FFFFFF] block leading-snug">خدمة العملاء المباشرة</span>
            <span className="text-[11px] text-[#9CA3AF] block mt-0.5 leading-snug">متواجدون على مدار الساعة لخدمتكم</span>
          </div>
          <div className="flex items-center gap-2 shrink-0">
            <a
              href="https://wa.me/967770775252"
              target="_blank"
              rel="noopener noreferrer"
              className="w-9 h-9 rounded-xl bg-emerald-500/20 hover:bg-emerald-500/30 border border-emerald-500/40 text-emerald-400 flex items-center justify-center transition-all active:scale-95 shadow-sm"
              aria-label="واتساب"
            >
              <MessageSquare className="w-4 h-4" />
            </a>
            <a
              href="tel:770775252"
              className="w-9 h-9 rounded-xl bg-[#27BDE3]/20 hover:bg-[#27BDE3]/30 border border-[#27BDE3]/40 text-[#27BDE3] flex items-center justify-center transition-all active:scale-95 shadow-sm"
              aria-label="اتصال"
            >
              <Phone className="w-4 h-4" />
            </a>
            <button
              onClick={onNavigateToSupport}
              className="px-3 py-2 rounded-xl bg-[#10224d] hover:bg-[#152e68] border border-[#27BDE3]/30 text-[#FFFFFF] text-[11px] font-bold flex items-center gap-1 transition-all active:scale-95 shadow-sm"
            >
              <span>مركز الدعم</span>
              <ChevronLeft className="w-3 h-3 text-[#27BDE3]" />
            </button>
          </div>
        </div>
      </section>
    </div>
  );
}

export function App() {
  const [currentScreen, setCurrentScreen] = useState<Screen>('home');
  const [screenStack, setScreenStack] = useState<Screen[]>(['home']);

  // Flow State
  const [selectedPlanId, setSelectedPlanId] = useState<string>('plan_1_month');
  const [selectedMethodId, setSelectedMethodId] = useState<string>('kuraimi');
  const [latestRequestNumber, setLatestRequestNumber] = useState<string>('ASA-2026-000001');

  // Transactions State with persistence
  const [transactions, setTransactions] = useState<TransactionRecord[]>(() => {
    const saved = localStorage.getItem('asalah_transactions');
    if (saved) {
      try {
        return JSON.parse(saved);
      } catch (e) {
        console.error(e);
      }
    }
    return [
      {
        requestNumber: 'ASA-2026-000001',
        operationType: 'تجديد اشتراك',
        smartCardNumber: '**** 4587',
        planId: 'plan_1_month',
        methodId: 'kuraimi',
        status: 'قيد المراجعة',
        requestDate: '29 سبتمبر 2026',
      },
    ];
  });

  useEffect(() => {
    localStorage.setItem('asalah_transactions', JSON.stringify(transactions));
  }, [transactions]);

  // Selected item states
  const [selectedTransaction, setSelectedTransaction] = useState<TransactionRecord>(
    transactions[0]
  );
  const [selectedPackage, setSelectedPackage] = useState<PackageDetailInfo>(
    PackageDetailRepository.encryptedPackage
  );

  const navigateTo = (screen: Screen) => {
    setScreenStack((prev) => [...prev, screen]);
    setCurrentScreen(screen);
    window.scrollTo({ top: 0, behavior: 'smooth' });
  };

  const switchTab = (screen: Screen) => {
    setCurrentScreen(screen);
    setScreenStack([screen]);
    window.scrollTo({ top: 0, behavior: 'smooth' });
  };

  const handleBack = () => {
    if (screenStack.length > 1) {
      const newStack = [...screenStack];
      newStack.pop();
      const previousScreen = newStack[newStack.length - 1];
      setScreenStack(newStack);
      setCurrentScreen(previousScreen);
    } else {
      setCurrentScreen('home');
      setScreenStack(['home']);
    }
    window.scrollTo({ top: 0, behavior: 'smooth' });
  };

  const handleReturnHome = () => {
    setCurrentScreen('home');
    setScreenStack(['home']);
    window.scrollTo({ top: 0, behavior: 'smooth' });
  };

  const handleRenewalStart = () => {
    navigateTo('renewal');
  };

  const handleRenewalPlanChosen = (planId: string) => {
    setSelectedPlanId(planId);
    navigateTo('payment_method');
  };

  const handlePaymentMethodChosen = (planId: string, methodId: string) => {
    setSelectedPlanId(planId);
    setSelectedMethodId(methodId);
    navigateTo('payment_instructions');
  };

  const handleGoToProof = (planId: string, methodId: string) => {
    setSelectedPlanId(planId);
    setSelectedMethodId(methodId);
    navigateTo('payment_proof');
  };

  const handleSubmitProof = (data: {
    planId: string;
    methodId: string;
    transferReference: string;
    note: string;
    receiptImage: string | null;
  }) => {
    const nextNum = transactions.length + 1;
    const formattedNum = `ASA-2026-${String(nextNum).padStart(6, '0')}`;
    const newTx: TransactionRecord = {
      requestNumber: formattedNum,
      operationType: 'تجديد اشتراك',
      smartCardNumber: '**** 4587',
      planId: data.planId,
      methodId: data.methodId,
      status: 'قيد المراجعة',
      requestDate: new Date().toLocaleDateString('ar-YE', {
        year: 'numeric',
        month: 'long',
        day: 'numeric',
      }),
    };

    setTransactions((prev) => [newTx, ...prev]);
    setLatestRequestNumber(formattedNum);
    setSelectedTransaction(newTx);
    navigateTo('renewal_confirmation');
  };

  const handleOpenTransaction = (tx: TransactionRecord) => {
    setSelectedTransaction(tx);
    navigateTo('transaction_details');
  };

  const handleOpenPackage = (pkg: ChannelPackage | PackageDetailInfo) => {
    const pkgDetail = PackageDetailRepository.getPackageById(pkg.id);
    setSelectedPackage(pkgDetail);
    navigateTo('package_details');
  };

  // Determine current tab
  const getCurrentTab = () => {
    switch (currentScreen) {
      case 'home':
        return 'home';
      case 'services':
        return 'services';
      case 'media':
        return 'media';
      case 'support':
      case 'support_ticket':
      case 'faq':
        return 'support';
      case 'account':
      case 'my_subscription':
      case 'subscription_details':
        return 'account';
      default:
        return 'home';
    }
  };

  const isMainTab = ['home', 'services', 'media', 'support', 'account'].includes(currentScreen);

  return (
    <div className="min-h-screen bg-[#0B1739] font-sans antialiased text-[#E5E7EB] flex flex-col justify-between relative overflow-x-hidden">
      {/* Layered broadcast background atmosphere with increased visibility */}
      <div className="fixed inset-0 pointer-events-none overflow-hidden z-0" aria-hidden="true">
        {/* Deep mesh gradient base */}
        <div className="absolute inset-0 bg-gradient-to-b from-[#0B1739] via-[#0E1F4B] to-[#071026]" />
        
        {/* Luminous cyan light layer from top-right */}
        <div className="absolute -top-32 -right-32 w-[460px] h-[460px] rounded-full bg-[#27BDE3]/20 blur-[90px] pointer-events-none" />
        
        {/* Luminous gold warm highlight from mid-left */}
        <div className="absolute top-1/2 -left-36 w-84 h-84 rounded-full bg-[#C8A45D]/14 blur-[85px] pointer-events-none" />
        
        {/* Subtle supporting media energy glow */}
        <div className="absolute top-1/4 -right-24 w-72 h-72 rounded-full bg-[#8B5CF6]/12 blur-[90px] pointer-events-none" />
        
        {/* Concentric broadcast waves and radar rings (Noticeable & Crisp) */}
        <div className="absolute top-20 left-1/2 -translate-x-1/2 w-[340px] h-[340px] rounded-full border-2 border-[#27BDE3]/28 shadow-[0_0_24px_rgba(39,189,227,0.22)] pointer-events-none animate-broadcast-pulse" />
        <div className="absolute top-10 left-1/2 -translate-x-1/2 w-[520px] h-[520px] rounded-full border border-[#27BDE3]/22 pointer-events-none" />
        <div className="absolute -top-2 left-1/2 -translate-x-1/2 w-[720px] h-[720px] rounded-full border border-[#27BDE3]/18 border-dashed pointer-events-none" />
        <div className="absolute -top-16 left-1/2 -translate-x-1/2 w-[920px] h-[920px] rounded-full border border-[#C8A45D]/18 border-dashed pointer-events-none" />
        <div className="absolute -top-28 left-1/2 -translate-x-1/2 w-[1140px] h-[1140px] rounded-full border border-[#27BDE3]/14 pointer-events-none" />
        
        {/* Media / sports dynamic broadcast energy arc */}
        <div className="absolute top-28 left-1/2 -translate-x-1/2 w-[600px] h-[600px] rounded-full border-t-2 border-r border-[#8B5CF6]/22 border-b-transparent border-l-transparent rotate-45 pointer-events-none" />
        
        {/* Signal & Radar light trails and crosshair beams */}
        <div className="absolute top-36 left-1/2 -translate-x-1/2 w-96 h-[2px] bg-gradient-to-r from-transparent via-[#27BDE3]/35 to-transparent pointer-events-none" />
        <div className="absolute top-16 left-1/2 -translate-x-1/2 w-[2px] h-48 bg-gradient-to-b from-transparent via-[#27BDE3]/30 to-transparent pointer-events-none" />
        <div className="absolute top-28 left-1/2 -translate-x-1/2 w-[460px] h-[1.5px] -rotate-45 bg-gradient-to-r from-transparent via-[#27BDE3]/24 to-transparent pointer-events-none" />
        
        {/* Digital frequency matrix lattice pattern overlay */}
        <div className="absolute inset-0 bg-[linear-gradient(rgba(39,189,227,0.055)_1px,transparent_1px),linear-gradient(90deg,rgba(39,189,227,0.055)_1px,transparent_1px)] bg-[size:40px_40px] [mask-image:radial-gradient(ellipse_80%_60%_at_50%_0%,#000_75%,transparent_100%)] pointer-events-none" />
      </div>

      <main className="flex-1 w-full relative z-10">
        {currentScreen === 'home' && (
          <OrganizedHomeView
            onNavigateToSubscription={() => navigateTo('my_subscription')}
            onNavigateToSubscriptionDetails={() => navigateTo('subscription_details')}
            onNavigateToRenewal={handleRenewalStart}
            onNavigateToPayment={() => navigateTo('payment_method')}
            onNavigateToTransactions={() => navigateTo('transactions')}
            onNavigateToChannels={() => navigateTo('channels')}
            onNavigateToMedia={() => navigateTo('media')}
            onNavigateToCoverage={() => navigateTo('coverage')}
            onNavigateToFrequencies={() => navigateTo('frequencies')}
            onNavigateToReceptionGuide={() => navigateTo('reception_guide')}
            onNavigateToReceiverCardInfo={() => navigateTo('receiver_card_info')}
            onNavigateToNotifications={() => navigateTo('notifications')}
            onNavigateToSupport={() => navigateTo('support')}
            onNavigateToAccount={() => navigateTo('account')}
            onNavigateToFaq={() => navigateTo('faq')}
            onNavigateToSupportTicket={() => navigateTo('support_ticket')}
          />
        )}

        {currentScreen === 'services' && (
          <ServicesView
            onNavigateToSubscription={() => navigateTo('my_subscription')}
            onNavigateToRenewal={handleRenewalStart}
            onNavigateToPayment={() => navigateTo('payment_method')}
            onNavigateToFrequencies={() => navigateTo('frequencies')}
            onNavigateToCoverage={() => navigateTo('coverage')}
            onNavigateToSupport={() => navigateTo('support')}
          />
        )}

        {currentScreen === 'my_subscription' && (
          <MySubscriptionScreen
            onBackClick={handleBack}
            onNavigateToRenewal={handleRenewalStart}
          />
        )}

        {currentScreen === 'subscription_details' && (
          <SubscriptionDetailsScreen
            onBackClick={handleBack}
            onNavigateToRenewal={handleRenewalStart}
          />
        )}

        {currentScreen === 'renewal' && (
          <RenewalScreen
            onBackClick={handleBack}
            onNavigateToPaymentMethod={handleRenewalPlanChosen}
          />
        )}

        {currentScreen === 'payment_method' && (
          <PaymentMethodScreen
            planId={selectedPlanId}
            onBackClick={handleBack}
            onNavigateToInstructions={handlePaymentMethodChosen}
          />
        )}

        {currentScreen === 'payment_instructions' && (
          <PaymentInstructionsScreen
            planId={selectedPlanId}
            methodId={selectedMethodId}
            onBackClick={handleBack}
            onNavigateToProof={handleGoToProof}
          />
        )}

        {currentScreen === 'payment_proof' && (
          <PaymentProofScreen
            planId={selectedPlanId}
            methodId={selectedMethodId}
            onBackClick={handleBack}
            onSubmitProof={handleSubmitProof}
          />
        )}

        {currentScreen === 'renewal_confirmation' && (
          <RenewalRequestConfirmationScreen
            planId={selectedPlanId}
            methodId={selectedMethodId}
            requestNumber={latestRequestNumber}
            onReturnHome={handleReturnHome}
          />
        )}

        {currentScreen === 'transactions' && (
          <TransactionsScreen
            transactions={transactions}
            planId={selectedPlanId}
            methodId={selectedMethodId}
            onBackClick={handleBack}
            onTransactionClick={handleOpenTransaction}
          />
        )}

        {currentScreen === 'transaction_details' && (
          <TransactionDetailsScreen
            transaction={selectedTransaction}
            onBackClick={handleBack}
          />
        )}

        {currentScreen === 'media' && (
          <MediaScreen
            onNavigateToChannels={() => navigateTo('channels')}
            onNavigateToCoverage={() => navigateTo('coverage')}
            onNavigateToFrequencies={() => navigateTo('frequencies')}
          />
        )}

        {currentScreen === 'channels' && (
          <ChannelsScreen
            onBackClick={handleBack}
            onPackageClick={handleOpenPackage}
          />
        )}

        {currentScreen === 'package_details' && (
          <PackageDetailsScreen
            packageInfo={selectedPackage}
            onBackClick={handleBack}
          />
        )}

        {currentScreen === 'receiver_card_info' && (
          <ReceiverCardInfoScreen onBackClick={handleBack} />
        )}

        {currentScreen === 'coverage' && (
          <CoverageScreen onBackClick={handleBack} />
        )}

        {currentScreen === 'frequencies' && (
          <FrequenciesScreen onBackClick={handleBack} />
        )}

        {currentScreen === 'reception_guide' && (
          <ReceptionGuideScreen onBackClick={handleBack} />
        )}

        {currentScreen === 'support' && (
          <SupportScreen
            onOpenTicket={() => navigateTo('support_ticket')}
            onOpenFaq={() => navigateTo('faq')}
            onBack={handleBack}
          />
        )}

        {currentScreen === 'support_ticket' && (
          <SupportTicketScreen
            onSubmitTicket={(card, phone, problem) => {
              console.log('Ticket submitted:', { card, phone, problem });
            }}
            onBack={handleBack}
          />
        )}

        {currentScreen === 'faq' && (
          <FaqScreen onBack={handleBack} />
        )}

        {currentScreen === 'notifications' && (
          <NotificationsScreen onBackClick={handleBack} />
        )}

        {currentScreen === 'account' && (
          <AccountScreen
            onNavigateToSubscription={() => navigateTo('my_subscription')}
            onNavigateToSubscriptionDetails={() => navigateTo('subscription_details')}
            onNavigateToTransactions={() => navigateTo('transactions')}
          />
        )}
      </main>

      {/* Global Bottom Navigation on Main Tabs */}
      {isMainTab && (
        <BottomNav
          currentTab={getCurrentTab()}
          onNavigateHome={() => switchTab('home')}
          onNavigateServices={() => switchTab('services')}
          onNavigateMedia={() => switchTab('media')}
          onNavigateChannels={() => navigateTo('channels')}
          onNavigateSupport={() => switchTab('support')}
          onNavigateAccount={() => switchTab('account')}
        />
      )}
    </div>
  );
}

export default App;
