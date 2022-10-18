package com.ermain.bitfit_application

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface BitFitDao {
    @Query("SELECT * FROM bitfit_activity_table")
    fun getAll(): Flow<List<BitFitActivity>>

    @Insert
    fun insertAll(bitFitActivities: List<BitFitActivity>)

    @Insert
    fun insert(bitFitActivity: BitFitActivity)

    @Query("DELETE FROM bitfit_activity_table")
    fun deleteAll()

    @Query("SELECT AVG(calories) FROM bitfit_activity_table")
    fun getAverages(): Int

    @Query("SELECT MAX(calories) FROM bitfit_activity_table")
    fun getMaximum(): Int

    @Query("SELECT MIN(calories) FROM bitfit_activity_table")
    fun getMinimum(): Int
}

