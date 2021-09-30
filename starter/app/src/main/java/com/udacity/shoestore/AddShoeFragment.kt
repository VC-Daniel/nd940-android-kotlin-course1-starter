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
import com.udacity.shoestore.models.Shoe


class AddShoeFragment : Fragment() {

    private lateinit var binding: FragmentAddShoeBinding
    private lateinit var viewModel: AddShoeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_shoe, container, false)

        viewModel = ViewModelProvider(this).get(AddShoeViewModel::class.java)

        binding.addShoeViewModel = viewModel

        binding.lifecycleOwner = this

        viewModel.eventCancelCreate.observe(viewLifecycleOwner, { createCanceled ->
            if (createCanceled) {
                findNavController().popBackStack()
                viewModel.onCancelCreateComplete()
            }
        }
        )

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