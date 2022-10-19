package me.tucker.hashword.hardware;

public class MacInstance extends OSInstance {

    @Override
    public String getCmd() {
        return "system_profiler SPSoftwareDataType SPHardwareDataType";
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
    public String getOsInfo() {
        return "null";
    }

    @Override
    public String getHardwareInfo() {
        return getDataPoint("Hardware UUID:") + getDataPoint("Serial Number");
    }

    @Override
    public String getRamInfo() {
        return null;
    }
}
