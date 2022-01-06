package uk.ac.aber.dcs.cs31620.quizapp.fragments.student.randomQuiz

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.preference.PreferenceManager
import uk.ac.aber.dcs.cs31620.quizapp.R
import uk.ac.aber.dcs.cs31620.quizapp.databinding.FragmentQuizViewBinding
import uk.ac.aber.dcs.cs31620.quizapp.datasource.viewmodel.QuestionViewModel
import uk.ac.aber.dcs.cs31620.quizapp.fragments.teacher.model.Question


class QuizView : Fragment() {

    private var index = -1
    private var score = 0

    private var _binding: FragmentQuizViewBinding? = null
    private val binding get() = _binding!!

    private lateinit var qUserViewModel: QuestionViewModel
    private val questionList = emptyList<Question>().toMutableList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentQuizViewBinding.inflate(inflater, null, false)

        qUserViewModel = ViewModelProvider(this)[QuestionViewModel::class.java]

        binding.buttonConfirmNext.isEnabled = false
        binding.buttonConfirmNext.alpha = 0.5.toFloat()

        getQuestions()

        return binding.root
    }


    private fun getQuestions() {

        qUserViewModel.readAllData.observe(
            viewLifecycleOwner
        )
        { array ->
            for (element in array.shuffled()) {
                questionList += element
            }
            execute(questionList)
        }


    }

    private fun execute(questionList: MutableList<Question>) {
        if (index == -1) {
            index++
            binding.listOptions.removeAllViews()
            Log.d("question", "${questionList[index]}")
            val option = option(questionList[index])

            Log.d("option", "$option")
            binding.textViewQuestion.text = questionList[index].question
            for (j in option.indices) {
                val radioButton = RadioButton(this.context)
                radioButton.text = option[j]
                radioButton.id = View.generateViewId()
                binding.listOptions.addView(radioButton)
            }
        } else {
            nextQuestion()
        }

        binding.buttonConfirmNext.isEnabled = true
        binding.buttonConfirmNext.alpha = 1.toFloat()

        binding.buttonConfirmNext.setOnClickListener {
            if (index == -1) {
                index++
                binding.listOptions.removeAllViews()

                val option = option(questionList[index])

                binding.textViewQuestion.text = questionList[index].question
                for (j in option.indices) {
                    val radioButton = RadioButton(this.context)
                    radioButton.text = option[j]
                    radioButton.id = View.generateViewId()
                    binding.listOptions.addView(radioButton)
                }
            } else {
                nextQuestion()
            }
        }
    }

    private fun nextQuestion() {
        val selected = binding.listOptions.checkedRadioButtonId
        if (selected == -1) {
            Toast.makeText(this.context, "Please select an option", Toast.LENGTH_SHORT).show()
            return
        }

        if(index < questionList.size){
                if(selected == questionList[index].questionAnswer){
                    score++
                }
        }
        index++
        binding.listOptions.removeAllViews()
        if(index < questionList.size){
            val option = option(questionList[index])
            binding.textViewQuestion.text = questionList[index].question
            for (j in option.indices) {
                val radioButton = RadioButton(this.context)
                radioButton.text = option[j]
                radioButton.id = View.generateViewId()
                binding.listOptions.addView(radioButton)
            }
            binding.listOptions.clearCheck()
            if((index+1) == questionList.size)
                binding.buttonConfirmNext.text = "Finish"
        } else {
            val preferences = PreferenceManager.getDefaultSharedPreferences(context)
            val editor = preferences.edit()
            editor.putInt("pastScore", score)
        }
    }

    private fun option(question: Question): MutableList<String> {
        val option: MutableList<String> = ArrayList()

        if (!TextUtils.isEmpty(question.optionAns1)) {
            option.add(question.optionAns1)
        }
        if (!TextUtils.isEmpty(question.optionAns2)) {
            option.add(question.optionAns2)
        }
        if (!TextUtils.isEmpty(question.optionAns3)) {
            option.add(question.optionAns3)
        }
        if (!TextUtils.isEmpty(question.optionAns4)) {
            option.add(question.optionAns4)
        }
        if (!TextUtils.isEmpty(question.optionAns5)) {
            option.add(question.optionAns5)
        }
        if (!TextUtils.isEmpty(question.optionAns6)) {
            option.add(question.optionAns6)
        }
        if (!TextUtils.isEmpty(question.optionAns7)) {
            option.add(question.optionAns7)
        }
        if (!TextUtils.isEmpty(question.optionAns8)) {
            option.add(question.optionAns8)
        }
        if (!TextUtils.isEmpty(question.optionAns9)) {
            option.add(question.optionAns9)
        }
        if (!TextUtils.isEmpty(question.optionAns10)) {
            option.add(question.optionAns10)
        }

        return option
    }

}