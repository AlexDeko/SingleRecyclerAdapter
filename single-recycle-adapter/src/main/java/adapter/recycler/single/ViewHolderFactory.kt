package adapter.recycler.single

import android.view.ViewGroup
import androidx.viewbinding.ViewBinding

interface ViewHolderFactory<B : ViewBinding, M : BindingClass> {

    fun create(parent: ViewGroup): BindingViewHolder<M, B>

    fun bind(binding: B, model: M, payloads: List<Any>) = Unit
}