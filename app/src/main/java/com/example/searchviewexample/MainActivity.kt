package com.example.searchviewexample

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import com.example.searchviewexample.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

// Adapted from https://www.geeksforgeeks.org/searchview-in-android-with-kotlin/
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val programmeringLanguages =
        arrayOf("C", "C#", "Java", "JavaScript", "Dart", "Kotlin", "TypeScript")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view: View = binding.root
        setContentView(view)

        val listAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            programmeringLanguages
        )

        binding.listviewLanguages.adapter = listAdapter

        binding.searchview.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String?): Boolean {
                listAdapter.filter.filter(newText)
                return false
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query == null) {
                    binding.textviewMessage.text = "No query"
                    return false
                }
                binding.textviewMessage.text = query.toString()
                if (programmeringLanguages.contains(query)) {
                    Snackbar.make(binding.layout, "Found: $query", Snackbar.LENGTH_LONG).show()
                    //Toast.makeText(this@MainActivity, "Found: $query", Toast.LENGTH_LONG).show()
                } else {
                    Snackbar.make(binding.layout, "No Language found..", Snackbar.LENGTH_LONG)
                        .show()
                    //Toast.makeText(this@MainActivity, "No Language found..", Toast.LENGTH_LONG)
                    //    .show()
                }
                return false
            }
        })
    }
}