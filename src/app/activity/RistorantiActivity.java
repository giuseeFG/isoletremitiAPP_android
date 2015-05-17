package app.activity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import android.annotation.SuppressLint;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import app.adapter.InflateImage;
import app.entity.PoiBean;
import app.isole.R;

public class RistorantiActivity extends ListActivity {
	private String[] array;

	private static List<String> listaRistorantiString = new ArrayList<String>();
	
	private static boolean ristorantiGiaCaricati;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		new backgroundLoadListView().execute();
		setContentView(R.layout.pagina_ristoranti);
		if (!ristorantiGiaCaricati) {
		for (PoiBean pb : HomeActivity.listaRistoranti)
			listaRistorantiString.add(pb.getName());
		ristorantiGiaCaricati = true;
		}
		this.array = listaRistorantiString.toArray(new String[HomeActivity.listaRistoranti.size()]);
		Arrays.sort(array);
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		TextView label = (TextView)v.findViewById(R.id.textViewNomeRistorante);
		String nomePoiSelezionato = label.getText().toString();
		startDettagliRistoranteActivity(nomePoiSelezionato);
	}
	protected void startDettagliRistoranteActivity(String nomePDI) {
		Intent intent = new Intent(this, DettagliRistoranteActivity.class);
		for (PoiBean pb: HomeActivity.listaRistoranti)
			if (pb.getName().equals(nomePDI))
				intent.putExtra("POI_SELEZIONATO", pb);
		startActivity(intent);
	}
	public class MyCustomAdapter extends ArrayAdapter<String> {
		public MyCustomAdapter(Context context, int textViewResourceId, String[] objects) {
			super(context, textViewResourceId, objects);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View row = convertView;
			if(row==null){
				LayoutInflater inflater=getLayoutInflater();
				row=inflater.inflate(R.layout.riga_lista_ristorante, parent, false);
			}
			TextView label=(TextView)row.findViewById(R.id.textViewNomeRistorante);
			label.setText(array[position]);
			ImageView icon=(ImageView)row.findViewById(R.id.imageViewRistorante);
			Typeface bellerose = Typeface.createFromAsset(getAssets(),"fonts/eurof35.ttf");
			label.setTypeface(bellerose);
			InflateImage inflate = new InflateImage();
			inflate.inflateImageRistorante(array[position], icon);

			return row;
		}
	}

	@SuppressLint("NewApi")
	public class backgroundLoadListView extends AsyncTask<Void, Void, Void> {
		@Override
		protected void onPostExecute(Void result) {
			setListAdapter(new MyCustomAdapter(RistorantiActivity.this, R.layout.riga_lista_ristorante, array));
		}

		@Override
		protected void onPreExecute() {
		}

		@Override
		protected Void doInBackground(Void... params) {
			return null;
		}

	}
	@Override
	public void onBackPressed() {
		Intent intent = new Intent(this, HomeActivity.class);
		startActivity(intent);
	}
}
