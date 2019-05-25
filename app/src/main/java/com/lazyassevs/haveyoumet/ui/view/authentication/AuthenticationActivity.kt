package com.lazyassevs.haveyoumet.ui.view.authentication

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import com.lazyassevs.haveyoumet.R
import com.lazyassevs.haveyoumet.databinding.ActivityAuthenticationBinding
import com.lazyassevs.haveyoumet.util.base.BaseActivity
import com.lazyassevs.haveyoumet.util.extensions.withBinding

class AuthenticationActivity : BaseActivity() {

    companion object {
        fun newIntent(context: Context): Intent = Intent(context, AuthenticationActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
    }

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        withBinding<ActivityAuthenticationBinding>(R.layout.activity_authentication) {
            setSupportActionBar(toolbar)
            navController = findNavController(R.id.nav_host_fragment)
            setupActionBarWithNavController(navController)
        }
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.signInFragment -> supportActionBar?.hide()
                else -> supportActionBar?.show()
            }
        }
    }

    /**
     * Have the NavHelper look for an action or destination matching the menu
     * item id and navigate there if found.
     * Otherwise, bubble up to the parent.
     */
    override fun onOptionsItemSelected(item: MenuItem?): Boolean = when (item) {
        null -> super.onOptionsItemSelected(item)
        else -> NavigationUI.onNavDestinationSelected(item, navController) || super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean = navController.navigateUp() || super.onSupportNavigateUp()

}