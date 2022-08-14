package com.mayurjajoo.employees.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mayurjajoo.employees.api.EmployeeService
import com.mayurjajoo.employees.db.EmployeeDatabase
import com.mayurjajoo.employees.model.Hierarchy
import com.mayurjajoo.employees.utils.NetworkUtils
import kotlin.Exception

/**
 * Repository responsible for fetching data.
 * Fetches data from API if internet is available
 * Else, fetches from Database
 */
class EmployeeRepository(
    private val mApplicationContext: Context,
    private val mEmployeeService: EmployeeService,
    private val mEmployeeDatabase: EmployeeDatabase
) {

    private val mEmployeeListLiveData = MutableLiveData<Response<List<Hierarchy>>>()

    val employeeList: LiveData<Response<List<Hierarchy>>>
        get() = mEmployeeListLiveData

    /**
     * Fetches Employee Data from API if internet is available,
     * Otherwise gets data from Database
     */
    suspend fun getEmployeeListData() {
        if (NetworkUtils.isInternetAvailable(mApplicationContext)) {
            //try to fetch data from api
            try {
                //get data from api
                val result = mEmployeeService.getEmployeeData()
                if (result?.body() != null) {
                    //update live data
                    mEmployeeListLiveData.postValue(
                        Response.Success(
                            result.body()!!
                                .dataObject[0]
                                .myHierarchy[0]
                                .heirarchyList
                        )
                    )
                    //store data in DB
                    mEmployeeDatabase.employeeDao().insertEmployeeDetails(
                        result.body()!!.dataObject[0]
                            .myHierarchy[0]
                            .heirarchyList
                    )
                }

            } catch (e: Exception) {
                //if exception occurs, respond with failure message
                mEmployeeListLiveData.postValue(Response.Failure(e.toString()))
            }
        } else {
            //fetch data from database
            try {
                //update live data
                mEmployeeListLiveData.postValue(
                    Response.Success(
                        mEmployeeDatabase.employeeDao().getEmployeeDetails()
                    )
                )
            } catch (e: Exception) {
                //if exception occurs, send failure response
                mEmployeeListLiveData.postValue(Response.Failure(e.toString()))
            }
        }
    }
}