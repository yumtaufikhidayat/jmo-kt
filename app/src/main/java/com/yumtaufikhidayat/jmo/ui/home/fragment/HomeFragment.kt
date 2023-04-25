package com.yumtaufikhidayat.jmo.ui.home.fragment

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.yumtaufikhidayat.jmo.R
import com.yumtaufikhidayat.jmo.databinding.FragmentHomeBinding
import com.yumtaufikhidayat.jmo.model.home.ImageSlider
import com.yumtaufikhidayat.jmo.ui.home.adapter.ImageSliderAdapter
import com.yumtaufikhidayat.jmo.ui.home.adapter.OtherServiceAdapter
import com.yumtaufikhidayat.jmo.ui.home.adapter.ServiceProgramAdapter
import com.yumtaufikhidayat.jmo.ui.home.viewmodel.HomeViewModel
import com.yumtaufikhidayat.jmo.utils.Common
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<HomeViewModel>()
    private var doubleBackToExitPressedOnce = false
    private val serviceProgramAdapter by lazy { ServiceProgramAdapter() }
    private val otherServiceAdapter by lazy { OtherServiceAdapter() }
    private val imageSliderAdapter by lazy { ImageSliderAdapter() }
    private var dotsIndicator: ArrayList<TextView>? = null
    private var handler: Handler? = null
    private var runnable: Runnable? = null

    private val backPressedCallback: OnBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            if (doubleBackToExitPressedOnce) {
                requireActivity().finish()
                return
            }

            doubleBackToExitPressedOnce = true
            Toast.makeText(requireContext(), "Ketuk sekali lagi untuk keluar ", Toast.LENGTH_SHORT).show()

            Handler(Looper.getMainLooper())
                .postDelayed({
                    doubleBackToExitPressedOnce = false
                }, Common.DELAY_TIME)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            backPressedCallback
        )

        initServiceProgramAdapter()
        initOtherProgramAdapter()
        initHandlerRunnable()
        setServiceProgramData()
        setOtherServiceProgramData()
        setServiceProgramListener()
        setImageSliderInformationData()
    }

    private fun initServiceProgramAdapter() {
        binding.rvServiceProgram.apply {
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            adapter = serviceProgramAdapter
        }
    }

    private fun initOtherProgramAdapter() {
        binding.rvOtherService.apply {
            layoutManager = GridLayoutManager(requireContext(), 4)
            setHasFixedSize(true)
            adapter = otherServiceAdapter
        }
    }

    private fun initHandlerRunnable() {
        dotsIndicator = ArrayList()
        handler = Handler(Looper.getMainLooper())
        runnable = object : Runnable {
            var index = 0
            override fun run() {
                if (index == dotsIndicator?.size) index = 0
                binding.vpInformation.currentItem = index
                index++
                handler?.postDelayed(this, Common.DELAY_TIME)
            }
        }
    }

    private fun setServiceProgramData() {
        val data = viewModel.listOfServiceProgram(requireContext())
        serviceProgramAdapter.submitList(data.take(2))
    }

    private fun setOtherServiceProgramData() {
        otherServiceAdapter.submitList(viewModel.listOfOtherService(requireContext()))
    }

    private fun setServiceProgramListener() {
        binding.btnOtherPrograms.setOnClickListener {
            findNavController().navigate(R.id.homeDetailFragment)
        }
    }

    private fun setImageSliderInformationData() {
        binding.apply {
            val dataImageSlider = viewModel.listOfImageSlider()
            vpInformation.adapter = imageSliderAdapter
            imageSliderAdapter.submitList(dataImageSlider)
            setDotsIndicator(dataImageSlider)
            vpInformation.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
                override fun onPageSelected(position: Int) {
                    selectedDotsIndicator(dataImageSlider, position)
                    super.onPageSelected(position)
                }
            })
        }
    }

    private fun setDotsIndicator(dataImageSlider: List<ImageSlider>) {
        dataImageSlider.indices.forEach { i ->
            binding.apply {
                dotsIndicator?.let { dots ->
                    dots.add(TextView(requireContext()))
                    dots[i].text = Html.fromHtml("&#9679", Html.FROM_HTML_MODE_LEGACY).toString()
                    dots[i].textSize = 14f
                    llDotsIndicator.addView(dots[i])
                }
            }
        }
    }

    private fun selectedDotsIndicator(dataImageSlider: List<ImageSlider>, position: Int) {
        dataImageSlider.indices.forEach { i ->
            val colors = if (i == position) R.color.matte_green_light else R.color.grey
            dotsIndicator?.get(i)?.setTextColor(ContextCompat.getColor(requireContext(), colors))
        }
    }

    override fun onStart() {
        super.onStart()
        runnable?.let { handler?.post(it) }
    }

    override fun onStop() {
        super.onStop()
        runnable?.let { handler?.removeCallbacks(it) }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}