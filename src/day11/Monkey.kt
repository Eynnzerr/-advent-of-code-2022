package day11

typealias Level = Long

class Monkey(
    val testNum: Level = 0,
    val next: Pair<Int, Int> = 0 to 0,
    val operate: (Level) -> Level,
    val items: MutableList<Level>
)