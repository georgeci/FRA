package arch.screen.persons

import arch.domain.model.Person
import arch.screen.person_detail.DetailsActivity
import io.reactivex.functions.Consumer

interface PersonsRouter {
    val navigateToDetail: Consumer<Person>
}

class PersonsRouterImpl(activity: PersonsActivity) : PersonsRouter {
    override val navigateToDetail: Consumer<Person> = Consumer {
        activity.startActivity(DetailsActivity.intent(activity, it))
    }
}