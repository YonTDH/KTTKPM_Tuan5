package composite;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class Conposite {
    public static void main(String[] args) {
        Path path = Paths.get("T://");
        try {
            if (Files.isDirectory(path)) {
                Files.walkFileTree(path, new SimpleFileVisitor<Path>() {
                    @Override
                    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                        System.out.println(file + " - " + attrs.size() + " bytes");
                        return FileVisitResult.CONTINUE;
                    }
                });
            } else if (Files.isRegularFile(path)) {
                System.out.println(path + " - " + Files.size(path) + " bytes");
            } else {
                System.out.println("Path is neither a file nor a directory.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
