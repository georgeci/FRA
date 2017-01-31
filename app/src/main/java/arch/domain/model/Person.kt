package arch.domain.model

data class Person(
    val id: String,
    val name: String,
    val isFavorite: Boolean,
    val email: String,
    val skills: List<Skill>
)