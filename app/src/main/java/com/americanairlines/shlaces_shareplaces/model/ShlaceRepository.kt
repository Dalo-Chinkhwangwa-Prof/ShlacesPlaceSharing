package com.americanairlines.shlaces_shareplaces.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.americanairlines.shlaces_shareplaces.model.data.Shlace
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

object ShlaceRepository {

    //val firebaseDatabase = Firebas
    private val shlacesLiveData: MutableLiveData<List<Shlace>> = MutableLiveData()
    //private val e

    val firebaseDatabase: FirebaseDatabase = FirebaseDatabase.getInstance()
    init {
        firebaseDatabase.setPersistenceEnabled(true)
    }


    fun getShlaces(): LiveData<List<Shlace>>{

        firebaseDatabase.reference.child("SHLACES")
            .addValueEventListener(object : ValueEventListener{
                override fun onCancelled(error: DatabaseError) {
                    Log.d("TAG_X", "Error ${error.message}")
                }

                override fun onDataChange(snapshot: DataSnapshot) {

                    val shlaceList = mutableListOf<Shlace>()

                    snapshot.children.forEach{
                        it.getValue(Shlace::class.java)?.let { shlace ->
                            shlaceList.add(shlace)
                        }
                    }
                    shlacesLiveData.value = shlaceList
                }
            })


        return shlacesLiveData
    }

    fun postShlace(shlace: Shlace){
        firebaseDatabase.reference.child("SHLACES").push().setValue(shlace)
        Log.d("TAG_X", "Shlace Posted!")
    }




}