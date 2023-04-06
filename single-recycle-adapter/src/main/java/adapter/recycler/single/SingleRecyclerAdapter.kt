package adapter.recycler.single

import android.view.ViewGroup
import androidx.collection.ArrayMap
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import kotlin.reflect.KClass

class SingleRecyclerAdapter(
    private val factory: ArrayMap<KClass<out BindingClass>, ViewHolderFactory<ViewBinding, BindingClass>>
) : RecyclerView.Adapter<BindingViewHolder<BindingClass, ViewBinding>>() {

    private val items
        get() = differ.currentList
    var differ: AsyncListDiffer<BindingClass> = AsyncListDiffer(
        this@SingleRecyclerAdapter,
        BindingDiffUtilItemCallback()
    )

    override fun getItemCount(): Int = differ.currentList.size

    override fun getItemViewType(position: Int): Int {
        val bindingItem = items.getOrNull(position) ?: return super.getItemViewType(position)

        return factory.indexOfKey(bindingItem::class)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BindingViewHolder<BindingClass, ViewBinding> =
        factory.valueAt(viewType).create(parent)

    fun setItems(items: List<BindingClass>) = differ.submitList(items)

    override fun onBindViewHolder(
        holder: BindingViewHolder<BindingClass, ViewBinding>,
        position: Int
    ) = Unit

    override fun onBindViewHolder(
        holder: BindingViewHolder<BindingClass, ViewBinding>,
        position: Int,
        payloads: MutableList<Any>
    ) {
        val model = items.getOrNull(position) ?: return
        holder.item = model
        val factory = factory.getValue(model::class)
        factory.bind(holder.binding, model, payloads)
    }
}