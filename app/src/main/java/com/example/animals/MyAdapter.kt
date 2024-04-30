package com.example.animals

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView

enum class Continent(val intValue: Int) {
    AFRICA(0),
    EUROPE(1),
    ASIA(2),
    NORTH_AMERICA(3),
    SOUTH_AMERICA(4),
    AUSTRALIA(5),
    ANTARCTICA(6)
}

class MyAdapter(
    private val data : List<Animal>,
    private val listener : RecyclerViewEvent
) : RecyclerView.Adapter<MyAdapter.ItemViewHolder>() {
    inner class ItemViewHolder(view : View): RecyclerView.ViewHolder(view), View.OnClickListener{
        val name: TextView = view.findViewById(R.id.tvName)
        val continent : TextView = view.findViewById(R.id.tvContinent)

        init{
            view.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if(position != RecyclerView.NO_POSITION){
                listener.onItemClick(position)
            }
        }
    }


    override fun getItemViewType(position : Int) : Int {
        val animal : Animal = data[position]
        return getContinentFromString(animal.continent)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val inflatedView : View = LayoutInflater.from(parent.context)
            .inflate(getLayoutFromType(viewType),parent,false)
        return ItemViewHolder(inflatedView)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val animal : Animal = data[position]

        holder.name.text = animal.name
        holder.continent.text = animal.continent
    }
    private fun getContinentFromString(continentString: String): Int {
        return when (continentString) {
            "Africa" -> Continent.AFRICA.intValue
            "Europe" -> Continent.EUROPE.intValue
            "Asia" -> Continent.ASIA.intValue
            "North America" -> Continent.NORTH_AMERICA.intValue
            "South America" -> Continent.SOUTH_AMERICA.intValue
            "Australia" -> Continent.AUSTRALIA.intValue
            "Antarctica" -> Continent.ANTARCTICA.intValue
            else -> -1
        }
    }
    private fun getLayoutFromType(viewType : Int) : Int{
        return when(viewType){
            Continent.AFRICA.intValue -> R.layout.africa_row
            Continent.EUROPE.intValue -> R.layout.europe_row
            Continent.ASIA.intValue -> R.layout.asia_row
            Continent.NORTH_AMERICA.intValue -> R.layout.north_america_row
            Continent.SOUTH_AMERICA.intValue -> R.layout.south_america_row
            Continent.AUSTRALIA.intValue -> R.layout.australia_row
            Continent.ANTARCTICA.intValue -> R.layout.antarctica_row
            else -> -1
        }
    }
    interface RecyclerViewEvent{
        fun onItemClick(position:Int)
    }
}