package dangnhap.logic;

import dangnhap.entity.Account;

import java.util.Scanner;

import static dangnhap.main.Main.accounts;
import static dangnhap.main.Main.view;

public class AccountHandle {
    public void register(Scanner sc, Account account) {
        System.out.println("Nhập email của bạn: ");
        emailRegister(sc, account);
        usernameRegister(sc, account);
        passwordRegister(sc, account);
        System.out.println("Đăng kí thành công, xin mời đăng nhập.");
    }

    private void passwordRegister(Scanner sc, Account account) {
        System.out.println("Mật khẩu: ");
        String password;
        String regexPattern = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[.,-_;]).{8,15})";
        do {
            password =  sc.nextLine();
            if (!patternMatches(password, regexPattern)) {
                System.out.println("Mật khẩu phải chứa từ 8-15 kí tự, chứa ít nhất 1 ký tự in hoa, 1 ký tự đặc biệt (. , - _ ;)");
            }
        } while (patternMatches(password, regexPattern));
        account.setPassword(sc.nextLine());
    }

    private void emailRegister(Scanner sc, Account account) {
        String email;
        String regexPattern = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
        do {
            email = sc.nextLine();
            if (accounts.size() == 0) {
                break;
            }
            if (!patternMatches(email, regexPattern)) {
                System.out.println("Bạn cần nhập đúng định dạng email.");
            }
        } while (checkEmailValid(email) && patternMatches(email, regexPattern));
        account.setEmail(email);
    }

    private boolean checkEmailValid(String email) {
        boolean flag =  false;
        for (Account i : accounts) {
            if (email.equals(i.getEmail())) {
                System.out.println("Email này đã được sử dụng.");
                flag = true;
            }
        }
        return flag;
    }

    private void usernameRegister(Scanner sc, Account account) {
        System.out.println("Nhập username: ");
        String user;
        do {
            user = sc.nextLine();
            if (accounts.size() == 0) {
                break;
            }
        } while (checkUserValid(user));
        account.setUsername(user);
    }

    private boolean checkUserValid(String user) {
        boolean flag =  false;
        for (Account i : accounts) {
            if (user.equals(i.getUsername())) {
                System.out.println("Username này đã được sử dụng.");
                flag = true;
            }
        }
        return flag;
    }


    public void inputDefaultAccount() {
        Account account1 = new Account("trananhquan335@gmail.com", "xomchua1234", "quan123");
        Account account2 = new Account("trananhquan@gmail.com", "xomchua123456", "quan12313");
        accounts.add(account1);
        accounts.add(account2);
    }

    public void logIn(Scanner sc) {
        Account account = userLogIn(sc);
        passwordLogIn(sc, account);
    }

    public Account userLogIn(Scanner sc) {
        System.out.println("Nhập username: ");
        String user;
        Account account = null;
        int count = 0;
        do {
            user = sc.nextLine();
            for (Account i : accounts) {
                if (user.equals(i.getUsername())) {
                    account = i;
                    count++;
                }
            }
            if (count == 0) {
                System.out.println("Kiểm tra lại username.");
            }
        } while (count == 0);
        return account;
    }

    public void passwordLogIn(Scanner sc, Account account) {
        System.out.println("Nhập password: ");
        String password = sc.nextLine();
        if (password.equals(account.getPassword())) {
            System.out.println("Chào mừng " + account.getUsername() + ", bạn có thể thực hiện các công việc sau:");
            LogicHandle logicHandle = new LogicHandle();
            while (true) {
                view.LoginView();
                logicHandle.menuLogin(sc, account);
            }
        } else {
            System.out.println("Password không đúng");
            System.out.println("1. Đăng nhập lại");
            System.out.println("2. Quên mật khẩu");
            int choice = LogicHandle.checkNumberException(sc, 1, 2);
            if (choice == 2) {
                checkEmailForgetMode(sc, account);
            }
        }

    }

    private void checkEmailForgetMode(Scanner sc, Account account) {
        boolean flag = true ;
        do {
            System.out.println("Nhập lại email đăng ký của bạn: ");
            String email = sc.nextLine();
            if (email.equals(account.getEmail())) {
                LogicHandle logicHandle = new LogicHandle();
                logicHandle.changePassword(sc,account);
                flag = false;
            }
            if (flag) {
                System.out.println("Email vừa nhập không khớp với email đăng ký");
            }
        } while (flag);
    }

}
