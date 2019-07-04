package com.example.socket.linkdatabaseapplication;

import android.content.Intent;
import android.os.AsyncTask;

import java.io.File;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
/*
public  class DownloaderTask extends AsyncTask<String, Void, String> {

    @Override
    protected String doInBackground(String... params) {
        String url = params[0];
        String fileName = url.substring(url.lastIndexOf("/") + 1);
        try {
            fileName = URLDecoder.decode(fileName, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        MyLogger.i("download", "fileName=" + fileName);
        try {
            URL resUrl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) resUrl.openConnection();
            conn.connect();
            InputStream input = conn.getInputStream();
            DownloadUtils.writeToSDCard(fileName, input);
            input.close();
            return fileName;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        DownloadUtils.closeProgressDialog();
        if (result == null) {
            ToastUtils.showShortToast(StartActivity.this, R.string.msg_downloaderror);
            return;
        }
        ToastUtils.showShortToast(StartActivity.this, R.string.msg_downloadsuccess);
        String dir = DownloadUtils.getDownloadPath();
        File file = new File(dir, result);
        Intent intent = DownloadUtils.getFileIntent(StartActivity.this, file);
        //自动打开
        startActivity(intent);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        DownloadUtils.showProgressDialog(StartActivity.this);
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

}
*/