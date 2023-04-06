package adapter.recycler.sample.presentation

import adapter.recycler.sample.R
import adapter.recycler.sample.data.SampleRecyclerDataFactory
import adapter.recycler.sample.presentation.model.GroupUiModel
import adapter.recycler.sample.presentation.model.HeaderUiModel
import adapter.recycler.sample.presentation.view_holder.GroupViewHolderFactory
import adapter.recycler.sample.presentation.view_holder.HeaderViewHolderFactory
import adapter.recycler.single.bindWith
import adapter.recycler.single.binderAdapterOf
import adapter.recycler.single.extensions.adapter.setBindingList
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val dataFactory = SampleRecyclerDataFactory()

    private val recycler: RecyclerView
        get() = findViewById(R.id.recycler)
    private val button: AppCompatButton
        get() = findViewById(R.id.button)

    private var isFirstData = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        with(recycler) {
            adapter = binderAdapterOf(
                HeaderUiModel::class bindWith HeaderViewHolderFactory(),
                GroupUiModel::class bindWith GroupViewHolderFactory(
                    action = { title ->
                        Toast.makeText(context, "Clicked $title", Toast.LENGTH_SHORT).show()
                    }
                )
            )
            setBindingList(dataFactory.createGroups())
        }
    }

    override fun onStart() {
        super.onStart()

        button.setOnClickListener {
            recycler.setBindingList(
                if (isFirstData) dataFactory.createOtherGroups()
                else dataFactory.createGroups()
            )
            isFirstData = isFirstData.not()
        }
    }
}