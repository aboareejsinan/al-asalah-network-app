import React, { useState } from 'react';
import {
  CreditCard,
  Calendar,
  Building2,
  Repeat,
  Banknote,
  Send,
  Image as ImageIcon,
  Edit2,
  Trash2,
  FileText,
  ShieldCheck,
  Hash,
} from 'lucide-react';
import { TopBar } from '../components/TopBar';
import { RenewalPlanDataSource, PaymentMethodDataSource } from '../data';

interface PaymentProofScreenProps {
  planId?: string;
  methodId?: string;
  onBackClick: () => void;
  onSubmitProof: (data: {
    planId: string;
    methodId: string;
    transferReference: string;
    note: string;
    receiptImage: string | null;
  }) => void;
}

export const PaymentProofScreen: React.FC<PaymentProofScreenProps> = ({
  planId = 'plan_1_month',
  methodId = 'kuraimi',
  onBackClick,
  onSubmitProof,
}) => {
  const plan =
    RenewalPlanDataSource.plans.find((p) => p.id === planId) ||
    RenewalPlanDataSource.plans[0];

  const method =
    PaymentMethodDataSource.paymentMethods.find((m) => m.id === methodId) ||
    PaymentMethodDataSource.paymentMethods[0];

  const [transferReference, setTransferReference] = useState('');
  const [noteText, setNoteText] = useState('');
  const [receiptImage, setReceiptImage] = useState<string | null>(null);

  const handleImageChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const file = e.target.files?.[0];
    if (file) {
      const reader = new FileReader();
      reader.onloadend = () => {
        setReceiptImage(reader.result as string);
      };
      reader.readAsDataURL(file);
    }
  };

  const handleRemoveImage = () => {
    setReceiptImage(null);
  };

  return (
    <div
      className="flex flex-col min-h-screen bg-transparent text-[#E5E7EB] pb-28"
      data-testid="payment_proof_scaffold"
    >
      <TopBar
        title="إثبات الدفع"
        onBack={onBackClick}
        testTag="payment_proof_title"
        backTestTag="payment_proof_back_button"
      />

      <div
        className="px-5 py-3 space-y-5 max-w-2xl mx-auto w-full"
        data-testid="payment_proof_content_column"
      >
        {/* 1. Compact Summary Card */}
        <div
          className="rounded-3xl bg-gradient-to-br from-[#122452]/90 via-[#0E1D44]/95 to-[#0B1739]/95 backdrop-blur-xl border border-[#C8A45D]/35 p-5 space-y-4 shadow-xl"
          data-testid="payment_proof_summary_card"
        >
          <div className="grid grid-cols-2 gap-3">
            <div className="flex items-center gap-2.5">
              <div className="w-9 h-9 rounded-xl bg-[#27BDE3]/15 border border-[#27BDE3]/25 flex items-center justify-center shrink-0">
                <CreditCard className="w-5 h-5 text-[#27BDE3]" />
              </div>
              <div>
                <p className="text-[11px] text-[#9CA3AF]">البطاقة الذكية</p>
                <p className="text-sm font-bold text-white font-mono" data-testid="proof_summary_smart_card">
                  **** 4587
                </p>
              </div>
            </div>

            <div className="flex items-center gap-2.5">
              <div className="w-9 h-9 rounded-xl bg-[#27BDE3]/15 border border-[#27BDE3]/25 flex items-center justify-center shrink-0">
                <Calendar className="w-5 h-5 text-[#27BDE3]" />
              </div>
              <div>
                <p className="text-[11px] text-[#9CA3AF]">مدة التجديد</p>
                <p className="text-sm font-bold text-white" data-testid="proof_summary_duration">
                  {plan.durationLabel}
                </p>
              </div>
            </div>
          </div>

          <div className="h-px bg-white/10" />

          <div className="grid grid-cols-2 gap-3 items-center">
            <div className="flex items-center gap-2.5">
              <div className="w-9 h-9 rounded-xl bg-[#27BDE3]/15 border border-[#27BDE3]/25 flex items-center justify-center shrink-0">
                {method.accountNumber ? (
                  <Building2 className="w-5 h-5 text-[#27BDE3]" />
                ) : (
                  <Repeat className="w-5 h-5 text-[#27BDE3]" />
                )}
              </div>
              <div>
                <p className="text-[11px] text-[#9CA3AF]">وسيلة الدفع</p>
                <p className="text-sm font-bold text-white" data-testid="proof_summary_provider">
                  {method.providerName}
                </p>
              </div>
            </div>

            <div className="flex items-center gap-2.5">
              <div className="w-9 h-9 rounded-xl bg-[#C8A45D]/15 border border-[#C8A45D]/25 flex items-center justify-center shrink-0">
                <Banknote className="w-5 h-5 text-[#C8A45D]" />
              </div>
              <div>
                <p className="text-[11px] text-[#9CA3AF]">المبلغ المطلوب</p>
                <p className="text-sm font-extrabold text-[#C8A45D]" data-testid="proof_summary_amount">
                  {RenewalPlanDataSource.formatPrice(plan.price, plan.currency)}
                </p>
              </div>
            </div>
          </div>
        </div>

        {/* 2. Transfer Reference Input */}
        <div className="rounded-2xl bg-[#0B1739]/80 backdrop-blur-md border border-[#27BDE3]/20 p-4 space-y-3">
          <div className="flex items-center gap-2">
            <Hash className="w-4 h-4 text-[#27BDE3]" />
            <label htmlFor="refInput" className="text-sm font-bold text-white">
              رقم عملية التحويل
            </label>
          </div>
          <input
            id="refInput"
            type="text"
            value={transferReference}
            onChange={(e) => setTransferReference(e.target.value)}
            placeholder="أدخل رقم العملية إن وجد"
            className="w-full px-4 py-3 rounded-xl bg-[#07112B] border border-[#27BDE3]/25 focus:border-[#27BDE3] focus:outline-none text-white text-sm placeholder:text-[#9CA3AF] transition-colors"
            data-testid="transfer_reference_input"
          />
        </div>

        {/* 3. Proof Image Area */}
        <div
          className="rounded-3xl border border-[#27BDE3]/30 bg-[#0B1739]/85 backdrop-blur-xl p-5 space-y-3 shadow-lg relative overflow-hidden"
          data-testid="proof_image_card"
        >
          <div className="absolute top-0 right-0 w-32 h-32 bg-[#27BDE3]/5 rounded-full blur-xl pointer-events-none" />

          {!receiptImage ? (
            <div className="flex flex-col items-center justify-center text-center py-4 space-y-3 relative z-10">
              <div className="w-14 h-14 rounded-2xl bg-[#27BDE3]/15 border border-[#27BDE3]/30 flex items-center justify-center text-[#27BDE3]">
                <ImageIcon className="w-7 h-7" />
              </div>
              <div>
                <h3 className="text-sm font-bold text-white" data-testid="proof_image_title">
                  صورة الإيصال
                </h3>
                <p className="text-xs text-[#9CA3AF] mt-1" data-testid="proof_image_description">
                  أرفق صورة واضحة لإيصال أو إثبات التحويل
                </p>
              </div>

              <label
                className="cursor-pointer inline-flex items-center gap-2 px-5 py-2.5 rounded-xl border border-[#27BDE3]/40 bg-[#27BDE3]/15 hover:bg-[#27BDE3]/25 text-white text-xs font-bold transition-all active:scale-[0.98]"
                data-testid="attach_receipt_button"
              >
                <ImageIcon className="w-4 h-4 text-[#27BDE3]" />
                <span>إرفاق صورة</span>
                <input
                  type="file"
                  accept="image/*"
                  onChange={handleImageChange}
                  className="hidden"
                />
              </label>
            </div>
          ) : (
            <div className="flex flex-col items-center space-y-3 relative z-10">
              <h3 className="text-xs font-bold text-white" data-testid="proof_image_title">
                صورة الإيصال المرفقة
              </h3>
              <div className="w-36 h-36 rounded-2xl overflow-hidden border-2 border-[#27BDE3]/40 shadow-xl">
                <img
                  src={receiptImage}
                  alt="Receipt Preview"
                  className="w-full h-full object-cover"
                  data-testid="receipt_image_preview"
                />
              </div>
              <div className="flex items-center gap-3">
                <label
                  className="cursor-pointer inline-flex items-center gap-1.5 px-3.5 py-1.5 rounded-xl border border-[#27BDE3]/40 text-[#27BDE3] hover:bg-[#27BDE3]/15 text-xs font-bold transition-colors"
                  data-testid="change_receipt_button"
                >
                  <Edit2 className="w-3.5 h-3.5" />
                  <span>تغيير الصورة</span>
                  <input
                    type="file"
                    accept="image/*"
                    onChange={handleImageChange}
                    className="hidden"
                  />
                </label>

                <button
                  type="button"
                  onClick={handleRemoveImage}
                  className="inline-flex items-center gap-1.5 px-3.5 py-1.5 rounded-xl border border-rose-500/40 text-rose-300 hover:bg-rose-500/15 text-xs font-bold transition-colors"
                  data-testid="remove_receipt_button"
                >
                  <Trash2 className="w-3.5 h-3.5" />
                  <span>إزالة</span>
                </button>
              </div>
            </div>
          )}
        </div>

        {/* 4. Optional Note Input */}
        <div className="rounded-2xl bg-[#0B1739]/80 backdrop-blur-md border border-[#27BDE3]/20 p-4 space-y-3">
          <div className="flex items-center gap-2">
            <FileText className="w-4 h-4 text-[#27BDE3]" />
            <label htmlFor="noteInput" className="text-sm font-bold text-white">
              ملاحظة
            </label>
          </div>
          <textarea
            id="noteInput"
            value={noteText}
            onChange={(e) => setNoteText(e.target.value)}
            rows={3}
            placeholder="أضف ملاحظة عند الحاجة"
            className="w-full px-4 py-3 rounded-xl bg-[#07112B] border border-[#27BDE3]/25 focus:border-[#27BDE3] focus:outline-none text-white text-sm placeholder:text-[#9CA3AF] resize-none transition-colors"
            data-testid="optional_note_input"
          />
        </div>

        {/* 5. Review Info Card */}
        <div
          className="rounded-2xl bg-[#0B1739]/85 backdrop-blur-md border border-[#27BDE3]/25 p-4 flex items-start gap-3 shadow-lg"
          data-testid="review_info_card"
        >
          <div className="w-9 h-9 rounded-xl bg-[#27BDE3]/15 border border-[#27BDE3]/25 flex items-center justify-center shrink-0">
            <ShieldCheck className="w-5 h-5 text-[#27BDE3]" />
          </div>
          <div className="space-y-1">
            <h3 className="text-sm font-bold text-white" data-testid="review_info_title">
              مراجعة العملية
            </h3>
            <p className="text-xs text-[#9CA3AF] leading-relaxed" data-testid="review_info_text">
              سيتم التحقق من عملية الدفع قبل تنفيذ تجديد الاشتراك.
            </p>
          </div>
        </div>
      </div>

      {/* Fixed Bottom Action */}
      <div className="fixed bottom-0 inset-x-0 bg-[#0B1739]/95 backdrop-blur-2xl border-t border-[#27BDE3]/20 p-4 z-40 shadow-[0_-8px_32px_rgba(0,0,0,0.65)]">
        <div className="max-w-2xl mx-auto">
          <button
            onClick={() =>
              onSubmitProof({
                planId: plan.id,
                methodId: method.id,
                transferReference,
                note: noteText,
                receiptImage,
              })
            }
            className="w-full py-4 px-6 rounded-2xl bg-gradient-to-r from-[#C8A45D] via-[#e0c079] to-[#C8A45D] hover:from-[#d5b56e] hover:to-[#d5b56e] text-[#0B1739] font-black text-sm tracking-wide shadow-lg shadow-[#C8A45D]/30 transition-all flex items-center justify-center gap-2 active:scale-[0.99]"
            data-testid="submit_proof_button"
          >
            <Send className="w-5 h-5 rtl:rotate-180 text-[#0B1739]" />
            <span>إرسال الطلب</span>
          </button>
        </div>
      </div>
    </div>
  );
};
