package com.dicoding.todoapp.ui.detail

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.dicoding.todoapp.R
import com.dicoding.todoapp.data.Task
import com.dicoding.todoapp.ui.ViewModelFactory
import com.dicoding.todoapp.utils.DateConverter
import com.dicoding.todoapp.utils.TASK_ID
import com.google.android.material.textfield.TextInputEditText

class DetailTaskActivity : AppCompatActivity() {

    private lateinit var detailTaskViewModel: DetailTaskViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_detail)

        val factory = ViewModelFactory.getInstance(this)
        detailTaskViewModel = ViewModelProvider(this, factory)[DetailTaskViewModel::class.java]
        val id = intent.getIntExtra(TASK_ID, 0)
        Log.d(this.toString(), "onCreate: $id")
        detailTaskViewModel.setTaskId(id)

        observeViewModel()
        setDeleteButton()
    }


    private fun observeViewModel() {
        detailTaskViewModel.task.observe(this) { task ->
            Log.d(this.toString(), "observe: $task")
            task?.let {
                setDetailTask(it)
            }
        }
    }

    private fun setDeleteButton() {
        val delete = findViewById<Button>(R.id.btn_delete_task)
        delete.setOnClickListener {
            detailTaskViewModel.deleteTask()
            finish()
        }
    }

    private fun setDetailTask(task: Task) {
        val title = findViewById<TextInputEditText>(R.id.detail_ed_title)
        val description = findViewById<TextInputEditText>(R.id.detail_ed_description)
        val dueDate = findViewById<TextInputEditText>(R.id.detail_ed_due_date)
        title.setText(task.title)
        description.setText(task.description)
        dueDate.setText(DateConverter.convertMillisToString(task.dueDateMillis))
    }
}