package uk.ac.aber.dcs.cs31620.quizapp.fragments.teacher.add

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import uk.ac.aber.dcs.cs31620.quizapp.R
import uk.ac.aber.dcs.cs31620.quizapp.databinding.FragmentAddBinding
import uk.ac.aber.dcs.cs31620.quizapp.datasource.Module
import uk.ac.aber.dcs.cs31620.quizapp.datasource.model.ModuleViewModel


class AddFragment : Fragment() {

    private lateinit var mUserViewModel: ModuleViewModel

    private var _binding: FragmentAddBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAddBinding.inflate(inflater, container, false)

        mUserViewModel = ViewModelProvider(this).get(ModuleViewModel::class.java)

        binding.addBtn.setOnClickListener {
            insertDataToDatabase()
        }

        return binding.root
    }

    private fun insertDataToDatabase() {
        val moduleName = binding.addModuleName.text.toString()
        val moduleDescription = binding.addModuleDescription.text.toString()
        val questionBank = binding.addQuestions.text.toString()

        if (inputCheck(moduleName, moduleDescription, questionBank)) {
            val module = Module(0, moduleName, moduleDescription, questionBank)
            mUserViewModel.addModule(module)
            Toast.makeText(requireContext(), "Successfully Added!", Toast.LENGTH_LONG).show()

            findNavController().navigate(R.id.action_addFragment_to_listFragment)

        } else {
            Toast.makeText(requireContext(), "Please fill in out all fields", Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(
        moduleName: String,
        moduleDescription: String,
        questionBank: String
    ): Boolean {
        return !(TextUtils.isEmpty(moduleName) && TextUtils.isEmpty(moduleDescription) && TextUtils.isEmpty(
            questionBank
        ))
    }

}