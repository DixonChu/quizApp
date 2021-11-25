package uk.ac.aber.dcs.cs31620.quizapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class RecycleAdapter:RecyclerView.Adapter<RecycleAdapter.viewHolder>() {
    private var titles = arrayOf("CS31310", "CS37410", "CS33410", "CS37410", "CS37410", "CS37410", "CS37410")
    private var details = arrayOf("Item one details", "Item two details", "Item two details", "Item two details", "Item two details", "Item two details", "Item two details")


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecycleAdapter.viewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.module_view, parent, false)
        return viewHolder(v)
    }

    override fun onBindViewHolder(holder: RecycleAdapter.viewHolder, position: Int) {
        holder.itemTitle.text = titles[position]
        holder.itemDetail.text = details[position]
//        holder.itemImage.setImageResource[images[position]]
    }

    override fun getItemCount(): Int {
        return titles.size
    }

    inner class viewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
//        var itemImage: ImageView
        var itemTitle: TextView
        var itemDetail: TextView

        init {
//            itemImage = itemView.findViewById(R.id.item_image)
            itemTitle = itemView.findViewById(R.id.module_title)
            itemDetail = itemView.findViewById(R.id.module_details)

            itemView.setOnClickListener {
                val position: Int = adapterPosition

                Toast.makeText(itemView.context, "You clicked on ${titles[position]}", Toast.LENGTH_LONG).show()
            }
        }
    }
}