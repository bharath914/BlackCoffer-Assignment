package com.example.blackcoffer_assignment.data.entity


data class MerchantData(
    val name: String = "",
    val city: String = "",

    val distance: String = "",

    val about: String = "",
    val phoneNumber: String = "",

    val imageUrl: String = "",
) {
    fun doesMatchQueryMerch(query: String): Boolean {
        val matchingComb = listOf(
            name,

            city,


            )
        return matchingComb.any {
            it.contains(query.trim(), true)
        }
    }
}