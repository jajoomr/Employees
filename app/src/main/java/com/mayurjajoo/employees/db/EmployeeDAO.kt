package com.mayurjajoo.employees.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mayurjajoo.employees.model.Hierarchy

/**
 * Data Access Object class for Employee Details
 *
 * Responsible for inserting and querying data from Room DB.
 */
@Dao
interface EmployeeDAO {

    @Query("SELECT * FROM hierarchy")
    suspend fun getEmployeeDetails(): List<Hierarchy>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEmployeeDetails(employees: List<Hierarchy>)
}