package backEnd;

import javax.swing.*;
import java.io.*;
import javax.swing.filechooser.FileFilter;

public class FileChooser extends FileFilter {
    
    @Override
    public boolean accept(File archivo) {
       // Solo acepta archivos .txt y carpetas
       return archivo.isDirectory() || archivo.getName().toLowerCase().endsWith(".txt");
    }
    
    @Override
    public String getDescription() { // Muestra el contenido del archivo
        return "Archivos de texto (*.txt)";
    }
    
}