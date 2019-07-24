package com.example.movies.details


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient

import com.example.movies.R


class WebFragment : Fragment() {

    private lateinit var webView: WebView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_web, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        webView=view.findViewById(R.id.web_imdb)
        val args = WebFragmentArgs.fromBundle(arguments!!)
        webView.webViewClient=object :WebViewClient(){
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                view!!.loadUrl(url)
                return true
            }
        }

        webView.scrollBarStyle=View.SCROLLBARS_INSIDE_OVERLAY
        webView.settings.javaScriptEnabled=true
        webView.loadUrl(args.url)
    }


}
