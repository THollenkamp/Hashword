package me.tucker.hashword.hardware;

import me.tucker.hashword.HashManager;
import me.tucker.hashword.Hashword;
import org.json.simple.JSONObject;

import java.awt.*;
import java.util.Properties;

public class HardwareManager {

    private Properties properties;

    public HardwareManager() {
        this.properties = System.getProperties();
    }

    public String getPropertyHash(OSInstance instance) {
        StringBuilder str = new StringBuilder();
        str.append(instance.getHardwareInfo());
        JSONObject hardwareSettings = Hashword.getConfig().getObject("hardware-settings");
        if ((boolean)hardwareSettings.get("os"))
            str.append(instance.getOsInfo());
        if ((boolean)hardwareSettings.get("cpu"))
            str.append(instance.getCpuInfo());
        if ((boolean)hardwareSettings.get("gpu"))
            str.append(instance.getGpuInfo());
        if ((boolean)hardwareSettings.get("ram"))
            str.append(instance.getRamInfo());
        if ((boolean)hardwareSettings.get("monitor"))
            str.append(instance.getMonitorInfo());
        if ((boolean)hardwareSettings.get("keyboard"))
            str.append("");
        if ((boolean)hardwareSettings.get("mouse"))
            str.append(MouseInfo.getNumberOfButtons());
        if (Hashword.debug)
            return str.toString();
        return HashManager.getMD5(str.toString());
    }

}
