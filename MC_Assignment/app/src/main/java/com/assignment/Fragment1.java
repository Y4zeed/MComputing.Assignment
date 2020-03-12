package com.assignment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import com.assignment.Adapter.ImageAdapter;
import com.assignment.Model.IModel;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Fragment1 extends Fragment {

    private RecyclerView recyclerView;
    View root;


   // private List<ImageModel> allList = new ArrayList<>();
    private List<IModel> allList = new ArrayList<>();
    private ImageAdapter adapter;

    private RequestQueue queue;
    String url ="https://api.myjson.com/bins/agnrs";

    public Fragment1() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment1, container, false);

        queue = Volley.newRequestQueue(getContext());
        //INITIALIZATION WITH UI
        initUI();

        return root;
    }

    private void initUI() {
        recyclerView = root.findViewById(R.id.imageRecyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));

        loadData();
    }

    private void loadData() {
        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("Category");

                    for (int i=0; i<jsonArray.length(); i++) {
                        JSONObject object = jsonArray.getJSONObject(i);

                        IModel model = new IModel(
                                object.getString("image"),
                                object.getString("name")
                        );

                        allList.add(model);
                    }

                    adapter = new ImageAdapter(getContext(),allList);
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        queue.add(request);

    }

}
