package com.abhilash.livedata.ui.theme.userdatabase

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Employee (

   // @PrimaryKey
   // var recNo:String,
     var dutyNo:String,
     var performedOn:String,
     var dutyEarned:String,
    var collection:String="",
    var employeeName:String="",
    var wayBillNo:String="",
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
)
