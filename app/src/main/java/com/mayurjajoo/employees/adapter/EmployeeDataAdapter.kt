package com.mayurjajoo.employees.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mayurjajoo.employees.R
import com.mayurjajoo.employees.model.Hierarchy

/**
 * Adapter class responsible for creating views to display Employee List
 * and binding data to it.
 */
class EmployeeDataAdapter(
    private val mContext: Context
) : RecyclerView.Adapter<EmployeeDataAdapter.EmployeeDataViewHolder>() {

    var onCallClickListener: ((String) -> Unit)? = null
    var onMessageClickListener: ((String) -> Unit)? = null
    private var mEmployeeList: List<Hierarchy> = ArrayList()

    inner class EmployeeDataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val employeeName: TextView = itemView.findViewById(R.id.tv_employee_name)
        val employeeDesignation: TextView = itemView.findViewById(R.id.tv_employee_designation)
        val callButton: ImageButton = itemView.findViewById(R.id.iv_call)
        val messageButton: ImageButton = itemView.findViewById(R.id.iv_message)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeDataViewHolder {
        val view = LayoutInflater.from(mContext)
            .inflate(R.layout.employee_item, parent, false)
        return EmployeeDataViewHolder(view)
    }

    /**
     * Updates list
     */
    fun updateList(employeeList: List<Hierarchy>) {
        mEmployeeList = employeeList
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: EmployeeDataViewHolder, position: Int) {
        val hierarchy : Hierarchy = mEmployeeList[position]
        val employeeNumber = hierarchy.contactNumber
        holder.employeeName.text = hierarchy.contactName
        holder.employeeDesignation.text = hierarchy.designationName

        holder.callButton.setOnClickListener() {
            onCallClickListener?.invoke(employeeNumber)
        }

        holder.messageButton.setOnClickListener() {
            onMessageClickListener?.invoke(employeeNumber)
        }
    }

    override fun getItemCount(): Int {
        return mEmployeeList.size
    }
}