package com.mayurjajoo.employees.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mayurjajoo.employees.repository.EmployeeRepository

class EmployeeListViewModelFactory(private val repository: EmployeeRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return EmployeeListViewModel(repository) as T
    }
}