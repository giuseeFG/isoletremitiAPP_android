package app.activity;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.provider.TrovaGPS;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.RadioButton;
import app.entity.PoiBean;
import app.isole.R;
import app.realtaAumentata.DataSource;

public class MappaActivity extends FragmentActivity {
	final Context context = this;
	private GoogleMapOptions options = new GoogleMapOptions();
	private double lat, lng;
	@SuppressWarnings("unused")
	private GoogleMap mMap;
	private GoogleMap googleMap;
	private SupportMapFragment mMapFragment;
	private List<PoiBean> listaPoiBean = new ArrayList<PoiBean>();
	private Map<String, LatLng> mappaCale = new HashMap<String, LatLng>();
	private boolean avvisoUscito = false;

	public PoiBean getListaPoiBean(String name) {
		PoiBean poi = null;
		for (PoiBean pb : this.listaPoiBean)
			if (pb.getName().equals(name))
				poi = pb;
		return poi;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.mappa);
		final RadioButton alberghi = (RadioButton)findViewById(R.id.radioButtonAlberghi);
		final RadioButton attivita = (RadioButton)findViewById(R.id.radioButtonAttivita);
		final RadioButton barLocali = (RadioButton)findViewById(R.id.radioButtonBarLocali);
		final CheckBox cale = (CheckBox)findViewById(R.id.checkBoxMostraCale);
		final CheckBox zone = (CheckBox)findViewById(R.id.checkBoxMostraZone);
		TextView seleziona = (TextView)findViewById(R.id.seleziona);
		final ImageButton imageHelp = (ImageButton)findViewById(R.id.imageHelp);

		Typeface fontSeleziona = Typeface.createFromAsset(getAssets(),"fonts/eurof75bold.ttf");
		seleziona.setTypeface(fontSeleziona);
		Typeface fontScritte = Typeface.createFromAsset(getAssets(),"fonts/eurof35.ttf"); 
		alberghi.setTypeface(fontScritte);
		attivita.setTypeface(fontScritte);
		barLocali.setTypeface(fontScritte);
		cale.setTypeface(fontScritte);
		zone.setTypeface(fontScritte);
		final Dialog dialog = new Dialog(context);

		popolaListaCale();

		imageHelp.setVisibility(View.GONE);

		imageHelp.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				dialog.setCancelable(true);
				dialog.setContentView(R.layout.dialog_legenda);
				dialog.setTitle("Legenda:");
				dialog.show();
				final ImageView moreInfoZonaA = (ImageView)dialog.findViewById(R.id.moreInfoZonaA);
				final ImageView moreInfoZonaB = (ImageView)dialog.findViewById(R.id.moreInfoZonaB);
				final ImageView moreInfoZonaC = (ImageView)dialog.findViewById(R.id.moreInfoZonaC);

