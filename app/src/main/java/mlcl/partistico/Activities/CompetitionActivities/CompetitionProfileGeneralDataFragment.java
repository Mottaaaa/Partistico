package mlcl.partistico.Activities.CompetitionActivities;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import Model.Utils;
import mlcl.partistico.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CompetitionProfileGeneralDataFragment extends Fragment {


    public CompetitionProfileGeneralDataFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_competition_profile_general_data, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        View view = getView();
        if (view != null) {

            // coisas
            TextView dates = (TextView) getView().findViewById(R.id.tv_dates);

            dates.setText(Utils.getInstance().getActiveCompetition().getStartDate() + " | " + Utils.getInstance().getActiveCompetition().getEndDate());
            activateEchelons(Utils.getInstance().getActiveCompetition().getEchelonsList());
            activateSpecializations(Utils.getInstance().getActiveCompetition().getSpecializationsList());
        }
    }

    private void activateEchelons(List<Integer> echelons) {

        TextView tvEchelon = null;
        ImageView ivEchelon = null;

        for (int echelon : echelons) {

            switch (echelon) {

                case 1:
                    tvEchelon = (TextView) getView().findViewById(R.id.tv_echelon1);
                    ivEchelon = (ImageView) getView().findViewById(R.id.iv_echelon_dot1);
                    break;
                case 2:
                    tvEchelon = (TextView) getView().findViewById(R.id.tv_echelon2);
                    ivEchelon = (ImageView) getView().findViewById(R.id.iv_echelon_dot2);
                    break;
                case 3:
                    tvEchelon = (TextView) getView().findViewById(R.id.tv_echelon3);
                    ivEchelon = (ImageView) getView().findViewById(R.id.iv_echelon_dot3);
                    break;
                case 4:
                    tvEchelon = (TextView) getView().findViewById(R.id.tv_echelon4);
                    ivEchelon = (ImageView) getView().findViewById(R.id.iv_echelon_dot4);
                    break;
                case 5:
                    tvEchelon = (TextView) getView().findViewById(R.id.tv_echelon5);
                    ivEchelon = (ImageView) getView().findViewById(R.id.iv_echelon_dot5);
                    break;
                case 6:
                    tvEchelon = (TextView) getView().findViewById(R.id.tv_echelon6);
                    ivEchelon = (ImageView) getView().findViewById(R.id.iv_echelon_dot6);
                    break;
                case 7:
                    tvEchelon = (TextView) getView().findViewById(R.id.tv_echelon7);
                    ivEchelon = (ImageView) getView().findViewById(R.id.iv_echelon_dot7);
                    break;
                case 8:
                    tvEchelon = (TextView) getView().findViewById(R.id.tv_echelon8);
                    ivEchelon = (ImageView) getView().findViewById(R.id.iv_echelon_dot8);
                    break;
            }

            tvEchelon.setAlpha(1f);
            ivEchelon.setAlpha(1f);
        }
    }

    private void activateSpecializations(List<Integer> specializations) {

        TextView tvSpecialization = null;
        ImageView ivSpecialization = null;

        for (int specialization : specializations) {

            switch (specialization) {

                case 1:
                    tvSpecialization = (TextView) getView().findViewById(R.id.tv_specialization1);
                    ivSpecialization = (ImageView) getView().findViewById(R.id.iv_specialization_dot1);
                    break;
                case 2:
                    tvSpecialization = (TextView) getView().findViewById(R.id.tv_specialization2);
                    ivSpecialization = (ImageView) getView().findViewById(R.id.iv_specialization_dot2);
                    break;
                case 3:
                    tvSpecialization = (TextView) getView().findViewById(R.id.tv_specialization3);
                    ivSpecialization = (ImageView) getView().findViewById(R.id.iv_specialization_dot3);
                    break;
                case 4:
                    tvSpecialization = (TextView) getView().findViewById(R.id.tv_specialization4);
                    ivSpecialization = (ImageView) getView().findViewById(R.id.iv_specialization_dot4);
                    break;
                case 5:
                    tvSpecialization = (TextView) getView().findViewById(R.id.tv_specialization5);
                    ivSpecialization = (ImageView) getView().findViewById(R.id.iv_specialization_dot5);
                    break;
                case 6:
                    tvSpecialization = (TextView) getView().findViewById(R.id.tv_specialization6);
                    ivSpecialization = (ImageView) getView().findViewById(R.id.iv_specialization_dot6);
                    break;
            }

            tvSpecialization.setAlpha(1f);
            ivSpecialization.setAlpha(1f);
        }
    }
}
