package uk.ac.aber.dcs.cs31620.quizapp.fragments.teacher.update.questionBanks

import android.app.AlertDialog
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import uk.ac.aber.dcs.cs31620.quizapp.R
import uk.ac.aber.dcs.cs31620.quizapp.databinding.FragmentUpdateQuestionBanksBinding
import uk.ac.aber.dcs.cs31620.quizapp.fragments.teacher.model.QuestionBank
import uk.ac.aber.dcs.cs31620.quizapp.fragments.teacher.viewmodel.QuestionBankViewModel
import uk.ac.aber.dcs.cs31620.quizapp.fragments.teacher.viewmodel.QuestionViewModel

class UpdateQuestionBanks : Fragment() {

    private val args by navArgs<UpdateQuestionBanksArgs>()

    private lateinit var qbUserViewModel: QuestionBankViewModel
    private lateinit var qUserViewModel: QuestionViewModel
    private var _binding: FragmentUpdateQuestionBanksBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentUpdateQuestionBanksBinding.inflate(inflater, container, false)

        qbUserViewModel = ViewModelProvider(this)[QuestionBankViewModel::class.java]
        qUserViewModel = ViewModelProvider(this)[QuestionViewModel::class.java]

        binding.updateQuestionBankName.setText(args.currentQuestionBank.questionBankName)
        binding.updateQuestionBanksDescription.setText(args.currentQuestionBank.questionBankDescription)

        binding.updateQuestionBankBtn.setOnClickListener {
            updateItem()
        }

        setHasOptionsMenu(true)

        return binding.root
    }

    private fun updateItem() {
        val questionBankName = binding.updateQuestionBankName.text.toString()
        val questionBankDescription = binding.updateQuestionBanksDescription.text.toString()

        if (inputCheck(questionBankName, questionBankDescription)) {
            val updatedQuestionBank = QuestionBank(args.currentQuestionBank.uid,args.currentQuestionBank.moduleName, questionBankName, questionBankDescription)
            qbUserViewModel.updateQuestionBank(updatedQuestionBank)
            findNavController().navigate(R.id.action_update_question_banks_to_questionbanks)
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

    private fun deleteQuestionBank() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") { _, _ ->
            qbUserViewModel.deleteQuestionBank(args.currentQuestionBank)
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