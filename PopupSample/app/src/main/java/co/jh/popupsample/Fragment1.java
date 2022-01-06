package co.jh.popupsample;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import co.jh.popupsample.databinding.Fragment1Binding;

public class Fragment1 extends Fragment {

    private Fragment1Binding binding;
    private ArrayList<Number> numberList;
    private RecyclerView recyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = Fragment1Binding.inflate(inflater, container, false);
        binding.bannerView.setVisibility(View.GONE);
        recyclerView = binding.recyclerView;
        numberList = new ArrayList<>();
        setNumberList();
        setAdapter();

        binding.showPopup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), Pop.class));
            }
        });

        binding.closeBannerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.bannerView.setVisibility(View.GONE);
                SharedPreferences pref = getContext().getSharedPreferences("my_settings", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();
                editor.remove("stringValue");
                editor.commit();
            }
        });
        // Inflate the layout for this fragment
        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        SharedPreferences pref = getContext().getSharedPreferences("my_settings", Context.MODE_PRIVATE);
        String stringValue = pref.getString("stringValue", "");
            Log.d("dddd","2222" + stringValue);
            if (stringValue != "") {
                binding.bannerView.setVisibility(View.VISIBLE);
            }
    }

    private void setAdapter() {
        recyclerAdapter adapter = new recyclerAdapter(numberList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    private void setNumberList() {
        for (int i = 0; i < 100; i++) {
            String value = String.valueOf(i);
            numberList.add(new Number(value));
        }
    }
}