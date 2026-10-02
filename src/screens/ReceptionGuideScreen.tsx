import React from 'react';
import {
  Radio,
  Tv,
  Wrench,
  SlidersHorizontal,
  Info,
  Check,
} from 'lucide-react';
import { TopBar } from '../components/TopBar';

interface ReceptionGuideScreenProps {
  onBackClick: () => void;
}

export const ReceptionGuideScreen: React.FC<ReceptionGuideScreenProps> = ({
  onBackClick,
}) => {
  const guideTopics = [
    {
      title: 'كيف تستقبل شبكة الأصالة؟',
      icon: <Radio className="w-5 h-5 text-[#27BDE3]" />,
      accentColor: 'border-[#27BDE3]/35 hover:border-[#27BDE3]/60 shadow-[0_8px_24px_rgba(39,189,227,0.12)]',
      badgeColor: 'bg-[#27BDE3]/20 text-[#27BDE3] border-[#27BDE3]/40',
      iconBg: 'bg-[#27BDE3]/15 border-[#27BDE3]/30',
      glowBg: 'bg-[#27BDE3]/10',
      points: [
        'يعتمد استقبال شبكة الأصالة على موقع المشترك وقوة الإشارة.',
        'يمكن استقبال البث مباشرة باستخدام رأس استقبال إشارة يدعم DVB-T2 / UHF وتوجيهه نحو برج البث المحلي.',
        'يفضل تركيب وسيلة الاستقبال خارج المبنى وعلى السطح للحصول على استقرار أفضل للإشارة.',
        'يمكن استخدام الاستقبال الداخلي عند القرب من برج البث وتوفر إشارة مناسبة.',
        'يمكن أيضاً استخدام طبق استقبال مع رأس استقبال مناسب عند الحاجة لتحسين الإشارة حسب موقع المشترك والعوائق المحيطة.',
      ],
      tag: 'guide_topic_reception',
    },
    {
      title: 'الأجهزة المطلوبة',
      icon: <Tv className="w-5 h-5 text-[#C8A45D]" />,
      accentColor: 'border-[#C8A45D]/35 hover:border-[#C8A45D]/60 shadow-[0_8px_24px_rgba(200,164,93,0.12)]',
      badgeColor: 'bg-[#C8A45D]/20 text-[#C8A45D] border-[#C8A45D]/40',
      iconBg: 'bg-[#C8A45D]/15 border-[#C8A45D]/30',
      glowBg: 'bg-[#C8A45D]/10',
      points: [
        'جهاز تلفزيون أو رسيفر يدعم DVB-T2.',
        'رسيفر خارجي عند الحاجة.',
        'قارئ بطاقة ذكية Smart Card Reader / CA Slot للقنوات المشفرة.',
        'دعم Multi-CAS والتوافق مع بطاقة DRE-Crypt للقنوات المشفرة.',
        'كابل Coaxial RG6 للتوصيل.',
      ],
      tag: 'guide_topic_devices',
    },
    {
      title: 'طريقة التركيب',
      icon: <Wrench className="w-5 h-5 text-[#8B5CF6]" />,
      accentColor: 'border-[#8B5CF6]/35 hover:border-[#8B5CF6]/60 shadow-[0_8px_24px_rgba(139,92,246,0.12)]',
      badgeColor: 'bg-[#8B5CF6]/20 text-[#A78BFA] border-[#8B5CF6]/40',
      iconBg: 'bg-[#8B5CF6]/15 border-[#8B5CF6]/30',
      glowBg: 'bg-[#8B5CF6]/10',
      points: [
        'تثبيت وسيلة الاستقبال في مكان مناسب.',
        'توجيهها نحو برج شبكة الأصالة.',
        'توصيل كابل الاستقبال بالجهاز.',
        'يتم التوصيل عبر ANTENNA / AIR / DTV IN في التلفزيون أو RF IN / ANT IN في الرسيفر الخارجي.',
        'عند استخدام رسيفر خارجي يتم توصيله بالتلفزيون عبر HDMI.',
      ],
      tag: 'guide_topic_installation',
    },
    {
      title: 'طريقة البحث والضبط',
      icon: <SlidersHorizontal className="w-5 h-5 text-[#27BDE3]" />,
      accentColor: 'border-[#27BDE3]/35 hover:border-[#27BDE3]/60 shadow-[0_8px_24px_rgba(39,189,227,0.12)]',
      badgeColor: 'bg-[#27BDE3]/20 text-[#27BDE3] border-[#27BDE3]/40',
      iconBg: 'bg-[#27BDE3]/15 border-[#27BDE3]/30',
      glowBg: 'bg-[#27BDE3]/10',
      points: [
        'فتح إعدادات القنوات أو البث في الجهاز.',
        'اختيار Air / Antenna / DVB-T2.',
        'يمكن استخدام البحث اليدوي وإدخال بيانات التردد المعتمدة للشبكة.',
        'يمكن أيضاً استخدام البحث التلقائي DTV.',
        'مراقبة قوة وجودة الإشارة أثناء الضبط.',
        'حفظ القنوات بعد اكتمال البحث.',
      ],
      tag: 'guide_topic_setup',
    },
    {
      title: 'معلومات عن DVB-T2',
      icon: <Info className="w-5 h-5 text-[#8B5CF6]" />,
      accentColor: 'border-[#8B5CF6]/35 hover:border-[#8B5CF6]/60 shadow-[0_8px_24px_rgba(139,92,246,0.12)]',
      badgeColor: 'bg-[#8B5CF6]/20 text-[#A78BFA] border-[#8B5CF6]/40',
      iconBg: 'bg-[#8B5CF6]/15 border-[#8B5CF6]/30',
      glowBg: 'bg-[#8B5CF6]/10',
      points: [
        'DVB-T2 هو معيار للبث التلفزيوني الرقمي الأرضي عبر أبراج البث.',
        'يوفر جودة أفضل للصورة والصوت.',
        'لا يحتاج إلى اتصال بالإنترنت لاستقبال البث.',
        'جودة الاستقبال تعتمد على قوة الإشارة وموقع المشترك.',
      ],
      tag: 'guide_topic_info',
    },
  ];

  return (
    <div
      className="flex flex-col min-h-screen bg-transparent text-[#E5E7EB] pb-20"
      data-testid="reception_guide_screen_scaffold"
    >
      <TopBar
        title="دليل استقبال البث"
        onBack={onBackClick}
        testTag="reception_guide_screen_title"
        backTestTag="reception_guide_back_button"
      />

      <div className="px-5 py-4 space-y-4 max-w-2xl mx-auto w-full">
        {guideTopics.map((topic) => (
          <div
            key={topic.tag}
            className={`rounded-3xl bg-[#0B1739]/85 backdrop-blur-xl border p-5 space-y-3.5 shadow-xl transition-all relative overflow-hidden ${topic.accentColor}`}
            data-testid={topic.tag}
          >
            <div className={`absolute top-0 right-0 w-28 h-28 ${topic.glowBg} rounded-full blur-2xl pointer-events-none`} />

            <div className="flex items-center gap-3 relative z-10">
              <div className={`w-11 h-11 rounded-2xl border flex items-center justify-center shrink-0 shadow-xs ${topic.iconBg}`}>
                {topic.icon}
              </div>
              <h2
                className="text-base font-bold text-white tracking-tight"
                data-testid={`${topic.tag}_title`}
              >
                {topic.title}
              </h2>
            </div>

            <div className="h-px bg-white/10 relative z-10" />

            <div className="space-y-2.5 relative z-10">
              {topic.points.map((point, pIndex) => (
                <div
                  key={pIndex}
                  className="flex items-start gap-2.5"
                  data-testid={`${topic.tag}_point_${pIndex}`}
                >
                  <div
                    className={`w-5 h-5 rounded-full flex items-center justify-center shrink-0 mt-0.5 border ${topic.badgeColor}`}
                  >
                    <Check className="w-3 h-3" />
                  </div>
                  <p className="text-xs text-[#E5E7EB] leading-relaxed">{point}</p>
                </div>
              ))}
            </div>
          </div>
        ))}
      </div>
    </div>
  );
};
