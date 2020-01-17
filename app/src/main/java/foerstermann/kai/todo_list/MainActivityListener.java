package foerstermann.kai.todo_list;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Toast;

import java.io.Console;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class MainActivityListener implements View.OnClickListener, AdapterView.OnItemClickListener {

    private MainActivity mainActivity;
    private TaskAdapter taskAdapter;


    private List<Task> Tasklist = new ArrayList<>();

    MainActivityListener(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        bindAdapterToListView();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnAddTask) {
            if (!mainActivity.txtInputTask.getText().toString().isEmpty()) {
                Tasklist.add(new Task(mainActivity.txtInputTask.getText().toString(), //TaskContent
                        getTimeStamp(), //TimeStamp
                        false)); //TaskStatus
                updateTaskList();

                mainActivity.txtInputTask.setText(""); //Eingabefeld leeren
                hideSoftKeyboard();
            } else {
                Toast.makeText(mainActivity.getApplicationContext(), "Bitte TaskTitle angeben", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Task currentTask = (Task) parent.getItemAtPosition(position);
        currentTask.setTaskStatus(!currentTask.getTaskStatus());
        updateTaskList();
    }

    private void updateTaskList() {
        taskAdapter.setNotifyOnChange(true);
    }

    private void bindAdapterToListView() {
        taskAdapter = new TaskAdapter(mainActivity, Tasklist);
        mainActivity.lvTasks.setAdapter(taskAdapter);
    }

    private String getTimeStamp() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy - hh:mm", Locale.GERMAN);
        String output = simpleDateFormat.format(calendar.getTime());
        return output;
    }

    boolean onCreateOptionsMenu(Menu menu) {
        mainActivity.getMenuInflater().inflate(R.menu.options_menu, menu);
        return true;
    }

    boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuDeleteAllTasks:
                Tasklist.clear();
                break;
            case R.id.menuDeselectAll:
                for (Task currentTask : Tasklist) {
                    currentTask.setTaskStatus(false);
                }
                break;
            case R.id.menuDeleteAllTasks2:
                Tasklist.clear();
                break;
        }
        updateTaskList();
        return true;
    }

    void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        mainActivity.getMenuInflater().inflate(R.menu.context_menu, menu);
    }

    boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int listPosition = info.position;
        Task selectedTask = Tasklist.get(listPosition);
        switch (item.getItemId()) {
            case R.id.menuDeleteTask:
                Tasklist.remove(selectedTask);
                updateTaskList();
                break;
            case R.id.menuEditTask:
                EditDialog(selectedTask);
                break;
        }
        return false;
    }

    private void EditDialog(final Task selectedTask) {
        final EditText txtEditTask = new EditText(mainActivity);
        txtEditTask.setText(selectedTask.getTaskContent());
        AlertDialog.Builder alert = new AlertDialog.Builder(mainActivity);
        alert.setTitle("Edit Task")
            .setMessage("Bitte neuen Tasktitel angeben")
            .setView(txtEditTask)
            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int id) {
                    selectedTask.setTaskContent(txtEditTask.getText().toString());
                    updateTaskList();
                }
            })
            .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
            }
        });
        alert.show();
    }

    private void hideSoftKeyboard()
    {
        InputMethodManager inputMethodManager = (InputMethodManager) mainActivity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(mainActivity.getCurrentFocus().getWindowToken(), 0);
    }


}
