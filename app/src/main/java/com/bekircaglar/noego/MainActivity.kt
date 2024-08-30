package com.bekircaglar.noego

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.bekircaglar.noego.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity(), OnSwitchStateChangedListener {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    private lateinit var bottomNavBar: BottomNavigationView
    private lateinit var navController: NavController
    private val selectedItems = mutableListOf<NavItem>()
    private var egoSwitchChecked = true // Initialize as true for the default state

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        bottomNavBar = binding.bottomBar
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView5) as NavHostFragment
        navController = navHostFragment.navController

        bottomNavBar.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.homeFragment -> navigateToHome()
                R.id.kindnessFragment -> navigateToFragment(R.id.kindnessFragment)
                R.id.optimismFragment -> navigateToFragment(R.id.optimismFragment)
                R.id.respectFragment -> navigateToFragment(R.id.respectFragment)
                R.id.givingFragment -> navigateToFragment(R.id.givingFragment)
                R.id.happinessFragment -> navigateToFragment(R.id.happinessFragment)
                else -> false
            }
        }

        // Initial setup
        updateBottomNavBar()
    }

    override fun onSwitchStateChanged(navItem: NavItem, isChecked: Boolean) {
        if (navItem.id == 0) { // Ego switch
            egoSwitchChecked = isChecked
            if (isChecked) {
                hideBottomNavBar()
                clearNavItems()
            } else {
                showBottomNavBar()
                updateBottomNavBar()
            }
        } else { // Other switches
            if (isChecked) {
                addNavItem(navItem)
            } else {
                removeNavItem(navItem)
            }
        }
    }

    private fun addNavItem(navItem: NavItem) {
        if (selectedItems.contains(navItem)) return

        if (selectedItems.size >= 5) {
            Toast.makeText(this, "Hey stranger! You can only select up to 5 items", Toast.LENGTH_SHORT).show()
            selectedItems.removeAt(0)
        }

        selectedItems.add(navItem)
        updateBottomNavBar()
    }

    private fun removeNavItem(navItem: NavItem) {
        selectedItems.remove(navItem)
        updateBottomNavBar()
    }

    private fun updateBottomNavBar() {
        if (egoSwitchChecked) {
            hideBottomNavBar()
            return
        }

        val menu = bottomNavBar.menu
        menu.clear()

        menu.add(0, R.id.homeFragment, 0, "Home").setIcon(R.drawable.ic_home)

        val itemsToShow = selectedItems.take(4)
        itemsToShow.forEachIndexed { index, item ->
            menu.add(0, item.id, index + 1, item.title).setIcon(item.icon)
        }
    }

    private fun clearNavItems() {
        selectedItems.clear()
        updateBottomNavBar()
    }

    private fun navigateToHome(): Boolean {
        clearNavItems()
        navController.navigate(R.id.homeFragment)
        return true
    }

    private fun navigateToFragment(fragmentId: Int): Boolean {
        navController.navigate(fragmentId)
        return true
    }

    private fun hideBottomNavBar() {
        bottomNavBar.visibility = BottomNavigationView.GONE
    }

    private fun showBottomNavBar() {
        bottomNavBar.visibility = BottomNavigationView.VISIBLE
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
