package com.udacity.shoestore

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.udacity.shoestore.databinding.FragmentAddShoeBinding
import com.udacity.shoestore.models.Shoe


class AddShoeFragment : Fragment() {

    lateinit var binding: FragmentAddShoeBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_shoe, container, false)

        binding.cancelButton.setOnClickListener { v: View -> v.findNavController().popBackStack() }

        binding.saveButton.setOnClickListener { v: View ->

            val action = AddShoeFragmentDirections.actionAddShoeFragmentToViewShoesFragment()
            action.shoe = createShoe()

            v.findNavController()
                .navigate(action)
        }

        return binding.root
    }

    private fun createShoe(): Shoe {
        val name = binding.nameEditText.text.toString()
        val size: Double = try {
            binding.sizeEditText.text.toString().toDouble()
        } catch (ERROR: NumberFormatException) {
            0.0
        }

        val company = binding.companyEditText.text.toString()
        val description = binding.descriptionEditText.text.toString()

        return Shoe(name, size, company, description)

    }

}