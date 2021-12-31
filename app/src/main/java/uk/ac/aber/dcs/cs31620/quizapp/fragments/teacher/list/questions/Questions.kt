package uk.ac.aber.dcs.cs31620.quizapp.fragments.teacher.list.questions

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.core.view.isEmpty
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import uk.ac.aber.dcs.cs31620.quizapp.R
import uk.ac.aber.dcs.cs31620.quizapp.databinding.FragmentQuestionsBinding
import uk.ac.aber.dcs.cs31620.quizapp.fragments.teacher.viewmodel.QuestionViewModel

class Questions : Fragment() {

    private lateinit var qUserViewModel: QuestionViewModel
    private var layoutManager: RecyclerView.LayoutManager?=null
    private var adapter: RecyclerView.Adapter<QuestionAdapter.ViewHolder>?=null

    private var _binding: FragmentQuestionsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentQuestionsBinding.inflate(inflater, container, false)

        val recyclerView = binding.questionRecyclerView

        layoutManager = LinearLayoutManager(requireContext())
        recyclerView.layoutManager = layoutManager

        adapter = QuestionAdapter()
        recyclerView.adapter = adapter

        qUserViewModel = ViewModelProvider(this).get(QuestionViewModel::class.java)
        qUserViewModel.readAllData.observe(viewLifecycleOwner, {question -> (adapter as QuestionAdapter).setData(question)})

        binding.addQuestionFloatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_questions_to_addQuestions)
        }


        setHasOptionsMenu(true)

        return binding.root

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_delete) {
            deleteAllQuestions()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteAllQuestions(){
        if(!binding.questionRecyclerView.isEmpty()){
            val builder = AlertDialog.Builder(requireContext())
            builder.setPositiveButton("Yes") { _, _ ->
                qUserViewModel.deleteAllQuestion()
                Toast.makeText(requireContext(), "Successfully removed everything", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.questions)
            }
            builder.setNegativeButton("No") { _, _ -> }
            builder.setTitle("Delete everything?")
            builder.setMessage("Are you sure you want to delete everything?")
            builder.create().show()
        }else {
            Toast.makeText(requireContext(), "Question lists are empty", Toast.LENGTH_SHORT)
                .show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}