package com.mayurjajoo.employees.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mayurjajoo.employees.EmployeeApplication
import com.mayurjajoo.employees.R
import com.mayurjajoo.employees.adapter.EmployeeDataAdapter
import com.mayurjajoo.employees.repository.Response
import com.mayurjajoo.employees.viewmodel.EmployeeListViewModel
import com.mayurjajoo.employees.viewmodel.EmployeeListViewModelFactory


/**
 * Responsible for displaying list of employees with their respective designations
 */
class EmployeeListActivity : AppCompatActivity() {
    private val TAG = EmployeeListActivity::class.java.simpleName

    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mEmployeeDataAdapter: EmployeeDataAdapter
    private lateinit var mEmployeeListViewModel: EmployeeListViewModel
    private lateinit var mSearchView: SearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate()")
        setContentView(R.layout.activity_employee_list)
        initialise()
        setClickListeners()
        initialiseSearchView()
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume()")
        observeListAndUpdateUI()
        observeFilteredListAndUpdate()
    }

    /**
     * Initializes search view
     */
    private fun initialiseSearchView() {
        mSearchView = findViewById(R.id.search_view)

        mSearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(searchText: String): Boolean {
                filterList(searchText)
                return false
            }
        })
    }

    /**
     * Filter list and update UI
     *
     * @param searchText : Text searched on text box
     */
    private fun filterList(searchText: String) {
        mEmployeeListViewModel.filterList(searchText)
    }

    /**
     * Observes changes in filtered list and updates
     */
    private fun observeFilteredListAndUpdate() {
        mEmployeeListViewModel.filteredEmployeeList.observe(this, {
            mEmployeeDataAdapter.updateList(it)
        })
    }

    /**
     * sets Click listeners
     */
    private fun setClickListeners() {
        mEmployeeDataAdapter.onCallClickListener = {
            openDefaultDiallerApp(it)
        }

        mEmployeeDataAdapter.onMessageClickListener = {
            openDefaultMessagingApp(it)
        }
    }

    /**
     * Opens default dialler app with number
     * @param contactNumber: Number to be entered on dialing app
     */
    private fun openDefaultDiallerApp(contactNumber: String) {
        Log.d(TAG, "Opening dialler with contact number:$contactNumber")
        val intent = Intent(Intent.ACTION_DIAL)
        intent.data = Uri.parse("tel:$contactNumber")
        startActivity(intent)
    }

    /**
     * Opens default Messaging app with number
     * @param contactNumber: Number to be entered on dialing app
     */
    private fun openDefaultMessagingApp(contactNumber: String) {
        Log.d(TAG, "Opening messenger with contact number:$contactNumber")
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse("sms:$contactNumber")
        startActivity(intent)
    }

    /**
     * Observes Live data and updates UI accordingly
     */
    private fun observeListAndUpdateUI() {
        Log.d(TAG, "observeListAndUpdateUI()")
        mEmployeeListViewModel.employeeList.observe(this, {
            when (it) {
                is Response.Success -> {
                    Log.d(TAG, "Received Data: ${it.data}")
                    it.data?.let { it1 ->
                        mEmployeeDataAdapter.updateList(it1)
                    }
                }

                is Response.Failure -> {
                    Toast.makeText(this, "Problem Loading Data", Toast.LENGTH_SHORT)
                        .show()
                    Log.d(TAG, "Failure Reason: ${it.errorMessage.toString()}")
                }

                is Response.Loading -> {
                    Log.d(TAG, "Loading Data")
                }
            }
        })
    }

    /**
     * Initialise required components
     */
    private fun initialise() {
        mEmployeeDataAdapter = EmployeeDataAdapter(this)
        mRecyclerView = findViewById(R.id.recycler_view)
        mRecyclerView.apply {
            adapter = mEmployeeDataAdapter
            layoutManager = LinearLayoutManager(this@EmployeeListActivity)
        }

        mSearchView = findViewById(R.id.search_view)

        //init view model
        mEmployeeListViewModel = ViewModelProvider(
            this,
            EmployeeListViewModelFactory(EmployeeApplication.repository)
        ).get(EmployeeListViewModel::class.java)
    }
}