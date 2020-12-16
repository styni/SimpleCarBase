package com.styni.simplecarbase.ui.global

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.core.view.GravityCompat
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.styni.simplecarbase.R
import kotlinx.android.synthetic.main.activity_main.*

class AppActivity : AppCompatActivity() {

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navView.bringToFront()
    }

    override fun onStart() {
        super.onStart()
        navView.setNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.all_cars -> findNavController(R.id.nav_host_fragment).navigate(R.id.selectBrandFragment)
                R.id.search -> findNavController(R.id.nav_host_fragment).navigate(R.id.searchFragment)
            }
            onBackPressed()
            true
        }
    }

    override fun onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
            return
        }
        super.onBackPressed()
    }
}