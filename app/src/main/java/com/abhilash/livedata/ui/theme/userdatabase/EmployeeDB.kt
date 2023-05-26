package com.abhilash.livedata.ui.theme.userdatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Employee::class], version = 1)
abstract class EmployeeDB:RoomDatabase (){
    abstract fun getEmployeeDao():EmployeeDao
    companion object {
        var INSTANCE:EmployeeDB?=null
        fun getInstance(context: Context):EmployeeDB{
        if (INSTANCE==null){
            INSTANCE=Room.databaseBuilder(context.applicationContext,EmployeeDB::class.java,"Employeedb.db").build()
        }
         return INSTANCE!!
        }
    }
}