				moreInfoZonaA.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View arg0) {
						Dialog dialogZonaA = new Dialog(MappaActivity.this);
						dialogZonaA.setCancelable(true);
						dialogZonaA.setContentView(R.layout.dialog_moreinfozona_a);
						dialogZonaA.setTitle("Zona A:");
						dialogZonaA.show();
					}
				});
				moreInfoZonaB.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View arg0) {
						Dialog dialogZonaB = new Dialog(MappaActivity.this);
						dialogZonaB.setCancelable(true);
						dialogZonaB.setContentView(R.layout.dialog_moreinfozona_b);
						dialogZonaB.setTitle("Zona B:");
						dialogZonaB.show();
					}
				});
				moreInfoZonaC.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View arg0) {
						Dialog dialogZonaC = new Dialog(MappaActivity.this);
						dialogZonaC.setCancelable(true);
						dialogZonaC.setContentView(R.layout.dialog_moreinfozona_c);
						dialogZonaC.setTitle("Zona C:");
						dialogZonaC.show();
					}
				});
			}
		});

		zone.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton cb, boolean arg1) {
				if (cb.isChecked()) {
					visualizzaZoneRiservateSullaMappa();
					if (avvisoUscito == false)
						mostraToastInformativo();
					imageHelp.setVisibility(View.VISIBLE);
				}
				else {
					imageHelp.setVisibility(View.GONE);
					googleMap.clear();
					cale.setChecked(false);
					alberghi.setChecked(false);
					attivita.setChecked(false);
					barLocali.setChecked(false);
				}
			}
		});

		cale.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton cb, boolean arg1) {
				if (cb.isChecked())
					visualizzaCaleSullaMappa();
				else {
					googleMap.clear();
					zone.setChecked(false);
					alberghi.setChecked(false);
					attivita.setChecked(false);
					barLocali.setChecked(false);

				}
			}
		});
		alberghi.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				attivita.setChecked(false);
				barLocali.setChecked(false);
				cale.setChecked(false);
				alberghi.setChecked(true);
				googleMap.clear();
				visualizzaAlberghiSullaMappa();
			}
		});
		attivita.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				attivita.setChecked(true);
				alberghi.setChecked(false);
				barLocali.setChecked(false);
				cale.setChecked(false);
				googleMap.clear();
				visualizzaAttivitaSullaMappa();
			}
		});
		barLocali.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				barLocali.setChecked(true);
				attivita.setChecked(false);
				alberghi.setChecked(false);
				cale.setChecked(false);
				googleMap.clear();
				visualizzaBarELocaliSullaMappa();
			}
		});

		DataSource dataSource = new DataSource();
		InputStream stream = getResources().openRawResource(R.raw.db_tremiti);
		try {
			this.listaPoiBean = dataSource.getPoisByURL(stream);
		} catch (Exception e) {
			e.printStackTrace();
		}
		TrovaGPS gps = new TrovaGPS(getApplicationContext());
		this.lat = gps.getLatitude();
		this.lng = gps.getLongitude();
		options.mapType(GoogleMap.MAP_TYPE_NORMAL).compassEnabled(true).rotateGesturesEnabled(true).tiltGesturesEnabled(true);
		SupportMapFragment.newInstance(options);
		mMap = ((SupportMapFragment)  getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
		mMapFragment = SupportMapFragment.newInstance();
		FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
		fragmentTransaction.add(R.id.map, mMapFragment);
		fragmentTransaction.commit();
	}

	protected void mostraToastInformativo() {
		Toast.makeText(this, "Clicca sul punto interrogativo per avere più informazioni sulle zone riservate.", Toast.LENGTH_LONG).show();
		this.avvisoUscito = true;
	}

	protected void visualizzaZoneRiservateSullaMappa() {
		PolylineOptions polylineBueMarino = new PolylineOptions();
		PolylineOptions polylineCapraia = new PolylineOptions();
		PolylineOptions polylineZonaC_nord = new PolylineOptions();
		PolylineOptions polylineZonaC_sud = new PolylineOptions();
		PolylineOptions polylineZonaA = new PolylineOptions();

		//Zona B: Bue Marino
		LatLng zonaB_1 = new LatLng(42.106658,15.476375);
		LatLng zonaB_2 = new LatLng(42.106485,15.468996);
		LatLng zonaB_3 = new LatLng(42.112319,15.469831);
		LatLng zonaB_4 = new LatLng(42.112286,15.474801);

		//Zona C: limiti nord
		LatLng zonaC_1 = new LatLng(42.125604,15.471346);
		LatLng zonaC_2 = new LatLng(42.138489,15.49981);

		//Zona C: limiti sud
		LatLng zonaC_3 = new LatLng(42.092246,15.483148);
		LatLng zonaC_4 = new LatLng(42.097205,15.468197);

		//Zona B: Capraia
		LatLng zonaB_Capraia_1 = new LatLng(42.142342,15.507977);
		LatLng zonaB_Capraia_2 = new LatLng(42.132621,15.507615);
		LatLng zonaB_Capraia_3 = new LatLng(42.140768,15.533115);
		LatLng zonaB_Capraia_4 = new LatLng(42.125653,15.524261);
		LatLng zonaB_Capraia_5 = new LatLng(42.133837,15.513756);

		//Zona A: Pianosa
		LatLng zonaA_1 = new LatLng(42.215694,15.749824);
		LatLng zonaA_2 = new LatLng(42.217305,15.73648);
		LatLng zonaA_3 = new LatLng(42.225663,15.733167);
		LatLng zonaA_4 = new LatLng(42.22902,15.741565);
		LatLng zonaA_5 = new LatLng(42.227387,15.755333);

		//Scritte
		polylineZonaA.color(Color.RED);
		polylineZonaA.add(zonaA_1, zonaA_2, zonaA_3, zonaA_4, zonaA_5, zonaA_1);

		polylineBueMarino.color(Color.YELLOW);
		polylineBueMarino.add(zonaB_1, zonaB_2, zonaB_3, zonaB_4);

		polylineCapraia.color(Color.YELLOW);
		polylineCapraia.add(zonaB_Capraia_2, zonaC_2, zonaB_Capraia_1, zonaB_Capraia_3, zonaB_Capraia_4, zonaB_Capraia_5);

		polylineZonaC_nord.color(Color.GREEN);
		polylineZonaC_nord.add(zonaB_3, zonaC_1, zonaC_2);

		polylineZonaC_sud.color(Color.GREEN);
		polylineZonaC_sud.add(zonaB_Capraia_4, zonaC_3, zonaC_4, zonaB_2);

		googleMap.addPolyline(polylineBueMarino);
		googleMap.addPolyline(polylineCapraia);
		googleMap.addPolyline(polylineZonaC_nord);
		googleMap.addPolyline(polylineZonaC_sud);
		googleMap.addPolyline(polylineZonaA);

		MarkerOptions marker1 = new MarkerOptions();
		MarkerOptions marker2 = new MarkerOptions();
		MarkerOptions marker3 = new MarkerOptions();
		MarkerOptions marker4 = new MarkerOptions();
		MarkerOptions marker5 = new MarkerOptions();
		MarkerOptions marker6 = new MarkerOptions();
		MarkerOptions marker7 = new MarkerOptions();
		MarkerOptions marker8 = new MarkerOptions();
		MarkerOptions marker9 = new MarkerOptions();
		MarkerOptions marker10 = new MarkerOptions();
		MarkerOptions marker11 = new MarkerOptions();
		MarkerOptions marker12 = new MarkerOptions();
		MarkerOptions marker13 = new MarkerOptions();
		MarkerOptions marker14 = new MarkerOptions();

		marker1.position(zonaB_2).icon(BitmapDescriptorFactory.fromResource(R.drawable.boa));
		marker2.position(zonaB_3).icon(BitmapDescriptorFactory.fromResource(R.drawable.boa));
		marker3.position(zonaC_1).icon(BitmapDescriptorFactory.fromResource(R.drawable.boa));
		marker4.position(zonaC_2).icon(BitmapDescriptorFactory.fromResource(R.drawable.boa));
		marker5.position(zonaB_Capraia_1).icon(BitmapDescriptorFactory.fromResource(R.drawable.boa));
		marker6.position(zonaB_Capraia_3).icon(BitmapDescriptorFactory.fromResource(R.drawable.boa));
		marker7.position(zonaB_Capraia_4).icon(BitmapDescriptorFactory.fromResource(R.drawable.boa));
		marker8.position(zonaC_3).icon(BitmapDescriptorFactory.fromResource(R.drawable.boa));
		marker9.position(zonaC_4).icon(BitmapDescriptorFactory.fromResource(R.drawable.boa));
		marker10.position(zonaA_1).icon(BitmapDescriptorFactory.fromResource(R.drawable.boa));
		marker11.position(zonaA_2).icon(BitmapDescriptorFactory.fromResource(R.drawable.boa));
		marker12.position(zonaA_3).icon(BitmapDescriptorFactory.fromResource(R.drawable.boa));
		marker13.position(zonaA_4).icon(BitmapDescriptorFactory.fromResource(R.drawable.boa));
		marker14.position(zonaA_5).icon(BitmapDescriptorFactory.fromResource(R.drawable.boa));

		googleMap.addMarker(marker1);
		googleMap.addMarker(marker2);
		googleMap.addMarker(marker3);
		googleMap.addMarker(marker4);
		googleMap.addMarker(marker5);
		googleMap.addMarker(marker6);
		googleMap.addMarker(marker7);
		googleMap.addMarker(marker8);
		googleMap.addMarker(marker9);
		googleMap.addMarker(marker10);
		googleMap.addMarker(marker11);
		googleMap.addMarker(marker12);
		googleMap.addMarker(marker13);
		googleMap.addMarker(marker14);
	}

	private void popolaListaCale() {
		LatLng l1 = new LatLng(42.119784, 15.496997);
		this.mappaCale.put("Punta della Toppa", l1);
		LatLng l2 = new LatLng(42.119649, 15.496203);
		this.mappaCale.put("Cala delle Arene", l2);
		LatLng l4 = new LatLng(42.116872, 15.494455);
		this.mappaCale.put("Cala dello Spido", l4);
		LatLng l6 = new LatLng(42.115399,15.493487);
		this.mappaCale.put("Cala Matano", l6);
		LatLng l8 = new LatLng(42.111627, 15.491771);
		this.mappaCale.put("Cala del Pigno", l8);
		LatLng l9 = new LatLng(42.110529, 15.492178);
		this.mappaCale.put("Scoglio dell'Elefante", l9);
		LatLng l10 = new LatLng(42.108953, 15.490355);
		this.mappaCale.put("Grotta del Sale", l10);
		LatLng l11 = new LatLng(42.107997,15.487393);
		this.mappaCale.put("Cala del Sale", l11);
		LatLng l12 = new LatLng(42.106477,15.485872);
		this.mappaCale.put("Cala delle Rosselle", l12);
		LatLng l13 = new LatLng(42.105506,15.484397);
		this.mappaCale.put("Grotta delle Viole", l13);
		LatLng l14 = new LatLng(42.104646,15.483157);
		this.mappaCale.put("Cala di Zio Cesare", l14);
		LatLng l15 = new LatLng(42.103715,15.481972);
		this.mappaCale.put("Punta di Ponente", l15);
		LatLng l16 = new LatLng(42.106581,15.47628);
		this.mappaCale.put("Punta del Diavolo", l16);
		LatLng l17 = new LatLng(42.107209,15.476237);
		this.mappaCale.put("Punta della Provvidenza", l17);
		LatLng l18 = new LatLng(42.108021, 15.477943);
		this.mappaCale.put("Grotta del Bue Marino", l18);
		LatLng l19 = new LatLng(42.111436, 15.476259);
		this.mappaCale.put("Architiello di San Domino", l19);
		LatLng l20 = new LatLng(42.112566, 15.475218);
		this.mappaCale.put("Punta Secca di San Domino", l20);
		LatLng l21 = new LatLng(42.113286,15.477686);
		this.mappaCale.put("Cala Rossa", l21);
		LatLng l22 = new LatLng(42.113931,15.479017);
		this.mappaCale.put("Grotta delle Rondinelle", l22);
		LatLng l23 = new LatLng(42.117142,15.48305);
		this.mappaCale.put("Cala dei Benedettini", l23);
		LatLng l24 = new LatLng(42.118062,15.482568);
		this.mappaCale.put("Punta del Vapore", l24);
		LatLng l25 = new LatLng(42.117898,15.484175);
		this.mappaCale.put("Cala degli Inglesi", l25);
		LatLng l26 = new LatLng(42.119681,15.481793);
		this.mappaCale.put("Punta del Vuccolo", l26);
		LatLng l27 = new LatLng(42.120063,15.485655);
		this.mappaCale.put("Cala Tramontana", l27);
		LatLng l28 = new LatLng(42.121432,15.486921);
		this.mappaCale.put("Cala Tonda", l28);
		LatLng l29 = new LatLng(42.124448,15.487406);
		this.mappaCale.put("Punta del Coccodrillo", l29);
		LatLng l30 = new LatLng(42.124074,15.489131);
		this.mappaCale.put("Cala Tamariello", l30);
		LatLng l31 = new LatLng(42.125936, 15.490912);
		this.mappaCale.put("Punta del Diamante", l31);
		LatLng l32 = new LatLng(42.121702, 15.494603);
		this.mappaCale.put("I Pagliai", l32);

		// fine San Domino

		LatLng l33 = new LatLng(42.123712,15.506928);
		this.mappaCale.put("Grotta della Madonna", l33);
		LatLng l34 = new LatLng(42.123318, 15.513284);
		this.mappaCale.put("Scoglio Segato", l34);
		LatLng l35 = new LatLng(42.127965, 15.517414);
		this.mappaCale.put("Punta del Camposanto", l35);

		// fine San Nicola

		LatLng l36 = new LatLng(42.129524, 15.508295);
		this.mappaCale.put("Punta della Stracciona", l36);
		LatLng l37 = new LatLng(42.130201,15.507309);
		this.mappaCale.put("Cala della Stracciona", l37);
		LatLng l38 = new LatLng(42.131307,15.507125);
		this.mappaCale.put("Scoglio della Cernia", l38);
		LatLng l39 = new LatLng(42.131852,15.507486);
		this.mappaCale.put("Cala Pietra del Fucile", l39);
		LatLng l40 = new LatLng(42.132389,15.507587);
		this.mappaCale.put("Cala Sorrentino", l40);
		LatLng l41 = new LatLng(42.134728,15.50734);
		this.mappaCale.put("Grotta delle Vedove", l41);
		LatLng l42 = new LatLng(42.134959,15.510205);
		this.mappaCale.put("Cala dei Turchi", l42);
		LatLng l43 = new LatLng(42.138777,15.522259);
		this.mappaCale.put("Architiello di Capraia", l43);
		LatLng l44 = new LatLng(42.138519,15.521573);
		this.mappaCale.put("Il Grottone", l44);
		LatLng l45 = new LatLng(42.138777, 15.523605);
		this.mappaCale.put("Punta Secca di Capraia", l45);
		LatLng l46 = new LatLng(42.137906,15.519003);
		this.mappaCale.put("Cala dei Vermi", l46);
		LatLng l47 = new LatLng(42.133749, 15.513026);
		this.mappaCale.put("Gli Scoglietti", l47);
		LatLng l48 = new LatLng(42.132492, 15.512565);
		this.mappaCale.put("Statua di Padre Pio Sommersa", l48);
		LatLng l49 = new LatLng(42.137632,15.517777);
		this.mappaCale.put("Cala dei Pesci", l49);

		// fine Capraia
	}

	protected void visualizzaCaleSullaMappa() {
		for (String s : this.mappaCale.keySet()) {
			if (s.equals("Cala Matano")) {
				googleMap.addMarker(new MarkerOptions().position(this.mappaCale.get(s)).title(s).snippet("Clicca per più dettagli...").icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_calamatano)));
				googleMap.setOnInfoWindowClickListener(new OnInfoWindowClickListener() {
					@Override
					public void onInfoWindowClick(Marker marker) {
						startImmagineIngranditaActivity(marker);
					}
				});
			}
			else if (s.equals("Scoglio dell'Elefante")) {
				googleMap.addMarker(new MarkerOptions().position(this.mappaCale.get(s)).title(s).snippet("Clicca per più dettagli...").icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_elefante)));
				googleMap.setOnInfoWindowClickListener(new OnInfoWindowClickListener() {
					@Override
					public void onInfoWindowClick(Marker marker) {
						startImmagineIngranditaActivity(marker);
					}
				});
			}

			else if (s.equals("Cala dello Spido")) {
				googleMap.addMarker(new MarkerOptions().position(this.mappaCale.get(s)).title(s).snippet("Clicca per più dettagli...").icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_calaspido)));
				googleMap.setOnInfoWindowClickListener(new OnInfoWindowClickListener() {
					@Override
					public void onInfoWindowClick(Marker marker) {
						startImmagineIngranditaActivity(marker);
					}
				});
			}
			else if (s.equals("Cala del Sale")) {
				googleMap.addMarker(new MarkerOptions().position(this.mappaCale.get(s)).title(s).snippet("Clicca per più dettagli...").icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_caladelsale)));
				googleMap.setOnInfoWindowClickListener(new OnInfoWindowClickListener() {
					@Override
					public void onInfoWindowClick(Marker marker) {
						startImmagineIngranditaActivity(marker);
					}
				});
			}
			else if (s.equals("Cala delle Roselle")) {
				googleMap.addMarker(new MarkerOptions().position(this.mappaCale.get(s)).title(s).snippet("Clicca per più dettagli...").icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_calaroselle)));
				googleMap.setOnInfoWindowClickListener(new OnInfoWindowClickListener() {
					@Override
					public void onInfoWindowClick(Marker marker) {
						startImmagineIngranditaActivity(marker);
					}
				});
			}
			else if (s.equals("Grotta delle Viole")) {
				googleMap.addMarker(new MarkerOptions().position(this.mappaCale.get(s)).title(s).snippet("Clicca per più dettagli...").icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_grottadelleviole)));
				googleMap.setOnInfoWindowClickListener(new OnInfoWindowClickListener() {
					@Override
					public void onInfoWindowClick(Marker marker) {
						startImmagineIngranditaActivity(marker);
					}
				});
			}

			else if (s.equals("Cala di Zio Cesare")) {
				googleMap.addMarker(new MarkerOptions().position(this.mappaCale.get(s)).title(s).snippet("Clicca per più dettagli...").icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_calaziocesare)));
				googleMap.setOnInfoWindowClickListener(new OnInfoWindowClickListener() {
					@Override
					public void onInfoWindowClick(Marker marker) {
						startImmagineIngranditaActivity(marker);
					}
				});
			}
			else if (s.equals("Punta di Ponente")) {
				googleMap.addMarker(new MarkerOptions().position(this.mappaCale.get(s)).title(s).snippet("Clicca per più dettagli...").icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_puntaponente)));
				googleMap.setOnInfoWindowClickListener(new OnInfoWindowClickListener() {
					@Override
					public void onInfoWindowClick(Marker marker) {
						startImmagineIngranditaActivity(marker);
					}
				});
			}
			else if (s.equals("Punta della Provvidenza")) {
				googleMap.addMarker(new MarkerOptions().position(this.mappaCale.get(s)).title(s).snippet("Clicca per più dettagli...").icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_puntadellaprovvidenza)));
				googleMap.setOnInfoWindowClickListener(new OnInfoWindowClickListener() {
					@Override
					public void onInfoWindowClick(Marker marker) {
						startImmagineIngranditaActivity(marker);
					}
				});
			}
			else if (s.equals("Grotta del Bue Marino")) {
				googleMap.addMarker(new MarkerOptions().position(this.mappaCale.get(s)).title(s).snippet("Clicca per più dettagli...").icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_buemarino)));
				googleMap.setOnInfoWindowClickListener(new OnInfoWindowClickListener() {
					@Override
					public void onInfoWindowClick(Marker marker) {
						startImmagineIngranditaActivity(marker);
					}
				});
			}
			else if (s.equals("Architiello di San Domino")) {
				googleMap.addMarker(new MarkerOptions().position(this.mappaCale.get(s)).title(s).snippet("Clicca per più dettagli...").icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_architiellosandomino)));
				googleMap.setOnInfoWindowClickListener(new OnInfoWindowClickListener() {
					@Override
					public void onInfoWindowClick(Marker marker) {
						startImmagineIngranditaActivity(marker);
					}
				});
			}
			else if (s.equals("Cala delle Arene")) {
				googleMap.addMarker(new MarkerOptions().position(this.mappaCale.get(s)).title(s).snippet("Clicca per più dettagli...").icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_caladellearene)));
				googleMap.setOnInfoWindowClickListener(new OnInfoWindowClickListener() {
					@Override
					public void onInfoWindowClick(Marker marker) {
						startImmagineIngranditaActivity(marker);
					}
				});
			}
			else if (s.equals("Punta Secca di San Domino")) {
				googleMap.addMarker(new MarkerOptions().position(this.mappaCale.get(s)).title(s).snippet("Clicca per più dettagli...").icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_puntaseccasandomino)));
				googleMap.setOnInfoWindowClickListener(new OnInfoWindowClickListener() {
					@Override
					public void onInfoWindowClick(Marker marker) {
						startImmagineIngranditaActivity(marker);
					}
				});
			}
			else if (s.equals("Grotta delle Rondinelle")) {
				googleMap.addMarker(new MarkerOptions().position(this.mappaCale.get(s)).title(s).snippet("Clicca per più dettagli...").icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_grottadellerondinelle)));
				googleMap.setOnInfoWindowClickListener(new OnInfoWindowClickListener() {
					@Override
					public void onInfoWindowClick(Marker marker) {
						startImmagineIngranditaActivity(marker);
					}
				});
			}
			else if (s.equals("Cala degli Inglesi")) {
				googleMap.addMarker(new MarkerOptions().position(this.mappaCale.get(s)).title(s).snippet("Clicca per più dettagli...").icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_calaingelsi)));
				googleMap.setOnInfoWindowClickListener(new OnInfoWindowClickListener() {
					@Override
					public void onInfoWindowClick(Marker marker) {
						startImmagineIngranditaActivity(marker);
					}
				});
			}
			else if (s.equals("Cala dei Benedettini")) {
				googleMap.addMarker(new MarkerOptions().position(this.mappaCale.get(s)).title(s).snippet("Clicca per più dettagli...").icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_caladeibenedettini)));
				googleMap.setOnInfoWindowClickListener(new OnInfoWindowClickListener() {
					@Override
					public void onInfoWindowClick(Marker marker) {
						startImmagineIngranditaActivity(marker);
					}
				});
			}
			else if (s.equals("Cala Tramontana")) {
				googleMap.addMarker(new MarkerOptions().position(this.mappaCale.get(s)).title(s).snippet("Clicca per più dettagli...").icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_calatramontana)));
				googleMap.setOnInfoWindowClickListener(new OnInfoWindowClickListener() {
					@Override
					public void onInfoWindowClick(Marker marker) {
						startImmagineIngranditaActivity(marker);
					}
				});
			}
			else if (s.equals("Cala Tonda")) {
				googleMap.addMarker(new MarkerOptions().position(this.mappaCale.get(s)).title(s).snippet("Clicca per più dettagli...").icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_calatonda)));
				googleMap.setOnInfoWindowClickListener(new OnInfoWindowClickListener() {
					@Override
					public void onInfoWindowClick(Marker marker) {
						startImmagineIngranditaActivity(marker);
					}
				});
			}
			else if (s.equals("Cala Tamariello")) {
				googleMap.addMarker(new MarkerOptions().position(this.mappaCale.get(s)).title(s).snippet("Clicca per più dettagli...").icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_calatamariello)));
				googleMap.setOnInfoWindowClickListener(new OnInfoWindowClickListener() {
					@Override
					public void onInfoWindowClick(Marker marker) {
						startImmagineIngranditaActivity(marker);
					}
				});
			}
			else if (s.equals("I Pagliai")) {
				googleMap.addMarker(new MarkerOptions().position(this.mappaCale.get(s)).title(s).snippet("Clicca per più dettagli...").icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_pagliai)));
				googleMap.setOnInfoWindowClickListener(new OnInfoWindowClickListener() {
					@Override
					public void onInfoWindowClick(Marker marker) {
						startImmagineIngranditaActivity(marker);
					}
				});
			}
			else if (s.equals("Cala Pietra del Fucile")) {
				googleMap.addMarker(new MarkerOptions().position(this.mappaCale.get(s)).title(s).snippet("Clicca per più dettagli...").icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_pietrafucili)));
				googleMap.setOnInfoWindowClickListener(new OnInfoWindowClickListener() {
					@Override
					public void onInfoWindowClick(Marker marker) {
						startImmagineIngranditaActivity(marker);
					}
				});
			}
			else if (s.equals("Cala dei Turchi")) {
				googleMap.addMarker(new MarkerOptions().position(this.mappaCale.get(s)).title(s).snippet("Clicca per più dettagli...").icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_caladeiturchi)));
				googleMap.setOnInfoWindowClickListener(new OnInfoWindowClickListener() {
					@Override
					public void onInfoWindowClick(Marker marker) {
						startImmagineIngranditaActivity(marker);
					}
				});
			}
			else if (s.equals("Architiello di Capraia")) {
				googleMap.addMarker(new MarkerOptions().position(this.mappaCale.get(s)).title(s).snippet("Clicca per più dettagli...").icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_architiellocapraia)));
				googleMap.setOnInfoWindowClickListener(new OnInfoWindowClickListener() {
					@Override
					public void onInfoWindowClick(Marker marker) {
						startImmagineIngranditaActivity(marker);
					}
				});
			}
			else if (s.equals("Statua di Padre Pio Sommersa")) {
				googleMap.addMarker(new MarkerOptions().position(this.mappaCale.get(s)).title(s).snippet("Clicca per più dettagli...").icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_padrepio)));
				googleMap.setOnInfoWindowClickListener(new OnInfoWindowClickListener() {
					@Override
					public void onInfoWindowClick(Marker marker) {
						startImmagineIngranditaActivity(marker);
					}
				});
			}
			else {
				googleMap.addMarker(new MarkerOptions().position(this.mappaCale.get(s)).title(s).snippet("Nessun dettaglio disponibile.").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
				googleMap.setOnInfoWindowClickListener(new OnInfoWindowClickListener() {
					@Override
					public void onInfoWindowClick(Marker marker) {
						startImmagineIngranditaActivity(marker);
					}
				});
			}
		}
	}

	protected void visualizzaAttivitaSullaMappa() {
		for (PoiBean pb : this.listaPoiBean) {
			if (pb.getType().contains("Attivita")) {
				LatLng latLngPOI = new LatLng(pb.getLat(), pb.getLng());
				MarkerOptions marker = new MarkerOptions();
				marker.position(latLngPOI).title(pb.getName()).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
				googleMap.addMarker(marker);
				googleMap.setOnInfoWindowClickListener(new OnInfoWindowClickListener() {

					@Override
					public void onInfoWindowClick(Marker marker) {
						startDettagliAttivita(marker);
					}
				});
			}
		}
		LatLng zoomMappa = new LatLng(42.115097, 15.48965);
		googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(zoomMappa, 15));
	}
	protected void startImmagineIngranditaActivity(Marker marker) {
		Intent intent = new Intent(this, ImmagineIngranditaActivity.class);
		intent.putExtra("nome marker", marker.getTitle());
		startActivity(intent);
	}

	protected void startDettagliAttivita(Marker marker) {
		Intent intent = new Intent(this, DettagliAttivitaVarieActivity.class);
		intent.putExtra("POI_SELEZIONATO", this.getListaPoiBean(marker.getTitle()));
		startActivity(intent);		
	}
	protected void visualizzaAlberghiSullaMappa() {
		for (PoiBean pb : this.listaPoiBean) {
			if (pb.getType().contains("Albergo")) {
				LatLng latLngPOI = new LatLng(pb.getLat(), pb.getLng());
				MarkerOptions marker = new MarkerOptions();
				marker.position(latLngPOI).title(pb.getName()).icon(BitmapDescriptorFactory.fromResource(R.drawable.iconaletto));
				googleMap.addMarker(marker);
				googleMap.setOnInfoWindowClickListener(new OnInfoWindowClickListener() {

					@Override
					public void onInfoWindowClick(Marker marker) {
						startDettagliAlbergo(marker);

					}
				});

			}
		}
		LatLng zoomMappa = new LatLng(42.115097, 15.48965);
		googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(zoomMappa, 15));
	}
	protected void startDettagliAlbergo(Marker marker) {
		Intent intent = new Intent(this, DettagliHotelActivity.class);
		intent.putExtra("POI_SELEZIONATO", this.getListaPoiBean(marker.getTitle()));
		startActivity(intent);	

	}
	protected void visualizzaBarELocaliSullaMappa() {
		for (PoiBean pb : this.listaPoiBean) {
			if (pb.getType().contains("Bar")) {
				LatLng latLngPOI = new LatLng(pb.getLat(), pb.getLng());
				googleMap.addMarker(new MarkerOptions().position(latLngPOI).title(pb.getName()).icon(BitmapDescriptorFactory.fromResource(R.drawable.iconabar)));
				googleMap.setOnInfoWindowClickListener(new OnInfoWindowClickListener() {
					@Override
					public void onInfoWindowClick(Marker marker) {
						startDettagliBar(marker);
					}
				});
			}
		}
		LatLng zoomMappa = new LatLng(42.115097, 15.48965);
		googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(zoomMappa, 15));
	}
	protected void startDettagliBar(Marker marker) {
		Intent intent = new Intent(this, DettagliBarActivity.class);
		intent.putExtra("POI_SELEZIONATO", this.getListaPoiBean(marker.getTitle()));
		startActivity(intent);	

	}
	@Override
	public void onAttachedToWindow() {
		googleMap = (mMapFragment).getMap();
		LatLng latLngUSER = new LatLng(lat, lng);
		LatLng latLngTREMITI = new LatLng(42.126493, 15.497417);
		googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
		googleMap.addMarker(new MarkerOptions().position(latLngUSER).title("io").icon(BitmapDescriptorFactory.fromResource(R.drawable.markergps)));
		googleMap.getUiSettings().setCompassEnabled(true);
		googleMap.getUiSettings().setZoomControlsEnabled(true);
		googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLngTREMITI, 13));
		super.onAttachedToWindow();
	}
}
