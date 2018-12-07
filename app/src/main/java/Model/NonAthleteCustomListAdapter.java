package Model;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Calendar;
import java.util.List;

import Database.BDNonAthlete;
import mlcl.partistico.R;

public class NonAthleteCustomListAdapter extends ArrayAdapter<BDNonAthlete> {
    private final Activity context;
    private List<BDNonAthlete> nonAthletes;

    public NonAthleteCustomListAdapter(Activity context, List<BDNonAthlete> nonAthletes) {
        super(context, R.layout.non_athlete_list_item, nonAthletes);

        this.nonAthletes = nonAthletes;
        this.context = context;
    }

    public View getView(int position, View view, ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.non_athlete_list_item, null, true);

        ImageView icon = (ImageView) rowView.findViewById(R.id.iv_icon);
        TextView name = (TextView) rowView.findViewById(R.id.lbl_name);
        TextView age = (TextView) rowView.findViewById(R.id.lbl_age);
        TextView role = (TextView) rowView.findViewById(R.id.lbl_role);


        icon.setImageBitmap(nonAthletes.get(position).getImage());

        name.setText(nonAthletes.get(position).getName());

        String date = nonAthletes.get(position).getBirthday();
        String[] calend = date.split("/");
        int day = Integer.parseInt(calend[0]);
        int month = Integer.parseInt(calend[1]);
        int year = Integer.parseInt(calend[2]);
        age.setText(getAge(year, month, day) + " Anos");


        role.setText(nonAthletes.get(position).getRole());

        rowView.setTag(nonAthletes.get(position).getId());

        return rowView;

    }

    private String getAge(int year, int month, int day) {
        Calendar dob = Calendar.getInstance();
        Calendar today = Calendar.getInstance();

        dob.set(year, month, day);

        int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);

        if (today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)) {
            age--;
        }

        Integer ageInt = new Integer(age);
        String ageS = ageInt.toString();

        return ageS;
    }
}
