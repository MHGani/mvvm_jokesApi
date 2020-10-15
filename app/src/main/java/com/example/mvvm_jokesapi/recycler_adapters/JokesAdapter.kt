package com.example.mvvm_jokesapi.recycler_adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvm_jokesapi.R
import com.example.mvvm_jokesapi.database.DatabaseJokes
import com.example.mvvm_jokesapi.databinding.RowJokesBinding



class JokesAdapter(val callback: JokeClick): RecyclerView.Adapter<JokesViewHolder>(){

    var results: List<DatabaseJokes> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JokesViewHolder {
        val withDataBinding: RowJokesBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            JokesViewHolder.LAYOUT,
            parent,
            false
        )
        return JokesViewHolder(withDataBinding)
    }

    override fun getItemCount() = results.size

    override fun onBindViewHolder(holder: JokesViewHolder, position: Int) {
        holder.viewDataBinding.also {
            it.results = results[position]
            //  To handle onclick : In Databinding
            it.resultsCallback = callback

        }
    }
}

class JokesViewHolder(val viewDataBinding: RowJokesBinding) :
    RecyclerView.ViewHolder(viewDataBinding.root) {
    companion object {
        @LayoutRes
        val LAYOUT = R.layout.row_jokes
    }
}

class JokeClick(val block: (DatabaseJokes) -> Unit) {
    fun onClick(joke: DatabaseJokes) = block(joke)
}