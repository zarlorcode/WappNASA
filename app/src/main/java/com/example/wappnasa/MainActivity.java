package com.example.wappnasa;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

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

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMapClickListener, GoogleMap.OnMapLongClickListener {
    EditText txtDireccion;
    GoogleMap mMap;
    ImageView botonX;
    ImageView imagenPrincipal;
    ImageView imagenMapa;
    RelativeLayout popup;
    TextView nombre;
    TextView estado;
    TextView descripcion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtDireccion = findViewById(R.id.txtDireccion);
        botonX = findViewById(R.id.botonX);
        popup = findViewById(R.id.popup);
        imagenPrincipal = findViewById(R.id.imagenPrincipal);
        //imagenMapa = findViewById(R.id.imagenMapa);
        nombre = findViewById(R.id.nombre);
        estado = findViewById(R.id.estado);
        descripcion = findViewById(R.id.descripcion);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

       botonX.setImageDrawable(getResources().getDrawable(R.drawable.cross));
        imagenPrincipal.setImageDrawable(getResources().getDrawable(R.drawable.cross));
        //imagenMapa.setImageDrawable(getResources().getDrawable(R.drawable.cross));
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap= googleMap;
        this.mMap.setOnMapClickListener(this);
        this.mMap.setOnMapLongClickListener(this);

        //MOVE CAMERA TO SPAIN
        LatLng spain = new LatLng(40.33619115953262, -3.767719012949285);
        mMap.addMarker(new MarkerOptions().position(spain).title("Spain"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(spain));



        //Shagreen Skate
        LatLng shangreen = new LatLng(57.2567039, 0.6946521);
        MarkerOptions markerShangreen = new MarkerOptions();
        markerShangreen.position(shangreen);
        markerShangreen.title("Shagreen Skate");
        markerShangreen.snippet("El Shagreen Skate es una especie de raya que se encuentra en el océano Atlántico " +
                "nororiental. Es conocida por su piel rugosa, llamada shagreen, y su aspecto peculiar. Está " +
                "adaptada a vivir en aguas profundas y se alimenta de peces y otros organismos marinos. Aunque no es" +
                " ampliamente conocida, su conservación es importante debido a la vulnerabilidad de muchas especies de" +
                " rayas en todo el mundo.");
        markerShangreen.icon(BitmapDescriptorFactory.fromResource(R.drawable.peligroextincion));
        mMap.addMarker(markerShangreen);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(shangreen));

        //Basking Shark
        LatLng basking = new LatLng(33.2943895, 17.8180001);
        MarkerOptions markerBasking = new MarkerOptions();
        markerBasking.position(basking);
        markerBasking.title("Basking Shark");
        markerBasking.snippet("El Basking Shark es uno de los tiburones más grandes del mundo, llegando a medir hasta " +
                "12 metros de largo. Aunque parece imponente, se alimenta principalmente de plancton y es inofensivo para" +
                " los humanos. Se caracteriza por nadar lentamente cerca de la superficie del agua.");
        markerBasking.icon(BitmapDescriptorFactory.fromResource(R.drawable.peligroextincion));
        mMap.addMarker(markerBasking);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(basking));

        //Angel Shark
        LatLng angel = new LatLng(51.2485543, -6.9811829);
        MarkerOptions markerAngel = new MarkerOptions();
        markerAngel.position(angel);
        markerAngel.title("Angel Shark");
        markerAngel.snippet("El Angelshark es un tipo de tiburón aplanado que se asemeja a una raya. Vive en el fondo" +
                " marino y se camufla muy bien. Su cuerpo es ancho y plano, con aletas pectorales anchas que se asemejan" +
                " a alas de ángel, de ahí su nombre. Son depredadores sigilosos que esperan enterrados en la arena para" +
                " atrapar presas que pasan cerca. Aunque solían ser comunes, muchas especies de angelshark se consideran" +
                " en peligro debido a la pesca excesiva y la degradación del hábitat marino.");
        markerAngel.icon(BitmapDescriptorFactory.fromResource(R.drawable.iconopeligro2));
        //mMap.addMarker(markerAngel);
       // mMap.moveCamera(CameraUpdateFactory.newLatLng(angel));

        //Dusky Grouper
        LatLng dusky = new LatLng(39.4356891, 0.6393341);
        MarkerOptions markerDusky = new MarkerOptions();
        markerDusky.position(dusky);
        markerDusky.title("Angel Shark");
        markerDusky.snippet("El Dusky Grouper es un pez grande de color gris oscuro que se encuentra en el Atlántico oriental y" +
                " el Mediterráneo. Vive en hábitats rocosos y se alimenta de otros peces y crustáceos. Es valorado en la pesca" +
                " comercial y a menudo se implementan medidas de conservación debido a la disminución de sus poblaciones en" +
                " algunas áreas.");
        markerDusky.icon(BitmapDescriptorFactory.fromResource(R.drawable.peligroextincion));
        mMap.addMarker(markerDusky);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(dusky));

        //-----------------------------------------------------------------------
        //ZONA 1

        // Define los vértices del polígono
        LatLng p1 = new LatLng(41.26417100476725, 1.953193052956383);
        LatLng p2 = new LatLng(41.02885965406742, 0.9676577810986803);
        LatLng p3 = new LatLng(39.562252886971415, -0.18613961034447837);
        LatLng p4 = new LatLng(39.227888621023325, -0.18613961034447837);
        LatLng p5 = new LatLng(38.835774245922785, 0.27057185710177256);
        PolygonOptions zoneBalear = new PolygonOptions().add(p1, p2, p3, p4,p5).strokeWidth(6).strokeColor(Color.RED).fillColor(Color.argb(50, 255, 0,0 ));
        // Dibuja el polígono en el mapa
        googleMap.addPolygon(zoneBalear);

        // Configura un Listener para el polígono

        Polygon polygon = mMap.addPolygon(zoneBalear);
        polygon.setTag("Zona 1");

        // Configura un Listener para el polígono
        polygon.setClickable(true);


        //-----------------------------------------------------------------------

        mMap.setOnPolygonClickListener(new GoogleMap.OnPolygonClickListener() {
            @Override
            public void onPolygonClick(Polygon polygon) {
                // Obtén la etiqueta o identificador del polígono clicado
                String tag = (String) polygon.getTag();

                // Realiza acciones diferentes según la etiqueta o identificador
                if ("Zona 1".equals(tag)) {
                    // Acciones para la Zona 1
                    imagenPrincipal.setImageDrawable(getResources().getDrawable(R.drawable.elcabanyal));
                    //imagenMapa.setImageDrawable(getResources().getDrawable(R.drawable.elcabanyal));

                    nombre.setText("Mar Balear");
                    estado.setText(Html.fromHtml("<b>Estado:</b> Moderadamente contaminada"));
                    descripcion.setText(Html.fromHtml("<b>Temperatura del agua:</b> verano 24-28 grados Celsius, invierno 12-15 grados Celsius.\n" +
                            "<br>" +
                            "<b>Salinidad:</b> alrededor de 38 partes por mil (ppt), es decir relativamente alta.\n" +
                            "<br>" +
                            "<b>Niveles de oxígeno disuelto:</b> Los valores suelen estar por encima de 5-6 miligramos por litro (mg/L). Tiene niveles saludables para la vida marina.\n" +
                            "<br>" +
                            "<b>Acidez del agua (pH):</b> alcalino, con valores típicos que varían de 7.8 a 8.4.\n" +
                            "<br>" +
                            "<b>Contaminación por plásticos:</b> El Mar Balear, al igual que otros mares, enfrenta problemas de contaminación por plásticos, especialmente en áreas costeras y cerca de rutas de navegación."));


                } else if ("Zona 2".equals(tag)) {

                } else if ("Zona 3".equals(tag)) {
                    // Acciones para la Zona 3
                }
                popup.setVisibility(View.VISIBLE);
            }
        });



        //Blue Shark Marker
        LatLng blueshark = new LatLng(36.034271, -6.169991);
        MarkerOptions markerBlueshark = new MarkerOptions();
        markerBlueshark.position(blueshark);
        markerBlueshark.title("Blue Shark");
        markerBlueshark.icon(BitmapDescriptorFactory.fromResource(R.drawable.iconopeligro2));
        mMap.addMarker(markerBlueshark);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(blueshark));

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                // Verificar qué marcador se hizo clic utilizando el título o una etiqueta personalizada
                if (marker.getTitle().equals("Blue Shark")) {
                    // Hacer algo para el Marcador 1
                    imagenPrincipal.setImageDrawable(getResources().getDrawable(R.drawable.blueshark));
                    //imagenMapa.setImageDrawable(getResources().getDrawable(R.drawable.elcabanyal));

                    nombre.setText("Blue Shark");
                    estado.setText(Html.fromHtml("<b>Estado:</b> Población decreciendo"));
                    descripcion.setText("El tiburón azul es un tiburón de color azul brillante que habita en océanos de todo el mundo. \" +\n" +
                            "\"Es conocido por ser rápido y ágil, se alimenta de peces y calamares, y desempeña un papel importante en \" +\n" +
                            "\"los ecosistemas marinos. Sin embargo, enfrenta amenazas debido a la pesca excesiva y la demanda de sus \" +\n" +
                            "\"aletas y carne. Está clasificado como una especie vulnerable. ");
                    // Por ejemplo, mostrar una ventana emergente específica o realizar una acción específica.
                } else if (marker.getTitle().equals("Marcador 2")) {
                    // Hacer algo para el Marcador 2
                } else if (marker.getTitle().equals("Marcador 3")) {
                    // Hacer algo para el Marcador 3
                }
                popup.setVisibility(View.VISIBLE);
                return false; // Devuelve 'true' si consumes el evento, 'false' si no.
            }
        });

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