package Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {

            //inserir aqui mais instruções de criação de tabelas
            db.execSQL(BDClub.DATABASE_CREATE);
            db.execSQL(BDAthlete.DATABASE_CREATE);

        } catch
                (Exception er) {
            Log.e("Error", "exception");
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {

            //inserir drop table para cada tabela que quisermos
            db.execSQL("DROP TABLE IF EXISTS " + BDClub.TABLE_CLUB);
            db.execSQL("DROP TABLE IF EXISTS " + BDAthlete.TABLE_ATHLETE);

            onCreate(db);
        }
    }
}