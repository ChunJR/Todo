package com.example.todo.features.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todo.core.domain.Contact
import com.example.todo.core.domain.Product
import com.example.todo.features.R
import com.example.todo.features.databinding.FragmentDetailsBinding
import com.example.todo.features.ui.details.adapter.DetailsAdapter

class DetailsFragment : Fragment() {

    private var binding: FragmentDetailsBinding? = null
    private var contactList = java.util.ArrayList<Contact>()
    private var productList = java.util.ArrayList<Product>()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
        initView()
    }

    private fun initData() {
        if (arguments?.containsKey("contacts")!!) {
            contactList = arguments?.getParcelableArrayList("contacts")!!
        } else if (arguments?.containsKey("products")!!) {
            productList = arguments?.getParcelableArrayList("products")!!
        }
    }

    private fun initView() {
        val adapter = DetailsAdapter(contactList, productList)
        binding?.recyclerView?.layoutManager = LinearLayoutManager(requireContext())
        binding?.recyclerView?.adapter = adapter
        val divider = DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
        divider.setDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.line_divider)!!)
        binding?.recyclerView?.addItemDecoration(divider)
    }
}