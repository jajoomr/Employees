package com.mayurjajoo.employees

import android.app.Application
import com.mayurjajoo.employees.api.EmployeeService
import com.mayurjajoo.employees.api.RetrofitHelper
import com.mayurjajoo.employees.db.EmployeeDatabase
import com.mayurjajoo.employees.repository.EmployeeRepository

class EmployeeApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        initialise()
    }

    private fun initialise() {
        val employeeService = RetrofitHelper.getInstance().create(EmployeeService::class.java)
        val employeeDatabase = EmployeeDatabase.getDatabase(applicationContext)
        repository = EmployeeRepository(applicationContext,employeeService, employeeDatabase)
    }

    companion object {
        lateinit var repository: EmployeeRepository
    }

}