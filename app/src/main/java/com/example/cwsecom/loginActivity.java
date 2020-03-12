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


public class loginActivity extends AppCompatActivity implements View.OnClickListener {
private EditText loginpageeditemail,loginpageeditpassword;
private Button loginpageloginbtn,simp;
    private TextView forgotpasstextv,signuptextv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginpageeditemail=findViewById(R.id.loginpageeditemail);
        loginpageeditpassword=findViewById(R.id.loginpageeditpassword);
        forgotpasstextv=findViewById(R.id.forgotpasstextview);
        signuptextv=findViewById(R.id.newsignuptextview);
        loginpageloginbtn=findViewById(R.id.loginpageloginbtn);
        loginpageloginbtn.setOnClickListener(this);
        forgotpasstextv.setOnClickListener(this);
        signuptextv.setOnClickListener(this);
       // simp=findViewById(R.id.simp);
        //simp.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.loginpageloginbtn:
                if(loginpageeditemail.getText().toString().equals("")||loginpageeditpassword.getText().toString().equals(""))
            {
                Toast.makeText(loginActivity.this,"Please fill everything",Toast.LENGTH_SHORT).show();
            }
                else
                {
                    String str="http://192.168.43.222//cwsecom/login.php?email="+loginpageeditemail.getText().toString()+"&"+"password="+loginpageeditpassword.getText().toString();
                    RequestQueue queue= Volley.newRequestQueue(loginActivity.this);
                    StringRequest stringRequest=new StringRequest(Request.Method.GET, str, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                             if(response.equals("User does not exist"))
                             {
                                 Toast.makeText(loginActivity.this,"Plese enter the correct emaill and password",Toast.LENGTH_SHORT).show();
                             }
                             else
                             {
                                 Toast.makeText(loginActivity.this,"Login is Succesfull",Toast.LENGTH_SHORT).show();
                                 Intent intent=new Intent(loginActivity.this,frontproductspage.class);
                                 Person.email=loginpageeditemail.getText().toString();
                                // person.setEmail(loginpageeditemail.getText().toString());
                                 startActivity(intent);
                                 finish();
                             }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(loginActivity.this,error.getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    });
                    queue.add(stringRequest);
                }
                break;
            case R.id.forgotpasstextview:
                Toast.makeText(loginActivity.this,"forgot password clicked",Toast.LENGTH_SHORT).show();
                break;
            case R.id.newsignuptextview:
               // Toast.makeText(loginActivity.this,"Login Succesfull",Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(loginActivity.this,signpag.class);
                startActivity(intent);
                finish();
                break;


        }
    }
}
