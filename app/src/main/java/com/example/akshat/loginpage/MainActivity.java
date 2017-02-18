package com.example.akshat.loginpage;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText e1,e2;
    Button b1,b2,b3;
    String s1,s2,s3,s4;
    DBAdapter db;
    int check=0;
    /*TextView t1;*/
    ImageView i1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        e1=(EditText)findViewById(R.id.editText);
        e2=(EditText)findViewById(R.id.editText2);
        b1=(Button)findViewById(R.id.button);
        b2=(Button)findViewById(R.id.button2);
        b3=(Button)findViewById(R.id.button3);
        i1=(ImageView)findViewById(R.id.imageView);
        db=new DBAdapter(this);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i1.setImageResource(R.drawable.downloadss);
                s1=e1.getText().toString();
                s2=e2.getText().toString();
                check=showDataOnToast();
                e1.setText("");
                e2.setText("");
                if(s1.equals("")||s2.equals(""))
                    Toast.makeText(getBaseContext(),"Username and Password both are mandatory fields" , Toast.LENGTH_LONG).show();
                else if(check==1) {
                    Intent y=new Intent(MainActivity.this,Main2Activity.class);
                    startActivity(y);
                }
                else{
                    i1.setImageResource(R.drawable.download);
                }
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i1.setImageResource(R.drawable.downloadss);
                s3=e1.getText().toString();
                s4=e2.getText().toString();
                check=showDataOnToastcreate();
                if(s3.equals("")||s4.equals(""))
                    Toast.makeText(getBaseContext(),"Username and Password both are mandatory fields" , Toast.LENGTH_LONG).show();
                else if(isValidEmail(s3)==false){
                    Toast.makeText(getBaseContext(),"INVALID EMAIL ID" , Toast.LENGTH_LONG).show();
                }
                else if(check==0) {
                    db.insert123(e1.getText().toString(), e2.getText().toString());
                    Toast.makeText(getBaseContext(), "Data Inserted", Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(getBaseContext(), "Email ID Already exists", Toast.LENGTH_LONG).show();
                }
                e1.setText("");
                e2.setText("");
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent y=new Intent(MainActivity.this,Main3Activity.class);
                startActivity(y);
            }
        });
    }
    public final static boolean isValidEmail(CharSequence target) {
        if (target == null) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }
    protected int showDataOnToastcreate() {
        // TODO Auto-generated method stub
        Cursor c=db.getInsertedData();
        if(c.moveToFirst())
        {
            do
            {
                if(s3.equals(c.getString(1)))
                return 1;
            }while(c.moveToNext());
            return 0;
        }
        db.close();
        return 0;
    }
    protected int showDataOnToast() {
        // TODO Auto-generated method stub
        Cursor c=db.getInsertedData();
        if(c.moveToFirst())
        {
            do
            {
                if(s1.equals(c.getString(1))&&s2.equals(c.getString(2)))
                    return 1;
            }while(c.moveToNext());
            return 0;
        }
        db.close();
        return 0;
    }

}