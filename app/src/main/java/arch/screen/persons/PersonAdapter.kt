package arch.screen.persons

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import arch.R
import arch.domain.model.Person
import extensions.inflate
import extensions.publishRelay

class PersonAdapter : RecyclerView.Adapter<PersonVh>() {

    val clicks = publishRelay<Person>()

    val items = mutableListOf<Person>()

    override fun onBindViewHolder(holder: PersonVh, position: Int) {
        val person = items[position]
        holder.bindValue(person)
        holder.itemView.setOnClickListener { clicks.accept(person) }
    }

    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonVh {
        return PersonVh(parent.inflate(R.layout.person))
    }
}

class PersonVh(view: View) : RecyclerView.ViewHolder(view) {
    val name = view.findViewById(R.id.name) as TextView

    fun bindValue(person: Person) {
        name.text = person.name
    }
}