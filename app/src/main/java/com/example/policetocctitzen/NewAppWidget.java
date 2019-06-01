package com.example.policetocctitzen;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.widget.RemoteViews;
import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;

public class NewAppWidget extends AppWidgetProvider {

    static void updateAppWidget(Context context,

                                AppWidgetManager appWidgetManager,

                                int appWidgetId) {

//Instantiate the RemoteViews object//

        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.new_app_widget);

//Update your app’s text, using the setTextViewText method of the RemoteViews class//

        views.setTextViewText(R.id.id_value, String.valueOf(appWidgetId));

//Register the OnClickListener//


    }

    @Override

    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.new_app_widget);
        Intent configIntent = new Intent(context, FileComplaints.class);

        PendingIntent configPendingIntent = PendingIntent.getActivity(context, 0, configIntent, 0);

        remoteViews.setOnClickPendingIntent(R.id.launch_url, configPendingIntent);
        appWidgetManager.updateAppWidget(appWidgetIds, remoteViews);
    }
}