package com.example.cwsecom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.lang.ref.ReferenceQueue;

public class signpag extends AppCompatActivity implements View.OnClickListener {
private EditText signupeditname,signupeditemail,signupeditphone,signupeditpassword;
private Button signuppagebtn;
private TextView logintextv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signpag);
        signupeditname=findViewById(R.id.signupeditname);
        signupeditemail=findViewById(R.id.signupeditemail);
        signupeditphone=findViewById(R.id.signupeditphone);
        signupeditpassword=findViewById(R.id.signupeditpassword);
        signuppagebtn=findViewById(R.id.signuppagebtn);
        signuppagebtn.setOnClickListener(this);
        logintextv=findViewById(R.id.loginclicktextview);
logintextv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId())
        {
            case R.id.signuppagebtn:

            if(signupeditname.getText().toString().equals("")||signupeditpassword.getText().toString().equals("")||signupeditemail.getText().toString().equals("")||signupeditphone.getText().toString().equals(""))
            {
                Toast.makeText(signpag.this,"Please fill everything",Toast.LENGTH_SHORT).show();
            }
            else
            {
             String str="http://192.168.43.222//cwsecom/cwsecomtable.php?name="+signupeditname.getText().toString()+"&"+"email="+signupeditemail.getText().toString()+"&"+"phone="+signupeditphone.getText().toString()+"&"+"password="+signupeditpassword.getText().toString();
             RequestQueue queue=Volley.newRequestQueue(signpag.this);
                StringRequest stringRequest=new StringRequest(Request.Method.GET, str, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                       if(response.equals("Email already exist"))
                       {
                           Toast.makeText(signpag.this,"Email id already exist",Toast.LENGTH_SHORT).show();
                       }
                       else
                       {
                           Toast.makeText(signpag.this,response,Toast.LENGTH_SHORT).show();
                       }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(signpag.this,error.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });
                queue.add(stringRequest);


            }
            break;
            case R.id.loginclicktextview:
                Intent intent=new Intent(signpag.this,loginActivity.class);
                startActivity(intent);
                finish();
                break;
              //  Toast.makeText(signpag.this,"Please fill everything",Toast.LENGTH_SHORT).show();

        }

    }
}
