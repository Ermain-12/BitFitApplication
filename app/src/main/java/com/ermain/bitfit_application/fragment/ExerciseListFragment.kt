package com.ermain.bitfit_application.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ermain.bitfit_application.*
import com.ermain.bitfit_application.ExerciseInputActivity
import kotlinx.coroutines.launch

class ExerciseListFragment : Fragment() {
    private val activities = mutableListOf<BitFitActivity>()
    private lateinit var bitFitAdapter: BitFitAdapter
    private lateinit var bitFitActivity: EditText
    private lateinit var bitFitCalories: EditText
    private lateinit var exercisesRecyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_exercise_list, container, false)

        // Configure the adapter
        val layoutManager = LinearLayoutManager(context)
        exercisesRecyclerView = view.findViewById(R.id.rvBitFit)
        exercisesRecyclerView.layoutManager = layoutManager
        exercisesRecyclerView.setHasFixedSize(true)
        bitFitAdapter = BitFitAdapter(view.context, activities)
        exercisesRecyclerView.adapter = bitFitAdapter

        val addActivityButton = view.findViewById<Button>(R.id.add_activity_button)
        addActivityButton.setOnClickListener {
            val intent: Intent = Intent(this.context, ExerciseInputActivity::class.java)
            startActivity(intent)
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            (activity?.application as BitFitApplication).db.bitFitDao().getAll().collect() {
                it.map { food ->
                    BitFitActivity(
                        food.activity,
                        food.calories
                    )
                }.also { mappedList ->
                    activities.clear()
                    activities.addAll(mappedList)
                    bitFitAdapter.notifyDataSetChanged()
                }
            }
        }
    }

    private fun displayExercises() {
    }
}