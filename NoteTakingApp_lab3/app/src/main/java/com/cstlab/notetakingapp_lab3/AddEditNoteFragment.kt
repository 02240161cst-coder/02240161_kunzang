package com.cstlab.notetakingapp_lab3


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment

class AddEditNoteFragment : Fragment() {

    private lateinit var titleInput: EditText
    private lateinit var contentInput: EditText

    private var noteId: Int = -1

    private var isEditing: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        noteId = arguments?.getInt(ARG_NOTE_ID, -1) ?: -1

        isEditing = noteId != -1
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return inflater.inflate(
            R.layout.fragment_add_edit_note,
            container,
            false
        )
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {

        super.onViewCreated(view, savedInstanceState)

        titleInput = view.findViewById(R.id.titleInput)
        contentInput = view.findViewById(R.id.contentInput)

        val saveButton =
            view.findViewById<Button>(R.id.saveButton)

        val cancelButton =
            view.findViewById<Button>(R.id.cancelButton)

        if (isEditing) {

            val note = NoteRepository.getNote(noteId)

            note?.let {
                titleInput.setText(it.title)
                contentInput.setText(it.content)
            }
        }

        saveButton.setOnClickListener {
            saveNote()
        }

        cancelButton.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }

    private fun saveNote() {

        val title = titleInput.text.toString().trim()
        val content = contentInput.text.toString().trim()

        if (title.isEmpty()) {

            titleInput.error = "Title is required"
            titleInput.requestFocus()

            return
        }

        if (content.isEmpty()) {

            contentInput.error = "Content is required"
            contentInput.requestFocus()

            return
        }

        if (isEditing) {

            NoteRepository.updateNote(
                noteId,
                title,
                content
            )

        } else {

            NoteRepository.addNote(
                title,
                content
            )
        }

        parentFragmentManager.popBackStack()
    }

    companion object {

        private const val ARG_NOTE_ID = "note_id"

        fun newInstance(noteId: Int): AddEditNoteFragment {

            val fragment = AddEditNoteFragment()

            val bundle = Bundle()

            bundle.putInt(
                ARG_NOTE_ID,
                noteId
            )

            fragment.arguments = bundle

            return fragment
        }
    }
}