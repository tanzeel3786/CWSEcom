package com.example.cwsecom;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class tabbedact extends AppCompatActivity {
  //  private desfrag;
private Toolbar toolbar;
private ViewPager viewPager;
private TabLayout tabLayout;
private descriptionfrag descriptionfrag;
private detailsfrag detailsfrag;
private infofrag infofrag;
public int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabbedact);
        viewPager=findViewById(R.id.view_pager);
        Bundle extras = getIntent().getExtras();
         id=extras.getInt("productid");
toolbar=findViewById(R.id.mytoolbar);
setSupportActionBar(toolbar);
tabLayout=findViewById(R.id.tablayout);
String id2=Integer.toString(id);
descriptionfrag =new descriptionfrag(id2);
detailsfrag= new detailsfrag(id2);
infofrag=new infofrag(id2);
tabLayout.setupWithViewPager(viewPager);
ViewPagerAdapter viewPagerAdapter=new ViewPagerAdapter(getSupportFragmentManager(),0);
viewPagerAdapter.addfragment(descriptionfrag,"Description");
viewPagerAdapter.addfragment(detailsfrag,"Specifications");
viewPagerAdapter.addfragment(infofrag,"Info");
viewPager.setAdapter(viewPagerAdapter);

    }

    private class ViewPagerAdapter extends FragmentPagerAdapter {
               List<Fragment> fragments=new ArrayList<>();
               List<String> fragmenttitle=new ArrayList<>();
        public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
            super(fm, behavior);
        }
        public void addfragment(Fragment fragment,String title)
        {
            fragments.add(fragment);
            fragmenttitle.add(title);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {

            return fragments.get(position);

        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return fragmenttitle.get(position);
        }
    }
}