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
                    polygonZona.strokeWidth(5).strokeColor(Color.argb(50, 0, 0,255 )).fillColor(Color.argb(50, 0, 0,255 ));
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


                /*// Realiza acciones diferentes según la etiqueta o identificador
                if ("Zona 1".equals(tag)) {
                    // Acciones para la Zona 1
                    imagenPrincipal.setImageDrawable(getResources().getDrawable(R.drawable.elcabanyal));

                    nombre.setText("Costa Valenciana");
                    estado.setText(Html.fromHtml("<b>Estado:</b> Ligeramente contaminada"));
                    descripcion.setText(Html.fromHtml("<b>Temperatura del agua:</b> verano 24-28 grados Celsius, invierno 12-15 grados Celsius.\n" +
                            "<br>" +
                            "<b>Salinidad:</b> alrededor de 38 partes por mil (ppt), es decir relativamente alta.\n" +
                            "<br>" +
                            "<b>Niveles de oxígeno disuelto:</b> Los valores suelen estar por encima de 5-6 miligramos por litro (mg/L). Tiene niveles saludables para la vida marina.\n" +
                            "<br>" +
                            "<b>Acidez del agua (pH):</b> alcalino, con valores típicos que varían de 7.8 a 8.4.\n" +
                            "<br>" +
                            "<b>Contaminación por plásticos:</b> La costa Valenciana, al igual que otros mares, enfrenta problemas de contaminación por plásticos, especialmente en áreas costeras y cerca de rutas de navegación."));


                } else if ("Zona 2".equals(tag)) {
                    imagenPrincipal.setImageDrawable(getResources().getDrawable(R.drawable.playafrancia));
                    nombre.setText("Costa Francesa");
                    estado.setText(Html.fromHtml("<b>Estado:</b> Moderadamente contaminada"));
                    descripcion.setText(Html.fromHtml("<b>Temperatura:</b> Verano 20-22°C, invierno 8-10°C\n" +
                            "<br>" +
                            "<b>Salinidad:</b> alrededor de 30-35 partes por mil (ppt), valores moderadamente altos.\n" +
                            "<br>" +
                            "<b>Niveles de oxígeno disuelto:</b> Los valores suelen estar por encima de 5-6 miligramos por litro (mg/L). Tiene niveles saludables para la vida marina.\n" +
                            "<br>" +
                            "<b>Acidez del agua (pH):</b> alcalino, con valores típicos que varían de 7.8 a 8.4.\n" +
                            "<br>" +
                            "<b>Contaminación por plásticos:</b> Como en muchas partes del mundo, la contaminación por plásticos es un problema en algunas áreas costeras de Francia, especialmente cerca de rutas de navegación y áreas urbanas."));

                } else if ("Zona 3".equals(tag)) {
                    // Acciones para la Zona 3
                    imagenPrincipal.setImageDrawable(getResources().getDrawable(R.drawable.marmuerto));
                    nombre.setText("Mar Muerto");
                    estado.setText(Html.fromHtml("<b>Estado:</b> Muy Contaminada"));
                    descripcion.setText(Html.fromHtml("<b>Temperatura:</b> 35ºC-40ºC en verano, supera con creces a la mayoría de cuerpos de agua. En invierno puede estar entre 17ºC y 20ºC\n" +
                            "<br>" +
                            "<b>Salinidad:</b> alrededor de 330-350 partes por mil (ppt), valores exageradamente altos.\n" +
                            "<br>" +
                            "<b>Niveles de oxígeno disuelto:</b> Debido a la alta cantidad de sal y, por ello a la ausencia de vida marina típica, los niveles de oxigeno en el mar muerto son extremadamente bajos\n" +
                            "<br>" +
                            "<b>Acidez del agua (pH):</b> El pH del Mar Muerto es típicamente muy bajo, lo que significa que es altamente ácido. Los valores de pH en el Mar Muerto pueden caer en un rango de alrededor de 6.3 a 6.7.\n" +
                            "<br>" +
                            "<b>Contaminación por plásticos:</b> Con un impacto menor que en otros cuerpos de agua pero, también se han encontrado plásticos y deshechos en el Mar Muerto."));

                } else if ("Zona 4".equals(tag)){
                    imagenPrincipal.setImageDrawable(getResources().getDrawable(R.drawable.marnegro));
                    nombre.setText("Mar Negro\n");
                    estado.setText(Html.fromHtml("<b>Estado:</b> Muy Contaminado\n"));
                    descripcion.setText(Html.fromHtml("<b>Temperatura:</b> 22ºC-26ºC en verano. En invierno puede estar entre 8ºC y 12ºC\n" +
                            "<br>" +
                            "<b>Salinidad:</b> alrededor de 17-18 partes por mil (ppt), valores bastante bajos comparados con otros mares y oceanos.\n" +
                            "<br>" +
                            "<b>Niveles de oxígeno disuelto:</b> En general tiene niveles normales de oxigeno disuelto. Saludables para la vida marina\n" +
                            "<br>" +
                            "<b>Contaminación: </b> Debido a la situación del mar negro, con muchas zonas costeras cercanas a fábricas, está bastante contaminado"));

                }else if ("Zona 5".equals(tag)) {
                    imagenPrincipal.setImageDrawable(getResources().getDrawable(R.drawable.marrojo));
                    nombre.setText("Mar Rojo\n");
                    estado.setText(Html.fromHtml("<b>Estado:</b> Ligeramente Contaminado\n"));
                    descripcion.setText(Html.fromHtml("<b>Temperatura:</b> 26ºC-30ºC en verano. En invierno puede estar entre 21ºC y 25ºC\n" +
                            "<br>" +
                            "<b>Salinidad:</b> La salinidad del Mar Rojo puede oscilar entre 36 y 41 partes por mil (ppt). Son valores bastante altos\n" +
                            "<br>" +
                            "<b>Niveles de oxígeno disuelto:</b> En general tiene niveles normales de oxigeno disuelto. Saludables para la vida marina\n" +
                            "<br>" +
                            "<b>Peligrosidad: El mar rojo tiene bastantes especies peligrosas, como tiburones, peces león, peces escorpión y muchos más"));

                }*/
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