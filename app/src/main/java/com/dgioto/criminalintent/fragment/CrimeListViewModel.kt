package com.dgioto.criminalintent.fragment

import androidx.lifecycle.ViewModel
import com.dgioto.criminalintent.Crime
import com.dgioto.criminalintent.CrimeRepository

class CrimeListViewModel: ViewModel() {

    private val crimeRepository = CrimeRepository.get()
    val crimeListLiveData = crimeRepository.getCrimes()

    fun addCrime(crime : Crime){
        crimeRepository.addCrime(crime)
    }
}