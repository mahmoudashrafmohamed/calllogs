package com.amanyabdalla.myappexam;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.provider.CallLog;
import android.util.Log;
import android.widget.Toast;

import java.util.Date;

import static android.content.Context.MODE_PRIVATE;



public class CallReceiver extends PhonecallReceiver {



    @Override
    protected void onIncomingCallReceived(Context ctx, String number, Date start)
    {
        //
        Log.e("incoming calllll","received");
        SharedPreferences pref =ctx.getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        final SharedPreferences.Editor editor = pref.edit();
        boolean isEnableToSaveCalls = pref.getBoolean("checked", true);

        if(isEnableToSaveCalls){ // add to shared pref


            Toast.makeText(ctx,"new call added to database",Toast.LENGTH_SHORT).show();

            String callType = "Incoming";
            Date callDate = start;
            String num = "\nPhone Number:--- "+number;

            Log.e("xxx ",callDate+"--" +num);

            String d = pref.getString("history","");
           editor.putString("history",d+"\n------------\n"+callType+"\n "+num+"\n "+callDate);
            editor.commit();
            editor.apply();






        }
    }

    @Override
    protected void onIncomingCallAnswered(Context ctx, String number, Date start)
    {
        //
    }

    @Override
    protected void onIncomingCallEnded(Context ctx, String number, Date start, Date end)
    {
        //
    }

    @Override
    protected void onOutgoingCallStarted(Context ctx, String number, Date start)
    {
        //
    }

    @Override
    protected void onOutgoingCallEnded(Context ctx, String number, Date start, Date end)
    {
        //
    }

    @Override
    protected void onMissedCall(Context ctx, String number, Date start)
    {
        //
    }

}