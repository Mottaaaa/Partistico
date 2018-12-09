package Database;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Model.Utils;
import mlcl.partistico.R;

public class FirebaseAdapter {
    private FirebaseDatabase database;
    private Context context;
    List<BDClub> list;
    List<BDAthlete> athletes;
    List<BDNonAthlete> nonAthletes;
    List<BDCompetition> competitions;
    Utils util;
    Bitmap image;
    private static File localAthleteFile;
    private static File localNonAthleteFile;
    private static File localClubFile;


    public FirebaseAdapter(Context context, Utils util) {
        this.util = util;
        this.database = FirebaseDatabase.getInstance("https://cmpartistico.firebaseio.com/");
        this.context = context;
        this.athletes = new ArrayList<>();
        this.nonAthletes = new ArrayList<>();
        this.list = new ArrayList<>();
        this.competitions = new ArrayList<>();
    }

    public void getClubs() {

        final DatabaseAdapter adapter = new DatabaseAdapter(context);
        list.clear();

        ValueEventListener listener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                adapter.deleteClubTable();
                for (DataSnapshot snap : dataSnapshot.getChildren()) {
                    int id = snap.child("id").getValue(int.class);
                    String name = snap.child("name").getValue(String.class);
                    BDClub club = new BDClub(id, name);
                    list.add(club);
                }
                util.populateClubs(list);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };

