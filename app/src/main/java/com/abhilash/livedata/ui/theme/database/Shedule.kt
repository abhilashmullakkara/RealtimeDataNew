package com.abhilash.livedata.ui.theme.database

data class Shedule (
    var busType:String="",
    var tripNumber:String="",
    var startPlace:String="",
    var via:String="",
    var destinationPlace:String="",
    var departureTime:String="",
    var  arrivalTime:String="",
    var kilometer:String="",
    var depoNumber:String="",
    var scheduleNo:String=""
        ) {
    companion object {
        val tripNumber: String=""
        val scheduleNo: String=""
        val depoNumber: String=""
        val kilometer: String=""
        val arrivalTime: String=""
        val departureTime: String=""
        val destinationPlace: String=""
        val via: String=""
        val startPlace: String=""
        val bustype:String=""
    }
}
