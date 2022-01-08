package uk.ac.aber.dcs.cs31620.quizapp.fragments.student.chooseQuestionBankQuiz

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.preference.PreferenceManager
import uk.ac.aber.dcs.cs31620.quizapp.databinding.FragmentQuestionBankQuizViewBinding
import uk.ac.aber.dcs.cs31620.quizapp.datasource.viewmodel.QuestionViewModel
import uk.ac.aber.dcs.cs31620.quizapp.fragments.teacher.model.Question

class QuestionBankQuizView : Fragment() {

    private var index = -1
    private var questionNum = 0
    private var score = 0

    private var _binding: FragmentQuestionBankQuizViewBinding? = null
    private val binding get() = _binding!!

    private lateinit var qUserViewModel: QuestionViewModel
    private val questionList = emptyList<Question>().toMutableList()

    private val args by navArgs<QuestionBankQuizViewArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentQuestionBankQuizViewBinding.inflate(inflater, container, false)
        qUserViewModel = ViewModelProvider(this)[QuestionViewModel::class.java]

        binding.endQuizButtonChoose.setOnClickListener {
            endQuizDialog()
        }

        binding.buttonConfirmNextChoose.isEnabled = false
        binding.buttonConfirmNextChoose.alpha = 0.5.toFloat()


        val question = getQuestions()
        question.observe(viewLifecycleOwner) { questions ->
            for (element in questions.shuffled()) {
                questionList += element
            }

            if(questionList.isNotEmpty()){
                execute(questionList)
            } else {
                val dialog = AlertDialog.Builder(context)
                dialog.setTitle("No Quiz For This Module At The Moment")
                dialog.setPositiveButton("Close") { dialog, _ ->
                    dialog?.dismiss()
                    findNavController().popBackStack()
                }
                dialog.show()
            }
        }


        return binding.root
    }


    private fun getQuestions(): LiveData<List<Question>> {
        val questionBankName = args.questionBank.questionBankName
        return qUserViewModel.getQuestionByQuestionBankName(questionBankName)
    }

    @SuppressLint("SetTextI18n")
    private fun execute(questionList: MutableList<Question>) {
        if (index == -1) {
            index++
            questionNum++
            val option = option(questionList[index])

            binding.numberOfQuestionChoose.text =
                questionNum.toString() + "/" + questionList.size.toString()
            binding.textViewQuestionChoose.text = questionList[index].question
            binding.listOptionsChoose.clearCheck()
            radioButton(option)
        } else {
            nextQuestion()
        }

        binding.buttonConfirmNextChoose.isEnabled = true
        binding.buttonConfirmNextChoose.alpha = 1.toFloat()

        binding.buttonConfirmNextChoose.setOnClickListener {
            if (index == -1) {
                index++
                questionNum++

                val option = option(questionList[index])

                binding.numberOfQuestionChoose.text = questionNum.toString() + "/" + questionList.size.toString()
                binding.textViewQuestionChoose.text = questionList[index].question
                binding.listOptionsChoose.clearCheck()
                radioButton(option)

            } else {
                nextQuestion()
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun nextQuestion() {
        val selected = binding.listOptionsChoose.checkedRadioButtonId

        if (index < questionList.size) {
//            Log.d("selected", "$selected")
//            Log.d("answer", "${questionList[index].questionAnswer}")
            if (selected == questionList[index].questionAnswer) {
                score++
            }
        }

        index++
        questionNum++
        binding.listOptionsChoose.removeAllViews()

        if (index < questionList.size) {
            val option = option(questionList[index])
            binding.numberOfQuestionChoose.text =
                questionNum.toString() + "/" + questionList.size.toString()
            binding.textViewQuestionChoose.text = questionList[index].question
            radioButton(option)
            binding.listOptionsChoose.clearCheck()
            if ((index + 1) == questionList.size)
                binding.buttonConfirmNextChoose.text = "Finish"
        } else {
            val dialog = AlertDialog.Builder(context)
            dialog.setTitle("Your Score")
            dialog.setMessage("You have scored " + score + " out of " + questionList.size + " questions.")
            dialog.setPositiveButton("Close") { dialog, _ ->
                dialog?.dismiss()
                findNavController().popBackStack()
            }
            dialog.show()
        }
    }


    private fun radioButton(option: MutableList<String>) {
        for (j in option.indices) {
            val radioButton = RadioButton(this.context)
            radioButton.text = option[j]
            radioButton.id = j + 1
            binding.listOptionsChoose.addView(radioButton)
        }
    }


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