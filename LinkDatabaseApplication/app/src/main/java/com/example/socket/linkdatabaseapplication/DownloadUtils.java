package com.example.socket.linkdatabaseapplication;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.StrictMode;
import android.support.v4.content.FileProvider;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class DownloadUtils {

    private static ProgressDialog mDialog = null;

    /**
     * 显示下载进度条
     *
     * @param aty
     */
    public static void showProgressDialog(Activity aty) {
        if (mDialog == null) {
            mDialog = new ProgressDialog(aty);
            mDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);//设置风格为圆形进度条
            //mDialog.setMessage(aty.getResources().getString(R.string.msg_downloading));
            mDialog.setIndeterminate(false);//设置进度条是否为不明确
            mDialog.setCancelable(true);//设置进度条是否可以按退回键取消
            mDialog.setCanceledOnTouchOutside(false);
            mDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {
                    mDialog = null;
                }
            });
            mDialog.show();
        }
    }

    /**
     * 关闭下载进度条
     */
    public static void closeProgressDialog() {
        if (mDialog != null) {
            mDialog.dismiss();
            mDialog = null;
        }
    }

    /**
     * 获取打开文档意图
     *
     * @param file
     * @return
     */
    public static Intent getFileIntent(Activity activity, File file,String file_s) {
        Uri uri = Uri.fromFile(file);
        file.setReadable(true,false);
        //uri= Uri.parse(file_s);
      //  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
      //      //通过FileProvider创建一个content类型的Uri
      //      uri = FileProvider.getUriForFile(activity, "com.invt.fileprovider", file);
      //  }

      //  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
       //     StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
       //     StrictMode.setVmPolicy(builder.build());
       // }



        String type = getMIMEType(file);
        //优先使用WPS打开
        //检测是否安装了wps软件，没有安装选择默认打开
        Intent intent = new Intent();
        //intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(android.content.Intent.ACTION_VIEW);

        //intent.setAction(Intent.ACTION_VIEW);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            uri  = FileProvider.getUriForFile(activity,
                    activity.getPackageName()+".fileProvider", file);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            //intent.setDataAndType(uri , "*/*");
        } else {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            uri = Uri.fromFile(file);
           // intent.setDataAndType(Uri.fromFile(file), "*/*");
        }


        //if (SystemUtil.isInstall(activity, "cn.wps.moffice_eng")) {
         //   intent.setClassName("cn.wps.moffice_eng",
         //           "cn.wps.moffice.documentmanager.PreStartActivity2");
          //  intent.setData(uri);
      //  } else {
            intent.addCategory("android.intent.category.DEFAULT");
            intent.setDataAndType(uri, type);
       // }
        return intent;
    }

    /**
     * 下载文件到sdcard
     *
     * @param fileName
     * @param input
     */
    public static void writeToSDCard(String fileName, InputStream input) {
        // 判断是否挂载了SD卡
        String storageState = Environment.getExternalStorageState();
        if (storageState.equals(Environment.MEDIA_MOUNTED)) {
            String directory = getDownloadPath();
            File dir = new File(directory);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            File file = new File(directory, fileName);
            if (file.exists()) {
                file.delete();
            }
            try {
                FileOutputStream fos = new FileOutputStream(file);
                byte[] b = new byte[2048];
                int j = 0;
                while ((j = input.read(b)) != -1) {
                    fos.write(b, 0, j);
                }
                fos.flush();
                fos.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 获取下载路径
     *
     * @return
     */
    public static String getDownloadPath() {
        String compName="" ;//= AppString.getCompanyName();
        return Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + compName + "/Doc/";

    }

    /**
     * 获取文档类型，并打开
     *
     * @param f
     * @return
     */
    private static String getMIMEType(File f) {
        String type = "";
        String fName = f.getName();
        /* 取得扩展名 */
        String end = fName.substring(fName.lastIndexOf(".") + 1, fName.length()).toLowerCase();
        /* 依扩展名的类型决定MimeType */
        if (end.equals("pdf")) {
            type = "application/pdf";
        } else if (end.equals("m4a") || end.equals("mp3") || end.equals("mid") ||
                end.equals("xmf") || end.equals("ogg") || end.equals("wav")) {
            type = "audio/*";
        } else if (end.equals("3gp") || end.equals("mp4")) {
            type = "video/*";
        } else if (end.equals("jpg") || end.equals("gif") || end.equals("png") ||
                end.equals("jpeg") || end.equals("bmp")) {
            type = "image/*";
        } else if (end.equals("apk")) {
            type = "application/vnd.android.package-archive";
        } else if (end.equals("pptx") || end.equals("ppt")) {
            type = "application/vnd.ms-powerpoint";
        } else if (end.equals("docx") || end.equals("doc")) {
            type = "application/vnd.ms-word";
        } else if (end.equals("xlsx") || end.equals("xls")) {
            type = "application/vnd.ms-excel";
        } else {
            //如果无法直接打开，就跳出软件列表给用户选择
            type = "*/*";
        }
        return type;
    }

}
