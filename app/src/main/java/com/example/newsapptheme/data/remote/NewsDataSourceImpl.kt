package com.example.newsapptheme.data.remote


import com.example.newsapptheme.domain.repository.NewsDataSource
import com.example.newsapptheme.utils.Constants.NEWS
import android.util.Log
import com.example.newsapptheme.domain.model.News
import com.example.newsapptheme.domain.model.Resource

import com.google.firebase.firestore.CollectionReference
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsDataSourceImpl @Inject constructor(
    private val newsRef: CollectionReference
) : NewsDataSource, BaseAPI() {
    override fun addNews(news: News) = callbackFlow<Resource<Void?>> {
        saveDocument(
            collectionName = NEWS,
            data = news,
            onSuccess = {
                trySend(Resource.success(null))
            },
            onFailure = {
                trySend(Resource.error(null, it.localizedMessage ?: "SomeThing Went Wrong"))
            }
        )
        awaitClose {}
    }

    override fun getNews() = callbackFlow<Resource<List<News>>> {
        readDocuments(
            collectionName = NEWS,
            onSuccess = { querySnapshot ->
                querySnapshot?.let {
                    val newsList: List<News> = querySnapshot.documents.mapNotNull { document ->
                        // Get the News object from the document and set the document ID
                        document.toObject(News::class.java)?.apply {
                            id = document.id // Assuming your News class has a documentId field
                        }
                    } // Filter out any null values in case of a mapping failure

                    trySend(Resource.success(newsList)).isSuccess // Send the successful result
                }
            },
            onFailure = {
                trySend(Resource.error(null, it.localizedMessage ?: "\"SomeThing Went Wrong\""))
                Log.d("TAG", "Failed to load News Items")
                it.printStackTrace()
            }
        )
        awaitClose {}
    }


    override fun getNews(documentId: String) = callbackFlow<Resource<News>> {
        readDocument(
            documentId = documentId,
            collectionName = NEWS,
            onSuccess = { querySnapshot->
                querySnapshot?.let {
                    val news: News? = querySnapshot.toObject(News::class.java)?.apply {
                        id = querySnapshot.id // Assuming your News class has a documentId field
                    }
                    news?.let {
                        trySend(Resource.success(news)).isSuccess
                    }
                     // Send the successful result
                }

            },
            onFailure = {
                trySend(Resource.error(null, it.localizedMessage ?: "\"SomeThing Went Wrong\""))
                Log.d("TAG", "Failed to load Published Menu Item")
                it.printStackTrace()
            }
        )
        awaitClose {}
    }


}