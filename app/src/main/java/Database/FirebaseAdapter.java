package Database;

import android.content.Context;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import Model.bosta;

public class FirebaseAdapter {
    private FirebaseDatabase database;
    private Context context;
    BDClub[] clubs;
    List<BDClub> list;
    List<BDAthlete> athletes;
    List<BDNonAthlete> nonAthletes;
    BDNonAthlete nonAthlete;
    BDAthlete athlete;
    String str = "";

    public FirebaseAdapter(Context context) {
        this.database = FirebaseDatabase.getInstance("https://cmpartistico.firebaseio.com/");
        this.context = context;
        this.athletes = new ArrayList<>();
        this.nonAthletes = new ArrayList<>();
        this.list = new ArrayList<>();
        nonAthlete = null;
        athlete = null;
    }

    public void bosta() {
        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                bosta o = dataSnapshot.getValue(bosta.class);

                Object value;

                Iterable<DataSnapshot> children = dataSnapshot.getChildren();

                for (DataSnapshot snap : children) {
                    value = snap.getValue();
                }
                // ...
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                //Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
                // ...
            }
        };
        database.getReference().addListenerForSingleValueEvent(postListener);
    }

    /*public String getOla(){
        database.getReference("clubs").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                str = dataSnapshot.getValue(String.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return str;
    }**/

    /*
    public void populateClubs() {
        List<BDClub> clubs = new ArrayList<>();

        clubs.add(new BDClub(1, "Axis", BitmapFactory.decodeResource(context.getResources(), R.drawable.hitler)));
        clubs.add(new BDClub(2, "Allies", BitmapFactory.decodeResource(context.getResources(), R.drawable.hitler)));
        clubs.add(new BDClub(3, "Neutrals", BitmapFactory.decodeResource(context.getResources(), R.drawable.hitler)));

        database.getReference().child("clubs").setValue(clubs);
    }

    public void populateAthletes() {
        List<BDAthlete> athletes = new ArrayList<>();
        athletes.add(new BDAthlete(1, BitmapFactory.decodeResource(context.getResources(), R.drawable.hitler), "Adolf Hitler", "20/04/1889", "30/04/1945", "Führer", "Masculino", "Genocia", 2));
        athletes.add(new BDAthlete(2, BitmapFactory.decodeResource(context.getResources(), R.drawable.stalin), "Joseph Stalin", "18/12/1878", "05/03/1953", "General Secretary", "Masculino", "Arquinimigo do Hitler", 2));
        athletes.add(new BDAthlete(3, BitmapFactory.decodeResource(context.getResources(), R.drawable.churchill), "Winston Churchill", "30/11/1874", "24/01/1965", "Prime Minister", "Masculino", "Fez um brilhante discurso", 2));
        athletes.add(new BDAthlete(4, BitmapFactory.decodeResource(context.getResources(), R.drawable.mussolini), "Benito Mussolini", "29/07/1883", "28/04/1945", "Duce", "Masculino", "Compincha do Hitler", 1));
        athletes.add(new BDAthlete(5, BitmapFactory.decodeResource(context.getResources(), R.drawable.hirohito), "Hirohito", "29/04/1901", "07/01/1989", "Emperor", "Masculino", "Levou com duas bombas que se lixou", 1));

        database.getReference().child("athletes").setValue(athletes);
    }

    public void populateNonAthletes() {
        List<BDNonAthlete> nonAthletes = new ArrayList<>();
        nonAthletes.add(new BDNonAthlete(1, BitmapFactory.decodeResource(context.getResources(), R.drawable.salazar), "António de Oliveira Salazar", "28/04/1889", "Presidente", "Masculino", "Caiu da cadeira", 3));
        nonAthletes.add(new BDNonAthlete(2, BitmapFactory.decodeResource(context.getResources(), R.drawable.franco), "Francisco Franco", "04/12/1892", "Presidente", "Masculino", "Ficou com ciúmes do Salazar", 3));
        nonAthletes.add(new BDNonAthlete(3, BitmapFactory.decodeResource(context.getResources(), R.drawable.roosevelt), "Franklin Delano Roosevelt", "30/01/1882", "President", "Masculino", "Ficou triste por a guerra estar a acabar e morreu", 2));

        database.getReference().child("nonathletes").setValue(nonAthletes);
    }
    */
}
