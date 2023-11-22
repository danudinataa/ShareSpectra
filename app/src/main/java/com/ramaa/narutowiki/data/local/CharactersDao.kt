package com.ramaa.narutowiki.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ramaa.narutowiki.domain.model.Character
import kotlinx.coroutines.flow.Flow

@Dao
interface CharactersDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(character: Character)

    @Delete
    suspend fun delete(character: Character)

    @Query("SELECT * FROM Character")
    fun getCharacters(): Flow<List<Character>>

    @Query("SELECT * FROM Character WHERE id=:id")
    suspend fun getCharacter(id: Int): Character?

}