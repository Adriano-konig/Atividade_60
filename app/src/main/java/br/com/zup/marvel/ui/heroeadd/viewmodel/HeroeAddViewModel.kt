package br.com.zup.marvel.ui.heroeadd.viewmodel

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

class HeroeAddViewModel (application: Application) : AndroidViewModel(application){

    private val heroeUseCase = HeroeUseCase(application)
    val heroeAddState = MutableLiveData<ViewState<Marvel>>()

    fun insertHeroe(marvel: Marvel) {
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    heroeUseCase.insertHeroe(marvel)
                }
                heroeAddState.value = response
            } catch (ex: Exception) {
                heroeAddState.value =
                    ViewState.Error(Throwable("Não foi possível inserir o filme!"))
            }
        }
    }
}