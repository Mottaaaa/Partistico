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
    private static Athlete[] athletes = Utils.getInstance().getAthletes();

    public AthleteCustomListAdapter(Activity context) {
        super(context, R.layout.athlete_list_item, athletes);
        // TODO Auto-generated constructor stub

        this.context = context;
    }

    public View getView(int position, View view, ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.athlete_list_item, null, true);

        ImageView icon = (ImageView) rowView.findViewById(R.id.icon);
        TextView name = (TextView) rowView.findViewById(R.id.name);
        TextView age = (TextView) rowView.findViewById(R.id.age);
        TextView expirationDate = (TextView) rowView.findViewById(R.id.expirationDate);
        TextView echelon = (TextView) rowView.findViewById(R.id.echelon);


        icon.setImageResource(R.drawable.hitler);
        if(position == 1)
            icon.setImageResource(R.drawable.stalin);

        name.setText(athletes[position].getName());
        age.setText(athletes[position].getAge() + " Anos");
        expirationDate.setText("Validade Atestado: " + athletes[position].getExpirationDate());
        echelon.setText(athletes[position].getEchelon());

        rowView.setTag(position);
        return rowView;
    }
}
