package nanicky.losties.android.features.rateanimals

data class AnimalRandomRequest(
    val name_photo: String,
    val photo: List<Byte>,
    val count_liked: Long,
    val count_unliked: Long,
    val type_animal: String
)
