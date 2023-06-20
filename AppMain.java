import domain.Tree;
import services.BackUpHandler;

import java.io.File;
import java.nio.file.Path;

public class AppMain {

    public static void main(String[] args) {
        try{
            Path sourcePath = Path.of("d:", "test");
            Path bachUpPath = Path.of("d:", "backup");

            System.out.println("Исходня директория:");
            Tree.print(new File(sourcePath.toUri()), " ", true);

            BackUpHandler backUpHandler = new BackUpHandler(sourcePath, bachUpPath);
            backUpHandler.doBackUp();

            System.out.println("\nДиректория бэкапа:");
            Tree.print(new File(bachUpPath.toUri()), " ", true);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
