package nanicky.losties.android.features.enums

enum class PublicationTypes {
    LOST, SEEN, FOUND
}

fun String.toPublicationType()
        = PublicationTypes.values().first { it.name ==  this}