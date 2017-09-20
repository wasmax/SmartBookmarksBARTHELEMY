package com.example.barthelemy.smartbookmarks;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.barthelemy.smartbookmarks.Adapters.LivreAdapter;
import com.example.barthelemy.smartbookmarks.Models.Livre;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.TimeZone;

import static java.lang.System.in;

public class AddNoteActivity extends AppCompatActivity {

    EditText et;
    Spinner spn;
    Button btnAdd;
    Button btnAccueil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        et = (EditText)findViewById(R.id.txtAvis);
        spn = (Spinner)findViewById(R.id.spLivres);
        btnAdd = (Button)findViewById(R.id.btnAdd);
        btnAccueil = (Button)findViewById(R.id.btnHome);

        SmartBookmarksDb maDb = new SmartBookmarksDb(this);
        final SQLiteDatabase db = maDb.getWritableDatabase();
        final LivreAdapter la = new LivreAdapter(db, AddNoteActivity.this);

        ArrayList<String> allTitresLivres = new ArrayList<String>();

        for(Livre livres : la.getAllLivres()){
            allTitresLivres.add(livres.getTitle());
        }

        ArrayAdapter<String> arrayadapter =
                new ArrayAdapter<String>(AddNoteActivity.this, android.R.layout.simple_spinner_dropdown_item, allTitresLivres);
        spn.setAdapter(arrayadapter);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(et.getText().length() == 0){
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(AddNoteActivity.this);
                    builder1.setMessage("Vous devez rentrer un commentaire.");
                    builder1.setCancelable(true);

                    builder1.setPositiveButton(
                            "Ok",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });

                    AlertDialog alert11 = builder1.create();
                    alert11.show();
                }
                else{
                    Integer idType = la.getLivreByName(spn.getSelectedItem().toString()).getId();
                    String insert = "INSERT INTO Comments ('bookId', 'comment', 'date') VALUES ('" + idType + "', '" + et.getText() + "', 'datetime()')";
                    db.execSQL(insert);

                    AlertDialog.Builder builder1 = new AlertDialog.Builder(AddNoteActivity.this);
                    builder1.setMessage("Commentaire ajouté.");
                    builder1.setCancelable(true);

                    builder1.setPositiveButton(
                            "Ok",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });

                    AlertDialog alert11 = builder1.create();
                    alert11.show();
                    et.setText("");
                }
            }
        });

        btnAccueil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AddNoteActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
    }
}
