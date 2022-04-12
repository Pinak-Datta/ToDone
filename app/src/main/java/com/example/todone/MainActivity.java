package com.example.todone;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText editTextTask = findViewById(R.id.editTextTask);
        Button addTaskBtn = findViewById(R.id.addTaskBtn);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        addTaskBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String newTask = editTextTask.getText().toString();

                if (newTask.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please add a task first.", Toast.LENGTH_SHORT).show();
                    return;
                }

                final TaskModel taskModel = new TaskModel();
                taskModel.setTaskDone(false);
                taskModel.setTask(newTask);

                db.collection("todoCollection")
                        .add(taskModel)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                editTextTask.setText(null);
                            }
                        });
            }
        });

    }


}