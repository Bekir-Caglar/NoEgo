package com.bekircaglar.noego

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.bekircaglar.noego.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        setupSwitchListeners()
        restoreSwitchStates()

        return binding.root
    }

    private fun setupSwitchListeners() {
        binding.egoSwitch.setOnCheckedChangeListener { _, isChecked ->
            viewModel.toggleSwitch("ego", isChecked)
            // Other switches should be disabled or enabled based on ego switch state
            setSwitchesEnabled(!isChecked)
        }
        binding.happinessSwitch.setOnCheckedChangeListener { _, isChecked ->
            viewModel.toggleSwitch("happiness", isChecked)
        }
        binding.optimismSwitch.setOnCheckedChangeListener { _, isChecked ->
            viewModel.toggleSwitch("optimism", isChecked)
        }
        binding.kindnessSwitch.setOnCheckedChangeListener { _, isChecked ->
            viewModel.toggleSwitch("kindness", isChecked)
        }
        binding.givingSwitch.setOnCheckedChangeListener { _, isChecked ->
            viewModel.toggleSwitch("giving", isChecked)
        }
        binding.respectSwitch.setOnCheckedChangeListener { _, isChecked ->
            viewModel.toggleSwitch("respect", isChecked)
        }
    }

    private fun restoreSwitchStates() {
        viewModel.isEgoOn.observe(viewLifecycleOwner) { isEgoOn ->
            binding.egoSwitch.isChecked = isEgoOn

            setSwitchesEnabled(!isEgoOn)
        }
        viewModel.isHappinessOn.observe(viewLifecycleOwner) { isChecked ->
            binding.happinessSwitch.isChecked = isChecked
        }
        viewModel.isOptimismOn.observe(viewLifecycleOwner) { isChecked ->
            binding.optimismSwitch.isChecked = isChecked
        }
        viewModel.isKindnessOn.observe(viewLifecycleOwner) { isChecked ->
            binding.kindnessSwitch.isChecked = isChecked
        }
        viewModel.isGivingOn.observe(viewLifecycleOwner) { isChecked ->
            binding.givingSwitch.isChecked = isChecked
        }
        viewModel.isRespectOn.observe(viewLifecycleOwner) { isChecked ->
            binding.respectSwitch.isChecked = isChecked
        }
    }

    private fun setSwitchesEnabled(enabled: Boolean) {
        binding.happinessSwitch.isEnabled = enabled
        binding.optimismSwitch.isEnabled = enabled
        binding.kindnessSwitch.isEnabled = enabled
        binding.givingSwitch.isEnabled = enabled
        binding.respectSwitch.isEnabled = enabled
    }
}
