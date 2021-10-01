package com.udacity.shoestore

import android.os.Bundle
import android.view.*
import android.widget.LinearLayout
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.udacity.shoestore.databinding.FragmentViewShoesBinding
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.models.ShoeViewModel


class ViewShoesFragment : Fragment() {

    private lateinit var viewModel: ShoeViewModel
    lateinit var binding: FragmentViewShoesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this).get(ShoeViewModel::class.java)

        val viewShoesFragmentArgs by navArgs<ViewShoesFragmentArgs>()

        setHasOptionsMenu(true)

        if (viewShoesFragmentArgs.shoe != null) {
            viewModel.addShoe(viewShoesFragmentArgs.shoe!!)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.overflow_menu, menu)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_view_shoes, container, false)

        binding.newShoeActionButton.setOnClickListener { v: View ->
            v.findNavController()
                .navigate(ViewShoesFragmentDirections.actionViewShoesFragmentToAddShoeFragment())
        }

        // Specify the current activity as the lifecycle owner of the binding. This is used so that
        // the binding can observe LiveData updates
        binding.setLifecycleOwner(this)

        viewModel.shoeList.observe(viewLifecycleOwner, Observer { shoes -> displayShoes(shoes) })

        return binding.root
    }

    private fun displayShoes(shoes: List<Shoe>) {
        shoes.count()

        val shoeLayout =
            LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.MATCH_PARENT,
            )

        shoeLayout.bottomMargin = resources.getDimension(R.dimen.small_margin).toInt()

        for (shoe in shoes) {

            var shoeInfoText = TextView(context);
            shoeInfoText.layoutParams = shoeLayout

            shoeInfoText.text = String.format(
                getString(R.string.shoe_info_format),
                shoe.name,
                shoe.size,
                shoe.company,
                shoe.description
            )

            binding.shoeListLayout.addView(shoeInfoText, 0)
        }
    }

}