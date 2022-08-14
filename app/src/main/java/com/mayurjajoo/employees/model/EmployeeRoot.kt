package com.mayurjajoo.employees.model

data class EmployeeRoot(
    val dataObject: List<DataObject>,
    val message: String,
    val status: Boolean,
    val statusCode: Int
)