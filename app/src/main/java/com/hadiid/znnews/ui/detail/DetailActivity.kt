package com.hadiid.znnews.ui.detail

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.core.text.HtmlCompat
import com.hadiid.znnews.R
import com.hadiid.znnews.databinding.ActivityDetailBinding
import com.hadiid.znnews.databinding.CustomToolbarBinding
import com.hadiid.znnews.source.news.ArticleModel
import com.hadiid.znnews.util.loadImage
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.dsl.module

val detailModule = module {
    factory { DetailActivity() }
}

class DetailActivity : AppCompatActivity() {

    private val binding by lazy { ActivityDetailBinding.inflate(layoutInflater) }
    private lateinit var bindingToolbar: CustomToolbarBinding
    private val viewModel: DetailViewModel by viewModel()

    private val detail by lazy {
        intent.getSerializableExtra("intent_detail") as ArticleModel
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingToolbar = binding.toolbar
        setContentView(binding.root)

        setSupportActionBar(bindingToolbar.container)
        supportActionBar!!.apply {
            title = detail.name
            setDisplayHomeAsUpEnabled(true)
        }

//        Glide.with(applicationContext)
//            .load(detail.urlToImage)
//            .placeholder(R.drawable.logo)
//            .error(R.drawable.placeholder)
//            .into(binding.image)

        detail.let {
            viewModel.find(it!!)
            binding.title.text = detail.title
            binding.textKolumnis.text = detail.kolumnis
            binding.textTime.text = detail.publishedAt + " WITA"

            loadImage(binding.image, detail.urlToImage)
            binding.textDescription.text =
                detail.description?.let { it1 -> HtmlCompat.fromHtml(it1,0) }

            bindingToolbar.logoTitle.visibility = View.GONE
            binding.progressTop.visibility = View.VISIBLE

        }

        binding.progressTop.visibility = View.GONE

    }


    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_bookmark, menu)
        val menuBookmark = menu!!.findItem(R.id.action_bookmark)


        menuBookmark.setOnMenuItemClickListener {
            viewModel.bookmark(detail)
            true
        }
                viewModel.isBookmark.observe(this, {
            if(it == 0) menuBookmark.setIcon(R.drawable.ic_add)
            else menuBookmark.setIcon(R.drawable.ic_check)

        })


        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.action_share -> {
                val shareBody = "ZNNews - Berita Hangat sumber: ${detail.name} klik link selengkapnya : ${detail.url}"

                val shareSub = "From ZNNews"

                val share = Intent(Intent.ACTION_SEND)
                share.type = "text/plain"

                share.putExtra(Intent.EXTRA_SUBJECT, shareSub)
                share.putExtra(Intent.EXTRA_TEXT, shareBody)
                startActivity(share)
            }
            R.id.action_link -> {
                val openUrl = Intent(Intent.ACTION_VIEW)
                openUrl.data = Uri.parse(detail.url)
                startActivity(openUrl)
            }

        }

        return super.onOptionsItemSelected(item)
    }



    }




