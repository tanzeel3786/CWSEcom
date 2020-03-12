package com.example.cwsecom;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
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

public class nameproductpage extends AppCompatActivity {
private String extrastring;
private ArrayList<product> productList;
private RecyclerView recyclerView;
private TextView mobiletextv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nameproductpage);
        mobiletextv=findViewById(R.id.mobiletextv);
        Bundle extras = getIntent().getExtras();
        productList=new ArrayList<>();
        extrastring=extras.getString("BRAND");
         recyclerView=(RecyclerView)findViewById(R.id.recyclerlist);
        String url="http://192.168.43.222//cwsecom/fetchproducts.php?brand="+extrastring;
      //Toast.makeText(nameproductpage.this,extrastring,Toast.LENGTH_SHORT).show();
        mobiletextv.setText(extrastring+" mobiles");
        RequestQueue queue= Volley.newRequestQueue(nameproductpage.this);
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for(int i=0;i<response.length();i++)
                {try {


                    productList.add(new product(response.getJSONObject(i).getInt("id"),response.getJSONObject(i).getString("productname"),response.getJSONObject(i).getString("brand"),response.getJSONObject(i).getString("price"),response.getJSONObject(i).getString("image")));

                }




                catch (JSONException e)
                {
               e.printStackTrace();
                }
                }
               // Toast.makeText(nameproductpage.this,productList.get(1).price.toString(),Toast.LENGTH_SHORT).show();



            productadapter productadapter=new productadapter(nameproductpage.this,productList);
        recyclerView.setLayoutManager(new LinearLayoutManager(nameproductpage.this));
        recyclerView.setAdapter(productadapter);
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
            Intent intent=new Intent(nameproductpage.this,cart.class);
            startActivity(intent);
        }
        if(item.getItemId()==R.id.shopbybrand)
        {
            Intent intent=new Intent(nameproductpage.this,productspage.class);
            startActivity(intent);
        }
        if(item.getItemId()==R.id.signout)
        {
            Intent intent=new Intent(nameproductpage.this,loginActivity.class);
            startActivity(intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
