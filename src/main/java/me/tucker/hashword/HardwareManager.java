package me.tucker.hashword;

import java.awt.*;
import java.util.Properties;

public class HardwareManager {

    private Properties properties;

    public HardwareManager() {
        this.properties = System.getProperties();
    }

    public String getPropertyHash() {
        GraphicsDevice monitor = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        String str = "";
        if ((boolean)Hashword.getConfig().getObject("hardware-settings").get("monitor"))
            str += monitor.getIDstring() + monitor.getDisplayMode().getRefreshRate() + (monitor.getDisplayMode().getHeight() * monitor.getDisplayMode().getWidth() + monitor.getDisplayMode().getBitDepth());
        if ((boolean)Hashword.getConfig().getObject("hardware-settings").get("cpu"))
            str += System.getenv("PROCESSOR_IDENTIFIER") + " " + System.getenv("PROCESSOR_LEVEL");
        if ((boolean)Hashword.getConfig().getObject("hardware-settings").get("keyboard"))
            str += "";
        if ((boolean)Hashword.getConfig().getObject("hardware-settings").get("mouse"))
            str += "";
        if ((boolean)Hashword.getConfig().getObject("hardware-settings").get("os-info"))
            str += properties.getProperty("os.name") + properties.getProperty("os.arch") + System.getenv("COMPUTERNAME") + properties.getProperty("user.name");
        return HashManager.getMD5(str);
    }

}
