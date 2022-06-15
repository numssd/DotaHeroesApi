package com.example.newdotahereos.ui

import com.example.newdotahereos.data.model.HeroesItem

interface Repository {

    suspend fun getHeroes(): Map<String, HeroesItem>
}