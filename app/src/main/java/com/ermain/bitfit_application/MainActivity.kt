package com.ermain.bitfit_application

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.ermain.bitfit_application.databinding.ActivityMainBinding
import com.ermain.bitfit_application.fragment.ExerciseListFragment
import com.ermain.bitfit_application.fragment.StatisticsViewFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(ExerciseListFragment())

        binding.bnSwitch?.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.nav_exercises -> replaceFragment(ExerciseListFragment())
                R.id.nav_stats -> replaceFragment(StatisticsViewFragment())
                else -> false
            }
            true
        }
    }

    private fun replaceFragment(exerciseListFragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.flStagingArea, exerciseListFragment)
        fragmentTransaction.commit()
    }
}