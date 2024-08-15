package com.nttdata.androapp.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.etebarian.meowbottomnavigation.MeowBottomNavigation
import com.google.android.material.bottomsheet.BottomSheetDragHandleView
import com.nttdata.androapp.ProjectFragments.DevelopmentFragment
import com.nttdata.androapp.ProjectFragments.HUATsFragment
import com.nttdata.androapp.ProjectFragments.IncidentFragment
import com.nttdata.androapp.ProjectFragments.MCissuesFragment
import com.nttdata.androapp.R


class IosFragment : Fragment() {

    lateinit var navigationView : MeowBottomNavigation

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view : View =  inflater.inflate(R.layout.fragment_ios, container, false)

        navigationView = view.findViewById(R.id.meowBottomNav)

        addFragment(DevelopmentFragment.newInstance())
        navigationView.show(0)
        navigationView.add(MeowBottomNavigation.Model(0,R.drawable.reme))
        navigationView.add(MeowBottomNavigation.Model(1,R.drawable.reme))
        navigationView.add(MeowBottomNavigation.Model(2,R.drawable.reme))
        navigationView.add(MeowBottomNavigation.Model(3,R.drawable.reme))

        navigationView.setOnClickMenuListener {
            when(it.id){
                0 -> {
                    replacementFragment(DevelopmentFragment.newInstance())
                }
                1 -> {
                    replacementFragment(IncidentFragment.newInstance())
                }
                2 -> {
                    replacementFragment(HUATsFragment.newInstance())

                }
                3 -> {
                    replacementFragment(MCissuesFragment.newInstance())
                }
                else -> {
                    replacementFragment(DevelopmentFragment.newInstance())
                }
            }
        }


        return view
    }
    private fun replacementFragment(fragment: Fragment){
        val fragmentTransition = requireActivity().supportFragmentManager.beginTransaction()
            fragmentTransition.replace(R.id.fragmentContainer,fragment).addToBackStack(Fragment::class.java.simpleName).commit()

    }
    private fun addFragment(fragment: Fragment) {
        val fragmentTransition = requireActivity().supportFragmentManager.beginTransaction()
        fragmentTransition.add(R.id.fragmentContainer, fragment)
            .addToBackStack(Fragment::class.java.simpleName).commit()


    }
}