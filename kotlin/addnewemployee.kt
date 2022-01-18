package com.example.projectfinalwad
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class addnewemployee : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addnewemployee)

        var savebutton: Button =findViewById(R.id.save)
        var field1: EditText =findViewById(R.id.first)
        var field2: EditText =findViewById(R.id.second)
        var field3: EditText =findViewById(R.id.thrid)
        var field4: EditText =findViewById(R.id.forth)
        var field5: EditText =findViewById(R.id.fiveth)

        savebutton.setOnClickListener({

            var firstname=field1.text.toString()
            var lastname=field2.text.toString()
            var empage=field3.text.toString()
            var emgsalary=field4.text.toString()
            var empjob=field5.text.toString()

            var Userdata:employeeclass= employeeclass()

            Userdata.initialize_the_data(firstname,lastname,empage,emgsalary,empjob)

            val database = Firebase.database
            val myRef = database.getReference("EmployeesData")
            val rnds = (0..1000).random()
            myRef.child(""+rnds).setValue(Userdata)
                .addOnCompleteListener({
                    TASK->
                    if(TASK.isSuccessful)
                    {
                        Toast.makeText(this,"Employee Add Successfull",Toast.LENGTH_SHORT).show()
                    }
                    else
                    {
                        Toast.makeText(this,"Can't Add Employee",Toast.LENGTH_SHORT).show()
                    }
                })
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        })

    }
}