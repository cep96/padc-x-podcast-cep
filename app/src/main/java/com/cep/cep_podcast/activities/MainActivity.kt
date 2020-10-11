package com.cep.cep_podcast.activities

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.cep.cep_podcast.R
import com.cep.cep_podcast.fragments.DownloadedFragment
import com.cep.cep_podcast.fragments.HomeFragment
import com.cep.cep_podcast.fragments.ProfileFragment
import com.cep.cep_podcast.fragments.SearchFragment
import com.cep.shared.activities.BaseActivity
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null){
            openFragment(HomeFragment.newInstance())
        }

        setUpBottomNavigation()

    }

    private fun setUpBottomNavigation() {
        bottomNavigation.setOnNavigationItemSelectedListener {item: MenuItem ->

            when(item.itemId){
                R.id.navHome -> {
                    openFragment(HomeFragment.newInstance())
                    true
                }
                R.id.navSearch -> {
                    openFragment(SearchFragment.newInstance())
                    true
                }
                R.id.navDownload -> {
                    openFragment(DownloadedFragment.newInstance())
                    true
                }
                R.id.navProfile -> {
                    openFragment(ProfileFragment.newInstance())
                    true
                }
            }
            false
        }
    }

    private fun openFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.commit()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}

