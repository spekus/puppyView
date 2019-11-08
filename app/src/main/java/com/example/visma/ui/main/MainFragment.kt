package com.example.visma.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.visma.R
import com.example.visma.model.Dogs
import kotlinx.android.synthetic.main.main_fragment.view.*

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private val viewModel: MainViewModel by lazy {
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }
        ViewModelProviders.of(this, MainViewModel.Factory(activity.application))
            .get(MainViewModel::class.java)
    }

    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var adapter: DogRecyclerAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val view = inflater.inflate(R.layout.main_fragment, container, false)

        linearLayoutManager = LinearLayoutManager(this.context)
        view.recyclerView.layoutManager = linearLayoutManager
        adapter = DogRecyclerAdapter()
        view.recyclerView.adapter = adapter

        viewModel.dogs.observe(viewLifecycleOwner, Observer<Dogs> { dogs ->
            adapter.refreshList(dogs.urls)
        })

        return view
    }
}
