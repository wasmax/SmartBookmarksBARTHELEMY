package com.example.barthelemy.smartbookmarks.Adapters;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.barthelemy.smartbookmarks.Models.Comment;
import com.example.barthelemy.smartbookmarks.Models.Livre;
import com.example.barthelemy.smartbookmarks.R;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Max on 20/09/2017.
 */

public class CommentAdapter extends BaseAdapter {

    public static final String TABLE = "comments";
    public SQLiteDatabase db;
    ArrayList<Comment> list;
    Context context;

    public CommentAdapter(SQLiteDatabase db) {
        this.db = db;
    }

    public CommentAdapter(SQLiteDatabase db, Context context) {
        this.db = db;
        this.list = getAllComments();
        this.context = context;
    }

    public ArrayList<Comment> getAllComments(){

        Cursor c = db.query("comments", new String[]{"id", "comment", "bookid", "date"}, //table
                null,
                null, //parametre de selection
                null, //order
                null, //group
                null); //limit

        ArrayList<Comment> comments = new ArrayList<>();
        LivreAdapter la = new LivreAdapter(db, context);

        while (c.moveToNext()) {
            Date date = new Date(c.getLong(3)*1000);
            Comment comm = new Comment(c.getInt(0), c.getString(1), la.getLivreById(c.getInt(0)), date);
            comments.add(comm);
        }

        c.close();

        return comments;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(context).
                    inflate(R.layout.layout_list_view_livres_items, viewGroup, false);
        }

        Comment current = list.get(i);

        TextView textViewItemName = (TextView)
                view.findViewById(R.id.text_view_item_title);
        TextView textViewItemAuthor = (TextView)
                view.findViewById(R.id.text_view_item_author);
        TextView textViewItemGenre = (TextView)
                view.findViewById(R.id.text_view_item_genre);

        String dataTitle = current.getLivre().getTitle().toString();
        String dataAuth = current.getComment();
        String dataGenre = current.getDate().toString();

        textViewItemName.setText(dataTitle);
        textViewItemAuthor.setText(dataAuth);
        textViewItemGenre.setText(dataGenre);

        return view;
    }
}
