package com.example.introductiontoroom

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface PersonDao {

    @Insert
    suspend fun savePerson(personModel: PersonModel)

    @Update
    suspend fun updatePerson(personModel: PersonModel)

    @Delete
    suspend fun deletePerson(personModel: PersonModel)

    @Query("Delete from person_table where pId = :pId ")
    suspend fun deletePersonById(pId: Int)

    @Query("Select * from person_table")
     fun getAllData(): Flow<List<PersonModel>>

}