# Telegram Pro 📱

تطبيق Android مخصص بناءً على Telegram مع واجهة مستخدم جديدة بالكامل.

## 🎨 المميزات الرئيسية

### الشاشة الرئيسية
- عرض آخر منشورات القنوات والمجموعات العامة
- تصميم مشابه لـ Twitter (Feed)
- تحديث فوري للمنشورات

### القائمة الجانبية (يمين الشاشة)
- عجلة دائرية تظهر عند الضغط
- خيارات: الرسائل، البوتات، الصفحات، المجموعات
- عرض 3 عناصر مع إمكانية السحب لأعلى/أسفل

### أزرار القاع
- 🏦 **وسط (كبير)**: فتح المحفظة
- 📝 **يمين**: نشر منشور
- ⚙️ **يسار**: الإعدادات

## 🛠️ التقنيات المستخدمة

- **اللغة**: Kotlin
- **الإطار العمل**: Android Jetpack
- **واجهة برمجية**: Telegram Client API (TDLib)
- **التخزين**: Room Database
- **الشبكة**: Retrofit + OkHttp

## 📦 البنية الأساسية

```
Telegram-pro/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/joktoken/telegrammpro/
│   │   │   │   ├── ui/
│   │   │   │   ├── viewmodel/
│   │   │   │   ├── data/
│   │   │   │   ├── MainActivity.kt
│   │   │   │   └── LoginActivity.kt
│   │   │   └── res/
│   │   │       ├── layout/
│   │   │       ├── drawable/
│   │   │       └── values/
│   │   └── AndroidManifest.xml
│   └── build.gradle.kts
├── build.gradle.kts
└── settings.gradle.kts
```

## 🚀 البدء السريع

### المتطلبات
- Android Studio Koala أو أحدث
- SDK 24 أو أعلى
- Telegram Developer Account

### خطوات التثبيت

1. استنساخ المستودع:
```bash
git clone https://github.com/Joktoken/Telegram-pro.git
cd Telegram-pro
```

2. فتح المشروع في Android Studio

3. الحصول على API ID و Hash من [my.telegram.org](https://my.telegram.org)

4. بناء وتشغيل التطبيق

## 📝 الترخيص

MIT License

## 👨‍💻 المطور

**Joktoken**
