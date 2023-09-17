package com.todone.parser;

import java.io.*;

public class RWFile {
    private final String FILE_PATH;
    private StringBuilder content;

    public RWFile(String filePath) {
        this.FILE_PATH = filePath;
    }

    public StringBuilder getContent() {
        return content;
    }

    public void setContent(StringBuilder content) {
        this.content = content;
    }

    private void readContent() {
        try {
            FileReader fileReader = new FileReader(this.FILE_PATH);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;

            while ((line = bufferedReader.readLine()) != null){
                this.content.append(line).append("\n");
            }

            bufferedReader.close();
        } catch (FileNotFoundException e){
            System.out.println("El archivo no existe");
        } catch (IOException e) {
            System.out.println("Ocurrio un error al leer el archivo" + e.getMessage());
        }
    }
}
