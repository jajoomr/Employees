package com.mayurjajoo.employees.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mayurjajoo.employees.model.Hierarchy
import com.mayurjajoo.employees.repository.EmployeeRepository
import com.mayurjajoo.employees.repository.Response
import kotlinx.coroutines.launch

class EmployeeListViewModel(private val repository: EmployeeRepository) : ViewModel() {

    val employeeList: LiveData<Response<List<Hierarchy>>>
        get() = repository.employeeList

    private val mFilteredEmployeeListLiveData = MutableLiveData<List<Hierarchy>>()
    val filteredEmployeeList: LiveData<List<Hierarchy>>
        get() = mFilteredEmployeeListLiveData

    init {
        getEmployeeData()
    }

    private fun getEmployeeData() {
        viewModelScope.launch {
            repository.getEmployeeListData()
        }
    }

    /**
     * Filters list based on search text
     *
     * @param searchText: Text searched on search bar
     */
    fun filterList(searchText: String) {
        val filteredList: MutableList<Hierarchy> = ArrayList()
        for (employee in employeeList.value?.data!!) {
            if (employee.contactName.toLowerCase().contains(searchText.toLowerCase())) {
                filteredList.add(employee)
            }
        }
        mFilteredEmployeeListLiveData.postValue(filteredList)
    }
}