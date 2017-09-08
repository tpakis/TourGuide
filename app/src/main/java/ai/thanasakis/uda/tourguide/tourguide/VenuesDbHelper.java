package ai.thanasakis.uda.tourguide.tourguide;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class VenuesDbHelper extends SQLiteAssetHelper {
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "mydb.db";
    // Table name
    private static final String TABLE_VENUES = "PlacesTable";
    public SQLiteDatabase myDataBase;
    private Context mycontext;
    private String DB_PATH;

    public VenuesDbHelper(Context context) throws IOException {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        checkdatabase();
        this.mycontext = context;
    }

    public ArrayList<Venue> getVenuesFromCategory(String category, String language) {

        ArrayList<Venue> venusList = new ArrayList<Venue>();
        String selectQuery;
        if ((language.equals("el_GR")) || (language.equals("el"))) {
            selectQuery = "SELECT _id, name_el, info, tel, link, longtitude, latitude FROM " + TABLE_VENUES + " WHERE genre = '" + category + "'";
        } else {
            selectQuery = "SELECT _id, name_en, info_en, tel, link, longtitude, latitude FROM " + TABLE_VENUES + " WHERE genre = '" + category + "'";
        }

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        cursor.moveToFirst();
        int drawableResourceId = 0;
        String drawableStringtemp;
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Venue venue = new Venue();
                venue.setVenueID(cursor.getInt(0));
                venue.setVenueName(cursor.getString(1));
                venue.setVenueDescription(cursor.getString(2).trim());
                venue.setVenueCategory(category);
                venue.setVenuePhone(cursor.getString(3));
                venue.setVenueSite(cursor.getString(4));
                venue.setVenueLongtitude(cursor.getDouble(5));
                venue.setVenueLatitude(cursor.getDouble(6));
                drawableStringtemp = "a" + String.valueOf(cursor.getInt(0));
                drawableResourceId = mycontext.getApplicationContext().getResources().getIdentifier(drawableStringtemp, "drawable", mycontext.getApplicationContext().getPackageName());
                if (drawableResourceId != 0) {
                    venue.setImageResourceId(drawableResourceId);
                }
                venusList.add(venue);

            } while (cursor.moveToNext());
        }
        // return quest list
        return venusList;
    }

    private boolean checkdatabase() {
        boolean checkdb = false;
        try {
            String myPath = DB_PATH + DATABASE_NAME;
            File dbfile = new File(myPath);
            checkdb = dbfile.exists();
        } catch (SQLiteException e) {
            System.out.println("Database doesn't exist");
        }
        return checkdb;
    }

    public synchronized void close() {
        if (myDataBase != null) {
            myDataBase.close();
        }
        super.close();
    }
}
