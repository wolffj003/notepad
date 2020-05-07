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

    private lateinit var editNoteViewModel: EditNoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_note)

        initViews()
        initViewModels()
    }

    private fun initViews() {
        fabSaveNote.setOnClickListener {

            editNoteViewModel.notes.value?.apply {
                notesTitle = tiletNoteTitle.text.toString()
                notesLastUpdated = Date()
                notesText = tiletNoteText.text.toString()
            }

            editNoteViewModel.updateNotes()
        }
    }

    private fun initViewModels() {
        editNoteViewModel = ViewModelProvider(this).get(editNoteViewModel::class.java)
        editNoteViewModel.notes.value = intent.extras?.getParcelable(EXTRA_NOTE)!!

        editNoteViewModel.notes.observe(this, Observer { notes ->
            if (notes != null) {
                tiletNoteTitle.setText(notes.notesTitle)
                tiletNoteText.setText(notes.notesText)
            }
        })

        editNoteViewModel.error.observe(this, Observer { message ->
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        })

        editNoteViewModel.success.observe(this, Observer { success ->
            if (success) finish()
        })
    }


    override fun onOptionsItemSelected(item: MenuItem?): Boolean {  // Backbutton ff fixen
        return when (item?.itemId) {
            android.R.id.home -> {
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
