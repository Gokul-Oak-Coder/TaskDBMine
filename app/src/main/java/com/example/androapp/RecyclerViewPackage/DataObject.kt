package com.nttdata.androapp.RecyclerViewPackage

import android.widget.ImageView

object DataObject {

    var listdata = mutableListOf<CardInfo>()

    fun setData(projectName: String,
                start: String,
                end: String,
                assignee: String,
                statusImag: String,

    )
    {
        listdata.add(CardInfo(projectName,start,end,assignee,statusImag))

    }
    fun getAllData():List<CardInfo>
    {
        return listdata
    }

    fun deleteAll(){
        listdata.clear()
    }

    fun getData(pos:Int): CardInfo {
        return listdata[pos]
    }

    fun deleteData(pos : Int){
        listdata.removeAt(pos)
    }

    fun updateData(pos : Int,projectName : String,start :String ,end: String,assignee: String,statusImag: String){
        listdata[pos].projectName = projectName
        listdata[pos].start= start
        listdata[pos].end = end
        listdata[pos].assignee = assignee
        listdata[pos].statusImage = statusImag

    }
}