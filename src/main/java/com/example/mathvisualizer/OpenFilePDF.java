package com.example.mathvisualizer;
import java.awt.*;
import java.io.File;

public class OpenFilePDF {
        public static void openFile(String filename) {
            String command = "rundll32 url.dll,FileProtocolHandler " + filename;
            try {
                if ((new File(filename)).exists()) {
                    File myFile = new File(filename);
                    Desktop.getDesktop().open(myFile);
                } else {
                    System.out.println("File is not exists");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
}

