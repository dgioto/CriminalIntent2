package com.dgioto.criminalintent

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
class Crime(@PrimaryKey val id: UUID = UUID.randomUUID(),
            var title: String = "",
            var date: Date = Date(),
            var isSolved: Boolean = false,
            var suspect: String = "") {

    //Добавление свойства для получения имени файла
    val photoFileName
        get() = "IMG_$id.jpg"
}