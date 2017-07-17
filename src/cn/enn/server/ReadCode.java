package cn.enn.server;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ReadCode {

    public List<String> readAllCode() {
        List<String> code = null;

        File file = new File("E:/tag.txt");

        try {
            // 读取文件的输入流
            InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(file), "utf-8");

            // 判断文件是否存在
            if (file.isFile() && file.exists()) {
                code = new ArrayList<String>();

                // 字符缓存输入流
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                String str = null;
                // 读取文件，将文件内容放入到set中
                while ((str = bufferedReader.readLine()) != null) {
                    code.add(str);
                }
                bufferedReader.close();
            }
            inputStreamReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return code;
    }

    public static void main(String[] args) {
        ReadCode readCode = new ReadCode();
        System.out.println(readCode.readAllCode().toString());
    }
}
