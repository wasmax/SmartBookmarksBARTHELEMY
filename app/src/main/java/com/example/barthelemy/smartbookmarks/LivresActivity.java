package com.example.barthelemy.smartbookmarks;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.barthelemy.smartbookmarks.Adapters.LivreAdapter;
import com.example.barthelemy.smartbookmarks.Models.Livre;

public class LivresActivity extends AppCompatActivity {

    ListView lv;
    Button btnAccueil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_livres);

        lv = (ListView)findViewById(R.id.lvLivres);
        btnAccueil = (Button)findViewById(R.id.btnHome);

        btnAccueil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LivresActivity.this, MainActivity.class);
                startActivity(i);
            }
        });

        SmartBookmarksDb maDb = new SmartBookmarksDb(this);
        SQLiteDatabase db = maDb.getWritableDatabase();
        LivreAdapter la = new LivreAdapter(db, LivresActivity.this);

        ArrayAdapter<Livre> adapter = new ArrayAdapter<Livre>(this,
                android.R.layout.simple_list_item_1, la.getAllLivres());
        lv.setAdapter(la);
    }
}
