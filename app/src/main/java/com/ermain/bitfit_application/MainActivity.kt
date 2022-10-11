package com.ermain.bitfit_application

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val activities = mutableListOf<BitFitActivity>()
    private lateinit var bitFitAdapter: BitFitAdapter
    private lateinit var bitFitActivity: EditText
    private lateinit var bitFitCalories: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bitFitAdapter = BitFitAdapter(mutableListOf())

        // Fetch the RecyclerView
        val bitFitRecyclerView: RecyclerView = findViewById(R.id.rvBitFit)
        bitFitRecyclerView.adapter = bitFitAdapter
        bitFitRecyclerView.layoutManager = LinearLayoutManager(this)

        val buttonAdd = findViewById<Button>(R.id.buttonSubmit)
        bitFitActivity = findViewById(R.id.editTextBitFitActivity)
        bitFitCalories = findViewById(R.id.editTextCalorieAmount)

        buttonAdd.setOnClickListener {
            val activityName = bitFitActivity.text.toString()
            val activityCalories = bitFitCalories.text.toString()

            val activity = BitFitActivity(activityName, activityCalories)
            bitFitAdapter.addBitFitActivity(activity)
            bitFitActivity.text.clear()
            bitFitCalories.text.clear()

            lifecycleScope.launch(IO) {
                (application as BitFitApplication).db.bitFitDao().getAll().collect() {
                    it.map { food ->
                        BitFitActivity(
                            food.activity,
                            food.calories
                        )
                    }
                }
                (application as BitFitApplication).db.bitFitDao().insert(
                    BitFitActivity(activityName, activityCalories)
                )
            }
        }


        lifecycleScope.launch {
            val activityList = AppDatabase.getInstance(this@MainActivity).bitFitDao().getAll()
            activityList.collect()
        }
    }
}