package Inhertance;
import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String name = sc.nextLine();
        String language = sc.nextLine();

        Developer developer = new Developer();
        developer.setName(name);
        developer.showName();
        developer.showLanguage(language);
    }
}