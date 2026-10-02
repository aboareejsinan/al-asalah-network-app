import React from 'react';
import {
  Tag,
  Lock,
  Radio,
  ShieldCheck,
  Film,
  Trophy,
  Smile,
  Tv,
  Newspaper,
  Globe,
  Sparkles,
  Baby,
} from 'lucide-react';
import { TopBar } from '../components/TopBar';
import { PackageDetailInfo } from '../types';
import { PackageDetailRepository } from '../data';

interface PackageDetailsScreenProps {
  packageInfo?: PackageDetailInfo;
  onBackClick: () => void;
}

export const PackageDetailsScreen: React.FC<PackageDetailsScreenProps> = ({
  packageInfo = PackageDetailRepository.encryptedPackage,
  onBackClick,
}) => {
  const isEncrypted = packageInfo.isEncrypted;

  const getCategoryConfig = (title: string) => {
    switch (title) {
      case 'رياضة':
        return {
          icon: <Trophy className="w-4 h-4 text-[#C8A45D]" />,
          border: 'border-[#C8A45D]/40',
          bg: 'bg-[#C8A45D]/15',
          text: 'text-[#E0B865]',
          badgeBg: 'bg-[#C8A45D]/10',
        };
      case 'أفلام':
        return {
          icon: <Film className="w-4 h-4 text-[#8B5CF6]" />,
          border: 'border-[#8B5CF6]/40',
          bg: 'bg-[#8B5CF6]/15',
          text: 'text-[#A78BFA]',
          badgeBg: 'bg-[#8B5CF6]/10',
        };
      case 'دراما':
        return {
          icon: <Tv className="w-4 h-4 text-[#C8A45D]" />,
          border: 'border-[#C8A45D]/30',
          bg: 'bg-[#C8A45D]/15',
          text: 'text-[#E0B865]',
          badgeBg: 'bg-[#C8A45D]/10',
        };
      case 'ترفيه':
        return {
          icon: <Sparkles className="w-4 h-4 text-[#8B5CF6]" />,
          border: 'border-[#8B5CF6]/35',
          bg: 'bg-[#8B5CF6]/15',
          text: 'text-[#C4B5FD]',
          badgeBg: 'bg-[#8B5CF6]/10',
        };
      case 'أخبار':
        return {
          icon: <Newspaper className="w-4 h-4 text-[#27BDE3]" />,
          border: 'border-[#27BDE3]/40',
          bg: 'bg-[#27BDE3]/15',
          text: 'text-[#38BDF8]',
          badgeBg: 'bg-[#27BDE3]/10',
        };
      case 'عامة':
        return {
          icon: <Globe className="w-4 h-4 text-[#27BDE3]" />,
          border: 'border-[#27BDE3]/30',
          bg: 'bg-[#27BDE3]/15',
          text: 'text-[#7DD3FC]',
          badgeBg: 'bg-[#27BDE3]/10',
        };
      case 'دينية':
        return {
          icon: <Smile className="w-4 h-4 text-[#C8A45D]" />,
          border: 'border-[#C8A45D]/30',
          bg: 'bg-[#C8A45D]/15',
          text: 'text-[#E0B865]',
          badgeBg: 'bg-[#C8A45D]/10',
        };
      case 'أطفال':
        return {
          icon: <Baby className="w-4 h-4 text-[#38BDF8]" />,
          border: 'border-[#38BDF8]/40',
          bg: 'bg-[#38BDF8]/15',
          text: 'text-[#38BDF8]',
          badgeBg: 'bg-[#38BDF8]/10',
        };
      case 'قنوات محلية':
        return {
          icon: <Radio className="w-4 h-4 text-emerald-400" />,
          border: 'border-emerald-500/35',
          bg: 'bg-emerald-500/15',
          text: 'text-emerald-400',
          badgeBg: 'bg-emerald-500/10',
        };
      default:
        return {
          icon: <Tv className="w-4 h-4 text-[#27BDE3]" />,
          border: 'border-[#27BDE3]/30',
          bg: 'bg-[#27BDE3]/15',
          text: 'text-[#27BDE3]',
          badgeBg: 'bg-[#27BDE3]/10',
        };
    }
  };

  return (
    <div
      className="flex flex-col min-h-screen bg-transparent text-[#E5E7EB] pb-20"
      data-testid="package_details_scaffold"
    >
      <TopBar
        title="تفاصيل الباقة"
        onBack={onBackClick}
        testTag="package_details_title"
        backTestTag="package_details_back_button"
      />

      <div className="px-5 py-4 space-y-4 max-w-2xl mx-auto w-full">
        {/* Package Overview Card */}
        <div
          className="rounded-3xl bg-[#0B1739]/85 backdrop-blur-xl border p-5 space-y-4 shadow-2xl relative overflow-hidden"
          style={{
            borderColor: isEncrypted ? 'rgba(200, 164, 93, 0.4)' : 'rgba(39, 189, 227, 0.4)',
          }}
          data-testid="package_details_summary_card"
        >
          <div
            className="absolute top-0 right-0 w-32 h-32 rounded-full blur-2xl pointer-events-none"
            style={{
              backgroundColor: isEncrypted ? 'rgba(200, 164, 93, 0.1)' : 'rgba(39, 189, 227, 0.1)',
            }}
          />

          <div className="flex items-center justify-between relative z-10">
            <div className="flex items-center gap-3">
              <div
                className="w-12 h-12 rounded-2xl flex items-center justify-center shrink-0 border shadow-lg"
                style={{
                  backgroundColor: isEncrypted ? 'rgba(200, 164, 93, 0.15)' : 'rgba(39, 189, 227, 0.15)',
                  borderColor: isEncrypted ? 'rgba(200, 164, 93, 0.35)' : 'rgba(39, 189, 227, 0.35)',
                  color: isEncrypted ? '#C8A45D' : '#27BDE3',
                }}
              >
                {isEncrypted ? <Lock className="w-6 h-6" /> : <Radio className="w-6 h-6" />}
              </div>

              <div>
                <h2
                  className="text-base font-bold text-white tracking-tight"
                  data-testid="package_details_name"
                >
                  {packageInfo.packageName}
                </h2>
                <div className="flex items-center gap-1.5 mt-0.5">
                  <Tag className="w-3.5 h-3.5 text-[#9CA3AF]" />
                  <span
                    className="text-xs font-semibold"
                    style={{ color: isEncrypted ? '#C8A45D' : '#27BDE3' }}
                    data-testid="package_details_channels_count"
                  >
                    {packageInfo.channelCount}
                  </span>
                </div>
              </div>
            </div>

            <span
              className={`inline-flex items-center gap-1 px-3 py-1 rounded-full text-xs font-bold border ${
                isEncrypted
                  ? 'bg-[#C8A45D]/15 text-[#C8A45D] border-[#C8A45D]/30'
                  : 'bg-[#27BDE3]/15 text-[#27BDE3] border-[#27BDE3]/30'
              }`}
              data-testid="package_details_access"
            >
              {isEncrypted ? <Lock className="w-3 h-3" /> : <ShieldCheck className="w-3 h-3" />}
              <span>{packageInfo.accessType}</span>
            </span>
          </div>

          <div className="h-px bg-[#27BDE3]/15" />

          <p
            className="text-xs text-[#E5E7EB] leading-relaxed relative z-10"
            data-testid="package_details_description"
          >
            {packageInfo.description}
          </p>
        </div>

        {/* Content Categories Card */}
        <div
          className="rounded-3xl bg-[#0B1739]/80 backdrop-blur-xl border border-[#27BDE3]/20 p-5 space-y-4 shadow-xl"
          data-testid="package_details_categories_card"
        >
          <h3
            className="text-sm font-bold text-white flex items-center gap-2"
            data-testid="package_details_categories_title"
          >
            <span className="w-1.5 h-4 rounded-full bg-[#27BDE3]" />
            <span>تصنيفات المحتوى</span>
          </h3>

          <div className="flex flex-wrap gap-2.5">
            {packageInfo.categories.map((category) => {
              const config = getCategoryConfig(category.title);
              return (
                <div
                  key={category.title}
                  className={`flex items-center gap-2.5 px-3.5 py-2.5 rounded-2xl bg-[#07112B]/90 border ${config.border} hover:scale-[1.02] transition-all shadow-md`}
                  data-testid={`category_card_${category.title}`}
                >
                  <div
                    className={`w-8 h-8 rounded-xl ${config.bg} border ${config.border} flex items-center justify-center shrink-0 shadow-xs`}
                  >
                    {config.icon}
                  </div>
                  <span
                    className={`text-xs font-bold ${config.text}`}
                    data-testid={`category_item_${category.title}`}
                  >
                    {category.title}
                  </span>
                </div>
              );
            })}
          </div>
        </div>
      </div>
    </div>
  );
};
