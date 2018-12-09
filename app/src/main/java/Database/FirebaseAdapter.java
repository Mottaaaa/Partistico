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
}
