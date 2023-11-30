package com.example.studentms.util;

import java.io.ByteArrayOutputStream;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

public class StoreFile {
    public static byte[] compressFile(byte[] bytes){
        Deflater deflater = new Deflater();
        deflater.setLevel(Deflater.BEST_COMPRESSION);
        deflater.setInput(bytes);
        deflater.finish();

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(bytes.length);
        byte[] temp = new byte[4*1024];
        while (!deflater.finished()){
            int size = deflater.deflate(temp);
            byteArrayOutputStream.write(temp,0,size);
        }

        try {
            byteArrayOutputStream.close();
        }catch (Exception ignored){

        }

        return byteArrayOutputStream.toByteArray();
    }

    public static byte[] decompressFile(byte[] bytes){
        Inflater inflater = new Inflater();
        inflater.setInput(bytes);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(bytes.length);
        byte[] temp = new byte[4*1024];

        try {
            while (!inflater.finished()){
                int count = inflater.inflate(temp);
                byteArrayOutputStream.write(temp,0,count);
            }
            byteArrayOutputStream.close();
        }catch (Exception ignored){

        }

        return byteArrayOutputStream.toByteArray();
    }
}
