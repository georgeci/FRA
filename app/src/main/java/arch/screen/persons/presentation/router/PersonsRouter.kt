package arch.screen.persons.presentation.router

import android.content.Context
import android.content.Intent
import android.view.View
import arch.domain.model.Person
import arch.screen.person_detail.DetailsActivity
import io.reactivex.functions.Consumer

interface PersonsRouter {
    val navigateToDetail: Consumer<Person>
}

class PersonsRouterImpl(
    val context: Context,
    val navigator: (Intent) -> Unit,
    val view: View
) : PersonsRouter {

    override val navigateToDetail: Consumer<Person> = Consumer {
        navigator(DetailsActivity.intent(context, it))
    }
}