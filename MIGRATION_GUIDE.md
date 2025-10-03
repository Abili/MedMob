# MedMob Migration Guide

## Overview
This guide helps you understand the modernization changes made to the MedMob Doctors App and how to work with the new architecture.

## Major Changes

### 1. Build System Upgrades
- **Gradle**: Upgraded from 5.6.4 to 7.4
- **Android Gradle Plugin**: Upgraded from 3.6.3 to 7.2.2
- **Kotlin**: Added version 1.7.20
- **Target SDK**: Upgraded from 28 to 33

### 2. New Dependencies Added

#### Dependency Injection
```kotlin
// Hilt for dependency injection
implementation 'com.google.dagger:hilt-android:2.44'
kapt 'com.google.dagger:hilt-compiler:2.44'
```

#### Database
```kotlin
// Room for local database
implementation 'androidx.room:room-runtime:2.4.3'
implementation 'androidx.room:room-ktx:2.4.3'
kapt 'androidx.room:room-compiler:2.4.3'
```

#### Networking
```kotlin
// Retrofit & OkHttp
implementation 'com.squareup.retrofit2:retrofit:2.9.0'
implementation 'com.squareup.okhttp3:okhttp:4.10.0'
```

#### Security
```kotlin
// Encrypted storage and biometric auth
implementation 'androidx.security:security-crypto:1.1.0-alpha03'
implementation 'androidx.biometric:biometric:1.1.0'
```

### 3. Package Structure

```
app/src/main/java/com/raisac/medmob/
├── MedMobApplication.kt          # Application class with Hilt
│
├── data/                         # Data Layer
│   ├── local/                   # Local data sources
│   │   ├── MedMobDatabase.kt   # Room database
│   │   ├── Converters.kt       # Type converters
│   │   ├── dao/                # Data Access Objects
│   │   │   ├── DoctorDao.kt
│   │   │   ├── PatientDao.kt
│   │   │   └── AppointmentDao.kt
│   │   └── entity/             # Database entities
│   │       ├── DoctorEntity.kt
│   │       ├── PatientEntity.kt
│   │       └── AppointmentEntity.kt
│   ├── repository/             # Repository implementations
│   │   └── AuthRepositoryImpl.kt
│   └── security/               # Security utilities
│       ├── SecureStorage.kt    # Encrypted SharedPreferences
│       └── BiometricHelper.kt  # Biometric authentication
│
├── di/                         # Dependency Injection
│   ├── AppModule.kt           # App dependencies
│   └── NetworkModule.kt       # Network dependencies
│
├── domain/                    # Domain Layer
│   └── repository/           # Repository interfaces
│       └── AuthRepository.kt
│
├── presentation/             # Presentation Layer
│   ├── base/                # Base classes
│   │   └── BaseViewModel.kt
│   └── auth/                # Authentication screens
│       ├── AuthViewModel.kt
│       └── MainActivityKt.kt
│
└── utils/                   # Utilities
    ├── Constants.kt         # App constants
    └── Resource.kt          # Result wrapper
```

## Migration Steps

### Converting Java Activity to Kotlin

#### Before (Java):
```java
public class MainActivity extends AppCompatActivity {
    private Button mRegisterButton;
    private EditText mPhoneNumber;
    private FirebaseAuth mAuth;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPhoneNumber = findViewById(R.id.phoneNumber);
        mRegisterButton = findViewById(R.id.doctorSignUp);
        mAuth = FirebaseAuth.getInstance();
    }
}
```

#### After (Kotlin with MVVM):
```kotlin
@AndroidEntryPoint
class MainActivityKt : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: AuthViewModel by viewModels()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupObservers()
    }
    
    private fun setupObservers() {
        viewModel.authState.observe(this) { resource ->
            when (resource) {
                is Resource.Success -> navigateToProfile()
                is Resource.Error -> showError(resource.message)
                is Resource.Loading -> showLoading()
            }
        }
    }
}
```

### Using the New Architecture

#### 1. Accessing Secure Storage
```kotlin
@Inject
lateinit var secureStorage: SecureStorage

// Save data
secureStorage.saveString(SecureStorage.KEY_AUTH_TOKEN, token)

// Retrieve data
val token = secureStorage.getString(SecureStorage.KEY_AUTH_TOKEN)

// Remove data
secureStorage.remove(SecureStorage.KEY_AUTH_TOKEN)
```

