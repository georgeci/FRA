package arch.screen.person_detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import arch.R
import arch.domain.model.Person

class DetailsActivity : AppCompatActivity() {
    companion object {
        fun intent(context: Context, item: Person) = Intent(context, DetailsActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.details)
    }
}
