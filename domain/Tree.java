package domain;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Tree {

    /**
     * TODO: Доработать метод print, необходимо распечатывать директории и файлы
     * @param sourceFile
     * @param indent
     * @param isLast
     */
    public static void print(File sourceFile, String indent, boolean isLast){


        boolean isFile = sourceFile.isFile();
        System.out.print(indent); // рисуем отступ
        if (isLast){
            System.out.print(isFile ? "└─" : "└☐");
            indent += "  ";
        }
        else {
            System.out.print(isFile ? "├─" : "├☐");
            indent += "│ ";
        }

        System.out.println(sourceFile.getName());

        if(!isFile) {
            List<File> sortedFileList = new ArrayList<>();
            sortedFileList.addAll(Arrays.stream(Objects.requireNonNull(sourceFile.listFiles())).filter(File::isDirectory).toList());
            sortedFileList.addAll(Arrays.stream(Objects.requireNonNull(sourceFile.listFiles())).filter(File::isFile).toList());

            for(File dir : sortedFileList){
                boolean last = false;
                if(dir.equals(sortedFileList.get(sortedFileList.size()-1))) last = true;
                print(dir, indent, last);
            }
        }
    }

}
