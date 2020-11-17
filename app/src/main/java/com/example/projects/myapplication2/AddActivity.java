package com.example.projects.myapplication2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.android.volley.Request.*;

public class AddActivity extends AppCompatActivity {
EditText title, imageUrl;
Button add;
RequestQueue queue;
String url = "https://gaming-panda.df.r.appspot.com/intern_test";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        add = findViewById(R.id.add);
        title = findViewById(R.id.title);
        imageUrl = findViewById(R.id.imageUrl);

        queue = Volley.newRequestQueue(this);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ti = title.getText().toString();
                String im = imageUrl.getText().toString();
                addData(ti,im);
            }
        });
    }

    private void addData(String title, String image) {

        JSONArray it = new JSONArray();
        for(int i = 0; i<it.length(); i++){
            JSONObject a = new JSONObject();
            try {
                a.put("title",title);
                a.put("img_src",image);
            } catch(JSONException e) {
                e.printStackTrace();
            }
            it.put(a);
        }

        JSONArray res = new JSONArray();
        for(int i = 0; i<res.length(); i++){
            JSONObject bb = new JSONObject();
            try {
                bb.put("type","type1");
                bb.put("title","category1");
                bb.put("items",it);
            } catch(JSONException e) {
                e.printStackTrace();
            }
            res.put(bb);
        }

        JSONObject base = new JSONObject();
        try {
            base.put("response",res);
        } catch(JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Method.POST,
                "https://gaming-panda.df.r.appspot.com/intern_test", base, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Toast.makeText(AddActivity.this, "Data saved successfully.", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(AddActivity.this, "Error saving data.", Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(jsonObjectRequest);
    }
}