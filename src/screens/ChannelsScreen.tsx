import React from 'react';
import {
  Tv,
  Lock,
  Radio,
  Tag,
  ShieldCheck,
  ChevronLeft,
} from 'lucide-react';
import { TopBar } from '../components/TopBar';
import { ChannelPackage } from '../types';

interface ChannelsScreenProps {
  onBackClick: () => void;
  onPackageClick: (pkg: ChannelPackage) => void;
}

export const ChannelsScreen: React.FC<ChannelsScreenProps> = ({
  onBackClick,
  onPackageClick,
}) => {
  const packages: ChannelPackage[] = [
    {
      id: 'encrypted_package',
      name: 'الباقة المشفرة',
      channelCount: '20 قناة',
      accessType: 'للمشتركين فقط',
      description: 'تضم قنوات رياضية مشفرة بالإضافة إلى قنوات الأفلام والدراما والترفيه.',
      categories: ['رياضة', 'أفلام', 'دراما', 'ترفيه'],
      accentColor: '#C8A45D',
      isEncrypted: true,
    },
    {
      id: 'open_package',
      name: 'الباقة المفتوحة',
      channelCount: '20 قناة',
      accessType: 'متاحة للجميع',
      description: 'مجموعة قنوات متنوعة متاحة بدون اشتراك.',
      categories: ['أخبار', 'عامة', 'دينية', 'أطفال', 'ترفيه', 'محلية'],
      accentColor: '#27BDE3',
      isEncrypted: false,
    },
  ];

  return (
    <div
      className="flex flex-col min-h-screen bg-transparent text-[#E5E7EB] pb-20"
      data-testid="channels_screen_scaffold"
    >
      <TopBar
        title="القنوات"
        onBack={onBackClick}
        testTag="channels_screen_title"
        backTestTag="channels_back_button"
      />

      <div className="px-5 py-4 space-y-4 max-w-2xl mx-auto w-full">
        {/* Intro TV Network Banner */}
        <div
          className="rounded-2xl bg-[#0B1739]/85 backdrop-blur-md border border-[#27BDE3]/30 p-4 flex items-center gap-3.5 shadow-lg"
          data-testid="channels_intro_card"
        >
          <div className="w-11 h-11 rounded-xl bg-[#27BDE3]/15 border border-[#27BDE3]/30 flex items-center justify-center shrink-0">
            <Tv className="w-6 h-6 text-[#27BDE3]" />
          </div>
          <div>
            <h2 className="text-sm font-bold text-white">بث رقمي DVB-T2</h2>
            <p className="text-xs text-[#9CA3AF] mt-0.5">
              بث أرضي عالي الوضوح لشبكة الأصالة الرقمية
            </p>
          </div>
        </div>

        {/* Packages List */}
        <div className="space-y-4">
          {packages.map((pkg) => {
            const isEncrypted = pkg.isEncrypted;
            return (
              <div
                key={pkg.id}
                className="rounded-3xl p-5 space-y-4 shadow-2xl transition-all border backdrop-blur-xl relative overflow-hidden"
                style={{
                  background: isEncrypted
                    ? 'linear-gradient(135deg, rgba(18, 36, 82, 0.9) 0%, rgba(14, 29, 68, 0.95) 50%, rgba(11, 23, 57, 0.98) 100%)'
                    : 'linear-gradient(135deg, rgba(12, 38, 77, 0.9) 0%, rgba(11, 31, 66, 0.95) 50%, rgba(11, 23, 57, 0.98) 100%)',
                  borderColor: isEncrypted ? 'rgba(200, 164, 93, 0.45)' : 'rgba(39, 189, 227, 0.4)',
                  boxShadow: isEncrypted
                    ? '0 10px 30px -10px rgba(200, 164, 93, 0.2), 0 0 20px -5px rgba(139, 92, 246, 0.15)'
                    : '0 10px 30px -10px rgba(39, 189, 227, 0.2)',
                }}
                data-testid={`package_card_${pkg.id}`}
              >
                {/* Decorative ambient corner glow */}
                <div
                  className="absolute top-0 right-0 w-36 h-36 rounded-full blur-2xl pointer-events-none"
                  style={{
                    backgroundColor: isEncrypted ? 'rgba(200, 164, 93, 0.15)' : 'rgba(39, 189, 227, 0.15)',
                  }}
                />
                {isEncrypted && (
                  <div className="absolute bottom-0 left-0 w-28 h-28 bg-[#8B5CF6]/10 rounded-full blur-2xl pointer-events-none" />
                )}

                {/* Header Row */}
                <div className="flex items-center justify-between relative z-10">
                  <div className="flex items-center gap-3">
                    <div
                      className="w-12 h-12 rounded-2xl flex items-center justify-center shrink-0 border shadow-md"
                      style={{
                        backgroundColor: isEncrypted ? 'rgba(200, 164, 93, 0.18)' : 'rgba(39, 189, 227, 0.18)',
                        borderColor: isEncrypted ? 'rgba(200, 164, 93, 0.45)' : 'rgba(39, 189, 227, 0.45)',
                        color: pkg.accentColor,
                      }}
                    >
                      {isEncrypted ? <Lock className="w-6 h-6 text-[#C8A45D]" /> : <Radio className="w-6 h-6 text-[#27BDE3]" />}
                    </div>

                    <div>
                      <h3
                        className="text-base font-bold text-white tracking-tight"
                        data-testid={`package_title_${pkg.id}`}
                      >
                        {pkg.name}
                      </h3>
                      <div className="flex items-center gap-1.5 mt-0.5">
                        <Tag className="w-3.5 h-3.5 text-[#9CA3AF]" />
                        <span
                          className="text-xs font-semibold"
                          style={{ color: pkg.accentColor }}
                          data-testid={`package_channels_count_${pkg.id}`}
                        >
                          {pkg.channelCount}
                        </span>
                      </div>
                    </div>
                  </div>

                  {/* Access badge */}
                  <span
                    className={`inline-flex items-center gap-1 px-3 py-1 rounded-full text-xs font-bold border shadow-sm ${
                      isEncrypted
                        ? 'bg-[#C8A45D]/15 text-[#C8A45D] border-[#C8A45D]/40'
                        : 'bg-[#27BDE3]/15 text-[#27BDE3] border-[#27BDE3]/40'
                    }`}
                    data-testid={`package_access_${pkg.id}`}
                  >
                    {isEncrypted ? <Lock className="w-3 h-3" /> : <ShieldCheck className="w-3 h-3" />}
                    <span>{pkg.accessType}</span>
                  </span>
                </div>

                {/* Description */}
                <p
                  className="text-xs text-[#E5E7EB] leading-relaxed relative z-10"
                  data-testid={`package_desc_${pkg.id}`}
                >
                  {pkg.description}
                </p>

                <div
                  className="h-px relative z-10"
                  style={{
                    backgroundColor: isEncrypted ? 'rgba(200, 164, 93, 0.2)' : 'rgba(39, 189, 227, 0.2)',
                  }}
                />

                {/* Categories */}
                <div className="space-y-2 relative z-10">
                  <span className="text-xs text-[#9CA3AF] font-medium">التصنيفات:</span>
                  <div className="flex flex-wrap gap-2">
                    {pkg.categories.map((cat) => {
                      const isSports = cat === 'رياضة';
                      const isDramaOrMovies = cat === 'دراما' || cat === 'أفلام';
                      const isEnt = cat === 'ترفيه';
                      return (
                        <span
                          key={cat}
                          className={`px-3 py-1 rounded-xl text-xs font-medium border shadow-xs transition-colors ${
                            isSports
                              ? 'bg-[#C8A45D]/15 border-[#C8A45D]/30 text-[#E0B865]'
                              : isDramaOrMovies
                              ? 'bg-[#8B5CF6]/15 border-[#8B5CF6]/30 text-[#A78BFA]'
                              : isEnt
                              ? 'bg-[#27BDE3]/15 border-[#27BDE3]/30 text-[#38BDF8]'
                              : 'bg-[#0B1739]/80 border-white/10 text-[#E5E7EB]'
                          }`}
                          data-testid={`package_category_${pkg.id}_${cat}`}
                        >
                          {cat}
                        </span>
                      );
                    })}
                  </div>
                </div>

                {/* Action Button */}
                <button
                  onClick={() => onPackageClick(pkg)}
                  className={`w-full py-3 px-4 rounded-xl font-bold text-sm transition-all flex items-center justify-center gap-2 mt-2 shadow-md relative z-10 active:scale-[0.99] ${
                    isEncrypted
                      ? 'bg-gradient-to-r from-[#C8A45D] to-[#dfbc74] hover:from-[#dfbc74] hover:to-[#C8A45D] text-[#0B1739] font-black shadow-[#C8A45D]/20'
                      : 'bg-[#0B1739] hover:bg-[#12224d] text-[#27BDE3] border border-[#27BDE3]/45 shadow-[#27BDE3]/15'
                  }`}
                  data-testid={`package_action_${pkg.id}`}
                >
                  <span>تفاصيل الباقة</span>
                  <ChevronLeft className="w-4 h-4" />
                </button>
              </div>
            );
          })}
        </div>
      </div>
    </div>
  );
};
