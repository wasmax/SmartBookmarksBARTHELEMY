package com.example.barthelemy.smartbookmarks.Adapters;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.barthelemy.smartbookmarks.Models.Livre;
import com.example.barthelemy.smartbookmarks.R;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Max on 20/09/2017.
 */

public class LivreAdapter extends BaseAdapter{

    public static final String TABLE = "books";
    public SQLiteDatabase db;
    ArrayList<Livre> list;
    Context context;

    public LivreAdapter(SQLiteDatabase db) {
        this.db = db;
    }

    public LivreAdapter(SQLiteDatabase db, Context context) {
        this.db = db;
        this.list = getAllLivres();
        this.context = context;
    }

    public ArrayList<Livre> getAllLivres(){

        Cursor c = db.query("books", new String[]{"id", "title", "author", "genre"}, //table
                null,
                null, //parametre de selection
                null, //order
                null, //group
                null); //limit

        ArrayList<Livre> livres = new ArrayList<>();

        while (c.moveToNext()) {
            Livre livre = new Livre(c.getInt(0), c.getString(1), c.getString(2), c.getString(3));
            livres.add(livre);
        }

        c.close();

        return livres;
    }

    public Livre getLivreByName(String name){

        Cursor c = db.query("books", new String[]{"id", "title", "author", "genre"}, //table
                "title = ?", //selection (where)
                new String[]{name}, //parametre de selection
                null, //order
                null, //group
                null); //limit

        Livre livre = new Livre();

        while (c.moveToFirst()) {

            int columnName = c.getColumnCount();
            livre.setId(c.getInt(0));
            livre.setTitle(c.getString(1));
            livre.setAuthor(c.getString(2));
            livre.setGenre(c.getString(3));
            break;
        }

        c.close();

        return livre;
    }

    public Livre getLivreById(Integer id){

        Cursor c = db.query("books", new String[]{"id", "title", "author", "genre"}, //table
                "id = ?", //selection (where)
                new String[]{id.toString()}, //parametre de selection
                null, //order
                null, //group
                null); //limit

        Livre livre = new Livre();

        while (c.moveToFirst()) {

            int columnName = c.getColumnCount();
            livre.setId(c.getInt(0));
            livre.setTitle(c.getString(1));
            livre.setAuthor(c.getString(2));
            livre.setGenre(c.getString(3));
            break;
        }

        c.close();

        return livre;
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

        Livre current = list.get(i);

        TextView textViewItemName = (TextView)
                view.findViewById(R.id.text_view_item_title);
        TextView textViewItemAuthor = (TextView)
                view.findViewById(R.id.text_view_item_author);
        TextView textViewItemGenre = (TextView)
                view.findViewById(R.id.text_view_item_genre);

        String dataTitle = current.getTitle();
        String dataAuth = current.getAuthor();
        String dataGenre = current.getGenre();

        textViewItemName.setText(dataTitle);
        textViewItemAuthor.setText(dataAuth);
        textViewItemGenre.setText(dataGenre);

        return view;
    }
}
