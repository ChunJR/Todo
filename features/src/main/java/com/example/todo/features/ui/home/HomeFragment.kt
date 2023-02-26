package com.example.todo.features.ui.home

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.todo.core.domain.Contact
import com.example.todo.core.domain.Product
import com.example.todo.features.R
import com.example.todo.features.databinding.FragmentHomeBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModel()
    private var binding: FragmentHomeBinding? = null

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        observeDataChange()
    }

    private fun initView() {
        binding?.btnCall?.setOnClickListener{
            viewModel.getContacts()
        }
        binding?.btnBuy?.setOnClickListener{
            viewModel.getProducts()
        }
        binding?.btnSell?.setOnClickListener{
            viewModel.getProducts()
        }
    }

    private fun observeDataChange() {
        viewModel.state.observe(viewLifecycleOwner) {
            when (it) {
                is HomeViewState.onLoading -> {
                    binding?.progressBar?.visibility = View.VISIBLE
                }
                is HomeViewState.onSuccessGetContact -> {
                    binding?.progressBar?.visibility = View.GONE
                    moveToDetailScreen(it.contacts, null)
                }
                is HomeViewState.onSuccessGetProduct -> {
                    binding?.progressBar?.visibility = View.GONE
                    moveToDetailScreen(null, it.products)
                }
                is HomeViewState.onError -> {
                    binding?.progressBar?.visibility = View.GONE
                    AlertDialog.Builder(requireContext())
                        .setTitle(R.string.txt_error)
                        .setMessage(it.message)
                        .setPositiveButton(R.string.txt_dismiss) { dialog, _ ->
                            dialog.dismiss()
                        }
                        .show()
                }
            }
        }
    }

    private fun moveToDetailScreen(contacts: List<Contact>?, products: List<Product>?) {
        val args = Bundle()
        var bundle: Bundle? = null
        if (contacts != null) {
            bundle = bundleOf("contacts" to contacts)
        } else if (products != null) {
            bundle = bundleOf("products" to products)
        }
        findNavController().navigate(R.id.nav_details, bundle)
    }
}