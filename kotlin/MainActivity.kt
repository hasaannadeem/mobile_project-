package com.example.projectfinalwad

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listview: ListView =findViewById<ListView>(R.id.listviewscreen)

        val data= listOf<String>("Add a new Employee","Delete an Employee","Update an Employee Data"
            ,"Display All Employees")
        val data2= arrayOfNulls<String>(data.size)

        for(i in 0 until data.size)
        {
            data2[i]=data[i]
        }
        val newadapter=
            ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,data2)

        listview.adapter=newadapter

        listview.setOnItemClickListener({
                parent, view, position, id ->

            if(position==0)
            {
             startActivity(Intent(this,addnewemployee::class.java))
                finish()
            }
            if(position==1)
            {
                startActivity(Intent(this,deleteanemployee::class.java))
                finish()
            }
            if(position==2)
            {
                startActivity(Intent(this,updateemployeedata::class.java))
                finish()
            }
            if(position==3)
            {
                startActivity(Intent(this,displayallemployees::class.java))
                finish()
            }

        })
    }

}
