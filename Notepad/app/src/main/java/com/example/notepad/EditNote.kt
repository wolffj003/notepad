package com.example.notepad

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_edit_note.*
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class EditNote : AppCompatActivity() {

    private lateinit var editViewModel: EditNoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_note)

        supportActionBar?.title = "Edit Notes"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        initViews()
        initViewModel()
    }

    private fun initViews() {
        fabSaveNote.setOnClickListener {

            editViewModel.notes.value?.apply {
                notesTitle = tiletNoteTitle.text.toString()
                notesLastUpdated = Date()
                notesText = tiletNoteText.text.toString()
            }

            editViewModel.updateNotes()
        }
    }

    private fun initViewModel() {
        editViewModel = ViewModelProvider(this).get(EditNoteViewModel::class.java)
        editViewModel.notes.value = intent.extras?.getParcelable(EXTRA_NOTE)!!

        editViewModel.notes.observe(this, Observer { notes ->
            if (notes != null) {
                tiletNoteTitle.setText(notes.notesTitle)
                tiletNoteText.setText(notes.notesText)
            }
        })

        editViewModel.error.observe(this, Observer { message ->
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        })

        editViewModel.success.observe(this, Observer { success ->
            if (success) finish()
        })
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> { // Used to identify when the user has clicked the back button
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    companion object {
        const val EXTRA_NOTE = "EXTRA_NOTE"
    }
}