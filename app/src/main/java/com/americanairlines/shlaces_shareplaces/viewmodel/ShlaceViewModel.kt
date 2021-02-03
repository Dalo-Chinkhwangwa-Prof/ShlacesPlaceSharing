package com.americanairlines.shlaces_shareplaces.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.americanairlines.shlaces_shareplaces.model.ShlaceRepository
import com.americanairlines.shlaces_shareplaces.model.data.Shlace

class ShlaceViewModel: ViewModel() {

    fun getShlaces(): LiveData<List<Shlace>> = ShlaceRepository.getShlaces()

    fun uploadShlace(shlace: Shlace){
        ShlaceRepository.postShlace(shlace)
    }

}