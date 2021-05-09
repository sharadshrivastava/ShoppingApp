package com.test.app.ui.products

import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.test.app.R
import com.test.app.data.network.Resource.Status.*
import com.test.app.databinding.FragmentListBinding
import com.test.app.domain.model.Product
import com.test.app.ui.common.ItemClickListener
import com.test.app.ui.common.showErrorBar
import com.test.app.ui.common.showToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_list.*
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class ProductsListFragment : Fragment(), ItemClickListener {

    private val productsListViewModel: ProductsListViewModel by viewModels()
    private lateinit var binding: FragmentListBinding
    private var cartBadge: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentListBinding.inflate(inflater, container, false).apply {
            binding = this
            lifecycleOwner = this@ProductsListFragment
            vm = this@ProductsListFragment.productsListViewModel
            clickListener = this@ProductsListFragment
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupDivider()
        observeProducts()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)
        val menuItem = menu.findItem(R.id.cart)
        cartBadge = menuItem.actionView.findViewById(R.id.cart_badge)
        observeCartCount() //observing only after inflating menu otherwise badge count will be 0 always.

        menuItem.actionView.setOnClickListener {
            onOptionsItemSelected(menuItem)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.cart -> {
                showToast("Cart")
                findNavController().navigate(R.id.listFragmentAction)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setupDivider() {
        val itemDecorator = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        ContextCompat.getDrawable(requireContext(), R.drawable.divider)
            ?.let { itemDecorator.setDrawable(it) }
        productsList.addItemDecoration(itemDecorator)
    }

    private fun observeProducts() {
        productsListViewModel.products.observe(viewLifecycleOwner) {
            when (it.status) {
                LOADING -> binding.isLoading = true
                SUCCESS -> handleResponse(isEmpty = it.data?.size == 0)
                ERROR -> handleResponse(false, it.message)
            }
        }
    }

    private fun handleResponse(
        isSuccess: Boolean = true,
        msg: String? = null,
        isEmpty: Boolean = false
    ) {
        binding.isLoading = false

        if (isSuccess) {
            binding.isEmpty = isEmpty
        } else {
            showErrorBar(msg)
            binding.isEmpty = true
        }
    }

    override fun onItemClick(item: Any?) {
        if (item is Product) {
            showToast("Added To Cart")
            lifecycleScope.launchWhenStarted {
                productsListViewModel.insertProduct(item.toCartProduct())
            }
        }
    }

    private fun observeCartCount() {
        lifecycleScope.launchWhenStarted {
            productsListViewModel.entryCount()?.collect {
                cartBadge?.text = (it).toString()
            }
        }
    }
}