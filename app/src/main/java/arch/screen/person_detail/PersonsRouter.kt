package arch.screen.person_detail

import android.widget.Toast
import arch.domain.model.Person
import io.reactivex.functions.Consumer

interface PersonRouter {
    val goToEdit: Consumer<Person>
}

class PersonRouterImpl(activity: DetailsActivity) : PersonRouter {
    override val goToEdit = Consumer<Person> {
        Toast.makeText(activity, "navigate to edit", Toast.LENGTH_SHORT).show()
    }
}