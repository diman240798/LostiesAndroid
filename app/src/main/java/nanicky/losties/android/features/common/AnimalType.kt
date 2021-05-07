package nanicky.losties.losties.util

import nanicky.losties.android.R

enum class AnimalType(val rusName: String, val engName: String, val image: Int) {
    CAT("кот", "cat", R.drawable.ic_cat_mail),
    CATTY("кошка", "catty", R.drawable.ic_cat_female),
    DOG("пес", "dog", R.drawable.ic_dog_mail),
    DOGGY("собака", "doggy", R.drawable.ic_dog_female),
    OTHER("другое", "other", R.drawable.ic_other),
    ALL("все", "all", R.drawable.ic_cat_mail)
}

fun String.toAnimalType() = AnimalType.values().first { it.name == this }