#### 2. Using Biometric Authentication
```kotlin
@Inject
lateinit var biometricHelper: BiometricHelper

// Check if biometric is available
if (biometricHelper.isBiometricAvailable()) {
    biometricHelper.authenticate(
        activity = this,
        title = "Authenticate",
        subtitle = "Use fingerprint to login",
        onSuccess = { 
            // Authentication successful
        },
        onError = { errorMessage ->
            // Handle error
        },
        onFailed = {
            // Authentication failed
        }
    )
}
```

#### 3. Working with Room Database
```kotlin
// Inject DAOs
@Inject
lateinit var patientDao: PatientDao

// Insert patient
viewModelScope.launch {
    val patient = PatientEntity(
        uid = "patient123",
        firstName = "John",
        lastName = "Doe",
        phone = "+256700000000"
    )
    patientDao.insertPatient(patient)
}

// Query patients
patientDao.getAllPatients().collect { patients ->
    // Update UI with patients
}
```

#### 4. Using Repository Pattern
```kotlin
@HiltViewModel
class MyViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : BaseViewModel() {
    
    fun login(phone: String, code: String) {
        viewModelScope.launch {
            val result = authRepository.verifyPhoneNumber(verificationId, code)
            when (result) {
                is Resource.Success -> // Handle success
                is Resource.Error -> // Handle error
                is Resource.Loading -> // Show loading
            }
        }
    }
}
```

## Security Features

### 1. Encrypted Local Storage
All sensitive data is stored using `EncryptedSharedPreferences` with AES-256 encryption:
- Authentication tokens
- User credentials
- Sensitive user preferences

### 2. Biometric Authentication
Support for:
- Fingerprint recognition
- Face recognition
- Fallback to password authentication

### 3. ProGuard Configuration
Release builds are obfuscated with comprehensive ProGuard rules:
- Code obfuscation enabled
- Logging removed in release
- Model classes preserved for serialization

## Testing

### Unit Testing Example
```kotlin
@Test
fun `verify phone number returns success`() = runTest {
    // Arrange
    val repository = AuthRepositoryImpl(mockAuth, mockDatabase, mockStorage)
    
    // Act
    val result = repository.verifyPhoneNumber("verificationId", "123456")
    
    // Assert
    assertTrue(result is Resource.Success)
}
```

### Integration Testing Example
```kotlin
@Test
fun `insert and retrieve patient from database`() = runTest {
    // Arrange
    val patient = PatientEntity(
        uid = "test123",
        firstName = "John",
        lastName = "Doe",
        phone = "+256700000000"
    )
    
    // Act
    patientDao.insertPatient(patient)
    val retrieved = patientDao.getPatientById("test123").first()
    
    // Assert
    assertEquals(patient, retrieved)
}
```

## Common Issues and Solutions

### Issue: ViewBinding not found
**Solution**: Make sure `buildFeatures { viewBinding true }` is enabled in `app/build.gradle`

### Issue: Hilt annotation processors not working
**Solution**: Ensure you're using `kapt` instead of `annotationProcessor` for Hilt:
```kotlin
kapt 'com.google.dagger:hilt-compiler:2.44'
```

### Issue: Room database migration error
**Solution**: For development, use `.fallbackToDestructiveMigration()`. For production, implement proper migrations.

### Issue: Coroutines not working
**Solution**: Make sure you're using `viewModelScope` or `lifecycleScope` for coroutines in Android components.

## Best Practices

1. **Always use dependency injection**: Inject dependencies instead of creating them directly
2. **Use Kotlin Flow for reactive data**: Prefer Flow over LiveData for new features
3. **Handle errors gracefully**: Always use the Resource wrapper for operations that can fail
4. **Keep ViewModels clean**: Business logic should be in UseCases or Repositories
5. **Use sealed classes**: For representing states and results
6. **Leverage Kotlin features**: Use data classes, extension functions, and null safety

## Next Steps

1. **Complete Activity Migration**: Convert all remaining Java Activities to Kotlin
2. **Implement Use Cases**: Extract complex business logic to domain layer
3. **Add Jetpack Compose**: Gradually migrate UI to Compose
4. **Implement Offline Support**: Use Room as single source of truth
5. **Add Comprehensive Tests**: Unit, integration, and UI tests

## Resources

- [Kotlin Documentation](https://kotlinlang.org/docs/home.html)
- [Hilt Documentation](https://dagger.dev/hilt/)
- [Room Documentation](https://developer.android.com/training/data-storage/room)
- [Android Architecture Guide](https://developer.android.com/topic/architecture)
- [Coroutines Guide](https://kotlinlang.org/docs/coroutines-guide.html)

## Support

For questions or issues, please contact the development team or open an issue in the repository.
