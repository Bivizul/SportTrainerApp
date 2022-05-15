package com.bivizul.sporttrainerapp.domain.user

data class User(
    var name: String = "",
    var height: Int = 0,
    var weight: Int = 0,
    var progress: Int = 0,
    var distance: Int = 0,
    var squats: Int = 0,
    var date: String = "",
)