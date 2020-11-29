package nanicky.losties.losties.enums

enum class PublicationTypes {
    LOST, SEEN, FOUND
}

fun String.toPublicationType()
        = PublicationTypes.values().first { it.name ==  this}