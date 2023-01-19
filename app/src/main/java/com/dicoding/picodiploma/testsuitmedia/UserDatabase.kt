package com.dicoding.picodiploma.testsuitmedia

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.dicoding.picodiploma.testsuitmedia.dataclass.ConverterList
import com.dicoding.picodiploma.testsuitmedia.dataclass.GetUserResponse

@Database(
    entities = [GetUserResponse::class],
    version = 1,
    exportSchema = false
)

@TypeConverters(ConverterList::class)
abstract class UserDatabase : RoomDatabase() {

    companion object {
        @Volatile
        private var INSTANCE: UserDatabase? = null

        @JvmStatic
        fun getDatabase(context: Context): UserDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    UserDatabase::class.java, "user_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { INSTANCE = it }
            }
        }
    }
}