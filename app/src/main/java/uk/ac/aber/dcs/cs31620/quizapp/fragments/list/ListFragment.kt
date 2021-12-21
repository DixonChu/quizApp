package uk.ac.aber.dcs.cs31620.quizapp.fragments.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import uk.ac.aber.dcs.cs31620.quizapp.R
import uk.ac.aber.dcs.cs31620.quizapp.databinding.FragmentListBinding


class ListFragment : Fragment() {

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentListBinding.inflate(inflater, container, false)

        binding.floatingActionButton.setOnClickListener{
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }

        return binding.root

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}