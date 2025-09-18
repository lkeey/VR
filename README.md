# VR Arena - Desktop Application

A modern desktop application for V/S Arena built with Kotlin Multiplatform and Jetpack Compose, featuring a media player and video platform capabilities.

## 🏗️ Project Structure

```
VR/
├── composeApp/                    # Main application module
│   ├── commonMain/               # Shared code across platforms
│   │   ├── composeResources/     # Shared resources (images, fonts, etc.)
│   │   └── sqldelight/          # Database schema and queries
│   └── desktopMain/             # Desktop-specific code
│       └── kotlin/              # Kotlin source files
├── gradle/                      # Gradle configuration
├── .github/workflows/           # CI/CD pipeline
├── build.gradle.kts            # Root build configuration
├── settings.gradle.kts         # Project settings
└── README.md                   # This file
```

## 🛠️ Technology Stack

### Core Technologies
- **Kotlin Multiplatform** (2.1.20) - Cross-platform development framework
- **Jetpack Compose Multiplatform** (1.7.3) - Modern declarative UI framework
- **Gradle** (Kotlin DSL) - Build automation and dependency management
- **Java 17** - Runtime environment

### UI & Framework
- **Compose UI** - Declarative UI toolkit
- **Compose Material** - Material Design components
- **Compose Foundation** - Basic building blocks
- **Compose Resources** - Resource management system

### Database & Persistence
- **SQLDelight** (2.0.2) - Type-safe SQL code generation
- **SQLite** (3.47.0.0) - Embedded database engine
- **Coroutines Extensions** - Asynchronous database operations

### Media & Video
- **VLCJ** (4.8.2) - Java wrapper for VLC media player
- **Kotlinx Coroutines** (1.10.2) - Asynchronous programming
- **Kotlinx Serialization** (1.7.3) - JSON serialization/deserialization

### Architecture & Lifecycle
- **AndroidX Lifecycle** (2.8.4) - Lifecycle-aware components
- **ViewModel** - UI-related data holder
- **Compose Runtime** - Compose state management

### Build & Distribution
- **Compose Desktop** - Desktop application packaging
- **Native Distributions** - Platform-specific installers
  - **DMG** - macOS installer
  - **MSI** - Windows installer  
  - **DEB** - Linux Debian package
- **ProGuard** - Code obfuscation and optimization

### Development Tools
- **IntelliJ IDEA** - Recommended IDE
- **Gradle Wrapper** - Consistent build environment
- **Git** - Version control

## 🚀 Getting Started

### Prerequisites
- **JDK 17+** (OpenJDK or Oracle JDK)
- **Git** for version control
- **IntelliJ IDEA** (recommended) or Android Studio

### Installation & Setup

1. **Clone the repository:**
   ```bash
   git clone https://github.com/lkeey/VR.git
   cd VR
   git checkout dev
   ```

2. **Verify Java installation:**
   ```bash
   java -version  # Should show Java 17 or higher
   ```

3. **Build the project:**
   ```bash
   ./gradlew clean build
   ```

4. **Run the application:**
   ```bash
   ./gradlew run
   ```

### Building Distributables

To create platform-specific installers:

```bash
# Build all distributions
./gradlew packageDmg packageMsi packageDeb

# Or build specific platform
./gradlew packageDmg    # macOS
./gradlew packageMsi    # Windows
./gradlew packageDeb    # Linux
```

## 🔄 CI/CD Pipeline

The project includes automated CI/CD using GitHub Actions:

- **Triggers**: Pushes to `dev` and `main` branches
- **Platforms**: macOS, Windows, Linux
- **Artifacts**: DMG, MSI, and DEB installers
- **Releases**: Automatic release creation with downloadable assets

### Workflow Features
- Multi-platform builds (macOS, Windows, Linux)
- Automatic artifact generation
- Release management with versioning
- Artifact retention (30 days)
- Branch-specific release types (dev = prerelease, main = stable)

## 📦 Distribution

The application generates native installers for multiple platforms:

- **macOS**: `.dmg` file for easy installation
- **Windows**: `.msi` installer for Windows systems
- **Linux**: `.deb` package for Debian/Ubuntu systems

## 🗄️ Database

The application uses SQLDelight for type-safe database operations:
- **Database**: SQLite (embedded)
- **Schema**: Defined in `commonMain/sqldelight/`
- **Queries**: Type-safe Kotlin code generation
- **Migrations**: Handled by SQLDelight

## 🎥 Media Features

- **Video Playback**: Powered by VLCJ (VLC media player integration)
- **Cross-platform**: Works on Windows, macOS, and Linux
- **Format Support**: All formats supported by VLC
- **UI Integration**: Seamless integration with Compose UI

## 📝 Development

### Code Structure
- **commonMain**: Shared business logic and UI
- **desktopMain**: Desktop-specific implementations
- **Resources**: Centralized asset management
- **Database**: Type-safe SQL with SQLDelight

### Key Dependencies
```kotlin
// UI Framework
implementation(compose.runtime)
implementation(compose.foundation)
implementation(compose.material)

// Database
implementation("app.cash.sqldelight:runtime:2.0.2")
implementation("app.cash.sqldelight:sqlite-driver:2.0.2")

// Media
implementation("uk.co.caprica:vlcj:4.8.2")

// Serialization
implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.7.3")
```

## 📄 License

This project is licensed under the GPL-3.0 License - see the [LICENSE](LICENSE) file for details.

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## 📞 Support

For support and questions, please open an issue in the GitHub repository.