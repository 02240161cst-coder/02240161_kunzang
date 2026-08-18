package com.cstlab.notetakingapp_lab3



import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NoteAdapter(
    private val notes: MutableList<Note>,
    private val onNoteClick: (Note) -> Unit,
    private val onDeleteClick: (Note) -> Unit
) : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    class NoteViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        val titleText: TextView =
            itemView.findViewById(R.id.noteTitle)

        val contentText: TextView =
            itemView.findViewById(R.id.noteContent)

        val deleteButton: ImageButton =
            itemView.findViewById(R.id.deleteButton)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NoteViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_note, parent, false)

        return NoteViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: NoteViewHolder,
        position: Int
    ) {

        val note = notes[position]

        holder.titleText.text = note.title

        holder.contentText.text = note.content

        holder.itemView.setOnClickListener {
            onNoteClick(note)
        }

        holder.deleteButton.setOnClickListener {
            onDeleteClick(note)
        }
    }

    override fun getItemCount(): Int {
        return notes.size
    }
}