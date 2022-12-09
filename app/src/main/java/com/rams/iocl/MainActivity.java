/*
       Licensed to the Apache Software Foundation (ASF) under one
       or more contributor license agreements.  See the NOTICE file
       distributed with this work for additional information
       regarding copyright ownership.  The ASF licenses this file
       to you under the Apache License, Version 2.0 (the
       "License"); you may not use this file except in compliance
       with the License.  You may obtain a copy of the License at

         http://www.apache.org/licenses/LICENSE-2.0

       Unless required by applicable law or agreed to in writing,
       software distributed under the License is distributed on an
       "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
       KIND, either express or implied.  See the License for the
       specific language governing permissions and limitations
       under the License.
 */

package com.rams.iocl;

import android.Manifest;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Toast;


import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import org.apache.cordova.*;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import nl.xservices.plugins.FileProvider;

public class MainActivity extends CordovaActivity
{

    private static final int STORAGE_PERMISSION_CODE = 1001;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        super.init();

        // enable Cordova apps to be started in the background
        Bundle extras = getIntent().getExtras();
        if (extras != null && extras.getBoolean("cdvStartInBackground", false)) {
            moveTaskToBack(true);
        }

       checkPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE,
                STORAGE_PERMISSION_CODE);

        WebView webView = (WebView) appView.getEngine().getView();
        webView.getSettings().setJavaScriptEnabled(true);
        webView.addJavascriptInterface(new MyJSInterfaceIOCL(this), "myJSInterfaceIOCL");

        WebView webView2 = (WebView) appView.getEngine().getView();
        webView2.getSettings().setJavaScriptEnabled(true);
        webView2.addJavascriptInterface(new MyJSInterfaceIOCL(this), "myJSInterfaceIOCLWell");

        // Set by <content src="index.html" /> in config.xml
        loadUrl(launchUrl);
    }

    public void checkPermission(String permission, int requestCode)
    {
        // Checking if permission is not granted
        if (ContextCompat.checkSelfPermission(MainActivity.this, permission) == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[] { permission }, requestCode);
        }
        else {
            //Toast.makeText(MainActivity.this, "Permission already granted", Toast.LENGTH_SHORT).show();
        }
    }

    public class MyJSInterfaceIOCL {
        public MyJSInterfaceIOCL(MainActivity mainActivity) {
            Log.i("DemoDemo", "constructor of jsinterface");
        }

        @JavascriptInterface
        public void btnClick(String url, String packageName) {
            Log.e("click url >>>>>>>",url);
            String spliltDAta = url.substring(url.indexOf("apks") + 5  , url.indexOf(".apk"));
            Log.e("split >>>>>>>",spliltDAta);
            String applicationName = url.substring(url.indexOf("apks") + 5  , url.indexOf(".apk"));
            Utils utils = new Utils(MainActivity.this, packageName, applicationName);
            utils.CallForInstallation(url);
        }
    }

    public class MyJSInterfaceIOCLWell {
        public MyJSInterfaceIOCLWell(MainActivity mainActivity) {
            Log.i("Demo", "constructor of jsinterface");
        }

        @JavascriptInterface
        public void btnClick(String url, String packageName) {
            Log.e("url >>>>>>>",url);
            Log.e("pkgname >>>>>>>",packageName);
            String spliltDAta = url.substring(url.indexOf("apks/")  , url.length());
            Log.e("split >>>>>>>",spliltDAta);
            String applicationName = packageName.split("\\.")[2];
            Utils utils = new Utils(MainActivity.this, packageName, applicationName);
            utils.CallForInstallation(url);
            //new Utils.DownloadFileFromURL().execute("http://3.109.130.156:5000/apks/IOCL.apk");
        }
    }
}
