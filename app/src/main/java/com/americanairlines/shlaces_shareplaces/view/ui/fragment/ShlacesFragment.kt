package com.americanairlines.shlaces_shareplaces.view.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.americanairlines.shlaces_shareplaces.R
import com.americanairlines.shlaces_shareplaces.view.adapter.ShlacesAdapter

class ShlacesFragment: Fragment() {

    private lateinit var shlacesRecyclerView: RecyclerView
    private val shlacesAdapter: ShlacesAdapter = ShlacesAdapter(listOf())

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.shlaces_fragment_layout, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        shlacesRecyclerView = view.findViewById(R.id.shlaces_recyclerview)
        shlacesRecyclerView.adapter = shlacesAdapter
    }
}











