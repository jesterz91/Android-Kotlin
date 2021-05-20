package io.github.jesterz91.pagingwithroom.ui

import android.os.Bundle
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import io.github.jesterz91.pagingwithroom.databinding.ActivityCheeseBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class CheeseActivity : AppCompatActivity() {

    private val viewModel by viewModel<CheeseViewModel>()

    private val binding: ActivityCheeseBinding by lazy { ActivityCheeseBinding.inflate(layoutInflater) }

    private val cheeseAdapter: CheeseAdapter = CheeseAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.cheeseListView.run {
            setHasFixedSize(true)
            adapter = cheeseAdapter
        }

        lifecycleScope.launch {
            viewModel.stream.collectLatest(cheeseAdapter::submitData)
        }

        initAddButtonListener() // 아이템 추가 설정

        initSwipeToDelete() // 스와이프로 아이템 삭제
    }

    private fun addCheese() {
        val newCheese = binding.inputText.text.trim()

        if (newCheese.isNotEmpty()) {
            viewModel.insert(newCheese)
            binding.inputText.setText("")
        }
    }

    private fun initAddButtonListener(): Unit = with(binding) {
        addButton.setOnClickListener { addCheese() }

        inputText.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                addCheese()
                return@setOnEditorActionListener true
            }
            false
        }

        inputText.setOnKeyListener { _, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                addCheese()
                return@setOnKeyListener true
            }
            false
        }
    }

    private fun initSwipeToDelete() {
        ItemTouchHelper(object : ItemTouchHelper.Callback() {
            // 아이템 스와이프 활성화, 좌측 또는 우측으로
            override fun getMovementFlags(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder
            ): Int =
                makeMovementFlags(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT)

            override fun onMove(
                recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean = false

            // 스와이프 완료시 아이템 삭제
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                (viewHolder as? CheeseAdapter.CheeseViewHolder)?.cheese?.run(viewModel::remove)
            }
        }).attachToRecyclerView(binding.cheeseListView)
    }
}
