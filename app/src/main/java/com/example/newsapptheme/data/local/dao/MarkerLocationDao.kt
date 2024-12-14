package com.example.newsapptheme.data.local.dao

//import androidx.room.Dao
//import androidx.room.Delete
//import androidx.room.Insert
//import androidx.room.OnConflictStrategy
//import androidx.room.Query
//import ai.conscent.conscentnews.domain.model.MarkerLocation
//import kotlinx.coroutines.flow.Flow
//
//@Dao
//interface MarkerLocationDao {
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertMarkerLocation(markerLocation: MarkerLocation)
//
//    @Delete
//    suspend fun deleteMarkerLocation(markerLocation: MarkerLocation)
//
//    @Query("SELECT * FROM marker_location_table")
//    fun getMarkerLocations(): Flow<List<MarkerLocation>>
//}