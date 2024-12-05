package com.leopold.javalearning;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class TestProcessBuilder {
    public static void main(String[] args) {
        try (FileChannel channel = new FileOutputStream("E:\\MarkDown\\1.txt").getChannel()) {
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            String str = "aaabbbccc";
            System.out.println(str.getBytes().length);
            buffer.put("aaabbbccc".getBytes());
            buffer.flip();
            channel.write(buffer);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
