package com.rams.iocl;

import android.app.Activity;
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
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import nl.xservices.plugins.FileProvider;

public class Utils {

    Activity activity;
    Context context;
    String packageName;
    String applicationName;

    private static final String PROVIDER_PATH = ".provider";


    Utils(Activity activity, String packageName, String applicationName){
        this.activity = activity;
        this.context = activity.getApplicationContext();
        this.packageName = packageName;
        this.applicationName = applicationName;
    }

    public void CallForInstallation(String url){
        new DownloadFileFromURL().execute(url);
    }

    public class DownloadFileFromURL extends AsyncTask<String, String, String> {
        ProgressDialog pd;
        String pathFolder = "";
        String pathFile = "";

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd = new ProgressDialog(activity);
            pd.setTitle("Downloading...");
            pd.setMessage("Please wait...");
            pd.setMax(100);
            pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            pd.setCancelable(true);
            pd.show();
        }

        @Override
        protected String doInBackground(String... f_url) {
            int count;
            int lengthOfFile;
            try {
                pathFolder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + File.separator + "IOCL/";
                pathFile = pathFolder + applicationName + ".apk";
                File futureStudioIconFile = new File(pathFolder);
                if(!futureStudioIconFile.exists()) {
                    futureStudioIconFile.mkdirs();

                }
                Log.e("url>>>>>>>>>>>>>>>>>",f_url[0]);
                URL url = new URL(f_url[0]);
                URLConnection connection = url.openConnection();
                connection.connect();

                // this will be useful so that you can show a tipical 0-100%
                // progress bar
                lengthOfFile = connection.getContentLength();


                Log.d("Length Of File Server", String.valueOf(lengthOfFile));

                // download the file
                InputStream input = new BufferedInputStream(url.openStream());

                File apkFile = new File(pathFile);
                if(!apkFile.exists()){
                    FileOutputStream output = new FileOutputStream(pathFile);
                    byte data[] = new byte[1024]; //anybody know what 1024 means ?
                    long total = 0;
                    while ((count = input.read(data)) != -1) {
                        total += count;
                        // publishing the progress....
                        // After this onProgressUpdate will be called
                        publishProgress("" + (int) ((total * 100) / lengthOfFile));

                        // writing data to file
                        output.write(data, 0, count);
                    }

                    // flushing output
                    output.flush();

                    // closing streams
                    output.close();
                    input.close();
                }
                else{
                    File file = new File(pathFile);
                    int file_size = (int) file.length();//Integer.parseInt(String.valueOf(file.length()/1024));

                    Log.d("Length Of File System", String.valueOf(file_size));

                    if(lengthOfFile == file_size)
                    {
                        PackageManager pm = context.getPackageManager();
                        boolean isInstalled = isPackageInstalled(packageName, pm);

                        if(isInstalled){
                            Intent launchIntent = context.getPackageManager().getLaunchIntentForPackage(packageName);
                            if (launchIntent != null) {
                                context.startActivity(launchIntent);
                            } else {
                                Toast.makeText(activity, "There is no package available in android", Toast.LENGTH_LONG).show();
                            }
                        }
                        else{
                            InstallPackage(pathFile);
                        }
                    }
                    else{
                        String pathFolder = Environment.getExternalStorageDirectory() + File.separator + "IOCL/";
                        //String pathFile = pathFolder + applicationName + ".apk";
                        File fileDelete = new File(pathFolder, applicationName + ".apk");
                        boolean deleted = fileDelete.delete();

                        if(deleted){
                            Log.d("Delete File", "Yesssss");

                            if(!apkFile.exists()){
                                FileOutputStream output = new FileOutputStream(pathFile);
                                byte data[] = new byte[1024]; //anybody know what 1024 means ?
                                long total = 0;
                                while ((count = input.read(data)) != -1) {
                                    total += count;
                                    // publishing the progress....
                                    // After this onProgressUpdate will be called
                                    publishProgress("" + (int) ((total * 100) / lengthOfFile));

                                    // writing data to file
                                    output.write(data, 0, count);
                                }

                                // flushing output
                                output.flush();

                                // closing streams
                                output.close();
                                input.close();
                            }
                        }
                    }
                }


            } catch (Exception e) {
                Log.e("Error: ", e.getMessage());
            }

            return pathFile;
        }

        protected void onProgressUpdate(String... progress) {
            // setting progress percentage
            pd.setProgress(Integer.parseInt(progress[0]));
            pd.setCanceledOnTouchOutside(false);
        }

        @Override
        protected void onPostExecute(String file_url) {
            if (pd!=null) {
                pd.dismiss();
            }

            BroadcastReceiver br = new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {

                    File destinatioNfile = new File(file_url);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        Uri contentUri = FileProvider.getUriForFile(
                                context,
                                BuildConfig.APPLICATION_ID + PROVIDER_PATH,
                                destinatioNfile
                        );
                        Intent install = new Intent(Intent.ACTION_VIEW);
                        install.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                        install.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        install.putExtra(Intent.EXTRA_NOT_UNKNOWN_SOURCE, true);
                        install.setData(contentUri);
                        context.startActivity(install);
                        context.unregisterReceiver(this);
                        // finish()
                    } else {
                        Uri uri = Uri.parse(file_url);
                        Intent install = new Intent(Intent.ACTION_VIEW);
                        install.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        install.setDataAndType(
                                uri,
                                file_url
                        );
                        context.startActivity(install);
                        context.unregisterReceiver(this);
                        // finish()
                    }

                }
            };

            context.registerReceiver(br, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));

            /*File destination = new File(pathFile);
            Uri contentUri = FileProvider.getUriForFile(
                    context,
                    BuildConfig.APPLICATION_ID + PROVIDER_PATH,
                    destination
            );

            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setDataAndType(Uri.fromFile(new File(file_url)), "application/vnd.android.package-archive");
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtra(Intent.EXTRA_NOT_UNKNOWN_SOURCE, true);
            //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setData(contentUri);
            context.startActivity(intent);*/

            PackageManager pm = context.getPackageManager();
            boolean isInstalled = isPackageInstalled(packageName, pm);

            if(isInstalled){
                Intent launchIntent = context.getPackageManager().getLaunchIntentForPackage(packageName);
                if (launchIntent != null) {
                    context.startActivity(launchIntent);
                } else {
                    Toast.makeText(context, "There is no package available in android", Toast.LENGTH_LONG).show();
                }
            }else{
                InstallPackage(pathFile);
            }

            /*StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
            StrictMode.setVmPolicy(builder.build());
            Intent i = new Intent(Intent.ACTION_VIEW);

            i.setDataAndType(Uri.fromFile(new File(file_url)), "application/vnd.android.package-archive" );
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            getApplicationContext().startActivity(i);*/
        }

    }

    private void InstallPackage(String pathFile){
        Log.e("file path == f",pathFile);
        Log.e("full path",BuildConfig.APPLICATION_ID + PROVIDER_PATH);
        File destination = new File(pathFile);
        Uri contentUri = FileProvider.getUriForFile(
                context,
                BuildConfig.APPLICATION_ID + PROVIDER_PATH,
                destination
        );

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(new File(pathFile)), "application/vnd.android.package-archive");
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra(Intent.EXTRA_NOT_UNKNOWN_SOURCE, true);
        //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setData(contentUri);
        activity.startActivity(intent);
    }

    private boolean isPackageInstalled(String packageName, PackageManager packageManager) {
        try {
            packageManager.getPackageInfo(packageName, 0);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

}