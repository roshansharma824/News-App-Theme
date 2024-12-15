package com.roshan.themebuilder.data

import android.util.Log
import com.google.common.reflect.TypeToken
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.gson.Gson
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class DataSource {
    private val TAG = DataSource::class.java.simpleName
    private val realtimeDb = FirebaseDatabase.getInstance().reference


    /**
     * This method will read a document with provided document id
     * in a collection
     */
    fun getTextDisplayMedium(): Flow<ResultState<PlayText>> = callbackFlow {
        trySend(ResultState.Loading)
        val valueEvent = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val items = snapshot.child("ThemeBuilder/Text/displayMedium")
                val playText = items.getValue(PlayText::class.java)
                if (playText != null) {
                    trySend(ResultState.Success(playText))
                } else {
                    trySend(ResultState.Failure(NullPointerException("PlayText is null")))
                }
                Log.i(TAG, "getItem ${items.value}")
            }

            override fun onCancelled(error: DatabaseError) {
                trySend(ResultState.Failure(error.toException()))
            }
        }

        realtimeDb.addValueEventListener(valueEvent)

        awaitClose {
            realtimeDb.removeEventListener(valueEvent)
            close()
        }
    }

    fun getTextTitleLarge(): Flow<ResultState<PlayText>> = callbackFlow {
        trySend(ResultState.Loading)
        val valueEvent = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val items = snapshot.child("ThemeBuilder/Text/titleLarge")
                val playText = items.getValue(PlayText::class.java)
                if (playText != null) {
                    trySend(ResultState.Success(playText))
                } else {
                    trySend(ResultState.Failure(NullPointerException("PlayText is null")))
                }
                Log.i(TAG, "getTextTitleLarge ${items.value}")
            }

            override fun onCancelled(error: DatabaseError) {
                trySend(ResultState.Failure(error.toException()))
            }
        }

        realtimeDb.addValueEventListener(valueEvent)

        awaitClose {
            realtimeDb.removeEventListener(valueEvent)
            close()
        }
    }

    fun getTextTitleMedium(): Flow<ResultState<PlayText>> = callbackFlow {
        trySend(ResultState.Loading)
        val valueEvent = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val items = snapshot.child("ThemeBuilder/Text/titleMedium")
                val playText = items.getValue(PlayText::class.java)
                if (playText != null) {
                    trySend(ResultState.Success(playText))
                } else {
                    trySend(ResultState.Failure(NullPointerException("PlayText is null")))
                }
                Log.i(TAG, "getTextTitleLarge ${items.value}")
            }

            override fun onCancelled(error: DatabaseError) {
                trySend(ResultState.Failure(error.toException()))
            }
        }

        realtimeDb.addValueEventListener(valueEvent)

        awaitClose {
            realtimeDb.removeEventListener(valueEvent)
            close()
        }
    }

    fun getTextBodyMedium(): Flow<ResultState<PlayText>> = callbackFlow {
        trySend(ResultState.Loading)
        val valueEvent = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val items = snapshot.child("ThemeBuilder/Text/bodyMedium")
                val playText = items.getValue(PlayText::class.java)
                if (playText != null) {
                    trySend(ResultState.Success(playText))
                } else {
                    trySend(ResultState.Failure(NullPointerException("PlayText is null")))
                }
                Log.i(TAG, "getItem ${items.value}")
            }
            override fun onCancelled(error: DatabaseError) {
                trySend(ResultState.Failure(error.toException()))
            }
        }
        realtimeDb.addValueEventListener(valueEvent)
        awaitClose {
            realtimeDb.removeEventListener(valueEvent)
            close()
        }
    }

    fun getTextHeadlineMedium(): Flow<ResultState<PlayText>> = callbackFlow {
        trySend(ResultState.Loading)
        val valueEvent = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val items = snapshot.child("ThemeBuilder/Text/headlineMedium")
                val playText = items.getValue(PlayText::class.java)
                if (playText != null) {
                    trySend(ResultState.Success(playText))
                } else {
                    trySend(ResultState.Failure(NullPointerException("PlayText is null")))
                }
                Log.i(TAG, "getTextHeadlineMedium ${items.value}")
            }
            override fun onCancelled(error: DatabaseError) {
                trySend(ResultState.Failure(error.toException()))
            }
        }
        realtimeDb.addValueEventListener(valueEvent)
        awaitClose {
            realtimeDb.removeEventListener(valueEvent)
            close()
        }
    }

    fun getTextLabelMedium(): Flow<ResultState<PlayText>> = callbackFlow {
        trySend(ResultState.Loading)
        val valueEvent = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val items = snapshot.child("ThemeBuilder/Text/labelMedium")
                val playText = items.getValue(PlayText::class.java)
                if (playText != null) {
                    trySend(ResultState.Success(playText))
                } else {
                    trySend(ResultState.Failure(NullPointerException("PlayText is null")))
                }
                Log.i(TAG, "getItem ${items.value}")
            }

            override fun onCancelled(error: DatabaseError) {
                trySend(ResultState.Failure(error.toException()))
            }
        }

        realtimeDb.addValueEventListener(valueEvent)

        awaitClose {
            realtimeDb.removeEventListener(valueEvent)
            close()
        }
    }

    fun getTextHeadlineSmall(): Flow<ResultState<PlayText>> = callbackFlow {
        trySend(ResultState.Loading)
        val valueEvent = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val items = snapshot.child("ThemeBuilder/Text/headlineSmall")
                val playText = items.getValue(PlayText::class.java)
                if (playText != null) {
                    trySend(ResultState.Success(playText))
                } else {
                    trySend(ResultState.Failure(NullPointerException("PlayText is null")))
                }
                Log.i(TAG, "getItem ${items.value}")
            }

            override fun onCancelled(error: DatabaseError) {
                trySend(ResultState.Failure(error.toException()))
            }
        }

        realtimeDb.addValueEventListener(valueEvent)

        awaitClose {
            realtimeDb.removeEventListener(valueEvent)
            close()
        }
    }


    fun getColorsLight(): Flow<ResultState<ColorSchema>> = callbackFlow {
        trySend(ResultState.Loading)
        val valueEvent = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val items = snapshot.child("ThemeBuilder/ColorSchema/Light")
                val colorSchema = items.getValue(ColorSchema::class.java)
                if (colorSchema != null) {
                    trySend(ResultState.Success(colorSchema))
                } else {
                    trySend(ResultState.Failure(NullPointerException("PlayText is null")))
                }
                Log.i(TAG, "getItem ${items.value}")
            }

            override fun onCancelled(error: DatabaseError) {
                trySend(ResultState.Failure(error.toException()))
            }
        }

        realtimeDb.addValueEventListener(valueEvent)

        awaitClose {
            realtimeDb.removeEventListener(valueEvent)
            close()
        }
    }

    fun getCardData(): Flow<ResultState<PlayCard>> = callbackFlow {
        trySend(ResultState.Loading)
        val valueEvent = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val items = snapshot.child("ThemeBuilder/Card")
                val playCard = items.getValue(PlayCard::class.java)
                if (playCard != null) {
                    trySend(ResultState.Success(playCard))
                } else {
                    trySend(ResultState.Failure(NullPointerException("PlayText is null")))
                }
                Log.i(TAG, "getCardData ${items.value}")
            }

            override fun onCancelled(error: DatabaseError) {
                trySend(ResultState.Failure(error.toException()))
            }
        }

        realtimeDb.addValueEventListener(valueEvent)

        awaitClose {
            realtimeDb.removeEventListener(valueEvent)
            close()
        }
    }


    fun postThemeJson(jsonString:String): Flow<ResultState<String>> = callbackFlow {

        trySend(ResultState.Loading)

        // Step 1: Check if ThemeBuilder node exists
        realtimeDb.child("ThemeBuilder").addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (!snapshot.exists()) {
                    // If the node does not exist, update the state with a success message
                    try {
                        // Step 1: Parse JSON to a Map (or your desired data class)
                        val type = object : TypeToken<Map<String, Any>>() {}.type
                        val data: Map<String, Any> = Gson().fromJson(jsonString, type)
                        // Step 2: Push the parsed JSON to Firebase
                        realtimeDb.child("ThemeBuilder").setValue(data)
                            .addOnFailureListener { exception ->
                                trySend(ResultState.Failure(exception))
                            }
                            .addOnCompleteListener { task ->
                                if (task.isSuccessful) {
                                    trySend(ResultState.Success("Data inserted successfully"))
                                }
                            }
                    } catch (e: Exception) {
                        trySend(ResultState.Failure(e))
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle database errors
                trySend(ResultState.Failure(Exception("Error checking ThemeBuilder node: ${error.message}")))
            }
        })


        awaitClose { close() }
    }
}