package com.example.myapplication.lab3;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TestAdapter extends AppCompatActivity {
    private ListView listView;
    private SimpleAdapter adapter;
    private List<Map<String,Object>> list;
    private int[] image = new int[]{R.drawable.cat,R.drawable.dog,
            R.drawable.elephant,R.drawable.lion,R.drawable.monkey,R.drawable.tiger};
    private String[] strings = new String[]{"🐱","🐕","🐘","🦁","🐒","🐅"};

    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.test_adapter);
        listView=(ListView) findViewById(R.id.listView);
        list=new ArrayList<>();

    }


}
