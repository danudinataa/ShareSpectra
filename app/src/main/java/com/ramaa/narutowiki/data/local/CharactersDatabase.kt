package com.ramaa.narutowiki.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ramaa.narutowiki.domain.model.ItemCharacter

@Database(
    entities = [ItemCharacter::class],
    version = 1,
    )
@TypeConverters(CharacterConverter::class)
abstract class CharactersDatabase : RoomDatabase() {

    abstract val charactersDao: CharactersDao

}