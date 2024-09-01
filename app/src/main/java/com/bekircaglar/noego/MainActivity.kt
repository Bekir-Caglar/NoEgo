package com.bekircaglar.noego

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.bekircaglar.noego.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        observeViewModel()
        setupNavigation()
    }

    private fun observeViewModel() {
        viewModel.isEgoOn.observe(this) { isEgoOn ->
            binding.bottomBar.visibility = if (isEgoOn) View.GONE else View.VISIBLE
        }

        viewModel.selectedItems.observe(this) { items ->
            updateBottomNavigationBar(items)
        }
    }

    private fun setupNavigation() {
        binding.bottomBar.setOnNavigationItemSelectedListener { menuItem ->
            val selectedFragment: Fragment = when (menuItem.title.toString()) {
                "MainScreen" -> HomeFragment()
                "happiness" -> HappinessFragment()
                "optimism" -> OptimismFragment()
                "kindness" -> KindnessFragment()
                "giving" -> GivingFragment()
                "respect" -> RespectFragment()
                else -> HomeFragment()
            }
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView5, selectedFragment)
                .commit()

            true
        }
    }

    private fun updateBottomNavigationBar(items: List<String>) {
        val menu = binding.bottomBar.menu
        menu.clear()

        items.forEachIndexed { index, item ->
            if (index < 5) {  // Ensure that no more than 5 items are added
                val menuItem = menu.add(0, index, index, item)
                // Set the icon based on the item name
                when (item) {
                    "MainScreen" -> menuItem.setIcon(R.drawable.ic_home)
                    "happiness" -> menuItem.setIcon(R.drawable.ic_happiness)
                    "optimism" -> menuItem.setIcon(R.drawable.ic_optimism)
                    "kindness" -> menuItem.setIcon(R.drawable.ic_kindness)
                    "giving" -> menuItem.setIcon(R.drawable.ic_giving)
                    "respect" -> menuItem.setIcon(R.drawable.ic_respect)
                }
            } else {
                Toast.makeText(this, "En fazla 5 item ekleyebilirsiniz", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
