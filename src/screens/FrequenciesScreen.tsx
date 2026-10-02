import React from 'react';
import {
  Radio,
  Lock,
  LockOpen,
  MapPin,
  Waves,
} from 'lucide-react';
import { TopBar } from '../components/TopBar';

interface FrequenciesScreenProps {
  onBackClick: () => void;
}

export const FrequenciesScreen: React.FC<FrequenciesScreenProps> = ({ onBackClick }) => {
  const towersList = [
    {
      id: 'samdah',
      towerName: 'برج الصمدة (الرئيسي)',
      location: 'الوادي - حصون آل جلال - منطقة الصمدة',
      packages: [
        {
          packageName: 'الباقة المشفرة',
          isEncrypted: true,
          frequency: '12450',
          polarization: 'أفقي',
          symbolRate: '32000',
        },
        {
          packageName: 'الباقة المفتوحة',
          isEncrypted: false,
          frequency: '12226',
          polarization: 'أفقي',
          symbolRate: '32000',
        },
      ],
    },
    {
      id: 'haiat',
      towerName: 'برج الهيئة',
      location: 'المدينة - غرباً - أمام مستشفى الهيئة الطبي',
      packages: [
        {
          packageName: 'الباقة المشفرة',
          isEncrypted: true,
          frequency: '12500',
          polarization: 'أفقي',
          symbolRate: '32000',
        },
        {
          packageName: 'الباقة المفتوحة',
          isEncrypted: false,
          frequency: '12300',
          polarization: 'أفقي',
          symbolRate: '32000',
        },
      ],
    },
  ];

  return (
    <div
      className="flex flex-col min-h-screen bg-transparent text-[#E5E7EB] pb-20"
      data-testid="frequencies_screen_scaffold"
    >
      <TopBar
        title="الترددات"
        onBack={onBackClick}
        testTag="frequencies_screen_title"
        backTestTag="frequencies_back_button"
      />

      <div className="px-5 py-4 space-y-4 max-w-2xl mx-auto w-full">
        {/* Intro Card */}
        <div
          className="rounded-2xl bg-[#0B1739]/85 backdrop-blur-md border border-[#27BDE3]/30 p-4 flex items-center gap-3.5 shadow-lg"
          data-testid="frequencies_intro_card"
        >
          <div className="w-11 h-11 rounded-xl bg-[#27BDE3]/15 border border-[#27BDE3]/30 flex items-center justify-center shrink-0">
            <Waves className="w-6 h-6 text-[#27BDE3]" />
          </div>
          <div>
            <h2 className="text-sm font-bold text-white">بيانات الترددات الرسمية</h2>
            <p className="text-xs text-[#9CA3AF] mt-0.5">
              ترددات الاستقبال المعتمدة لأبراج بث شبكة الأصالة
            </p>
          </div>
        </div>

        {/* Towers and Packages */}
        {towersList.map((tower, tIdx) => {
          const isSecondTower = tIdx % 2 === 1;
          const towerAccentColor = isSecondTower ? '#8B5CF6' : '#27BDE3';
          const towerBorderColor = isSecondTower ? 'border-[#8B5CF6]/35' : 'border-[#27BDE3]/35';
          const towerShadow = isSecondTower
            ? 'shadow-[0_8px_30px_rgba(139,92,246,0.12)]'
            : 'shadow-[0_8px_30px_rgba(39,189,227,0.12)]';

          return (
            <div
              key={tower.id}
              className={`rounded-3xl bg-[#0B1739]/85 backdrop-blur-xl border ${towerBorderColor} p-5 space-y-4 ${towerShadow} relative overflow-hidden`}
              data-testid={`tower_card_${tower.id}`}
            >
              {/* Subtle ambient glow */}
              <div
                className="absolute top-0 right-0 w-32 h-32 rounded-full blur-2xl pointer-events-none"
                style={{
                  backgroundColor: isSecondTower ? 'rgba(139, 92, 246, 0.12)' : 'rgba(39, 189, 227, 0.12)',
                }}
              />

              {/* Tower Header */}
              <div className="flex items-center gap-3 relative z-10">
                <div
                  className="w-10 h-10 rounded-2xl flex items-center justify-center shrink-0 border shadow-xs"
                  style={{
                    backgroundColor: isSecondTower ? 'rgba(139, 92, 246, 0.15)' : 'rgba(39, 189, 227, 0.15)',
                    borderColor: isSecondTower ? 'rgba(139, 92, 246, 0.35)' : 'rgba(39, 189, 227, 0.35)',
                    color: towerAccentColor,
                  }}
                >
                  <Radio className="w-5 h-5" />
                </div>
                <div>
                  <h3
                    className="text-base font-bold text-white tracking-tight"
                    data-testid={`tower_name_${tower.id}`}
                  >
                    {tower.towerName}
                  </h3>
                  <div className="flex items-center gap-1.5 mt-0.5 text-[#9CA3AF]">
                    <MapPin className="w-3.5 h-3.5 shrink-0" style={{ color: towerAccentColor }} />
                    <span
                      className="text-xs text-[#9CA3AF]"
                      data-testid={`tower_location_${tower.id}`}
                    >
                      {tower.location}
                    </span>
                  </div>
                </div>
              </div>

              <div
                className="h-px relative z-10"
                style={{
                  backgroundColor: isSecondTower ? 'rgba(139, 92, 246, 0.18)' : 'rgba(39, 189, 227, 0.18)',
                }}
              />

              {/* Packages */}
              <div className="space-y-3 relative z-10">
                {tower.packages.map((pkg) => {
                  const packageTag = `pkg_${tower.id}_${pkg.isEncrypted ? 'encrypted' : 'open'}`;
                  const isEnc = pkg.isEncrypted;

                  return (
                    <div
                      key={pkg.packageName}
                      className={`p-4 rounded-2xl border transition-all ${
                        isEnc
                          ? 'bg-[#121c44]/80 border-[#C8A45D]/30 hover:border-[#C8A45D]/50 shadow-[0_4px_16px_rgba(200,164,93,0.08)]'
                          : 'bg-[#0e224c]/80 border-[#27BDE3]/25 hover:border-[#27BDE3]/50 shadow-[0_4px_16px_rgba(39,189,227,0.08)]'
                      } space-y-3`}
                      data-testid={packageTag}
                    >
                      <div className="flex items-center justify-between">
                        <span className="text-xs font-bold text-white tracking-tight">{pkg.packageName}</span>
                        <span
                          className={`inline-flex items-center gap-1 px-2.5 py-0.5 rounded-full text-[11px] font-bold border shadow-xs ${
                            isEnc
                              ? 'bg-[#C8A45D]/20 text-[#E0B865] border-[#C8A45D]/40'
                              : 'bg-[#27BDE3]/20 text-[#38BDF8] border-[#27BDE3]/40'
                          }`}
                        >
                          {isEnc ? <Lock className="w-3 h-3 text-[#C8A45D]" /> : <LockOpen className="w-3 h-3 text-[#27BDE3]" />}
                          <span>{isEnc ? 'مشفرة' : 'مفتوحة'}</span>
                        </span>
                      </div>

                      <div
                        className="h-px"
                        style={{
                          backgroundColor: isEnc ? 'rgba(200, 164, 93, 0.15)' : 'rgba(39, 189, 227, 0.15)',
                        }}
                      />

                      <div className="grid grid-cols-3 gap-2 text-center">
                        <div className="space-y-0.5 p-2 rounded-xl bg-[#07112B]/70 border border-white/5">
                          <p className="text-[10px] text-[#9CA3AF]">التردد</p>
                          <p
                            className="font-mono text-sm font-bold"
                            style={{ color: isEnc ? '#E0B865' : '#27BDE3' }}
                          >
                            {pkg.frequency}
                          </p>
                        </div>

                        <div className="space-y-0.5 p-2 rounded-xl bg-[#07112B]/70 border border-white/5">
                          <p className="text-[10px] text-[#9CA3AF]">الاستقطاب</p>
                          <p className="text-xs font-bold text-emerald-400">{pkg.polarization}</p>
                        </div>

                        <div className="space-y-0.5 p-2 rounded-xl bg-[#07112B]/70 border border-white/5">
                          <p className="text-[10px] text-[#9CA3AF]">معدل الترميز</p>
                          <p className="font-mono text-xs font-bold text-white">{pkg.symbolRate}</p>
                        </div>
                      </div>
                    </div>
                  );
                })}
              </div>
            </div>
          );
        })}
      </div>
    </div>
  );
};
