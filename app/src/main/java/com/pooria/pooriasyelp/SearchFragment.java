package com.pooria.pooriasyelp;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

// Fragment for searching and displaying restaurant results.
public class SearchFragment extends Fragment implements SearchView.OnQueryTextListener {

    SearchView searchView;
    ListView listView;
    Spinner spinner;
    ArrayList<Business> businessArrayList = new ArrayList<>();
    BusinessDatabaseHelper db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_search, container, false);
        listView = v.findViewById(R.id.restaurants_list);
        searchView = v.findViewById(R.id.search_view);
        spinner = v.findViewById(R.id.spinner);
        db = new BusinessDatabaseHelper(getActivity());

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.spinner_items, android.R.layout.simple_spinner_item);

        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selected = parent.getItemAtPosition(position).toString();
                if ("Price".equals(selected)) {

                    Collections.sort(businessArrayList, new Comparator<Business>() {
                        @Override
                        public int compare(Business b1, Business b2) {
                            if (b1.price == null) {
                                return (b2.price == null) ? 0 : -1;
                            }
                            if (b2.price == null) {
                                return 1;
                            }
                            return b1.price.length() - b2.price.length();
                        }
                    });
                } else if ("Rating".equals(selected)) {

                    Collections.sort(businessArrayList, new Comparator<Business>() {
                        @Override
                        public int compare(Business b1, Business b2) {
                            return Float.compare(b2.rating, b1.rating);
                        }
                    });
                }


                ListAdapter listAdapter = new ListAdapter(getActivity(), businessArrayList);
                listView.setAdapter(listAdapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Collections.sort(businessArrayList, new Comparator<Business>() {
                   @Override
                   public int compare(Business b1, Business b2) {
                        return Float.compare(b2.rating, b1.rating);
                   }
               });
            }
        });


        searchView.setOnQueryTextListener(this);

        getRestaurantCall("French");

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Business selectedBusiness = businessArrayList.get(position);

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

                builder.setTitle("Add to favorites?");
                builder.setMessage("Do you want to add this item to favorites?");

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (db.addFavorite(selectedBusiness)) {
                            Toast.makeText(getActivity(), "Added to favorites", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getActivity(), "Failed to add to favorites", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                builder.setNegativeButton("No", null);

                builder.create().show();
            }
        });
        return v;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        getRestaurantCall(query);
        searchView.clearFocus();
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return true;
    }

    public void getRestaurantCall(String term) {

        Call<DataResponse> call = RetrofitClient.getInstance().getApi().getRestaurant(term,
                "Montreal", 30); // You can enter your location here

        call.enqueue(new Callback<DataResponse>() {
            @Override
            public void onResponse(Call<DataResponse> call, Response<DataResponse> response) {
                DataResponse dataResponse = response.body();
                Log.d("Data Received", "" + dataResponse.total);
                businessArrayList = dataResponse.businesses;

                String selected = spinner.getSelectedItem().toString();
                if ("Price".equals(selected)) {

                    Collections.sort(businessArrayList, new Comparator<Business>() {
                        @Override
                        public int compare(Business b1, Business b2) {
                            if (b1.price == null) {
                                return (b2.price == null) ? 0 : 1;
                            }
                            if (b2.price == null) {
                                return -1;
                            }
                            return b2.price.length() - b1.price.length();
                        }
                    });
                } else if ("Rating".equals(selected)) {

                    Collections.sort(businessArrayList, new Comparator<Business>() {
                        @Override
                        public int compare(Business b1, Business b2) {
                            return Float.compare(b2.rating, b1.rating);
                        }
                    });
                }

                ListAdapter listAdapter = new ListAdapter(getActivity(), businessArrayList);
                listView.setAdapter(listAdapter);
            }

            @Override
            public void onFailure(Call<DataResponse> call, Throwable t) {

            }
        });
    }
}
