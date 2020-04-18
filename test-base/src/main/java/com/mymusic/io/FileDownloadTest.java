package com.mymusic.io;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class FileDownloadTest {

    public static void main(String[] args) {
        String url = "";
        String fileDir = "";
        String filename = "";
        String method = "";
        String path = saveUrlAs(url, fileDir, filename, method);
        System.out.println("下载文件存放路径："+path);
    }

    private static String saveUrlAs(String url, String fileDir, String filename, String method) {

        String localFile = "";
        // System.out.println("fileName---->"+filePath);
        // 创建不同的文件夹目录
        File file = new File(fileDir);
        // 判断文件夹是否存在
        if (!file.exists()) {
            // 创建多级目录
            file.mkdirs();
        }
        FileOutputStream fileOut = null;
        HttpURLConnection conn = null;
        InputStream inputStream = null;
        try {
            // 建立链接
            URL httpUrl = new URL(url);
            conn = (HttpURLConnection) httpUrl.openConnection();
            // 以Post方式提交表单，默认get方式
            conn.setRequestMethod(method);
            conn.setDoInput(true);
            conn.setDoOutput(true);
            // post方式不能使用缓存
            conn.setUseCaches(false);
            conn.connect();
            // 获取网络输入流
            inputStream = conn.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(inputStream);
            // 判断文件的保存路径后面是否以/结尾
            if (!fileDir.endsWith("/")) {
                fileDir += "/";
            }
            fileOut = new FileOutputStream(fileDir + filename);
            BufferedOutputStream bos = new BufferedOutputStream(fileOut);

            //把读出来的数据放到字节数组里面，并返回字节长度
            byte[] buf = new byte[4096];
            int length;
            while ((length = bis.read(buf)) != -1) {
                //写入文件
                bos.write(buf, 0, length);
            }
            bos.flush();
            bos.close();
            bis.close();
            conn.disconnect();
            localFile = fileDir + filename;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("抛出异常！！");
        }

        return localFile;
    }
}
