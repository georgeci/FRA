package arch.screen.persons.presentation.create

import android.view.View
import android.widget.Button
import android.widget.EditText
import arch.R

class PersonsCreateViewHolder(val view: View) {

    val titleInput = view.findViewById(R.id.title_input) as EditText
    val createButton = view.findViewById(R.id.create) as Button
}