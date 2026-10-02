import React from 'react';
import { ArrowRight } from 'lucide-react';

interface TopBarProps {
  title: string;
  onBack?: () => void;
  actions?: React.ReactNode;
  testTag?: string;
  backTestTag?: string;
}

export const TopBar: React.FC<TopBarProps> = ({
  title,
  onBack,
  actions,
  testTag = 'top_bar',
  backTestTag = 'back_button',
}) => {
  return (
    <header className="sticky top-0 z-30 flex items-center justify-between px-4 py-3 bg-[#0B1739]/92 backdrop-blur-xl border-b border-[#27BDE3]/20 shadow-[0_4px_20px_rgba(0,0,0,0.4)]">
      {/* Bottom subtle cyan glow bar */}
      <div className="absolute bottom-0 left-0 right-0 h-[1px] bg-gradient-to-r from-transparent via-[#27BDE3]/40 to-transparent pointer-events-none" />

      <div className="flex items-center gap-3">
        {onBack && (
          <button
            onClick={onBack}
            className="flex items-center justify-center w-10 h-10 rounded-xl bg-[#0B1739] border border-[#27BDE3]/25 text-[#E5E7EB] hover:text-white hover:border-[#27BDE3]/60 hover:bg-[#27BDE3]/15 active:scale-95 transition-all duration-200 shadow-sm"
            data-testid={backTestTag}
            aria-label="الرجوع"
          >
            {/* In RTL, back points right (towards previous history in Arabic flow) */}
            <ArrowRight className="w-5 h-5 text-[#E5E7EB]" />
          </button>
        )}
        <h1
          className="text-base sm:text-lg font-bold text-white tracking-tight break-words"
          data-testid={testTag}
        >
          {title}
        </h1>
      </div>
      {actions && <div className="flex items-center gap-2">{actions}</div>}
    </header>
  );
};
