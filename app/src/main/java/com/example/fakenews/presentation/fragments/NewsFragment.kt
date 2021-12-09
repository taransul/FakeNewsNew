package com.example.fakenews.presentation.fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.fakenews.R
import com.example.fakenews.databinding.Fragment1Binding
import com.example.fakenews.presentation.NewsFragmentViewModel
import com.example.fakenews.presentation.NewsViewModelRoom
import com.example.fakenews.presentation.PassesList
import com.example.fakenews.presentation.recycler.News
import com.example.fakenews.presentation.recycler.NewsAdapter
import org.koin.android.viewmodel.ext.android.viewModel

class NewsFragment : Fragment(R.layout.fragment1) {

    private val adapter by lazy { NewsAdapter() }
    private val viewModel by viewModel<NewsFragmentViewModel>()
    private val userViewModel by viewModel<NewsViewModelRoom>()

    val binding: Fragment1Binding by viewBinding(Fragment1Binding::bind)

    private val passesList: PassesList =
        object : PassesList {
            override fun displaysListOnScreen(filter: List<News>, selectionInformation: String) {
                viewModel.loadMessages(filter)
                binding.textViewInfo.text = selectionInformation
            }
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.list.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        initObserves(viewModel.passesListViewModel)
        initObserves(viewModel.newsList)
        viewModel.newsList()
        binding.list.adapter = adapter

        binding.imageView.setOnClickListener {
            findNavController().navigate(R.id.action_newsFragment_to_radioGroupFragment)

        }
    }

    override fun onStart() {
        super.onStart()
        binding.floatingActionButtonInsertLoad.setOnClickListener {
            saveNews()
        }
    }

    private fun saveNews() {
        userViewModel.insertNews()
        userViewModel.loadNews()
        userViewModel.newsLoad.observe(this) { news ->
            passesList.displaysListOnScreen(news, "local ")
            Toast.makeText(context, "Сохранено", Toast.LENGTH_SHORT).show()
        }
    }

    private fun initObserves(observeLiveData: LiveData<List<News>>) {
        observeLiveData.observe(viewLifecycleOwner) { list ->
            adapter.submitList(list)
        }
    }
}