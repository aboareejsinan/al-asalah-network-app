import React from 'react';
import {
  Tv,
  CheckCircle2,
  Info,
  ShieldCheck,
  CreditCard,
  Cpu,
} from 'lucide-react';
import { TopBar } from '../components/TopBar';

interface ReceiverCardInfoScreenProps {
  onBackClick: () => void;
}

export const ReceiverCardInfoScreen: React.FC<ReceiverCardInfoScreenProps> = ({
  onBackClick,
}) => {
  return (
    <div
      className="flex flex-col min-h-screen bg-transparent text-[#E5E7EB] pb-20"
      data-testid="receiver_card_info_scaffold"
    >
      <TopBar
        title="معلومات الرسيفر والبطاقة"
        onBack={onBackClick}
        testTag="receiver_card_info_title"
        backTestTag="receiver_card_info_back_button"
      />

      <div className="px-5 py-4 space-y-4 max-w-2xl mx-auto w-full">
        {/* SECTION 1: مواصفات جهاز الاستقبال */}
        <div
          className="rounded-3xl bg-[#0B1739]/85 backdrop-blur-xl border border-[#27BDE3]/30 p-5 space-y-3.5 shadow-2xl relative overflow-hidden"
          data-testid="card_receiver_specs"
        >
          <div className="absolute top-0 right-0 w-32 h-32 bg-[#27BDE3]/10 rounded-full blur-2xl pointer-events-none" />

          <div className="flex items-center gap-3 relative z-10">
            <div className="w-11 h-11 rounded-2xl bg-[#27BDE3]/15 border border-[#27BDE3]/30 flex items-center justify-center shrink-0">
              <Tv className="w-5 h-5 text-[#27BDE3]" />
            </div>
            <h2 className="text-base font-bold text-white tracking-tight">
              مواصفات جهاز الاستقبال
            </h2>
          </div>

          <div className="h-px bg-[#27BDE3]/15" />

          <div className="space-y-2.5 relative z-10">
            {[
              'دعم معيار البث الأرضي DVB-T2.',
              'وجود قارئ بطاقة ذكية Smart Card Reader / CA Slot للقنوات المشفرة.',
              'دعم نظام Multi-CAS.',
              'التوافق مع بطاقة DRE-Crypt.',
            ].map((text, idx) => (
              <div key={idx} className="flex items-start gap-2.5">
                <CheckCircle2 className="w-4 h-4 text-emerald-400 shrink-0 mt-0.5" />
                <span className="text-xs text-[#E5E7EB] leading-relaxed">{text}</span>
              </div>
            ))}
          </div>

          <div className="rounded-2xl bg-[#07112B] border border-[#27BDE3]/25 p-3.5 flex items-start gap-2.5 relative z-10">
            <Info className="w-4 h-4 text-[#27BDE3] shrink-0 mt-0.5" />
            <p className="text-xs text-[#9CA3AF] leading-relaxed">
              الجهاز ليس خاصاً بشبكة الأصالة، ويمكن استخدام أي جهاز متوافق مع المتطلبات.
            </p>
          </div>
        </div>

        {/* SECTION 2: أجهزة متوافقة */}
        <div
          className="rounded-3xl bg-[#0B1739]/85 backdrop-blur-xl border border-[#C8A45D]/30 p-5 space-y-3.5 shadow-2xl relative overflow-hidden"
          data-testid="card_compatible_devices"
        >
          <div className="absolute top-0 right-0 w-32 h-32 bg-[#C8A45D]/10 rounded-full blur-2xl pointer-events-none" />

          <div className="flex items-center gap-3 relative z-10">
            <div className="w-11 h-11 rounded-2xl bg-[#C8A45D]/15 border border-[#C8A45D]/30 flex items-center justify-center shrink-0">
              <ShieldCheck className="w-5 h-5 text-[#C8A45D]" />
            </div>
            <h2 className="text-base font-bold text-white tracking-tight">أجهزة متوافقة</h2>
          </div>

          <div className="h-px bg-[#C8A45D]/15" />

          <div className="space-y-3 relative z-10">
            {/* Ali Processors */}
            <div className="space-y-1">
              <div className="flex items-center gap-2">
                <Cpu className="w-4 h-4 text-[#C8A45D]" />
                <h3 className="text-xs font-bold text-[#C8A45D]">معالجات Ali</h3>
              </div>
              <p className="text-xs text-[#E5E7EB] mr-6">Ali 3510 / Ali 3821</p>
            </div>

            {/* GX Processors */}
            <div className="space-y-1">
              <div className="flex items-center gap-2">
                <Cpu className="w-4 h-4 text-[#C8A45D]" />
                <h3 className="text-xs font-bold text-[#C8A45D]">معالجات GX</h3>
              </div>
              <p className="text-xs text-[#E5E7EB] mr-6">GX6605S</p>
              <p className="text-[11px] text-[#9CA3AF] mr-6">
                يعمل عند توفر دعم Multi-CAS المناسب.
              </p>
            </div>

            {/* Other brands */}
            <div className="space-y-1.5 pt-1">
              <h3 className="text-xs font-bold text-[#C8A45D]">
                علامات تجارية متوافقة أخرى:
              </h3>
              <div className="flex flex-wrap gap-2">
                {['Tiger', 'Starsat', 'Geant'].map((brand) => (
                  <span
                    key={brand}
                    className="px-3.5 py-1.5 rounded-xl bg-[#07112B] border border-[#C8A45D]/30 text-xs font-bold text-white"
                  >
                    {brand}
                  </span>
                ))}
              </div>
            </div>
          </div>

          <div className="rounded-2xl bg-[#07112B] border border-[#C8A45D]/25 p-3.5 flex items-start gap-2.5 relative z-10">
            <Info className="w-4 h-4 text-[#C8A45D] shrink-0 mt-0.5" />
            <p className="text-xs text-[#9CA3AF] leading-relaxed">
              يشترط دعم DVB-T2 وفتحة البطاقة والتوافق مع نظام البطاقة.
            </p>
          </div>
        </div>

        {/* SECTION 3: البطاقة الذكية والتفعيل */}
        <div
          className="rounded-3xl bg-gradient-to-br from-[#121c47]/90 via-[#10193d]/95 to-[#0B1739]/95 backdrop-blur-xl border border-[#8B5CF6]/35 p-5 space-y-3.5 shadow-2xl relative overflow-hidden"
          data-testid="card_smart_card_info"
        >
          <div className="absolute top-0 right-0 w-32 h-32 bg-[#8B5CF6]/15 rounded-full blur-2xl pointer-events-none" />

          <div className="flex items-center gap-3 relative z-10">
            <div className="w-11 h-11 rounded-2xl bg-[#8B5CF6]/15 border border-[#8B5CF6]/35 flex items-center justify-center shrink-0">
              <CreditCard className="w-5 h-5 text-[#A78BFA]" />
            </div>
            <h2 className="text-base font-bold text-white tracking-tight">
              البطاقة الذكية والتفعيل
            </h2>
          </div>

          <div className="h-px bg-[#8B5CF6]/20" />

          <div className="space-y-2.5 relative z-10">
            {[
              'بعد شراء البطاقة وتفعيلها يتم التفعيل تلقائياً.',
              'يتم إدخال البطاقة في جهاز استقبال متوافق.',
              'في حال عدم عمل الخدمة يتم التواصل مع الدعم الفني.',
            ].map((text, idx) => (
              <div key={idx} className="flex items-start gap-2.5">
                <CheckCircle2 className="w-4 h-4 text-emerald-400 shrink-0 mt-0.5" />
                <span className="text-xs text-[#E5E7EB] leading-relaxed">{text}</span>
              </div>
            ))}
          </div>
        </div>
      </div>
    </div>
  );
};
