package com.rsin.todonotes;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.RemoteViews;

import static com.rsin.todonotes.ExampleAppwidgetConfig.SHARED_PREF;
import static com.rsin.todonotes.ExampleAppwidgetConfig.key_Button_text;

public class ExampleAppWidget extends AppWidgetProvider {

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for (int appWidegetID: appWidgetIds)
        {
            Intent intent= new Intent(context,MainActivity.class);
           
            PendingIntent pendingIntent = PendingIntent.getActivity(context,0,intent,0);
            SharedPreferences pref = context.getSharedPreferences(SHARED_PREF,Context.MODE_PRIVATE);
            String buttontext = pref.getString(key_Button_text+appWidegetID,"press me");
            RemoteViews remoteViews = new RemoteViews(context.getPackageName(),R.layout.example_widget);
            remoteViews.setOnClickPendingIntent(R.id.lay,pendingIntent);
            remoteViews.setCharSequence(R.id.clcikme,"setText",buttontext);
            appWidgetManager.updateAppWidget(appWidegetID,remoteViews);
        }
    }
}
