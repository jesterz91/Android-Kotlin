package io.github.jesterz91.room.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import io.github.jesterz91.room.R
import io.github.jesterz91.room.data.WordRepository
import io.github.jesterz91.room.data.WordRepositoryImpl
import io.github.jesterz91.room.data.db.WordDatabase
import io.github.jesterz91.room.data.entity.Word
import io.github.jesterz91.room.databinding.ActivityWordListBinding
import io.github.jesterz91.room.ui.dialog.WordInsertDialog
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class WordListActivity : AppCompatActivity(), WordInsertDialog.OnWordInsertListener {

    private val db: WordDatabase by lazy { WordDatabase.getInstance(applicationContext) }

    private val repository: WordRepository by lazy { WordRepositoryImpl(db.wordDao()) }

    private val binding: ActivityWordListBinding by lazy { ActivityWordListBinding.inflate(layoutInflater) }

    private val wordListViewModel: WordListViewModel by viewModels {
        object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return WordListViewModel(repository) as T
            }
        }
    }

    private val wordListAdapter: WordListAdapter = WordListAdapter(::onWordDelete)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launch {
            wordListViewModel.allWordList.collect(wordListAdapter::setWords)
        }

        with(binding) {
            setContentView(root)
            recyclerView.setHasFixedSize(true)
            recyclerView.adapter = wordListAdapter
            fab.setOnClickListener { showWordInsertDialog() }
        }
    }

    private fun showWordInsertDialog() = WordInsertDialog().show(supportFragmentManager, WordInsertDialog.TAG)

    override fun onWordInsert(word: String) = wordListViewModel.insert(Word(word = word))

    private fun onWordDelete(word: Word) = wordListViewModel.delete(word)

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menuDeleteAll -> {
                wordListViewModel.deleteAll()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
