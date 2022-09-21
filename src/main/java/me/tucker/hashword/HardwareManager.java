package me.tucker.hashword;

import java.awt.*;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Properties;

public class HardwareManager {

    private Properties properties;

    public HardwareManager() {
        this.properties = System.getProperties();
    }

    public String getPropertyHash() {
        GraphicsDevice monitor = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        String str = monitor.getIDstring() + monitor.getDisplayMode().getRefreshRate() + (monitor.getDisplayMode().getHeight() * monitor.getDisplayMode().getWidth() + monitor.getDisplayMode().getBitDepth()) +
                System.getenv("PROCESSOR_IDENTIFIER") + " " + System.getenv("PROCESSOR_LEVEL") +
                properties.getProperty("os.name") + properties.getProperty("os.arch") + System.getenv("COMPUTERNAME") + properties.getProperty("user.name");
        return HashManager.getMD5(str);
    }

}
