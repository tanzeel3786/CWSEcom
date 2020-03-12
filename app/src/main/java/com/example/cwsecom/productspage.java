package com.example.cwsecom;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class productspage extends AppCompatActivity {
private String url;
private ArrayList<String> brandlist;
private ListView brandlistview;
private ArrayAdapter arrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productspage);
        url="http://192.168.43.222//cwsecom/prodtable.php";
        brandlistview=findViewById(R.id.brandlistview);
        brandlist=new ArrayList<>();

        RequestQueue queue= Volley.newRequestQueue(productspage.this);
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                 for(int i=0;i<response.length();i++)
                 {
                     try{
                         brandlist.add(response.getJSONObject(i).getString("brand"));
                     }
                     catch (JSONException e)
                     {
                         e.printStackTrace();
                     }
                 }
                arrayAdapter=new ArrayAdapter(productspage.this,R.layout.myownlist,brandlist);
                brandlistview.setAdapter(arrayAdapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(jsonArrayRequest);
        brandlistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String tappedbrand=brandlist.get(position);
                Intent intent=new Intent(productspage.this,nameproductpage.class);
                intent.putExtra("BRAND",tappedbrand);
             //   Toast.makeText(productspage.this,tappedbrand,Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.cartmenu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId()==R.id.signout)
        {
            Intent intent=new Intent(productspage.this,loginActivity.class);
            startActivity(intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
