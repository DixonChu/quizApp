package uk.ac.aber.dcs.cs31620.quizapp.ui.teacher.update.questionBanks

import android.app.AlertDialog
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import uk.ac.aber.dcs.cs31620.quizapp.R
import uk.ac.aber.dcs.cs31620.quizapp.databinding.FragmentUpdateQuestionBanksBinding
import uk.ac.aber.dcs.cs31620.quizapp.model.QuestionBank
import uk.ac.aber.dcs.cs31620.quizapp.datasource.viewmodel.QuestionBankViewModel
import uk.ac.aber.dcs.cs31620.quizapp.datasource.viewmodel.QuestionViewModel

class UpdateQuestionBanks : Fragment() {

    private val args by navArgs<UpdateQuestionBanksArgs>()

    private lateinit var qbUserViewModel: QuestionBankViewModel
    private lateinit var qUserViewModel: QuestionViewModel
    private var _binding: FragmentUpdateQuestionBanksBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentUpdateQuestionBanksBinding.inflate(inflater, container, false)

        qbUserViewModel = ViewModelProvider(this)[QuestionBankViewModel::class.java]
        qUserViewModel = ViewModelProvider(this)[QuestionViewModel::class.java]

        binding.updateQuestionBankName.setText(args.currentQuestionBank.questionBankName)
        binding.updateQuestionBanksDescription.setText(args.currentQuestionBank.questionBankDescription)

        binding.updateQuestionBankBtn.setOnClickListener {
            updateItem()
        }

        // Add menu
        setHasOptionsMenu(true)

        return binding.root
    }

    // Update current question bank
    private fun updateItem() {
        val questionBankName = binding.updateQuestionBankName.text.toString()
        val questionBankDescription = binding.updateQuestionBanksDescription.text.toString()

        if (inputCheck(questionBankName, questionBankDescription)) {
            val updatedQuestionBank = QuestionBank(args.currentQuestionBank.uid, args.currentQuestionBank.moduleName, questionBankName, questionBankDescription)

            // Update current question bank info and question bank name that attached to questions
            qbUserViewModel.updateQuestionBankNameWithQuestion(updatedQuestionBank, questionBankName, args.currentQuestionBank.questionBankName)

            findNavController().popBackStack()
            Toast.makeText(requireContext(), "Question bank update successfully", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(requireContext(), "Please fill in all fields.", Toast.LENGTH_SHORT)
                .show()
        }
    }

    private fun inputCheck(questionBankName: String, questionBankDescription: String): Boolean {
        return !(TextUtils.isEmpty(questionBankName) && TextUtils.isEmpty(questionBankDescription))
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_delete) {
            deleteQuestionBank()
        }
        return super.onOptionsItemSelected(item)
    }

    // Delete current question bank
    private fun deleteQuestionBank() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") { _, _ ->

            // Delete current question bank and question that attached to current question bank
            qbUserViewModel.deleteQuestionBankAndQuestion(args.currentQuestionBank, args.currentQuestionBank.questionBankName)

            Toast.makeText(requireContext(),
                "Successfully removed: ${args.currentQuestionBank.questionBankName}",
                Toast.LENGTH_SHORT).show()

            findNavController().popBackStack()
        }
        builder.setNegativeButton("No") { _, _ -> }
        builder.setTitle("Delete ${args.currentQuestionBank.questionBankName}?")
        builder.setMessage("Are you sure you want to delete ${args.currentQuestionBank.questionBankName}?")
        builder.create().show()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}