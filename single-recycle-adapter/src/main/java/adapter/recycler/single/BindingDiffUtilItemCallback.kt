package adapter.recycler.single

import androidx.recyclerview.widget.DiffUtil

internal class BindingDiffUtilItemCallback : DiffUtil.ItemCallback<BindingClass>() {

    override fun areItemsTheSame(oldItem: BindingClass, newItem: BindingClass): Boolean =
        oldItem.areItemsTheSame(newItem)

    override fun areContentsTheSame(
        oldItem: BindingClass,
        newItem: BindingClass
    ): Boolean = oldItem.areContentsTheSame(newItem)

    override fun getChangePayload(
        oldItem: BindingClass,
        newItem: BindingClass
    ): Any = oldItem.getChangePayload(newItem)
}