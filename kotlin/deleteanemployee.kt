package com.example.projectfinalwad

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase

class deleteanemployee : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_deleteanemployee)

        var deletebuttom: Button =findViewById(R.id.delete)
        var field1: EditText =findViewById(R.id.firstname)
        var field2: EditText =findViewById(R.id.lastname)

        deletebuttom.setOnClickListener({
            val database = Firebase.database
            val myRef = database.getReference("EmployeesData")

            myRef.addValueEventListener(object: ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {


                    //data is in snapshot

                    for (obj in snapshot.children) {
                        var temp = obj.getValue<employeeclass>()
                        if (temp?.firstname == field1.text.toString() && temp?.lastname == field2.text.toString()) {
                            obj.ref.removeValue()
                            Toast.makeText(baseContext,"Employee Deleted Successfully",Toast.LENGTH_SHORT).show()
                        }

                    }


                }

                override fun onCancelled(error: DatabaseError) {

                }

            })
        })

    }
}
