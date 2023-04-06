package adapter.recycler.sample.data

import adapter.recycler.sample.presentation.model.GroupUiModel
import adapter.recycler.sample.presentation.model.HeaderUiModel
import adapter.recycler.sample.presentation.model.InnerItemUiModel

class SampleRecyclerDataFactory {

    private val list
        get() = mutableListOf(
            InnerItemUiModel(
                itemId = 0,
                title = "Item 1",
                description = "Description 1"
            ),
            InnerItemUiModel(
                itemId = 1,
                title = "Item 2",
                description = "Description 2"
            ),
            InnerItemUiModel(
                itemId = 2,
                title = "Item 3",
                description = "Description 3"
            ),
            InnerItemUiModel(
                itemId = 3,
                title = "Item 4",
                description = "Description 4"
            )
        )
    private val otherList
        get() = mutableListOf(
            InnerItemUiModel(
                itemId = 0,
                title = "Item 1 changed",
                description = "Description 1 changed"
            ),
            InnerItemUiModel(
                itemId = 2,
                title = "Item 3 changed",
                description = "Description 3"
            ),
            InnerItemUiModel(
                itemId = 1,
                title = "Item 2",
                description = "Description 2 changed"
            ),
            InnerItemUiModel(
                itemId = 3,
                title = "Item 4",
                description = "Description 4 changed"
            )

        )

    private fun createHeader() = HeaderUiModel

    fun createGroups() = listOf(
        createHeader(),
        GroupUiModel(
            title = "Group 1",
            items = list
        ),
        GroupUiModel(
            title = "Group 2",
            items = list
        ),
        createGroup().copy(title = "Group 3"),
        createGroup().copy(title = "Group 4"),
        createGroup().copy(title = "Group 5"),
        createGroup().copy(title = "Group 6"),
        createGroup().copy(title = "Group 7"),
        createGroup().copy(title = "Group 8"),
        createGroup().copy(title = "Group 9"),
        createGroup().copy(title = "Group 10"),
        createGroup().copy(title = "Group 11"),
        createGroup().copy(title = "Group 12"),
        createGroup().copy(title = "Group 13"),
        createGroup().copy(title = "Group 14"),
        createGroup().copy(title = "Group 15"),
    )

    private fun createGroup() = GroupUiModel(
        title = "Group 1",
        items = list
    )

    fun createOtherGroups() = listOf(
        createHeader(),
        GroupUiModel(
            title = "Group 1",
            items = otherList
        ),
        GroupUiModel(
            title = "Group 2",
            items = otherList
        ),
        createGroup().copy(title = "Group 3"),
        createGroup().copy(title = "Group 4"),
        createGroup().copy(title = "Group 5"),
        createGroup().copy(title = "Group 6"),
        createGroup().copy(title = "Group 7"),
        createGroup().copy(title = "Group 8"),
        createGroup().copy(title = "Group 9"),
        createGroup().copy(title = "Group 10"),
        createGroup().copy(title = "Group 11"),
        createGroup().copy(title = "Group 12"),
        createGroup().copy(title = "Group 13"),
        createGroup().copy(title = "Group 14"),
        createGroup().copy(title = "Group 15"),
    )
}