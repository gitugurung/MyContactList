package com.gita.mycontactlist;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class DetailActivity extends AppCompatActivity {
    TextView name ;
    TextView phone;
    TextView email;
    TextView street;
    TextView add;
    DatabaseHelper db;
    int id_To_Update = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

            name = (TextView) findViewById(R.id.editTextName);
            phone = (TextView) findViewById(R.id.editTextPhone);
            email = (TextView) findViewById(R.id.editTextStreet);
            street = (TextView) findViewById(R.id.editTextEmail);
            add = (TextView) findViewById(R.id.editTextCity);

            db = new DatabaseHelper(this);

            Bundle extras = getIntent().getExtras();
            if(extras !=null) {
                int Value = extras.getInt("id");

                if(Value>0){
                    //means this is the view part not the add contact part.
                    Cursor rs = db.getData(Value);
                    id_To_Update = Value;
                    rs.moveToFirst();

                    String nam = rs.getString(rs.getColumnIndex(DatabaseHelper.CONTACTS_COLUMN_NAME));
                    String phon = rs.getString(rs.getColumnIndex(DatabaseHelper.CONTACTS_COLUMN_PHONE));
                   String emai = rs.getString(rs.getColumnIndex(DatabaseHelper.CONTACTS_COLUMN_EMAIL));
                    String stree = rs.getString(rs.getColumnIndex(DatabaseHelper.CONTACTS_COLUMN_STREET));
                    String address = rs.getString(rs.getColumnIndex(DatabaseHelper.CONTACTS_COLUMN_CITY));

                    if (!rs.isClosed())  {
                        rs.close();
                    }
                    Button b = (Button)findViewById(R.id.button1);
                    b.setVisibility(View.INVISIBLE);

                  /*  name.setText((CharSequence)nam);
                    name.setFocusable(false);
                    name.setClickable(false);

                    phone.setText((CharSequence)phon);
                    phone.setFocusable(false);
                    phone.setClickable(false);

                   email.setText((CharSequence)emai);
                    email.setFocusable(false);
                    email.setClickable(false);

                    street.setText((CharSequence)stree);
                    street.setFocusable(false);
                    street.setClickable(false);

                    add.setText((CharSequence)address);
                    add.setFocusable(false);
                    add.setClickable(false);*/
                }
            }
        }
        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            // Inflate the menu; this adds items to the action bar if it is present.
            Bundle extras = getIntent().getExtras();

            if(extras !=null) {
                int Value = extras.getInt("id");
                if(Value>0){
                    getMenuInflater().inflate(R.menu.menu1, menu);
                } else{
                    getMenuInflater().inflate(R.menu.menu2, menu);
                }
            }
            return true;
        }

    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch(item.getItemId()) {
            case R.id.Edit_Contact:
                /*Button b = (Button)findViewById(R.id.button1);
                b.setVisibility(View.VISIBLE);
                name.setEnabled(true);
                name.setFocusableInTouchMode(true);
                name.setClickable(true);

                phone.setEnabled(true);
                phone.setFocusableInTouchMode(true);
                phone.setClickable(true);

                email.setEnabled(true);
                email.setFocusableInTouchMode(true);
                email.setClickable(true);

                street.setEnabled(true);
                street.setFocusableInTouchMode(true);
                street.setClickable(true);

                add.setEnabled(true);
                add.setFocusableInTouchMode(true);
                add.setClickable(true);*/

                return true;
            case R.id.Delete_Contact:

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("dlete contact")
                        .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                db.deleteContact(id_To_Update);
                                Toast.makeText(getApplicationContext(), "Deleted Successfully",
                                        Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // User cancelled the dialog
                            }
                        });

                AlertDialog d = builder.create();
                d.setTitle("Are you sure");
                d.show();

                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }

    public void run(View view) {
        Bundle extras = getIntent().getExtras();
        if(extras !=null) {
            int Value = extras.getInt("id");
            if(Value>0){
                if(db.updateContact(id_To_Update,name.getText().toString(),
                        phone.getText().toString(),
                         add.getText().toString())){
                    Toast.makeText(getApplicationContext(), "Updated", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);
                } else{
                    Toast.makeText(getApplicationContext(), "not Updated", Toast.LENGTH_SHORT).show();
                }
            } else {
                if(db.insertContact(name.getText().toString(), phone.getText().toString(),

                        add.getText().toString())){
                    Toast.makeText(getApplicationContext(), "done",
                            Toast.LENGTH_SHORT).show();
                } else{
                    Toast.makeText(getApplicationContext(), "not done",
                            Toast.LENGTH_SHORT).show();
                }
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        }

    }
}
