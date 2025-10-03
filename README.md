# MedMob Doctors App

A modern, HIPAA-compliant Android application for healthcare professionals to manage patient care, appointments, and medical records.

## Overview

MedMob is being transformed from a traditional Java-based Android app into a professional-grade medical application featuring:
- **Modern Architecture**: MVVM with Clean Architecture principles
- **Enhanced Security**: AES-256 encryption, biometric authentication, HIPAA compliance
- **Offline-First**: Room database with intelligent sync
- **Professional Features**: Patient management, digital prescriptions, video consultations

## Key Features

### Current Features ✅
- 🔐 **Secure Authentication** - Phone verification with Firebase, biometric login
- 💾 **Offline Support** - Room database for local data storage
- 🏗️ **Modern Architecture** - MVVM pattern with dependency injection (Hilt)
- 🔒 **Data Encryption** - AES-256 encrypted local storage
- 📱 **Type-Safe UI** - ViewBinding for all screens
- 🧪 **Testing Ready** - Unit test infrastructure with Mockito

### Planned Features 📋
- 👥 **Patient Management** - Comprehensive patient profiles with medical history
- 📅 **Smart Scheduling** - AI-powered appointment scheduling
- 💊 **Digital Prescriptions** - Electronic prescription system with drug interaction warnings
- 🎥 **Video Consultations** - WebRTC-based telemedicine
- 📊 **Clinical Tools** - Vital signs tracking, medical calculators
- 🎨 **Modern UI** - Material Design 3 with Jetpack Compose

## Tech Stack

### Core
- **Language**: Kotlin 1.7.20 (migrating from Java)
- **Min SDK**: 25 (Android 7.1)
- **Target SDK**: 33 (Android 13)
- **Architecture**: MVVM + Clean Architecture

### Libraries
- **Dependency Injection**: Hilt
- **Database**: Room
- **Networking**: Retrofit + OkHttp
- **Image Loading**: Glide
- **Firebase**: Auth, Database, Storage, Analytics
- **Security**: EncryptedSharedPreferences, Biometric
- **UI**: ViewBinding, Material Design 3
- **Testing**: JUnit, Mockito, Espresso

## Project Structure

```
app/
├── data/                   # Data Layer
│   ├── local/             # Room database
│   ├── remote/            # API services
│   ├── repository/        # Repository implementations
│   ├── models/            # Data models
│   └── security/          # Security utilities
├── domain/                # Domain Layer
│   ├── repository/        # Repository interfaces
│   └── usecase/           # Business logic
├── presentation/          # Presentation Layer
│   ├── auth/              # Authentication screens
│   ├── main/              # Main app screens
│   └── base/              # Base classes
├── di/                    # Dependency Injection
└── utils/                 # Utilities
```

## Getting Started

### Prerequisites
- Android Studio Arctic Fox or later
- JDK 11 or higher
- Android SDK 33
- Google Services configuration (`google-services.json`)

### Setup
1. Clone the repository
   ```bash
   git clone https://github.com/Abili/MedMob.git
   ```

2. Open in Android Studio
   ```bash
   cd MedMob
   # Open the project in Android Studio
   ```

3. Add Google Services configuration
   - Download `google-services.json` from Firebase Console
   - Place it in `app/` directory

4. Sync Gradle and Build
   ```bash
   ./gradlew build
   ```

## Documentation

- **[Architecture Guide](ARCHITECTURE.md)** - Technical architecture details
- **[Migration Guide](MIGRATION_GUIDE.md)** - Guide for migrating to modern stack
- **[Implementation Roadmap](ROADMAP.md)** - Detailed development plan
- **[Features Overview](FEATURES.md)** - Complete feature list

## Development

### Running Tests
```bash
# Unit tests
./gradlew test

# Instrumented tests
./gradlew connectedAndroidTest
```

### Code Style
- Follow Kotlin coding conventions
- Use ViewBinding for view access
- Implement MVVM pattern for new screens
- Write unit tests for business logic

### Contributing
We welcome contributions! Please:
1. Fork the repository
2. Create a feature branch
3. Follow the architecture patterns
4. Write tests for new features
5. Submit a pull request

## Security

This app implements multiple security layers:
- **Encryption**: AES-256 for data at rest
- **Authentication**: Multi-factor with biometric support
- **Network**: HTTPS/TLS for all communications
- **Code**: ProGuard obfuscation for releases

### Reporting Security Issues
Please report security vulnerabilities to [security contact]

## HIPAA Compliance

The app is being developed with HIPAA compliance in mind:
- ✅ Encrypted data storage
- ✅ Secure authentication
- 🚧 Audit trails
- 🚧 Access controls
- 📋 Data retention policies

## Roadmap

See [ROADMAP.md](ROADMAP.md) for detailed implementation plan.

**Current Phase**: Phase 5 - Repository Pattern & Authentication ✅  
**Next Phase**: Phase 6 - Complete Activity Migration

## Version History

### v1.0.0 (In Development)
- Modern architecture implementation
- Security enhancements
- Database foundation
- Authentication system
- Documentation

### v0.1.0 (Legacy)
- Initial Java implementation
- Basic Firebase integration
- Phone authentication
- Doctor profiles

## License

[Add your license here]

## Contact

For questions or support:
- **GitHub Issues**: [Create an issue](https://github.com/Abili/MedMob/issues)
- **Developer**: Abili

## Acknowledgments

- Firebase for backend services
- Android Jetpack for modern components
- Material Design for UI guidelines

---

**Status**: 🚧 Active Development  
**Last Updated**: 2024  
**Maintained by**: Abili

## Quick Links

- [Features](FEATURES.md)
- [Architecture](ARCHITECTURE.md)
- [Migration Guide](MIGRATION_GUIDE.md)
- [Roadmap](ROADMAP.md)
- [Issues](https://github.com/Abili/MedMob/issues)
- [Pull Requests](https://github.com/Abili/MedMob/pulls)

