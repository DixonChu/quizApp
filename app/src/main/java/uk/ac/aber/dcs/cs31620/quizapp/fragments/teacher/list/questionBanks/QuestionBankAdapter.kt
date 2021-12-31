package uk.ac.aber.dcs.cs31620.quizapp.fragments.teacher.list.questionBanks

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import uk.ac.aber.dcs.cs31620.quizapp.R
import uk.ac.aber.dcs.cs31620.quizapp.databinding.FragmentQuestionbanksBinding
import uk.ac.aber.dcs.cs31620.quizapp.fragments.teacher.model.Module
import uk.ac.aber.dcs.cs31620.quizapp.fragments.teacher.model.QuestionBank

class QuestionBankAdapter : RecyclerView.Adapter<QuestionBankAdapter.ViewHolder>() {

    private var questionBankList = emptyList<QuestionBank>()
    private var _binding: FragmentQuestionbanksBinding? = null
    private val binding get() = _binding!!

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v =
            LayoutInflater.from(parent.context).inflate(R.layout.question_bank_view, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return questionBankList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val currentItem = questionBankList[position]
        holder.itemTitle.text = currentItem.questionBankName
        holder.itemDetails.text = currentItem.questionBankDescription

        holder.editButton.setOnClickListener{
            val action = QuestionBanksFragmentDirections.actionQuestionbanksToUpdateQuestionBanks(currentItem)
            holder.itemView.findNavController().navigate(action)
        }

        holder.cardView.setOnClickListener {
            val action = QuestionBanksFragmentDirections.actionQuestionbanksToQuestions()
            holder.itemView.findNavController().navigate(action)
        //add questions fragment
        }


    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var itemTitle: TextView = itemView.findViewById(R.id.question_bank_title)
        var itemDetails: TextView = itemView.findViewById(R.id.question_bank_description)
        var editButton: View = itemView.findViewById(R.id.question_bank_edit_button)
        var cardView: View = itemView.findViewById(R.id.question_bank_card_view)
    }

    fun setData(questionBank: List<QuestionBank>) {
        this.questionBankList = questionBank
        notifyDataSetChanged()
    }


}