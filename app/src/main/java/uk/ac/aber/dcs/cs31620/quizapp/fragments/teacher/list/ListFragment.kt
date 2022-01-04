package uk.ac.aber.dcs.cs31620.quizapp.fragments.teacher.list

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.core.view.isEmpty
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import uk.ac.aber.dcs.cs31620.quizapp.R
import uk.ac.aber.dcs.cs31620.quizapp.databinding.FragmentListBinding
import uk.ac.aber.dcs.cs31620.quizapp.datasource.viewmodel.ModuleViewModel
import uk.ac.aber.dcs.cs31620.quizapp.datasource.viewmodel.QuestionBankViewModel
import uk.ac.aber.dcs.cs31620.quizapp.datasource.viewmodel.QuestionViewModel

class ListFragment : Fragment() {

    private lateinit var mUserViewModel: ModuleViewModel
    private lateinit var qbUserViewModel: QuestionBankViewModel
    private lateinit var qUserViewModel: QuestionViewModel
    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<RecycleAdapter.ViewHolder>? = null

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentListBinding.inflate(inflater, container, false)

        val recyclerView = binding.recyclerview

        layoutManager = LinearLayoutManager(requireContext())
        recyclerView.layoutManager = layoutManager

        adapter = RecycleAdapter()
        recyclerView.adapter = adapter

        mUserViewModel = ViewModelProvider(this).get(ModuleViewModel::class.java)
        qbUserViewModel = ViewModelProvider(this).get(QuestionBankViewModel::class.java)
        qUserViewModel = ViewModelProvider(this).get(QuestionViewModel::class.java)


        mUserViewModel.readAllData.observe(viewLifecycleOwner, Observer { module -> (adapter as RecycleAdapter).setData(module) })

        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }


        // Add menu
        setHasOptionsMenu(true)

        return binding.root
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_delete) {
            deleteAllModules()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteAllModules() {
        if(!binding.recyclerview.isEmpty()){
            val builder = AlertDialog.Builder(requireContext())
            builder.setPositiveButton("Yes") { _, _ ->
                mUserViewModel.deleteAllModule()
                qbUserViewModel.deleteAllQuestionBank()
                qUserViewModel.deleteAllQuestion()

                Toast.makeText(requireContext(), "Successfully removed everything", Toast.LENGTH_SHORT)
                    .show()
                findNavController().currentDestination
            }
            builder.setNegativeButton("No") { _, _ -> }
            builder.setTitle("Delete everything?")
            builder.setMessage("Are you sure you want to delete everything?")
            builder.create().show()
        } else {
            Toast.makeText(requireContext(), "Module lists are empty", Toast.LENGTH_SHORT)
                .show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}