package com.rsin.todonotes;

import androidx.appcompat.app.AppCompatActivity;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RemoteViews;

public class ExampleAppwidgetConfig extends AppCompatActivity {
    public  static final String SHARED_PREF = "prefs";
    public  static final String key_Button_text = "KeyButtonText";
    private int appWidgetId = AppWidgetManager.INVALID_APPWIDGET_ID;
    Button button;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example_appwidget_config);

        Intent configIntent = getIntent();
        Bundle extras = configIntent.getExtras();
        if (extras!=null){
            appWidgetId = extras.getInt(AppWidgetManager.EXTRA_APPWIDGET_ID,AppWidgetManager.INVALID_APPWIDGET_ID);
        }

        Intent resultvalue = new Intent();
        resultvalue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,appWidgetId);
        setResult(RESULT_CANCELED,resultvalue);

        if (appWidgetId == AppWidgetManager.INVALID_APPWIDGET_ID)
        {
            //something wrong
            finish();
        }

        button = findViewById(R.id.set_text);
        editText = findViewById(R.id.input_text);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(getApplicationContext());
                Intent  intent = new Intent(getApplicationContext(),MainActivity.class);
                PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(),0,intent,0);
                String button_text = editText.getText().toString();
                RemoteViews views = new RemoteViews(getPackageName(),R.layout.example_widget);
                views.setOnClickPendingIntent(R.id.lay,pendingIntent);
                views.setCharSequence(R.id.clcikme,"setText",button_text);
                appWidgetManager.updateAppWidget(appWidgetId,views);
                SharedPreferences preferences = getSharedPreferences(SHARED_PREF,MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString(key_Button_text+appWidgetId,button_text);
                editor.apply();

                Intent resultvalue = new Intent();
                resultvalue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,appWidgetId);
                setResult(RESULT_OK,resultvalue);
                finish();

            }
        });

    }
}