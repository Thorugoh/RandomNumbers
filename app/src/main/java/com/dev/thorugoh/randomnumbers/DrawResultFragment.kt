package com.dev.thorugoh.randomnumbers

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.dev.thorugoh.randomnumbers.databinding.FragmentDrawResultBinding
import kotlinx.coroutines.launch
import kotlin.random.Random

class DrawResultFragment : Fragment() {
    private val viewModel: DrawViewModel by activityViewModels()

    private var _binding: FragmentDrawResultBinding? = null
    private val binding get() = _binding!!

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentDrawResultBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {


            lifecycleScope.launch {
                viewModel.uiState.collect { uiState ->
                    tvDrawNumber.text = getString(R.string.draw_number, uiState.currentDrawNumber.toString())
                    clearLastDrewNumbers()

                    uiState.drawNumbers.forEach { drawNumber ->
                        generateRandomNumbers(drawNumber)
                    }
                }
            }
        }
    }

    private fun FragmentDrawResultBinding.generateRandomNumbers(drawNumber: Int) {
        val drawnNumberTextView = TextView(requireContext()).apply {
            id = View.generateViewId()
            text = drawNumber.toString()
            setTextAppearance(R.style.TextAppearance_RobotoMono_Overline)
            textSize = 48f
            setTextColor(ContextCompat.getColor(requireContext(), R.color.content_brand))
        }

        root.addView(drawnNumberTextView)
        flowResultNumbersHelper.referencedIds =
            flowResultNumbersHelper.referencedIds.plus(drawnNumberTextView.id)
    }

    private fun FragmentDrawResultBinding.clearLastDrewNumbers(){
        flowResultNumbersHelper.referencedIds.forEach {
            root.removeView(root.findViewById(it))
        }
    }

}