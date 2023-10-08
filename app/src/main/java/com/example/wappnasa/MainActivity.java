package com.example.wappnasa;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.util.Base64;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.ByteArrayOutputStream;
import java.util.List;

import Persistencia.AnimalRepository;
import Persistencia.PuntoRepository;
import Persistencia.SingletonConnection;
import Persistencia.ZonaRepository;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMapClickListener, GoogleMap.OnMapLongClickListener {
    EditText txtDireccion;
    GoogleMap mMap;
    ImageView botonX, imagenPrincipal;
    RelativeLayout popup;
    TextView nombre, estado, descripcion;

    List<Animal> animales;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_main);

        txtDireccion = findViewById(R.id.txtDireccion);
        botonX = findViewById(R.id.botonX);
        popup = findViewById(R.id.popup);
        imagenPrincipal = findViewById(R.id.imagenPrincipal);
        nombre = findViewById(R.id.nombre);
        estado = findViewById(R.id.estado);
        descripcion = findViewById(R.id.descripcion);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap= googleMap;
        this.mMap.setOnMapClickListener(this);
        this.mMap.setOnMapLongClickListener(this);

        //-----------------------------------------------
        //CREACION DE ZONAS
        Thread hiloZona = new Thread(() -> {
            List<Zona> zonas = new ZonaRepository(SingletonConnection.getSingletonInstance()).obtenerTodos();

            for(Zona zonaActual : zonas)
            {
                Thread hiloPunto = new Thread(() -> {

                    List<Punto> puntosZona1 = new PuntoRepository(SingletonConnection.getSingletonInstance()).obtenerPuntosZona(zonaActual.idZona);
                    PolygonOptions polygonZona = new PolygonOptions();
                    for(Punto puntoActual : puntosZona1) {
                        // Define los vértices del polígono
                        polygonZona.add(new LatLng(puntoActual.latitud, puntoActual.longitud));
                    }
                    //Editar el color
                    if(zonaActual.color.equals("BLUE")) {
                        polygonZona.strokeWidth(5).strokeColor(Color.argb(50, 0, 0,255 )).fillColor(Color.argb(50, 0, 0,255 ));
                    } else if(zonaActual.color.equals("GREEN")) {
                        polygonZona.strokeWidth(5).strokeColor(Color.argb(50, 0, 255,0 )).fillColor(Color.argb(50, 0, 255,0 ));
                    } else if(zonaActual.color.equals("RED")) {
                        polygonZona.strokeWidth(5).strokeColor(Color.argb(50, 255, 0,0 )).fillColor(Color.argb(50, 255, 0,0 ));
                    }

                    runOnUiThread(() -> {
                        googleMap.addPolygon(polygonZona);

                        // Configura un Listener para el polígono
                        Polygon polygon = mMap.addPolygon(polygonZona);
                        polygon.setTag(zonaActual.idZona);
                        polygon.setClickable(true);
                    });

                    System.out.println("Ya hay una nueva zona: ");


                });
                hiloPunto.start();
            }

        });
        hiloZona.start();


        //-----------------------------------------------
        //CLICK EN ZONAS
        mMap.setOnPolygonClickListener(new GoogleMap.OnPolygonClickListener() {
            @Override
            public void onPolygonClick(Polygon polygon) {
                // Obtén la etiqueta o identificador del polígono clicado
                int id = (int) polygon.getTag();


                Thread hilo = new Thread(() -> {

                    Zona zona = new ZonaRepository(SingletonConnection.getSingletonInstance()).obtener(id);
                    runOnUiThread(() -> {
                        // Código que afecta a la interfaz de usuario
                        imagenPrincipal.setImageDrawable(getResources().getDrawable(R.drawable.blueshark));

                        nombre.setText(zona.nombre);
                        estado.setText(Html.fromHtml("<b>Estado: </b>") + zona.estado);
                        descripcion.setText(zona.descripcion);
                    });

                });
                hilo.start();

                popup.setVisibility(View.VISIBLE);

            }
        });


        //-----------------------------------------------
        //CREACION DE ANIMALES
        Thread hilo = new Thread(() -> {

            List<Animal> animales = new AnimalRepository(SingletonConnection.getSingletonInstance()).obtenerTodos();
            for(Animal animalActual : animales) {
                runOnUiThread(() -> {
                    // Código que afecta a la interfaz de usuario
                    LatLng nuevoAnimal = new LatLng(animalActual.latitud, animalActual.longitud);
                    MarkerOptions markerNuevoAnimal = new MarkerOptions();
                    markerNuevoAnimal.position(nuevoAnimal);
                    markerNuevoAnimal.title(""+animalActual.idAnimal);
                    markerNuevoAnimal.icon(BitmapDescriptorFactory.fromResource(R.drawable.iconotiburon));
                    mMap.addMarker(markerNuevoAnimal);
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(nuevoAnimal));
                });

                System.out.println("Ya hay un nuevo animal: " + animalActual.nombre);
            }

        });
        hilo.start();

        //--------------------------------------------
        //ANIMALES CLICKADOS
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                int id = Integer.parseInt(marker.getTitle());

                Thread hilo = new Thread(() -> {

                    Animal animal = new AnimalRepository(SingletonConnection.getSingletonInstance()).obtener(id);
                    runOnUiThread(() -> {
                        // Código que afecta a la interfaz de usuario
                        imagenPrincipal.setImageDrawable(getResources().getDrawable(R.drawable.blueshark));

                        nombre.setText(animal.nombre);
                        estado.setText(Html.fromHtml("<b>Estado:</b> Población decreciendo"));
                        descripcion.setText(animal.descripcion);
                    });

                });
                hilo.start();

                popup.setVisibility(View.VISIBLE);
                return false; // Devuelve 'true' si consumes el evento, 'false' si no.
            }
        });
        //-----------------------------------------------------------------------



    }


    public void clickBotonX (View v){
        popup.setVisibility(View.INVISIBLE);
    }


    @Override
    public void onMapClick(@NonNull LatLng latLng) {
        //txtLatitud.setText(""+latLng.latitude);
        //txtLongitud.setText(""+latLng.longitude);
    }

    @Override
    public void onMapLongClick(@NonNull LatLng latLng) {
        //txtLatitud.setText(""+latLng.latitude);
        //txtLongitud.setText(""+latLng.longitude);
    }
}