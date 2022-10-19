package me.tucker.hashword.hardware;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public abstract class OSInstance {
    public abstract String getCmd();
    public abstract String getMonitorInfo();
    public abstract String getCpuInfo();
    public abstract String getOsInfo();
    public abstract String getHardwareInfo();
    public abstract String getRamInfo();

    public String getDataPoint(String key) {
        StringBuilder str = new StringBuilder();
        try {
            Process proc = Runtime.getRuntime().exec(getCmd());
            BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String output;
            while ((output = stdInput.readLine()) != null) {
                if (output.contains(key))
                    str.append(output);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str.toString();
    }
}
