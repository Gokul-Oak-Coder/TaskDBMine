 package com.example.androapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.os.Handler
import android.os.Looper
import androidx.room.Room
import com.blogspot.atifsoftwares.animatoolib.Animatoo
import com.nttdata.androapp.RecyclerViewPackage.CardInfo
import com.nttdata.androapp.RecyclerViewPackage.DataObject
import com.nttdata.androapp.RoomDatabase.WorkDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var dataBase: WorkDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


                //Database
                dataBase = Room.databaseBuilder(
                    applicationContext,WorkDatabase::class.java,"Work_Data"
                ).build()

                GlobalScope.launch{
                    DataObject.listdata = dataBase.dao().getTasks() as MutableList<CardInfo>
                }

                //splash screen code
                Handler(Looper.getMainLooper()).postDelayed({
                    val intent = Intent(this,LoginActivity::class.java)
                    startActivity(intent)
                    Animatoo.animateSlideLeft(this)
                    finish()

                },500)

            }
        }
    }
}