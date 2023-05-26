package com.abhilash.livedata.ui.theme.userdatabase

import androidx.room.Dao
import androidx.room.Insert

@Dao
interface EmployeeDao {
    @Insert
    suspend fun insert(employee: Employee)
}