package majikku.majikku.Files;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class HomeConfig {

    private static File file;
    private static FileConfiguration customFile;

    public static void setup() throws IOException {
        file = new File(Bukkit.getServer().getPluginManager().getPlugin("Majikku").getDataFolder(), "home.yml");

        if(!file.exists()){
            try{
                file.createNewFile();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        customFile = YamlConfiguration.loadConfiguration(file);
    }

    public static FileConfiguration get(){
        return customFile;
    }

    public static void save(){
        try {
            customFile.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void reload(){
        customFile = YamlConfiguration.loadConfiguration(file);
    }

}
