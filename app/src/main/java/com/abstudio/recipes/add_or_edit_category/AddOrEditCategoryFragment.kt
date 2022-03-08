package com.abstudio.recipes.add_or_edit_category

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.abstudio.recipes.R
import com.abstudio.recipes.data.entities.Category
import com.abstudio.recipes.utils.hideSoftKeyboard
import com.abstudio.recipes.utils.showSoftKeyboard
import com.google.android.material.textfield.TextInputEditText


class AddOrEditCategoryFragment : DialogFragment() {

    private lateinit var category_name_edit: TextInputEditText
    private lateinit var category_photo_img: ImageView
    private lateinit var btn_cancel: Button
    private lateinit var btn_ok: Button

    private val addOrEditCategoryViewModel: AddOrEditCategoryViewModel by viewModels()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val inflater = requireActivity().layoutInflater
        val view = inflater.inflate(R.layout.fragment_add_or_edit_category, null)

        findViewsById(view)
        setupViews()

        showSoftKeyboard(requireActivity())

        return buildDialog()
    }

    private fun findViewsById(view: View) {
        category_name_edit = view.findViewById(R.id.category_name_edit)
        category_photo_img = view.findViewById(R.id.category_photo_img)
        btn_cancel = view.findViewById(R.id.btn_cancel)
        btn_ok = view.findViewById(R.id.btn_ok)
    }

    private fun setupViews() {
        btn_cancel.setOnClickListener(View.OnClickListener {
            cancel()
        })

        btn_ok.setOnClickListener(View.OnClickListener {
            addCategory()
        })
    }

    private fun cancel() {
        hideSoftKeyboard(requireActivity())
        dismiss()
    }

    private fun addCategory() {
        val categoryName = category_name_edit.text.toString()

        val validation = addOrEditCategoryViewModel.verifyDataFromUser(categoryName)

        if (validation) {
            val newCategory = Category(categoryName)
            addOrEditCategoryViewModel.addCategory(newCategory)
            Toast.makeText(requireContext(), getString(R.string.category_added), Toast.LENGTH_SHORT)
                .show()
            hideSoftKeyboard(requireActivity())
            //findNavController().navigate(R.id.action_addOrEditCategoryFragment_to_categoriesListFragment)
            dismiss()
        } else {
            Toast.makeText(
                requireContext(),
                getString(R.string.input_category_name),
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun buildDialog(): AlertDialog {
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
        textView.setBackgroundColor(getResources().getColor(R.color.color_primary))
        textView.setTextColor(getResources().getColor(R.color.white))
        return textView
    }
}