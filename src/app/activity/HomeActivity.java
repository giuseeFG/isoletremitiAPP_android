package app.activity;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.view.Display;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import app.entity.PoiBean;
import app.extras.AppRater;
import app.isole.R;
import app.realtaAumentata.DataSource;

public class HomeActivity extends Activity {
	final Context context = this;
	private Handler h = new Handler();
	private DataSource dataSource = new DataSource();
	private SharedPreferences preferences;
	private String name;
	
	public static List<PoiBean> listaAttivitaVarie = new ArrayList<PoiBean>();
	public static List<PoiBean> listaBar = new ArrayList<PoiBean>();
	public static List<PoiBean> listaAlberghi = new ArrayList<PoiBean>();
	public static List<PoiBean> listaRistoranti = new ArrayList<PoiBean>();

	private static boolean attivitaCaricata;
	private static boolean barCaricata;
	private static boolean albergoCaricata;
	private static boolean ristoranteCaricata;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		AppRater.app_launched(this);

		this.preferences = PreferenceManager.getDefaultSharedPreferences(this);
		this.name = preferences.getString("preferenza","");
		if (getScreenOrientation() == Configuration.ORIENTATION_LANDSCAPE) {
			AlertDialog.Builder alertDialog = new AlertDialog.Builder(HomeActivity.this);
			alertDialog.setTitle("Attenzione:");
			alertDialog.setMessage("Posizionare il dispositivo in posizione verticale.");
			alertDialog.show();
			if (getScreenOrientation() == Configuration.ORIENTATION_PORTRAIT)
				super.onBackPressed();
		}

		ImageView numeriUtili = (ImageView)findViewById(R.id.imageButtonNumeriUtili);
		ImageView mappaIsole = (ImageView)findViewById(R.id.mappaIsole);
		ImageView cosaNePensi = (ImageView)findViewById(R.id.cosaNePensi);
		ImageView attivitaVarie = (ImageView)findViewById(R.id.attivitaVarie);
		ImageView informazioni = (ImageView)findViewById(R.id.infoIsole);
		ImageView mitiLeggende = (ImageView)findViewById(R.id.mitiLeggende);
		ImageView comeArrivare = (ImageView)findViewById(R.id.comeArrivare);
		ImageView doveAlloggiare = (ImageView)findViewById(R.id.doveAlloggiare);
		ImageView Bar = (ImageView)findViewById(R.id.bar);
		ImageView ristoranti = (ImageView)findViewById(R.id.ristoranti);
		ImageView eventi = (ImageView)findViewById(R.id.eventi);
		

		this.preferences = PreferenceManager.getDefaultSharedPreferences(this);

		if(name == null || !name.equals("no"))
			h.postDelayed(myrunnable, 1500);		//fa uscire l'alertDialog dopo 1.5 secondi solo se non ho già schiacciato "no"

