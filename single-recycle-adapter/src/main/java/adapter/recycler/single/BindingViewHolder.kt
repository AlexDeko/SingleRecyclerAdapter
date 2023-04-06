package adapter.recycler.single

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

class BindingViewHolder<M : BindingClass, out B : ViewBinding>(
        val binding: B
) : RecyclerView.ViewHolder(binding.root) {

    lateinit var item: M
}