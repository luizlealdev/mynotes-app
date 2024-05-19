package dev.luizleal.mynotes.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dev.luizleal.mynotes.MainActivity
import dev.luizleal.mynotes.R
import dev.luizleal.mynotes.databinding.FragmentNewNoteBinding
import dev.luizleal.mynotes.model.Note
import dev.luizleal.mynotes.viewModel.NoteViewModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class NewNoteFragment : Fragment(R.layout.fragment_new_note) {
    private var newNoteBinding: FragmentNewNoteBinding? = null
    private val binding get() = newNoteBinding!!

    private lateinit var notesViewModel: NoteViewModel
    private lateinit var newNoteView: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        newNoteBinding = FragmentNewNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonGoBackIcon.setOnClickListener(handleGoBack(requireContext()))

        binding.fabNewNote.setOnClickListener {
            saveNote(newNoteView)
        }

        notesViewModel = (activity as MainActivity).noteViewModel

        newNoteView = view
    }

    override fun onDestroy() {
        super.onDestroy()
        newNoteBinding = null
    }

    private fun saveNote(view: View) {
        val noteTitle = binding.editNoteTitle.text.toString().trim()
        val noteDescription = binding.editNoteDescription.text.toString().trim()
        val noteLastChangeDate = getCurrentDate()

        if (noteTitle.isNotEmpty()) {
            val note = Note(0, noteTitle, noteDescription, noteLastChangeDate)
            notesViewModel.addNote(note)

            Toast.makeText(newNoteView.context, getString(R.string.note_saved), Toast.LENGTH_SHORT).show()
            view.findNavController().popBackStack(R.id.homeFragment, false)
        } else
            Toast.makeText(newNoteView.context, getString(R.string.no_title_notice), Toast.LENGTH_SHORT)
                .show()
    }

    private fun handleGoBack(context: Context): View.OnClickListener {
        return View.OnClickListener { view ->
            MaterialAlertDialogBuilder(context, R.style.AlertDialogStyle)
                .setTitle(getString(R.string.confirm_exit))
                .setMessage(getString(R.string.exit_new_note))
                .setPositiveButton(getString(R.string.yes)) { _, _ ->
                    view.findNavController().popBackStack(R.id.homeFragment, false)
                }
                .setNegativeButton(getString(R.string.no), null)
                .show()
        }
    }

    private fun getCurrentDate(): String {
        val currentDate = Calendar.getInstance()
        val dateFormat = SimpleDateFormat("d MMM", Locale.getDefault())
        return dateFormat.format(currentDate.time)
    }

}