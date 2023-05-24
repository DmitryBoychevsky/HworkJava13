package ru.netology.taskscheduler;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.management.Query;

public class TasksTest {

    @Test
    public void CheckSimpleTask() {
        SimpleTask check1 = new SimpleTask(5, "Позвонить родителям");

        Assertions.assertTrue(check1.matches("Позвонить"));
        Assertions.assertTrue(check1.matches("родителям"));
        Assertions.assertTrue(check1.matches("Позвонить родителям"));
        Assertions.assertFalse(check1.matches("позвонить родители"));
        Assertions.assertFalse(check1.matches("позвонить"));
        Assertions.assertFalse(check1.matches("позвонит"));
        Assertions.assertFalse(check1.matches("маме"));
    }

    @Test
    public void CheckEpic() {
        String[] subtasks = {"Молоко", "Яйца", "Хлеб"};
        Epic check2 = new Epic(55, subtasks);
        Epic epicZero = new Epic(55, new String[]{});

        Assertions.assertTrue(check2.matches("Яйца"));
        Assertions.assertFalse(check2.matches("молоко"));
        Assertions.assertFalse(check2.matches("Молока"));
        Assertions.assertFalse(check2.matches("Хлебный"));
        Assertions.assertFalse(check2.matches("пирожок"));
        Assertions.assertFalse(epicZero.matches("Хлеб"));
    }

    @Test
    public void CheckMeeting() {
        Meeting check3 = new Meeting(
                555,
                "Выкатка 3й версии приложения",
                "Приложение НетоБанка",
                "Во вторник после обеда");

        Assertions.assertTrue(check3.matches("Выкатка 3й"));
        Assertions.assertTrue(check3.matches("Приложение НетоБанка"));
        Assertions.assertFalse(check3.matches("выкатка"));
        Assertions.assertFalse(check3.matches("версия"));
        Assertions.assertFalse(check3.matches("Инструкция"));
        Assertions.assertTrue(check3.matches("НетоБанк"));
        Assertions.assertTrue(check3.matches("НетоБанка"));
        Assertions.assertFalse(check3.matches("приложение НетоБанка"));
        Assertions.assertFalse(check3.matches("Во вторник после обеда")); // но метод Meeting для start не доделан по условию
    }

}
