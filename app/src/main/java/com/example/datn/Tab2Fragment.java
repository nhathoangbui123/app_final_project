package com.example.datn;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class Tab2Fragment extends Fragment {
    private ArrayList<String> deviceList = new ArrayList<>();
    private ArrayList<String> statusList = new ArrayList<>();

    public Tab2Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        final View view = inflater.inflate(R.layout.fragment_tab2, container, false);

        ListView listView = view.findViewById(R.id.deviceListView);
        final DeviceStatusAdapter deviceBarAdapter = new DeviceStatusAdapter(getContext(), deviceList,  statusList );
        listView.setAdapter(deviceBarAdapter);
        for(int i=0;i<4;i++){
            try{
                deviceList.get(i);
                deviceList.set(i,"Laptop");
            }catch(IndexOutOfBoundsException e){
                deviceList.add("Laptop");
            }

            Log.e("Tab2Fragment", "onDataChange:  Device List" + "Laptop");
            deviceBarAdapter.notifyDataSetChanged();

        }
        for(int i=0;i<4;i++){
            statusList.add(i, "1");
            Log.e("Tab2Fragment", "onDataChange:  Status List" + "1");
            deviceBarAdapter.notifyDataSetChanged();
        }
//        DatabaseReference referenceDeviceListViewBar = FirebaseDatabase.getInstance().getReference().child("DeviceList");
//        DatabaseReference referenceStatusListViewBar = FirebaseDatabase.getInstance().getReference().child("RealTimeStatus");

//        referenceDeviceListViewBar.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
//                    int key = Integer.parseInt(snapshot.getKey().toString().substring(6)) -1 ;
//                    try{
//                        deviceList.get(key);
//                        deviceList.set(key,snapshot.getValue().toString());
//                    }catch(IndexOutOfBoundsException e){
//                        deviceList.add(snapshot.getValue().toString());
//                    }
//
//                    Log.e("Tab1Fragment", "onDataChange:  Device List" + snapshot.getValue().toString());
//                    deviceBarAdapter.notifyDataSetChanged();
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
//
//        referenceStatusListViewBar.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
//                    int position = Integer.parseInt(snapshot.getKey().toString().substring(6)) - 1;
//                    statusList.add(position, snapshot.getValue().toString());
//                    Log.e("Tab1Fragment", "onDataChange:  Status List" + snapshot.getValue().toString());
//                    deviceBarAdapter.notifyDataSetChanged();
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });


        return  view;
    }
}