        database.getReference("clubs").addValueEventListener(listener);
    }

    public void getAthletes() {

        final DatabaseAdapter adapter = new DatabaseAdapter(context);
        athletes.clear();

        ValueEventListener listener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                adapter.deleteAthleteTable();
                for (DataSnapshot snap : dataSnapshot.getChildren()) {
                    int id = snap.child("id").getValue(int.class);
                    String name = snap.child("name").getValue(String.class);
                    String image = snap.child("image").getValue(String.class);

                    String birthday = snap.child("birthday").getValue(String.class);
                    String expirationDate = snap.child("expirationDate").getValue(String.class);
                    String echelon = snap.child("echelon").getValue(String.class);
                    String gender = snap.child("gender").getValue(String.class);
                    String notes = snap.child("notes").getValue(String.class);
                    String history = snap.child("history").getValue(String.class);
                    int clubID = snap.child("clubID").getValue(int.class);

                    BDAthlete athlete = new BDAthlete(id, image, name, birthday, expirationDate, echelon, gender, notes, history, clubID);
                    athletes.add(athlete);
                }

                util.populateAthletes(athletes);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };

        database.getReference("athletes").addValueEventListener(listener);
    }

    public void getNonAthletes() {

        final DatabaseAdapter adapter = new DatabaseAdapter(context);
        nonAthletes.clear();

        ValueEventListener listener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                adapter.deleteNonAthleteTable();
                for (DataSnapshot snap : dataSnapshot.getChildren()) {
                    //BDNonAthlete nonAthlete = snap.getValue(BDNonAthlete.class);

                    int id = snap.child("id").getValue(int.class);
                    String name = snap.child("name").getValue(String.class);
                    String image = snap.child("image").getValue(String.class);
                    String birthday = snap.child("birthday").getValue(String.class);
                    String role = snap.child("role").getValue(String.class);
                    String gender = snap.child("gender").getValue(String.class);
                    String notes = snap.child("notes").getValue(String.class);
                    String history = snap.child("history").getValue(String.class);
                    int clubID = snap.child("clubID").getValue(int.class);

                    //BDNonAthlete nonAthlete = new BDNonAthlete(id, name, birthday, role, gender, history, clubID);
                    BDNonAthlete nonAthlete = new BDNonAthlete(id,image, name, birthday, role, gender, notes, history, clubID);
                    nonAthletes.add(nonAthlete);
                    //getNonAthleteImageFromFirebase(id);
                }

                util.populateNonAthletes(nonAthletes);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };

        database.getReference("nonathletes").addValueEventListener(listener);
    }

    public void getCompetitions() {

        final DatabaseAdapter databaseAdapter = new DatabaseAdapter(context);
        competitions.clear();

        ValueEventListener listener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                databaseAdapter.deleteCompetitionTable();
                for (DataSnapshot snap : dataSnapshot.getChildren()) {
                    //BDNonAthlete nonAthlete = snap.getValue(BDNonAthlete.class);

                    int id = snap.child("id").getValue(int.class);
                    String name = snap.child("name").getValue(String.class);
                    String coordinates = snap.child("coordinates").getValue(String.class);
                    String startDate = snap.child("startDate").getValue(String.class);
                    String endDate = snap.child("endDate").getValue(String.class);
                    String typeOfCompetition = snap.child("typeOfCompetition").getValue(String.class);
                    String echelons = snap.child("echelons").getValue(String.class);
                    String specializations = snap.child("specializations").getValue(String.class);
                    String information = snap.child("information").getValue(String.class);

                    //BDNonAthlete nonAthlete = new BDNonAthlete(id, name, birthday, role, gender, history, clubID);
                    BDCompetition competition = new BDCompetition(id, name, coordinates, startDate, endDate, typeOfCompetition, echelons, specializations, information);
                    competitions.add(competition);
                    //getNonAthleteImageFromFirebase(id);
                }

                util.populateCompetitions(competitions);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };

        database.getReference("competitions").addValueEventListener(listener);
    }

    public void populate() {
        populateNonAthletes();
        populateAthletes();
        populateClubs();
    }

    public void populateClubs() {
        List<BDClub> clubs = new ArrayList<>();

        clubs.add(new BDClub(1, "Axis" ));
        clubs.add(new BDClub(2, "Allies"));
        clubs.add(new BDClub(3, "Neutrals"));

        database.getReference().child("clubs").setValue(clubs);
    }

    public void populateAthletes() {
        List<BDAthlete> athletes = new ArrayList<>();
        athletes.add(new BDAthlete(1, Utils.bitmapToString(BitmapFactory.decodeResource(context.getResources(), R.drawable.hitler)), "Adolf Hitler", "20/04/1889", "30/04/1945", "Führer", "Masculino", "Genocia", 2));
        athletes.add(new BDAthlete(2, Utils.bitmapToString(BitmapFactory.decodeResource(context.getResources(), R.drawable.stalin)), "Joseph Stalin", "18/12/1878", "05/03/1953", "General Secretary", "Masculino", "Arquinimigo do Hitler", 2));
        athletes.add(new BDAthlete(3, Utils.bitmapToString(BitmapFactory.decodeResource(context.getResources(), R.drawable.churchill)), "Winston Churchill", "30/11/1874", "24/01/1965", "Prime Minister", "Masculino", "Fez um brilhante discurso", 2));
        athletes.add(new BDAthlete(4, Utils.bitmapToString(BitmapFactory.decodeResource(context.getResources(), R.drawable.mussolini)), "Benito Mussolini", "29/07/1883", "28/04/1945", "Duce", "Masculino", "Compincha do Hitler", 1));
        athletes.add(new BDAthlete(5, Utils.bitmapToString(BitmapFactory.decodeResource(context.getResources(), R.drawable.hirohito)), "Hirohito", "29/04/1901", "07/01/1989", "Emperor", "Masculino", "Levou com duas bombas que se lixou", 1));

        database.getReference().child("athletes").setValue(athletes);
    }

    public void populateNonAthletes() {
        List<BDNonAthlete> nonAthletes = new ArrayList<>();
        nonAthletes.add(new BDNonAthlete(1, Utils.bitmapToString(BitmapFactory.decodeResource(context.getResources(), R.drawable.salazar)), "António de Oliveira Salazar", "28/04/1889", "Presidente", "Masculino", "Caiu da cadeira", 3));
        nonAthletes.add(new BDNonAthlete(2, Utils.bitmapToString(BitmapFactory.decodeResource(context.getResources(), R.drawable.franco)), "Francisco Franco", "04/12/1892", "Presidente", "Masculino", "Ficou com ciúmes do Salazar", 3));
        nonAthletes.add(new BDNonAthlete(3, Utils.bitmapToString(BitmapFactory.decodeResource(context.getResources(), R.drawable.roosevelt)), "Franklin Delano Roosevelt", "30/01/1882", "President", "Masculino", "Ficou triste por a guerra estar a acabar e morreu", 2));

        database.getReference().child("nonathletes").setValue(nonAthletes);
    }

}
