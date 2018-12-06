package mlcl.partistico.Activities.NonAthleteActivities;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import Model.Utils;
import mlcl.partistico.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class NonAthleteProfilePersonalDataFragment extends Fragment {


    public NonAthleteProfilePersonalDataFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_non_athlete_profile_personal_data, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        View view = getView();
        if (view != null) {
            // coisas
            TextView age = (TextView) getView().findViewById(R.id.tv_nonAthlete_birdthay_date);
            TextView role = (TextView) getView().findViewById(R.id.tv_nonAthlete_role);
            TextView gender = (TextView) getView().findViewById(R.id.tv_nonAthlete_gender);

            age.setText(Utils.getInstance().getActiveNonAthlete().getBirthday());
            role.setText(Utils.getInstance().getActiveNonAthlete().getRole());
            gender.setText(Utils.getInstance().getActiveNonAthlete().getGender());
        }
    }

}
