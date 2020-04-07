package com.example.github.feature.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.github.core.functional.Result
import com.example.github.core.functional.SingleLiveData
import com.example.github.core.interactor.None
import com.example.github.domain.model.Repository
import com.example.github.domain.usecase.GetAllRepositoriesUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class ListFragmentViewModel
@Inject constructor(
    private val getAllRepositoriesUseCase: GetAllRepositoriesUseCase
) : ViewModel() {

    private val _listFragmentViewEvent =
        SingleLiveData<ListFragmentViewEvent>()
    val listFragmentViewEvent: LiveData<ListFragmentViewEvent>
        get() = _listFragmentViewEvent

    private val _listItem = MutableLiveData<List<Repository>>()
    val listItem: LiveData<List<Repository>>
        get() = _listItem

    fun fetchData() {
        viewModelScope.launch {
            when (val result = getAllRepositoriesUseCase(None())) {
                is Result.Success -> _listItem.value = result.data
                is Result.Error -> _listFragmentViewEvent.value =
                    ListFragmentViewEvent.ShowErrorMessage(result.exception.message)
            }
        }
    }

}

sealed class ListFragmentViewEvent {
    data class ShowErrorMessage(val message: String?) : ListFragmentViewEvent()
}

