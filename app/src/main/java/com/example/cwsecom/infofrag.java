package com.example.cwsecom;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link infofrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class infofrag extends Fragment {
    static String id;
    private TextView infotextv;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public infofrag(String id) {
        this.id=id;
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment infofrag.
     */
    // TODO: Rename and change types and number of parameters
    public static infofrag newInstance(String param1, String param2) {
        infofrag fragment = new infofrag(id);
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootview= inflater.inflate(R.layout.fragment_infofrag, container, false);
        infotextv=rootview.findViewById(R.id.infotextv);
        //Toast.makeText(rootview.getContext(),id,Toast.LENGTH_SHORT).show();

        String url="http://192.168.43.222/cwsecom/fetchproductdetails.php?productid="+id;
        RequestQueue queue= Volley.newRequestQueue(rootview.getContext());
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                String str ="mytext" ;
                for(int i=0;i<response.length();i++)
                {
                    try {
                        str =response.getJSONObject(i).getString("info").toString();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                infotextv.setText(str);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(rootview.getContext(),error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(jsonArrayRequest);

        return rootview;
    }
}
