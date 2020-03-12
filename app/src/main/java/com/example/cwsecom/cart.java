package com.example.cwsecom;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;

import org.json.JSONArray;
import org.json.JSONException;

import java.math.BigDecimal;
import java.util.ArrayList;

public class cart extends AppCompatActivity  {
private String price,email;
private int totalprice;
Double total=0.00;
private TextView totalpricetextv;
int id;
private Button placeorederbtn,removebtn;
private ArrayList<productcart> arrayList;
private  ArrayAdapter arrayAdapter;
private RecyclerView recyclerView3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        Bundle extras = getIntent().getExtras();
        totalpricetextv=findViewById(R.id.totalpricetextv);
      //  removebtn=findViewById(R.id.removebtn);
        placeorederbtn=findViewById(R.id.placceorderbtn);
       // removebtn.setOnClickListener();
     //   placeorederbtn.setOnClickListener(this);
        arrayList=new ArrayList<>();
        if(price!=null&&email!=null&&id>=0)
        {id=extras.getInt("proid");
            price=extras.getString("amt");
            email=extras.getString("cartemail");
        }
             totalprice=0;
        recyclerView3=findViewById(R.id.recyclerlist3);

     //   Toast.makeText(cart.this,price+" "+email+" ",Toast.LENGTH_SHORT).show();
        String url="http://192.168.43.222/cwsecom/fetchtemporders.php?email="+Person.email;
        RequestQueue queue= Volley.newRequestQueue(cart.this);
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
              for(int i=0;i<response.length();i++)
              {
                  try{
                      arrayList.add(new productcart(response.getJSONObject(i).getInt("productid"),response.getJSONObject(i).getString("email"),response.getJSONObject(i).getString("amount"),response.getJSONObject(i).getString("productname"),response.getJSONObject(i).getString("image")));
                     totalprice=totalprice+Integer.parseInt(response.getJSONObject(i).getString("amount"));
                     total=total+Double.parseDouble(response.getJSONObject(i).getString("amount"));
                  }
                  catch (JSONException e)
                  {

                  }
              }
              totalpricetextv.setText(Integer.toString(totalprice));

                productadapter3 productadapter3=new productadapter3(cart.this,arrayList);
                recyclerView3.setLayoutManager(new LinearLayoutManager(cart.this));
                recyclerView3.setAdapter(productadapter3);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(jsonArrayRequest);
        total=total/72;
        final PayPalConfiguration paypalConfig=new PayPalConfiguration();
        paypalConfig.environment(PayPalConfiguration.ENVIRONMENT_SANDBOX).clientId("AZJ4a1Ml3arSSD4xXyhAOWwLzfuLc4_moyxokXvKryo8eLae83PDTvDGuHPrkyhjGInEbM5KfSNTygku");
        Intent payPalService=new Intent(cart.this,PayPalService.class);
        payPalService.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION,paypalConfig);
        startService(payPalService);
        placeorederbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PayPalPayment payPalPayment=new PayPalPayment(BigDecimal.valueOf(total/70),"USD","CWS Ecommerce",PayPalPayment.PAYMENT_INTENT_SALE);
                Intent paypalpaymentintent=new Intent(cart.this, PaymentActivity.class);
                paypalpaymentintent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION,paypalConfig);
                paypalpaymentintent.putExtra(PaymentActivity.EXTRA_PAYMENT,payPalPayment);
                startActivityForResult(paypalpaymentintent,1000);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1000)
        {
            if(resultCode== Activity.RESULT_OK)
            {
                Intent intent=new Intent(cart.this,endactivity.class);
                startActivity(intent);
            }
        }
    }

    @Override
    protected void onDestroy() {
        Intent intent=new Intent( cart.this,PayPalService.class);
        stopService(intent);
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.cartmenu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId()==R.id.signout)
        {
            Intent intent=new Intent(cart.this,loginActivity.class);
            startActivity(intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
