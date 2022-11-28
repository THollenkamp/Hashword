package me.tucker.hashword.hardware;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
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
        this.commandOutput = executeCommand(this.command);
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
        for (String line : executeCommand(command)) {
            if (line.contains(key))
                return line.replaceAll("\\s+", "-");
        }
        return "null";
    }

    public List<String> executeCommand(String command) {
        List<String> list = new ArrayList<>();
        try {
            Process proc = Runtime.getRuntime().exec(command);
            BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String output;
            while ((output = stdInput.readLine()) != null) {
                list.add(output);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }


    public static OSInstance get() {
        if (System.getProperty("os.name").toLowerCase().contains("windows"))
            return new WindowsInstance();
        else if (System.getProperty("os.name").toLowerCase().contains("mac"))
            return new MacInstance();
        else
            return new LinuxInstance();
    }

    public enum OSType {
        WINDOWS, MAC, LINUX;
    }

    public void copyToClipboard(String str) {
        StringSelection selection = new StringSelection(str);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(selection, selection);
    }

}
