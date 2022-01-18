package com.example.projectfinalwad

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.service.autofill.UserData
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase

class displayallemployees : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_displayallemployees)

        var progress:ProgressDialog=ProgressDialog(this)
        progress.setTitle("Please Wait...")

        var listView1=findViewById<ListView>(R.id.listview1)

        var datalist=ArrayList<String>()



        val database = Firebase.database
        val myRef = database.getReference("EmployeesData")

        myRef.addValueEventListener(object:ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {

                progress.show()
                //data is in snapshot

                for(obj in snapshot.children)
                {
                    var temp=obj.getValue<employeeclass>()
                    var currentUser:String="First Name: ${temp?.firstname} \nLast Name: ${temp?.lastname} \n" +
                            "Age: ${temp?.empage} \nSalary: ${temp?.emgsalary} \nJob Title: ${temp?.empjob}"
                    datalist.add(currentUser)

                }

                var myadapter= ArrayAdapter<String>(baseContext,android.R.layout.simple_list_item_1,datalist)
                listView1.adapter=myadapter
                progress.dismiss()
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(baseContext,"Can't Add Employee", Toast.LENGTH_SHORT).show()
            }

        })

    }
}