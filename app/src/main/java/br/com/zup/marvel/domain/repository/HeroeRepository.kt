package br.com.zup.marvel.domain.repository

import br.com.zup.marvel.data.dao.HeroeDao
import br.com.zup.marvel.domain.model.Marvel

class HeroeRepository(private val heroeDao: HeroeDao) {

    suspend fun getAllHeroes(): List<Marvel> = heroeDao.getAllMovies()

    suspend fun insertHeroe(marvel: Marvel){
        heroeDao.insertHeroe(marvel)
    }
}