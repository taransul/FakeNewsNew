package com.example.fakenews.presentation.fragments

import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.fakenews.R
import com.example.fakenews.databinding.FragmentTestTextViewBinding

class TestTextHeaderFragment : Fragment(R.layout.fragment_test_text_view) {

    private val binding: FragmentTestTextViewBinding by viewBinding(FragmentTestTextViewBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Toast.makeText(context, "open fragment \"testTextView\" + animation", Toast.LENGTH_SHORT)
            .show()
    }

    override fun onStart() {
        super.onStart()
        binding.buttonHome.setOnClickListener {
            findNavController().navigate(TestTextHeaderFragmentDirections.actionTestTextViewFragmentToNewsFragment())
        }
        binding.buttonStart.setOnClickListener {
            binding.headerView.startAnimation()
        }
    }
}