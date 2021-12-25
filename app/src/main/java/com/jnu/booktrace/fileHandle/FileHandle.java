package com.jnu.booktrace.fileHandle;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileHandle {

//    //写数据
//    public void writeFile(String fileName,String writestr) throws IOException {
//        try{
//
//            FileOutputStream fout =openFileOutput(fileName, MODE_PRIVATE);
//
//            byte [] bytes = writestr.getBytes();
//
//            fout.write(bytes);
//
//            fout.close();
//        }
//
//        catch(Exception e){
//            e.printStackTrace();
//        }
//    }
//
//    //读数据
//    public String readFile(String fileName) throws IOException{
//        String res="";
//        try{
//            FileInputStream fin = openFileInput(fileName);
//            int length = fin.available();
//            byte [] buffer = new byte[length];
//            fin.read(buffer);
//            res = EncodingUtils.getString(buffer, "UTF-8");
//            fin.close();
//        }
//        catch(Exception e){
//            e.printStackTrace();
//        }
//        return res;
//
//    }
}
