package com.abhilash.livedata.ui.theme.userdatabase

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface EmployeeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(employee: Employee)
    @Query ("select * from  Employee  order by performedOn" )
    suspend fun display():List<Employee>
    @Query("delete  from Employee where id=:recNo")
    suspend fun delete(recNo:Int)
    @Query("DELETE FROM Employee")
    suspend fun deleteAll()
    @Query("DELETE FROM sqlite_sequence WHERE name='Employee'")
    suspend fun resetSequence()

}