package Model.CustomListAdapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import Database.BDExercise;
import mlcl.partistico.R;

public class ExerciseCustomListAdapter extends ArrayAdapter<BDExercise> {

    private final Activity context;
    private List<BDExercise> exercises;

    public ExerciseCustomListAdapter(Activity context, List<BDExercise> exercises) {
        super(context, R.layout.exercise_list_item, exercises);

        this.exercises = exercises;
        this.context = context;
    }

    public View getView(int position, View view, ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.exercise_list_item, null, true);

        TextView number = (TextView) rowView.findViewById(R.id.tv_number);
        TextView name = (TextView) rowView.findViewById(R.id.tv_name);

        number.setText("" + exercises.get(position).getId());
        name.setText(exercises.get(position).getName());

        rowView.setTag(exercises.get(position).getId());

        return rowView;
    }
}
