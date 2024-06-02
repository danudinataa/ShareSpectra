package com.ramaa.pmobile_uas.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ramaa.pmobile_uas.data.remote.response.ResultsStockItem
import com.ramaa.pmobile_uas.domain.model.ItemCharacter
import kotlinx.coroutines.flow.Flow

@Dao
interface CharactersDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(itemCharacter: ResultsStockItem)

    @Delete
    suspend fun delete(itemCharacter: ResultsStockItem)

    @Query("SELECT * FROM ResultsStockItem")
    fun getCharacters(): Flow<List<ResultsStockItem>>

    @Query("SELECT * FROM ResultsStockItem WHERE symbol=:symbol")
    suspend fun getCharacter(symbol: String): ResultsStockItem?

}