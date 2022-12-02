package com.anteprocess.food.todo.data

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.anteprocess.food.R
import dagger.hilt.android.AndroidEntryPoint


class TodoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todo)

        // calling the action bar

        // showing the back button in action bar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        setupActionBarWithNavController(findNavController(R.id.navHostfragment))

    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.navHostfragment)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

}