package me.tucker.hashword.hardware;

public class LinuxInstance extends OSInstance {

    public LinuxInstance() {
        super("null", OSType.LINUX);
    }

    @Override
    public String getMonitorInfo() {
        return "null";
    }

    @Override
    public String getCpuInfo() {
        return "null";
    }

    @Override
    public String getGpuInfo() {
        return null;
    }

    @Override
    public String getOsInfo() {
        return "null";
    }

    @Override
    public String getHardwareInfo() {
        return "null";
    }

    @Override
    public String getRamInfo() {
        return "null";
    }
}
