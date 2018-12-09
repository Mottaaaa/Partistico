package mlcl.partistico.Activities.WarmUpActivities;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.List;

import Database.BDWarmUp;
import Database.BDWarmUpExercise;
import Model.Utils;
import mlcl.partistico.R;

public class WarmUpProfileActivity extends AppCompatActivity {

    ListView list;
    final Activity activity = this;
    Dialog dialog;
    EditText name;
    int warmupID;
    boolean insertLeft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_warm_up_ecercise_profile);

        dialog = new Dialog(this);
        dialog.setContentView(R.layout.add_warm_up_pop_up);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        boolean insertLeft = true;
        populateList();
    }

    private void populateList() {

        warmupID = Utils.getInstance().getActiveWarmup().getId();
        List<BDWarmUpExercise> exercises = Utils.getInstance().getBDWarmUpExercisesByWarmup(warmupID);
        LayoutInflater inflater = this.getLayoutInflater();
        View rowView = null;
        CheckBox checkBox;
        LinearLayout layout = null;

        for (BDWarmUpExercise exercise : exercises) {


            rowView = inflater.inflate(R.layout.warm_up_exercise_profile_item, null, true);

            checkBox = rowView.findViewById(R.id.btn_check_box);
            checkBox.setText(exercise.getName());
            checkBox.setChecked(exercise.isDone());


            if(insertLeft){
                layout = findViewById(R.id.list_check_buttons_left);
            }else{
                layout = findViewById(R.id.list_check_buttons_right);
            }
            insertLeft = !insertLeft;

        }

        layout.addView(rowView);
    }

    public void addPopup(View view) {
        name = dialog.findViewById(R.id.warmup_name);
        dialog.show();
        Button accept = (Button) dialog.findViewById(R.id.accept);
        Button reject = (Button) dialog.findViewById(R.id.reject);

        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!name.getText().toString().equals("")) {
                    Utils.getInstance().insertBDWarmUp(new BDWarmUp(name.getText().toString()));
                    Utils.getInstance().insertBDWarmUpExercise(new BDWarmUpExercise(name.getText().toString(),0, warmupID));
                    dialog.dismiss();
                }
            }
        });

        reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name.setText("");
                dialog.dismiss();
            }
        });
    }

    public void editPopup(View view) {
        LinearLayout linearLayout1 = (LinearLayout) view.getParent();
        FrameLayout frameLayout1 = (FrameLayout) linearLayout1.getParent();
        LinearLayout linearLayout2 = (LinearLayout) frameLayout1.getParent();
        Utils.getInstance().setActiveWarmup((Integer) linearLayout2.getTag());
        BDWarmUp temp = Utils.getInstance().getActiveWarmup();
        warmupID = temp.getId();
        name = dialog.findViewById(R.id.warmup_name);
        name.setText(temp.getName());
        dialog.show();
        Button accept = (Button) dialog.findViewById(R.id.accept);
        Button reject = (Button) dialog.findViewById(R.id.reject);

        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!name.getText().toString().equals("")) {
                    Utils.getInstance().updateBDWarmup(warmupID, name.getText().toString());

                    dialog.dismiss();
                }
            }
        });

        reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name.setText("");
                dialog.dismiss();
            }
        });
    }

    public void delete(View view) {

        LinearLayout linearLayout1 = (LinearLayout) view.getParent();
        FrameLayout frameLayout1 = (FrameLayout) linearLayout1.getParent();
        LinearLayout linearLayout2 = (LinearLayout) frameLayout1.getParent();
        Utils.getInstance().setActiveWarmup((Integer) linearLayout2.getTag());
        int id = Utils.getInstance().getActiveWarmup().getId();
        Utils.getInstance().deleteBDWarmup(id);

    }
}
