package app.activity;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import app.db.MySQLiteHelper;
import app.entity.PoiBean;
import app.isole.R;
import app.realtaAumentata.DataSource;

public class PreferitiActivity extends ListActivity{
	private MySQLiteHelper dbHelper;
	private static List<PoiBean> listaTuttiPoiBean;
	private String[] array;
	private static List<PoiBean> poiBeanPreferiti;
	private List<String> listPoiBeanPreferitiString = new ArrayList<String>();
	private ArrayAdapter<String> adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.preferiti);
		dbHelper = new MySQLiteHelper(getApplicationContext());
		listaTuttiPoiBean = getAllPOI();
		poiBeanPreferiti = dbHelper.getPOIPreferiti(listaTuttiPoiBean);
		TextView textViewListaVuota = (TextView)findViewById(R.id.textViewListaVuota);
		if (poiBeanPreferiti.size() == 0) {			//se non ci sono preferiti
			textViewListaVuota.setVisibility(View.VISIBLE);
		}
		for (PoiBean pb : PreferitiActivity.poiBeanPreferiti)
			listPoiBeanPreferitiString.add(pb.getName());
		this.array = listPoiBeanPreferitiString.toArray(new String[poiBeanPreferiti.size()]);
		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, array);
		System.out.println("DIMENSIONE LISTA PREFERITI: " + listPoiBeanPreferitiString.size());
		setListAdapter(adapter);
	}
	public List<PoiBean> getAllPOI(){
		DataSource dataSource = new DataSource();
		try {
			InputStream stream = getResources().openRawResource(R.raw.db_tremiti);
			listaTuttiPoiBean = dataSource.getPoisByURL(stream);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listaTuttiPoiBean;
	}


	public static List<PoiBean> getListaTuttiPoiBean() {
		return listaTuttiPoiBean;
	}
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		if (id == 0)		//solo se clicco il primo.... non so perché solo per il primo non funziona
			startDettagliActivity(0);
		else
			for (PoiBean pb : listaTuttiPoiBean)
				if (id == pb.getId())
					startDettagliActivity(position);
	}
	protected void startDettagliActivity(int pos) {
		PoiBean pb = poiBeanPreferiti.get(pos);

		if (pb.getType().contains("Attivita")) {
			Intent intent = new Intent(this, DettagliAttivitaVarieActivity.class);
			intent.putExtra("POI_SELEZIONATO", pb);
			intent.putExtra("PROVENIENZA", "preferiti");
			startActivity(intent);
		}
		else if (pb.getType().contains("Bar")) {
			Intent intent = new Intent(this, DettagliBarActivity.class);
			intent.putExtra("POI_SELEZIONATO", pb);
			intent.putExtra("PROVENIENZA", "preferiti");
			startActivity(intent);
		}
		else if (pb.getType().contains("Ristorante")) {
			Intent intent = new Intent(this, DettagliRistoranteActivity.class);
			intent.putExtra("POI_SELEZIONATO", pb);
			intent.putExtra("PROVENIENZA", "preferiti");
			startActivity(intent);
		}
		else if (pb.getType().contains("Albergo")) {
			Intent intent = new Intent(this, DettagliHotelActivity.class);
			intent.putExtra("POI_SELEZIONATO", pb);
			intent.putExtra("PROVENIENZA", "preferiti");
			startActivity(intent);
		}
	}
	@Override
	public void onBackPressed() {
		Intent intent = new Intent(this, HomeActivity.class);
		startActivity(intent);
	}
}