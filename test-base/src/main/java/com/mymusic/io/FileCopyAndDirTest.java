package com.mymusic.io;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.nio.charset.StandardCharsets;

@Slf4j
public class FileCopyAndDirTest {

    public static void main(String[] args) {
        //make dir
//        String f0 = "D:\\Workspace-Java\\springboot-study\\test-base\\log\\a\\b\\c";
//
//        File f = new File(f0);
//        f.mkdirs();


        //copy file
//        String f1 = "D:\\Workspace-Java\\springboot-study\\test-base\\log\\1.txt";
//        String f2 = "D:\\Workspace-Java\\springboot-study\\test-base\\log\\target1\\iso\\CentOS-7-x86_64-DVD-1810.iso";
//        long t1 = System.currentTimeMillis();
//        copyFile(f1, f2);
//        System.out.println("耗时："+ (System.currentTimeMillis() - t1)/1000);

        //delete dir
        File f = new File("D:\\Workspace-Java\\springboot-study\\test-base\\log\\ddd");
        deleteFile(f);
    }

    /**
     * 删除文件夹
     * 思路
     *  - 读取文件列表
     *  - 如果列表为空，直接删除目录
     *  - 如果是文件直接删除
     *  - 如果目录递归进，重复步骤1
     * @param file 文件对象
     */
    private static void deleteFile(File file){
        File[] files = file.listFiles();
        if (files != null) {
            for (File f : files) {
                if (f.isDirectory()) {
                    deleteFile(f);
                } else {
                    if (!f.delete()) {
                        log.error("文件："+f.getPath()+"删除失败");
                    } else {
                        log.info("文件："+f.getPath()+"删除成功");
                    }

                }
            }
        }
        if (!file.delete()) {
            log.error("目录："+file.getPath()+"删除失败");
        } else {
            log.info("目录："+file.getPath()+"删除成功");
        }
    }

    private static void copyFile(String from, String to){
        File file1 = new File(from);
        try {
            FileInputStream fi = new FileInputStream(file1);
            BufferedInputStream bis = new BufferedInputStream(fi);

            //文件目录是否存在
            File file2 = new File(to);
            File fileParent = file2.getParentFile();
            if (!fileParent.exists()) {
                System.out.println("文件目录不存在:"+to);
                fileParent.mkdirs();
                file2.createNewFile();
            }

            FileOutputStream fo = new FileOutputStream(file2);
//            OutputStreamWriter writer = new OutputStreamWriter(fo, StandardCharsets.UTF_8);
            BufferedOutputStream bos = new BufferedOutputStream(fo);
            byte[] b = new byte[1024];
            int len;
            while ((len = bis.read(b)) != -1) {
                bos.write(b);
            }
            bos.close();
            fo.close();
            bis.close();;
            fi.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
