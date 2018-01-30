package com.example.daan.gps.Fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Toast;


import com.example.daan.gps.Classes.CustomExpandableListAdapter;
import com.example.daan.gps.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static java.sql.DriverManager.println;

/**
 * A simple {@link Fragment} subclass.
 */
public class Games_Fragment extends Fragment {

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    public Games_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_games, container, false);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        expListView = view.findViewById(R.id.eL);

        prepareListData();

        listAdapter  = new CustomExpandableListAdapter(getActivity(), listDataHeader, listDataChild);

        expListView.setAdapter(listAdapter);

        expListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
                // Toast.makeText(getApplicationContext(),
                // "Group Clicked " + listDataHeader.get(groupPosition),
                // Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        // Listview Group expanded listener
        expListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                Toast.makeText(getActivity().getApplicationContext(),
                        listDataHeader.get(groupPosition) + " Expanded",
                        Toast.LENGTH_SHORT).show();
            }
        });

        // Listview Group collasped listener
        expListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
                Toast.makeText(getActivity().getApplicationContext(),
                        listDataHeader.get(groupPosition) + " Collapsed",
                        Toast.LENGTH_SHORT).show();

            }
        });

        // Listview on child click listener
        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                // TODO Auto-generated method stub
                Toast.makeText(
                        getActivity().getApplicationContext(),
                        listDataHeader.get(groupPosition)
                                + " : "
                                + listDataChild.get(
                                listDataHeader.get(groupPosition)).get(
                                childPosition), Toast.LENGTH_SHORT)
                        .show();

                Fragment fragment = new Game_Info_Fragment();

                if(fragment != null) {
                    getActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fragment_container, fragment)
                            .addToBackStack(null)
                            .commit();
                } 
                return false;
            }
        });

    }


    private void prepareListData(){
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding the Headers
        listDataHeader.add("Amsterdam");
        listDataHeader.add("Utrecht");
        listDataHeader.add("Rotterdam");

        // Adding Children
        List<String> amsterdam = new ArrayList<String>();
        amsterdam.add("amsterdam1");
        amsterdam.add("amsterdam2");
        amsterdam.add("amsterdam3");
        amsterdam.add("amsterdam4");
        amsterdam.add("amsterdam5");

        List<String> utrecht = new ArrayList<String>();
        utrecht.add("utrecht1");
        utrecht.add("utrecht2");
        utrecht.add("utrecht3");
        utrecht.add("utrecht4");
        utrecht.add("utrecht5");

        List<String> rotterdam = new ArrayList<String>();
        rotterdam.add("rotterdam1");
        rotterdam.add("rotterdam2");
        rotterdam.add("rotterdam3");
        rotterdam.add("rotterdam4");
        rotterdam.add("rotterdam5");

        listDataChild.put(listDataHeader.get(0), amsterdam);
        listDataChild.put(listDataHeader.get(1), utrecht);
        listDataChild.put(listDataHeader.get(2), rotterdam);

    }

}
