package uk.ac.aber.dcs.cs31620.quizapp.fragments.teacher

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import uk.ac.aber.dcs.cs31620.quizapp.R

class RecycleAdapter:RecyclerView.Adapter<RecycleAdapter.viewHolder>() {

//    @Query("SELECT * FROM modules")


    private var titles = arrayOf("CS31310", "CS31620", "CS36110", "CS37420", "CS38220", "CS39440")
    private var details = arrayOf("Agile Development amd Testing", "Mobile Development with Android", "Fundamentals of Machine Learning", "E-Commerce: Implementation, Management and Security", "Professional Issues in the computer industry", "Major Project")


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.module_view, parent, false)
        return viewHolder(v)
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
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

//                Intent intent = new Intent(itemView.context, )
            }
        }
    }
}