package com.leopold.javalearning;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

@SpringBootTest
class JavaLearningApplicationTests {

    @Test
    void contextLoads() {
    }

    public static void main(String[] args) {
        ProcessBuilder processBuilder = new ProcessBuilder( "cmd", "/c", "ping", "192.168.180.1", "-n", "100");
        processBuilder.redirectErrorStream(true);
        StringBuilder s = new StringBuilder();
        Process process = null;
        try {
            System.out.println(LocalDateTime.now());
            process = processBuilder.start();
            final Thread t = new Thread(new A(process));
            t.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
        assert process != null;
        try (BufferedReader r = new BufferedReader(new InputStreamReader(process.getInputStream(), "GBK"))) {
            String line;
            while(process.isAlive() && (line = r.readLine()) != null) {
                    s.append(line).append("\n");
                    System.out.println(line);
//                if ((line = r.readLine()) != null) {
//                }
            }
            if (!process.isAlive()) {
                System.out.println("process close");
                r.close();
                process.getInputStream().close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            System.out.println(process.waitFor());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(LocalDateTime.now());
//        System.out.println(s);
    }
}
    class A implements Runnable{
        Process process;
        public A (Process process) {
            this.process = process;
        }

        @Override
        public void run() {
            try {
                final boolean b = process.waitFor(3, TimeUnit.SECONDS);
                if (!b) {
                    process.destroy();
                    process.destroyForcibly();
                    System.out.println("destroy");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
