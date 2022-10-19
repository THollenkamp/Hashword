package me.tucker.hashword.hardware;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MacInstance extends OSInstance {
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
        StringBuilder str = new StringBuilder();
        try {
            Process proc = Runtime.getRuntime().exec("system_profiler SPSoftwareDataType SPHardwareDataType");
            BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String output;
            while ((output = stdInput.readLine()) != null) {
                if (output.contains("Hardware UUID:") || output.contains("Serial Number"))
                    str.append(output);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str.toString();
    }
}
