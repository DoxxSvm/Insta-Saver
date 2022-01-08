package com.doxx.instasaver

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.core.content.ContextCompat.getSystemService
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.doxx.instasaver.GetRequest.returnClass
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import java.lang.invoke.ConstantCallSite


class ReelsFragment : Fragment() {

    var success=false;
    var uri: Uri? =null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_reels, container, false)
        val reelLink = view.findViewById<EditText>(R.id.getReelsLink)
        val fetch=view.findViewById<Button>(R.id.getReelsButton)
        val videoPrev = view.findViewById<VideoView>(R.id.reelsPreview)
        val mediaController = MediaController(context)
        val download = view.findViewById<Button>(R.id.downloadReelsButton)
        //returnClass.success=false
        mediaController.setAnchorView(videoPrev)
        fetch.setOnClickListener {
            it.hideKeyboard()
            var url =reelLink.text.toString().trim()
            if(url == ""){
                Toast.makeText(context,"First enter url",Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            else{
                val murl= url.substring(0,url.indexOf("?"))
                url= "$murl?__a=1"
                Toast.makeText(context,url,Toast.LENGTH_LONG).show()

            }
            val returnClass = GetRequest.get(url,videoPrev,mediaController)
            this.success=returnClass.success
            Toast.makeText(context,(returnClass.uri).toString(),Toast.LENGTH_SHORT).show()



        }
        download.setOnClickListener {
            if(!success){
                Toast.makeText(context,"No Video to Download",Toast.LENGTH_SHORT).show()
            }
            val request = DownloadManager.Request(uri)
            request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI )
            request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE)
            request.setTitle("Download")
            request.setDescription("Downloaded")
            request.allowScanningByMediaScanner()
            request.setNotificationVisibility( DownloadManager.Request.VISIBILITY_VISIBLE )
            request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DCIM,""+System.currentTimeMillis())
            val location = activity?.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
            location.enqueue(request)

            Toast.makeText(context,"Downloadedd yeahhhh",Toast.LENGTH_SHORT).show()
        }
        return  view
    }
    private fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }






}