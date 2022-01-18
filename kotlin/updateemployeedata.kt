package com.example.projectfinalwad

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase

class updateemployeedata : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_updateemployeedata)

        var updatebutton: Button =findViewById(R.id.update)
        var field11: EditText =findViewById(R.id.firstnameu)
        var field22: EditText =findViewById(R.id.lastnameu)

        var firstnameu: EditText =findViewById(R.id.updatefirst)
        var lastnameu: EditText =findViewById(R.id.lastupdate)
        var ageu: EditText =findViewById(R.id.ageupdate)
        var salaryu: EditText =findViewById(R.id.salaryupdate)
        var jobu: EditText =findViewById(R.id.jobtitleupdate)


        updatebutton.setOnClickListener({

            val database = Firebase.database
            val myRef = database.getReference("EmployeesData")

            myRef.addValueEventListener(object: ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {

                    //data is in snapshot
                    for (obj in snapshot.children) {
                        var temp = obj.getValue<employeeclass>()
                        if (temp?.firstname == field11.text.toString() && temp?.lastname == field22.text.toString()) {
                            obj.ref.child("firstname").setValue(firstnameu.text.toString())
                            obj.ref.child("lastname").setValue(lastnameu.text.toString())
                            obj.ref.child("empage").setValue(ageu.text.toString())
                            obj.ref.child("emgsalary").setValue(salaryu.text.toString())
                            obj.ref.child("empjob").setValue(jobu.text.toString())
                            Toast.makeText(baseContext,"Employee Data Updated Successfully", Toast.LENGTH_SHORT).show()
                        }

                    }


                }

                override fun onCancelled(error: DatabaseError) {

                }

            })


        })



    }
}