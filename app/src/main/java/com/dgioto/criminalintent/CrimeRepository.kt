package com.dgioto.criminalintent

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.dgioto.criminalintent.database.CrimeDataBase
import java.lang.IllegalStateException
import java.util.*
import java.util.concurrent.Executors

private const val DATABASE_NAME = "crime-database"

class CrimeRepository private constructor(context: Context){

    private val dataBase : CrimeDataBase = Room.databaseBuilder(
        context.applicationContext,
        CrimeDataBase::class.java,
        DATABASE_NAME
    ).build()

    private val crimeDao = dataBase.crimeDao()

    //Добавляем свойство исполнителя для хранения ссылки, затем выполняем функции
    // вставки и обновления с помощью исполнителя.
    /*
        Функция newSingleThreadExecutor() возвращает экземпляр исполнителя,
        который указывает на новый поток. Таким образом, любая работа,
        которую вы выполняете с исполнителем, будет происходить вне основного
        потока.
     */
    private val executor = Executors.newSingleThreadExecutor()

    //оборачиваем  список в LiveData
    fun getCrimes(): LiveData<List<Crime>> = crimeDao.getCrimes()
    //оборачиваем  список в LiveData
    fun getCrime(id: UUID): LiveData<Crime?> = crimeDao.getCrime(id)

    /*
        Как updateCrime(), так и addCrime() оборачивают вызовы в DAO
        внутри блока execute {}. Он выталкивает эти операции из основного
        потока, чтобы не блокировать работу пользовательского интерфейса.
     */
    fun updateCrime(crime: Crime){
        executor.execute {
            crimeDao.updateCrime(crime)
        }
    }

    fun addCrime(crime: Crime){
        executor.execute {
            crimeDao.addCrime(crime)
        }
    }

    companion object{
        private var INSTANCE: CrimeRepository? = null

        fun initialize(context: Context){
            if (INSTANCE == null){
                INSTANCE = CrimeRepository(context)
            }
        }

        fun get(): CrimeRepository{
            return INSTANCE ?:
            throw IllegalStateException("CrimeRepository must be initialized")
        }
    }
}