package com.abstudio.recipes.add_or_edit_category_fragment

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.abstudio.recipes.R


class AddOrEditCategoryFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val inflater = requireActivity().layoutInflater
        val view = inflater.inflate(R.layout.fragment_add_or_edit_category, null)

        val builder: AlertDialog.Builder = AlertDialog.Builder(requireContext())
        builder.setView(view)
        builder.setTitle(R.string.add_category_dialog_title)
        builder.setCustomTitle(makeCustomTitle(requireContext()));

        val dialog: AlertDialog = builder.create()
        dialog.setCanceledOnTouchOutside(false)
        return dialog
    }

    private fun makeCustomTitle(context: Context): TextView {
        val textView = TextView(context)
        textView.setText(R.string.add_category_dialog_title)
        textView.setPadding(20, 30, 20, 30)
        textView.setTextSize(20F)
        textView.setBackgroundColor(getResources().getColor(R.color.color_primary_dark))
        textView.setTextColor(getResources().getColor(R.color.white))
        return textView
    }

}