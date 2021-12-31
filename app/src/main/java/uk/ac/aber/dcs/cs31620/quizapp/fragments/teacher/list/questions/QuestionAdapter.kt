package uk.ac.aber.dcs.cs31620.quizapp.fragments.teacher.list.questions

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import uk.ac.aber.dcs.cs31620.quizapp.R
import uk.ac.aber.dcs.cs31620.quizapp.databinding.FragmentQuestionsBinding
import uk.ac.aber.dcs.cs31620.quizapp.fragments.teacher.model.Question
import uk.ac.aber.dcs.cs31620.quizapp.fragments.teacher.model.QuestionBank

class QuestionAdapter: RecyclerView.Adapter<QuestionAdapter.ViewHolder>() {

    private var questionList = emptyList<Question>()
    private var _binding: FragmentQuestionsBinding?= null
    private val binding get() = _binding!!

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.questions_view, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return questionList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = questionList[position]
        holder.itemTitle.text = currentItem.question

        holder.editButton.setOnClickListener {
            val action = QuestionsDirections.actionQuestionsToUpdateQuestions(currentItem)
            holder.itemView.findNavController().navigate(action)
        }

    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var itemTitle: TextView = itemView.findViewById(R.id.question_title)
        var editButton: View = itemView.findViewById(R.id.question_edit_button)
    }


    fun setData(question: List<Question>) {
        this.questionList = question
        notifyDataSetChanged()
    }
}