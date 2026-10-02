export interface RenewalPlan {
  id: string;
  durationLabel: string;
  durationValue: number; // in months
  price: number;
  currency: string;
  isEnabled: boolean;
  badgeLabel?: string | null;
}

export interface PaymentMethod {
  id: string;
  providerName: string;
  accountNumber?: string | null;
  methodType: string;
  isEnabled: boolean;
  note?: string | null;
}

export interface TransactionRecord {
  requestNumber: string;
  operationType: string;
  smartCardNumber: string;
  planId: string;
  methodId: string;
  status: string;
  requestDate: string;
  transferReference?: string;
  note?: string;
  receiptImage?: string;
}

export interface ChannelPackage {
  id: string;
  name: string;
  channelCount: string;
  accessType: string;
  description: string;
  categories: string[];
  accentColor: string;
  isEncrypted: boolean;
}

export interface ContentCategory {
  title: string;
  iconName: string;
  accentColor: string;
}

export interface PackageDetailInfo {
  id: string;
  packageName: string;
  channelCount: string;
  accessType: string;
  description: string;
  categories: ContentCategory[];
  headerIconName: string;
  accentColor: string;
  isEncrypted: boolean;
}

export interface NotificationItem {
  id: string;
  title: string;
  description: string;
  type: string;
  status?: string | null;
  iconName: string;
  accentColor: string;
  testTag: string;
}

export interface PackageFrequencyData {
  packageName: string;
  isEncrypted: boolean;
  frequency: string;
  polarization: string;
  symbolRate: string;
}

export interface TowerData {
  id: string;
  towerName: string;
  location: string;
  packages: PackageFrequencyData[];
}

export interface GuideTopic {
  title: string;
  iconName: string;
  accentColor: string;
  points: string[];
  tag: string;
}

export interface FaqItem {
  question: string;
  answer: string;
}

export type ScreenState =
  | { type: 'Home' }
  | { type: 'MySubscription' }
  | { type: 'Renewal' }
  | { type: 'PaymentMethod'; planId: string }
  | { type: 'PaymentInstructions'; planId: string; methodId: string }
  | { type: 'PaymentProof'; planId: string; methodId: string }
  | { type: 'RenewalConfirmation'; planId: string; methodId: string; requestNumber: string }
  | { type: 'Transactions'; planId?: string; methodId?: string }
  | { type: 'Account' }
  | { type: 'TransactionDetails'; transaction: TransactionRecord }
  | { type: 'Notifications' }
  | { type: 'SubscriptionDetails' }
  | { type: 'Media' }
  | { type: 'Channels' }
  | { type: 'PackageDetails'; packageInfo: PackageDetailInfo }
  | { type: 'ReceptionGuide' }
  | { type: 'Frequencies' }
  | { type: 'Coverage' }
  | { type: 'ReceiverCardInfo' }
  | { type: 'Support' }
  | { type: 'SupportTicket' }
  | { type: 'Faq' };
