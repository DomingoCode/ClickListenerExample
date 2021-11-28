package com.example.clicklisteneractivity.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.clicklisteneractivity.R
import com.example.clicklisteneractivity.model.State

class StateListRVAdapter: ListAdapter<State, StateListRVAdapter.StateViewHolder>(DiffCallback()) {
    var states = ArrayList<State>()
    
    fun setUpdatedList(newStates: ArrayList<State>) {
        this.states = newStates
        notifyDataSetChanged()
    }
    
    inner class StateViewHolder(
        itemView: View,
    ) :
        RecyclerView.ViewHolder(itemView) {
        private val stateName: TextView = itemView.findViewById(R.id.stateName)
        
        fun bind(state: State) {
            stateName.text = state.state
        }
    }
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StateViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.state_list_item, parent, false)
        return StateViewHolder(view)
    }
    
   
    override fun onBindViewHolder(holder: StateViewHolder, position: Int) {
        holder.bind(states[position])
        holder.itemView.setOnClickListener(object: View.OnClickListener {
            override fun onClick(v: View?) {
                onClickListener?.onItemClick(states[position])
            }
        })
    }
    
    override fun getItemCount(): Int = states.size
    
    //notify activity about click event via interface [MyClickListener]
    private var onClickListener: MyClickListener? = null
    fun setMyOnClickListener(listener: MyClickListener) {
        this.onClickListener = listener
    }
    interface MyClickListener {
        fun onItemClick(item: State)
    }
    
    class DiffCallback : DiffUtil.ItemCallback<State>() {
        override fun areItemsTheSame(
            oldItem: State,
            newItem: State
        ): Boolean {
            return oldItem.state == newItem.state
        }
        
        override fun areContentsTheSame(
            oldItem: State,
            newItem: State
        ): Boolean {
            return oldItem == newItem
        }
    }
}