import React from 'react';
import {
  Globe,
  CheckCircle2,
  MapPin,
  Info,
} from 'lucide-react';
import { TopBar } from '../components/TopBar';

interface CoverageScreenProps {
  onBackClick: () => void;
}

export const CoverageScreen: React.FC<CoverageScreenProps> = ({ onBackClick }) => {
  const mainCoverage = 'محافظة مأرب';
  const coveredAreas = ['مدينة مأرب', 'مديريات الوادي'];
  const networkNote = 'شبكة الأصالة قابلة للتوسع مستقبلاً.';

  const towers = [
    {
      name: 'برج الصمدة (الرئيسي)',
      location: 'الوادي - حصون آل جلال - منطقة الصمدة',
    },
    {
      name: 'برج الهيئة',
      location: 'المدينة - غرباً - أمام مستشفى الهيئة الطبي',
    },
  ];

  return (
    <div
      className="flex flex-col min-h-screen bg-transparent text-[#E5E7EB] pb-20"
      data-testid="coverage_screen_scaffold"
    >
      <TopBar
        title="التغطية ومناطق البث"
        onBack={onBackClick}
        testTag="coverage_screen_title"
        backTestTag="coverage_back_button"
      />

      <div className="px-5 py-4 space-y-4 max-w-2xl mx-auto w-full">
        {/* Intro / Main Coverage Card */}
        <div
          className="rounded-2xl bg-[#0B1739]/85 backdrop-blur-md border border-[#27BDE3]/30 p-4 flex items-center gap-3.5 shadow-lg"
          data-testid="coverage_intro_card"
        >
          <div className="w-11 h-11 rounded-xl bg-[#27BDE3]/15 border border-[#27BDE3]/30 flex items-center justify-center shrink-0">
            <Globe className="w-6 h-6 text-[#27BDE3]" />
          </div>
          <div>
            <h2 className="text-sm font-bold text-white">نطاق البث والتغطية</h2>
            <p className="text-xs text-[#9CA3AF] mt-0.5">{mainCoverage}</p>
          </div>
        </div>

        {/* Covered Areas Card */}
        <div
          className="rounded-2xl bg-[#0B1739]/85 backdrop-blur-md border border-[#27BDE3]/20 p-5 space-y-3.5 shadow-xl"
          data-testid="covered_areas_card"
        >
          <h2 className="text-sm font-bold text-white">المناطق المغطاة</h2>

          <div className="h-px bg-[#27BDE3]/15" />

          <div className="space-y-2.5">
            {coveredAreas.map((area) => (
              <div key={area} className="flex items-center gap-2.5">
                <CheckCircle2 className="w-4 h-4 text-emerald-400 shrink-0" />
                <span className="text-sm font-medium text-[#E5E7EB]">{area}</span>
              </div>
            ))}
          </div>
        </div>

        {/* Transmission Towers */}
        {towers.map((tower, index) => {
          const isSecondTower = index % 2 === 1;
          const towerColor = isSecondTower ? '#8B5CF6' : '#27BDE3';
          const towerBorder = isSecondTower ? 'border-[#8B5CF6]/30' : 'border-[#27BDE3]/30';

          return (
            <div
              key={index}
              className={`rounded-2xl bg-[#0B1739]/85 backdrop-blur-md border ${towerBorder} p-5 space-y-2.5 shadow-xl transition-all relative overflow-hidden`}
              data-testid={`coverage_tower_card_${index}`}
            >
              <div
                className="absolute top-0 right-0 w-24 h-24 rounded-full blur-2xl pointer-events-none"
                style={{
                  backgroundColor: isSecondTower ? 'rgba(139, 92, 246, 0.1)' : 'rgba(39, 189, 227, 0.1)',
                }}
              />

              <div className="flex items-center gap-2 relative z-10" style={{ color: towerColor }}>
                <MapPin className="w-5 h-5 shrink-0" />
                <h3 className="text-sm font-bold text-white">{tower.name}</h3>
              </div>
              <p className="text-xs text-[#9CA3AF] mr-7 leading-relaxed relative z-10">
                <span className="text-[#9CA3AF]/80">الموقع: </span>
                {tower.location}
              </p>
            </div>
          );
        })}

        {/* Network Note */}
        <div
          className="rounded-2xl bg-[#C8A45D]/10 border border-[#C8A45D]/30 p-4 flex items-center gap-3 shadow-md"
          data-testid="coverage_note_card"
        >
          <Info className="w-5 h-5 text-[#C8A45D] shrink-0" />
          <p className="text-xs text-[#C8A45D] leading-relaxed font-medium">
            {networkNote}
          </p>
        </div>
      </div>
    </div>
  );
};
