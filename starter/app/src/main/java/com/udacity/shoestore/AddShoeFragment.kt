package com.udacity.shoestore

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.databinding.FragmentAddShoeBinding
import com.udacity.shoestore.models.AddShoeViewModel

/** Allows the user to add a new shoe to the shoe list */
class AddShoeFragment : Fragment() {

    private lateinit var binding: FragmentAddShoeBinding
    private lateinit var viewModel: AddShoeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel = ViewModelProvider(this).get(AddShoeViewModel::class.java)

        // Inflate the layout for this fragment using data binding and connect the view model
        // that handles the logic with the UI
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_shoe, container, false)
        binding.addShoeViewModel = viewModel
        binding.lifecycleOwner = this

        // Return to the shoe list if the user no longer wants to add a shoe
        viewModel.eventCancelCreate.observe(viewLifecycleOwner, { createCanceled ->
            if (createCanceled) {
                findNavController().popBackStack()
                viewModel.onCancelCreateComplete()
            }
        }
        )

        // Save a new shoe and then return to the shoe list
        viewModel.eventSaveShoe.observe(viewLifecycleOwner, { createShoe ->
            if (createShoe) {
                val action = AddShoeFragmentDirections.actionAddShoeFragmentToViewShoesFragment()
                action.shoe = viewModel.createShoe()

                findNavController()
                    .navigate(action)
                viewModel.onSaveShoeComplete()
            }
        }
        )

        return binding.root
    }
}