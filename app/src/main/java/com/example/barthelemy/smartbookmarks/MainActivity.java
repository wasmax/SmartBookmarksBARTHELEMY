package com.example.barthelemy.smartbookmarks;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.barthelemy.smartbookmarks.Adapters.LivreAdapter;

public class MainActivity extends AppCompatActivity {

    Button livresLnk;
    Button addNote;
    Button noteLink;
    TextView tvLivres;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        livresLnk = (Button)findViewById(R.id.lstLivres);
        tvLivres = (TextView)findViewById(R.id.tvNbLivres);
        addNote = (Button)findViewById(R.id.btnAddNote);
        noteLink = (Button)findViewById(R.id.lstNotes);

        SmartBookmarksDb maDb = new SmartBookmarksDb(this);
        SQLiteDatabase db = maDb.getWritableDatabase();
        LivreAdapter la = new LivreAdapter(db, MainActivity.this);

        livresLnk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, LivresActivity.class);
                startActivity(i);
            }
        });

        noteLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, CommentsActivity.class);
                startActivity(i);
            }
        });

        addNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, AddNoteActivity.class);
                startActivity(i);
            }
        });

        tvLivres.setText(String.valueOf(la.getCount()));
    }
}
