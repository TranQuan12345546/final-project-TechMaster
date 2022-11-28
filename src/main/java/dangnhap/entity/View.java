package dangnhap.entity;

import dangnhap.logic.LogicHandle;
import dangnhap.logic.RegisterLogic;

import java.util.Scanner;

import static dangnhap.main.Main.accounts;

public class View {
    public void mainMenu() {
        Scanner sc = new Scanner(System.in);
        RegisterLogic registerLogic = new RegisterLogic();
        registerLogic.inputDefaultAccount();
        while (true) {
            showMenu();
            int choose = LogicHandle.checkNumberException(sc, 1, 2);
            if (choose == 1) {
                register(sc);
            }
            if (choose == 2) {
                logIn(sc);
            }
        }
    }

    public void showMenu() {
        System.out.println("Welcome to JAVA INTERVIEW QUESTION PROGRAM: ");
        System.out.println("1. Register");
        System.out.println("2. Log in");
    }


    public void register(Scanner sc) {
        RegisterLogic registerLogic = new RegisterLogic();
        Account account = new Account();
        registerLogic.register(sc, account);
        accounts.add(account);
    }

    public void logIn(Scanner sc) {
        LogInLogic logInLogic = new LogInLogic();
        logInLogic.logIn(sc);
    }

    public void LoginView() {
        System.out.println("1. Thay đổi username.");
        System.out.println("2. Thay đổi email.");
        System.out.println("3. Thay đổi mật khẩu.");
        System.out.println("4. Đăng xuất.");
        System.out.println("0. Thoát chương trình");
    }

}
