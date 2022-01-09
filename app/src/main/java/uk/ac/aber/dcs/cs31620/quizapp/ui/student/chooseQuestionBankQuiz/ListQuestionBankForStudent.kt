package uk.ac.aber.dcs.cs31620.quizapp.ui.student.chooseQuestionBankQuiz

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import uk.ac.aber.dcs.cs31620.quizapp.databinding.FragmentListQuestionBankBinding
import uk.ac.aber.dcs.cs31620.quizapp.datasource.viewmodel.QuestionBankViewModel


class ListQuestionBankForStudent : Fragment() {

    private lateinit var qbUserViewModel: QuestionBankViewModel

    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<ListQuestionBankForStudentAdapter.ViewHolder>?=null

    private var _binding: FragmentListQuestionBankBinding?= null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListQuestionBankBinding.inflate(inflater, container, false)

        val recyclerView = binding.studentQuestionBankRecycleView

        layoutManager = LinearLayoutManager(requireContext())
        recyclerView.layoutManager = layoutManager

        adapter = ListQuestionBankForStudentAdapter()
        recyclerView.adapter = adapter

        qbUserViewModel = ViewModelProvider(this)[QuestionBankViewModel::class.java]
        qbUserViewModel.readAllData.observe(viewLifecycleOwner){questionBank -> (adapter as ListQuestionBankForStudentAdapter).setData(questionBank)}


        return binding.root
    }


}