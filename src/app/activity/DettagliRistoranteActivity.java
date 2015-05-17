package app.activity;

import java.util.List;

import android.content.Intent;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import app.adapter.InflateImage;
import app.db.MySQLiteHelper;
import app.entity.PoiBean;
import app.isole.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.provider.TrovaGPS;

public class DettagliRistoranteActivity extends FragmentActivity {
	@SuppressWarnings("unused")
	private String nome, web, tel, email, type, descrizione;
	private int id;
	private MySQLiteHelper db;
	private GoogleMapOptions options = new GoogleMapOptions();
	private double latitudePOI, longitudePOI, lat, lng;
	@SuppressWarnings("unused")
	private GoogleMap mMap;
	private GoogleMap googleMap;
	private SupportMapFragment mMapFragment;
	private PoiBean poiSelezionato;
	private List<PoiBean> listaTuttiPdi = HomeActivity.getListaRistoranti();
	private String valorePrecedente;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.dettagli_bar_ristorante_attivita);
		db = new MySQLiteHelper(getApplicationContext());
		this.valorePrecedente = getIntent().getStringExtra("PROVENIENZA");
		this.poiSelezionato = this.getIntent().getParcelableExtra("POI_SELEZIONATO");
		if (poiSelezionato.getLat() != 0) {
			TrovaGPS gps = new TrovaGPS(getApplicationContext());
			this.lat = gps.getLatitude();
			this.lng = gps.getLongitude();
		}
		ImageButton buttonGetPOILocation = (ImageButton)findViewById(R.id.buttonGetPOILocationSecond);
		ImageButton buttonGetCurrentLocation = (ImageButton)findViewById(R.id.buttonGetCurrentLocationSecond);
		FrameLayout mappaGoogle = (FrameLayout)findViewById(R.id.mappaGoogle);						//serve a rimuovere la mappa se il PDI non ha lat e lng
		LinearLayout layout = (LinearLayout)findViewById(R.id.layoutPadreBarAttivitaRistorante);	//serve a rimuovere la mappa se il PDI non ha lat e lng
		final Button bottoneAggiungiPreferiti = (Button)findViewById(R.id.buttonAggiungiPreferiti);
		final Button bottoneRimuoviPreferiti = (Button)findViewById(R.id.buttonRimuoviPreferiti);

		List<PoiBean> listaPDIpreferiti = db.getPOIPreferiti(listaTuttiPdi);

		if (listaPDIpreferiti.size() == 0) {
			bottoneRimuoviPreferiti.setVisibility(View.GONE);
			bottoneAggiungiPreferiti.setVisibility(View.VISIBLE);
		}

		else {
			bottoneAggiungiPreferiti.setVisibility(View.GONE);
			bottoneRimuoviPreferiti.setVisibility(View.GONE);
			boolean trovato = false;
			for (PoiBean pb : db.getPOIPreferiti(listaTuttiPdi)) {
				if (pb.getId() == this.poiSelezionato.getId() && !trovato) {
					bottoneAggiungiPreferiti.setVisibility(View.GONE);
					bottoneRimuoviPreferiti.setVisibility(View.VISIBLE);
					trovato = true;
				}
			}
			if (trovato == false) {
				bottoneAggiungiPreferiti.setVisibility(View.VISIBLE);
				bottoneRimuoviPreferiti.setVisibility(View.GONE);
			}
		}

		if (poiSelezionato.getLat() != 0) {
			options.mapType(GoogleMap.MAP_TYPE_NORMAL).compassEnabled(true).rotateGesturesEnabled(true).tiltGesturesEnabled(true);
			SupportMapFragment.newInstance(options);
			mMap = ((SupportMapFragment)  getSupportFragmentManager().findFragmentById(R.id.map2)).getMap();
			mMapFragment = SupportMapFragment.newInstance();
			android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
			fragmentTransaction.add(R.id.map2, mMapFragment);
			fragmentTransaction.commit();
		}
		else 
			layout.removeView(mappaGoogle);
		
		bottoneAggiungiPreferiti.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				aggiungiPreferito();
				bottoneAggiungiPreferiti.setVisibility(View.GONE);
				bottoneRimuoviPreferiti.setVisibility(View.VISIBLE);
			}
		});
		bottoneRimuoviPreferiti.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				rimuoviPreferito();
				bottoneAggiungiPreferiti.setVisibility(View.VISIBLE);
				bottoneRimuoviPreferiti.setVisibility(View.GONE);
			}
		});
		this.latitudePOI = poiSelezionato.getLat();
		this.longitudePOI = poiSelezionato.getLng();
		this.id = poiSelezionato.getId();
		this.nome = poiSelezionato.getName();
		this.web = poiSelezionato.getSitoWeb();
		this.tel = poiSelezionato.getTelefono();
		this.email = poiSelezionato.getEmail();
		this.type = poiSelezionato.getType();
		this.descrizione = poiSelezionato.getDescrizione();

		ImageView logo = (ImageView)findViewById(R.id.logo);
		InflateImage inflate = new InflateImage();
		inflate.inflateImageRistorante(nome, logo);

		TextView tvDescrizione = (TextView)findViewById(R.id.textViewDescrizione);
		TextView tvName = (TextView)findViewById(R.id.textViewNome);
		TextView tvWeb = (TextView)findViewById(R.id.textViewWeb);
		TextView tvTel = (TextView)findViewById(R.id.textViewTel);
		TextView tvEmail = (TextView)findViewById(R.id.textViewEmail);
		buttonGetCurrentLocation.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				moveCameraToCurrentLocation();
			}
		});
		buttonGetPOILocation.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				moveCameraToCurrentLocationPOI();
			}
		});
		tvName.setText(nome);
		Typeface type = Typeface.createFromAsset(getAssets(),"fonts/eurof35.ttf"); 
		tvName.setTypeface(type);
		if (this.descrizione.equals(""))
			tvDescrizione.setText("Descrizione per questo punto di interesse non ancora disponibile. :(");
		else
			tvDescrizione.setText(descrizione);
		Typeface typeDes = Typeface.createFromAsset(getAssets(),"fonts/eurof35.ttf"); 
		tvDescrizione.setTypeface(typeDes);
		tvTel.setPaintFlags(tvWeb.getPaintFlags() |   Paint.UNDERLINE_TEXT_FLAG);
		tvTel.setText(tel);


		if (!web.equals("nd")) {
			tvWeb.setPaintFlags(tvWeb.getPaintFlags() |   Paint.UNDERLINE_TEXT_FLAG);
			tvWeb.setText(web);
			tvWeb.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(web));
					startActivity(intent);
				}
			});
		}
		else {
			tvWeb.setText("Sito web non disponibile");
			tvWeb.setTextSize(15);
		}
		if (!email.equals("nd")) {
			tvEmail.setPaintFlags(tvWeb.getPaintFlags() |   Paint.UNDERLINE_TEXT_FLAG);
			tvEmail.setText(email);
		}
		else {
			tvEmail.setText("Indirizzo email non disponibile");
			tvEmail.setTextSize(15);
		}
		tvTel.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Uri number = Uri.parse("tel:" + tel);
				Intent dial = new Intent(Intent.ACTION_CALL, number);
				startActivity(dial);
			}
		});
		tvEmail.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				inviaEmail();
			}
		});
		TextView textDaRendereBoldTel = (TextView)findViewById(R.id.textDaRendereBoldTel);
		TextView textDaRendereBoldEmail = (TextView)findViewById(R.id.textDaRendereBoldEmail);
		TextView textDaRendereBoldWeb = (TextView)findViewById(R.id.textDaRendereBoldWeb);
		Typeface bold = Typeface.createFromAsset(getAssets(),"fonts/eurof75bold.ttf"); 
		textDaRendereBoldTel.setTypeface(bold);
		textDaRendereBoldEmail.setTypeface(bold);
		textDaRendereBoldWeb.setTypeface(bold);
	}
	protected void rimuoviPreferito() {
		db.deleteFavorite(id);
		db.close();
		Toast.makeText(this, "Rimosso dai preferiti.", Toast.LENGTH_LONG).show();
	}
	protected void aggiungiPreferito() {
		if (db.checkIfAlreadyExists(id)) {
			db.close();
			Toast.makeText(this, "Già è stato aggiunto.", Toast.LENGTH_LONG).show();
		}
		else {
			List<PoiBean> listaPoiRistoranti = HomeActivity.getListaRistoranti();
			for (PoiBean pb : listaPoiRistoranti)
				if (poiSelezionato.getId() == pb.getId()) {
					db.addFavorite(id);
					db.close();
					Toast.makeText(this, "Aggiunto ai preferiti.", Toast.LENGTH_LONG).show();
					return;
				}
		}
	}
	protected void moveCameraToCurrentLocationPOI() {
		LatLng latLngPOI = new LatLng(latitudePOI, longitudePOI);
		this.googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLngPOI, 16));		
	}
	protected void moveCameraToCurrentLocation() {
		LatLng latLngUSER = new LatLng(lat, lng);
		this.googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLngUSER, 16));		
	}
	@Override
	public void onAttachedToWindow() {
		if (poiSelezionato.getLat() != 0) {
			LatLng latLngPOI = new LatLng(this.latitudePOI, this.longitudePOI);
			LatLng latLngUSER = new LatLng(lat, lng);
			googleMap = (mMapFragment).getMap();
			googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
			googleMap.addMarker(new MarkerOptions().position(latLngPOI).title(nome).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
			googleMap.addMarker(new MarkerOptions().position(latLngUSER).title("io").icon(BitmapDescriptorFactory.fromResource(R.drawable.markergps)));
			googleMap.getUiSettings().setCompassEnabled(true);
			googleMap.getUiSettings().setZoomControlsEnabled(true);
			googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLngPOI, 16));
			super.onAttachedToWindow();
		}
	}

	protected void inviaEmail() {
		Intent intent = new Intent(Intent.ACTION_SEND);
		intent.setType("message/rfc822");
		intent.putExtra(Intent.EXTRA_EMAIL, new String[] {this.email});
		startActivity(intent);
	}
	@Override
	public void onBackPressed() {
		if (valorePrecedente == null || valorePrecedente.equals("main")) {
			Intent intent = new Intent(this, RistorantiActivity.class);
			startActivity(intent);
		}
		else {
			Intent intent = new Intent(this, PreferitiActivity.class);
			startActivity(intent);
		}
	}

}