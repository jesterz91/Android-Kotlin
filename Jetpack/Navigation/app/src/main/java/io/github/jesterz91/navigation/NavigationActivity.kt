package io.github.jesterz91.navigation

import android.content.res.Resources
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.NavDeepLinkBuilder
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.ui.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import io.github.jesterz91.navigation.databinding.ActivityNavigationBinding
import io.github.jesterz91.navigation.fragment.FlowStepFragment

class NavigationActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener, NavController.OnDestinationChangedListener {

    private val binding: ActivityNavigationBinding by lazy { ActivityNavigationBinding.inflate(layoutInflater) }

    private val navController: NavController by lazy { findNavController(R.id.my_nav_host_fragment) }

    private val appBarConfiguration: AppBarConfiguration by lazy { AppBarConfiguration(navController.graph) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        navController.addOnDestinationChangedListener(this)
        setupActionBarWithNavController(navController, appBarConfiguration)
        
        binding.bottomNavigationView.setupWithNavController(navController)
        binding.bottomNavigationView.setOnNavigationItemSelectedListener(this)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onNavigationItemSelected(menu: MenuItem): Boolean {
        when (menu.itemId) {
            R.id.home_dest -> {
                val pendingIntent = NavDeepLinkBuilder(this)
                        .setGraph(R.navigation.nav_graph)
                        .setDestination(R.id.homeFragment)
                        .createPendingIntent()

                pendingIntent.send()
            }
            R.id.deeplink_dest -> {
                val pendingIntent = navController.createDeepLink()
                        .setGraph(R.navigation.nav_graph)
                        .setDestination(R.id.flowStepTwoFragment)
                        .setArguments(bundleOf(FlowStepFragment.FLOW_STEP_NUMBER to 4))
                        .createPendingIntent()

                pendingIntent.send()
            }
        }
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.overflow_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return item.onNavDestinationSelected(navController) || super.onOptionsItemSelected(item)
    }

    override fun onDestinationChanged(controller: NavController, destination: NavDestination, arguments: Bundle?) {
        val dest: String = try {
            resources.getResourceName(destination.id)
        } catch (e: Resources.NotFoundException) {
            destination.id.toString()
        }
        Log.d("Navigation", "Navigated to $dest")
    }
}