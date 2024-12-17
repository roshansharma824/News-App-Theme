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

Add the dependency below to your module's `build.gradle` file:
```gradle
dependencies {
    implementation("io.github.roshansharma824:themebuilder:1.0.2")
}
```

## ✨ Setup

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

### 2. **MainActivity Implementation ThemeViewModel**
Initialize `themeViewModel` in MainActivity:
- **ThemeBuilder :** ThemeBuilder uses Material3 Theme 
```kotlin 
class MainActivity : ComponentActivity() {
    // Get a reference to the singleton ViewModel from the Application instance
    private val themeViewModel by lazy { (application as ConsCentNewsApp).themeViewModel }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CompositionLocalProvider(LocalAppViewModel provides themeViewModel){
                ThemeBuilder(themeViewModel = themeViewModel) {
                    // A surface container using the 'background' color from the theme
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        RootNavigationGraph(navController = rememberNavController())
                    }
                }
            }

        }
    }
}
```

#### CardUi
You can use CardUi as `Jetpack Compose Card` composable function as the following example below:
```kotlin
 CardUi(
        modifier = Modifier,
        onClick = {
            onItemClicked.invoke(index)
        }
) {
    // You can execute your own composable functions here
}
```

# Preview App
| Card UI  | 
| ------------- |
| <img src="assets/card_ui_video.mp4" height=500 width=250/>  | 























