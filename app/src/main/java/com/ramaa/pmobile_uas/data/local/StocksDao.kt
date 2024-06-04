package com.ramaa.pmobile_uas.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ramaa.pmobile_uas.data.remote.response.ResultsStockItem
import kotlinx.coroutines.flow.Flow

@Dao
interface StocksDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(itemStock: ResultsStockItem)

    @Delete
    suspend fun delete(itemStock: ResultsStockItem)

    @Query("SELECT * FROM ResultsStockItem")
    fun getStocks(): Flow<List<ResultsStockItem>>

    @Query("SELECT * FROM ResultsStockItem WHERE symbol=:symbol")
    suspend fun getStock(symbol: String): ResultsStockItem?

}