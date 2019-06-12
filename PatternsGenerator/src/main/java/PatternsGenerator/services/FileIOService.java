package PatternsGenerator.services;

import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Objects;

@Service
public class FileIOService {

    public FileIOService() {}

    public String ReadFromFile(String filePath) throws IOException {
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        File file = new File(Objects.requireNonNull(classLoader.getResource(filePath)).getFile());
        return new String(Files.readAllBytes(file.toPath()));
    }
}