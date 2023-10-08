package com.example.wappnasa;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
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

import java.util.List;

import Persistencia.AnimalRepository;
import Persistencia.SingletonConnection;

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

        //-----------------------------------------------------------------------
        //ZONA 1

        // Define los vértices del polígono
        LatLng p1 = new LatLng(41.26417100476725, 1.953193052956383);
        LatLng p2 = new LatLng(41.02885965406742, 0.9676577810986803);
        LatLng p3 = new LatLng(39.562252886971415, -0.18613961034447837);
        LatLng p4 = new LatLng(39.227888621023325, -0.18613961034447837);
        LatLng p5 = new LatLng(38.835774245922785, 0.27057185710177256);
        PolygonOptions zoneBalear = new PolygonOptions().add(p1, p2, p3, p4,p5).strokeWidth(5).strokeColor(Color.argb(100, 0, 0,255 )).fillColor(Color.argb(50, 0, 0,255 ));

        // Dibuja el polígono en el mapa
        googleMap.addPolygon(zoneBalear);

        // Configura un Listener para el polígono
        Polygon polygon = mMap.addPolygon(zoneBalear);
        polygon.setTag("Zona 1");

        polygon.setClickable(true);


        //-----------------------------------------------------------------------
        //ZONA 2
        p1 = new LatLng(43.5421999,-1.5401939);
        p2 = new LatLng(46.1548007,-1.5081489);
        p3 = new LatLng(47.7409786,-4.4735639);
        p4 = new LatLng(44.9864197,-6.3757989);
        PolygonOptions zonax = new PolygonOptions().add(p1, p2, p3, p4).strokeWidth(5).strokeColor(Color.argb(100, 0, 255,0 )).fillColor(Color.argb(50, 0, 255,0 ));

        googleMap.addPolygon(zonax);

        Polygon polygonFran = mMap.addPolygon(zonax);
        polygonFran.setTag("Zona 2");
        polygonFran.setClickable(true);

        //-----------------------------------------------------------------------
        //ZONA 3
        p1 = new LatLng(31.7618455,35.5069471);
        p2 = new LatLng(31.5882584,35.4244368);
        p3 = new LatLng(31.3405186,35.4314751);
        p4 = new LatLng(31.4144056,35.5523041);
        p5 = new LatLng(31.7366645,35.5717301);
        PolygonOptions zonam = new PolygonOptions().add(p1, p2, p3, p4,p5).strokeWidth(5).strokeColor(Color.argb(100, 0, 255,0 )).fillColor(Color.argb(50, 0, 255,0 ));

        googleMap.addPolygon(zonam);

        Polygon polygonMuer = mMap.addPolygon(zonam);
        polygonMuer.setTag("Zona 3");
        polygonMuer.setClickable(true);

        //-----------------------------------------------------------------------
        //ZONA 4
        p1 = new LatLng(45.8976976,30.829275);
        p2 = new LatLng(42.2413829,28.3443241);
        p3 = new LatLng(42.2438319,34.0893151);
        p4 = new LatLng(41.473398,39.3170011);
        p5 = new LatLng(44.7853808,36.9592651);
        LatLng p6 = new LatLng(44.7853808,36.9592651);
        PolygonOptions zonan = new PolygonOptions().add(p1, p2, p3, p4,p5,p6).strokeWidth(5).strokeColor(Color.argb(100, 0, 255,0 )).fillColor(Color.argb(50, 0, 255,0 ));

        googleMap.addPolygon(zonan);

        Polygon polygonNegr = mMap.addPolygon(zonan);
        polygonNegr.setTag("Zona 4");
        polygonNegr.setClickable(true);

        //-----------------------------------------------------------------------
        //ZONA 5
        p1 = new LatLng(27.1712907,34.0769491);
        p2 = new LatLng(27.8664967,35.1607181);
        p3 = new LatLng(17.7282221,41.7809541);
        p4 = new LatLng(13.3527472,43.0056311);
        p5 = new LatLng(15.8863821,40.2927481);
        PolygonOptions zonar = new PolygonOptions().add(p1, p2, p3, p4,p5).strokeWidth(5).strokeColor(Color.argb(100, 255, 0,0 )).fillColor(Color.argb(50, 255, 0,0 ));

        googleMap.addPolygon(zonar);

        Polygon polygonRojo = mMap.addPolygon(zonar);
        polygonRojo.setTag("Zona 5");
        polygonRojo.setClickable(true);

        mMap.setOnPolygonClickListener(new GoogleMap.OnPolygonClickListener() {
            @Override
            public void onPolygonClick(Polygon polygon) {
                // Obtén la etiqueta o identificador del polígono clicado
                String tag = (String) polygon.getTag();

                // Realiza acciones diferentes según la etiqueta o identificador
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

                }
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
                        System.out.println("Hugo mira aqui: ");
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