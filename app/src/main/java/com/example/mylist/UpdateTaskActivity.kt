package com.example.mylist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import com.example.mylist.databinding.ActivityUpdateTaskBinding


class UpdateTaskActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateTaskBinding
    private lateinit var db:DataManager
    private var taskID: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = DataManager(this)

        taskID = intent.getIntExtra("task_id", -1)
        if (taskID == -1){
            finish()
            return
        }

        val task = db.getTaskByID(taskID)
        binding.updateTitleEditText.setText(task.title)
        binding.UpdateContentEditText.setText(task.content)

        binding.UpdateSaveButton.setOnClickListener {
            val newTitle = binding.updateTitleEditText.text.toString()
            val newContent = binding.UpdateContentEditText.text.toString()
            val updateTask = Task(taskID, newTitle, newContent)
            db.updateTask(updateTask)
            finish()
            Toast.makeText(this, "Changes Saved", Toast.LENGTH_SHORT).show()
        }

    }
}