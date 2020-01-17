package foerstermann.kai.todo_list;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class TaskAdapter extends ArrayAdapter<Task> {

    private Context context;
    private List<Task> taskList;
    private LayoutInflater layoutInflater;

    private Resources resources;
    private String packageName;

    public TaskAdapter(Context context, List<Task> taskList) {
        super(context, R.layout.tasklist_row, taskList);

        this.context = context;
        this.taskList = taskList;
        layoutInflater = LayoutInflater.from(context);

        this.resources = context.getResources();
        this.packageName = context.getPackageName();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //Erzeugen der View-Hierarchie auf Grundlage des Zeilenlayouts
        View rowView;
        if (convertView == null) {
            rowView = layoutInflater.inflate(R.layout.tasklist_row, parent, false);
        } else {
            rowView = convertView;
        }

        //Anfordern des zur Listenposition gehörenden Datenobjektes
        Task currentTask = taskList.get(position);

        //Finden der einzelnen View-Objekte
        TextView txtvtaskContent = (TextView) rowView.findViewById(R.id.txtvtaskContent);
        TextView txtvTaskTimeStamp = (TextView) rowView.findViewById(R.id.txtvTaskTimeStamp);
        ImageView imageVIew = (ImageView) rowView.findViewById(R.id.imageView);
        //Füllen der View-Objekte mit den passenden Inhalten des Datenobjekts
        txtvtaskContent.setText(currentTask.getTaskContent());
        txtvTaskTimeStamp.setText(currentTask.getTaskTimeStamp());

        if (currentTask.getTaskStatus() == true)
            imageVIew.setImageBitmap(getBitmap(android.R.drawable.checkbox_on_background));
        else
            imageVIew.setImageBitmap(getBitmap(android.R.drawable.checkbox_off_background));

        //Rückgabe der befüllten View-Hierarchie an die aufrufende AdapterView
        return rowView;
    }

    private Bitmap getBitmap(int resID) {
        Bitmap bm = BitmapFactory.decodeResource(context.getResources(), resID);
        return bm;
    }
}
