package com.dgioto.criminalintent.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.dgioto.criminalintent.Crime
import java.util.*

@Dao
interface CrimeDao {

    @Query("SELECT * FROM crime")
    //оборачиваем  список в LiveData
    fun getCrimes(): LiveData<List<Crime>>

    @Query("SELECT * FROM crime WHERE id=(:id)")
    //оборачиваем  список в LiveData
    fun getCrime(id: UUID): LiveData<Crime?>
}