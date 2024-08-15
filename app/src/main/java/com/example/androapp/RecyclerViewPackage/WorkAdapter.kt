package com.nttdata.androapp.RecyclerViewPackage

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nttdata.androapp.NewTask
import com.nttdata.androapp.R
import com.nttdata.androapp.UpdateTask
import com.nttdata.androapp.WorkDescriptionActivity
import kotlinx.android.synthetic.main.work_list.view.*

class WorkAdapter(var data:List<CardInfo>) : RecyclerView.Adapter<WorkAdapter.viewHolder>() {


    class viewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){

        var projectName = itemView.projectName
        var start = itemView.startDatee
        var end = itemView.endDatee
        var statusImage  = itemView.statusImage
        var assignee = itemView.assigneee
        var layout = itemView.taskHolderLayout
        var desc  = itemView.desc
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        var itemView = LayoutInflater.from(parent.context).inflate(R.layout.work_list,parent,false)
        return viewHolder(itemView)
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {


        holder.projectName.text = data[position].projectName
        holder.start.text= data[position].start
        holder.end.text= data[position].end
        holder.statusImage.text = data[position].statusImage
        holder.assignee.text= data[position].assignee
        holder.layout.setOnClickListener {
            val intent = Intent(holder.itemView.context,UpdateTask::class.java)
            intent.putExtra("id",position)
            holder.itemView.context.startActivity(intent)



        }

        // To see the Detailed Description
        holder.desc.setOnClickListener {
            val intent = Intent(holder.itemView.context,WorkDescriptionActivity::class.java)

           intent.putExtra("pro",data[position].projectName)
            intent.putExtra("assign",data[position].assignee)
            intent.putExtra("start",data[position].start)
            intent.putExtra("end",data[position].end)



            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

}