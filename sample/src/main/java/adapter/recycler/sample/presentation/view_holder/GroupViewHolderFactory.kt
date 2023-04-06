package adapter.recycler.sample.presentation.view_holder

import adapter.recycler.sample.databinding.RecyclerItemGroupBinding
import adapter.recycler.sample.presentation.model.GroupPayload
import adapter.recycler.sample.presentation.model.GroupUiModel
import adapter.recycler.sample.presentation.model.InnerItemUiModel
import adapter.recycler.single.BindingViewHolder
import adapter.recycler.single.ViewHolderFactory
import adapter.recycler.single.bindWith
import adapter.recycler.single.binderAdapterOf
import adapter.recycler.single.extensions.adapter.setBindingList
import adapter.recycler.single.util.check
import android.view.LayoutInflater
import android.view.ViewGroup

class GroupViewHolderFactory(
    private val action: (title: String) -> Unit
) : ViewHolderFactory<RecyclerItemGroupBinding, GroupUiModel> {

    override fun create(
        parent: ViewGroup
    ) = BindingViewHolder<GroupUiModel, RecyclerItemGroupBinding>(
        RecyclerItemGroupBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    ).apply {
        with(binding) {
            root.setOnClickListener {
                action(item.title)
            }

            recycler.adapter = binderAdapterOf(
                InnerItemUiModel::class bindWith InnerGroupViewHolderFactory()
            )
        }
    }

    override fun bind(
        binding: RecyclerItemGroupBinding,
        model: GroupUiModel,
        payloads: List<Any>
    ) = when {
        payloads.isNotEmpty() -> payloads.check { payload ->
            when (payload) {
                GroupPayload.ItemsChanged -> binding.recycler.setBindingList(model.items)
            }
        }
        else -> with(binding) {
            recycler.setBindingList(model.items)
            title.text = model.title
        }
    }
}