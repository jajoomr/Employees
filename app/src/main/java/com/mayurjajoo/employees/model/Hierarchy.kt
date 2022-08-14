package com.mayurjajoo.employees.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "hierarchy")
data class Hierarchy(
    @PrimaryKey(autoGenerate = true)
    val contactId: Int,
    val contactName: String,
    val contactNumber: String,
    val designationName: String
)