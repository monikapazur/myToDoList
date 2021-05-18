package com.example.mytodolist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.mytodolist.data.IMPORTANCE
import com.example.mytodolist.data.IMPORTANCE.*

class DisplayTaskFragment : Fragment() {
val args: DisplayTaskFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_display_task, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val task = args.task
        view.findViewById<TextView>(R.id.displayTitle).text = task.title
        view.findViewById<TextView>(R.id.displayDescription).text = task.description

        val resource = when(task.importance){
            LOW -> R.drawable.circle_drawable_green
            NORMAL -> R.drawable.circle_drawable_orange
            HIGH -> R.drawable.circle_drawable_red
        }
        view.findViewById<ImageView>(R.id.displayImportance).setImageResource(resource)
    }
}