package ua.goit.util;

import com.google.gson.Gson;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.Scanner;

public class HandleBodyUtil {

    private final static Gson GSON = new Gson();

    public static <T> Optional<T> getModelFromStream(InputStream in, Class<T> returnType) {
        try (InputStream inputStream = in; Scanner scanner = new Scanner(inputStream, StandardCharsets.UTF_8.name())) {
            String json = scanner.useDelimiter("\\A").next();

            System.out.println(json);

            return Optional.ofNullable(GSON.fromJson(json, returnType));
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}