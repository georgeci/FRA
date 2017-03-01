package arch.domain.model

import android.annotation.SuppressLint
import io.mironov.smuggler.AutoParcelable

@SuppressLint("ParcelCreator")
data class Person(
    val id: String,
    val name: String,
    val isFavorite: Boolean,
    val email: String,
    val skills: List<Skill>
) : AutoParcelable