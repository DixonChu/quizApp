package uk.ac.aber.dcs.cs31620.quizapp.fragments.teacher.add.questions

import android.graphics.Color
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import uk.ac.aber.dcs.cs31620.quizapp.R
import uk.ac.aber.dcs.cs31620.quizapp.databinding.FragmentAddQuestionsBinding
import uk.ac.aber.dcs.cs31620.quizapp.datasource.module.ModuleDatabase
import uk.ac.aber.dcs.cs31620.quizapp.fragments.teacher.add.questionBanks.AddQuestionBanksArgs
import uk.ac.aber.dcs.cs31620.quizapp.fragments.teacher.model.Question
import uk.ac.aber.dcs.cs31620.quizapp.fragments.teacher.viewmodel.QuestionViewModel


class AddQuestions : Fragment() {

    private lateinit var qUserViewModel: QuestionViewModel
    private lateinit var layoutInflater: LinearLayout.LayoutParams
    private lateinit var linearLayout: LinearLayout

    private var _binding: FragmentAddQuestionsBinding? = null
    private val binding get() = _binding!!

    private val args by navArgs<AddQuestionsArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAddQuestionsBinding.inflate(inflater, container, false)

        qUserViewModel = ViewModelProvider(this).get(QuestionViewModel::class.java)

        binding.addQuestionBtn.setOnClickListener {
            insertToDatabase()
        }

        return binding.root
    }

    private fun insertToDatabase() {
        val questionName = binding.addQuestionName.text.toString()
        val questionAnswer = binding.addQuestionAnswer.text.toString()
        val optionAns1 = binding.optionAnswer1.text.toString()
        val optionAns2 = binding.optionAnswer2.text.toString()
        val optionAns3 = binding.optionAnswer3.text.toString()
        val optionAns4 = binding.optionAnswer4.text.toString()
        val optionAns5 = binding.optionAnswer5.text.toString()
        val optionAns6 = binding.optionAnswer6.text.toString()
        val optionAns7 = binding.optionAnswer7.text.toString()
        val optionAns8 = binding.optionAnswer8.text.toString()
        val optionAns9 = binding.optionAnswer9.text.toString()
        val optionAns10 = binding.optionAnswer10.text.toString()

        if (inputCheck(questionName, questionAnswer)) {
            val question = Question(
                0,
                args.currentQuestionName,
                questionName,
                questionAnswer,
                optionAns1,
                optionAns2,
                optionAns3,
                optionAns4,
                optionAns5,
                optionAns6,
                optionAns7,
                optionAns8,
                optionAns9,
                optionAns10
            )

            qUserViewModel.addQuestion(question)
            findNavController().popBackStack()
            Toast.makeText(requireContext(), "Successfully Added!", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(
                requireContext(),
                "Please fill in Question Name and Correct answer fields",
                Toast.LENGTH_LONG
            ).show()

        }

    }

    private fun inputCheck(
        questionName: String, questionAnswer: String
    ): Boolean {
        return !(TextUtils.isEmpty(questionName)) && !(TextUtils.isEmpty(questionAnswer))
    }


}