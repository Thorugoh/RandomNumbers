package com.dev.thorugoh.randomnumbers

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.dev.thorugoh.randomnumbers.databinding.FragmentDrawResultBinding
import kotlin.random.Random

class DrawResultFragment : Fragment() {
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
            tvDrawNumber.text = getString(R.string.draw_number, "10th")

            generateRandomNumbers()
            generateRandomNumbers()
        }
    }

    fun FragmentDrawResultBinding.generateRandomNumbers() {
        val drawnNumberTextView = TextView(requireContext()).apply {
            id = View.generateViewId()
            text = Random.nextInt(100).toString()
            setTextAppearance(R.style.TextAppearance_RobotoMono_Overline)
            textSize = 48f
            setTextColor(ContextCompat.getColor(requireContext(), R.color.content_brand))
        }

        root.addView(drawnNumberTextView)
        flowResultNumbersHelper.referencedIds =
            flowResultNumbersHelper.referencedIds.plus(drawnNumberTextView.id)
    }

}