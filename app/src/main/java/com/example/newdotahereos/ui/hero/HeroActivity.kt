package com.example.newdotahereos.ui.hero

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import coil.load
import com.example.newdotahereos.R
import com.example.newdotahereos.data.api.OpenDotaAPI
import com.example.newdotahereos.data.model.HeroesItem

class HeroActivity : AppCompatActivity() {
    companion object {
        const val HERO_ARG = "hero"
    }

    private val heroItem by lazy { intent.getSerializableExtra(HERO_ARG) as HeroesItem }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hero)
        supportActionBar?.hide()
        val img = findViewById<ImageView>(R.id.imageViewHero)
        val name = findViewById<TextView>(R.id.textViewNameHero)
        val attr = findViewById<TextView>(R.id.textViewPrimaryAttr)
        val attack = findViewById<TextView>(R.id.textViewTypeAttack)
        img.load(OpenDotaAPI.URL_IMG + heroItem.img)
        name.text = heroItem.name
        when (heroItem.primaryAttr) {
            "int" -> {
                attr.text = getString(R.string.intelligence_text)
            }
            "str" -> {
                attr.text = getString(R.string.strength_text)
            }
            else -> {
                attr.text = getString(R.string.agility_text)
            }
        }
        attack.text = heroItem.attackType

    }
}