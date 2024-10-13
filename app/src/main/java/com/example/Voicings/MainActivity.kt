package com.example.Voicings

import android.database.sqlite.SQLiteDatabase
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
import java.util.Arrays

class MainActivity : AppCompatActivity() {
    private var mAppBarConfiguration: AppBarConfiguration? = null
    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val context = this.applicationContext
        val dbHelper = DBHelper(context)
        db = dbHelper.writableDatabase
        initializeInstance(dbHelper)
        binding = ActivityMainBinding.inflate(layoutInflater)
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
        typeRepo.delete()
        melodyRepo.delete()
        styleRepo.delete()
        voicingRepo.delete()

        //Insert Sample data if the table is empty
        val type = Type()
        var i = 0
        var list: List<String> = ArrayList(listOf("Maj7", "Min7", "Dom7", "Min7b5", "Dim7", "Alt"))
        while (i < list.size) {
            type.name = list[i]
            type.typeId = i.toString()
            i++
            typeRepo.insert(type)
        }
        val style = Style()
        i = 0
        list = ArrayList(
            listOf(
                "Closed",
                "Opened",
                "Spread",
                "Rootless",
                "Quartal",
                "SoWhat",
                "UpperStructure"
            )
        )
        while (i < list.size) {
            style.name = list[i]
            style.styleId = i.toString()
            i++
            styleRepo.insert(style)
        }
        val melody = Melody()
        i = 0
        list = ArrayList(
            listOf(
                "1",
                "2",
                "b3",
                "3",
                "4",
                "b5",
                "5",
                "#5",
                "6",
                "7",
                "M7",
                "b9",
                "9",
                "11",
                "#11",
                "b13",
                "13"
            )
        )
        while (i < list.size) {
            melody.name = list[i]
            melody.melodyId = i.toString()
            i++
            melodyRepo.insert(melody)
        }

        val voicing = Voicing()
        i = 0
        val listoflists = Arrays.asList<List<String>>(
            mutableListOf("Maj7", "M7", "Closed", "1"),
            mutableListOf("Min7", "11", "Spread", "2"),
            mutableListOf("Dom7", "5", "Spread", "3"),
            mutableListOf("Min7", "9", "Rootless", "4"),
            mutableListOf("Min7b5", "7", "Opened", "5")
        )
        while (i < listoflists.size) {
            voicing.type = listoflists[i][0]
            voicing.melody = listoflists[i][1]
            voicing.style = listoflists[i][2]
            voicing.voicingId = i.toString()
            i++
            voicingRepo.insert(voicing)
        }
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

    companion object {
        private val dbHelper: DBHelper? = null
        private var db: SQLiteDatabase? = null
    }
}