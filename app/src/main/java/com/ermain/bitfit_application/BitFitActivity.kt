package com.ermain.bitfit_application

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bitfit_activity_table")
data class BitFitActivity(
    @ColumnInfo(name = "activity")
    val activity: String,

    @ColumnInfo(name = "calories")
    val calories: String,

    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
)
