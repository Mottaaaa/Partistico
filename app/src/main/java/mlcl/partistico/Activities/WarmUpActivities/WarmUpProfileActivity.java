package mlcl.partistico.Activities.WarmUpActivities;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.util.Util;

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
        setContentView(R.layout.activity_warm_up_exercise_profile);

        dialog = new Dialog(this);
        dialog.setContentView(R.layout.add_warm_up_pop_up);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        TextView warmUpName = findViewById(R.id.tv_warm_up_name);
        warmUpName.setText(Utils.getInstance().getActiveWarmup().getName());

        boolean insertLeft = true;
        populateLists();
    }

    private void populateLists() {


        warmupID = Utils.getInstance().getActiveWarmup().getId();
        List<BDWarmUpExercise> exercises = Utils.getInstance().getBDWarmUpExercisesByWarmup(warmupID);
        LayoutInflater inflater = this.getLayoutInflater();
        View rowView = null;
        CheckBox checkBox;
        LinearLayout layout = null;

        layout = findViewById(R.id.list_check_buttons_left);
        layout.removeAllViews();
        layout = findViewById(R.id.list_check_buttons_right);
        layout.removeAllViews();

        for (BDWarmUpExercise exercise : exercises) {


            rowView = inflater.inflate(R.layout.warm_up_exercise_profile_item, null, true);

            checkBox = rowView.findViewById(R.id.btn_check_box);
            checkBox.setText(exercise.getName());
            checkBox.setChecked(exercise.isDone());

            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                    LinearLayout linearLayout = (LinearLayout) buttonView.getParent();
                    BDWarmUpExercise exercise = Utils.getInstance().getBDWarmUpExerciseByID((Integer) linearLayout.getTag());

                    Utils.getInstance().updateBDWarmUpExercise(exercise.getId(), exercise.getName(), isChecked);
                }
            });


            if (insertLeft) {
                layout = findViewById(R.id.list_check_buttons_left);
            } else {
                layout = findViewById(R.id.list_check_buttons_right);
            }
            insertLeft = !insertLeft;

            rowView.setTag(exercise.getId());
            layout.addView(rowView);
        }

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
                    Utils.getInstance().insertBDWarmUpExercise(new BDWarmUpExercise(name.getText().toString(), 0, warmupID));
                    populateLists();
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
        LinearLayout linearLayout = (LinearLayout) view.getParent();
        final BDWarmUpExercise exercise = Utils.getInstance().getBDWarmUpExerciseByID((Integer) linearLayout.getTag());
        name = dialog.findViewById(R.id.warmup_name);
        name.setText(exercise.getName());
        dialog.show();
        Button accept = (Button) dialog.findViewById(R.id.accept);
        Button reject = (Button) dialog.findViewById(R.id.reject);

        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!name.getText().toString().equals("")) {
                    Utils.getInstance().updateBDWarmUpExercise(exercise.getId(), name.getText().toString(), exercise.isDone());
                    populateLists();

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
        Utils.getInstance().deleteBDWarmUpExercise((Integer) linearLayout1.getTag());
        populateLists();
    }
}
