package com.udacity.shoestore

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.udacity.shoestore.databinding.FragmentInstructionsBinding

/** Display instructions on how to use the app. */
class InstructionsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment using data binding and handle navigating away
        // from the instructions when the user has finished reading the instructions
        val binding: FragmentInstructionsBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_instructions, container, false)

        binding.continueButton.setOnClickListener { v: View ->
            v.findNavController()
                .navigate(InstructionsFragmentDirections.actionInstructionsFragmentToViewShoesFragment())
        }

        return binding.root
    }

}