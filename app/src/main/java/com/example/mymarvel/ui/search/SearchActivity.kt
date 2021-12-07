package com.example.mymarvel.ui.search

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.SearchView
import android.widget.Toast
import androidx.activity.viewModels
import com.example.mymarvel.R
import com.example.mymarvel.databinding.ActivitySearchBinding

class SearchActivity : AppCompatActivity() {

    private var _binding: ActivitySearchBinding? = null
    private val binding: ActivitySearchBinding
    get() = _binding!! //if this throws an error you've done something terribly wrong

    private val viewModel: SearchViewModel by viewModels { ViewModelFactory() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivitySearchBinding.inflate(layoutInflater)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        setContentView(binding.root)

        viewModel.events.observe(this) {
            onEvent(it)
        }
    }

    private fun onEvent(event: SearchViewModel.Event) {
        when (event) {
            is SearchViewModel.Event.LoadingStatus -> {
                //not doing it right now
                if (event.isLoading) {
                    Toast.makeText(this, R.string.loading, Toast.LENGTH_SHORT).show()
                }
            }
            is SearchViewModel.Event.SearchError -> {
                //but you get the idea
                Toast.makeText(this, event.errorMsg, Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        handleIntent(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.search_menu, menu)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        (menu.findItem(R.id.search).actionView as SearchView).apply {
            setSearchableInfo(searchManager.getSearchableInfo(componentName))
        }

        return true
    }

    private fun handleIntent(intent: Intent) {
        if (Intent.ACTION_SEARCH == intent.action) {
            val query = intent.getStringExtra(SearchManager.QUERY)
            viewModel.search(query)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null //this would matter if our app lifecycle was bigger than this activity
    }
}