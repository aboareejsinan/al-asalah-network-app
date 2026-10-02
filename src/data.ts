import {
  RenewalPlan,
  PaymentMethod,
  TransactionRecord,
  ChannelPackage,
  PackageDetailInfo,
  NotificationItem,
  TowerData,
  GuideTopic,
  FaqItem,
} from './types';

export const RenewalPlanDataSource = {
  plans: [
    {
      id: 'plan_1_month',
      durationLabel: 'شهر واحد',
      durationValue: 1,
      price: 3000,
      currency: 'ريال',
      isEnabled: true,
      badgeLabel: null,
    },
    {
      id: 'plan_2_months',
      durationLabel: 'شهران',
      durationValue: 2,
      price: 6000,
      currency: 'ريال',
      isEnabled: true,
      badgeLabel: null,
    },
    {
      id: 'plan_3_months',
      durationLabel: '3 أشهر',
      durationValue: 3,
      price: 9000,
      currency: 'ريال',
      isEnabled: true,
      badgeLabel: null,
    },
    {
      id: 'plan_6_months',
      durationLabel: '6 أشهر',
      durationValue: 6,
      price: 16000,
      currency: 'ريال',
      isEnabled: true,
      badgeLabel: 'الأكثر طلباً',
    },
    {
      id: 'plan_1_year',
      durationLabel: 'سنة',
      durationValue: 12,
      price: 30000,
      currency: 'ريال',
      isEnabled: true,
      badgeLabel: 'أفضل قيمة',
    },
  ] as RenewalPlan[],

  getEnabledPlans(): RenewalPlan[] {
    return this.plans.filter((p) => p.isEnabled);
  },

  formatPrice(price: number, currency: string = 'ريال'): string {
    return `${price.toLocaleString('en-US')} ${currency}`;
  },
};

export const PaymentMethodDataSource = {
  paymentMethods: [
    {
      id: 'kuraimi',
      providerName: 'بنك الكريمي',
      accountNumber: '3059909117',
      methodType: 'حساب بنكي',
      isEnabled: true,
      note: null,
    },
    {
      id: 'alsharq',
      providerName: 'بنك الشرق',
      accountNumber: '411560754',
      methodType: 'حساب بنكي',
      isEnabled: true,
      note: null,
    },
    {
      id: 'qutaibi',
      providerName: 'بنك القطيبي',
      accountNumber: '411059188',
      methodType: 'حساب بنكي',
      isEnabled: true,
      note: null,
    },
    {
      id: 'alinma',
      providerName: 'بنك الإنماء',
      accountNumber: '1010230616110',
      methodType: 'حساب بنكي',
      isEnabled: true,
      note: null,
    },
    {
      id: 'alsalam',
      providerName: 'بنك السلام',
      accountNumber: '1010018468110',
      methodType: 'حساب بنكي',
      isEnabled: true,
      note: null,
    },
    {
      id: 'al_doha',
      providerName: 'الإيداع الدوحة',
      accountNumber: '2317076',
      methodType: 'إيداع نقدي',
      isEnabled: true,
      note: null,
    },
    {
      id: 'qutaibi_shilling',
      providerName: 'القطيبي شلن',
      accountNumber: '778147490',
      methodType: 'حساب شلن',
      isEnabled: true,
      note: null,
    },
    {
      id: 'unified_transfer_network',
      providerName: 'شبكة الحوالات الموحدة',
      accountNumber: null,
      methodType: 'شبكة حوالات',
      isEnabled: true,
      note: 'إرسال قيمة الاشتراك عبر شبكة الحوالات الموحدة',
    },
  ] as PaymentMethod[],

  getEnabledPaymentMethods(): PaymentMethod[] {
    return this.paymentMethods.filter((m) => m.isEnabled);
  },
};

export const initialTransactions: TransactionRecord[] = [
  {
    requestNumber: 'ASA-2026-000001',
    operationType: 'تجديد اشتراك',
    smartCardNumber: '**** 4587',
    planId: 'plan_1_month',
    methodId: 'kuraimi',
    status: 'قيد المراجعة',
    requestDate: '29 سبتمبر 2026',
  },
];

