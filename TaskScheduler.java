import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

class Task {
    private String title;
    private String description;
    private Date dueDate;   
    private int priority;

    public Task(String title, String description, String dueDate, int priority) {
        try {
            this.title = title;
            this.description = description;
            this.dueDate = new SimpleDateFormat("yyy-mm-dd").parse(dueDate);
            this.priority = priority;
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void updateTasks(String title, String description, String dueDate, int priority) throws ParseException {
        this.title = title;
        this.description = description;
        this.dueDate = new SimpleDateFormat("yyyy-mm-dd").parse(dueDate);
        this.priority = priority;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getDueDate() {
        return new SimpleDateFormat("yyyy-MM-dd").format(dueDate);
    }

    public int getPriority() {
        return priority;
    }

    public boolean isOverdue() {
        return new Date().after(dueDate);
    }
}

class TaskManager {
    private ArrayList<Task> tasks = new ArrayList<>();

    public void addTask(Task task) {
        tasks.add(task);
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void updateTask(int index, Task updatedTask) {
        tasks.set(index, updatedTask);
    }

    public void removeTask(int index) {
        tasks.remove(index);
    }

    public void displayTasks() {
        for (Task task : tasks) {
            System.out.println("Title: " + task.getTitle());
            System.out.println("Description: " + task.getDescription());
            System.out.println("Due Date: " + task.getDueDate());
            System.out.println("Priority: " + task.getPriority());
            System.out.println("-------------------------");
        }
    }
}

class NotificationThread extends Thread {
    private TaskManager taskManager;

    public NotificationThread(TaskManager taskManager) {
        this.taskManager = taskManager;
    }

    @Override
    public void run() {
        while (true) {
            checkAndNotify();
            try {
                Thread.sleep(60000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void checkAndNotify() {
        for (Task task : taskManager.getTasks()) {
            if (task.isOverdue()) {
                System.out.println("Notification: Task: '" + task.getTitle() + "' is OverDue!");
            }
        }
    }
}

public class TaskScheduler {
    private static Scanner scanner = new Scanner(System.in);
    private static TaskManager taskManager = new TaskManager();

    public static void main(String[] args) throws ParseException {
        NotificationThread notificationThread = new NotificationThread(taskManager);
        notificationThread.start();

        while (true) {
            displayMenu();

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addTask();
                    break;
                case 2:
                    displayTasks();
                    break;
                case 3:
                    updateTask();
                    break;
                case 4:
                    removeTask();
                    break;
                case 5:
                    exitApplication(notificationThread);
                    break;
                default:
                    System.out.println("Invalid Choice. Please try again!");

            }
        }
    }

    private static void displayMenu() {
        System.out.println("Task Management Application");
        System.out.println("1. Add Task");
        System.out.println("2. Display Tasks");
        System.out.println("3. Update Tasks");
        System.out.println("4. Remove Tasks");
        System.out.println("5. Exit");
        System.out.println("Enter your choice: ");
    }

    private static void addTask() throws ParseException {
        System.out.println("Enter task title: ");
        String title = scanner.nextLine();

        System.out.println("Enter task description: ");
        String description = scanner.nextLine();

        System.out.println("Enter due date (yyyy-MM-dd): ");
        String dueDate = scanner.nextLine();

        System.out.println("Enter priority (1-5): ");
        int priority = scanner.nextInt();

        Task task = new Task(title, description, dueDate, priority);
        taskManager.addTask(task);

        System.out.println("Task added successfully!");
    }

    private static void updateTask() throws ParseException {
        System.out.println("Enter the index of the task to update: ");
        int index = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        if (index >= 0 && index < taskManager.getTasks().size()) {
            System.out.println("Enter new task title: ");
            String title = scanner.nextLine();

            System.out.println("Enter new task description: ");
            String description = scanner.nextLine();

            System.out.println("Enter new due date (yyyy-MM-dd): ");
            String dueDate = scanner.nextLine();

            System.out.println("Enter new priority (1-5): ");
            int priority = scanner.nextInt();

            Task updatedTask = new Task(title, description, dueDate, priority);
            taskManager.updateTask(index, updatedTask);

            System.out.println("Task updated successfully!");
        } else {
            System.out.println("Invalid index. Please try again.");
        }
    }

    private static void removeTask() {
        System.out.println("Enter the index of the task to remove: ");
        int index = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        if (index >= 0 && index < taskManager.getTasks().size()) {
            taskManager.removeTask(index);
            System.out.println("Task removed successfully!");
        } else {
            System.out.println("Invalid index. Please try again.");
        }
    }

    private static void displayTasks() {
        System.out.println("Tasks:");
        taskManager.displayTasks();
    }

    private static void exitApplication(NotificationThread notificationThread) {
        notificationThread.interrupt();
        System.out.println("Exiting the application. Goodbye!");
        System.exit(0);
    }
}