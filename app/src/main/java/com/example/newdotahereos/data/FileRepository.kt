package com.example.newdotahereos.data

import android.content.Context
import android.util.Log
import com.example.newdotahereos.ui.Repository
import com.example.newdotahereos.data.model.HeroesItem
import com.squareup.moshi.JsonAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

@ExperimentalStdlibApi
class FileRepository(
    private val context: Context,
    private val adapter: JsonAdapter<Map<String, HeroesItem>>
) : Repository {

    suspend fun saveHeroes(hero: Map<String, HeroesItem>) {
        return withContext(Dispatchers.IO) {
            val file = getHeroFile()
            val json = adapter.toJson(hero)
            val fileOutputStream = FileOutputStream(file)
            val bufReader = fileOutputStream.bufferedWriter()
            bufReader.appendLine(json)
            bufReader.close()
        }
    }

    override suspend fun getHeroes(): Map<String, HeroesItem> {
        return withContext(Dispatchers.IO) {
            val file = getHeroFile()
            if (file.exists()) {
                val fileInputStream = FileInputStream(file)
                val json = fileInputStream.bufferedReader().readLine()
                fileInputStream.close()
                val hero = adapter.fromJson(json)
                hero ?: throw IllegalStateException("Не удалось распарсить героя ")
            } else {
                throw IllegalStateException("Нет кеша для героя")
            }
        }
    }

    private fun getHeroFile(): File {
        return File(context.filesDir, "Hero")
    }
}