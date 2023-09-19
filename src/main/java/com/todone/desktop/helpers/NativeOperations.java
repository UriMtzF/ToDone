package com.todone.desktop.helpers;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class NativeOperations {
    public static void openOnDefaultBrowser(String url){
        if(Desktop.isDesktopSupported()){
            Desktop desktop = Desktop.getDesktop();
            if (desktop.isSupported(Desktop.Action.BROWSE)){
                try {
                    desktop.browse(new URI(url));
                } catch (URISyntaxException | IOException e) {
                    System.out.println("An error has occurred: " + e);
                }
            }
        }

        String os = System.getProperty("os.name").toLowerCase();
        Runtime runtime = Runtime.getRuntime();
        try {
            if (os.contains("win")){
                runtime.exec("rundll32 url.dll,FileProtocolHandler" + url);
            } else if (os.contains("mac")) {
                runtime.exec("open " + url);
            } else if (os.contains("nix") || os.contains("nux")) {
                runtime.exec("xdg-open " + url);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
