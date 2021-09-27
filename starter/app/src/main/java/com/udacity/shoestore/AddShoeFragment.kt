package com.udacity.shoestore

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.udacity.shoestore.databinding.FragmentAddShoeBinding


class AddShoeFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentAddShoeBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_shoe, container, false)

        binding.cancelButton.setOnClickListener{v:View -> v.findNavController().popBackStack()}
        binding.saveButton.setOnClickListener {v:View -> v.findNavController().navigate(AddShoeFragmentDirections.actionAddShoeFragmentToViewShoesFragment())}

        return binding.root
    }

}