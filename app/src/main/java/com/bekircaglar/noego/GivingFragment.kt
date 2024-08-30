package com.bekircaglar.noego

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bekircaglar.noego.databinding.FragmentGivingBinding
import com.bekircaglar.noego.databinding.FragmentHappinessBinding


class GivingFragment : Fragment() {

    private var _binding: FragmentGivingBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentGivingBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null

    }

}