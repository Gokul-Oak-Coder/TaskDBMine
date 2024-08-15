package com.nttdata.androapp

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.blogspot.atifsoftwares.animatoolib.Animatoo.animateSlideRight


class WorkDescriptionActivity : AppCompatActivity() {

    lateinit var projectName : TextView
    lateinit var assigneeName : TextView
    lateinit var assigneeImage : ImageView
    lateinit var description : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_work_description)

        projectName = findViewById(R.id.proName)
        assigneeName = findViewById(R.id.assigneeName)
        assigneeImage = findViewById(R.id.assigneeProfile)
        description = findViewById(R.id.description)

        val bundle : Bundle? = intent.extras
        val pro = bundle?.getString("pro").toString()
        val ass = bundle!!.getString("assign").toString()
        val start = bundle.getString("start").toString()
        val end = bundle.getString("end").toString()



        projectName.text = pro
        assigneeName.text = ass
        if(end == "End Date : NA") {
            description.text = "The project $pro started on $start and still on the progress is going  $ass."
        }
        else{
            description.text = " The project $pro has started on $start and successfully completed on $end the great job done by $ass."
        }

        assigneeImage.setImageResource(R.drawable.andro)

    }
    override fun onBackPressed() {
        super.onBackPressed()
        animateSlideRight(this) //fire the slide left animation
    }
}