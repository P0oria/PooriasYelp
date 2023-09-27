package com.pooria.pooriasyelp;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class FavoritesFragment extends Fragment {

    private BusinessDatabaseHelper businessDatabaseHelper;
    private ListView listView;
    private ListAdapter listAdapter;
    private ArrayList<Business> favoriteBusinesses;
    private BusinessDatabaseHelper db;

    public static FavoritesFragment newInstance() {
        return new FavoritesFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        businessDatabaseHelper = new BusinessDatabaseHelper(getActivity());
        db = new BusinessDatabaseHelper(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorites, container, false);
        listView = view.findViewById(R.id.favorite_list);
        loadFavorites();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Business selectedBusiness = favoriteBusinesses.get(position);

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

                builder.setTitle("Remove from favorites?");
                builder.setMessage("Do you want to remove this item from favorites?");

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (db.removeFavorite(selectedBusiness)) {
                            Toast.makeText(getActivity(), "Removed from favorites", Toast.LENGTH_SHORT).show();
                            loadFavorites();
                        } else {
                            Toast.makeText(getActivity(), "Failed to remove from favorites", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                builder.setNegativeButton("No", null);

                builder.create().show();
            }
        });

        return view;
    }

    private void loadFavorites() {
        favoriteBusinesses = businessDatabaseHelper.getAllFavorites();
        if (favoriteBusinesses != null && !favoriteBusinesses.isEmpty()) {
            listAdapter = new ListAdapter(getActivity(), favoriteBusinesses);
            listView.setAdapter(listAdapter);
        } else {
            favoriteBusinesses = new ArrayList<>();
            if (listAdapter != null) {
                listAdapter = new ListAdapter(getActivity(), favoriteBusinesses);
                listView.setAdapter(listAdapter);
            }
            Toast.makeText(getActivity(), "No favorite items!", Toast.LENGTH_LONG).show();
        }
    }


}
