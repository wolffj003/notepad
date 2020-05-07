package com.example.notepad

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var mainActivityViewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
        initViewModel()
    }

    private fun initViews() {
        fabAddNote.setOnClickListener { startEditNoteActivity() }
    }

    private fun initViewModel() {
        mainActivityViewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        mainActivityViewModel.notes.observe(this, Observer { notes ->
            if (notes != null) {
                tvNoteTitle.text = notes.notesTitle
                tvDate.text = getString(R.string.tvDate, notes.notesLastUpdated.toString())
                tvNoteText.text = notes.notesText
            }
        })
    }


    private fun startEditNoteActivity() {
        val intent = Intent(this, EditNote::class.java)
        intent.putExtra(EditNote.EXTRA_NOTE, mainActivityViewModel.notes.value)

        startActivity(intent)
    }
}
