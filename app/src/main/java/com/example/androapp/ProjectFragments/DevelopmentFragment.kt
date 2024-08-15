package com.nttdata.androapp.ProjectFragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nttdata.androapp.NavigationPage
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.nttdata.androapp.NewTask
import com.nttdata.androapp.R
import com.nttdata.androapp.RecyclerViewPackage.CardInfo
import com.nttdata.androapp.RecyclerViewPackage.DataObject
import com.nttdata.androapp.RecyclerViewPackage.WorkAdapter
import com.nttdata.androapp.RoomDatabase.WorkDatabase
import kotlinx.android.synthetic.main.activity_new_task.*
import kotlinx.android.synthetic.main.fragment_android.*
import kotlinx.android.synthetic.main.fragment_development.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class DevelopmentFragment : Fragment() {
    private lateinit var dataBase: WorkDatabase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view : View =  inflater.inflate(R.layout.fragment_development, container, false)

        //RecycleView department


//        fab.setOnClickListener {
//            val intent = Intent(context,NewTask::class.java)
//            startActivity(intent)
//        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dataBase = Room.databaseBuilder(
            view.context,WorkDatabase::class.java,"Work_Data"
        ).build()

        //fun recyclerViewWork_dev(){


        recyclerViewWork_dev.adapter = WorkAdapter(DataObject.getAllData())
        recyclerViewWork_dev.layoutManager = LinearLayoutManager(context)

       // }
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            DevelopmentFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}