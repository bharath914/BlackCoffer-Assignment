package com.example.blackcoffer_assignment.data

import com.example.blackcoffer_assignment.data.entity.BusinessData
import com.example.blackcoffer_assignment.data.entity.MerchantData
import com.example.blackcoffer_assignment.data.entity.PersonData
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class PersonsDatabase {

    val firestore = FirebaseFirestore.getInstance()
    val personalCollection = firestore.collection(CollectionConst.personal)
    val businessCollection = firestore.collection(CollectionConst.business)
    val merchantCollection = firestore.collection(CollectionConst.merchant)


    suspend fun getMerchantCollection(): List<MerchantData> {
        return try {
            merchantCollection.get().await().toObjects(MerchantData::class.java)
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList<MerchantData>()
        }
    }

    suspend fun getBusinessCollection(): List<BusinessData> {
        return try {
            businessCollection.get().await().toObjects(BusinessData::class.java)
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList<BusinessData>()
        }
    }


    suspend fun getPersonalCollection(): List<PersonData> {
        return try {
            personalCollection.get().await().toObjects(PersonData::class.java)
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList<PersonData>()
        }
    }

}

object CollectionConst {
    const val personal = "Personal"
    const val business = "Business"
    const val merchant = "Merchant"
}