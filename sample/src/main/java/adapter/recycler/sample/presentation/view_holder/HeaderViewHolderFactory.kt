package adapter.recycler.sample.presentation.view_holder

import adapter.recycler.sample.databinding.RecyclerItemHeaderBinding
import adapter.recycler.sample.presentation.model.HeaderUiModel
import adapter.recycler.single.BindingViewHolder
import adapter.recycler.single.ViewHolderFactory
import android.view.LayoutInflater
import android.view.ViewGroup

class HeaderViewHolderFactory : ViewHolderFactory<RecyclerItemHeaderBinding, HeaderUiModel> {

    override fun create(
        parent: ViewGroup
    ): BindingViewHolder<HeaderUiModel, RecyclerItemHeaderBinding> = BindingViewHolder(
        RecyclerItemHeaderBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun bind(
        binding: RecyclerItemHeaderBinding,
        model: HeaderUiModel,
        payloads: List<Any>
    ) {
        binding.headerText.text = model.title
    }
}