package dangnhap.logic;

import dangnhap.entity.Account;

import java.util.Scanner;
import java.util.regex.Pattern;

import static dangnhap.main.Main.accounts;
import static dangnhap.main.Main.view;

public class LogicHandle {
    public boolean patternMatches(String string, String regexPattern) {
        return Pattern.compile(regexPattern)
                .matcher(string)
                .find();
    }

    public static int checkNumberException(Scanner sc, int fisrtNumber, int lastNumber) {
        int choice =-1;
        do {
            try {
                choice = Integer.parseInt(sc.nextLine());
                if (choice < fisrtNumber || choice > lastNumber) {
                    System.out.println("Lựa chọn không hợp lệ, vui lòng chọn lại");
                }
            } catch (Exception e ) {
                System.out.println("Bạn cần nhập vào 1 số.");
            }
        } while (choice < fisrtNumber || choice > lastNumber);
        return choice;
    }

    public void menuLogin(Scanner sc, Account account) {
        int choice = checkNumberException(sc, 0, 4);
        switch (choice) {
            case 0: System.exit(0);
                break;
            case 1: changeUsername(sc, account);
                break;
            case 2: changeEmail(sc, account);
                break;
            case 3: changePassword(sc, account);
                break;
            case 4:
                view.mainMenu();
                break;
        }
    }

    public void changePassword(Scanner sc, Account account) {
        System.out.println("Nhập mật khẩu mới: ");
        String newPassword;
        String regexPattern = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[.,-_;]).{8,15})";
        boolean flag = true;
        do {
            int count = 0;
            newPassword = sc.nextLine();
            if (newPassword.equals(account.getPassword())) {
                System.out.println("Mật khẩu mới không được trùng với mật khẩu cũ.");
                count++;
            }
            if (!patternMatches(newPassword, regexPattern)) {
                System.out.println("Mật khẩu phải chứa từ 8-15 kí tự, chứa ít nhất 1 ký tự in hoa, 1 ký tự đặc biệt (. , - _ ;)");
                count++;
            }
            if (count == 0) {
                flag = false;
            }
        } while (flag);
        account.setPassword(newPassword);
        System.out.println("Thay đổi password thành công");
    }

    private void changeEmail(Scanner sc, Account account) {
        System.out.println("Nhập email mới: ");
        String newEmail;
        String regexPattern = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
        boolean flag = true;
        do {
            int count = 0;
            newEmail = sc.nextLine();
            for (Account i : accounts) {
                if (newEmail.equals(i.getEmail())) {
                    if (newEmail.equals(account.getEmail())) {
                        System.out.println("Email mới không được trùng với email cũ.");
                        count++;
                        continue;
                    }
                    System.out.println("Email này đã được sử dụng.");
                    count++;
                    break;
                }
            }
            if (!patternMatches(newEmail, regexPattern)) {
                System.out.println("Bạn cần nhập đúng định dạng email.");
                count++;
            }
            if (count == 0) {
                flag = false;
            }
        } while (flag);
        account.setEmail(newEmail);
        System.out.println("Thay đổi email thành công");
    }

    private void changeUsername(Scanner sc, Account account) {
        System.out.println("Nhập username mới: ");
        String newUsername;
        boolean flag = true;
        do {
            int count = 0;
            newUsername = sc.nextLine();
            for (Account i : accounts) {
                if (newUsername.equals(i.getUsername())) {
                    if (newUsername.equals(account.getUsername())) {
                        System.out.println("Username mới không được trùng với username cũ.");
                        count++;
                        continue;
                    }
                    System.out.println("Username này đã được sử dụng.");
                    count++;
                    break;
                }
            }
            if (count == 0) {
                flag = false;
            }
        } while (flag);
        account.setUsername(newUsername);
        System.out.println("Thay đổi username thành công");
    }
}
