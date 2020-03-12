package com.example.cwsecom;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

public class productdetails extends AppCompatActivity {
private TextView productnametextv,brandtextv,pricetextv;
private ImageView imagetextv ;
private String  productname,brand,price,image;
private Button prodetailsbtn,buynowbtn,addtocartbtn;
private int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productdetails2);
        Bundle extras = getIntent().getExtras();
        productname=extras.getString("productname");
        brand=extras.getString("brand");
        price=extras.getString("price");
        image=extras.getString("image");
        id=extras.getInt("id");
        prodetailsbtn=findViewById(R.id.productdetailsbtn);
          buynowbtn=findViewById(R.id.buynowbtn);
          addtocartbtn=findViewById(R.id.addtocartbtn);
        productnametextv=findViewById(R.id.prodetproductname);
        brandtextv=findViewById(R.id.prodetbrand);
        pricetextv=findViewById(R.id.prodetprice);
        imagetextv=findViewById(R.id.image2);
        productnametextv.setText(productname);
        brandtextv.setText(brand);
        pricetextv.setText(changepricelayout(price));

        Glide.with(productdetails.this).asBitmap().load(image).into(imagetextv);
       prodetailsbtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent=new Intent(productdetails.this,tabbedact.class);
               intent.putExtra("productid",id);

               startActivity(intent);

           }
       });
       addtocartbtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
                     String url="http://192.168.43.222/cwsecom/placetemporder.php?"+"email="+Person.email+"&productid="+id+"&"+"amount="+price+"&"+"productname="+productname+"&"+"image="+image;
               RequestQueue queue= Volley.newRequestQueue(productdetails.this);
              // Toast.makeText(productdetails.this,Integer.toString(id),Toast.LENGTH_SHORT).show();
               StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                   @Override
                   public void onResponse(String response) {

                   }
               }, new Response.ErrorListener() {
                   @Override
                   public void onErrorResponse(VolleyError error) {

                   }
               });
               queue.add(stringRequest);
               Intent intent=new Intent(productdetails.this,cart.class);
           //    Person person=new Person();
               intent.putExtra("cartemail",Person.email);
               intent.putExtra("amt",price);
               intent.putExtra("proid",id);
               Toast.makeText(productdetails.this,"Added to cart",Toast.LENGTH_SHORT).show();
           }
       });
       buynowbtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               String url="http://192.168.43.222/cwsecom/placetemporder.php?"+"email="+Person.email+"&productid="+id+"&"+"amount="+price+"&"+"productname="+productname+"&"+"image="+image;
               RequestQueue queue= Volley.newRequestQueue(productdetails.this);
               StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                   @Override
                   public void onResponse(String response) {

                   }
               }, new Response.ErrorListener() {
                   @Override
                   public void onErrorResponse(VolleyError error) {

                   }
               });
               queue.add(stringRequest);
               Intent intent=new Intent(productdetails.this,cart.class);
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
        if(item.getItemId()==R.id.mycart)
        {
            Intent intent=new Intent(productdetails.this,cart.class);
            startActivity(intent);
        }
        if(item.getItemId()==R.id.shopbybrand)
        {
            Intent intent=new Intent(productdetails.this,productspage.class);
            startActivity(intent);
        }
        if(item.getItemId()==R.id.signout)
        {
            Intent intent=new Intent(productdetails.this,loginActivity.class);
            startActivity(intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public String changepricelayout(String price2)
    {
        String s="";
      int c=0;
      if(price2.length()<=4) {
          for(int i=0;i<price2.length();i++)
          {c++;
              if(c==2)
                  s=s+","+price2.charAt(i);
              else
                  s=s+price2.charAt(i);
          }
      }
      else
      {
          for(int i=0;i<price2.length();i++)
          {c++;
              if(c==3)
                  s=s+","+price2.charAt(i);
              else
                  s=s+price2.charAt(i);
          }

      }
     return s;
    }
}
