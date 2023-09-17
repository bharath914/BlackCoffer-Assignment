package com.example.blackcoffer_assignment.data.entity

data class BusinessData(
    val name: String = "",
    val city: String = "",
    val profession: String = "",
    val distance: String = "",

    val about: String = "",
    val experience: Int = 0,
    val phoneNumber: String = "",
    val imageUrl: String = "",
) {
    fun doesMatchQueryBus(query: String): Boolean {
        val matchingComb = listOf(
            "$name",
            city,
            profession,


            )
        return matchingComb.any {
            it.contains(query.trim(), true)
        }
    }
}
