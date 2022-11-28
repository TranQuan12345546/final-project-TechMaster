package secret.main;


import secret.entity.CoupleQuesAns;
import secret.logichandle.QuesAnsLogic;
import secret.view.View;

import java.util.ArrayList;

public class Main {
    public static ArrayList<CoupleQuesAns> coupleQuesAnsArrayList = new ArrayList<>();
    public static View view = new View();
    public static void main(String[] args) {
        QuesAnsLogic quesAnsLogic = new QuesAnsLogic();
        CoupleQuesAns coupleQuesAns = new CoupleQuesAns();
        quesAnsLogic.addDefaultQues();
        while (true) {
            view.showMenu();
            view.chooseMenu(quesAnsLogic, coupleQuesAns);
        }
    }
}
