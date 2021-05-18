package com.example.mytodolist

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageButton
import android.widget.RadioGroup
import androidx.navigation.fragment.findNavController
import com.example.mytodolist.data.IMPORTANCE
import com.example.mytodolist.data.TaskItem
import com.example.mytodolist.data.Tasks
import com.google.android.material.floatingactionbutton.FloatingActionButton

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AddTaskFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddTaskFragment : Fragment() {
    lateinit var importanceGroup: RadioGroup
    lateinit var titleInput: EditText
    lateinit var descriptionInput: EditText


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_task, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        importanceGroup = view.findViewById(R.id.importanceGroup)
        titleInput = view.findViewById(R.id.titleInput)
        descriptionInput = view.findViewById(R.id.descriptionInput)
        val saveButton = view.findViewById<ImageButton>(R.id.saveButton)
        saveButton.setOnClickListener{
            saveTask()
        }
        //var addButton = view.findViewById<FloatingActionButton>(R.id.addButton)
        //addButton.hide()
    }

    private fun saveTask(){
        var title: String = titleInput.text.toString()
        var description: String = descriptionInput.text.toString()
        val importance = when(importanceGroup.checkedRadioButtonId){
            R.id.low_radioButton -> IMPORTANCE.LOW
            R.id.normal_radioButton -> IMPORTANCE.NORMAL
            R.id.high_radioButton -> IMPORTANCE.HIGH
            else -> IMPORTANCE.NORMAL
        }
        if(title.isEmpty()) title = getString(R.string.default_task_title)+" ${Tasks.ITEMS.size + 1}"
        if(description.isEmpty()) description = getString(R.string.no_desc_msg)
        val taskItem = TaskItem({title + description}.hashCode().toString(),title,description,importance)
        Tasks.addTask(taskItem)

        val inputMethodManager: InputMethodManager = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(titleInput.windowToken, 0)
        findNavController().popBackStack()
        // findNavController().navigate(R.id.action_addTaskFragment_to_taskFragment)
    }


}