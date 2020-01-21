package com.example.githubexample.presentation.main

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.githubexample.R
import com.example.githubexample.data.entities.Repos
import com.example.githubexample.presentation.detail.DetailActivity
import com.example.githubexample.presentation.main.adapter.MainAdapter
import com.example.githubexample.utils.extension.hideKeyboard
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModel()
    private lateinit var mainAdapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViewModel()

        val onRepoClicked: (repos: Repos) -> Unit = { repos ->
            viewModel.reposClicked(repos)
        }

        mainAdapter = MainAdapter(this@MainActivity, onRepoClicked)

        rv_repos.apply {
            layoutManager = LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)
            adapter = mainAdapter
        }

        btn_submit.setOnClickListener {
            this@MainActivity.hideKeyboard(til_repo_name)

            mainAdapter.updateData(emptyList())

            if (til_repo_name.editText?.text.toString() == "")
                til_repo_name.editText?.error = "Lütfen kullanıcı adını boş bırakmayınız."
            else
                viewModel.loadRepos(til_repo_name.editText?.text.toString())
        }
    }

    private fun initViewModel() {
        viewModel.reposList.observe(this, Observer { newRepoList ->
            mainAdapter.updateData(newRepoList!!)
        })

        viewModel.showLoading.observe(this, Observer { showLoading ->
            progress_bar.visibility = if (showLoading!!) View.VISIBLE else View.GONE
        })

        viewModel.showError.observe(this, Observer { showError ->
            Toast.makeText(this, showError, Toast.LENGTH_SHORT).show()
        })

        viewModel.navigateToDetail.observe(this, Observer { imageUrl ->
            if (imageUrl != null) startActivity(DetailActivity.getStartIntent(this, imageUrl))
        })
    }
}
