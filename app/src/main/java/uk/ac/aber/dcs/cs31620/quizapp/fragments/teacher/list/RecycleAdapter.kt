package uk.ac.aber.dcs.cs31620.quizapp.fragments.teacher.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import uk.ac.aber.dcs.cs31620.quizapp.R
import uk.ac.aber.dcs.cs31620.quizapp.datasource.Module


class RecycleAdapter:RecyclerView.Adapter<RecycleAdapter.ViewHolder>() {

    private var moduleList = emptyList<Module>()

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
    }


    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var itemTitle: TextView = itemView.findViewById(R.id.module_title)
        var itemDetail: TextView = itemView.findViewById(R.id.module_details)

        init {

            itemView.setOnClickListener {
                val position: Int = adapterPosition

                Toast.makeText(itemView.context, "You clicked on ${moduleList[position]}", Toast.LENGTH_LONG).show()

//                Intent intent = new Intent(itemView.context, )
            }
        }
    }

    fun setData(module: List<Module>){
        this.moduleList = module
        notifyDataSetChanged()
    }
}