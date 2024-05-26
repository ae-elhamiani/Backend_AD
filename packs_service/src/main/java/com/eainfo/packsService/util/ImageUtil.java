package com.eainfo.packsService.util;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class ImageUtil {
    public static byte[] readImage(String filePath) throws IOException {
        File file = new File(filePath);
        return Files.readAllBytes(file.toPath());
    }
}
