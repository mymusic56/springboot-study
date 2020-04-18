package com.mymusic.io;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Slf4j
public class FileBufferedReaderTest {
    public static void main(String[] args) {

    }

    private static void consoleReadChar(){
        char c = '1';
        // 使用 System.in 创建 BufferedReader
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("输入字符, 按下 'q' 键退出。");
        // 读取字符
        do {
            try {
                c = (char) br.read();
                System.out.println(c);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } while (c != 'q');
    }

    private static void consoleReadString() throws IOException {
        String c = "";
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("输入字符, 按下 'q' 键退出。");
        do {
            c = (String) bufferedReader.readLine();
            if (c != "\n") {
                log.info("输入的字符串是："+c);
            }
        } while (c != "q");
    }
}
