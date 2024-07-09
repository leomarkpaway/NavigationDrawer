package com.leomarkpaway.navigationdrawer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    private val toolbar: Toolbar by lazy { this.findViewById(R.id.toolbar) }
    private val drawerLayout: DrawerLayout by lazy { this.findViewById(R.id.drawer_layout) }
    private val navigationView: NavigationView by lazy { this.findViewById(R.id.nav_view) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // setup custom toolbar
        setSupportActionBar(toolbar)

        // setup drawer toggle
        val toggle = ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_nav, R.string.close_nav)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        // setup initial value fragment container
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, HomeFragment()).commit()
            navigationView.setCheckedItem(R.id.home_screen)
        }

        // setup navigation view listener
        navigationView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.home_screen -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, HomeFragment()).commit()
                    drawerLayout.closeDrawer(GravityCompat.START)
                    true
                }
                R.id.wallet_screen -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, WalletFragment()).commit()
                    drawerLayout.closeDrawer(GravityCompat.START)
                    true
                }
                else -> {
                    drawerLayout.closeDrawer(GravityCompat.START)
                    true
                }
            }
        }

    }

}