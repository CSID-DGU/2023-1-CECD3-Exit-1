package com.cecd.exitmed.domain.type

data class DrawerDetail(
    val doseTime: List<String>,
    val comment: String,
    val dosage: String,
    val finalDoseDate: String,
    val imageLink: String,
    val pillName: String
)
