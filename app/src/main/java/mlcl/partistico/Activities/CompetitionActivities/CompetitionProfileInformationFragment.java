package mlcl.partistico.Activities.CompetitionActivities;


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
public class CompetitionProfileInformationFragment extends Fragment {


    public CompetitionProfileInformationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_competition_profile_information, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        View view = getView();
        if (view != null) {
            TextView information = (TextView) getView().findViewById(R.id.lbl_information);
            information.setText(Utils.getInstance().getActiveCompetition().getInformation());
        }
    }
}
