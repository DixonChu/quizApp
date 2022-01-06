package uk.ac.aber.dcs.cs31620.quizapp.fragments.teacher.update.questions

import android.app.AlertDialog
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import uk.ac.aber.dcs.cs31620.quizapp.R
import uk.ac.aber.dcs.cs31620.quizapp.databinding.FragmentUpdateQuestionsBinding
import uk.ac.aber.dcs.cs31620.quizapp.fragments.teacher.model.Question
import uk.ac.aber.dcs.cs31620.quizapp.datasource.viewmodel.QuestionViewModel

class UpdateQuestions : Fragment() {

    private val args by navArgs<UpdateQuestionsArgs>()

    private lateinit var qUserViewModel: QuestionViewModel
    private var _binding: FragmentUpdateQuestionsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentUpdateQuestionsBinding.inflate(inflater, container, false)

        qUserViewModel = ViewModelProvider(this)[QuestionViewModel::class.java]

        binding.updateQuestionName.setText(args.currentQuestion.question)
        binding.updateQuestionAnswer.setText(args.currentQuestion.questionAnswer.toString())
        binding.updateOptionAnswer1.setText(args.currentQuestion.optionAns1)
        binding.updateOptionAnswer2.setText(args.currentQuestion.optionAns2)
        binding.updateOptionAnswer3.setText(args.currentQuestion.optionAns3)
        binding.updateOptionAnswer4.setText(args.currentQuestion.optionAns4)
        binding.updateOptionAnswer5.setText(args.currentQuestion.optionAns5)
        binding.updateOptionAnswer6.setText(args.currentQuestion.optionAns6)
        binding.updateOptionAnswer7.setText(args.currentQuestion.optionAns7)
        binding.updateOptionAnswer8.setText(args.currentQuestion.optionAns8)
        binding.updateOptionAnswer9.setText(args.currentQuestion.optionAns9)
        binding.updateOptionAnswer10.setText(args.currentQuestion.optionAns10)

        binding.updateQuestionBtn.setOnClickListener {
            updateItem()
        }

        setHasOptionsMenu(true)

        return binding.root
    }

    private fun updateItem() {
        val questionName = binding.updateQuestionName.text.toString()
        val questionAnswer = binding.updateQuestionAnswer.text.toString()
        val optionAns1 = binding.updateOptionAnswer1.text.toString()
        val optionAns2 = binding.updateOptionAnswer2.text.toString()
        val optionAns3 = binding.updateOptionAnswer3.text.toString()
        val optionAns4 = binding.updateOptionAnswer4.text.toString()
        val optionAns5 = binding.updateOptionAnswer5.text.toString()
        val optionAns6 = binding.updateOptionAnswer6.text.toString()
        val optionAns7 = binding.updateOptionAnswer7.text.toString()
        val optionAns8 = binding.updateOptionAnswer8.text.toString()
        val optionAns9 = binding.updateOptionAnswer9.text.toString()
        val optionAns10 = binding.updateOptionAnswer10.text.toString()


        if (inputCheck(questionName, questionAnswer)) {
            val updatedQuestion = Question(
                args.currentQuestion.id,
                args.currentQuestion.moduleName,
                args.currentQuestion.questionBankName,
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
                optionAns10)

            qUserViewModel.updateQuestion(updatedQuestion)
//            findNavController().navigate(R.id.action_updateQuestions_to_questions)
            findNavController().popBackStack()
            Toast.makeText(requireContext(), "Question update successfully", Toast.LENGTH_SHORT).show()


        }

    }

    private fun inputCheck(questionName: String, questionAnswer: String): Boolean {
        return !(TextUtils.isEmpty(questionName) && TextUtils.isEmpty(questionAnswer))
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_delete) {
            deleteQuestion()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteQuestion(){
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") { _, _ ->
        qUserViewModel.deleteQuestion(args.currentQuestion)
            Toast.makeText(requireContext(), "Successfully removed: ${args.currentQuestion.question}", Toast.LENGTH_LONG).show()
            findNavController().popBackStack()
        }
        builder.setNegativeButton("No") {_, _ -> }
        builder.setTitle("Delete ${args.currentQuestion.question}?")
        builder.setMessage("Are you sure you want to delete ${args.currentQuestion.question}?")
        builder.create().show()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}