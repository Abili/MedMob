# MedMob Doctors App - Architecture Documentation

## Overview
This document outlines the modernization architecture implemented in the MedMob Doctors App.

## Architecture Pattern
The app follows **MVVM (Model-View-ViewModel)** architecture with **Repository Pattern** and **Clean Architecture** principles.

### Layer Structure

```
app/
├── data/                   # Data Layer
│   ├── local/             # Local data sources (Room DB)
│   │   ├── dao/          # Data Access Objects
│   │   └── entity/       # Database entities
│   ├── remote/           # Remote data sources (Retrofit APIs)
│   ├── repository/       # Repository implementations
│   ├── models/           # Data models
│   └── security/         # Security utilities (encryption, biometric)
│
├── domain/                 # Domain Layer
│   ├── usecase/          # Business logic use cases
│   └── repository/       # Repository interfaces
│
├── presentation/          # Presentation Layer
│   ├── auth/             # Authentication screens
│   ├── main/             # Main app screens
│   └── base/             # Base classes
│
├── di/                    # Dependency Injection
│   ├── AppModule.kt      # App-level dependencies
│   └── NetworkModule.kt  # Network dependencies
│
└── utils/                 # Utilities
    ├── Constants.kt      # App constants
    └── Resource.kt       # Result wrapper
```

## Key Technologies

### Core
- **Kotlin** - Modern, null-safe programming language
- **Kotlin Coroutines & Flow** - Asynchronous programming
- **Hilt** - Dependency injection framework
- **ViewBinding** - Type-safe view access

### Data
- **Room** - Local database for offline support
- **Retrofit** - REST API communication
- **OkHttp** - HTTP client with logging
- **Gson** - JSON serialization/deserialization

### Security
- **EncryptedSharedPreferences** - Secure local storage (AES-256)
- **Biometric Authentication** - Fingerprint/Face recognition
- **ProGuard** - Code obfuscation for release builds

### UI
- **Material Design 3** - Modern UI components
- **Glide** - Image loading and caching
- **CameraX** - Medical photography

### Firebase
- **Firebase Auth** - User authentication
- **Firebase Database** - Real-time database
- **Firebase Storage** - File storage
- **Firebase Analytics** - App analytics

## Database Schema

### Tables

#### Doctors
- uid (PRIMARY KEY)
- firstName
- lastName
- phone
- email
- department
- experience
- dateOfBirth
- profilePicUrl
- createdAt
- updatedAt

#### Patients
- uid (PRIMARY KEY)
- firstName
- lastName
- phone
- email
- dateOfBirth
- address
- profilePicUrl
- medicalHistory
- createdAt
- updatedAt

#### Appointments
- id (PRIMARY KEY)
- doctorId (FOREIGN KEY)
- patientId (FOREIGN KEY)
- appointmentDate
- status (PENDING, CONFIRMED, COMPLETED, CANCELLED)
- notes
- location
- createdAt
- updatedAt

## Security Features

### Data Encryption
- All sensitive data stored in EncryptedSharedPreferences
- AES-256 encryption for local storage
- Master key stored in Android Keystore

### Biometric Authentication
- Fingerprint and face recognition support
- Fallback to password authentication
- Biometric availability check

### Network Security
- Certificate pinning support (for production)
- HTTPS enforcement
- Request/response logging in debug mode only

## Build Configuration

### Gradle Setup
- **Gradle Version**: 7.4
- **Android Gradle Plugin**: 7.2.2
- **Kotlin Version**: 1.7.20
- **Compile SDK**: 33
- **Min SDK**: 25
- **Target SDK**: 33

### ProGuard Rules
- Code obfuscation enabled in release builds
- Logging removed in release builds
- Model classes preserved for serialization
- Security classes protected

## Testing Strategy

### Unit Tests
- Business logic in use cases
- Repository implementations
- ViewModels

### Integration Tests
- Database operations
- API calls
- End-to-end flows

### UI Tests
- Screen navigation
- User interactions
- Form validations

## Future Enhancements

### Phase 2 Features
- [ ] Complete migration of all screens to Kotlin
- [ ] Implement all repository patterns
- [ ] Add comprehensive use cases

### Phase 3 Features
- [ ] Jetpack Compose migration
- [ ] Advanced patient management
- [ ] Digital prescription system
- [ ] Video consultation (WebRTC)

### Phase 4 Features
- [ ] AI-powered scheduling
- [ ] Drug interaction checker
- [ ] Clinical decision support
- [ ] Medical calculators

## HIPAA Compliance

### Data Protection
- End-to-end encryption for patient data
- Secure data transmission (HTTPS/TLS)
- Encrypted local storage
- Audit trails for data access

### Access Control
- Biometric authentication
- Multi-factor authentication support
- Session management
- Auto-logout after inactivity

### Data Retention
- Configurable data retention policies
- Secure data deletion
- Backup and recovery procedures

## Contributing
Please follow the established architecture patterns when adding new features:
1. Create data models in `data/models/`
2. Define repository interfaces in `domain/repository/`
3. Implement repositories in `data/repository/`
4. Create use cases in `domain/usecase/`
5. Build ViewModels in `presentation/*/viewmodel/`
6. Design UI in `presentation/*/`

## Contact
For questions or support, please contact the development team.
