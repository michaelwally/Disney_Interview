package com.wally.disneyinterview.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wally.disneyinterview.domain.GetComicUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getComicUseCase: GetComicUseCase
) : ViewModel() {

    private val _comicUiState = MutableLiveData<ComicUiState>(ComicUiState.Loading)
    val comicUiState: LiveData<ComicUiState> = _comicUiState

    fun refresh() {
        viewModelScope.launch {
            try {
                _comicUiState.postValue(ComicUiState.Success(getComicUseCase("291")))
            } catch(e: HttpException) {
                Log.d("TAG123", e.message())
                _comicUiState.postValue(ComicUiState.Error(e.message()))
            } catch (e: Exception) {
                e.printStackTrace()
                _comicUiState.postValue(ComicUiState.Error(e.toString()))
            }
        }
    }
}