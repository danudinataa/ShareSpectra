package com.ramaa.pmobile_uas.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ramaa.pmobile_uas.data.remote.response.ResultsStockItem
import com.ramaa.pmobile_uas.util.Converters

@Database(
    entities = [ResultsStockItem::class],
    version = 1,
    )
@TypeConverters(Converters::class)
abstract class StocksDatabase : RoomDatabase() {

    abstract val stocksDao: StocksDao

}