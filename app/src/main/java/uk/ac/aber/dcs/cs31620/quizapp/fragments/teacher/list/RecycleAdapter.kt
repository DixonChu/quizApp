package uk.ac.aber.dcs.cs31620.quizapp.fragments.teacher.list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.NavArgs
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import uk.ac.aber.dcs.cs31620.quizapp.R
import uk.ac.aber.dcs.cs31620.quizapp.databinding.FragmentListBinding
import uk.ac.aber.dcs.cs31620.quizapp.fragments.teacher.model.Module


class RecycleAdapter() : RecyclerView.Adapter<RecycleAdapter.ViewHolder>() {

    private var moduleList = emptyList<Module>()

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.module_view, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return moduleList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = moduleList[position]
        holder.itemTitle.text = currentItem.moduleName
        holder.itemDetail.text = currentItem.moduleDescription
        holder.editButton.setOnClickListener {
            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        }
        holder.card.setOnClickListener {
            val action = ListFragmentDirections.actionListFragmentToQuestionbanks(currentItem)
            holder.itemView.findNavController().navigate(action)

        }


    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var itemTitle: TextView = itemView.findViewById(R.id.module_title)
        var itemDetail: TextView = itemView.findViewById(R.id.module_details)
        var editButton: View = itemView.findViewById(R.id.edit_button)
        var card: View = itemView.findViewById(R.id.card_view)

    }

    fun setData(module: List<Module>) {
        this.moduleList = module
        notifyDataSetChanged()
    }
}