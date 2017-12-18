package com.amanyabdalla.myappexam;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CallLog;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.amanyabdalla.myappexam.R;

import java.util.Date;

import static android.content.Context.MODE_PRIVATE;


public class Incoming extends Fragment {
    private static final int REQUEST_READ_CONTACTS = 710;


    TextView tv;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view=  inflater.inflate(R.layout.fragment_incoming, container, false);

        SharedPreferences pref =getActivity().getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        final SharedPreferences.Editor editor = pref.edit();
        String HISTORY = pref.getString("history","no data found");
        tv = (TextView)view.findViewById(R.id.tv);

         tv.setText(HISTORY);


           /// getCallDetails();


        return view;
    }
    private void getCallDetails() {
        StringBuffer sb = new StringBuffer();
        String strOrder = android.provider.CallLog.Calls.DATE + " DESC";
  /* Query the CallLog Content Provider */
        Cursor managedCursor = getActivity().managedQuery(CallLog.Calls.CONTENT_URI, null,
                null, null, strOrder);
        int number = managedCursor.getColumnIndex(CallLog.Calls.NUMBER);
        int type = managedCursor.getColumnIndex(CallLog.Calls.TYPE);
        int date = managedCursor.getColumnIndex(CallLog.Calls.DATE);
        int duration = managedCursor.getColumnIndex(CallLog.Calls.DURATION);
        sb.append("Call Log :");
        while (managedCursor.moveToNext()) {
            String phNum = managedCursor.getString(number);
            String callTypeCode = managedCursor.getString(type);
            String strcallDate = managedCursor.getString(date);
            Date callDate = new Date(Long.valueOf(strcallDate));
            String callDuration = managedCursor.getString(duration);
            String callType = null;
            int callcode = Integer.parseInt(callTypeCode);
            switch (callcode) {
                case CallLog.Calls.OUTGOING_TYPE:
                    callType = "Outgoing";
                    break;
                case CallLog.Calls.INCOMING_TYPE:
                    callType = "Incoming";
                    sb.append("\nPhone Number:--- " + phNum + " \nCall Type:--- "
                            + callType + " \nCall Date:--- " + callDate
                            + " \nCall duration in sec :--- " + callDuration);
                    sb.append("\n----------------------------------");
                    break;
                case CallLog.Calls.MISSED_TYPE:
                    callType = "Missed";
                    break;
            }




        }
      //  managedCursor.close();
         tv.setText(sb);
        Log.e("ans" , String.valueOf(sb));
        SharedPreferences pref =getActivity().getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        final SharedPreferences.Editor editor = pref.edit();
        editor.putString("history",String.valueOf(sb));
        editor.commit();
        editor.apply();

    }

}
