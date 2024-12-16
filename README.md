# ThemeBuilder Library

**ThemeBuilder** is an Android library built using **Jetpack Compose**. It allows developers to create and manage fully customizable themes, supporting both **light** and **dark modes**. With support for dynamic theming, the library empowers you to create a visually consistent and personalized UI experience that matches your app's branding.

---

## 🚀 Features

- **Customizable Theme Colors**: Easily define and manage theme colors for both light and dark modes.
- **Dynamic Theming**: Modify and apply themes in real-time for responsive and personalized UI experiences.
- **Pre-Built Components**:
  - Text styles
  - Cards
  - Dialogs
  - Buttons
  - And more!
- **Brand Matching**: Seamlessly integrate your app's branding into your UI.

---

## 📦 Installation

Add the following dependency to your `build.gradle` file:
- For Kotlin DSL:
```gradle
dependencies {
    implementation("io.github.roshansharma824:themebuilder:1.0.2")
}
```

## ✨ Usage

#### 1. Setting Up ThemeBuilder

#### Initialize ViewModel Inside Application in Android
##### Why Initialize ViewModel in Application?

1. **Global State Management**: Maintain shared data across the entire application lifecycle.
2. **Single Instance**: Use a single instance of the `ViewModel` for centralized logic or state.


---

## 📦 Implementation

### 1. **Create ThemeViewModel**


```kotlin
import android.app.Application
import com.roshan.themebuilder.ui.ThemeViewModel


class ConsCentNewsApp : Application() {
    // Singleton ViewModel instance
    lateinit var themeViewModel: ThemeViewModel
        private set

    override fun onCreate() {
        super.onCreate()
        // Initialize ViewModel once for the entire app lifecycle
        themeViewModel = ThemeViewModel(this)
    }
}
```






















