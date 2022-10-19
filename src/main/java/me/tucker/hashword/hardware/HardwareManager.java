package me.tucker.hashword.hardware;

import me.tucker.hashword.HashManager;
import me.tucker.hashword.Hashword;
import java.awt.*;
import java.util.Properties;

public class HardwareManager {

    private Properties properties;

    public HardwareManager() {
        this.properties = System.getProperties();
    }

    public String getPropertyHash() {
        OSInstance instance;
        if (System.getProperty("os.name").toLowerCase().contains("windows"))
            instance = new WindowsInstance();
        else if (System.getProperty("os.name").toLowerCase().contains("mac"))
            instance = new MacInstance();
        else
            instance = new LinuxInstance();
        GraphicsDevice monitor = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        StringBuilder str = new StringBuilder();
        if ((boolean) Hashword.getConfig().getObject("hardware-settings").get("monitor"))
            str.append(monitor.getIDstring()).append(monitor.getDisplayMode().getRefreshRate()).append(monitor.getDisplayMode().getHeight() * monitor.getDisplayMode().getWidth() + monitor.getDisplayMode().getBitDepth());
        if ((boolean)Hashword.getConfig().getObject("hardware-settings").get("cpu"))
            str.append(System.getenv("PROCESSOR_IDENTIFIER")).append(" ").append(System.getenv("PROCESSOR_LEVEL"));
        if ((boolean)Hashword.getConfig().getObject("hardware-settings").get("keyboard"))
            str.append("");
        if ((boolean)Hashword.getConfig().getObject("hardware-settings").get("mouse"))
            str.append(MouseInfo.getNumberOfButtons());
        if ((boolean)Hashword.getConfig().getObject("hardware-settings").get("os-info")) {
            str.append(instance.getHardwareInfo());
            str.append(properties.getProperty("os.name")).append(properties.getProperty("os.arch")).append(System.getenv("COMPUTERNAME")).append(properties.getProperty("user.name"));
        }
        if (Hashword.debug)
            return str.toString();
        return HashManager.getMD5(str.toString());
    }

}
