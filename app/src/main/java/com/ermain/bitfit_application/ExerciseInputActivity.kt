package com.ermain.bitfit_application

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import com.ermain.bitfit_application.R
import kotlinx.coroutines.launch

class ExerciseInputActivity : AppCompatActivity() {
    private lateinit var foodNameText: EditText
    private lateinit var foodCalories: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise_input)

        foodNameText = findViewById(R.id.editTextFoodName)
        foodCalories = findViewById(R.id.editTextAmountOfCalories)
        val recordFoodButton = findViewById<Button>(R.id.buttonRecordFood)
        recordFoodButton.setOnClickListener {
            Log.i("ExerciseInputActivity", "Save food button works")
            val foodText = foodNameText.text.toString()
            val calorieText = foodCalories.text.toString()
            Log.i("ExerciseInputActivity", "Food: ${foodText}, Calorie: $calorieText")
        }
    }
}