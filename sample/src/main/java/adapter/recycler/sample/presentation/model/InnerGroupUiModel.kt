package adapter.recycler.sample.presentation.model

import adapter.recycler.single.BindingClass
import adapter.recycler.single.util.checkChanges

data class InnerItemUiModel(
    override val itemId: Long,
    val title: String,
    val description: String
) : BindingClass {

    override fun getChangePayload(newItem: BindingClass): List<InnerItemPayload> {
        val item = newItem as? InnerItemUiModel
        return checkChanges(
            mapOf(
                InnerItemPayload.Title to (title != item?.title),
                InnerItemPayload.Description to (description != item?.description)
            )
        )
    }
}

sealed class InnerItemPayload {
    object Title : InnerItemPayload()
    object Description : InnerItemPayload()
}