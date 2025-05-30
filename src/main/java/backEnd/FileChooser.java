package backEnd;

import javax.swing.*;
import java.io.*;
import javax.swing.filechooser.FileFilter;

public class FileChooser extends FileFilter {
    
    @Override
    public boolean accept(File archivo) {
        boolean check;
        String name;
        name = archivo.getName().toLowerCase();
        check = name.endsWith(".txt") || archivo.isDirectory();
        return check;
    }
    
    @Override
    public String getDescription() { // Muestra el contenido del archivo
        return "Archivo seleccionado";
    }
    
}