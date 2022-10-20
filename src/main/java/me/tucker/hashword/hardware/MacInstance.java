package me.tucker.hashword.hardware;

public class MacInstance extends OSInstance {

    public MacInstance() {
        super("system_profiler SPSoftwareDataType SPHardwareDataType SPDisplaysDataType", OSType.MAC);
    }

    @Override
    public String getMonitorInfo() {
        return getDataPoint("Resolution:")
                + getDataPoint("Framebuffer Depth:")
                + getDataPoint("Connection Type:");
    }

    @Override
    public String getCpuInfo() {
        return getDataPoint("Number of Processors:")
                + getDataPoint("Processor Name:")
                + getDataPoint("Processor Speed:")
                + getDataPoint("Total Number of Cores:")
                + getDataPoint("Hyper-Threading Technology:");
    }

    @Override
    public String getGpuInfo() {
        return getDataPoint("Chipset Model:")
                + getDataPoint("Vendor:")
                + getDataPoint("VRAM")
                + getDataPoint("Device ID:");
    }

    @Override
    public String getOsInfo() {
        return getDataPoint("User Name:")
                + System.getProperty("os.name")
                + System.getProperty("os.arch");
    }

    @Override
    public String getHardwareInfo() {
        return getDataPoint("Hardware UUID:") + getDataPoint("Serial Number (system):");
    }

    @Override
    public String getRamInfo() {
        return getDataPoint("Memory:");
    }
}
