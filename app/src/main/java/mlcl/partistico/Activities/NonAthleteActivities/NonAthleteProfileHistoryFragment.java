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
public class NonAthleteProfileHistoryFragment extends Fragment {


    public NonAthleteProfileHistoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_non_athlete_profile_history, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        View view = getView();
        if (view != null) {
            TextView history = (TextView) getView().findViewById(R.id.lbl_nonAthlete_history);
            history.setText(Utils.getInstance().getActiveNonAthlete().getHistory());
        }
    }

}
