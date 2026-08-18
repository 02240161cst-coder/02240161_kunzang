package com.cstlab.notetakingapp_lab3


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import android.widget.TextView

class NoteListFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var emptyMessage: TextView
    private lateinit var adapter: NoteAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(
            R.layout.fragment_note_list,
            container,
            false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.recyclerView)
        emptyMessage = view.findViewById(R.id.emptyMessage)

        adapter = NoteAdapter(
            NoteRepository.notes,
            onNoteClick = { note ->
                openEditNote(note)
            },
            onDeleteClick = { note ->
                showDeleteConfirmation(note)
            }
        )

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter

        val addButton = view.findViewById<FloatingActionButton>(R.id.addButton)

        addButton.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(
                    R.id.fragment_container,
                    AddEditNoteFragment()
                )
                .addToBackStack(null)
                .commit()
        }

        updateEmptyMessage()
    }

    override fun onResume() {
        super.onResume()

        if (::adapter.isInitialized) {
            adapter.notifyDataSetChanged()
            updateEmptyMessage()
        }
    }

    private fun openEditNote(note: Note) {

        val fragment = AddEditNoteFragment.newInstance(note.id)

        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }

    private fun showDeleteConfirmation(note: Note) {

        androidx.appcompat.app.AlertDialog.Builder(requireContext())
            .setTitle("Delete Note")
            .setMessage("Are you sure you want to delete this note?")
            .setPositiveButton("Delete") { _, _ ->

                NoteRepository.deleteNote(note.id)

                adapter.notifyDataSetChanged()
                updateEmptyMessage()
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun updateEmptyMessage() {

        if (NoteRepository.notes.isEmpty()) {
            emptyMessage.visibility = View.VISIBLE
            recyclerView.visibility = View.GONE
        } else {
            emptyMessage.visibility = View.GONE
            recyclerView.visibility = View.VISIBLE
        }
    }
}