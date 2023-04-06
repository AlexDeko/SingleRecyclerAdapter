package adapter.recycler.single.extensions.adapter

import adapter.recycler.single.BindingClass
import adapter.recycler.single.SingleRecyclerAdapter
import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.setBindingList(
    bindingList: List<BindingClass>?
) {
    val bindingAdapter = adapter as? SingleRecyclerAdapter ?: return

    val newOrEmptyList = bindingList.orEmpty()

    bindingAdapter.setItems(newOrEmptyList)
}