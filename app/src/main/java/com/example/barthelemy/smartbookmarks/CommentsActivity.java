package com.example.barthelemy.smartbookmarks;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.barthelemy.smartbookmarks.Adapters.CommentAdapter;
import com.example.barthelemy.smartbookmarks.Adapters.LivreAdapter;
import com.example.barthelemy.smartbookmarks.Models.Comment;
import com.example.barthelemy.smartbookmarks.Models.Livre;

public class CommentsActivity extends AppCompatActivity {

    ListView lv;
    Button btnAccueil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);

        lv = (ListView)findViewById(R.id.lvComm);
        btnAccueil = (Button)findViewById(R.id.btnHome);

        SmartBookmarksDb maDb = new SmartBookmarksDb(this);
        SQLiteDatabase db = maDb.getWritableDatabase();
        CommentAdapter ca = new CommentAdapter(db, CommentsActivity.this);

        ArrayAdapter<Comment> adapter = new ArrayAdapter<Comment>(this,
                android.R.layout.simple_list_item_1, ca.getAllComments());
        lv.setAdapter(ca);

        btnAccueil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CommentsActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
    }
}
