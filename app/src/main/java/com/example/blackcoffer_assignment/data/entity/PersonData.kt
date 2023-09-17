package com.example.blackcoffer_assignment.data.entity

data class PersonData(
    val name: String = "",
    val city: String = "",
    val profession: String = "",
    val distance: String = "",
    val hobbies: String = "",
    val about: String = "",
    val description: String = "",
    val imageUrl: String = "",

    ) {
    fun doesMatchQuery(query: String): Boolean {
        val matchingComb = listOf(
            "$name",
            "$city",
            "$profession",
            "$hobbies"
        )
        return matchingComb.any {
            it.contains(query.trim(), true)
        }
    }
}