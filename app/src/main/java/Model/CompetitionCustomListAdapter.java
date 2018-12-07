package Model;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import Database.BDCompetition;
import mlcl.partistico.R;

public class CompetitionCustomListAdapter extends ArrayAdapter<BDCompetition> {

    private final Activity context;
    private List<BDCompetition> competitions;

    public CompetitionCustomListAdapter(Activity context, List<BDCompetition> competitions) {
        super(context, R.layout.activity_competition_list, competitions);

        this.competitions = competitions;
        this.context = context;
    }

    public View getView(int position, View view, ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.competition_list_item, null, true);

        TextView name = (TextView) rowView.findViewById(R.id.name);
        TextView address = (TextView) rowView.findViewById(R.id.address);
        TextView dates = (TextView) rowView.findViewById(R.id.dates);
        TextView type = (TextView) rowView.findViewById(R.id.type);

        name.setText(competitions.get(position).getName());
        address.setText(competitions.get(position).getAddress());
        dates.setText(competitions.get(position).getStartDate() + " | " + competitions.get(position).getEndDate());
        type.setText(competitions.get(position).getTypeOfCompetition());

        rowView.setTag(competitions.get(position).getId());

        return rowView;

    }
}
