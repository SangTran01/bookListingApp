package com.example.tony_.booklisting;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class BookAdapter extends ArrayAdapter<Book> {

    public BookAdapter(@NonNull Context context, ArrayList<Book> books) {
        super(context, 0, books);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listViewItem = convertView;

        if (listViewItem == null) {
            listViewItem = LayoutInflater.from(getContext())
                    .inflate(R.layout.book_list_item, parent, false);
        }

        TextView titleTextView = listViewItem.findViewById(R.id.book_title);
        TextView authorTextView = listViewItem.findViewById(R.id.book_author);
        TextView yearTextView = listViewItem.findViewById(R.id.book_year);
        ImageView cover = listViewItem.findViewById(R.id.book_cover);

        Book currentBook = getItem(position);

        titleTextView.setText(currentBook.getmTitle());

        ArrayList<String> authors = currentBook.getmAuthors();

        if (authors.size() > 1) {
            authorTextView.setText(authors.get(0) + " & others.");
        }
        else if (authors.size() == 1) {
            authorTextView.setText(authors.get(0) + ".");
        } else if (authors.isEmpty()){
            authorTextView.setText("Unknown authors.");
        }
        yearTextView.setText(currentBook.getmYear() + ".");
        cover.setImageBitmap(currentBook.getmCoverUrl());


        return listViewItem;
    }
}
