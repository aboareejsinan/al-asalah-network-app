import React from 'react';
import {
  Sparkles,
  Newspaper,
  Calendar,
  Tv,
  MapPin,
  Radio,
  ChevronLeft,
} from 'lucide-react';
import { TopBar } from '../components/TopBar';

interface MediaScreenProps {
  onBackClick?: () => void;
  onNavigateToChannels: () => void;
  onNavigateToCoverage: () => void;
  onNavigateToFrequencies: () => void;
}

export const MediaScreen: React.FC<MediaScreenProps> = ({
  onBackClick,
  onNavigateToChannels,
  onNavigateToCoverage,
  onNavigateToFrequencies,
}) => {
  return (
    <div
      className="flex flex-col min-h-screen bg-transparent text-[#E5E7EB] pb-24"
      data-testid="media_screen_scaffold"
    >
      <TopBar
        title="الإعلام"
        onBack={onBackClick}
        testTag="media_screen_title"
        backTestTag="media_back_button"
      />

      <div className="px-4 py-5 space-y-4 max-w-2xl mx-auto w-full text-right" dir="rtl">
        {/* Intro Banner */}
        <div className="p-4 rounded-3xl bg-gradient-to-r from-[#121b44]/90 via-[#0B1739]/95 to-[#16123b]/90 backdrop-blur-xl border border-[#8B5CF6]/30 shadow-xl relative overflow-hidden">
          <div className="absolute top-0 right-0 w-36 h-36 bg-[#8B5CF6]/15 rounded-full blur-2xl pointer-events-none" />
          <div className="relative z-10 flex items-center gap-3">
            <div className="w-11 h-11 rounded-2xl bg-[#8B5CF6]/20 border border-[#8B5CF6]/40 flex items-center justify-center shrink-0 shadow-md">
              <Sparkles className="w-5 h-5 text-[#8B5CF6]" />
            </div>
            <div>
              <h2 className="text-base font-bold text-white tracking-tight">مركز الإعلام والمستجدات</h2>
              <p className="text-xs text-[#9CA3AF] mt-0.5">متابعة أخبار البث الرقمي والفعاليات وقنوات شبكة الأصالة</p>
            </div>
          </div>
        </div>

        {/* 1. ما الجديد في شبكة الأصالة */}
        <div
          className="rounded-3xl bg-gradient-to-br from-[#0F224D]/90 via-[#0B1739]/95 to-[#12163b]/90 backdrop-blur-xl border border-[#27BDE3]/35 p-5 space-y-3.5 shadow-xl relative overflow-hidden group hover:border-[#27BDE3]/60 transition-all"
          data-testid="media_item_new_update"
        >
          <div className="absolute top-0 right-0 w-32 h-32 bg-[#27BDE3]/10 rounded-full blur-2xl pointer-events-none" />
          
          <div className="flex items-center justify-between relative z-10 flex-wrap gap-2">
            <div className="flex items-center gap-2.5">
              <div className="w-9 h-9 rounded-xl bg-[#27BDE3]/15 text-[#27BDE3] border border-[#27BDE3]/35 flex items-center justify-center shrink-0">
                <Sparkles className="w-4 h-4" />
              </div>
              <div>
                <span className="text-[10px] font-bold px-2 py-0.5 rounded-full bg-[#27BDE3]/20 border border-[#27BDE3]/40 text-[#27BDE3] inline-block mb-1">
                  تحديث
                </span>
                <h3 className="text-sm font-bold text-white">ما الجديد في شبكة الأصالة</h3>
              </div>
            </div>
          </div>

          <p className="text-xs text-[#E5E7EB] leading-relaxed relative z-10 break-words">
            تحديث منظومة البث الرقمي وتوسيع باقات القنوات لتوفير أفضل تجربة مشاهدة للمشتركين.
          </p>

          <div className="pt-2 border-t border-white/10 flex items-center justify-end relative z-10">
            <button
              onClick={onNavigateToChannels}
              className="text-xs font-bold text-[#27BDE3] hover:text-white px-3 py-1.5 rounded-xl bg-[#27BDE3]/15 hover:bg-[#27BDE3]/25 border border-[#27BDE3]/35 flex items-center gap-1.5 transition-all active:scale-95"
              data-testid="media_btn_view_channels"
            >
              <Tv className="w-3.5 h-3.5" />
              <span>عرض باقات القنوات</span>
              <ChevronLeft className="w-3.5 h-3.5" />
            </button>
          </div>
        </div>

        {/* 2. آخر الأخبار */}
        <div
          className="rounded-3xl bg-[#0B1739]/85 backdrop-blur-xl border border-[#8B5CF6]/30 hover:border-[#8B5CF6]/60 p-5 space-y-3.5 shadow-xl relative overflow-hidden transition-all"
          data-testid="media_item_latest_news"
        >
          <div className="absolute top-0 right-0 w-32 h-32 bg-[#8B5CF6]/10 rounded-full blur-2xl pointer-events-none" />

          <div className="flex items-center gap-2.5 relative z-10">
            <div className="w-9 h-9 rounded-xl bg-[#8B5CF6]/15 text-[#8B5CF6] border border-[#8B5CF6]/35 flex items-center justify-center shrink-0">
              <Newspaper className="w-4 h-4" />
            </div>
            <div>
              <span className="text-[10px] font-bold px-2 py-0.5 rounded-full bg-[#8B5CF6]/20 border border-[#8B5CF6]/40 text-[#8B5CF6] inline-block mb-1">
                آخر الأخبار
              </span>
              <h3 className="text-sm font-bold text-white">مستجدات البث والتغطية</h3>
            </div>
          </div>

          <p className="text-xs text-[#E5E7EB] leading-relaxed relative z-10 break-words">
            متابعة أحدث بيانات البث الرقمي، تحديثات الترددات الدورية، ومناطق تغطية أبراج الإرسال في المحافظة.
          </p>

          <div className="pt-2 border-t border-white/10 flex items-center justify-between gap-2 relative z-10 flex-wrap">
            <button
              onClick={onNavigateToCoverage}
              className="text-xs font-bold text-[#8B5CF6] hover:text-white px-3 py-1.5 rounded-xl bg-[#8B5CF6]/15 hover:bg-[#8B5CF6]/25 border border-[#8B5CF6]/35 flex items-center gap-1.5 transition-all active:scale-95"
              data-testid="media_btn_coverage"
            >
              <MapPin className="w-3.5 h-3.5" />
              <span>مناطق التغطية</span>
              <ChevronLeft className="w-3.5 h-3.5" />
            </button>

            <button
              onClick={onNavigateToFrequencies}
              className="text-xs font-bold text-[#27BDE3] hover:text-white px-3 py-1.5 rounded-xl bg-[#27BDE3]/15 hover:bg-[#27BDE3]/25 border border-[#27BDE3]/35 flex items-center gap-1.5 transition-all active:scale-95"
              data-testid="media_btn_frequencies"
            >
              <Radio className="w-3.5 h-3.5" />
              <span>الترددات وأبراج البث</span>
              <ChevronLeft className="w-3.5 h-3.5" />
            </button>
          </div>
        </div>

        {/* 3. الفعاليات القادمة */}
        <div
          className="rounded-3xl bg-[#0B1739]/85 backdrop-blur-xl border border-[#C8A45D]/30 hover:border-[#C8A45D]/60 p-5 space-y-3.5 shadow-xl relative overflow-hidden transition-all"
          data-testid="media_item_upcoming_events"
        >
          <div className="absolute top-0 right-0 w-32 h-32 bg-[#C8A45D]/10 rounded-full blur-2xl pointer-events-none" />

          <div className="flex items-center gap-2.5 relative z-10">
            <div className="w-9 h-9 rounded-xl bg-[#C8A45D]/15 text-[#C8A45D] border border-[#C8A45D]/35 flex items-center justify-center shrink-0">
              <Calendar className="w-4 h-4" />
            </div>
            <div>
              <span className="text-[10px] font-bold px-2 py-0.5 rounded-full bg-[#C8A45D]/20 border border-[#C8A45D]/40 text-[#C8A45D] inline-block mb-1">
                الفعاليات القادمة
              </span>
              <h3 className="text-sm font-bold text-white">الفعاليات الرياضية والبث المباشر</h3>
            </div>
          </div>

          <p className="text-xs text-[#E5E7EB] leading-relaxed relative z-10 break-words">
            متابعة البث الحي لأهم الفعاليات والأحداث الرياضية المنقولة عبر باقات قنوات شبكة الأصالة المشفرة.
          </p>

          <div className="pt-2 border-t border-white/10 flex items-center justify-end relative z-10">
            <button
              onClick={onNavigateToChannels}
              className="text-xs font-bold text-[#C8A45D] hover:text-white px-3 py-1.5 rounded-xl bg-[#C8A45D]/15 hover:bg-[#C8A45D]/25 border border-[#C8A45D]/35 flex items-center gap-1.5 transition-all active:scale-95"
              data-testid="media_btn_sports_channels"
            >
              <Tv className="w-3.5 h-3.5" />
              <span>استعراض القنوات</span>
              <ChevronLeft className="w-3.5 h-3.5" />
            </button>
          </div>
        </div>
      </div>
    </div>
  );
};
