package ua.goit.util;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.Scanner;

public class HandleBodyStringUtil {
    public static Optional<String> getModelFromStream(InputStream in) {
        try (InputStream inputStream = in; Scanner scanner = new Scanner(inputStream, StandardCharsets.UTF_8.name())) {
            String string = scanner.useDelimiter("\\A").next();

            System.out.println(string.replaceAll("\"", ""));

            return Optional.ofNullable(string);
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
