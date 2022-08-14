package com.mayurjajoo.employees.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mayurjajoo.employees.model.Hierarchy
import com.mayurjajoo.employees.utils.Constants

/**
 * Employee Database class holds instance of Room Database
 */
@Database(entities = [Hierarchy::class] , version = Constants.DATABASE_VERSION)
abstract class EmployeeDatabase: RoomDatabase() {

    abstract fun employeeDao(): EmployeeDAO

    companion object {
        @Volatile
        private var INSTANCE: EmployeeDatabase? = null

        fun getDatabase(context: Context): EmployeeDatabase {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = Room.databaseBuilder(
                        context,
                        EmployeeDatabase::class.java,
                        "employeeDB"
                    )
                        .build()
                }
            }
            return INSTANCE!!
        }
    }
}