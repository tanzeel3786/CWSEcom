package com.example.cwsecom;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

class productadapter3 extends RecyclerView.Adapter<productadapter3.Viewholder> {
    private LinearLayout productrowlayout;

    private Context context;
    private ArrayList<productcart> arrayList;

    productadapter3(Context context, ArrayList<productcart> arrayList) {
        this.context = context;
        this.arrayList = arrayList;

    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cartrowlist, parent, false);
        RecyclerView.ViewHolder holder = new Viewholder(view);
        return (Viewholder) holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final Viewholder holder, final int position) {
        String productname = arrayList.get(position).productname2;
        final int proid=arrayList.get(position).id;
    //    String brandname = arrayList.get(position).brand;
        String price = arrayList.get(position).amount;
        String imagename = arrayList.get(position).image;
        holder.productnametext.setText(productname);
        holder.pricename.setText(price);
       // holder.brandname.setText(brandname);
        // Toast.makeText(productadapter.this,,Toast.LENGTH_SHORT).show();
       final String url = "http://192.168.43.222/mobile%20images/";
//       Picasso.get().load(url+imagename).into(img);
        Glide.with(context).asBitmap().load(imagename).into(holder.img);
        holder.removebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(context,Integer.toString(proid),Toast.LENGTH_SHORT).show();
            String url="http://192.168.43.222/cwsecom/deleteorder.php?email="+Person.email+"&"+"productid="+Integer.toString(proid);
                RequestQueue queue= Volley.newRequestQueue(context);
                StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                          holder.removebtn.setText("Removed");
                          Toast.makeText(context,arrayList.get(position).productname2+" is removed",Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
                queue.add(stringRequest);
            }
        });
//        productrowlayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(context, productdetails.class);
//                intent.putExtra("productname", arrayList.get(position).productname);
//                intent.putExtra("brand", arrayList.get(position).brand);
//                intent.putExtra("image", (url + arrayList.get(position).image));
//                intent.putExtra("price", arrayList.get(position).price);
//                context.startActivity(intent);
//
//            }
//        });
    }


    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView productnametext, brandname, pricename;
      Button removebtn;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.imgbtn3);
            productnametext = itemView.findViewById(R.id.productnametextv3);
            //brandname = itemView.findViewById(R.id.brandtextv2);
            pricename = itemView.findViewById(R.id.pricetextv3);
            removebtn=itemView.findViewById(R.id.removebtn);
          //  productrowlayout = itemView.findViewById(R.id.prolayout2);

        }
    }
}