package uk.ac.aber.dcs.cs31620.quizapp.fragments.teacher.list.questionBanks

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.core.view.isEmpty
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import uk.ac.aber.dcs.cs31620.quizapp.R
import uk.ac.aber.dcs.cs31620.quizapp.databinding.FragmentQuestionbanksBinding
import uk.ac.aber.dcs.cs31620.quizapp.fragments.teacher.model.QuestionBank
import uk.ac.aber.dcs.cs31620.quizapp.datasource.viewmodel.QuestionBankViewModel

class QuestionBanksFragment : Fragment() {

    private lateinit var qbUserViewModel: QuestionBankViewModel

    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<QuestionBankAdapter.ViewHolder>? = null

    private var _binding: FragmentQuestionbanksBinding? = null
    private val binding get() = _binding!!
    private val moduleName: String get() = args.module.moduleName

    private val args by navArgs<QuestionBanksFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentQuestionbanksBinding.inflate(inflater, container, false)

        val recyclerView = binding.questionBankRecyclerView

        layoutManager = LinearLayoutManager(requireContext())
        recyclerView.layoutManager = layoutManager

        adapter = QuestionBankAdapter()
        recyclerView.adapter = adapter

        qbUserViewModel = ViewModelProvider(this).get(QuestionBankViewModel::class.java)
        val questionBankList = questionBankList()
        questionBankList.observe(viewLifecycleOwner){questionBanks -> (adapter as QuestionBankAdapter).setData(questionBanks)}

        binding.addQuestionBankFloatingActionButton.setOnClickListener {
            val action = QuestionBanksFragmentDirections.actionQuestionbanksToAddQuestionBanks(args.module.moduleName)

            findNavController().navigate(action)

        }

        // Add menu
        setHasOptionsMenu(true)

        return binding.root
    }

    private fun questionBankList(): LiveData<List<QuestionBank>> {
        val moduleName = args.module.moduleName
        return qbUserViewModel.getQuestionBankWithModuleName(moduleName)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_delete) {
            deleteAllQuestionBanks()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteAllQuestionBanks() {
        if (!binding.questionBankRecyclerView.isEmpty()) {
            val builder = AlertDialog.Builder(requireContext())
            builder.setPositiveButton("Yes") { _, _ ->
                qbUserViewModel.deleteAllQuestionBankByModuleName(args.module.moduleName)
                Toast.makeText(requireContext(),
                    "Successfully removed everything",
                    Toast.LENGTH_SHORT)
                    .show()
                findNavController().currentDestination
            }
            builder.setNegativeButton("No") { _, _ -> }
            builder.setTitle("Delete everything?")
            builder.setMessage("Are you sure you want to delete everything?")
            builder.create().show()
        } else {
            Toast.makeText(requireContext(), "Question Bank lists are empty", Toast.LENGTH_SHORT)
                .show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}