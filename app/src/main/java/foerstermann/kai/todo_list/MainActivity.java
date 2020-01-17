package foerstermann.kai.todo_list;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    //region Deklaration der Felder und Objekte
    MainActivityListener mainActivityListener;
    ListView lvTasks;
    EditText txtInputTask;
    ImageButton btnAddTask;
    //endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //View referenzieren
        setContentView(R.layout.activity_main);

        //View-Objekte auf der View referenzieren
        lvTasks = findViewById(R.id.lvTasks);
        txtInputTask = findViewById(R.id.txtInputTask);
        btnAddTask = findViewById(R.id.btnAddTask);

        //Logic instanzieren
        mainActivityListener = new MainActivityListener(this);

        //ClickListener instanzieren
        btnAddTask.setOnClickListener(mainActivityListener);
        lvTasks.setOnItemClickListener(mainActivityListener);

        //ContextMenu registrieren
        registerForContextMenu(lvTasks);
    }

    //region OptionsMenu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return mainActivityListener.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return mainActivityListener.onOptionsItemSelected(item);
    }
    //endregion

    //region ContextMenu
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        mainActivityListener.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        return mainActivityListener.onContextItemSelected(item);
    }
    //endregion
}
