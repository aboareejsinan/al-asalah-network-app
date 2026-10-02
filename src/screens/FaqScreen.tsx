import React, { useState } from 'react';
import { HelpCircle, ChevronDown } from 'lucide-react';
import { TopBar } from '../components/TopBar';

interface FaqScreenProps {
  onBack: () => void;
}

export const FaqScreen: React.FC<FaqScreenProps> = ({ onBack }) => {
  const faqList = [
    {
      question: 'هل يحتاج استقبال شبكة الأصالة إلى الإنترنت؟',
      answer: 'لا. استقبال البث الرقمي الأرضي عبر شبكة الأصالة لا يحتاج إلى اتصال بالإنترنت.',
    },
    {
      question: 'كيف يمكن استقبال بث شبكة الأصالة؟',
      answer: 'يمكن استقبال البث مباشرة باستخدام وسيلة استقبال تدعم DVB-T2 / UHF وموجهة نحو برج البث، ويمكن أيضاً استخدام طبق استقبال مع رأس مناسب عند الحاجة حسب الموقع وقوة الإشارة.',
    },
    {
      question: 'ما مواصفات جهاز الاستقبال المطلوب؟',
      answer: 'يجب أن يدعم الجهاز DVB-T2، وللقنوات المشفرة يجب توفر قارئ بطاقة ذكية Smart Card Reader / CA Slot ودعم Multi-CAS والتوافق مع بطاقة DRE-Crypt.',
    },
    {
      question: 'هل يجب استخدام رسيفر خاص بشبكة الأصالة؟',
      answer: 'لا. يمكن استخدام أي جهاز استقبال متوافق مع متطلبات الشبكة.',
    },
    {
      question: 'ماذا أفعل إذا لم تعمل البطاقة الذكية؟',
      answer: 'تأكد من إدخال البطاقة في جهاز استقبال متوافق. وإذا استمرت المشكلة يتم التواصل مع الدعم الفني.',
    },
    {
      question: 'أين تتوفر تغطية شبكة الأصالة؟',
      answer: 'التغطية الحالية في محافظة مأرب وتشمل مدينة مأرب ومديريات الوادي، والشبكة قابلة للتوسع مستقبلاً.',
    },
    {
      question: 'أين أجد ترددات الشبكة؟',
      answer: 'تتوفر الترددات المعتمدة داخل قسم الترددات في التطبيق، ويتم اختيار بيانات البرج المناسب لموقع المشترك.',
    },
    {
      question: 'ما أوقات عمل الدعم الفني؟',
      answer: 'الدوام الرسمي من الساعة 12 ظهراً إلى الساعة 12 صباحاً، وخلال الأحداث الرياضية المهمة يتوفر الدعم على مدار 24 ساعة.',
    },
  ];

  const [expandedIndices, setExpandedIndices] = useState<number[]>([]);

  const toggleIndex = (index: number) => {
    setExpandedIndices((prev) =>
      prev.includes(index) ? prev.filter((i) => i !== index) : [...prev, index]
    );
  };

  return (
    <div
      className="flex flex-col min-h-screen bg-transparent text-[#E5E7EB] pb-20"
      data-testid="faq_screen_scaffold"
    >
      <TopBar
        title="الأسئلة الشائعة"
        onBack={onBack}
        testTag="faq_screen_title"
        backTestTag="faq_back_button"
      />

      <div className="px-5 py-4 space-y-3.5 max-w-2xl mx-auto w-full">
        {faqList.map((item, index) => {
          const isExpanded = expandedIndices.includes(index);
          const colorTheme =
            index % 3 === 0
              ? {
                  color: '#27BDE3',
                  border: isExpanded ? 'border-[#27BDE3]/60' : 'border-[#27BDE3]/20 hover:border-[#27BDE3]/45',
                  glow: 'shadow-[0_4px_20px_rgba(39,189,227,0.18)]',
                  iconBg: 'bg-[#27BDE3]/15 border-[#27BDE3]/30 text-[#27BDE3]',
                  divider: 'border-[#27BDE3]/20',
                }
              : index % 3 === 1
              ? {
                  color: '#8B5CF6',
                  border: isExpanded ? 'border-[#8B5CF6]/60' : 'border-[#8B5CF6]/20 hover:border-[#8B5CF6]/45',
                  glow: 'shadow-[0_4px_20px_rgba(139,92,246,0.18)]',
                  iconBg: 'bg-[#8B5CF6]/15 border-[#8B5CF6]/30 text-[#A78BFA]',
                  divider: 'border-[#8B5CF6]/20',
                }
              : {
                  color: '#C8A45D',
                  border: isExpanded ? 'border-[#C8A45D]/60' : 'border-[#C8A45D]/20 hover:border-[#C8A45D]/45',
                  glow: 'shadow-[0_4px_20px_rgba(200,164,93,0.18)]',
                  iconBg: 'bg-[#C8A45D]/15 border-[#C8A45D]/30 text-[#E0B865]',
                  divider: 'border-[#C8A45D]/20',
                };

          return (
            <div
              key={index}
              onClick={() => toggleIndex(index)}
              className={`rounded-2xl border p-4 transition-all duration-200 cursor-pointer space-y-3 shadow-md backdrop-blur-xl ${
                colorTheme.border
              } ${
                isExpanded
                  ? `bg-[#0B1A3F]/95 ${colorTheme.glow}`
                  : 'bg-[#0B1739]/80 hover:bg-[#0e214d]'
              }`}
              data-testid={`faq_card_${index}`}
            >
              <div className="flex items-center justify-between gap-3">
                <div className="flex items-center gap-3">
                  <div className={`w-9 h-9 rounded-xl border flex items-center justify-center shrink-0 shadow-xs ${colorTheme.iconBg}`}>
                    <HelpCircle className="w-4 h-4" />
                  </div>
                  <h3 className="text-sm font-bold text-white leading-snug">
                    {item.question}
                  </h3>
                </div>

                <ChevronDown
                  className={`w-5 h-5 text-[#9CA3AF] shrink-0 transition-transform duration-200 ${
                    isExpanded ? 'rotate-180' : ''
                  }`}
                  style={{ color: isExpanded ? colorTheme.color : undefined }}
                />
              </div>

              {isExpanded && (
                <div className={`pt-2.5 border-t ${colorTheme.divider}`}>
                  <p
                    className="text-xs text-[#E5E7EB] leading-relaxed"
                    data-testid={`faq_answer_${index}`}
                  >
                    {item.answer}
                  </p>
                </div>
              )}
            </div>
          );
        })}
      </div>
    </div>
  );
};
