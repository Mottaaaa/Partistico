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

import Database.BDAthlete;
import mlcl.partistico.R;

public class AthleteCustomListAdapter extends ArrayAdapter<BDAthlete> {

    private final Activity context;
    private List<BDAthlete> athletes;

    public AthleteCustomListAdapter(Activity context, List<BDAthlete> athletes) {
        super(context, R.layout.athlete_list_item, athletes);

        this.athletes = athletes;
        this.context = context;
    }

    public View getView(int position, View view, ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.athlete_list_item, null, true);

        ImageView icon = (ImageView) rowView.findViewById(R.id.iv_icon);
        TextView name = (TextView) rowView.findViewById(R.id.lbl_name);
        TextView age = (TextView) rowView.findViewById(R.id.lbl_age);
        TextView expirationDate = (TextView) rowView.findViewById(R.id.lbl_expirationDate);
        TextView echelon = (TextView) rowView.findViewById(R.id.lbl_echelon);


        icon.setImageBitmap(athletes.get(position).getImage());

        name.setText(athletes.get(position).getName());

        String date = athletes.get(position).getBirthday();
        String[] calend = date.split("/");
        int day = Integer.parseInt(calend[0]);
        int month = Integer.parseInt(calend[1]);
        int year = Integer.parseInt(calend[2]);
        age.setText(getAge(year, month, day) + " Anos");

        expirationDate.setText("Validade Atestado: " + athletes.get(position).getExpirationDate());

        echelon.setText(athletes.get(position).getEchelon());

        rowView.setTag(athletes.get(position).getId());

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
