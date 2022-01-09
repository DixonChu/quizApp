package uk.ac.aber.dcs.cs31620.quizapp.ui.student.chooseQuestionBankQuiz

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import uk.ac.aber.dcs.cs31620.quizapp.R
import uk.ac.aber.dcs.cs31620.quizapp.model.QuestionBank

class ListQuestionBankForStudentAdapter: RecyclerView.Adapter<ListQuestionBankForStudentAdapter.ViewHolder>() {

    private var questionBankList = emptyList<QuestionBank>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.quiz_question_bank_view, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return questionBankList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = questionBankList[position]
        holder.itemTitle.text = currentItem.questionBankName
        holder.itemDetails.text = currentItem.questionBankDescription
        holder.cardView.setOnClickListener {
            val action = ListQuestionBankForStudentDirections.actionListQuestionBankToQuestionBankQuizView(currentItem)
            holder.itemView.findNavController().navigate(action)
        }
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var itemTitle: TextView = itemView.findViewById(R.id.quiz_question_bank_title)
        var itemDetails: TextView = itemView.findViewById(R.id.quiz_question_bank_description)
        var cardView: View = itemView.findViewById(R.id.quiz_question_bank_card_view)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(questionBank: List<QuestionBank>){
        this.questionBankList = questionBank
        notifyDataSetChanged()
    }


}