package com.udacity.shoestore

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.udacity.shoestore.databinding.FragmentViewShoesBinding
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.models.ShoeViewModel
import timber.log.Timber


class ViewShoesFragment : Fragment() {

    private lateinit var viewModel: ShoeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentViewShoesBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_view_shoes, container, false)

        binding.newShoeActionButton.setOnClickListener {v:View -> v.findNavController().navigate(ViewShoesFragmentDirections.actionViewShoesFragmentToAddShoeFragment())}

        viewModel = ViewModelProvider(this).get(ShoeViewModel::class.java)

        // Specify the current activity as the lifecycle owner of the binding. This is used so that
        // the binding can observe LiveData updates
        binding.setLifecycleOwner(this)

        viewModel.shoeList.observe(viewLifecycleOwner, Observer {shoes -> displayShoes(shoes) })

        val viewShoesFragmentArgs by navArgs<ViewShoesFragmentArgs>()

        if(viewShoesFragmentArgs.shoe != null) {
            viewModel.addShoe(viewShoesFragmentArgs.shoe!!)
        }

        return binding.root
    }

    private fun displayShoes(shoes: List<Shoe>) {
        shoes.count()
    }

}