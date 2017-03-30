package com.example.akshat.loginpage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Akshat on 2/12/2017.
 */

public class DBAdapter extends SQLiteOpenHelper{
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME="MY CLASS";
    private static final String TABLE_CONTACTS="tablename";

    private Context context;
    private static final String KEY_ID = "id";
    private static final String PASSWORD = "pass";
    private static final String EMAIL_ID = "email_id";
    private static final String SES = "ses";

    public DBAdapter(Context context) {
        super(context, DATABASE_NAME,null,DATABASE_VERSION);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase arg0) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + PASSWORD + " TEXT,"
                + EMAIL_ID + " TEXT," + SES + "INTEGER"+")";
        arg0.execSQL(CREATE_CONTACTS_TABLE);
    }



   @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
        onCreate(sqLiteDatabase);
    }

   /* @Override*/
/*    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }*/
    private void SES_C(SQLiteDatabase arg0){
        String SES_CHECK="UPDATE "+TABLE_CONTACTS+" SET ses = 0 where ses = 1";
        arg0.execSQL(SES_CHECK);
    }

    public void insert123(String pass, String email_Id) {
        // TODO Auto-generated method stub
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        SES_C(getWritableDatabase());
        values.put(PASSWORD, pass);
        values.put(EMAIL_ID, email_Id);
        values.put(SES,1);
        db.insert(TABLE_CONTACTS, null, values);
        db.close();
    }

    public Cursor getInsertedData()
    {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor x= db.query(TABLE_CONTACTS, new String[] {KEY_ID,PASSWORD,EMAIL_ID}, null, null, null, null, null);
        return x;
        //	return db.query(table, columns, selection, selectionArgs, groupBy, having, orderBy)
    }

    public void clearData() {
        // TODO Auto-generated method stub
        context.deleteDatabase(DATABASE_NAME);
    }

}
