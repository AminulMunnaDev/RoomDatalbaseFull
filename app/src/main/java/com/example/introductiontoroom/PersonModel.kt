package com.example.introductiontoroom

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "person_table")
data class PersonModel(
    @PrimaryKey(autoGenerate = true,)
    val pId: Int,
    @ColumnInfo(name = "person_name")
    val name: String,
    @ColumnInfo(name = "person_age")
    val age: Int,
    @ColumnInfo(name = "person_city")
    val city: String
)
