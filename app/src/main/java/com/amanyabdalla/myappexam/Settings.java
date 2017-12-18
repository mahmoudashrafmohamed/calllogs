package com.amanyabdalla.myappexam;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import static android.content.Context.MODE_PRIVATE;


public class Settings extends Fragment {

    CheckBox checkBox;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_settings, container, false);

        SharedPreferences pref =getActivity().getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        final SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("checked", true);
        editor.commit();

        checkBox = (CheckBox) view.findViewById(R.id.checkBox);

        checkBox.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if(checkBox.isChecked()){
                    System.out.println("Checked");
                    editor.putBoolean("checked", true);
                    editor.commit();
                }else{
                    editor.putBoolean("checked", false);
                    System.out.println("Un-Checked");
                    editor.commit();
                }
            }
        });

        return view;
    }


}
