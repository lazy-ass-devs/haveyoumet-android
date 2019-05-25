package com.lazyassevs.haveyoumet.ui.view.album

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import com.lazyassevs.haveyoumet.R
import com.lazyassevs.haveyoumet.databinding.ActivityAlbumBinding
import com.lazyassevs.haveyoumet.util.base.BaseActivity
import com.lazyassevs.haveyoumet.util.extensions.withBinding

class AlbumActivity : BaseActivity() {

    companion object {
        fun newIntent(context: Context): Intent = Intent(context, AlbumActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
    }

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        withBinding<ActivityAlbumBinding>(R.layout.activity_album) {
            setSupportActionBar(toolbar)
            navController = findNavController(R.id.nav_host_fragment)
            setupActionBarWithNavController(navController)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean = when (item) {
        null -> super.onOptionsItemSelected(item)
        else -> NavigationUI.onNavDestinationSelected(item, navController) || super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean = navController.navigateUp() || super.onSupportNavigateUp()

}