export const channelPackages: ChannelPackage[] = [
  {
    id: 'encrypted_package',
    name: 'الباقة المشفرة',
    channelCount: '20 قناة',
    accessType: 'للمشتركين فقط',
    description: 'تضم قنوات رياضية مشفرة بالإضافة إلى قنوات الأفلام والدراما والترفيه.',
    categories: ['رياضة', 'أفلام', 'دراما', 'ترفيه'],
    accentColor: '#38BDF8',
    isEncrypted: true,
  },
  {
    id: 'open_package',
    name: 'الباقة المفتوحة',
    channelCount: '20 قناة',
    accessType: 'متاحة للجميع',
    description: 'مجموعة قنوات متنوعة متاحة بدون اشتراك.',
    categories: ['أخبار', 'عامة', 'دينية', 'أطفال', 'ترفيه', 'محلية'],
    accentColor: '#34D399',
    isEncrypted: false,
  },
];

export const PackageDetailRepository = {
  encryptedPackage: {
    id: 'encrypted_package',
    packageName: 'الباقة المشفرة',
    channelCount: '20 قناة',
    accessType: 'للمشتركين فقط',
    description: 'تضم مجموعة من القنوات الرياضية المشفرة بالإضافة إلى قنوات الأفلام والدراما والترفيه.',
    categories: [
      { title: 'رياضة', iconName: 'trophy', accentColor: '#38BDF8' },
      { title: 'أفلام', iconName: 'film', accentColor: '#F472B6' },
      { title: 'دراما', iconName: 'tv', accentColor: '#A78BFA' },
      { title: 'ترفيه', iconName: 'sparkles', accentColor: '#FBBF24' },
    ],
    headerIconName: 'lock',
    accentColor: '#38BDF8',
    isEncrypted: true,
  } as PackageDetailInfo,

  openPackage: {
    id: 'open_package',
    packageName: 'الباقة المفتوحة',
    channelCount: '20 قناة',
    accessType: 'متاحة للجميع',
    description: 'مجموعة قنوات متنوعة ومتاحة بدون اشتراك.',
    categories: [
      { title: 'أخبار', iconName: 'newspaper', accentColor: '#38BDF8' },
      { title: 'عامة', iconName: 'globe', accentColor: '#34D399' },
      { title: 'دينية', iconName: 'book-open', accentColor: '#FBBF24' },
      { title: 'أطفال', iconName: 'smile', accentColor: '#FB923C' },
      { title: 'ترفيه', iconName: 'party-popper', accentColor: '#A855F7' },
      { title: 'قنوات محلية', iconName: 'radio', accentColor: '#2DD4BF' },
    ],
    headerIconName: 'radio',
    accentColor: '#34D399',
    isEncrypted: false,
  } as PackageDetailInfo,

  getPackageById(id: string): PackageDetailInfo {
    return id === 'open_package' ? this.openPackage : this.encryptedPackage;
  },
};

export const towersList: TowerData[] = [
  {
    id: 'samdah',
    towerName: 'برج الصمدة (الرئيسي)',
    location: 'الوادي - حصون آل جلال - منطقة الصمدة',
    packages: [
      {
        packageName: 'الباقة المشفرة',
        isEncrypted: true,
        frequency: '12450',
        polarization: 'أفقي',
        symbolRate: '32000',
      },
      {
        packageName: 'الباقة المفتوحة',
        isEncrypted: false,
        frequency: '12226',
        polarization: 'أفقي',
        symbolRate: '32000',
      },
    ],
  },
  {
    id: 'haiat',
    towerName: 'برج الهيئة',
    location: 'المدينة - غرباً - أمام مستشفى الهيئة الطبي',
    packages: [
      {
        packageName: 'الباقة المشفرة',
        isEncrypted: true,
        frequency: '12500',
        polarization: 'أفقي',
        symbolRate: '32000',
      },
      {
        packageName: 'الباقة المفتوحة',
        isEncrypted: false,
        frequency: '12300',
        polarization: 'أفقي',
        symbolRate: '32000',
      },
    ],
  },
];

export const guideTopics: GuideTopic[] = [
  {
    title: 'كيف تستقبل شبكة الأصالة؟',
    iconName: 'radio',
    accentColor: '#34D399',
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
    iconName: 'tv',
    accentColor: '#38BDF8',
    points: [
      'جهاز تلفزيون أو رسيفر يدعم DVB-T2.',
      'رسيفر خارجي عند الحاجة.',
      'قارئ بطاقة ذكية Smart Card Reader / CA Slot لاستقبال القنوات المشفرة.',
      'دعم Multi-CAS والتوافق مع بطاقة DRE-Crypt للقنوات المشفرة.',
      'كابل Coaxial RG6 للتوصيل.',
    ],
    tag: 'guide_topic_devices',
  },
  {
    title: 'طريقة التركيب',
    iconName: 'wrench',
    accentColor: '#FBBF24',
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
    iconName: 'settings',
    accentColor: '#A78BFA',
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
    iconName: 'info',
    accentColor: '#2DD4BF',
    points: [
      'DVB-T2 هو معيار للبث التلفزيوني الرقمي الأرضي عبر أبراج البث.',
      'يوفر جودة أفضل للصورة والصوت.',
      'لا يحتاج إلى اتصال بالإنترنت لاستقبال البث.',
      'جودة الاستقبال تعتمد على قوة الإشارة وموقع المشترك.',
    ],
    tag: 'guide_topic_info',
  },
];

