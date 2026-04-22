# 🕒 Android Widget Clock Sample

Android widgets are one of the most powerful yet underutilized features in mobile apps. They allow users to interact with your app directly from the home screen without opening it.

# 📸 Preview

> Add your widget screenshot here

---

# 🧱 Architecture Overview

```
AppWidgetProvider (BroadcastReceiver)
        ↓
ClockWidgetUpdater (Logic Layer)
        ↓
RemoteViews (UI)
        ↓
AppWidgetManager (System Update)
```

---

# 🧩 Widget Components

### 1. AppWidgetProvider
- Entry point (extends `BroadcastReceiver`)
- Handles lifecycle:
  - `onUpdate()`
  - `onEnabled()`
  - `onDisabled()`
  - `onReceive()`

### 2. AppWidgetProviderInfo (XML)
Defines metadata such as:
- Size (`minWidth`, `minHeight`)
- Layout (`initialLayout`)
- Update behavior
- Preview UI

### 3. RemoteViews Layout
- Limited UI components
- Rendered by launcher

### 4. AppWidgetManager
- Updates widget UI
- Communicates with system

---

# ⚙️ How It Works

- Widget updates via:
  - `ACTION_TIME_TICK` (every minute)
  - `TIMEZONE_CHANGED`
  - `TIME_CHANGED`

- Activity uses:
  - `Handler` for second-based updates (only when visible)

---

# 🔋 Battery Optimization Strategy

| State | Behavior |
|------|--------|
| Screen ON | Update UI |
| Screen OFF | No updates |
| Background | No work |

---

# 🚫 Things to Avoid

### ❌ Frequent Updates
- Avoid per-second updates in widget

### ❌ Heavy Work in Widget
- No API calls inside `onUpdate()`

### ❌ Complex Layouts
- Widgets support limited views only

### ❌ Background Services
- Not reliable for widgets

### ❌ Blocking Main Thread
- Can cause ANR

---

# 🧠 Best Practices

- ✅ Use system broadcasts (`TIME_TICK`)
- ✅ Keep UI minimal
- ✅ Update only when needed
- ✅ Separate logic
- ✅ Provide preview layout/image

---


---

# 📚 References

### 📝 Medium Article
https://medium.com/@orbitalsonic/building-a-production-ready-android-clock-widget-complete-guide-0ab32bc5e2ae

### 📖 Official Android Documentation
https://developer.android.com/develop/ui/views/appwidgets

