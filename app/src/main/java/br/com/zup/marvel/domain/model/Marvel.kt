package br.com.zup.marvel.domain.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "Heroes")
data class Marvel(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "cod_heroe")
    var codMovie: Long = 1,

    @ColumnInfo(name = "nome")
    var nome: String,

    @ColumnInfo(name = "detalhe")
    var detalhe: Director,

    var image: Int = 0,

) : Parcelable