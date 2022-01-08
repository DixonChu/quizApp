package uk.ac.aber.dcs.cs31620.quizapp.fragments.teacher.update

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
import uk.ac.aber.dcs.cs31620.quizapp.databinding.FragmentUpdateBinding
import uk.ac.aber.dcs.cs31620.quizapp.fragments.teacher.model.Module
import uk.ac.aber.dcs.cs31620.quizapp.datasource.viewmodel.ModuleViewModel
import uk.ac.aber.dcs.cs31620.quizapp.datasource.viewmodel.QuestionBankViewModel
import uk.ac.aber.dcs.cs31620.quizapp.datasource.viewmodel.QuestionViewModel


class UpdateFragment : Fragment() {

    private val args by navArgs<UpdateFragmentArgs>()

    private lateinit var mUserViewModel: ModuleViewModel
    private lateinit var qbUserViewModel: QuestionBankViewModel
    private lateinit var qUserViewModel: QuestionViewModel
    private var _binding: FragmentUpdateBinding? = null
    private val binding get() = _binding!!



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUpdateBinding.inflate(inflater, container, false)

        mUserViewModel = ViewModelProvider(this)[ModuleViewModel::class.java]
        qbUserViewModel = ViewModelProvider(this)[QuestionBankViewModel::class.java]
        qUserViewModel = ViewModelProvider(this)[QuestionViewModel::class.java]

        binding.updateModuleName.setText(args.currentModule.moduleName)
        binding.updateModuleDescription.setText(args.currentModule.moduleDescription)

        binding.updateBtn.setOnClickListener{
            updateItem()
        }

        // Add menu
        setHasOptionsMenu(true)
        return binding.root
    }

    private fun updateItem(){
        val moduleName = binding.updateModuleName.text.toString()
        val moduleDescription = binding.updateModuleDescription.text.toString()

        if (inputCheck(moduleName, moduleDescription)){
            val updatedModule = Module(args.currentModule.id, moduleName, moduleDescription)

            mUserViewModel.updateAllData(updatedModule,moduleName, args.currentModule.moduleName)

            findNavController().popBackStack()
            Toast.makeText(requireContext(), "Module name update successfully", Toast.LENGTH_SHORT).show()

        }else {
            Toast.makeText(requireContext(), "Please fill out all fields.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun inputCheck(moduleName: String, moduleDescription: String): Boolean{
        return !(TextUtils.isEmpty(moduleName) && TextUtils.isEmpty(moduleDescription))
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_delete) {
            deleteModule()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteModule(){
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") { _, _ ->
            mUserViewModel.deleteModule(args.currentModule)
            qbUserViewModel.deleteAllQuestionBankByModuleName(args.currentModule.moduleName)
            qUserViewModel.deleteQuestionByModuleName(args.currentModule.moduleName)

            Toast.makeText(requireContext(), "Successfully removed: ${args.currentModule.moduleName}", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }
        builder.setNegativeButton("No") {_, _ -> }
        builder.setTitle("Delete ${args.currentModule.moduleName}?")
        builder.setMessage("Are you sure you want to delete ${args.currentModule.moduleName}?")
        builder.create().show()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}