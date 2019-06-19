package com.example.abdo.remusix;


import android.Manifest;
import android.Manifest.permission;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.abdo.remusix.api.ApiService;
import com.example.abdo.remusix.api.NearbyUsersResponse;
import com.example.abdo.remusix.api.RetrofitClient;
import com.example.abdo.remusix.api.UpdateUser;

import android.os.IBinder;
import android.provider.Settings;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * A simple {@link Fragment} subclass.
 */
public class LocationFragment extends Fragment {


    private RecyclerView recyclerView;
    private LocationAdapter locationAdapter;
    private List<NearbyUsersResponse> mysers;

    LocationManager locationManager;
    LocationListener locationListener;


    Button btn_location_id;


    private Retrofit retrofit;

    private ApiService apiService;
    Context context;
    FragmentActivity activity;
    private String bestProvider=LocationManager.NETWORK_PROVIDER;

    public LocationFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = getActivity();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_location, container, false);
        context = activity.getApplicationContext();
        btn_location_id = view.findViewById(R.id.btn_location_id);
        mysers= new ArrayList<>();


        recyclerView = view.findViewById(R.id.location_list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        recyclerView.setAdapter(new LocationAdapter(getContext(),mysers));
        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);

        if (!runTime_Permission()) {

            if (ActivityCompat.checkSelfPermission(getActivity().getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) !=
                    PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity().getApplicationContext(),
                    Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            }else {

                Location lastKnownLocation = locationManager.getLastKnownLocation(bestProvider);
                if (lastKnownLocation != null) {
                    retrofit = RetrofitClient.getInstance();
                    apiService = retrofit.create(ApiService.class);
                    Log.e("location ", lastKnownLocation.getLongitude() + " " + lastKnownLocation.getAltitude());
                    Call<Boolean> call = apiService.updateUserLocation("ahmed_mohamed", lastKnownLocation.getLongitude(), lastKnownLocation.getLatitude());
                    call.enqueue(new Callback<Boolean>() {
                        @Override
                        public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                            Log.e("eeeeeeeeeeeeeeeeeeee", response.body() + "");
                            if (response.isSuccessful()) {
                                Toast.makeText(getActivity().getApplicationContext(), "Location updated", Toast.LENGTH_SHORT << 3).show();

                            } else {
                                Toast.makeText(getActivity().getApplicationContext(), "something is wrong", Toast.LENGTH_SHORT << 4).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<Boolean> call, Throwable t) {

                        }
                    });
                }
            }



            configureLocation();
        }


        return view;


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 100 && grantResults.length > 0) {
            for (int i : grantResults)
                if (i != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(context, "please check permissions", Toast.LENGTH_SHORT << 1).show();
                    return;
                }
            configureLocation();
        } else runTime_Permission();
    }

    private void configureLocation() {
        btn_location_id.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {

                locationListener = new LocationListener() {
                    @Override
                    public void onLocationChanged(Location lastKnownLocation) {
                        retrofit = RetrofitClient.getInstance();
                        apiService = retrofit.create(ApiService.class);

                        if (lastKnownLocation != null) {
                            Log.e("location ", lastKnownLocation.getLongitude() + " " + lastKnownLocation.getAltitude());
                            Call<Boolean> call = apiService.updateUserLocation("ahmed_mohamed", lastKnownLocation.getLongitude(), lastKnownLocation.getLatitude());
                            call.enqueue(new Callback<Boolean>() {
                                @Override
                                public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                                    Log.e("eeeeeeeeeeeeeeeeeeee", response.body() + "");
                                    if (response.isSuccessful() ) {
                                        Toast.makeText(context, "Location updated", Toast.LENGTH_SHORT << 3).show();

                                    } else {
                                        Toast.makeText(context, "something is wrong", Toast.LENGTH_SHORT << 4).show();
                                    }
                                }

                                @Override
                                public void onFailure(Call<Boolean> call, Throwable t) {

                                }
                            });

                            Call<NearbyUsersResponse[]> nearbyUsers = apiService.getNearbyUsers("ahmed_mohamed", lastKnownLocation.getLongitude(), lastKnownLocation.getLatitude());
                            nearbyUsers.enqueue(new Callback<NearbyUsersResponse[]>() {
                                @Override
                                public void onResponse(Call<NearbyUsersResponse[]> call, Response<NearbyUsersResponse[]> response) {
                                    if (response.isSuccessful()&&response.body()!=null){
                                        mysers.clear();
                                        for (int i = 0; i < response.body().length; i++) {
                                            mysers.add(response.body()[i]);
                                        }
                                        locationAdapter=new LocationAdapter(getActivity().getApplicationContext(),mysers);
                                        recyclerView.setAdapter(locationAdapter);
                                    }

                                }

                                @Override
                                public void onFailure(Call<NearbyUsersResponse[]> call, Throwable t) {

                                }
                            });

                        }
                    }



                    @Override
                    public void onStatusChanged(String provider, int status, Bundle extras) {

                    }

                    @Override
                    public void onProviderEnabled(String provider) {

                    }

                    @Override
                    public void onProviderDisabled(String provider) {
                        Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                };
                locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);

                if (ActivityCompat.checkSelfPermission(getActivity().getApplicationContext(),
                        permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                        ActivityCompat.checkSelfPermission(getActivity().getApplicationContext(), permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                locationManager.requestLocationUpdates(bestProvider!=null?bestProvider:LocationManager.NETWORK_PROVIDER, 2000, 5, locationListener);
            }
        });


    }



    private boolean runTime_Permission() {
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.M && activity.checkCallingOrSelfPermission( permission.ACCESS_FINE_LOCATION)
                !=PackageManager.PERMISSION_GRANTED && activity.checkCallingOrSelfPermission(permission.ACCESS_COARSE_LOCATION)
                !=PackageManager.PERMISSION_GRANTED){
            activity.requestPermissions(new String[]{permission.ACCESS_COARSE_LOCATION, permission.ACCESS_FINE_LOCATION},100);
            return true;
        }
        return false;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (locationManager!=null&&locationListener!=null){
            locationManager.removeUpdates(locationListener);

        }
    }
}
