package com.nttdata.androapp

import android.opengl.Visibility
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.fragment_login_tab.*

class LoginActivity : AppCompatActivity() {

    lateinit var tabLayout : TabLayout
    private lateinit var viewPager : ViewPager
    var v : Float = 0F


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        tabLayout = findViewById(R.id.tab_layout)
        viewPager = findViewById(R.id.view_pager)


        val adapter = MyViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(LoginTabFragment(),"LogIn")
        adapter.addFragment(SignupTabFragment(),"SignUp")
        viewPager.adapter = adapter
        tabLayout.setupWithViewPager(viewPager)


        tabLayout.translationX = 300F
        tabLayout.alpha = v
        tabLayout.animate().translationX(0F).alpha(1F).setDuration(1000).setStartDelay(100).start()

    }
    class MyViewPagerAdapter(manager: FragmentManager): FragmentPagerAdapter(manager){
        private val fragmentList : MutableList<Fragment> = ArrayList()
        private val titleList : MutableList<String> = ArrayList()

        override fun getCount(): Int {
            return fragmentList.size
        }

        override fun getItem(position: Int): Fragment {
            return fragmentList[position]
        }

        fun addFragment(fragment: Fragment,title:String){
            fragmentList.add(fragment)
            titleList.add(title)
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return titleList[position]
        }

    }
}