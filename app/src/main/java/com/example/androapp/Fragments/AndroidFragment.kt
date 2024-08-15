package com.nttdata.androapp.Fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.get
import com.blogspot.atifsoftwares.animatoolib.Animatoo
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.nttdata.androapp.NewTask
import com.nttdata.androapp.ProjectFragments.*
import com.nttdata.androapp.R



class AndroidFragment : Fragment() {

    lateinit var bottomNavigationView: BottomNavigationView
    lateinit var fab : FloatingActionButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view : View =  inflater.inflate(R.layout.fragment_android, container, false)

        bottomNavigationView = view.findViewById(R.id.bottomNavigationView)
        fab = view.findViewById(R.id.fab)


        bottomNavigationView.background = null
        bottomNavigationView.menu.getItem(2).isEnabled = false

        addFragment(DevelopmentFragment.newInstance())
        bottomNavigationView[0]

        bottomNavigationView.setOnItemSelectedListener {
            it.isChecked = true
            when(it.itemId){
                R.id.development -> replaceFragment(DevelopmentFragment())
                R.id.incidents -> replaceFragment(IncidentFragment())
                R.id.huats -> replaceFragment(HUATsFragment())
                R.id.mcissues -> replaceFragment(MCissuesFragment())
                else -> replaceFragment(DevelopmentFragment())
            }
            true
        }


        //Create new Recycler task

        fab.setOnClickListener {
            val intent = Intent(context,NewTask::class.java)
            startActivity(intent)
            activity?.finish()
            context?.let { it1 -> Animatoo.animateSlideUp(it1) }
        }



        return view
    }
    private fun replaceFragment(fragment :Fragment){
        val fragmentManager = requireActivity().supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.androFrame,fragment)
        fragmentTransaction.commit()
//        drawerLayout.closeDrawers()
//        setTitle(title)
    }

    private fun addFragment(fragment: Fragment){
        val fragmentManager = requireActivity().supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.androFrame,fragment)
        fragmentTransaction.commit()
    }

}