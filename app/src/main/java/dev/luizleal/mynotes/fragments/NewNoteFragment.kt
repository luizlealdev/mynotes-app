package dev.luizleal.mynotes.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dev.luizleal.mynotes.R
import dev.luizleal.mynotes.databinding.FragmentNewNoteBinding

class NewNoteFragment : Fragment() {
    private var newNoteBinding: FragmentNewNoteBinding? = null
    private val binding get() = newNoteBinding!!

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
    }

    override fun onDestroy() {
        super.onDestroy()
        newNoteBinding = null
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

}