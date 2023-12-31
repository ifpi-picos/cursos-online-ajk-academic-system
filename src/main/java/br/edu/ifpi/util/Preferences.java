package br.edu.ifpi.util;

import java.io.*;
import java.util.Properties;

public class Preferences {

    private static final String customDirectory = "src/main/java/br/edu/ifpi/config/";
    private static final String configs = customDirectory + "config.properties";
    private static final String darkMode = "modoEscuro";

    private static Properties properties = new Properties();

    static {
        try (InputStream input = new FileInputStream(configs)) {
            properties.load(input);
        } catch (IOException e) {
            System.out.println("-arquivo de configurações não encontrado");
        }
    }

    public static boolean isDarkMode() {
        return Boolean.parseBoolean(properties.getProperty(darkMode, "false"));
    }

    public static void setDarkMode(boolean modoEscuro) {
        properties.setProperty(darkMode, Boolean.toString(modoEscuro));

        try (OutputStream output = new FileOutputStream(configs)) {
            properties.store(output, null);
        } catch (IOException e) {
            System.out.println("-erro ao salvar arquivo de configurações");
        }
    }
}
