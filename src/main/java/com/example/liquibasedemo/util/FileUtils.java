package com.example.liquibasedemo.util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;

/**
 * @author Wisdom
 */
public class FileUtils {

    public static String read(String txt) {
        String lastId = "";
        File file = getFile(txt);

        try(InputStreamReader reader = new InputStreamReader(new FileInputStream(file),"GBK");
            BufferedReader bfReader = new BufferedReader(reader)){

            String value = bfReader.readLine();
            if(StringUtils.isNotEmpty(value)) {
                lastId = value;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lastId;
    }
    public static File getFile(String txt) {

        File file = new File(txt);
        try {
            if (!file.exists()) {
                boolean create = file.createNewFile();
                if (create) {
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file;
    }

    public static void write(String file){
        // 创建一个File对象
        File writename = new File(file);
        try {
            // 创建新文件
            writename.createNewFile();
            //参数：一个第一步的参数file和一个true可读写属性值
            FileWriter writer = new FileWriter(writename , true);
            BufferedWriter out = new BufferedWriter(writer);
            out.write("BSS-北六BSS系统|自有渠道营业员组合权限|普通营业员\r\n");
            // 其中\r\n即为换行
            out.flush(); // 把缓存区内容压入文件
            out.close(); // 最后记得关闭文件
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 清空文件内容
     * @param fileName
     */
    public static void clearInfoForFile(String fileName) {
        File file =new File(fileName);
        try {
            if(!file.exists()) {
                file.createNewFile();
            }
            FileWriter fileWriter =new FileWriter(file);
            fileWriter.write("");
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param filePath 要修改的文件名
     */
    public static Boolean updateStartBat(String filePath,String str){
        clearInfoForFile(filePath);
        RandomAccessFile raf = null;
        try {
            raf = new RandomAccessFile(filePath, "rw");
            raf.writeBytes(str);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();
        }
        return true;
    }
    private static final DateTimeFormatter DATE_ALL = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final DateTimeFormatter DATE = DateTimeFormatter.ofPattern("yyyyMMdd");
    private static final DateTimeFormatter DATE_TIME = DateTimeFormatter.ofPattern("HHmmss");
    public static void main(String[] args) {
        updateStartBat("D:/sftp/time.txt","2020-06-03 16:36:22");
//        System.out.println(DATE_ALL.format(LocalDateTime.now()));
//        System.out.println(DATE.format(LocalDateTime.now()));
//        System.out.println(DATE_TIME.format(LocalDateTime.now()));
        /*String read = read("D:/time/time.txt");
        System.out.println(read);*/
//        System.out.println(LocalDateTime.now().toString());
//        write("D:/time/test.txt");
        /*LocalDateTime now = LocalDateTime.now();
        String df1 = DTF1.format(now);
        String df3 = DTF3.format(now);
        System.out.println(df1);
        System.out.println(df3);*/
//        System.out.println(String.valueOf(new BigDecimal(0)));
//        name("");
//        write("D:/sftp/test.txt");
    }
    public static void name(Object o){

        System.out.println(o.getClass());
        if (o instanceof String){
            System.out.println("String");
        }else {
            System.out.println("other");
        }
    }
    /**
     *
     * @param filePath 要修改的文件名
     * @param oldstr 要修改的字段
     * @param newStr 替换的字段
     */
    public static Boolean updateStartBat(String filePath, String oldstr, String newStr){
        RandomAccessFile raf = null;
        try {
            raf = new RandomAccessFile(filePath, "rw");
            String line = null;
            long lastPoint = 0; //记住上一次的偏移量
            while ((line = raf.readLine()) != null) {
                final long ponit = raf.getFilePointer();
                if(line.contains(oldstr)){
                    String str=line.replace(oldstr, newStr);
                    raf.seek(lastPoint);
                    raf.writeBytes(str);
                }
                lastPoint = ponit;
            }
        } catch (FileNotFoundException e) {

            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();
        } finally {
            try {
                raf.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return true;
    }
}
