package com.example.fakenews.view.header

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.animation.LinearInterpolator
import androidx.appcompat.widget.AppCompatTextView
import com.example.fakenews.R

class HeaderView(context: Context, attrs: AttributeSet?) :
    AppCompatTextView(context, attrs) {

    companion object {
        private const val LENGTH_SIDE_SQUARE = 400f
        private const val ROUND_RECT_RADIUS = 120f
        private const val DEFAULT_TITLE_TEXT_SIZE = 32f
        private const val DEFAULT_SUBTITLE_TEXT_SIZE = DEFAULT_TITLE_TEXT_SIZE / 2
        private const val CIRCLE_START_ANGLE = 90f
        private const val DEFAULT_COLOR = Color.BLACK
        private const val EMPTY_STRING = ""
        private const val NUMBER_OF_ANIMATION_CYCLES = 3
    }

    private var titleText: String = EMPTY_STRING
    private var titleTextColor: Int = DEFAULT_COLOR
    private var titleTextSize: Float = DEFAULT_TITLE_TEXT_SIZE

    private var subTitleText: String = EMPTY_STRING
    private var subTitleTextColor: Int = DEFAULT_COLOR
    private var subTitleTextSize: Float = DEFAULT_SUBTITLE_TEXT_SIZE

    private var squareColor: Int = DEFAULT_COLOR
    private var circleColor: Int = DEFAULT_COLOR


    private var titleCoordinates: Pair<Float, Float> =
        x to y
    private var subTitleCoordinates: Pair<Float, Float> =
        x to y
    private var squareLeftTopPoint: Pair<Float, Float> =
        x to y
    private var squareRightBottomPoint: Pair<Float, Float> =
        LENGTH_SIDE_SQUARE to LENGTH_SIDE_SQUARE

    private var circleSweepAngle = 0f
    private var circleDrawDuration: Int = 0
    private var animator = ValueAnimator.ofFloat(0f, 360f)
    private var animatorText = ValueAnimator.ofFloat(-180f, 180f)

    init {

        context.theme.obtainStyledAttributes(attrs, R.styleable.HeaderView, 0, 0)
            .apply {
                titleText = getString(R.styleable.HeaderView_titleText).orEmpty()
                titleTextColor = getColor(R.styleable.HeaderView_titleTextColor, 0)
                titleTextSize =
                    getDimension(R.styleable.HeaderView_titleTextSize, DEFAULT_TITLE_TEXT_SIZE)
                subTitleText = getString(R.styleable.HeaderView_subTitleText).orEmpty()
                subTitleTextColor = getColor(R.styleable.HeaderView_subTitleTextColor, 0)
                subTitleTextSize = getDimension(
                    R.styleable.HeaderView_subTitleTextSize,
                    DEFAULT_SUBTITLE_TEXT_SIZE
                )
                squareColor = getColor(R.styleable.HeaderView_squareColor, 0)
                circleColor = getColor(R.styleable.HeaderView_circleColor, 0)
                circleDrawDuration = getInteger(R.styleable.HeaderView_circleDrawDuration, 0)
            }
    }

    private val titlePaint = Paint().apply {
        color = titleTextColor
        textSize = titleTextSize
        textAlign = Paint.Align.CENTER
    }
    private val subTitlePaint = Paint().apply {
        color = subTitleTextColor
        textSize = subTitleTextSize
        textAlign = Paint.Align.CENTER
    }

    private val squarePaint = Paint().apply {
        color = squareColor

    }
    private val circlePaint = Paint().apply {
        color = circleColor
        style = Paint.Style.FILL
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        resolveSubTitleTextSize()
        titleCoordinates = calculateTitleCoordinates(0f)
        subTitleCoordinates = calculateSubTitleCoordinates(0f)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawText(
            titleText,
            titleCoordinates.first,
            titleCoordinates.second,
            titlePaint
        )
        canvas?.drawText(
            subTitleText,
            subTitleCoordinates.first,
            subTitleCoordinates.second,
            subTitlePaint
        )
        canvas?.drawRoundRect(
            squareLeftTopPoint.first + (width / 2 - LENGTH_SIDE_SQUARE / 2),
            squareLeftTopPoint.second,
            squareRightBottomPoint.first + (width / 2 - LENGTH_SIDE_SQUARE / 2),
            squareRightBottomPoint.second,
            ROUND_RECT_RADIUS,
            ROUND_RECT_RADIUS,
            squarePaint
        )

        canvas?.drawArc(
            squareLeftTopPoint.first + (width / 2 - LENGTH_SIDE_SQUARE / 2),
            squareLeftTopPoint.second,
            squareRightBottomPoint.first + (width / 2 - LENGTH_SIDE_SQUARE / 2),
            squareRightBottomPoint.second,
            CIRCLE_START_ANGLE,
            circleSweepAngle,
            true,
            circlePaint
        )
    }

    private fun resolveSubTitleTextSize() {
        if (subTitlePaint.textSize >= titlePaint.textSize / 2) {
            subTitlePaint.textSize = titlePaint.textSize / 2
        }
    }

    private fun calculateTitleCoordinates(animator: Float): Pair<Float, Float> {
        return (x + width / 2f - (titlePaint.descent() + titlePaint.ascent()) / 2f) + animator to y + height / 2f
    }

    private fun calculateSubTitleCoordinates(animator: Float): Pair<Float, Float> {
        return (x + width / 2f - (subTitlePaint.descent() + titlePaint.ascent()) / 2f) - animator to y + height / 2f + titlePaint.textSize
    }

    fun startAnimation() {
        animator.addUpdateListener { animation ->
            val animatedValue = animation.animatedValue as Float
            circleSweepAngle = animatedValue
            invalidate()
        }
        animatorText.addUpdateListener { animation ->
            val animatedValue = animation.animatedValue as Float
            titleCoordinates = calculateTitleCoordinates(animatedValue)
            subTitleCoordinates = calculateSubTitleCoordinates(animatedValue)
            invalidate()
        }

        animator.interpolator = LinearInterpolator()
        animator.duration = circleDrawDuration.toLong()
        animator.repeatCount = NUMBER_OF_ANIMATION_CYCLES * 2 - 1
        animator.start()
        animator.repeatMode = ValueAnimator.REVERSE

        animatorText.interpolator = LinearInterpolator()
        animatorText.duration = circleDrawDuration.toLong()
        animatorText.repeatCount = NUMBER_OF_ANIMATION_CYCLES * 2 - 1
        animatorText.start()
        animatorText.repeatMode = ValueAnimator.REVERSE
    }
}