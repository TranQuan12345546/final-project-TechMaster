package dangnhap.main;

import dangnhap.entity.Account;
import dangnhap.entity.View;

import java.util.ArrayList;


public class Main {
    public static ArrayList<Account> accounts = new ArrayList<>();
    public static View view = new View();
    public static void main(String[] args) {
        view.mainMenu();
    }


}