export const faqList: FaqItem[] = [
  {
    question: 'هل يحتاج استقبال شبكة الأصالة إلى الإنترنت؟',
    answer: 'لا. استقبال البث الرقمي الأرضي عبر شبكة الأصالة لا يحتاج إلى اتصال بالإنترنت.',
  },
  {
    question: 'كيف يمكن استقبال بث شبكة الأصالة؟',
    answer:
      'يمكن استقبال البث مباشرة باستخدام وسيلة استقبال تدعم DVB-T2 / UHF وموجهة نحو برج البث، ويمكن أيضاً استخدام طبق استقبال مع رأس مناسب عند الحاجة حسب الموقع وقوة الإشارة.',
  },
  {
    question: 'ما مواصفات جهاز الاستقبال المطلوب؟',
    answer:
      'يجب أن يدعم الجهاز DVB-T2، وللقنوات المشفرة يجب توفر قارئ بطاقة ذكية Smart Card Reader / CA Slot ودعم Multi-CAS والتوافق مع بطاقة DRE-Crypt.',
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
    answer:
      'التغطية الحالية في محافظة مأرب وتشمل مدينة مأرب ومديريات الوادي، والشبكة قابلة للتوسع مستقبلاً.',
  },
  {
    question: 'أين أجد ترددات الشبكة؟',
    answer:
      'تتوفر الترددات المعتمدة داخل قسم الترددات في التطبيق، ويتم اختيار بيانات البرج المناسب لموقع المشترك.',
  },
  {
    question: 'ما أوقات عمل الدعم الفني؟',
    answer:
      'الدوام الرسمي من الساعة 12 ظهراً إلى الساعة 12 صباحاً، وخلال الأحداث الرياضية المهمة يتوفر الدعم على مدار 24 ساعة.',
  },
];

export const notificationsList: NotificationItem[] = [
  {
    id: 'notif_1',
    title: 'تم تفعيل الاشتراك',
    description: 'تم تحديث حالة اشتراكك بنجاح',
    type: 'اشتراك',
    status: 'جديد',
    iconName: 'check-circle',
    accentColor: '#34D399',
    testTag: 'notification_item_1',
  },
  {
    id: 'notif_2',
    title: 'تذكير بالتجديد',
    description: 'متبقي 16 يوم على انتهاء الاشتراك',
    type: 'تنبيه',
    iconName: 'clock',
    accentColor: '#FBBF24',
    testTag: 'notification_item_2',
  },
  {
    id: 'notif_3',
    title: 'تحديث جديد في شبكة الأصالة',
    description: 'تابع آخر الأخبار والخدمات الجديدة',
    type: 'أخبار',
    iconName: 'megaphone',
    accentColor: '#38BDF8',
    testTag: 'notification_item_3',
  },
  {
    id: 'notif_4',
    title: 'طلب تجديد قيد المراجعة',
    description: 'تم استلام إثبات الدفع وجاري مراجعة الطلب',
    type: 'عمليات',
    iconName: 'receipt',
    accentColor: '#A855F7',
    testTag: 'notification_item_4',
  },
];

export const homeMockChannels = [
  { name: 'الأصالة 1', type: 'عامة', accentColor: '#38BDF8', iconBgColor: 'rgba(2, 132, 199, 0.2)' },
  { name: 'الأصالة دراما', type: 'دراما', accentColor: '#A78BFA', iconBgColor: 'rgba(124, 58, 237, 0.2)' },
  { name: 'الأصالة رياضة', type: 'رياضة', accentColor: '#34D399', iconBgColor: 'rgba(5, 150, 105, 0.2)' },
  { name: 'الأصالة وثائقية', type: 'وثائقي', accentColor: '#FBBF24', iconBgColor: 'rgba(217, 119, 6, 0.2)' },
  { name: 'الأصالة إخبارية', type: 'إخبارية', accentColor: '#FB7185', iconBgColor: 'rgba(225, 29, 72, 0.2)' },
  { name: 'الأصالة أطفال', type: 'أطفال', accentColor: '#2DD4BF', iconBgColor: 'rgba(13, 148, 136, 0.2)' },
];
