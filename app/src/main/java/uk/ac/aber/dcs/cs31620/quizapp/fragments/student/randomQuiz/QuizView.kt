package uk.ac.aber.dcs.cs31620.quizapp.fragments.student.randomQuiz

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import uk.ac.aber.dcs.cs31620.quizapp.R
import uk.ac.aber.dcs.cs31620.quizapp.databinding.FragmentQuizViewBinding


class QuizView : Fragment() {

    private var _binding: FragmentQuizViewBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentQuizViewBinding.inflate(inflater, container, false)



        binding.exitQuizButton.setOnClickListener {
            findNavController().navigate(R.id.scoreFragment)
        }

        return  binding.root
    }


}