package com.example.trackntriggery;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.io.Console;
import java.net.ContentHandler;
import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdaptery extends FragmentPagerAdapter {
    private List<Fragment> fragmentList=new ArrayList<>();
    private List<String> stringList = new ArrayList<>();
    private Context mContext;
    /*
    public ViewPagerAdaptery(@NonNull FragmentManager fm) {
        super(fm);
    }*/

    public ViewPagerAdaptery(@NonNull FragmentManager fm) {
        super(fm);
    }



    public ViewPagerAdaptery(Context mContext, FragmentManager fm){
        super(fm);
        this.mContext=mContext;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return stringList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        Log.d("TAG","position is "+position);
        return stringList.get(position);
    }
    public void AddFragment(Fragment fragment,String title){
        fragmentList.add(fragment);
        stringList.add(title);
    }
}
