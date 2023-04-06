package adapter.recycler.single.extensions.collections

import androidx.collection.ArrayMap

/**
 * Создаёт [ArrayMap] из массива пар.
 *
 * На маленьком колличестве данных эффективнее использовать [ArrayMap], а не [HashMap].
 *
 * Без [ArrayMap] не получится использовать valueAt  и indexOfKey
 *
 * @see ArrayMap
 */
internal fun <K, V> arrayMapOf(pairs: Array<out Pair<K, V>>): ArrayMap<K, V> {
    val map = ArrayMap<K, V>(pairs.size)
    for ((first, second) in pairs) {
        map[first] = second
    }

    return map
}