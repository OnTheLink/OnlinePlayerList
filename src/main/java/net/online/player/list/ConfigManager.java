package net.online.player.list;

import java.io.File;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;

public class ConfigManager {
    // Create config folder under its own name
    public static void createConfigFolder(String folderName) {
        File configFolder = new File("config/" + folderName);
        if (!configFolder.exists()) {
            configFolder.mkdirs();
        }
    }
    // Create config file under its own name
    public static void createConfigFile(String folderName, String fileName) {
        File configFile = new File("config/" + folderName + "/" + fileName);
        if (!configFile.exists()) {
            try {
                configFile.createNewFile();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    // Empty config file
    public static void emptyConfigFile(String folderName, String fileName) {
        File configFile = new File("config/" + folderName + "/" + fileName);
        if (configFile.exists()) {
            try {
                configFile.delete();
                configFile.createNewFile();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    // Write player name to config file
    public static void writePlayerName(String player) {
        File configFile = new File("config/OnlinePlayerList/onlinePlayerList.json");
        if (configFile.exists()) {
            try {
                String oldContent = "";
                BufferedReader reader;
                FileWriter writer;
                reader = new BufferedReader(new FileReader(configFile));
                //Reading all the lines of input text file into oldContent
                String line = reader.readLine();
                while (line != null) {
                    oldContent = oldContent + line + System.lineSeparator();
                    line = reader.readLine();
                }
                //Replacing oldString with newString in the oldContent
                String newContent = oldContent.replaceAll("]", ", \"" + player + "\"]");
                //Rewriting the input text file with newContent
                writer = new FileWriter(configFile);
                writer.write(newContent);
                reader.close();
                writer.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    // Remove player name from config file
    public static void removePlayerName(String player) {
        File configFile = new File("config/OnlinePlayerList/onlinePlayerList.json");
        if (configFile.exists()) {
            try {
                String oldContent = "";
                BufferedReader reader;
                FileWriter writer;
                reader = new BufferedReader(new FileReader(configFile));
                //Reading all the lines of input text file into oldContent
                String line = reader.readLine();
                while (line != null) {
                    oldContent = oldContent + line + System.lineSeparator();
                    line = reader.readLine();
                }
                //Replacing oldString with newString in the oldContent
                String newContent = oldContent.replaceAll(", \"" + player + "\"", "");
                //Rewriting the input text file with newContent
                writer = new FileWriter(configFile);
                writer.write(newContent);
                reader.close();
                writer.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}