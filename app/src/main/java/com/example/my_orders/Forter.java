package com.example.my_orders;

import android.Manifest;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Build;

import androidx.core.app.ActivityCompat;

import com.forter.mobile.fortersdk.ForterSDK;
import com.forter.mobile.fortersdk.integrationkit.ForterIntegrationUtils;
import com.forter.mobile.fortersdk.interfaces.IForterSDK;
import com.forter.mobile.fortersdk.models.TrackType;
import com.google.android.gms.location.FusedLocationProviderClient;


public class Forter extends Application {
    private FusedLocationProviderClient fusedLocationClient;

    @Override
    public void onCreate() {
        super.onCreate();
        String mobileId = ForterIntegrationUtils.getDeviceUID(this);


        // Get ForterClient instance and initiate it with the application context
        IForterSDK ftr = ForterSDK.getInstance();
        ftr.setDevLogsEnabled(true);
        ftr.init(this,
                AppConstants.FORTER_SITE_ID, // include your unique Forter site ID
                mobileId // Provide your mobile device Unique ID
        );
        // Start generic activity-based navigation tracking
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            registerActivityLifecycleCallbacks(ftr.getActivityLifecycleCallbacks());
        }


        // Send the 'app active' event.
        ftr.trackAction(TrackType.APP_ACTIVE);


        LocationManager locationManager =
                (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        LocationProvider provider =
                locationManager.getProvider(LocationManager.GPS_PROVIDER);


        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Location loc = locationManager.getLastKnownLocation(String.valueOf(provider));

        ForterSDK.getInstance().onLocationChanged(loc);




    }



}