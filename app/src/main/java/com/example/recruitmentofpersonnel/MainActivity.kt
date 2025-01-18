package com.example.recruitmentofpersonnel

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class MainActivity : AppCompatActivity(), Removable {

    private var postList = mutableListOf(
        "Начальник производства",
        "Главный инженер",
        "Главный технолог",
        "Главный механик",
        "Главный энергетик",
        "Главный сварщик",
        "Начальник участка",
        "Начальник цеха (участка)",
        "Производитель работ (прораб)",
        "Бригадир на производстве"
        )
    var person: Person? = null
    private lateinit var listAdapter: ArrayAdapter<Person>
    private val listOfPersons:MutableList<Person> = mutableListOf()

    private lateinit var toolbarTB: Toolbar
    private lateinit var nameET: EditText
    private lateinit var surNameET: EditText
    private lateinit var ageET: EditText
    private lateinit var postACET: AutoCompleteTextView
    private lateinit var saveBTN: Button
    private lateinit var listLV: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        toolbarTB = findViewById(R.id.toolbarMain)
        nameET = findViewById(R.id.nameET)
        surNameET = findViewById(R.id.surNameET)
        ageET = findViewById(R.id.ageET)
        postACET = findViewById(R.id.postACTV)
        saveBTN = findViewById(R.id.saveBTN)
        listLV = findViewById(R.id.listLV)

        toolbarTB.setTitle(getString(R.string.app_name_ru))
        setSupportActionBar(toolbarTB)

        var adapter = ArrayAdapter(
            this,
            android.R.layout.simple_dropdown_item_1line,
            postList
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        postACET.setAdapter(adapter)

        saveBTN.setOnClickListener {
            var name = nameET.text.toString()
            var surName = surNameET.text.toString()
            var age = ageET.text.toString().toInt()
            var post = postACET.text.toString()
            person = Person(name, surName, age, post)

            listOfPersons.add(person!!)
            listAdapter = ListAdapter(this@MainActivity, listOfPersons)
            listLV.adapter = listAdapter
            listAdapter.notifyDataSetChanged()

            clearingFields()
        }

        listLV.onItemClickListener =
            AdapterView.OnItemClickListener {parent, view, position, id ->
                listAdapter.getItem(position)?.let { remove(it) }
            }
    }

    private fun clearingFields() {
        nameET.text.clear()
        surNameET.text.clear()
        ageET.text.clear()
        postACET.text.clear()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {R.id.exit -> finishAffinity() }
        return super.onOptionsItemSelected(item)
    }

    override fun remove(person: Person) { listAdapter.remove(person) }
}