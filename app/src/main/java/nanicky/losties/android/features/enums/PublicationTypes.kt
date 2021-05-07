package nanicky.losties.android.features.enums

enum class PublicationTypes {
    LOST, SEEN, TAKEN
}

fun String.toPublicationType()
        = PublicationTypes.values().first { it.name ==  this }