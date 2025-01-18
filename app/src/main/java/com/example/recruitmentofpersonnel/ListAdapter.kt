package com.example.recruitmentofpersonnel

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class ListAdapter(context: Context, list: MutableList<Person>):
    ArrayAdapter<Person>(context, R.layout.list_item, list){

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view = convertView
        val person = getItem(position)
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false)
        }
        val nameTV = view?.findViewById<TextView>(R.id.nameTV)
        val surNameTV = view?.findViewById<TextView>(R.id.surNameTV)
        val ageTV = view?.findViewById<TextView>(R.id.ageTV)
        val postTV = view?.findViewById<TextView>(R.id.postTV)

        nameTV?.setText(person?.name)
        surNameTV?.setText(person?.surName)
        ageTV?.setText(person?.age.toString())
        postTV?.setText(person?.post)
        return view!!
    }

}