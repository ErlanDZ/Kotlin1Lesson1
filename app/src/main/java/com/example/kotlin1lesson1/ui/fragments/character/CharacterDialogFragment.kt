package com.example.kotlin1lesson1.ui.fragments.character

import android.app.AlertDialog
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.bumptech.glide.Glide
import com.example.kotlin1lesson1.R
import com.example.kotlin1lesson1.databinding.FragmentCharacterDialogBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterDialogFragment : DialogFragment() {
    private var _binding: FragmentCharacterDialogBinding? = null

    private val binding get() = _binding!!

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        _binding = FragmentCharacterDialogBinding.inflate(LayoutInflater.from(context))
        val builder = AlertDialog.Builder(activity)
            .setView(binding.root)
            .create()
        builder.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        setupArgs()
        return builder
    }

    private fun setupArgs() {
        Glide.with(binding.imageDialog)
            .load(CharacterDialogFragmentArgs.fromBundle(requireArguments()).image)
            .into(binding.imageDialog)

    }
}