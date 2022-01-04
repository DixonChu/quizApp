package uk.ac.aber.dcs.cs31620.quizapp.fragments.student.list

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import uk.ac.aber.dcs.cs31620.quizapp.databinding.FragmentChooseQuizModeBinding
import uk.ac.aber.dcs.cs31620.quizapp.datasource.viewmodel.QuestionBankViewModel
import uk.ac.aber.dcs.cs31620.quizapp.datasource.viewmodel.QuestionViewModel

class ChooseQuizModeFragment : Fragment() {

    private var _binding: FragmentChooseQuizModeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentChooseQuizModeBinding.inflate(inflater, container, false)

        binding.chooseQuestionBankButton.setOnClickListener {
            val action = ChooseQuizModeFragmentDirections.actionChooseQuizModeFragmentToListQuestionBank()
            findNavController().navigate(action)
        }

        binding.randomQuizButton.setOnClickListener {
            val action = ChooseQuizModeFragmentDirections.actionChooseQuizModeFragmentToQuizView()
            findNavController().navigate(action)
        }

        return binding.root
    }




}