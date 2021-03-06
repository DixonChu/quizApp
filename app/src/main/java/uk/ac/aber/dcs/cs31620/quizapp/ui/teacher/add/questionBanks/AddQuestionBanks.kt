package uk.ac.aber.dcs.cs31620.quizapp.ui.teacher.add.questionBanks

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
import uk.ac.aber.dcs.cs31620.quizapp.databinding.FragmentAddQuestionBanksBinding
import uk.ac.aber.dcs.cs31620.quizapp.model.QuestionBank
import uk.ac.aber.dcs.cs31620.quizapp.datasource.viewmodel.QuestionBankViewModel

class AddQuestionBanks : Fragment() {

    private lateinit var qbUserViewModel: QuestionBankViewModel

    private var _binding: FragmentAddQuestionBanksBinding? = null
    private val binding get() = _binding!!

    private val args by navArgs<AddQuestionBanksArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentAddQuestionBanksBinding.inflate(inflater, container, false)

        qbUserViewModel = ViewModelProvider(this)[QuestionBankViewModel::class.java]

        binding.addQuestionBankBtn.setOnClickListener {
            insertDataToDatabase()
        }

        return binding.root
    }

    // Add question bank to database
    private fun insertDataToDatabase() {
        val questionBankName = binding.addQuestionBankName.text.toString()
        val questionBankDescription = binding.addQuestionBankDescription.text.toString()

        if (inputCheck(questionBankName)) {
            val questionBank =
                QuestionBank(0, args.moduleName, questionBankName, questionBankDescription)
            qbUserViewModel.addQuestionBank(questionBank)
            findNavController().popBackStack()
            Toast.makeText(requireContext(), "Successfully Added!", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(requireContext(), "Please fill in question bank name field", Toast.LENGTH_SHORT)
                .show()
        }

    }

    // Check important field which cannot be empty
    private fun inputCheck(
        questionBankName: String
    ): Boolean {
        return !(TextUtils.isEmpty(questionBankName))
    }

}