package sda.internaldatabasecontacts;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by RENT on 2017-02-22.
 */

public class ContactsDatabaseHelper extends SQLiteOpenHelper {
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_FIRST_NAME = "first_name";
    private static final String COLUMN_SURNAME = "last_surname";
    private static final String COLUMN_PHONE = "phone_number";
    private static final int DB_VERSION = 1;
    //
    private static final String TABLE_NAME = "contacts";
    private static final String DATABASE_NAME = "contacts.db";
    public ContactsDatabaseHelper(Context context) {
        super(context, DATABASE_NAME , null, DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " ( " +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT , " +
                COLUMN_FIRST_NAME + " VARCHAR, " +
                COLUMN_SURNAME + " VARCHAR, " +
                COLUMN_PHONE + "VARCHAR); ");
    }

    public void createContact(Contact contact) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT INTO " + TABLE_NAME +
                        " ( " + COLUMN_ID + ", " +
                COLUMN_FIRST_NAME + ", " +
                COLUMN_SURNAME + ", " +  " + getValuesString(contact));
                db.close();
    }

    private String getValuesString(Contact contact) {
        return "( NULL ," +
                "'" + contact.getFirstName() + "', " +
                "'" + contact.getSurname() + "', " +
                "'" + contact.getPhoneNumber() + "')";
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void updateContact(Contact c) { // update
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(" UPDATE " + TABLE_NAME + " SET " +
                COLUMN_FIRST_NAME + "='" + c.getFirstName() + "', " +
                COLUMN_SURNAME + "='" + c.getSurname() + "', " +
                COLUMN_PHONE + "='" + c.getPhoneNumber() + "' WHERE " +
                COLUMN_ID + "='" + c.getId() + "'");
        db.close();
    }
    public void deleteContact(Contact c) { // delete
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(" DELETE FROM " + TABLE_NAME + " WHERE " + COLUMN_ID + "='" + c.getId() + "'");
        db.close();
    }
}
