package adapter.recycler.single.util

fun <T> checkChanges(
    checks: Map<T, Boolean>
): List<T> = checks.entries.filter { (_, value) -> value }.map { it.key }


fun List<*>.check(check: (payload: Any?) -> Unit) = forEach { payload ->
    if (payload is List<*>) payload.forEach(check)
    else check(payload)
}
