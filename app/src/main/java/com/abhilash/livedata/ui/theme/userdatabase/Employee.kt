package com.abhilash.livedata.ui.theme.userdatabase

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import java.util.Date


@Entity
@TypeConverters(Employee.DateConverter::class)
data class Employee (
    var dutyNo:String,
    var performedOn: Date=Date(),
    var dutyEarned: String,
    var collection:String="",
    var employeeName:String="",
    var wayBillNo:String="",

   @PrimaryKey(autoGenerate = true) var id: Int = 0

) {
    class DateConverter {
        @TypeConverter
        fun fromDate(date: Date?): Long? {
            return date?.time
        }

        @TypeConverter
        fun toDate(timestamp: Long?): Date? {
            return timestamp?.let { Date(it) }
        }

    }
}
