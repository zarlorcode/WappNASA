package com.example.wappnasa;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.EditText;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMapClickListener, GoogleMap.OnMapLongClickListener {
    EditText txtLatitud, txtLongitud;
    GoogleMap mMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtLatitud = findViewById(R.id.txtLatitud);
        txtLongitud = findViewById(R.id.txtLongitud);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap= googleMap;
        this.mMap.setOnMapClickListener(this);
        this.mMap.setOnMapLongClickListener(this);

        //Add mark
        LatLng spain = new LatLng(40.33619115953262, -3.767719012949285);
        mMap.addMarker(new MarkerOptions().position(spain).title("Spain"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(spain));

        // Define los vértices del polígono
        LatLng p1 = new LatLng(41.26417100476725, 1.953193052956383);
        LatLng p2 = new LatLng(41.02885965406742, 0.9676577810986803);
        LatLng p3 = new LatLng(39.562252886971415, -0.18613961034447837);
        LatLng p4 = new LatLng(39.227888621023325, -0.18613961034447837);
        LatLng p5 = new LatLng(38.835774245922785, 0.27057185710177256);
        PolygonOptions zoneBalear = new PolygonOptions().add(p1, p2, p3, p4,p5).strokeWidth(6).strokeColor(Color.RED).fillColor(Color.argb(50, 255, 0,0 ));

        // Dibuja el polígono en el mapa
        googleMap.addPolygon(zoneBalear);

        LatLng balearicSea = new LatLng(40.18107881426427, 0.6552459715595473);
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(balearicSea);
        markerOptions.title("Balearic sea");
        markerOptions.snippet("Hola me llamo paco,me gustan las hamburguesas");
        markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.calavera));
        mMap.addMarker(markerOptions);


        // Crea un objeto PolygonOptions y agrega los vértices

    }



    @Override
    public void onMapClick(@NonNull LatLng latLng) {
        txtLatitud.setText(""+latLng.latitude);
        txtLongitud.setText(""+latLng.longitude);
    }

    @Override
    public void onMapLongClick(@NonNull LatLng latLng) {
        txtLatitud.setText(""+latLng.latitude);
        txtLongitud.setText(""+latLng.longitude);
    }
}