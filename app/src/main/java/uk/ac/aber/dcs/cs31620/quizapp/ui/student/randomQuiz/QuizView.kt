package uk.ac.aber.dcs.cs31620.quizapp.ui.student.randomQuiz

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import uk.ac.aber.dcs.cs31620.quizapp.databinding.FragmentQuizViewBinding
import uk.ac.aber.dcs.cs31620.quizapp.datasource.viewmodel.QuestionViewModel
import uk.ac.aber.dcs.cs31620.quizapp.model.Question


class QuizView : Fragment() {

    private var index = -1
    private var questionNum = 0
    private var score = 0

    private var _binding: FragmentQuizViewBinding? = null
    private val binding get() = _binding!!

    private lateinit var qUserViewModel: QuestionViewModel
    private val questionList = emptyList<Question>().toMutableList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentQuizViewBinding.inflate(inflater, null, false)

        qUserViewModel = ViewModelProvider(this)[QuestionViewModel::class.java]

        binding.endQuizButton.setOnClickListener {
            endQuizDialog()
        }

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

            if(questionList.isNotEmpty()){
                execute(questionList)
            } else {
                noQuizDialog()
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun execute(questionList: MutableList<Question>) {
        if (index == -1) {
            index++
            questionNum++

            val option = option(questionList[index])

            binding.numberOfQuestion.text = questionNum.toString() + "/" + questionList.size.toString()
            binding.textViewQuestion.text = questionList[index].question
            binding.listOptions.clearCheck()
            radioButton(option)
        } else {
            nextQuestion()
        }

        binding.buttonConfirmNext.isEnabled = true
        binding.buttonConfirmNext.alpha = 1.toFloat()

        binding.buttonConfirmNext.setOnClickListener {
            if (index == -1) {
                index++
                questionNum++

                val option = option(questionList[index])

                binding.numberOfQuestion.text = questionNum.toString() + "/" + questionList.size.toString()
                binding.textViewQuestion.text = questionList[index].question
                binding.listOptions.clearCheck()
                radioButton(option)

            } else {
                nextQuestion()
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun nextQuestion() {
        val selected = binding.listOptions.checkedRadioButtonId

        if (index < questionList.size) {
            if (selected == questionList[index].questionAnswer) {
                score++
            }
        }

        index++
        questionNum++
        binding.listOptions.removeAllViews()

        if (index < questionList.size) {
            val option = option(questionList[index])
            binding.numberOfQuestion.text = questionNum.toString() + "/" + questionList.size.toString()
            binding.textViewQuestion.text = questionList[index].question
            radioButton(option)
            binding.listOptions.clearCheck()
            if ((index + 1) == questionList.size)
                binding.buttonConfirmNext.text = "Finish"
        } else {
            scoreDialog()
        }
    }

    // Add radio button based on how many option answers the question have
    private fun radioButton(option: MutableList<String>) {
        for (j in option.indices) {
            val radioButton = RadioButton(this.context)
            radioButton.text = option[j]
            radioButton.id = j + 1
            binding.listOptions.addView(radioButton)
        }
    }

    // Score dialog
    private fun scoreDialog(){
        val dialog = AlertDialog.Builder(context)
        dialog.setTitle("Your Score")
        dialog.setMessage("You have scored " + score + " out of " + questionList.size + " questions.")
        dialog.setPositiveButton("Close") { dialog, _ ->
            dialog?.dismiss()
            findNavController().popBackStack()
        }
        dialog.show()
    }

    // No quiz dialog
    private fun noQuizDialog(){
        val dialog = AlertDialog.Builder(context)
        dialog.setTitle("No Quiz At The Moment")
        dialog.setPositiveButton("Close") { dialog, _ ->
            dialog?.dismiss()
            findNavController().popBackStack()
        }
        dialog.show()
    }

    // End quiz dialog
    private fun endQuizDialog() {
        val dialog = AlertDialog.Builder(context)
        dialog.setTitle("End Quiz")
        dialog.setMessage("You will not get a score by ending the quiz halfway. Are you sure?")
        dialog.setPositiveButton("Yes") { dialog, _ ->
            dialog?.dismiss()
            findNavController().popBackStack()
        }
        dialog.setNegativeButton("No") { dialog, _ -> dialog?.dismiss() }
        dialog.show()
    }

    // Get the list of option answers from question
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