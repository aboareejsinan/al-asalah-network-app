import React from 'react';
import {
  Headset,
  Ticket,
  HelpCircle,
  Phone,
  MessageCircle,
  MapPin,
  Clock,
  ExternalLink,
} from 'lucide-react';
import { TopBar } from '../components/TopBar';

interface SupportScreenProps {
  onOpenTicket: () => void;
  onOpenFaq: () => void;
  onBack: () => void;
}

export const SupportScreen: React.FC<SupportScreenProps> = ({
  onOpenTicket,
  onOpenFaq,
  onBack,
}) => {
  return (
    <div
      className="flex flex-col min-h-screen bg-transparent text-[#E5E7EB] pb-20"
      data-testid="support_screen_scaffold"
    >
      <TopBar
        title="الدعم"
        onBack={onBack}
        testTag="support_screen_title"
        backTestTag="support_back_button"
      />

      <div className="px-5 py-4 space-y-4 max-w-2xl mx-auto w-full">
        {/* SECTION 1: خدمات الدعم */}
        <div
          className="rounded-2xl bg-[#0B1739]/85 backdrop-blur-xl border border-[#27BDE3]/25 p-5 space-y-3.5 shadow-xl"
          data-testid="support_services_section"
        >
          <div className="flex items-center gap-3">
            <div className="w-10 h-10 rounded-xl bg-[#27BDE3]/15 border border-[#27BDE3]/30 flex items-center justify-center shrink-0">
              <Headset className="w-5 h-5 text-[#27BDE3]" />
            </div>
            <div>
              <h2 className="text-base font-bold text-white">خدمات الدعم</h2>
              <p className="text-xs text-[#9CA3AF]">المساعدة المباشرة والحلول السريعة</p>
            </div>
          </div>

          <div className="h-px bg-white/10" />

          {/* Action Card 1: فتح بلاغ */}
          <div
            onClick={onOpenTicket}
            className="p-3.5 rounded-2xl bg-gradient-to-r from-[#14123b]/85 to-[#0e214d]/90 border border-[#8B5CF6]/30 hover:border-[#8B5CF6]/60 hover:bg-[#181545] transition-all cursor-pointer flex items-center justify-between gap-3 active:scale-[0.99] shadow-sm"
            data-testid="action_open_ticket"
          >
            <div className="flex items-center gap-3">
              <div className="w-10 h-10 rounded-xl bg-[#8B5CF6]/15 border border-[#8B5CF6]/35 flex items-center justify-center shrink-0 text-[#A78BFA] shadow-xs">
                <Ticket className="w-5 h-5" />
              </div>
              <div>
                <h3 className="text-sm font-bold text-white">فتح بلاغ</h3>
                <p className="text-xs text-[#9CA3AF] mt-0.5">إرسال مشكلة أو طلب دعم فني مباشر</p>
              </div>
            </div>
          </div>

          {/* Action Card 2: الأسئلة الشائعة */}
          <div
            onClick={onOpenFaq}
            className="p-3.5 rounded-xl bg-[#10224d]/80 border border-[#27BDE3]/20 hover:border-[#27BDE3]/50 hover:bg-[#142a5c] transition-all cursor-pointer flex items-center justify-between gap-3 active:scale-[0.99]"
            data-testid="action_open_faq"
          >
            <div className="flex items-center gap-3">
              <div className="w-10 h-10 rounded-xl bg-[#27BDE3]/15 border border-[#27BDE3]/30 flex items-center justify-center shrink-0 text-[#27BDE3]">
                <HelpCircle className="w-5 h-5" />
              </div>
              <div>
                <h3 className="text-sm font-bold text-white">الأسئلة الشائعة</h3>
                <p className="text-xs text-[#9CA3AF] mt-0.5">إجابات عن أكثر الاستفسارات وضبط الجهاز</p>
              </div>
            </div>
          </div>
        </div>

        {/* SECTION 2: تواصل معنا */}
        <div
          className="rounded-2xl bg-[#0B1739]/85 backdrop-blur-xl border border-[#C8A45D]/30 p-5 space-y-3.5 shadow-xl"
          data-testid="contact_info_section"
        >
          <div className="flex items-center gap-3">
            <div className="w-10 h-10 rounded-xl bg-[#C8A45D]/15 border border-[#C8A45D]/30 flex items-center justify-center shrink-0">
              <Phone className="w-5 h-5 text-[#C8A45D]" />
            </div>
            <div>
              <h2 className="text-base font-bold text-white">تواصل معنا</h2>
              <p className="text-xs text-[#9CA3AF]">خدمة المشتركين المباشرة</p>
            </div>
          </div>

          <div className="h-px bg-white/10" />

          {/* Address */}
          <div className="flex items-center gap-2.5">
            <MapPin className="w-5 h-5 text-[#27BDE3] shrink-0" />
            <div>
              <p className="text-[11px] text-[#9CA3AF]">العنوان</p>
              <p className="text-sm font-medium text-white">مأرب - الوادي الصمدة</p>
            </div>
          </div>

          <div className="h-px bg-white/5" />

          {/* Phone 1: WhatsApp */}
          <div className="flex items-center justify-between">
            <a
              href="https://wa.me/967770775252"
              target="_blank"
              rel="noopener noreferrer"
              className="flex items-center gap-2.5 hover:opacity-80 transition-opacity"
            >
              <MessageCircle className="w-5 h-5 text-emerald-400 shrink-0" />
              <span className="font-mono text-base font-bold text-white dir-ltr">
                770775252
              </span>
            </a>
            <span className="text-xs font-bold px-2.5 py-1 rounded-lg bg-emerald-500/20 text-emerald-400 border border-emerald-500/30">
              واتساب مباشر
            </span>
          </div>

          {/* Phone 2: Call */}
          <div className="flex items-center justify-between">
            <a
              href="tel:711115252"
              className="flex items-center gap-2.5 hover:opacity-80 transition-opacity"
            >
              <Phone className="w-5 h-5 text-[#27BDE3] shrink-0" />
              <span className="font-mono text-base font-bold text-white dir-ltr">
                711115252
              </span>
            </a>
            <span className="text-xs font-bold px-2.5 py-1 rounded-lg bg-[#27BDE3]/20 text-[#27BDE3] border border-[#27BDE3]/30">
              اتصال مباشر
            </span>
          </div>

          <p className="text-center text-xs text-[#9CA3AF] pt-1">
            اضغط على الأرقام للتواصل مباشرة
          </p>
        </div>

        {/* SECTION 3: أوقات العمل */}
        <div
          className="rounded-2xl bg-[#0B1739]/85 backdrop-blur-xl border border-[#27BDE3]/20 p-5 space-y-3.5 shadow-xl"
          data-testid="working_hours_section"
        >
          <div className="flex items-center gap-3">
            <div className="w-10 h-10 rounded-xl bg-[#27BDE3]/15 border border-[#27BDE3]/30 flex items-center justify-center shrink-0">
              <Clock className="w-5 h-5 text-[#27BDE3]" />
            </div>
            <div>
              <h2 className="text-base font-bold text-white">أوقات العمل</h2>
              <p className="text-xs text-[#9CA3AF]">جاهزية البث والمساندة الفنية</p>
            </div>
          </div>

          <div className="h-px bg-white/10" />

          {/* Item 1 */}
          <div className="space-y-1">
            <h3 className="text-xs font-bold text-[#27BDE3]">الدوام الرسمي</h3>
            <p className="text-xs text-white">من الساعة 12 ظهراً إلى الساعة 12 صباحاً</p>
          </div>

          <div className="h-px bg-white/5" />

          {/* Item 2 */}
          <div className="space-y-1">
            <h3 className="text-xs font-bold text-[#C8A45D]">الأحداث الرياضية المهمة</h3>
            <p className="text-xs font-bold text-emerald-400">على مدار 24 ساعة دون انقطاع</p>
          </div>
        </div>
      </div>
    </div>
  );
};
