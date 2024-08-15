package com.nttdata.androapp

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.room.Room
import com.nttdata.androapp.RecyclerViewPackage.DataObject
import com.nttdata.androapp.RoomDatabase.WList
import com.nttdata.androapp.RoomDatabase.WorkDatabase
import kotlinx.android.synthetic.main.activity_new_task.*
import kotlinx.android.synthetic.main.activity_update_tasks.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class UpdateTask : AppCompatActivity() {

    private lateinit var dataBase: WorkDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_tasks)

        val pos = intent.getIntExtra("id",-1)


        //Database
        dataBase = Room.databaseBuilder(
            applicationContext,WorkDatabase::class.java,"Work_Data"
        ).build()


        val status = resources.getStringArray(R.array.status)
        val arrayAdapter = ArrayAdapter(this,R.layout.status_list,status)
        update_autoCompleteText.setAdapter(arrayAdapter)
        update_autoCompleteText.setOnItemClickListener { adapterView, view, i, l ->
        }

        updated_autoCompleteText2
        val taskType = resources.getStringArray(R.array.task_type)
        val arrayAdapter2 = ArrayAdapter(this,R.layout.task_list,taskType)
        updated_autoCompleteText2.setAdapter(arrayAdapter2)
        updated_autoCompleteText2.setOnItemClickListener { adapterView, view, i, l ->
        }

        update_date_btn.setOnClickListener {
            val myCalender = Calendar.getInstance()
            val datePickerDialog = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                myCalender.set(Calendar.YEAR,year)
                myCalender.set(Calendar.MONTH,month)
                myCalender.set(Calendar.DAY_OF_MONTH,dayOfMonth)
                updateLable(myCalender)
            }
            DatePickerDialog(this,datePickerDialog,myCalender.get(Calendar.YEAR),myCalender.get(
                Calendar.MONTH),
                myCalender.get(Calendar.DAY_OF_MONTH)).show()
        }

        update_end_date_btn.setOnClickListener {
            val myCalender = Calendar.getInstance()
            val datePickerDialog = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                myCalender.set(Calendar.YEAR,year)
                myCalender.set(Calendar.MONTH,month)
                myCalender.set(Calendar.DAY_OF_MONTH,dayOfMonth)
                updateLable2(myCalender)
            }
            DatePickerDialog(this,datePickerDialog,myCalender.get(Calendar.YEAR),myCalender.get(
                Calendar.MONTH),
                myCalender.get(Calendar.DAY_OF_MONTH)).show()
        }
        if(pos != -1){
            val projectName = DataObject.getData(pos).projectName
            val start = DataObject.getData(pos).start
            val end = DataObject.getData(pos).end
            val assignee = DataObject.getData(pos).assignee
            val statusImage = DataObject.getData(pos).statusImage

            updateTaskName.setText(projectName)
            updateTakerName.setText(assignee)
            updated_dateView.text = start
            update_endDateView.text = end


            update_del_Btn.setOnClickListener {
                DataObject.deleteData(pos)
                GlobalScope.launch {
                    dataBase.dao().deleteTask(
                        WList(pos+1,updateTaskName.text.toString(), updated_dateView.text.toString(), update_endDateView.text.toString(), updateTakerName.text.toString(), statusImage)
                    )
                }
                myIntent()

               // finish()
            }
            update_btn.setOnClickListener {
                DataObject.updateData(pos,updateTaskName.text.toString() ,updated_dateView.text.toString() ,update_endDateView.text.toString(),updateTakerName.text.toString(),statusImage)
                GlobalScope.launch {
                    dataBase.dao().updateTask(
                        WList(pos+1,updateTaskName.text.toString(), updated_dateView.text.toString(), update_endDateView.text.toString(), updateTakerName.text.toString(), statusImage)
                    )
                }
         myIntent()
               // finish()
            }

        }

    }
    fun  myIntent(){
        val intent = Intent(this,NavigationPage::class.java)
                startActivity(intent)
        finish()
    }

    private fun updateLable(myCalender: Calendar) {
        val myFormat = "dd-MM-yyyy"
        val sdf = SimpleDateFormat(myFormat,Locale.UK)
        updated_dateView.setText(sdf.format(myCalender.time))

    }
    private fun updateLable2(myCalender: Calendar) {
        val myFormat = "dd-MM-yyyy"
        val sdf = SimpleDateFormat(myFormat,Locale.UK)
        update_endDateView.setText(sdf.format(myCalender.time))
    }
}