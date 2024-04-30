package com.example.animals

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout

private const val ARG_PARAM1 = "name"
private const val ARG_PARAM2 = "continent"

class DetailsFragment : Fragment() {
    private var name: String? = null
    private var continent: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            name = it.getString(ARG_PARAM1)
            continent = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_details, container, false)
        val tvName = view.findViewById<TextView>(R.id.tvName)
        val tvContinent = view.findViewById<TextView>(R.id.tvContinent)

        tvName.text = name
        tvContinent.text = continent

        val textColor = getTextColor(continent ?: "")
        tvName.setTextColor(textColor)
        tvContinent.setTextColor(textColor)

        val backgroundColor = getBkColor(continent ?: "")
        view.findViewById<ConstraintLayout>(R.id.constraint_layout).setBackgroundResource(backgroundColor)

        return view
    }
    private fun getBkColor(continent : String?): Int {
        return when (continent) {
            "Europe" -> R.color.europe
            "Africa" -> R.color.africa
            "Asia" -> R.color.asia
            "North America" -> R.color.north_america
            "South America" -> R.color.south_america
            "Australia" -> R.color.australia
            "Antarctica" -> R.color.antarctica
            else -> R.color.black // Default color
        }
    }
    private fun getTextColor(continent : String?) : Int{
        if(continent == "Africa" || continent == "South America")
            return Color.BLACK
        return Color.WHITE
    }

}