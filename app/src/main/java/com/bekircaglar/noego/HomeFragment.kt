package com.bekircaglar.noego

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import com.bekircaglar.noego.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val kindnessSwitch = binding.kindnessSwitch
        val optimismSwitch = binding.optimismSwitch
        val respectSwitch = binding.respectSwitch
        val givingSwitch = binding.givingSwitch
        val egoSwitch = binding.egoSwitch
        val happinessSwitch = binding.happinessSwitch

        val colorStateList = ColorStateList(
            arrayOf(
                intArrayOf(android.R.attr.state_checked),
                intArrayOf(-android.R.attr.state_checked)
            ),
            intArrayOf(
                Color.GREEN,
                Color.RED
            )
        )

        fun setSwitchColors(switch: Switch) {
            switch.trackTintList = colorStateList
            switch.thumbTintList = colorStateList
        }

        setSwitchColors(kindnessSwitch)
        setSwitchColors(optimismSwitch)
        setSwitchColors(respectSwitch)
        setSwitchColors(givingSwitch)
        setSwitchColors(egoSwitch)
        setSwitchColors(happinessSwitch)

        fun setOtherSwitchesEnabled(enabled: Boolean) {
            kindnessSwitch.isEnabled = enabled
            optimismSwitch.isEnabled = enabled
            respectSwitch.isEnabled = enabled
            givingSwitch.isEnabled = enabled
            happinessSwitch.isEnabled = enabled
        }

        egoSwitch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                setOtherSwitchesEnabled(false)
                kindnessSwitch.isChecked = false
                optimismSwitch.isChecked = false
                respectSwitch.isChecked = false
                givingSwitch.isChecked = false
                happinessSwitch.isChecked = false
            } else {
                setOtherSwitchesEnabled(true)
            }
        }

        kindnessSwitch.setOnCheckedChangeListener { _, _ ->
            if (egoSwitch.isChecked) {
                kindnessSwitch.isChecked = false
            }
        }

        optimismSwitch.setOnCheckedChangeListener { _, _ ->
            if (egoSwitch.isChecked) {
                optimismSwitch.isChecked = false
            }
        }

        respectSwitch.setOnCheckedChangeListener { _, _ ->
            if (egoSwitch.isChecked) {
                respectSwitch.isChecked = false
            }
        }

        givingSwitch.setOnCheckedChangeListener { _, _ ->
            if (egoSwitch.isChecked) {
                givingSwitch.isChecked = false
            }
        }

        happinessSwitch.setOnCheckedChangeListener { _, _ ->
            if (egoSwitch.isChecked) {
                happinessSwitch.isChecked = false
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
