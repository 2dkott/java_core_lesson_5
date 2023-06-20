package services;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class BackUpHandler {
    Path sourcePath;
    Path backupPath;
    public BackUpHandler(Path sourcePath, Path backupPath) {
        this.sourcePath = sourcePath;
        this.backupPath = backupPath;

    }

    public void doBackUp() {
        File backUpDir = new File(backupPath.toUri());
        if(!backUpDir.exists()) backUpDir.mkdir();
        copyFiles(sourcePath, backupPath);

    }

    private void copyFiles(Path sourcePath, Path targetPath) {
        List<File> fileList = Arrays.asList(Objects.requireNonNull(sourcePath.toFile().listFiles()));
        fileList.forEach(file -> {
            if(file.isFile()){
                try {
                    Files.copy(file.toPath(), Path.of(targetPath.toString(), file.getName()), REPLACE_EXISTING);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else copyFiles(file.toPath(), targetPath);
        });
    }
}
