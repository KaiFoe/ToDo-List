package foerstermann.kai.todo_list;

class Task {

    private String TaskContent;
    private final String TaskTimeStamp;
    private boolean TaskStatus;


    Task(String TaskContent, String TaskTimeStamp, boolean TaskStatus) {
        this.TaskStatus = TaskStatus;
        this.TaskTimeStamp = TaskTimeStamp;
        this.TaskContent = TaskContent;
    }

    String getTaskContent() {
        return TaskContent;
    }

    String getTaskTimeStamp() {
        return TaskTimeStamp;
    }

    boolean getTaskStatus() {
        return TaskStatus;
    }

    void setTaskStatus(boolean taskStatus) {
        TaskStatus = taskStatus;
    }

    void setTaskContent(String taskContent) {
        TaskContent = taskContent;
    }
}


