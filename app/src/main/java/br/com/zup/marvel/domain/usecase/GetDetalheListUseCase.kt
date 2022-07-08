package br.com.zup.marvel.domain.usecase

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.zup.marvel.MARTIN_SCORSESE
import br.com.zup.marvel.MARTIN_SCORSESE_INFO
import br.com.zup.marvel.TARANTINO
import br.com.zup.marvel.TARANTINO_INFO
import br.com.zup.marvel.domain.model.Director
import br.com.zup.marvel.ui.viewstate.ViewState

class GetDetalheListUseCase {
    fun getDirectorList(): LiveData<ViewState<List<Director>>> {
        val response = MutableLiveData<ViewState<List<Director>>>()
        val listDirector = createDirectorList()

        if (listDirector.isNotEmpty()) {
            response.value = ViewState.Success(listDirector)
        } else {
            response.value =
                ViewState.Error(Exception("Não foi possível carregar a lista de diretores!"))
        }
        return response
    }

    private fun createDirectorList() = mutableListOf(
        Director(
            TARANTINO,
            //TARANTINO_INFO
        ),
        Director(
            MARTIN_SCORSESE,
            //MARTIN_SCORSESE_INFO
        )
    )
}