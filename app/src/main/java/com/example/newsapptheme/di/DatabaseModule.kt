package com.example.newsapptheme.di

import com.example.newsapptheme.data.remote.NewsDataSourceImpl
import com.example.newsapptheme.domain.repository.NewsDataSource
import com.example.newsapptheme.utils.Constants.NEWS
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

//
//@Module
//@InstallIn(SingletonComponent::class)
//object DatabaseModule {
//
//    @Provides
//    @Singleton
//    fun provideDatabase(
//        @ApplicationContext context: Context
//    ): MarkerLocationDatabase {
//        return Room.databaseBuilder(
//            context,
//            MarkerLocationDatabase::class.java,
//            MARKER_LOCATION_DATABASE
//        ).build()
//    }
//

//
//}

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun provideFirebaseFirestore() = Firebase.firestore

    @Provides
    fun provideNewsRef(
        db: FirebaseFirestore
    ) = db.collection(NEWS)


    @Provides
    @Singleton
    fun provideLocalDataSource(
        newsRef: CollectionReference
    ): NewsDataSource {
        return NewsDataSourceImpl(newsRef)
    }
}