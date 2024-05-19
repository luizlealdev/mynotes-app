package dev.luizleal.mynotes.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import dev.luizleal.mynotes.MainActivity
import dev.luizleal.mynotes.R
import dev.luizleal.mynotes.databinding.FragmentEditNoteBinding
import dev.luizleal.mynotes.model.Note
import dev.luizleal.mynotes.viewModel.NoteViewModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class EditNoteFragment : Fragment(R.layout.fragment_edit_note) {
    private var editNoteBinding: FragmentEditNoteBinding? = null
    private val binding get() = editNoteBinding!!

    //private lateinit var editNoteView: View
    private lateinit var notesViewModel: NoteViewModel
    private lateinit var currentNote: Note

    private val args: EditNoteFragmentArgs by navArgs()

    private lateinit var noteTitle: String
    private lateinit var noteDescription: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        editNoteBinding = FragmentEditNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        notesViewModel = (activity as MainActivity).noteViewModel

        //editNoteView = view

        currentNote = args.note!!

        noteTitle = currentNote.noteTitle
        noteDescription = currentNote.noteDescription

        binding.editNoteTitle.setText(noteTitle)
        binding.editNoteDescription.setText(noteDescription)

        binding.fabEditNote.setOnClickListener(saveNote(requireContext()))
    }

    override fun onDestroy() {
        super.onDestroy()
        editNoteBinding = null
    }

    private fun saveNote(context: Context): View.OnClickListener {
        return View.OnClickListener { view ->
            val newNoteTitle = binding.editNoteTitle.text.toString().trim()
            val newNoteDescription = binding.editNoteDescription.text.toString().trim()
            val newNoteLastChangeDate = getCurrentDate()

            if (noteTitle.isNotEmpty()) {
                val newNote =
                    Note(currentNote.id, newNoteTitle, newNoteDescription, newNoteLastChangeDate)
                notesViewModel.updateNote(newNote)
                view.findNavController().popBackStack(R.id.homeFragment, false)
            } else {
                Toast.makeText(context, getString(R.string.no_title_notice), Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun getCurrentDate(): String {
        val currentDate = Calendar.getInstance()
        val dateFormat = SimpleDateFormat("d MMM", Locale.getDefault())
        return dateFormat.format(currentDate.time)
    }
}