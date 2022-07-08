package br.com.zup.marvel.data.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import br.com.zup.marvel.domain.model.Marvel

@Database(entities = [Marvel::class], version = 1)
@TypeConverters(Converters::class)
abstract class HeroeDatabase : RoomDatabase() {
    abstract fun heroeDao() : HeroeDao

    companion object {
        @Volatile
        private var INSTANCE: HeroeDatabase? = null

        fun getDatabase(context: Context): HeroeDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    HeroeDatabase::class.java,
                    "filme_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}