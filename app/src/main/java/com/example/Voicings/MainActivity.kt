package com.example.Voicings

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.AppBarConfiguration.Builder
import androidx.navigation.ui.NavigationUI.navigateUp
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.example.Voicings.DatabaseManager.Companion.initializeInstance
import com.example.Voicings.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar


class MainActivity : AppCompatActivity() {
    private var mAppBarConfiguration: AppBarConfiguration? = null
    var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        val context = this.applicationContext
        val dbHelper = DBHelper(context)
        initializeInstance(dbHelper)
        setContentView(binding!!.root)
        setSupportActionBar(binding!!.appBarMain.toolbar)
        binding!!.appBarMain.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null)
                .setAnchorView(R.id.fab).show()
        }
        val drawer = binding!!.drawerLayout
        val navigationView = binding!!.navView
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = Builder(
            R.id.nav_choix, R.id.nav_edit, R.id.nav_slideshow
        )
            .setOpenableLayout(drawer)
            .build()
        val navController = findNavController(this, R.id.nav_host_fragment_content_main)
        setupActionBarWithNavController(this, navController, mAppBarConfiguration!!)
        setupWithNavController(navigationView, navController)
        insertSampleData()
    }

    private fun insertSampleData() {
        val typeRepo = TypeRepo()
        val melodyRepo = MelodyRepo()
        val styleRepo = StyleRepo()
        val voicingRepo = VoicingRepo()
        typeRepo.populate()
        melodyRepo.populate()
        styleRepo.populate()
        voicingRepo.populate()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(this, R.id.nav_host_fragment_content_main)
        return (navigateUp(navController, mAppBarConfiguration!!)
                || super.onSupportNavigateUp())
    }

}