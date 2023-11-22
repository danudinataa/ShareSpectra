package com.ramaa.narutowiki.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ramaa.narutowiki.domain.model.ItemCharacter
import kotlinx.coroutines.flow.Flow

@Dao
interface CharactersDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(itemCharacter: ItemCharacter)

    @Delete
    suspend fun delete(itemCharacter: ItemCharacter)

    @Query("SELECT * FROM ItemCharacter")
    fun getCharacters(): Flow<List<ItemCharacter>>

    @Query("SELECT * FROM ItemCharacter WHERE id=:id")
    suspend fun getCharacter(id: Int): ItemCharacter?

}