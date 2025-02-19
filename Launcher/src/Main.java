import org.apache.commons.io.FileUtils;

import utils.OSHelper;
import utils.UnzipUtility;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class Main {

    public static void main(String[] args) {
        new GUI();
    }

    public static void launch() {
        File mcDir = new File(OSHelper.getOS().getMc());
        File mcAssets = new File(mcDir + File.separator + "assets");

        File natives = new File(System.getProperty("user.dir") + File.separator + "natives.zip");
        File libraries = new File(System.getProperty("user.dir") + File.separator + "libraries.zip");
        File jar = new File(System.getProperty("user.dir") + File.separator + "volt.jar");

        natives.deleteOnExit();

        try {
            FileUtils.copyURLToFile(new URL("https://github.com/StephenIsTaken/Volt-Launcher/raw/main/Assets/natives.zip"), natives);
            FileUtils.copyURLToFile(new URL("https://github.com/StephenIsTaken/Volt-Launcher/raw/main/Assets/libraries.zip"), libraries);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        UnzipUtility unzipUtility = new UnzipUtility();
        try {
            unzipUtility.unzip(natives.toString(), (System.getProperty("user.dir") + File.separator + "natives"));
            unzipUtility.unzip(libraries.toString(), (System.getProperty("user.dir") + File.separator + "libraries"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Process process = Runtime.getRuntime().exec("java "
                    + "-Xmx4g "
                    + "-Xms4g "
                    + "-Djava.library.path=\"" + (System.getProperty("user.dir") + File.separator + "natives") + "\" "
                    + "-cp \"" + jar + ";" + "libraries\"" + libraries + ";lwjgl.jar;lwjgl_util.jar\" "
                    + "net.minecraft.client.main.Main "
                    + "--gameDir " + mcDir + " "
                    + "--assetsDir " + mcAssets + " "
                    + "-uuid fc5bc365-aedf-30a8-8b89-04e462e29bde "
                    + "-accessToken yes "
                    + "-version 1 "
                    + "--assetIndex 1.8 "
                    + "-username Vanilla"
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
