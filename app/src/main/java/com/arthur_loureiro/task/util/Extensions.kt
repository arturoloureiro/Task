package com.arthur_loureiro.task.util

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.arthur_loureiro.task.R
import com.arthur_loureiro.task.databinding.BottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialog

fun Fragment.initToolbar(toolbar: Toolbar) {
    (activity as AppCompatActivity).setSupportActionBar(toolbar)
    (activity as AppCompatActivity).title
    (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)

    toolbar.setNavigationOnClickListener {
        activity?.onBackPressedDispatcher?.onBackPressed()
    }
}

fun Fragment.showBottomSheet(
    titleDialog: Int? = null,
    titleButton: Int? = null,
    message: String,
    onClick: () -> Unit = {}
){
    val bottomSheetDialog = BottomSheetDialog(requireContext(), R.style.BottomSheetDialog)
    val binding: BottomSheetBinding =
        BottomSheetBinding.inflate( layoutInflater, null, false)

    binding.textviewTitle.text = getText( titleDialog ?: R.string.text_title_warning )

    binding.textviewMessage.text = message

    binding.buttonOk.text = getText( titleButton ?: R.string.text_button_warning)
    binding.buttonOk.setOnClickListener {
        //Quando o botão buttonOk é clicado: O código executa o que foi definido no onClick. Depois, fecha o Bottom Sheet.
        onClick() //callback
        bottomSheetDialog.dismiss()
    }

    bottomSheetDialog.setContentView(binding.root)
    bottomSheetDialog.show()
}


