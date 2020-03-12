package com.example.cwsecom;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

class productadapter2 extends RecyclerView.Adapter<productadapter2.Viewholder>
{private LinearLayout productrowlayout;

    private Context context;
    private ArrayList<product> arrayList;
    productadapter2(Context context,ArrayList<product> arrayList)
    {
        this.context=context;
        this.arrayList=arrayList;

    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.productrow2,parent,false);
        RecyclerView.ViewHolder holder=new Viewholder(view);
        return (Viewholder) holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, final int position) {
        String productname=arrayList.get(position).productname;
        String brandname=arrayList.get(position).brand;
        String price=arrayList.get(position).price;
        String imagename=arrayList.get(position).image;
        int id=arrayList.get(position).id;
        holder.productnametext.setText(productname);
        holder.pricename.setText(changepricelayout(price));
        holder.brandname.setText(brandname);
        // Toast.makeText(productadapter.this,,Toast.LENGTH_SHORT).show();
        final String url="http://192.168.43.222/mobile%20images/";
//       Picasso.get().load(url+imagename).into(img);
        Glide.with(context).asBitmap().load(url+imagename).into(holder.img);
        productrowlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,productdetails.class);
                intent.putExtra("id",arrayList.get(position).id);
                intent.putExtra("productname",arrayList.get(position).productname);
                intent.putExtra("brand",arrayList.get(position).brand);
                intent.putExtra("image",(url+arrayList.get(position).image));
                intent.putExtra("price",arrayList.get(position).price);
                context.startActivity(intent);

            }
        });
    }


    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder
    {
        ImageView img;
        TextView productnametext,brandname,pricename;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.imgbtn2);
            productnametext=itemView.findViewById(R.id.productnametextv2);
            brandname=itemView.findViewById(R.id.brandtextv2);
            pricename=itemView.findViewById(R.id.pricetextv2);
            productrowlayout=itemView.findViewById(R.id.prolayout2);

        }
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