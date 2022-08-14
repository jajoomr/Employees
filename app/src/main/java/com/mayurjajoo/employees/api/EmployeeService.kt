package com.mayurjajoo.employees.api

import com.mayurjajoo.employees.model.EmployeeRoot
import retrofit2.Response
import retrofit2.http.GET

/**
 * Responsible to fetch data from network
 */
interface EmployeeService {
    @GET("getMyList")
    suspend fun getEmployeeData() : Response<EmployeeRoot>
}