package com.bekircaglar.noego

import android.content.Context
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
    private var switchStateListener: OnSwitchStateChangedListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnSwitchStateChangedListener) {
            switchStateListener = context
        } else {
            throw RuntimeException("$context must implement OnSwitchStateChangedListener")
        }
    }

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
            switchStateListener?.onSwitchStateChanged(NavItem(0, 0, "Ego"), isChecked)
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

        val navItems = listOf(
            NavItem(R.id.kindnessFragment, R.drawable.ic_kindness, "Kindness"),
            NavItem(R.id.optimismFragment, R.drawable.ic_optimism, "Optimism"),
            NavItem(R.id.respectFragment, R.drawable.ic_respect, "Respect"),
            NavItem(R.id.givingFragment, R.drawable.ic_giving, "Giving"),
            NavItem(R.id.happinessFragment, R.drawable.ic_happiness, "Happiness")
        )

        val switches = listOf(
            kindnessSwitch to navItems[0],
            optimismSwitch to navItems[1],
            respectSwitch to navItems[2],
            givingSwitch to navItems[3],
            happinessSwitch to navItems[4]
        )

        switches.forEach { (switch, navItem) ->
            switch.setOnCheckedChangeListener { _, isChecked ->
                if (egoSwitch.isChecked) {
                    switch.isChecked = false
                } else {
                    switchStateListener?.onSwitchStateChanged(navItem, isChecked)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
