package com.nttdata.androapp

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.nttdata.androapp.ProjectFragments.DevelopmentFragment
import androidx.room.Room
import com.blogspot.atifsoftwares.animatoolib.Animatoo
import com.nttdata.androapp.RecyclerViewPackage.DataObject
import com.nttdata.androapp.RecyclerViewPackage.WorkAdapter
import com.nttdata.androapp.RoomDatabase.WList
import com.nttdata.androapp.RoomDatabase.WorkDatabase
import kotlinx.android.synthetic.main.activity_new_task.*
import kotlinx.android.synthetic.main.fragment_development.*
import kotlinx.android.synthetic.main.fragment_h_u_a_ts.*
import kotlinx.android.synthetic.main.fragment_incident.*
import kotlinx.android.synthetic.main.fragment_m_cissues.*
import kotlinx.android.synthetic.main.work_list.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class NewTask : AppCompatActivity() {

    lateinit var end : String

    private lateinit var dataBase: WorkDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_task)


        dataBase = Room.databaseBuilder(
            applicationContext,WorkDatabase::class.java,"Work_Data"
        ).build()

        //Implementing spinner (dropdown menu)
       val status = resources.getStringArray(R.array.status)
        val arrayAdapter = ArrayAdapter(this,R.layout.status_list,status)
        autoCompleteText.setAdapter(arrayAdapter)
        autoCompleteText.setOnItemClickListener { adapterView, view, i, l ->
            if(adapterView.getItemAtPosition(i) == "Completed"){
                end_dateView.isVisible = true
                end_date_btn.isVisible = true
                textView12.isVisible = true
            }
            else{
                end_dateView.isVisible = false
                end_date_btn.isVisible = false
                textView12.isVisible = false
            }

        }


        val taskType = resources.getStringArray(R.array.task_type)
        val arrayAdapter2 = ArrayAdapter(this,R.layout.task_list,taskType)
        autoCompleteText2.setAdapter(arrayAdapter2)
//        autoCompleteText2.setOnItemClickListener { adapterView, view, i, l ->
//           if(adapterView.getItemAtPosition(i)=="Development"){
//               recyclerViewWork_dev.adapter = WorkAdapter(DataObject.getAllData())
//            }
//            else if(adapterView.getItemAtPosition(i)=="Incidents"){
//               recyclerViewWork_Incidents.adapter = WorkAdapter(DataObject.getAllData())
//            }
//            else if(adapterView.getItemAtPosition(i)=="HUATs"){
//               recyclerViewWork_HUATs.adapter = WorkAdapter(DataObject.getAllData())
//            }
//            else if(adapterView.getItemAtPosition(i)=="MCIssues"){
//               recyclerViewWork_MCIssues.adapter = WorkAdapter(DataObject.getAllData())
//           }
//            }



        date_btn.setOnClickListener {
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

        end_date_btn.setOnClickListener {
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


        saveBtn.setOnClickListener {
            if(taskName.text.toString().trim{it<=' '}.isNotEmpty()
                && takerName.text.toString().trim{it<=' '}.isNotEmpty()
                && dateView.text.toString().trim(){it<=' '}.isNotEmpty()
                && autoCompleteText.text.toString().trim(){it<=' '}.isNotEmpty()
                && autoCompleteText2.text.toString().trim(){it<=' '}.isNotEmpty()
            )

            {
                var taskNamee = taskName.getText().toString()
                var taker = takerName.getText().toString()
                var date = dateView.getText().toString()


//                autoCompleteText2.setOnItemClickListener { adapterView, view, i, l ->
//                    if(adapterView.getItemAtPosition(i)=="Development"){
//                        recyclerViewWork_dev.adapter = WorkAdapter(DataObject.getAllData())
//                    }
//                    else if(adapterView.getItemAtPosition(i)=="Incidents"){
//                        recyclerViewWork_Incidents.adapter = WorkAdapter(DataObject.getAllData())
//                    }
//                    else if(adapterView.getItemAtPosition(i)=="HUATs"){
//                        recyclerViewWork_HUATs.adapter = WorkAdapter(DataObject.getAllData())
//                    }
//                    else if(adapterView.getItemAtPosition(i)=="MCIssues"){
//                        recyclerViewWork_MCIssues.adapter = WorkAdapter(DataObject.getAllData())
//                    }
//                }

//                autoCompleteText.setOnItemClickListener { adapterView, view, i, l ->
//                    if(adapterView.getItemAtPosition(i)=="Completed"){
//                         Image_of_status.setImageResource(R.drawable.comp)
//                    }
//                    else if(adapterView.getItemAtPosition(i)=="Yet to Start"){
//                         Image_of_status.setImageResource(R.drawable.yettostart)
//                    }
//                    else if(adapterView.getItemAtPosition(i)=="In Progress"){
//                        Image_of_status.setImageResource(R.drawable.inprogress)
//                    }
//                    else if(adapterView.getItemAtPosition(i)=="Hold"){
//                        Image_of_status.setImageResource(R.drawable.holding)
//                    }
//                    else if(adapterView.getItemAtPosition(i)=="Blocked"){
//                        Image_of_status.setImageResource(R.drawable.blocked)
//                    }
//                }


                //var countt = "1"

                if(end_dateView.text.isEmpty()){
                    end = "NA"
                }else {
                    end = end_dateView.getText().toString()
                }

                var statusImage = "Status : "

                DataObject.setData(taskNamee,date,end,taker,statusImage)

                GlobalScope.launch {

                dataBase.dao().insertTask(WList(0,taskNamee,date,end,taker,statusImage))
                 }

                GlobalScope.launch {
                    Log.i("harsh",dataBase.dao().getTasks().toString())
                }



                val intent = Intent(this,NavigationPage::class.java)
                startActivity(intent)
                //finish()



                // var username = editText.text.toString()

//                var bundle = Bundle()
//                bundle.putString("username",username)

                var developmentFragment = DevelopmentFragment()
                //developmentFragment.arguments = bundle

               // var fragmentManager : FragmentManager = supportFragmentManager
              //  var fragmentTransaction : FragmentTransaction = fragmentManager.beginTransaction()
              //  fragmentTransaction.replace(R.id.androFrame,developmentFragment)
            // fragmentTransaction.commit()

                //supportFragmentManager.beginTransaction().replace(R.id.recyclerViewWork, fragment).commit()

            }
        }


    }
    private fun updateLable(myCalender: Calendar) {
        val myFormat = "dd-MM-yyyy"
        val sdf = SimpleDateFormat(myFormat,Locale.UK)
        dateView.setText(sdf.format(myCalender.time))

    }
    private fun updateLable2(myCalender: Calendar) {
        val myFormat = "dd-MM-yyyy"
        val sdf = SimpleDateFormat(myFormat,Locale.UK)
        end_dateView.setText(sdf.format(myCalender.time))
    }

    override fun onBackPressed() {
        super.onBackPressed()
        Animatoo.animateSlideDown(this) //fire the slide left animation
    }

}