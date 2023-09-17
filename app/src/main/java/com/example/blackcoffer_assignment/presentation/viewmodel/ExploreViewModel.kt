package com.example.blackcoffer_assignment.presentation.viewmodel

import androidx.lifecycle.VIEW_MODEL_STORE_OWNER_KEY
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.blackcoffer_assignment.data.entity.PersonData
import com.example.blackcoffer_assignment.data.PersonsDatabase
import com.example.blackcoffer_assignment.data.entity.BusinessData
import com.example.blackcoffer_assignment.data.entity.MerchantData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExploreViewModel @Inject constructor(
    private val personsDatabase: PersonsDatabase
) : ViewModel() {


    private val _personalCollection = MutableStateFlow(emptyList<PersonData>())
    val personalCollection = _personalCollection.asStateFlow()

    private val _merchantCollection = MutableStateFlow(emptyList<MerchantData>())
    val merchantCollection = _merchantCollection.asStateFlow()

    private val _businessCollection = MutableStateFlow(emptyList<BusinessData>())
    val businessCollection = _businessCollection.asStateFlow()


    private val _isLoading = MutableStateFlow(true)
    val isLoading = _isLoading.asStateFlow()
    private val _isLoading2 = MutableStateFlow(true)
    val isLoading2 = _isLoading2.asStateFlow()
    private val _isLoading3 = MutableStateFlow(true)
    val isLoading3 = _isLoading3.asStateFlow()


    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()
    private val _searchText2 = MutableStateFlow("")
    val searchText2 = _searchText2.asStateFlow()
    private val _searchText3 = MutableStateFlow("")
    val searchText3 = _searchText3.asStateFlow()


    private val _currentTab = MutableStateFlow(0)
    val currentTab = _currentTab.asStateFlow()

    @OptIn(FlowPreview::class)
    val filterItems = searchText
        .onEach {

        }.debounce(100)
        .combine(
            _personalCollection
        ) { text, listItems ->
            if (text.isBlank()) {
                _personalCollection.value
            } else {
                listItems.filter {
                    it.doesMatchQuery(text)
                }
            }

        }.stateIn(
            viewModelScope,
            SharingStarted.Lazily,
            _personalCollection.value
        )


    val filterItemsBusiness = searchText2
        .onEach {

        }.debounce(100)
        .combine(
            _businessCollection
        ) { text, listItems ->
            if (text.isBlank()) {
                _businessCollection.value
            } else {
                listItems.filter {
                    it.doesMatchQueryBus(text)
                }
            }

        }.stateIn(
            viewModelScope,
            SharingStarted.Lazily,
            _businessCollection.value
        )


    @OptIn(FlowPreview::class)
    val filterItemsMerchant = searchText3
        .onEach {

        }.debounce(100)
        .combine(
            _merchantCollection
        ) { text, listItems ->
            if (text.isBlank()) {
                _merchantCollection.value
            } else {
                listItems.filter {
                    it.doesMatchQueryMerch(text)
                }
            }

        }.stateIn(
            viewModelScope,
            SharingStarted.Lazily,
            _merchantCollection.value
        )


    init {

        viewModelScope.launch(Dispatchers.IO) {

            _personalCollection.tryEmit(personsDatabase.getPersonalCollection())
            _isLoading.tryEmit(false)
        }


    }

    fun getMerchantCollections() {
        viewModelScope.launch(Dispatchers.IO) {

            _merchantCollection.tryEmit(personsDatabase.getMerchantCollection())

            _isLoading3.tryEmit(false)
        }

    }

    fun getBusinessCollections() {
        viewModelScope.launch(Dispatchers.IO) {
            _businessCollection.tryEmit(personsDatabase.getBusinessCollection())
            _isLoading2.tryEmit(false)
        }
    }

    fun setSearchTextPersonal(
        string: String
    ) {
        _searchText.tryEmit(string)


    }

    fun setSearchTextBusiness(
        string: String
    ) {
        _searchText2.tryEmit(string)


    }

    fun setSearchTextMerchant(
        string: String
    ) {
        _searchText3.tryEmit(string)


    }

    fun updateTabIndex(tab: Int) {
        _currentTab.tryEmit(tab)
    }


}