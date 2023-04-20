[![](https://jitpack.io/v/AlexDeko/SingleRecyclerAdapter.svg)](https://jitpack.io/#AlexDeko/SingleRecyclerAdapter)
## Add the library to a project

```groovy
allprojects {
  repositories {
      mavenCentral()
      maven { url "https://jitpack.io" }
  }
}
dependencies {
    implementation 'com.github.AlexDeko:SingleRecyclerAdapter:1.0.1'
    
}
```


## Better than others

Unified DiffUtil.ItemCallback, no need to create your own and pass it to the adapter.

```kotlin
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
```

The Viewholder always consumes the Binding Class,
so you can override the logic in a specific implementation.

```kotlin
interface BindingClass {

    val itemId: Long
        get() = this.hashCode().toLong()

    fun areContentsTheSame(other: BindingClass): Boolean = other == this

    fun areItemsTheSame(other: BindingClass): Boolean = (other as? BindingClass)?.itemId == itemId

    fun getChangePayload(newItem: BindingClass): List<Any> = listOf()
}
```


We are always waiting for payloads and successfully process them
```kotlin
fun <T> checkChanges(
    checks: Map<T, Boolean>
): List<T> = checks.entries.filter { (_, value) -> value }.map { it.key }


fun List<*>.check(check: (payload: Any?) -> Unit) = forEach { payload ->
    if (payload is List<*>) payload.forEach(check)
    else check(payload)
}
```

```kotlin
    override fun bind(
        binding: RecyclerItemGroupBinding,
        model: GroupUiModel,
        payloads: List<Any>
    ) = when {
        payloads.isNotEmpty() -> payloads.check { payload ->
            when (payload) {
                GroupPayload.ItemsChanged -> binding.recycler.setBindingList(model.items)
            }
        }
        else -> with(binding) {
            recycler.setBindingList(model.items)
            title.text = model.title
        }
    }
```

```kotlin
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
```

## Creating

See how sample works and works.

```kotlin
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        with(recycler) {
            adapter = binderAdapterOf(
                HeaderUiModel::class bindWith HeaderViewHolderFactory(),
                GroupUiModel::class bindWith GroupViewHolderFactory(
                    action = { title ->
                        Toast.makeText(context, "Clicked $title", Toast.LENGTH_SHORT).show()
                    }
                )
           )
           setBindingList(dataFactory.createGroups())
        }
    }
```
