import React from 'react';
import { Home, LayoutGrid, Tv, Headset, User } from 'lucide-react';

interface BottomNavProps {
  currentTab: string;
  onNavigateHome: () => void;
  onNavigateServices?: () => void;
  onNavigateMedia?: () => void;
  onNavigateChannels?: () => void;
  onNavigateSupport: () => void;
  onNavigateAccount: () => void;
}

export const BottomNav: React.FC<BottomNavProps> = ({
  currentTab,
  onNavigateHome,
  onNavigateServices,
  onNavigateMedia,
  onNavigateChannels,
  onNavigateSupport,
  onNavigateAccount,
}) => {
  const items = [
    {
      id: 'home',
      label: 'الرئيسية',
      icon: Home,
      onClick: onNavigateHome,
      testTag: 'nav_item_home',
    },
    {
      id: 'services',
      label: 'الخدمات',
      icon: LayoutGrid,
      onClick: onNavigateServices || onNavigateHome,
      testTag: 'nav_item_services',
    },
    {
      id: 'media',
      label: 'الإعلام',
      icon: Tv,
      onClick: onNavigateMedia || onNavigateChannels || (() => {}),
      testTag: 'nav_item_media',
    },
    {
      id: 'support',
      label: 'الدعم',
      icon: Headset,
      onClick: onNavigateSupport,
      testTag: 'nav_item_support',
    },
    {
      id: 'account',
      label: 'حسابي',
      icon: User,
      onClick: onNavigateAccount,
      testTag: 'nav_item_profile',
    },
  ];

  return (
    <nav
      className="fixed bottom-0 left-0 right-0 z-40 bg-[#0B1739]/92 backdrop-blur-2xl border-t border-[#27BDE3]/20 py-2 px-3 safe-area-bottom shadow-[0_-8px_32px_rgba(0,0,0,0.65)]"
      data-testid="home_bottom_navigation_bar"
    >
      {/* Top subtle cyan scanline highlight */}
      <div className="absolute top-0 left-1/2 -translate-x-1/2 w-48 h-[1px] bg-gradient-to-r from-transparent via-[#27BDE3]/60 to-transparent pointer-events-none" />

      <div className="max-w-md mx-auto flex items-center justify-around">
        {items.map((item) => {
          const IconComponent = item.icon;
          const isSelected = currentTab === item.id;
          return (
            <button
              key={item.id}
              onClick={item.onClick}
              data-testid={item.testTag}
              className={`flex flex-col items-center justify-center flex-1 py-1 min-h-[46px] transition-all duration-200 active:scale-95 ${
                isSelected ? 'text-[#FFFFFF]' : 'text-[#9CA3AF] hover:text-[#E5E7EB]'
              }`}
            >
              <div
                className={`relative px-3 py-1 rounded-xl transition-all duration-200 ${
                  isSelected
                    ? 'bg-gradient-to-b from-[#27BDE3]/20 to-[#27BDE3]/5 border border-[#27BDE3]/40 shadow-[0_0_12px_rgba(39,189,227,0.3)] text-[#27BDE3]'
                    : 'text-[#9CA3AF]'
                }`}
              >
                <IconComponent className={`w-5 h-5 transition-transform duration-200 ${isSelected ? 'scale-110' : ''}`} />
                {isSelected && (
                  <span className="absolute -bottom-1 left-1/2 -translate-x-1/2 w-1.5 h-1.5 rounded-full bg-[#C8A45D] shadow-[0_0_6px_#C8A45D]" />
                )}
              </div>
              <span className={`text-[11px] mt-1 tracking-tight font-medium ${isSelected ? 'text-[#27BDE3] font-bold' : ''}`}>
                {item.label}
              </span>
            </button>
          );
        })}
      </div>
    </nav>
  );
};
