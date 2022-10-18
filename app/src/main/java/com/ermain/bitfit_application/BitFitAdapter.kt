package com.ermain.bitfit_application

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class BitFitAdapter(private val context: Context,
                    private val activityList: MutableList<BitFitActivity>)
    : RecyclerView.Adapter<BitFitAdapter.ViewHolder>() {


        inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            var bitFitActivityName: TextView
            var bitFitCalorieAmount: TextView
            init {
                bitFitActivityName = view.findViewById(R.id.etBitFitActivity)
                bitFitCalorieAmount = view.findViewById(R.id.textViewAmount)
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val fitView =
            LayoutInflater.from(context).inflate(R.layout.bitfit_item, parent, false)

        return ViewHolder(fitView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val fitActivity: BitFitActivity = activityList[position]

        holder.bitFitActivityName.text = fitActivity.activity
        holder.bitFitCalorieAmount.text = fitActivity.calories
    }

    override fun getItemCount(): Int {
        return activityList.size
    }

    fun addBitFitActivity(bitFit: BitFitActivity): Unit {
        activityList.add(bitFit)
        notifyItemInserted(activityList.size-1)
    }

    fun setBitFitData(data: List<BitFitActivity>): Unit {
        activityList.apply {
            clear()
            addAll(data)
        }
    }
}