package br.com.zup.marvel.data.dao

import android.graphics.Movie
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.com.zup.marvel.domain.model.Marvel

@Dao
interface HeroeDao {
    @Query("SELECT * FROM heroes ORDER BY nome ASC")
    fun getAllMovies(): List<Marvel>

    @Query("SELECT * FROM heroes WHERE nome =:titleMovie")
    fun getMovieTitle(titleMovie: String): Marvel

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertHeroe(movie: Marvel)
}