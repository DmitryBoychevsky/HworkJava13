package ru.netology.taskscheduler;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TodosTest {

    SimpleTask simpleTask = new SimpleTask(5, "Позвонить родителям версии");

    String[] subtasks = {"Молоко", "Яйца", "Хлеб"};
    Epic epic = new Epic(55, subtasks);

    Meeting meeting = new Meeting(
            555,
            "Выкатка 3й версии приложения",
            "Приложение НетоБанка",
            "Во вторник после обеда"
    );

    Todos todos = new Todos();

    @BeforeEach
    public void assist() {
        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);
    }

    @Test
    public void addOneTaskMeeting() {
        Task[] expected = {meeting};
        Task[] actual = todos.search("Выкатка");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void addAllTasks() {
        Task[] expected = {simpleTask, epic, meeting};
        Task[] actual = todos.findAll();
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void addOneTaskEpic() {

        Task[] expected = {epic};
        Task[] actual = todos.search("Хлеб");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void requestDoesNotRun() {
        Task[] expected = {};
        Task[] actual = todos.search("Во вторник");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void addEmptyRequest() {
        Task[] expected = {};
        Task[] actual = todos.search("   ");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void addOneTaskSimpleTask() {
        Task[] expected = {simpleTask};
        Task[] actual = todos.search("Позвонить");
        Assertions.assertArrayEquals(expected, actual);  // находится одна задача.
    }

    @Test
    public void requestShouldNotBeLocated() {
        Task[] expected = {};
        Task[] actual = todos.search("среда");
        Assertions.assertArrayEquals(expected, actual);  // находится 0 задач, т.е. ни одна задача не подходит.
    }

    @Test
    public void thereAreSeveralTasks() {
        Task[] expected = {simpleTask, meeting};

        Task[] actual = todos.search("версии");
        Assertions.assertArrayEquals(expected, actual);  // находится несколько задач.
    }
}
