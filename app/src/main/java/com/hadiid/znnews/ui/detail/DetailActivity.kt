package com.hadiid.znnews.ui.detail

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import com.hadiid.znnews.R
import com.hadiid.znnews.databinding.ActivityDetailBinding
import com.hadiid.znnews.databinding.CustomToolbarBinding
import com.hadiid.znnews.source.news.ArticleModel
import com.hadiid.znnews.source.news.SourceModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.dsl.module
import java.net.URI

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
            title = detail.source?.name
            setDisplayHomeAsUpEnabled(true)
        }

        detail.let {
            viewModel.find(it)
            val web = binding.webView
            web.loadUrl(it.url!!)
            bindingToolbar.logoTitle.visibility = View.GONE
            web.webViewClient = object : WebViewClient(){
                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                    binding.progressTop.visibility = View.GONE
                }
            }
            val settings = binding.webView.settings
            settings.javaScriptCanOpenWindowsAutomatically = false
        }

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
                val shareBody = "ZNNews - Berita Hangat sumber: ${detail.source?.name} klik link selengkapnya : ${detail.url}"

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




