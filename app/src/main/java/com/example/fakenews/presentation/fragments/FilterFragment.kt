package com.example.fakenews.presentation.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.fakenews.R
import com.example.fakenews.data.constants.Constants
import com.example.fakenews.databinding.FragmentFilterBinding
import com.example.fakenews.presentation.NewsFragmentViewModel
import com.example.fakenews.presentation.recycler.News
import com.example.fakenews.presentation.recycler.NewsAdapter
import org.koin.android.viewmodel.ext.android.viewModel

class FilterFragment : Fragment(R.layout.fragment_filter) {

    private val adapter by lazy { NewsAdapter() }
    private val viewModel: NewsFragmentViewModel by viewModel()
    private val binding: FragmentFilterBinding by viewBinding()
    private var copyDataSource: List<News>? = listOf()

    private val navArgs by navArgs<FilterFragmentArgs>()

    private fun passFilter(): List<News> {
        val navF = navArgs.transmitNavDataFilterFragmentArgs.filter
        val navG = navArgs.transmitNavDataFilterFragmentArgs.groupInfo
        if (Constants.TOPIC_GROUP_INFO == navG) return copyDataSource!!.filter { it.topic == navF }
        if (Constants.AUTHOR_GROUP_INFO == navG) return copyDataSource!!.filter { it.author == navF }
        if (Constants.DATE_GROUP_INFO == navG) return copyDataSource!!.filter { it.date == navF }
        return emptyList()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.listFilterFragment.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        viewModel.newsList()
        binding.listFilterFragment.adapter = adapter

        copyDataSource = viewModel.newsList.value
        adapter.submitList(passFilter())
    }

    override fun onStart() {
        super.onStart()
        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(FilterFragmentDirections.actionFilterFragmentToNewsFragment())
        }
    }
}