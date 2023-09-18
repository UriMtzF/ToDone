package com.todone.tasks;

import java.io.*;

public class RWFile {
    public static StringBuilder readFileContent(String filePath) {
        StringBuilder content = new StringBuilder();
        try {
            FileReader fileReader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;

            while ((line = bufferedReader.readLine()) != null) {
                content.append(line).append("\n");
            }

            bufferedReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("El archivo no existe");
        } catch (IOException e) {
            System.out.println("Ocurrio un error al leer el archivo" + e.getMessage());
        }
        return content;
    }
}