		numeriUtili.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				startNumeriUtili();
			}
		});
		mappaIsole.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startMappaActivity();
			}
		});
		attivitaVarie.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (!attivitaCaricata) {
					caricaListaAttivitaVarie();
					attivitaCaricata = true;
				}
				startAttivitaVarieActivity();
			}
		});
		ristoranti.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (!ristoranteCaricata) {
					caricaListaRistoranti();
					ristoranteCaricata = true;
				}
				startRistorantiActivity();

			}
		});
		mitiLeggende.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startMitiLeggende();
			}

		});
		cosaNePensi.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				startValutaIsoleActivity();
			}

		});
		informazioni.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startInfoActivity();
			}

		});

		comeArrivare.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startComeArrivareActivity();
			}

		});
		doveAlloggiare.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (!albergoCaricata) {
					caricaListaAlberghi();
					albergoCaricata = true;
				}
				startDoveAlloggiareActivity();

			}

		});
		Bar.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (!barCaricata) {
					caricaListaBarLocali();
					barCaricata = true;
				}
				startBarActivity();
			}
		});
		eventi.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startEventiActivity();
			}
		});

	}

	protected void startNumeriUtili() {
		Intent intent = new Intent(this, NumeriUtiliActivity.class);
		startActivity(intent);		
	}

	protected void startValutaIsoleActivity() {
		Intent intent = new Intent(this, CosaNePensiActivity.class);
		startActivity(intent);
	}

	protected void startMappaActivity() {
		Intent intent = new Intent(this, MappaActivity.class);
		startActivity(intent);
	}

	protected void startAttivitaVarieActivity() {
		Intent intent = new Intent(this, AttivitaVarieActivity.class);
		startActivity(intent);

	}

	protected void startInfoSviluppatoreActivity() {
		Intent intent = new Intent(this, InfoAppActivity.class);
		startActivity(intent);
	}

	private void startBarActivity() {
		Intent intent = new Intent(this, BarActivity.class);
		startActivity(intent);
	}

	private void startRistorantiActivity() {
		Intent intent = new Intent(this, RistorantiActivity.class);
		startActivity(intent);
	}
	private void startEventiActivity() {
		Intent intent = new Intent(this, EventiActivity.class);
		startActivity(intent);
	}

	//	private void startRealtaAumentata() {
	//		Intent intent = new Intent(this, RealtaAumentataActivity.class);
	//		startActivity(intent);
	//	}

	private void startInfoActivity() {
		Intent intent = new Intent(this, InfoActivity.class);
		startActivity(intent);

	}
	private void startMitiLeggende() {
		Intent intent = new Intent(this, StoriaActivity.class);
		startActivity(intent);
	}
	private void startComeArrivareActivity() {
		Intent intent = new Intent(this, ComeArrivareActivity.class);
		startActivity(intent);
	}

	private void startDoveAlloggiareActivity() {
		Intent intent = new Intent(this, HotelActivity.class);
		startActivity(intent);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		MenuInflater mi = getMenuInflater();
		mi.inflate(R.menu.list_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()){
		case R.id.infoSviluppatore:
			startActivity (new Intent (this, InfoAppActivity.class));
			break;
		case R.id.preferiti:
			startActivity (new Intent (this, PreferitiActivity.class));
			break;
		}
		return true;
	}
	@Override
	public void onBackPressed() {			//fa chiudere l'app
		Intent intent = new Intent(Intent.ACTION_MAIN);
    	intent.addCategory(Intent.CATEGORY_HOME);
    	intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    	startActivity(intent);
	}

	private Runnable myrunnable = new Runnable() {

		@Override
		public void run() {
			LocationManager locManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
			AlertDialog.Builder alertDialog = new AlertDialog.Builder(HomeActivity.this);
			alertDialog.setTitle("Attenzione");
			alertDialog.setMessage("Per una migliore prestazione è consigliabile attivare il GPS");
			alertDialog.setIcon(R.drawable.gps_avviso);
			if (!locManager.isProviderEnabled(LocationManager.GPS_PROVIDER))  {
				alertDialog.setPositiveButton("Attiva",
						new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						Intent myIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
						startActivity(myIntent);
					}
				});
				alertDialog.setNegativeButton("No",	new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						dialog.cancel();
						SharedPreferences.Editor editor = preferences.edit();
						editor.putString("preferenza","no");
						editor.commit();
					}
				});
				alertDialog.show();
			}

		}

	};
	@SuppressWarnings("deprecation")
	public int getScreenOrientation() {
		Display getOrient = getWindowManager().getDefaultDisplay();
		int orientation = Configuration.ORIENTATION_UNDEFINED;
		if(getOrient.getWidth()==getOrient.getHeight()){
			orientation = Configuration.ORIENTATION_SQUARE;
		} else{ 
			if(getOrient.getWidth() < getOrient.getHeight()){
				orientation = Configuration.ORIENTATION_PORTRAIT;
			}else { 
				orientation = Configuration.ORIENTATION_LANDSCAPE;
			}
		}
		return orientation;
	}
	public static List<PoiBean> getListaAttivitaVarie() {
		return listaAttivitaVarie;
	}
	public static List<PoiBean> getListaBar() {
		return listaBar;
	}
	public static List<PoiBean> getListaAlberghi() {
		return listaAlberghi;
	}
	public static List<PoiBean> getListaRistoranti() {
		return listaRistoranti;
	}
	public void caricaListe() {
		caricaListaAttivitaVarie();
		caricaListaAlberghi();
		caricaListaRistoranti();
		caricaListaBarLocali();
	}
	private void caricaListaBarLocali() {
		try {
			List<PoiBean> listaPOI = null;
			InputStream stream = getResources().openRawResource(R.raw.db_tremiti);
			listaPOI = dataSource.getPoisByURL(stream);
			for (PoiBean pd : listaPOI)
				if (pd.getType().contains("Bar"))
					HomeActivity.listaBar.add(pd);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void caricaListaRistoranti() {
		try {
			List<PoiBean> listaPOI = null;
			InputStream stream = getResources().openRawResource(R.raw.db_tremiti);
			listaPOI = dataSource.getPoisByURL(stream);
			for (PoiBean pd : listaPOI)
				if (pd.getType().contains("Ristorante"))
					HomeActivity.listaRistoranti.add(pd);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void caricaListaAlberghi() {
		try {
			List<PoiBean> listaPOI = null;
			InputStream stream = getResources().openRawResource(R.raw.db_tremiti);
			listaPOI = dataSource.getPoisByURL(stream);
			for (PoiBean pd : listaPOI)
				if (pd.getType().contains("Albergo"))
					HomeActivity.listaAlberghi.add(pd);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void caricaListaAttivitaVarie() {
		try {
			List<PoiBean> listaPOI = null;
			InputStream stream = getResources().openRawResource(R.raw.db_tremiti);
			listaPOI = dataSource.getPoisByURL(stream);
			for (PoiBean pd : listaPOI)
				if (pd.getType().contains("Attivita"))
					HomeActivity.listaAttivitaVarie.add(pd);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}