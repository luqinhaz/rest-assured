import io.github.cdimascio.dotenv.Dotenv;

public class EnvironmentConfig {
    private static final Dotenv dotenv = Dotenv.configure()
        .directory("D:\\LQZ\\Programação\\RestAssured\\rest-assured\\.env") // Diretório onde o .env está localizado
        .load();

    public static String get(String key) {
        return dotenv.get(key);
    }
}