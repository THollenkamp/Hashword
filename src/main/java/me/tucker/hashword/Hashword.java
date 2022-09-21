package me.tucker.hashword;

import lombok.Getter;
import java.util.Scanner;

public class Hashword {
    @Getter
    private static Scanner keyboard;
    private static int code = 1;

    public static void main(String[] args) {
        keyboard = new Scanner(System.in);
        displayASCII();
        String properties = new HardwareManager().getPropertyHash();
        System.out.print("Enter your authentication key: ");
        String key = keyboard.next();
        while (code != 0) {
            System.out.print("Enter domain for account: ");
            String domain = keyboard.next();
            System.out.print("Enter email/username for account: ");
            String user = keyboard.next();
            String concat = concat(key, domain, user, properties);
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

    }

    private static void displayASCII() {
        System.out.println("+--------------------------------------------------------+");
        System.out.println("|      __  __           __                           __  |");
        System.out.println("|     / / / /___ ______/ /_ _      ______  _________/ /  |");
        System.out.println("|    / /_/ / __ `/ ___/ __ \\ | /| / / __ \\/ ___/ __  /   |");
        System.out.println("|   / __  / /_/ (__  ) / / / |/ |/ / /_/ / /  / /_/ /    |");
        System.out.println("|  /_/ /_/\\__,_/____/_/ /_/|__/|__/\\____/_/   \\__,_/     |");
        System.out.println("|                                                        |");
        System.out.println("+--------------------------------------------------------+");
    }


    private static String concat(String key, String domain, String user, String properties) {
        StringBuilder builder = new StringBuilder();
        builder.append(user, 0, user.length() > 4 ? 4 : user.length() - 1)
                .append(properties, properties.length() < 2 ? 0 : 1, properties.length() < 12 ? properties.length() - 1 : 12)
                .append(domain, 0, domain.length() > 2 ? 2 : domain.length() -1)
                .append(properties, properties.length() > 14 ? 14 : 0, properties.length() > 28 ? 28 : properties.length() - 1)
                .append(key.charAt(0))
                .reverse()
                .append(properties, properties.length() / 2, properties.length() - 1)
                .reverse()
                .append(key, key.length() > 5 ? 3 : 0, key.length() > 6 ? 6 : key.length() - 1)
                .append(properties.substring(properties.length() > 5 ? properties.length() - 6 : 0))
                .append(key.length())
                .reverse()
                .append(new StringBuilder(domain.substring(0, domain.length() / 2)).reverse().append(domain.substring(domain.length() / 2)))
                .append(properties)
                .append(user, user.length() > 5 ? user.length() - 6 : 0, user.length() - 3)
                .reverse()
                .append(properties)
                .reverse();
        return builder.toString();
    }

}
