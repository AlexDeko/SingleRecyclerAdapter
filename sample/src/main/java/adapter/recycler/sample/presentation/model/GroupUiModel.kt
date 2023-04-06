package adapter.recycler.sample.presentation.model

import adapter.recycler.single.BindingClass
import adapter.recycler.single.util.checkChanges

data class GroupUiModel(
    val title: String,
    val items: List<InnerItemUiModel>
) : BindingClass {
    override val itemId: Long = title.hashCode().toLong()

    override fun getChangePayload(newItem: BindingClass): List<GroupPayload> {
        val item = newItem as? GroupUiModel
        return checkChanges(
            mapOf(
                GroupPayload.ItemsChanged to (items != item?.items)
            )
        )
    }
}

sealed class GroupPayload {
    object ItemsChanged : GroupPayload()
}