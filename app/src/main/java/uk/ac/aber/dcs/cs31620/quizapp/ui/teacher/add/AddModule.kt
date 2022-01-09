package uk.ac.aber.dcs.cs31620.quizapp.ui.teacher.add

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
import uk.ac.aber.dcs.cs31620.quizapp.model.Module
import uk.ac.aber.dcs.cs31620.quizapp.datasource.viewmodel.ModuleViewModel


class AddModule : Fragment() {

    private lateinit var mUserViewModel: ModuleViewModel

    private var _binding: FragmentAddBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentAddBinding.inflate(inflater, container, false)

        mUserViewModel = ViewModelProvider(this)[ModuleViewModel::class.java]

        binding.addBtn.setOnClickListener {
            insertDataToDatabase()
        }

        binding.cancel.setOnClickListener{
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        }

        return binding.root
    }

    // Insert module to database
    private fun insertDataToDatabase() {
        val moduleName = binding.addModuleName.text.toString()
        val moduleDescription = binding.addModuleDescription.text.toString()

        if (inputCheck(moduleName)) {
            val module = Module(0, moduleName, moduleDescription)
            mUserViewModel.addModule(module)
            Toast.makeText(requireContext(), "Successfully Added!", Toast.LENGTH_SHORT).show()

            findNavController().navigate(R.id.action_addFragment_to_listFragment)

        } else {
            Toast.makeText(requireContext(), "Please fill in module name fields", Toast.LENGTH_LONG).show()
        }
    }

    // Check important field which cannot be empty
    private fun inputCheck(
        moduleName: String
    ): Boolean { return !(TextUtils.isEmpty(moduleName))}

}