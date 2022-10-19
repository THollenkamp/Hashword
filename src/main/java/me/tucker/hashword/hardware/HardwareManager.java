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
        StringBuilder str = new StringBuilder();
        str.append(instance.getHardwareInfo());
        if ((boolean)Hashword.getConfig().getObject("hardware-settings").get("os"))
            str.append(instance.getOsInfo());
        if ((boolean)Hashword.getConfig().getObject("hardware-settings").get("cpu"))
            str.append(instance.getCpuInfo());
        if ((boolean)Hashword.getConfig().getObject("hardware-settings").get("ram"))
            str.append(instance.getRamInfo());
        if ((boolean) Hashword.getConfig().getObject("hardware-settings").get("monitor"))
            str.append(instance.getMonitorInfo());
        if ((boolean)Hashword.getConfig().getObject("hardware-settings").get("keyboard"))
            str.append("");
        if ((boolean)Hashword.getConfig().getObject("hardware-settings").get("mouse"))
            str.append(MouseInfo.getNumberOfButtons());
        if (Hashword.debug)
            return str.toString();
        return HashManager.getMD5(str.toString());
    }

}
