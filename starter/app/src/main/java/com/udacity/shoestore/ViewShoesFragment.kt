package com.udacity.shoestore

import android.os.Bundle
import android.view.*
import android.widget.LinearLayout
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.udacity.shoestore.databinding.FragmentViewShoesBinding
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.models.ShoeViewModel

/** Display all the shoes in the shoe store including the user created shoes and allow the user
 * to navigate to a destination to create a new shoe */
class ViewShoesFragment : Fragment() {

    private lateinit var viewModel: ShoeViewModel
    lateinit var binding: FragmentViewShoesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this).get(ShoeViewModel::class.java)

        val viewShoesFragmentArgs by navArgs<ViewShoesFragmentArgs>()

        setHasOptionsMenu(true)

        // If the user has created a new shoe add it to the list of shoes in the shoe store
        if (viewShoesFragmentArgs.shoe != null) {
            viewModel.addShoe(viewShoesFragmentArgs.shoe!!)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        // Allow the user to logout
        inflater.inflate(R.menu.overflow_menu, menu)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment using data binding
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_view_shoes, container, false)

        // Specify the current activity as the lifecycle owner of the binding. This is used so that
        // the binding can observe LiveData updates
        binding.lifecycleOwner = this

        binding.viewShoesViewModel = viewModel

        viewModel.shoeList.observe(viewLifecycleOwner, Observer { shoes -> displayShoes(shoes) })

        // Handle the user navigating to the create a new show destination
        viewModel.eventCreateShoe.observe(viewLifecycleOwner, { createShoe ->
            if (createShoe) {
                findNavController()
                    .navigate(ViewShoesFragmentDirections.actionViewShoesFragmentToAddShoeFragment())
                viewModel.onCreateShoeComplete()
            }
        })

        return binding.root
    }

    private fun displayShoes(shoes: List<Shoe>) {

        // Create a layout which will be used for each shoe
        val shoeLayout =
            LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.MATCH_PARENT,
            )

        shoeLayout.bottomMargin = resources.getDimension(R.dimen.small_margin).toInt()

        // Show all the shoes in the shoe store including any the user added
        for (shoe in shoes) {

            var shoeInfoText = TextView(context);
            shoeInfoText.layoutParams = shoeLayout

            // format the display of all the information for a shoe
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