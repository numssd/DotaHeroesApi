package com.example.newdotahereos.data

import com.example.newdotahereos.ui.Repository
import com.example.newdotahereos.data.model.HeroesItem

@ExperimentalStdlibApi
class CompositeRepository(
    private val networkRepository: NetworkRepository,
    private val fileRepository: FileRepository,
) : Repository {

    override suspend fun getHeroes(): Map<String, HeroesItem> {
        return try {
            val heroes = fileRepository.getHeroes()
            heroes
        } catch (e: IllegalStateException) {
            val heroes = networkRepository.getHeroes()
            fileRepository.saveHeroes(heroes)
            heroes
        }
    }
}