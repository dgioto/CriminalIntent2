package com.dgioto.criminalintent.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.dgioto.criminalintent.Crime

@Database(entities = [Crime::class], version = 1, exportSchema = false)
@TypeConverters(CrimeTypeConverters::class)
abstract class CrimeDataBase : RoomDatabase() {

    abstract fun crimeDao(): CrimeDao
}