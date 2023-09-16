package com.today.nail.service.data.home.dto.availableTime

data class AvailableTime(
    val content: List<AvailableTimeData>,
    val pageable: String,
    val first: Boolean,
    val last: Boolean,
    val sort: sortData,
    val number: Int,
    val numberOfElement: Int,
    val size: Int,
    val empty: Boolean,
)
//
//"pageable": "INSTANCE",
//"first": true,
//"last": true,
//"sort": {
//    "unsorted": true,
//    "sorted": false,
//    "empty": true
//},
//"number": 0,
//"numberOfElements": 2,
//"size": 2,
//"empty": false