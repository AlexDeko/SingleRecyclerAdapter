package adapter.recycler.single

interface BindingClass {

    val itemId: Long
        get() = this.hashCode().toLong()

    fun areContentsTheSame(other: BindingClass): Boolean = other == this

    fun areItemsTheSame(other: BindingClass): Boolean = (other as? BindingClass)?.itemId == itemId

    fun getChangePayload(newItem: BindingClass): List<Any> = listOf()
}