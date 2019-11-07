package com.example.visma.ui.main

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.visma.R
import com.example.visma.model.Dogs
import kotlinx.android.synthetic.main.main_fragment.*
import kotlinx.android.synthetic.main.main_fragment.view.*

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var adapter: DogRecyclerAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.main_fragment, container, false)
        linearLayoutManager = LinearLayoutManager(this.context)
        view.recyclerView.layoutManager = linearLayoutManager
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }
        viewModel = ViewModelProviders.of(this, MainViewModel.Factory(activity.application))
            .get(MainViewModel::class.java)

        viewModel.dogs.observe(this, Observer<Dogs> { dogs ->
            adapter = DogRecyclerAdapter(dogs.urls)
            if(view!!.recyclerView.adapter == null){
                view!!.recyclerView.adapter = adapter
            }
        })
    }
}
