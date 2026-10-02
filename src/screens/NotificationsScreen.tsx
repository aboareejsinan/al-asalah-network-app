import React from 'react';
import {
  CheckCircle2,
  Clock,
  Megaphone,
  Receipt,
  Bell,
} from 'lucide-react';
import { TopBar } from '../components/TopBar';

interface NotificationsScreenProps {
  onBackClick: () => void;
}

export const NotificationsScreen: React.FC<NotificationsScreenProps> = ({
  onBackClick,
}) => {
  const notifications = [
    {
      id: 'notif_1',
      title: 'تم تفعيل الاشتراك',
      description: 'تم تحديث حالة اشتراكك بنجاح',
      type: 'اشتراك',
      status: 'جديد',
      icon: <CheckCircle2 className="w-5 h-5 text-emerald-400" />,
      accentColor: 'border-emerald-500/40 bg-emerald-500/20 text-emerald-400',
      cardBorder: 'border-emerald-500/35 hover:border-emerald-500/60',
      cardBg: 'bg-gradient-to-br from-[#0c2438]/85 via-[#0c1f36]/90 to-[#0B1739]/95',
      iconBg: 'bg-emerald-500/15 border-emerald-500/30',
      glowBg: 'bg-emerald-500/10',
      testTag: 'notification_item_1',
    },
    {
      id: 'notif_2',
      title: 'تذكير بالتجديد',
      description: 'متبقي 16 يوم على انتهاء الاشتراك',
      type: 'تنبيه',
      icon: <Clock className="w-5 h-5 text-[#C8A45D]" />,
      accentColor: 'border-[#C8A45D]/40 bg-[#C8A45D]/20 text-[#E0B865]',
      cardBorder: 'border-[#C8A45D]/35 hover:border-[#C8A45D]/60',
      cardBg: 'bg-gradient-to-br from-[#1c2436]/85 via-[#141d33]/90 to-[#0B1739]/95',
      iconBg: 'bg-[#C8A45D]/15 border-[#C8A45D]/30',
      glowBg: 'bg-[#C8A45D]/10',
      testTag: 'notification_item_2',
    },
    {
      id: 'notif_3',
      title: 'تحديث جديد في شبكة الأصالة',
      description: 'تابع آخر الأخبار والخدمات وباقات القنوات الجديدة',
      type: 'أخبار',
      icon: <Megaphone className="w-5 h-5 text-[#8B5CF6]" />,
      accentColor: 'border-[#8B5CF6]/40 bg-[#8B5CF6]/20 text-[#C4B5FD]',
      cardBorder: 'border-[#8B5CF6]/35 hover:border-[#8B5CF6]/60',
      cardBg: 'bg-gradient-to-br from-[#1a1742]/85 via-[#131538]/90 to-[#0B1739]/95',
      iconBg: 'bg-[#8B5CF6]/15 border-[#8B5CF6]/30',
      glowBg: 'bg-[#8B5CF6]/10',
      testTag: 'notification_item_3',
    },
    {
      id: 'notif_4',
      title: 'طلب تجديد قيد المراجعة',
      description: 'تم استلام إثبات الدفع وجاري مراجعة الطلب من الإدارة',
      type: 'عمليات',
      icon: <Receipt className="w-5 h-5 text-[#27BDE3]" />,
      accentColor: 'border-[#27BDE3]/40 bg-[#27BDE3]/20 text-[#38BDF8]',
      cardBorder: 'border-[#27BDE3]/35 hover:border-[#27BDE3]/60',
      cardBg: 'bg-gradient-to-br from-[#0c294a]/85 via-[#0c2242]/90 to-[#0B1739]/95',
      iconBg: 'bg-[#27BDE3]/15 border-[#27BDE3]/30',
      glowBg: 'bg-[#27BDE3]/10',
      testTag: 'notification_item_4',
    },
  ];

  return (
    <div
      className="flex flex-col min-h-screen bg-transparent text-[#E5E7EB] pb-20"
      data-testid="notifications_screen_scaffold"
    >
      <TopBar
        title="الإشعارات"
        onBack={onBackClick}
        testTag="notifications_screen_title"
        backTestTag="notifications_back_button"
      />

      <div
        className="px-5 py-4 space-y-3.5 max-w-2xl mx-auto w-full"
        data-testid="notifications_content_column"
      >
        {notifications.map((item) => (
          <div
            key={item.id}
            className={`p-4 rounded-3xl ${item.cardBg} backdrop-blur-xl border ${item.cardBorder} transition-all space-y-3 shadow-xl relative overflow-hidden`}
            data-testid={item.testTag}
          >
            <div className={`absolute top-0 right-0 w-28 h-28 ${item.glowBg} rounded-full blur-2xl pointer-events-none`} />

            <div className="flex items-start gap-3.5 relative z-10">
              <div className={`w-11 h-11 rounded-2xl ${item.iconBg} border flex items-center justify-center shrink-0 shadow-xs`}>
                {item.icon}
              </div>

              <div className="flex-1 space-y-1">
                <div className="flex items-center justify-between">
                  <span
                    className={`text-[11px] font-bold px-2.5 py-0.5 rounded-full border shadow-2xs ${item.accentColor}`}
                  >
                    {item.type}
                  </span>

                  {item.status && (
                    <span
                      className="text-[11px] font-bold px-2.5 py-0.5 rounded-full bg-emerald-500/20 text-emerald-400 border border-emerald-500/40 shadow-xs"
                      data-testid={`notification_status_${item.id}`}
                    >
                      {item.status}
                    </span>
                  )}
                </div>

                <h3
                  className="text-sm font-bold text-white pt-1 tracking-tight"
                  data-testid={`notification_title_${item.id}`}
                >
                  {item.title}
                </h3>
                <p
                  className="text-xs text-[#E5E7EB] leading-relaxed"
                  data-testid={`notification_desc_${item.id}`}
                >
                  {item.description}
                </p>
              </div>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
};
