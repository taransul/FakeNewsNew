package com.example.fakenews.presentation

import android.animation.ValueAnimator
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.fakenews.R
import com.example.fakenews.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    private val binding: ActivityMainBinding by viewBinding()

    var animator: ValueAnimator = ValueAnimator.ofFloat(-360f, 360f)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        swingHeaderViewActivity()
    }

    private fun swingHeaderViewActivity() {
        animator.addUpdateListener { animation ->
            val animatedValue = animation.animatedValue as Float
            binding.headerView.rotationY = animatedValue
            binding.headerView.translationX = animatedValue
        }
        animator.duration = 3000
        animator.repeatCount = ValueAnimator.INFINITE
        animator.start()
        animator.repeatMode = ValueAnimator.REVERSE
    }
}