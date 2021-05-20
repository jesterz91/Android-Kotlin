package io.github.jesterz91.room.ui.dialog

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import io.github.jesterz91.room.databinding.DialogWordInsertBinding
import io.github.jesterz91.room.extension.listener

class WordInsertDialog : DialogFragment() {

    interface OnWordInsertListener {
        fun onWordInsert(word: String)
    }

    private val listener: OnWordInsertListener? by listener<OnWordInsertListener>()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val binding = DialogWordInsertBinding.inflate(LayoutInflater.from(context))
        return AlertDialog.Builder(requireActivity())
            .setTitle("단어 추가")
            .setView(binding.root)
            .setPositiveButton("확인") { _, _ ->
                 listener?.onWordInsert("${binding.updateWordEdit.text}")
            }
            .setNegativeButton("취소", null)
            .create()
    }

    companion object {
        const val TAG = "WordInsertDialog"
    }
}
