package io.github.jesterz91.room.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.github.jesterz91.room.data.entity.Word
import io.github.jesterz91.room.databinding.ItemWordBinding

class WordListAdapter(private val onWordDelete: (Word) -> Unit) : RecyclerView.Adapter<WordListAdapter.WordViewHolder>() {

    private val words = mutableListOf<Word>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        val biding = ItemWordBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WordViewHolder(biding).apply {
            binding.deleteWord.setOnClickListener {
                onWordDelete(words[bindingAdapterPosition])
            }
        }
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) = holder.bind(words[position])

    internal fun setWords(words: List<Word>) {
        this.words.clear()
        this.words.addAll(words)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = words.size

    inner class WordViewHolder(val binding: ItemWordBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(word: Word) {
            binding.textView.text = word.word
        }
    }
}