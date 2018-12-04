package mlcl.partistico.Activities;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

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
        if(view != null) {
            // coisas
            TextView age = (TextView) getView().findViewById(R.id.tv_birdthay_date);
            TextView expirationDate = (TextView) getView().findViewById(R.id.tv_medical_expiration);
            TextView echelon = (TextView) getView().findViewById(R.id.tv_echelon);
            TextView gender = (TextView) getView().findViewById(R.id.tv_gender);

            age.setText(Utils.getInstance().getActiveAthlete().getBirthday());
            expirationDate.setText(Utils.getInstance().getActiveAthlete().getExpirationDate());
            echelon.setText(Utils.getInstance().getActiveAthlete().getEchelon());
            gender.setText(Utils.getInstance().getActiveAthlete().getGender());
        }
    }

}
