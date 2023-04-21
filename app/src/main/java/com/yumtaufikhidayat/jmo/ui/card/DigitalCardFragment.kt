package com.yumtaufikhidayat.jmo.ui.card

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.yumtaufikhidayat.jmo.R
import com.yumtaufikhidayat.jmo.databinding.FragmentDigitalCardBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DigitalCardFragment : Fragment() {
    private var _binding: FragmentDigitalCardBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDigitalCardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setText()
    }

    private fun setText() {
        binding.tvDigitalCard.text = getString(
            R.string.txt_digital_card_under_development,
            getString(R.string.txt_digital_card)
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}