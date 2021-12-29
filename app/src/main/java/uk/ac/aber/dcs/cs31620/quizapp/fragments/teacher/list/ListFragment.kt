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
import uk.ac.aber.dcs.cs31620.quizapp.fragments.teacher.viewmodel.ModuleViewModel

class ListFragment : Fragment() {

    private lateinit var mUserViewModule: ModuleViewModel
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

        mUserViewModule = ViewModelProvider(this).get(ModuleViewModel::class.java)
        mUserViewModule.readAllData.observe(viewLifecycleOwner,
            Observer { module -> (adapter as RecycleAdapter).setData(module) })

        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }

        binding.deleteAllModules.setOnClickListener {
            if (!binding.recyclerview.isEmpty()) {
                deleteAllModules()
            } else {
                Toast.makeText(requireContext(), "Module lists are empty", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        return binding.root
    }

    private fun deleteAllModules() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") { _, _ ->
            mUserViewModule.deleteAllModule()
            Toast.makeText(requireContext(), "Successfully removed everything", Toast.LENGTH_SHORT)
                .show()
            findNavController().navigate(R.id.listFragment)
        }
        builder.setNegativeButton("No") { _, _ -> }
        builder.setTitle("Delete everything?")
        builder.setMessage("Are you sure you want to delete everything?")
        builder.create().show()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}