package me.tucker.hashword.hardware;

import java.awt.*;

public class WindowsInstance extends OSInstance {

    @Override
    public String getCmd() {
        return "systeminfo";
    }

    @Override
    public String getMonitorInfo() {
        GraphicsDevice monitor = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        return monitor.getIDstring() + monitor.getDisplayMode().getRefreshRate() + monitor.getDisplayMode().getHeight() * monitor.getDisplayMode().getWidth() + monitor.getDisplayMode().getBitDepth();
    }

    @Override
    public String getCpuInfo() {
        return getDataPoint("BIOS Version:");
    }

    @Override
    public String getOsInfo() {
        return getDataPoint("OS Name:") + getDataPoint("OS Manufacturer:") + getDataPoint("OS Configuration:");
    }

    @Override
    public String getHardwareInfo() {
        return getDataPoint("Product ID:");
    }

    @Override
    public String getRamInfo() {
        return getDataPoint("Total Physical Memory:");
    }
}
