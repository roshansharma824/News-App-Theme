package com.example.newsapptheme.data.remote

import android.net.Uri
import android.util.Log
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.coroutines.tasks.await
import java.util.UUID

/**
 * This class contains a reference of Firestore data base as db
 * and some functions for basic CRUD operations
 *
 * Every API class must extend this BaseAPI class
 */

open class BaseAPI {

    private val TAG = BaseAPI::class.java.simpleName
    protected val db = FirebaseFirestore.getInstance()

    /**
     * This method will save a document with a random document id
     * in a Collection
     */
    protected fun saveDocument(
        collectionName: String,
        data: Any,
        onSuccess: (DocumentReference?) -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        db.collection(collectionName)
            .add(data)
            .addOnSuccessListener { documentReference ->
                Log.d(TAG, "Document saved to $collectionName")
                onSuccess(documentReference)
            }
            .addOnFailureListener { exception ->
                Log.d(TAG, "Failed to save document to $collectionName")
                onFailure(exception)
            }
    }

    protected suspend fun saveDocument(
        collectionName: String,
        dataObject: Any
    ): DocumentReference? {
        return try {
            val data = db.collection(collectionName)
                .add(dataObject)
                .await()
            data
        } catch (exc: Exception) {
            Log.d(TAG, exc.message?:"")
            null
        }
    }

    /**
     * This method will save a document with provided document id
     * in a Collection
     */
    protected fun saveDocument(
        collectionName: String,
        documentId: String,
        data: Any,
        onSuccess: (Void?) -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        db.collection(collectionName)
            .document(documentId)
            .set(data)
            .addOnSuccessListener { void ->
                Log.d(TAG, "Document saved to $collectionName with id $documentId")
                onSuccess(void)
            }
            .addOnFailureListener { exception ->
                Log.d(TAG, "Failed to save document to $collectionName with id $documentId")
                onFailure(exception)
            }
    }

    protected suspend fun saveDocument(
        collectionName: String,
        documentId: String,
        dataObject: Any
    ): Void? {
        return try {
            val data = db.collection(collectionName)
                .document(documentId)
                .set(dataObject)
                .await()
            data
        } catch (exc: Exception) {
            null
        }
    }

    /**
     * This method will save a list of document with random document id's
     * in a Collection
     */
    protected fun saveDocumentList(
        collectionName: String,
        data: List<Any>,
        onSuccess: (Void?) -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        val collectionRef = db.collection(collectionName)
        db.runBatch { batch ->
            // A batch of writes completes atomically
            data.forEach { any ->
                batch.set(collectionRef.document(UUID.randomUUID()!!.toString()), any)
            }
        }.addOnSuccessListener { void ->
            Log.d(TAG, "Document list saved to $collectionName")
            onSuccess(void)
        }.addOnFailureListener { exception ->
            Log.d(TAG, "Failed to save document list to $collectionName")
            onFailure(exception)
        }
    }

    /**
     * This method will read a document with provided document id
     * in a collection
     */
    protected fun readDocument(
        collectionName: String,
        documentId: String,
        onSuccess: (DocumentSnapshot?) -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        db.collection(collectionName)
            .document(documentId)
            .get()
            .addOnSuccessListener { documentSnapshot ->
                Log.d(TAG, "Document $documentId fetched from $collectionName")
                onSuccess(documentSnapshot)
            }
            .addOnFailureListener { exception ->
                Log.d(TAG, "Failed to fetch document $documentId from $collectionName")
                onFailure(exception)
            }
    }
    protected fun readDocuments(
        collectionName: String,
        onSuccess: (QuerySnapshot?) -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        db.collection(collectionName)
            .get()
            .addOnSuccessListener { documentSnapshot ->
                Log.d(TAG, "Document fetched from $collectionName")
                onSuccess(documentSnapshot)
            }
            .addOnFailureListener { exception ->
                Log.d(TAG, "Failed to fetch document  from $collectionName")
                onFailure(exception)
            }
    }

    protected suspend fun readDocument(
        collectionName: String,
        documentId: String
    ): DocumentSnapshot? {
        return try {
            val data = db.collection(collectionName)
                .document(documentId)
                .get()
                .await()
            data
        } catch (exc: Exception) {
            null
        }
    }

    /**
     * This method will update a document with provided document id
     * in a collection
     */

    protected fun updateDocument(
        collectionName: String,
        documentId: String,
        data: Any,
        onSuccess: (Void?) -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        saveDocument(collectionName, documentId, data, onSuccess, onFailure)
    }

    protected suspend fun updateDocument(
        collectionName: String,
        documentId: String,
        data: Any,
    ): Void? {
        return saveDocument(
            collectionName = collectionName,
            documentId = documentId,
            dataObject = data
        )
    }

    /**
     * This method will delete a document with provided document id
     * in a collection
     */

    protected fun deleteDocument(
        collectionName: String,
        documentId: String,
        onSuccess: (Void?) -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        db.collection(collectionName)
            .document(documentId)
            .delete()
            .addOnSuccessListener { void ->
                Log.d(TAG, "Document $documentId deleted from $collectionName")
                onSuccess(void)
            }
            .addOnFailureListener { exception ->
                Log.d(TAG, "Failed to delete document $documentId from $collectionName")
                onFailure(exception)
            }
    }

    protected suspend fun deleteDocument(
        collectionName: String,
        documentId: String
    ): Void? {
        return try {
            val data = db.collection(collectionName)
                .document(documentId)
                .delete()
                .await()
            data
        } catch (exc: Exception) {
            Log.d(TAG, exc.message?:"")
            null
        }
    }

    protected fun uploadImage(
        pathString: String,
        imageUri: Uri,
        onSuccess: (String) -> Unit,
        onFailure: (Exception) -> Unit
    ) {
//        val storage = FirebaseStorage.getInstance()
//        val storageReference = storage.reference
//        val profilePicStorageRef =
//            storageReference.child(pathString)
//        val uploadTask = profilePicStorageRef.putFile(imageUri)
//        uploadTask.addOnFailureListener {
//            Log.d(TAG, "${it.message}")
//            onFailure(it)
//        }.addOnSuccessListener {
//            Log.d(TAG, "${it.metadata}")
//            val downloadUrl = profilePicStorageRef.downloadUrl
//            downloadUrl.addOnSuccessListener { imageUrl ->
//                Log.d(TAG, imageUrl.toString())
//                onSuccess(imageUrl.toString())
//            }
//        }
    }

//    protected fun getPathString(folderName: String, imageUri: Uri) =
//        "${folderName}/${FirebaseAuth.getInstance().uid}/${imageUri.lastPathSegment}"
}