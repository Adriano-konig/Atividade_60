package br.com.zup.marvel.ui.heroedetail.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.zup.marvel.domain.model.Director
import br.com.zup.marvel.domain.usecase.GetDetalheListUseCase
import br.com.zup.marvel.ui.viewstate.ViewState

class HeroeDetailViewModel: ViewModel() {

    private val directorListUseCase = GetDetalheListUseCase()
    val directorListState = MutableLiveData<ViewState<List<Director>>>()

    fun getMovieList() {
        try {
            directorListState.value = directorListUseCase.getDirectorList().value
        } catch (ex: Exception) {
            directorListState.value =
                ViewState.Error(Throwable("Tivemos um problema! Tente mais tarde."))
        }
    }
}