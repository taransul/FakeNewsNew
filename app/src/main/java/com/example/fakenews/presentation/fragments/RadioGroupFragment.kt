package com.example.fakenews.presentation.fragments

import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.fakenews.R
import com.example.fakenews.data.constants.Constants
import com.example.fakenews.databinding.FragmentRadioGroupBinding
import com.example.fakenews.presentation.TransmitNavData
import com.example.fakenews.presentation.enums.AuthorEnum
import com.example.fakenews.presentation.enums.DateEnum
import com.example.fakenews.presentation.enums.TopicEnum

class RadioGroupFragment : Fragment(R.layout.fragment_radio_group) {

    private val binding: FragmentRadioGroupBinding by viewBinding(FragmentRadioGroupBinding::bind)

    override fun onResume() {
        super.onResume()
        initRadioGroup()
    }

    private fun initRadioGroup() {
        binding.apply {
            radioGroup.setOnCheckedChangeListener { _, checkedId ->
                when (checkedId) {
                    R.id.topicRadioButton -> {

                        dateRadioButton.isVisible = false
                        authorRadioButton.isVisible = false

                        radioGroup2.isVisible = true

                        radioButton1.text = TopicEnum.GAME.topic
                        radioButton2.text = TopicEnum.ECONOMY.topic
                        radioButton3.text = TopicEnum.TECHNOLOGIES.topic
                        radioButton4.text = TopicEnum.POLITICS.topic

                        radioGroup2.setOnCheckedChangeListener { _, checkedId2 ->
                            when (checkedId2) {
                                R.id.radioButton1 -> {
                                    passFilterTopic(
                                        TopicEnum.GAME.topic,
                                        Constants.TOPIC_GROUP_INFO
                                    )
                                }
                                R.id.radioButton2 -> {
                                    passFilterTopic(
                                        TopicEnum.ECONOMY.topic,
                                        Constants.TOPIC_GROUP_INFO
                                    )
                                }
                                R.id.radioButton3 -> {
                                    passFilterTopic(
                                        TopicEnum.TECHNOLOGIES.topic,
                                        Constants.TOPIC_GROUP_INFO
                                    )
                                }
                                R.id.radioButton4 -> {
                                    passFilterTopic(
                                        TopicEnum.POLITICS.topic,
                                        Constants.TOPIC_GROUP_INFO
                                    )
                                }
                            }
                        }
                    }

                    R.id.authorRadioButton -> {

                        dateRadioButton.isVisible = false
                        topicRadioButton.isVisible = false

                        radioGroup2.isVisible = true

                        radioButton1.text = AuthorEnum.AUTHOR1.author
                        radioButton2.text = AuthorEnum.AUTHOR2.author
                        radioButton3.text = AuthorEnum.AUTHOR3.author
                        radioButton4.text = AuthorEnum.AUTHOR4.author

                        radioGroup2.setOnCheckedChangeListener { _, checkedId2 ->
                            when (checkedId2) {
                                R.id.radioButton1 -> {
                                    passFilterTopic(
                                        AuthorEnum.AUTHOR1.author,
                                        Constants.AUTHOR_GROUP_INFO
                                    )
                                }
                                R.id.radioButton2 -> {
                                    passFilterTopic(
                                        AuthorEnum.AUTHOR2.author,
                                        Constants.AUTHOR_GROUP_INFO
                                    )
                                }
                                R.id.radioButton3 -> {
                                    passFilterTopic(
                                        AuthorEnum.AUTHOR3.author,
                                        Constants.AUTHOR_GROUP_INFO
                                    )
                                }
                                R.id.radioButton4 -> {
                                    passFilterTopic(
                                        AuthorEnum.AUTHOR4.author,
                                        Constants.AUTHOR_GROUP_INFO
                                    )
                                }
                            }
                        }
                    }

                    R.id.dateRadioButton -> {

                        authorRadioButton.isVisible = false
                        topicRadioButton.isVisible = false

                        radioGroup2.isVisible = true

                        radioButton1.text = DateEnum.TODAY.date
                        radioButton2.text = DateEnum.RECENTLY.date
                        radioButton3.text = DateEnum.WEEK.date
                        radioButton4.text = DateEnum.OLD.date

                        radioGroup2.setOnCheckedChangeListener { _, checkedId2 ->
                            when (checkedId2) {
                                R.id.radioButton1 -> {
                                    passFilterTopic(DateEnum.TODAY.date, Constants.DATE_GROUP_INFO)
                                }
                                R.id.radioButton2 -> {
                                    passFilterTopic(
                                        DateEnum.RECENTLY.date,
                                        Constants.DATE_GROUP_INFO
                                    )
                                }
                                R.id.radioButton3 -> {
                                    passFilterTopic(DateEnum.WEEK.date, Constants.DATE_GROUP_INFO)
                                }
                                R.id.radioButton4 -> {
                                    passFilterTopic(DateEnum.OLD.date, Constants.DATE_GROUP_INFO)
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private fun passFilterTopic(enumString: String, groupInfo: String) {
        findNavController().navigate(
            RadioGroupFragmentDirections.actionRadioGroupFragmentToFilterFragment(
                TransmitNavData(enumString, groupInfo)
            )
        )
        binding.radioGroup.clearCheck()
    }
}