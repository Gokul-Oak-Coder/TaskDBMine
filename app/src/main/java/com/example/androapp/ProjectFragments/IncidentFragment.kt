package com.nttdata.androapp.ProjectFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.nttdata.androapp.R
import com.nttdata.androapp.RecyclerViewPackage.DataObject
import com.nttdata.androapp.RecyclerViewPackage.WorkAdapter
import com.nttdata.androapp.RoomDatabase.WorkDatabase
import kotlinx.android.synthetic.main.fragment_development.*
import kotlinx.android.synthetic.main.fragment_incident.*


class IncidentFragment : Fragment() {
    private lateinit var dataBase: WorkDatabase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view : View =  inflater.inflate(R.layout.fragment_incident, container, false)



        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dataBase = Room.databaseBuilder(
            view.context, WorkDatabase::class.java,"Work_Data"
        ).build()


            recyclerViewWork_Incidents.adapter = WorkAdapter(DataObject.getAllData())
            recyclerViewWork_Incidents.layoutManager = LinearLayoutManager(context)

    }

    companion object {
       @JvmStatic
        fun newInstance() =
            IncidentFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}