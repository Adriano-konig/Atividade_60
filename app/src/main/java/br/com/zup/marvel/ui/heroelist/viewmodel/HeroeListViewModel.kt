package br.com.zup.marvel.ui.heroelist.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import br.com.zup.marvel.domain.model.Marvel
import br.com.zup.marvel.domain.usecase.HeroeUseCase
import br.com.zup.marvel.ui.viewstate.ViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HeroeListViewModel (application: Application) : AndroidViewModel(application){

    private val heroeUseCase = HeroeUseCase(application)
    val movieListState = MutableLiveData<ViewState<List<Marvel>>>()

    fun getAllMovies() {
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    heroeUseCase.getAllHeroes()
                }
                movieListState.value = response
            } catch (ex: Exception) {
                movieListState.value =
                    ViewState.Error(Throwable("Não foi possível carregar a lista!"))
            }
        }
    }
}