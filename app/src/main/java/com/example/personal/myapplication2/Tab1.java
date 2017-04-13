package com.example.personal.myapplication2;

import android.Manifest;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import static android.R.attr.enabled;
import static android.R.attr.targetActivity;
import static android.content.Context.LOCATION_SERVICE;
import static android.support.v4.content.PermissionChecker.checkSelfPermission;
import static java.lang.Math.atan2;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.sqrt;
import static java.security.AccessController.checkPermission;

public class Tab1 extends Fragment{
    ImageButton imagebutton, imagebutton1;
    double dist=0,prev_lat,prev_lon,current_lat,current_lon;
    Context ctx;
    boolean isGPSEnabled = false;
    boolean isNetworkEnabled = false;
    Location location,end_point,loc;
    double latitude;
    double longitude;
    double LOCAL_PI=3.1415926535897932385;
    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 1;
    private static final long MIN_TIME_BW_UPDATES = 1000 * 10 * 1;
    protected LocationManager locationManager;
    boolean canGetLocation = false;
    int i=0;
    GPSTracker gps;
    final Handler ha=new Handler();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_common_tab, container, false);
    }
    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ctx = getActivity();
        imagebutton = (ImageButton) view.findViewById(R.id.play);
        imagebutton1 = (ImageButton) view.findViewById(R.id.pause);
        ProgressBar progressBar = (ProgressBar) view.findViewById(R.id.pb);
        ObjectAnimator animation = ObjectAnimator.ofInt(progressBar, "progress", 0, 200);
        animation.setDuration(2000);
        animation.setInterpolator(new DecelerateInterpolator());
        animation.start();
        imagebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                imagebutton.setVisibility(View.INVISIBLE);
                imagebutton1.setVisibility(View.VISIBLE);
                loc = getLocation();
                if(loc!=null) {
                    prev_lat = getLocationLatitude(loc);
                    prev_lon=getLocationLongitude(loc);
                    }
                ha.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Location loc1 = getLocation();
                        if(loc1!=null) {
                            current_lat=getLocationLatitude(loc1);
                            current_lon=getLocationLongitude(loc1);
                            Toast.makeText(ctx,"cur"+current_lat+","+current_lon+"prv"+prev_lat+","+prev_lon,Toast.LENGTH_LONG).show();
                            //dist+=distance(prev_lat,prev_lon,current_lat,current_lon);
                            dist = loc.distanceTo(loc1);
                            loc=loc1;
                            prev_lat = current_lat;
                            prev_lon = current_lon;
                            //dist+=DirectDistance(prev_lat,prev_lon,current_lat,current_lon);
                        }
                        ha.postDelayed(this, 5000);

                    }
                }, 5000);
                //         getPermission(Manifest.permission.ACCESS_FINE_LOCATION, "This is required to to get your location at the time of danger!");
                //Location loc = getLocation();
                //if(loc!=null) {
                //  String msg = getLocationLink(loc);
                //}
            }
        });
        imagebutton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                imagebutton1.setVisibility(View.INVISIBLE);
                imagebutton.setVisibility(View.VISIBLE);
                ha.removeCallbacksAndMessages(null);
                Toast.makeText(ctx,"Distance is"+dist,Toast.LENGTH_SHORT).show();
                dist=0;
            }
        });
    }
    public void getPermission(String per, String reason){
        if (ActivityCompat.checkSelfPermission(getActivity(), per) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), per)) {
                if(reason!=null) {
                    Toast.makeText(getActivity(), reason, Toast.LENGTH_SHORT).show();
                }
            }+
            ActivityCompat.requestPermissions(getActivity(), new String[]{per}, 0);
        } else {
            Toast.makeText(getActivity(), "Permission already granted! for " + per, Toast.LENGTH_SHORT).show();
        }
    }
    private Location getLocation(){
        Location loc = null;
        Log.i("checkingt1","came here");
        GPSTracker1 gpsTrk=new GPSTracker1(getContext());
        loc = gpsTrk.location;
        //if(gpsTrk.lastTimeLocationChanged-System.currentTimeMillis()>1000*5){
         /*   Location tLoc=MyGPSUtils.getGPSLocation(ctx);
            if(tLoc!=null){
                Log.i("checkingtlocif","came here");
                loc=MyGPSUtils.getGPSLocation(ctx);
            }
            else {
                Log.i("checkingtlocelse","came here");
                loc=gpsTrk.location;
            }*/
        //}
        //else {
          //  if(gpsTrk.location!=null){
            //    loc=gpsTrk.location;
            //}
        //}
        return loc;
    }
    private void getLocationLink(Location loc) {
        Location lo1 = MyGPSUtils.getGPSLocation(ctx);
        double lat = loc.getLatitude();
        double longitude = loc.getLongitude();
        Toast.makeText(this.ctx, lat + "and" + longitude, Toast.LENGTH_LONG).show();
        //String res = "https://www.google.com/maps?q=loc:" + lat + "," + longitude;
        //return res;
    }
    private double getLocationLatitude(Location loc)
    {
        Location lo1 = MyGPSUtils.getGPSLocation(ctx);
        double lat = loc.getLatitude();
        return lat;
    }
    private double getLocationLongitude(Location loc)
    {
        Location lo1 = MyGPSUtils.getGPSLocation(ctx);
        double lon = loc.getLongitude();
        return lon;
    }
    private double distance(double lat1, double lon1, double lat2, double lon2) {
        double theta = lon1 - lon2;
        double distance_x = sin(deg2rad(lat1))
                * sin(deg2rad(lat2))
                + cos(deg2rad(lat1))
                * cos(deg2rad(lat2))
                * cos(deg2rad(theta));
        distance_x = Math.acos(dist);
        distance_x = rad2deg(dist);
        distance_x = dist * 60 * 1.1515;
        return (distance_x);//in km
    }

    private double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    private double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
    }
    public static double distFrom(double lat1, double lng1, double lat2, double lng2) {
        double earthRadius = 6371000; //meters
        double dLat = Math.toRadians(lat2-lat1);
        double dLng = Math.toRadians(lng2-lng1);
        double a = sin(dLat/2) * sin(dLat/2) +
                cos(Math.toRadians(lat1)) * cos(Math.toRadians(lat2)) *
                        sin(dLng/2) * sin(dLng/2);
        double c = 2 * atan2(sqrt(a), sqrt(1-a));
        double dist = (double) (earthRadius * c);
        return dist;
    }
    double ToRadians(double degrees)
    {
        double radians = degrees * LOCAL_PI / 180;
        return radians;
    }

    double DirectDistance(double lat1, double lng1, double lat2, double lng2)
    {
        double earthRadius = 3958.75;
        double dLat = ToRadians(lat2-lat1);
        double dLng = ToRadians(lng2-lng1);
        double a = sin(dLat/2) * sin(dLat/2) +
                cos(ToRadians(lat1)) * cos(ToRadians(lat2)) *
                        sin(dLng/2) * sin(dLng/2);
        double c = 2 * atan2(sqrt(a), sqrt(1-a));
        double dist = earthRadius * c;
        double meterConversion = 1609.00;
        return dist * meterConversion;
    }
}
