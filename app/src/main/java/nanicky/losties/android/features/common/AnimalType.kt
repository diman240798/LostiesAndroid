package nanicky.losties.losties.util

enum class AnimalType(val rusName: String, val engName: String) {
    CAT("кот", "cat"),
    CATTY("кошка", "catty"),
    DOG("пес", "dog"),
    DOGGY("собака", "doggy"),
    OTHER("другое", "other"),
    ALL("все", "all")
}

fun String.toAnimalType() = AnimalType.values().first { it.name == this }