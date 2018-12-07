package mlcl.partistico.Activities.AthleteActivities;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import Database.BDAthlete;
import Model.Utils;
import mlcl.partistico.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class AthleteProfilePersonalDataFragment extends Fragment {


    public AthleteProfilePersonalDataFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        return inflater.inflate(R.layout.fragment_athlete_profile_personal_data, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        View view = getView();
        if (view != null) {
            // coisas
            TextView age = (TextView) getView().findViewById(R.id.tv_birthday_date);
            TextView gender = (TextView) getView().findViewById(R.id.tv_gender);
            TextView echelon = (TextView) getView().findViewById(R.id.tv_echelon);
            TextView expirationDate = (TextView) getView().findViewById(R.id.tv_medical_expiration);

            age.setText(Utils.getInstance().getActiveAthlete().getBirthday());
            gender.setText(Utils.getInstance().getActiveAthlete().getGender());
            echelon.setText(Utils.getInstance().getActiveAthlete().getEchelon());
            expirationDate.setText(Utils.getInstance().getActiveAthlete().getExpirationDate());
        }
    }
}
