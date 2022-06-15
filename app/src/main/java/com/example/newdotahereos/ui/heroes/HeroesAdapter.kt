package com.example.newdotahereos.ui.heroes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.newdotahereos.R
import com.example.newdotahereos.data.api.OpenDotaAPI.URL_IMG
import com.example.newdotahereos.data.model.HeroesItem


interface OnHeroClickListener {
    fun onClick(item: HeroesItem)
}

class HeroesAdapter(
    private val onHeroClickListener: OnHeroClickListener,
) : RecyclerView.Adapter<HeroesAdapter.HeroesViewHolder>() {

    private val list = ArrayList<HeroesItem>()

    fun setData(map: Map<String, HeroesItem>) {
        list.clear()
        list.addAll(map.values.sortedBy { it.id })
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroesViewHolder {
        val item = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_view_items, parent, false)
        return HeroesViewHolder(item)
    }

    override fun onBindViewHolder(holder: HeroesViewHolder, position: Int) {
        val hero = list[position]
        holder.bindData(hero)
        holder.itemView.setOnClickListener { onHeroClickListener.onClick(hero) }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class HeroesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val heroImg = itemView.findViewById<ImageView>(R.id.imageViewHero)

        private val heroName = itemView.findViewById<TextView>(R.id.textViewNameHero)

        fun bindData(item: HeroesItem) {
            heroName.text = item.name
            heroImg.load(URL_IMG + item.img)


        }

    }
}