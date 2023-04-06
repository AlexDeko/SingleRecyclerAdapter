package adapter.recycler.sample.presentation.view_holder

import adapter.recycler.sample.databinding.RecyclerItemInnerGroupBinding
import adapter.recycler.sample.presentation.model.InnerItemPayload
import adapter.recycler.sample.presentation.model.InnerItemUiModel
import adapter.recycler.single.BindingViewHolder
import adapter.recycler.single.ViewHolderFactory
import adapter.recycler.single.util.check
import android.view.LayoutInflater
import android.view.ViewGroup

class InnerGroupViewHolderFactory :
    ViewHolderFactory<RecyclerItemInnerGroupBinding, InnerItemUiModel> {

    override fun create(
        parent: ViewGroup
    ): BindingViewHolder<InnerItemUiModel, RecyclerItemInnerGroupBinding> = BindingViewHolder(
        RecyclerItemInnerGroupBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun bind(
        binding: RecyclerItemInnerGroupBinding,
        model: InnerItemUiModel,
        payloads: List<Any>
    ) = when {
        payloads.isNotEmpty() -> payloads.check { payload ->
            when (payload) {
                is InnerItemPayload.Title -> binding.title.text = model.title
                is InnerItemPayload.Description -> binding.description.text = model.description
            }
        }
        else -> with(binding) {
            title.text = model.title
            description.text = model.description
        }
    }
}