package com.americanairlines.shlaces_shareplaces.view.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.americanairlines.shlaces_shareplaces.R
import com.americanairlines.shlaces_shareplaces.view.adapter.ShlacesAdapter
import com.americanairlines.shlaces_shareplaces.viewmodel.ShlaceViewModel

class ShlacesFragment : Fragment() {

    private lateinit var shlacesRecyclerView: RecyclerView
    private val shlacesAdapter: ShlacesAdapter = ShlacesAdapter(listOf())
    private lateinit var addShlaceImageView: ImageView

    private val shlaceViewModel: ShlaceViewModel by viewModels()

    private val uploadFragment: UploadFragment = UploadFragment()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.shlaces_fragment_layout, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        shlacesRecyclerView = view.findViewById(R.id.shlaces_recyclerview)
        shlacesRecyclerView.adapter = shlacesAdapter
        addShlaceImageView = view.findViewById(R.id.new_shlace_image)
        addShlaceImageView.setOnClickListener {
            //TODO: Load fragment
            childFragmentManager
                .beginTransaction()
                .add(R.id.upload_frame, uploadFragment)
                .addToBackStack(uploadFragment.tag)
                .commit()
        }

        shlaceViewModel.getShlaces().observe(viewLifecycleOwner, Observer {
            shlacesAdapter.updateShlaces(it)
        })

    }

}











