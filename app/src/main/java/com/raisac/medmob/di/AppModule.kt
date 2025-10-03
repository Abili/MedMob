package com.raisac.medmob.di

import android.content.Context
import androidx.room.Room
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.raisac.medmob.data.local.MedMobDatabase
import com.raisac.medmob.data.repository.AuthRepositoryImpl
import com.raisac.medmob.data.security.SecureStorage
import com.raisac.medmob.domain.repository.AuthRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Dagger Hilt module for providing app-wide dependencies
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    @Singleton
    fun provideFirebaseDatabase(): FirebaseDatabase = FirebaseDatabase.getInstance()

    @Provides
    @Singleton
    fun provideFirebaseStorage(): FirebaseStorage = FirebaseStorage.getInstance()

    @Provides
    @Singleton
    fun provideMedMobDatabase(
        @ApplicationContext context: Context
    ): MedMobDatabase {
        return Room.databaseBuilder(
            context,
            MedMobDatabase::class.java,
            "medmob_database"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideAuthRepository(
        firebaseAuth: FirebaseAuth,
        firebaseDatabase: FirebaseDatabase,
        secureStorage: SecureStorage
    ): AuthRepository {
        return AuthRepositoryImpl(firebaseAuth, firebaseDatabase, secureStorage)
    }
}

