package com.example.githubexample.presentation.detail

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.databinding.library.baseAdapters.BR
import com.example.githubexample.R
import com.example.githubexample.data.entities.Repos
import com.example.githubexample.databinding.ActivityDetailBinding
import com.example.githubexample.utils.extension.loadImage
import kotlinx.android.synthetic.main.activity_detail.*


class DetailActivity : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val repo = intent.getParcelableExtra("repo") as Repos

        val binding: ActivityDetailBinding = DataBindingUtil.setContentView(this, R.layout.activity_detail)
        binding.setVariable(BR.repo, repo)
        binding.executePendingBindings()

        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
    }

    companion object {
        fun getStartIntent(context: Context, repo: Repos): Intent {
            return Intent(context, DetailActivity::class.java).putExtra("repo", repo)
        }

        @JvmStatic
        @BindingAdapter("bind:imageUrl")
        fun setImageUrl(view: ImageView, url: String) {
            view.loadImage(url)
        }
    }
}
