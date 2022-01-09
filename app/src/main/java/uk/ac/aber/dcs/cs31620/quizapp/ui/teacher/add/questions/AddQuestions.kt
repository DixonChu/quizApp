package uk.ac.aber.dcs.cs31620.quizapp.ui.teacher.add.questions

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import uk.ac.aber.dcs.cs31620.quizapp.databinding.FragmentAddQuestionsBinding
import uk.ac.aber.dcs.cs31620.quizapp.model.Question
import uk.ac.aber.dcs.cs31620.quizapp.datasource.viewmodel.QuestionViewModel


class AddQuestions : Fragment() {

    private lateinit var qUserViewModel: QuestionViewModel

    private var _binding: FragmentAddQuestionsBinding? = null
    private val binding get() = _binding!!

    private val args by navArgs<AddQuestionsArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentAddQuestionsBinding.inflate(inflater, container, false)

        qUserViewModel = ViewModelProvider(this)[QuestionViewModel::class.java]

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

        if (inputCheck(questionName, questionAnswer) && checkOptionAnswer(questionAnswer,optionAns1,optionAns2,optionAns3,optionAns4,optionAns5,optionAns6,optionAns7,optionAns8,optionAns9,optionAns10)) {
            val question = Question(
                0,
                args.currentModuleName,
                args.currentQuestionName,
                questionName,
                questionAnswer.toInt(),
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
            Toast.makeText(requireContext(), "Successfully Added!", Toast.LENGTH_SHORT).show()
        } else {
            if((questionAnswer.toInt() < 0) || (questionAnswer.toInt() > 11)){
                Toast.makeText(requireContext(), "Please input only 1 to 10 for the correct answer field", Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(requireContext(), "Please fill in the fields correctly", Toast.LENGTH_SHORT).show()
            }

        }

    }

    // Check important field which cannot be empty
    private fun inputCheck(questionName: String, questionAnswer: String): Boolean {
        return !(TextUtils.isEmpty(questionName)) && (questionAnswer.toInt() > 0) && (questionAnswer.toInt() < 11)
    }

    // If question answer is n, then option n cannot be empty
    private fun checkOptionAnswer(questionAnswer: String, optionAns1: String, optionAns2: String, optionAns3: String, optionAns4: String, optionAns5: String, optionAns6: String, optionAns7: String, optionAns8: String, optionAns9: String, optionAns10: String): Boolean {
        if(questionAnswer.toInt() == 1 && optionAns1.isEmpty()){
            Toast.makeText(requireContext(), "Please fill option 1 answer", Toast.LENGTH_SHORT).show()
            return false
        }
        if(questionAnswer.toInt() == 2 && optionAns2.isEmpty()){
            Toast.makeText(requireContext(), "Please fill option 2 answer", Toast.LENGTH_SHORT).show()
            return false
        }
        if(questionAnswer.toInt() == 3 && optionAns3.isEmpty()){
            Toast.makeText(requireContext(), "Please fill option 3 answer", Toast.LENGTH_SHORT).show()
            return false
        }
        if(questionAnswer.toInt() == 4 && optionAns4.isEmpty()){
            Toast.makeText(requireContext(), "Please fill option 4 answer", Toast.LENGTH_SHORT).show()
            return false
        }
        if(questionAnswer.toInt() == 5 && optionAns5.isEmpty()){
            Toast.makeText(requireContext(), "Please fill option 5 answer", Toast.LENGTH_SHORT).show()
            return false
        }
        if(questionAnswer.toInt() == 6 && optionAns6.isEmpty()){
            Toast.makeText(requireContext(), "Please fill option 6 answer", Toast.LENGTH_SHORT).show()
            return false
        }
        if(questionAnswer.toInt() == 7 && optionAns7.isEmpty()){
            Toast.makeText(requireContext(), "Please fill option 7 answer", Toast.LENGTH_SHORT).show()
            return false
        }
        if(questionAnswer.toInt() == 8 && optionAns8.isEmpty()){
            Toast.makeText(requireContext(), "Please fill option 8 answer", Toast.LENGTH_SHORT).show()
            return false
        }
        if(questionAnswer.toInt() == 9 && optionAns9.isEmpty()){
            Toast.makeText(requireContext(), "Please fill option 9 answer", Toast.LENGTH_SHORT).show()
            return false
        }
        if(questionAnswer.toInt() == 10 && optionAns10.isEmpty()){
            Toast.makeText(requireContext(), "Please fill option 10 answer", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }


}