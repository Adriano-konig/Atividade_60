package br.com.zup.marvel.domain.usecase

import android.app.Application
import br.com.zup.marvel.data.dao.HeroeDatabase
import br.com.zup.marvel.domain.model.Marvel
import br.com.zup.marvel.domain.repository.HeroeRepository
import br.com.zup.marvel.ui.viewstate.ViewState

class HeroeUseCase(application: Application) {
    private val heroeDao = HeroeDatabase.getDatabase(application).heroeDao()
    private val heroeRepository = HeroeRepository(heroeDao)

    suspend fun getAllHeroes(): ViewState<List<Marvel>> {
        return try {
            val marvel = heroeRepository.getAllHeroes()
            ViewState.Success(marvel)
        } catch (ex: Exception) {
            ViewState.Error(Exception("Não foi possível carregar a lista de filmes!"))
        }
    }

    suspend fun insertHeroe(marvel: Marvel): ViewState<Marvel> {
        return try {
            heroeRepository.insertHeroe(marvel)
            ViewState.Success(marvel)
        } catch (ex: Exception) {
            ViewState.Error(Exception("Não foi possível cadastrar o filme!"))
        }
    }
}