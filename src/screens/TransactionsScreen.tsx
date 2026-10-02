import React from 'react';
import {
  CreditCard,
  Calendar,
  Building2,
  Repeat,
  Banknote,
  Hourglass,
  RefreshCw,
} from 'lucide-react';
import { TopBar } from '../components/TopBar';
import { RenewalPlanDataSource, PaymentMethodDataSource } from '../data';
import { TransactionRecord } from '../types';

interface TransactionsScreenProps {
  transactions?: TransactionRecord[];
  planId?: string;
  methodId?: string;
  onBackClick: () => void;
  onTransactionClick: (transaction: TransactionRecord) => void;
}

export const TransactionsScreen: React.FC<TransactionsScreenProps> = ({
  transactions,
  planId = 'plan_1_month',
  methodId = 'kuraimi',
  onBackClick,
  onTransactionClick,
}) => {
  const plan =
    RenewalPlanDataSource.plans.find((p) => p.id === planId) ||
    RenewalPlanDataSource.plans[0];

  const method =
    PaymentMethodDataSource.paymentMethods.find((m) => m.id === methodId) ||
    PaymentMethodDataSource.paymentMethods[0];

  const defaultList: TransactionRecord[] = [
    {
      requestNumber: 'ASA-2026-000001',
      operationType: 'تجديد اشتراك',
      smartCardNumber: '**** 4587',
      planId: plan.id,
      methodId: method.id,
      status: 'قيد المراجعة',
      requestDate: '29 سبتمبر 2026',
    },
  ];

  const list = transactions && transactions.length > 0 ? transactions : defaultList;

  return (
    <div
      className="flex flex-col min-h-screen bg-transparent text-[#E5E7EB] pb-20"
      data-testid="transactions_screen_scaffold"
    >
      <TopBar
        title="سجل العمليات"
        onBack={onBackClick}
        testTag="transactions_screen_title"
        backTestTag="transactions_back_button"
      />

      <div
        className="px-5 py-4 space-y-4 max-w-2xl mx-auto w-full"
        data-testid="transactions_content_column"
      >
        {/* Header row */}
        <div className="flex items-center justify-between">
          <div>
            <h2 className="text-base font-bold text-white" data-testid="transactions_section_title">
              العمليات والطلبات
            </h2>
            <p className="text-xs text-[#9CA3AF]">متابعة حالة تجديد الاشتراكات</p>
          </div>
          <span className="text-xs font-semibold px-2.5 py-1 rounded-full bg-[#27BDE3]/15 text-[#27BDE3] border border-[#27BDE3]/30">
            {list.length === 1 ? 'عملية واحدة' : `${list.length} عمليات`}
          </span>
        </div>

        {/* Transaction Cards List */}
        <div className="space-y-4">
          {list.map((tx) => {
            const txPlan =
              RenewalPlanDataSource.plans.find((p) => p.id === tx.planId) || plan;
            const txMethod =
              PaymentMethodDataSource.paymentMethods.find((m) => m.id === tx.methodId) || method;

            return (
              <div
                key={tx.requestNumber}
                onClick={() => onTransactionClick(tx)}
                className="p-5 rounded-3xl bg-gradient-to-br from-[#122452]/90 via-[#0E1D44]/95 to-[#0B1739]/95 backdrop-blur-xl border border-[#C8A45D]/30 hover:border-[#C8A45D]/60 hover:shadow-[0_8px_24px_rgba(200,164,93,0.18)] transition-all cursor-pointer space-y-3.5 shadow-xl active:scale-[0.99] group"
                data-testid={`transaction_card_${tx.requestNumber}`}
              >
                {/* Header Row: Request Number & Warm Amber Status Badge */}
                <div className="flex items-center justify-between">
                  <div>
                    <p className="text-[11px] text-[#9CA3AF]">رقم الطلب</p>
                    <p
                      className="font-mono text-base font-bold text-[#27BDE3] tracking-wider"
                      data-testid="transaction_request_number"
                    >
                      {tx.requestNumber}
                    </p>
                  </div>

                  <div
                    className="flex items-center gap-1.5 px-3 py-1 rounded-full bg-amber-500/15 border border-amber-400/40 text-amber-400 text-xs font-bold"
                    data-testid="transaction_status_badge"
                  >
                    <Hourglass className="w-3.5 h-3.5" />
                    <span>{tx.status}</span>
                  </div>
                </div>

                <div className="h-px bg-white/10" />

                {/* Grid of details */}
                <div className="grid grid-cols-2 gap-3">
                  <div className="flex items-center gap-2">
                    <div className="w-8 h-8 rounded-lg bg-[#C8A45D]/15 border border-[#C8A45D]/30 flex items-center justify-center shrink-0">
                      <RefreshCw className="w-4 h-4 text-[#C8A45D]" />
                    </div>
                    <div>
                      <p className="text-[11px] text-[#9CA3AF]">نوع العملية</p>
                      <p className="text-xs font-bold text-white" data-testid="transaction_type">
                        {tx.operationType}
                      </p>
                    </div>
                  </div>

                  <div className="flex items-center gap-2">
                    <div className="w-8 h-8 rounded-lg bg-[#27BDE3]/15 border border-[#27BDE3]/30 flex items-center justify-center shrink-0">
                      <CreditCard className="w-4 h-4 text-[#27BDE3]" />
                    </div>
                    <div>
                      <p className="text-[11px] text-[#9CA3AF]">البطاقة الذكية</p>
                      <p className="text-xs font-bold text-white" data-testid="transaction_smart_card">
                        {tx.smartCardNumber}
                      </p>
                    </div>
                  </div>
                </div>

                <div className="grid grid-cols-2 gap-3">
                  <div className="flex items-center gap-2">
                    <div className="w-8 h-8 rounded-lg bg-[#27BDE3]/15 border border-[#27BDE3]/30 flex items-center justify-center shrink-0">
                      <Calendar className="w-4 h-4 text-[#27BDE3]" />
                    </div>
                    <div>
                      <p className="text-[11px] text-[#9CA3AF]">مدة التجديد</p>
                      <p className="text-xs font-bold text-white" data-testid="transaction_duration">
                        {txPlan.durationLabel}
                      </p>
                    </div>
                  </div>

                  <div className="flex items-center gap-2">
                    <div className="w-8 h-8 rounded-lg bg-[#C8A45D]/15 border border-[#C8A45D]/30 flex items-center justify-center shrink-0">
                      <Banknote className="w-4 h-4 text-[#C8A45D]" />
                    </div>
                    <div>
                      <p className="text-[11px] text-[#9CA3AF]">المبلغ المطلوب</p>
                      <p className="text-xs font-black text-[#C8A45D]" data-testid="transaction_amount">
                        {RenewalPlanDataSource.formatPrice(txPlan.price, txPlan.currency)}
                      </p>
                    </div>
                  </div>
                </div>

                <div className="flex items-center gap-2 pt-1 border-t border-white/5">
                  <div className="w-8 h-8 rounded-lg bg-[#27BDE3]/15 border border-[#27BDE3]/30 flex items-center justify-center shrink-0">
                    {txMethod.accountNumber ? (
                      <Building2 className="w-4 h-4 text-[#27BDE3]" />
                    ) : (
                      <Repeat className="w-4 h-4 text-[#27BDE3]" />
                    )}
                  </div>
                  <div>
                    <p className="text-[11px] text-[#9CA3AF]">وسيلة الدفع</p>
                    <p className="text-xs font-bold text-white" data-testid="transaction_payment_provider">
                      {txMethod.providerName}
                    </p>
                  </div>
                </div>
              </div>
            );
          })}
        </div>
      </div>
    </div>
  );
};
