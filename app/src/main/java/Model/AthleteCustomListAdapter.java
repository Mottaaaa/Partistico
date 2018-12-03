package Model;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import mlcl.partistico.R;

public class AthleteCustomListAdapter extends ArrayAdapter<Athlete> {

    private final Activity context;
    private final Athlete[] athletes;

    public AthleteCustomListAdapter(Activity context, Athlete[] athletes) {
        super(context, R.layout.athlete_list, athletes);
        // TODO Auto-generated constructor stub

        this.context = context;
        this.athletes = athletes;
    }

    public View getView(int position, View view, ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.athlete_list, null, true);

        ImageView icon = (ImageView) rowView.findViewById(R.id.icon);
        TextView name = (TextView) rowView.findViewById(R.id.name);
        TextView age = (TextView) rowView.findViewById(R.id.age);
        TextView expirationDate = (TextView) rowView.findViewById(R.id.expirationDate);
        TextView echelon = (TextView) rowView.findViewById(R.id.echelon);

        icon.setImageResource(R.drawable.hitler);
        name.setText(athletes[position].name);
        age.setText(athletes[position].age + " Anos");
        expirationDate.setText("Validade Atestado: " + athletes[position].expirationDate);
        echelon.setText(athletes[position].echelon);
        return rowView;

    }
}
