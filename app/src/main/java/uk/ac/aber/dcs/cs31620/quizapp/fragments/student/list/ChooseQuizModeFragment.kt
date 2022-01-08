package uk.ac.aber.dcs.cs31620.quizapp.fragments.student.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import uk.ac.aber.dcs.cs31620.quizapp.databinding.FragmentChooseQuizModeBinding

class ChooseQuizModeFragment : Fragment() {

    private var _binding: FragmentChooseQuizModeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
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