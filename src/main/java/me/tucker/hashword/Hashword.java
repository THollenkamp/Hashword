package me.tucker.hashword;

import me.tucker.hashword.hardware.HardwareManager;

import java.util.Scanner;

public class Hashword {
    private static Scanner keyboard;
    private static int code = 1;
    private static Config config;
    public static boolean debug = true;

    public static void main(String[] args) {
        keyboard = new Scanner(System.in);
        config = new Config();
        displayASCII();
        String properties = new HardwareManager().getPropertyHash();
        System.out.print("Enter your authentication key: ");
        String key = keyboard.next();
        while (code != 0 && !debug) {
            System.out.print("Enter domain for account: ");
            String domain = keyboard.next();
            System.out.print("Enter email/username for account: ");
            String user = keyboard.next();
            String concat = HashManager.concat(key, domain, user, properties);
            try {
                String password = HashManager.getPassword(HashManager.getMD5(concat), properties);
                System.out.println("Your password for " + domain + " is: " + password);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.print("\nWould you like to generate another password? (y/n) ");
            if (!keyboard.next().equalsIgnoreCase("y"))
                code = 0;
            System.out.println();
        }
        if (debug) {
            System.out.println("Properties hash: " + properties);
            System.out.println("OS Info: " + System.getProperty("os.name") + " " + System.getProperty("os.arch"));
        }

    }

    private static void displayASCII() {
        System.out.println("NOTICE: This project depends on your specific hardware settings,");
        System.out.println("so making any hardware changes to your computer may change any passwords");
        System.out.println("that have been generated. Please make sure to make backups of any passwords");
        System.out.println("before making any hardware changes to your computer!");
        System.out.println();
        System.out.println("+--------------------------------------------------------+");
        System.out.println("|      __  __           __                           __  |");
        System.out.println("|     / / / /___ ______/ /_ _      ______  _________/ /  |");
        System.out.println("|    / /_/ / __ `/ ___/ __ \\ | /| / / __ \\/ ___/ __  /   |");
        System.out.println("|   / __  / /_/ (__  ) / / / |/ |/ / /_/ / /  / /_/ /    |");
        System.out.println("|  /_/ /_/\\__,_/____/_/ /_/|__/|__/\\____/_/   \\__,_/     |");
        System.out.println("|                                                        |");
        System.out.println("+--------------------------------------------------------+");
    }

    public static Config getConfig() {
        return config;
    }

    public static int getCode() {
        return code;
    }

    public static Scanner getKeyboard() {
        return keyboard;
    }
}