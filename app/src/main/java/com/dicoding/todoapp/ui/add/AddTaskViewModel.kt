package com.dicoding.todoapp.ui.add

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.todoapp.data.Task
import com.dicoding.todoapp.data.TaskRepository
import kotlinx.coroutines.launch

class AddTaskViewModel(private val taskRepository: TaskRepository): ViewModel() {

    private val _taskId = MutableLiveData<Long?>(null)
    val taskId: LiveData<Long?> = _taskId

    fun addTask(task: Task) {
        viewModelScope.launch {
            val id = taskRepository.insertTask(task)
            _taskId.postValue(id)
        }
    }
}