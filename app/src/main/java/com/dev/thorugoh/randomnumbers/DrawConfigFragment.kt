package com.dev.thorugoh.randomnumbers

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources.getColorStateList
import androidx.core.widget.addTextChangedListener
import com.dev.thorugoh.randomnumbers.databinding.FragmentDrawConfigBinding

class DrawConfigFragment : Fragment() {

    private var _binding: FragmentDrawConfigBinding? = null
    private val binding get() = _binding!!

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDrawConfigBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            swtNotRepeatNumbers.setOnCheckedChangeListener { _, isChecked ->
                swtNotRepeatNumbers.trackTintList =
                    getColorStateList(
                        requireContext(),
                        if (isChecked) R.color.background_brand else R.color.background_tertiary
                    )
            }

            etAmountNumbers.addTextChangedListener { text ->

            }

            etInitialLimit.addTextChangedListener { text ->

            }

            etFinalLimit.addTextChangedListener { text ->

            }


        }
    }

}