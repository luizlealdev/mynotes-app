package dev.luizleal.mynotes.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import dev.luizleal.mynotes.MainActivity
import dev.luizleal.mynotes.R
import dev.luizleal.mynotes.adapter.NoteAdapter
import dev.luizleal.mynotes.databinding.FragmentHomeBinding
import dev.luizleal.mynotes.model.Note
import dev.luizleal.mynotes.viewModel.NoteViewModel

class HomeFragment : Fragment(R.layout.fragment_home) {

    private var homeBinding: FragmentHomeBinding? = null
    private val binding get() = homeBinding!!

    private lateinit var notesViewModel: NoteViewModel
    private lateinit var noteAdapter: NoteAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        homeBinding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        notesViewModel = (activity as MainActivity).noteViewModel

        binding.fabNewNote.setOnClickListener {
            it.findNavController().navigate(R.id.action_homeFragment_to_newNoteFragment)
        }
        searchNote()
        setupHomeRecyclerView()
    }

    override fun onDestroy() {
        super.onDestroy()
        homeBinding = null
    }

    private fun updateUI(note: List<Note>?) {
        if (note != null) {
            if (note.isNotEmpty()) {
                binding.textNoNotesAlert.visibility = View.GONE
                binding.recyclerviewNotes.visibility = View.VISIBLE
            } else {
                binding.textNoNotesAlert.visibility = View.VISIBLE
                binding.recyclerviewNotes.visibility = View.GONE
            }
        }
    }

    private fun setupHomeRecyclerView() {
        noteAdapter = NoteAdapter()
        binding.recyclerviewNotes.apply {
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            setHasFixedSize(true)
            adapter = noteAdapter
        }

        activity?.let {
            notesViewModel.getAllNotes().observe(viewLifecycleOwner) { note ->
                noteAdapter.differ.submitList(note)
                updateUI(note)
            }
        }
    }

    private fun searchNote() {
        binding.editNoteSearch.setOnFocusChangeListener { view, hasFocus ->
            if (hasFocus) {
                binding.editNoteSearch.addTextChangedListener { text ->
                    text?.let {
                        notesViewModel.searchNote(it.toString())
                            .observe(viewLifecycleOwner) { list ->
                                noteAdapter.differ.submitList(list)
                            }
                    }
                }
            }
        }
    }

}