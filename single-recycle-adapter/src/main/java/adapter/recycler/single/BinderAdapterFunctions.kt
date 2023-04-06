package adapter.recycler.single

import adapter.recycler.single.extensions.collections.arrayMapOf
import androidx.collection.ArrayMap
import androidx.viewbinding.ViewBinding
import kotlin.reflect.KClass

@Suppress("UNCHECKED_CAST")
fun <M : BindingClass, VH : ViewHolderFactory<*, *>> binderAdapterOf(
    vararg factories: Pair<KClass<out M>, VH>
) = SingleRecyclerAdapter(
    arrayMapOf(factories)
            as ArrayMap<KClass<out BindingClass>, ViewHolderFactory<ViewBinding, BindingClass>>
)

infix fun <M : BindingClass, B : ViewBinding> KClass<M>.bindWith(
    factory: ViewHolderFactory<B, M>
): Pair<KClass<out M>, ViewHolderFactory<B, M>> = this to factory