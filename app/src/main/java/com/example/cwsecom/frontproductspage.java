package com.example.cwsecom;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class frontproductspage extends AppCompatActivity {
private ArrayList<product> productlist;
private RecyclerView recyclerView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frontproductspage);
         productlist=new ArrayList<>();
         recyclerView2=findViewById(R.id.recyclerlist2);
         String url="http://192.168.43.222/cwsecom/fetchallproducts.php";
        RequestQueue queue= Volley.newRequestQueue(frontproductspage.this);
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                   for(int i=0;i<response.length();i++)
                   {
                       try{
                           productlist.add(new product(response.getJSONObject(i).getInt("id"),response.getJSONObject(i).getString("productname"),response.getJSONObject(i).getString("brand"),response.getJSONObject(i).getString("price"),response.getJSONObject(i).getString("image")));
                       }
                       catch (JSONException e)
                       {
                           e.printStackTrace();
                       }
                   }

                productadapter2 productadapter2=new productadapter2(frontproductspage.this,productlist);
                recyclerView2.setLayoutManager(new LinearLayoutManager(frontproductspage.this));
                recyclerView2.setAdapter(productadapter2);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(jsonArrayRequest);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.cartmenu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.mycart)
        {
            Intent intent=new Intent(frontproductspage.this,cart.class);
            startActivity(intent);
        }
        if(item.getItemId()==R.id.shopbybrand)
        {
            Intent intent=new Intent(frontproductspage.this,productspage.class);
            startActivity(intent);
        }
        if(item.getItemId()==R.id.signout)
        {
            Intent intent=new Intent(frontproductspage.this,loginActivity.class);

            startActivity(intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
