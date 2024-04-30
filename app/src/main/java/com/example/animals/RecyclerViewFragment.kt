package com.example.animals

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.serialization.json.Json

class RecyclerViewFragment : Fragment(), MyAdapter.RecyclerViewEvent {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MyAdapter

    companion object {
        // Define a static variable to hold the shuffled data
        private var isShuffled = false
        private lateinit var data: List<Animal>

        // Initialize the data with shuffled animal data
        fun initAnimalData(context : Context) {
            if (!isShuffled) {
                data = getAnimalDataJson(context)
                data = data.shuffled()
                isShuffled = true
            }
        }

        // Access the data from outside the class
        fun getAnimalData(): List<Animal> {
            return data
        }
        private fun getAnimalDataJson(context : Context) : List<Animal>{
            val jsonString : String = readJsonFile(R.raw.animals, context)
            return Json.decodeFromString(jsonString)
        }
        private fun readJsonFile(resourceId: Int, context : Context): String {
            val inputStream = context.resources.openRawResource(resourceId)
            val bufferedReader = inputStream.bufferedReader()
            val jsonString = bufferedReader.use { it.readText() }
            inputStream.close()
            return jsonString
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_recicler_view, container, false)
        // Inflate the layout for this fragment
        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        initAnimalData(requireContext())

        adapter = MyAdapter(getAnimalData(), this)
        recyclerView.adapter = adapter
        return view
    }





    override fun onItemClick(position: Int) {
        val animal = getAnimalData()[position]
        val bundle : Bundle = bundleOf(
            "name" to animal.name,
            "continent" to animal.continent
        )

        findNavController().navigate(R.id.action_recyclerViewFragment_to_detailsFragment,bundle)
    }

}