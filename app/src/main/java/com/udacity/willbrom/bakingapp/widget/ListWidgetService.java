package com.udacity.willbrom.bakingapp.widget;

import android.content.Intent;
import android.widget.RemoteViewsService;



public class ListWidgetService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new ListRemoteViewsFactory(getApplicationContext());
    }
}
