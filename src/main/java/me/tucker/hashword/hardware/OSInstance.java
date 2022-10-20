package me.tucker.hashword.hardware;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public abstract class OSInstance {
    public OSType type;
    public String command;
    private List<String> commandOutput;

    public OSInstance(String command, OSType type) {
        this.command = command;
        this.type = type;
        this.commandOutput = new ArrayList<>();
        try {
            Process proc = Runtime.getRuntime().exec(this.command);
            BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String output;
            while ((output = stdInput.readLine()) != null) {
                commandOutput.add(output);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public abstract String getMonitorInfo();
    public abstract String getCpuInfo();
    public abstract String getGpuInfo();
    public abstract String getOsInfo();
    public abstract String getHardwareInfo();
    public abstract String getRamInfo();

    public OSType getType() {
        return type;
    }

    public String getCommand() {
        return command;
    }

    public List<String> getCommandOutput() {
        return commandOutput;
    }

    public String getDataPoint(String key) {
        for (String line : commandOutput) {
            if (line.contains(key)) {
                return line.replaceAll("\\s+", "-");
            }
        }
        return "null";
    }

    public String getDataPointFromCommand(String command, String key) {
        StringBuilder str = new StringBuilder();
        try {
            Process proc = Runtime.getRuntime().exec(command);
            BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String output;
            while ((output = stdInput.readLine()) != null) {
                if (output.contains(key))
                    str.append(output.replaceAll("\\s+", "-"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str.toString();
    }

    public static OSInstance get() {
        OSInstance instance;
        if (System.getProperty("os.name").toLowerCase().contains("windows"))
            instance = new WindowsInstance();
        else if (System.getProperty("os.name").toLowerCase().contains("mac"))
            instance = new MacInstance();
        else
            instance = new LinuxInstance();
        return instance;
    }
}
