import React, { useState } from 'react';
import {
  CreditCard,
  Phone,
  FileText,
  Send,
  Info,
  CheckCircle2,
} from 'lucide-react';
import { TopBar } from '../components/TopBar';

interface SupportTicketScreenProps {
  onSubmitTicket: (cardNumber: string, contactNumber: string, problemDescription: string) => void;
  onBack: () => void;
}

export const SupportTicketScreen: React.FC<SupportTicketScreenProps> = ({
  onSubmitTicket,
  onBack,
}) => {
  const [cardNumber, setCardNumber] = useState('');
  const [contactNumber, setContactNumber] = useState('');
  const [problemDescription, setProblemDescription] = useState('');
  const [isSubmitted, setIsSubmitted] = useState(false);

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    onSubmitTicket(cardNumber, contactNumber, problemDescription);
    setIsSubmitted(true);
    setTimeout(() => {
      onBack();
    }, 2000);
  };

  return (
    <div
      className="flex flex-col min-h-screen bg-transparent text-[#E5E7EB] pb-20"
      data-testid="support_ticket_screen_scaffold"
    >
      <TopBar
        title="فتح بلاغ"
        onBack={onBack}
        testTag="support_ticket_screen_title"
        backTestTag="support_ticket_back_button"
      />

      <div className="px-5 py-4 space-y-4 max-w-2xl mx-auto w-full">
        {/* Info Note Card */}
        <div
          className="rounded-2xl bg-[#0B1739]/85 backdrop-blur-xl border border-[#27BDE3]/30 p-4 flex items-start gap-3 shadow-md"
          data-testid="support_ticket_info_card"
        >
          <Info className="w-5 h-5 text-[#27BDE3] shrink-0 mt-0.5" />
          <p
            className="text-xs text-[#E5E7EB] leading-relaxed font-medium"
            data-testid="support_ticket_info_text"
          >
            يرجى توضيح المشكلة بشكل دقيق لتسهيل متابعتها من فريق الدعم الفني لشبكة الأصالة.
          </p>
        </div>

        {isSubmitted ? (
          <div className="rounded-3xl bg-[#0B1739]/90 backdrop-blur-xl border border-emerald-400/50 p-8 text-center space-y-3 shadow-xl">
            <div className="w-16 h-16 rounded-full bg-emerald-500/20 text-emerald-400 flex items-center justify-center mx-auto border border-emerald-500/40">
              <CheckCircle2 className="w-10 h-10" />
            </div>
            <h2 className="text-lg font-bold text-white">تم إرسال البلاغ بنجاح</h2>
            <p className="text-xs text-[#9CA3AF]">
              سيقوم فريق الدعم الفني بمراجعة بلاغك والتواصل معك عبر رقم الهاتف المسجل قريباً.
            </p>
          </div>
        ) : (
          <form onSubmit={handleSubmit} className="space-y-4">
            {/* Form Card */}
            <div
              className="rounded-3xl bg-[#0B1739]/85 backdrop-blur-xl border border-[#27BDE3]/20 p-5 space-y-4 shadow-xl"
              data-testid="support_ticket_form_card"
            >
              {/* Field 1: رقم البطاقة */}
              <div className="space-y-1.5">
                <label
                  htmlFor="cardNumber"
                  className="text-xs font-bold text-white"
                >
                  رقم البطاقة الذكية
                </label>
                <div className="relative">
                  <div className="absolute inset-y-0 right-0 pr-3.5 flex items-center pointer-events-none text-[#27BDE3]">
                    <CreditCard className="w-4 h-4" />
                  </div>
                  <input
                    id="cardNumber"
                    type="text"
                    required
                    value={cardNumber}
                    onChange={(e) => setCardNumber(e.target.value)}
                    placeholder="أدخل رقم البطاقة الذكية"
                    className="w-full pr-10 pl-4 py-3 rounded-xl bg-[#07112B] border border-[#27BDE3]/20 focus:border-[#27BDE3] focus:outline-none text-white text-sm placeholder:text-[#4B5563]"
                    data-testid="ticket_card_number_input"
                  />
                </div>
              </div>

              {/* Field 2: رقم التواصل */}
              <div className="space-y-1.5">
                <label
                  htmlFor="contactNumber"
                  className="text-xs font-bold text-white"
                >
                  رقم الهاتف للتواصل
                </label>
                <div className="relative">
                  <div className="absolute inset-y-0 right-0 pr-3.5 flex items-center pointer-events-none text-emerald-400">
                    <Phone className="w-4 h-4" />
                  </div>
                  <input
                    id="contactNumber"
                    type="tel"
                    required
                    value={contactNumber}
                    onChange={(e) => setContactNumber(e.target.value)}
                    placeholder="أدخل رقم التواصل (واتساب أو اتصال)"
                    className="w-full pr-10 pl-4 py-3 rounded-xl bg-[#07112B] border border-[#27BDE3]/20 focus:border-emerald-400 focus:outline-none text-white text-sm placeholder:text-[#4B5563]"
                    data-testid="ticket_contact_number_input"
                  />
                </div>
              </div>

              {/* Field 3: وصف المشكلة */}
              <div className="space-y-1.5">
                <label
                  htmlFor="problemDescription"
                  className="text-xs font-bold text-white"
                >
                  وصف المشكلة بالتفصيل
                </label>
                <div className="relative">
                  <textarea
                    id="problemDescription"
                    rows={4}
                    required
                    value={problemDescription}
                    onChange={(e) => setProblemDescription(e.target.value)}
                    placeholder="اكتب تفاصيل المشكلة أو استفسارك هنا"
                    className="w-full px-4 py-3 rounded-xl bg-[#07112B] border border-[#27BDE3]/20 focus:border-[#C8A45D] focus:outline-none text-white text-sm placeholder:text-[#4B5563] resize-none"
                    data-testid="ticket_problem_description_input"
                  />
                </div>
              </div>
            </div>

            {/* Submit Button */}
            <button
              type="submit"
              className="w-full py-3.5 px-6 rounded-xl bg-gradient-to-r from-[#27BDE3] to-[#1cb0d4] hover:brightness-110 text-[#0B1739] font-black text-base shadow-lg shadow-[#27BDE3]/25 transition-all flex items-center justify-center gap-2 active:scale-[0.99]"
              data-testid="submit_ticket_button"
            >
              <Send className="w-4 h-4 rtl:rotate-180" />
              <span>إرسال البلاغ</span>
            </button>
          </form>
        )}
      </div>
    </div>
  );
};
