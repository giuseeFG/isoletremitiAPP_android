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

public class BarActivity extends ListActivity {
	private String[] array;
	private static List<String> listaBarString = new ArrayList<String>();
	private static boolean barGiaCaricati;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		new backgroundLoadListView().execute();
		setContentView(R.layout.pagina_bar_locali);
		if (!barGiaCaricati) {
		for (PoiBean pb : HomeActivity.listaBar)
			listaBarString.add(pb.getName());
		barGiaCaricati = true;
		}
		this.array = listaBarString.toArray(new String[HomeActivity.listaBar.size()]);
		Arrays.sort(array);
	}
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		TextView label = (TextView)v.findViewById(R.id.textViewNomeBar);
		String nomePoiSelezionato = label.getText().toString();
		startDettagliBarActivity(nomePoiSelezionato);
	}
	protected void startDettagliBarActivity(String nomePDI) {
		Intent intent = new Intent(this, DettagliBarActivity.class);
		for (PoiBean pb: HomeActivity.listaBar)
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
				row=inflater.inflate(R.layout.riga_lista_bar, parent, false);
			}
			TextView label=(TextView)row.findViewById(R.id.textViewNomeBar);
			label.setText(array[position]);
			Typeface bellerose = Typeface.createFromAsset(getAssets(),"fonts/eurof35.ttf");
			label.setTypeface(bellerose);
			ImageView icon=(ImageView)row.findViewById(R.id.imageViewBar);
			InflateImage inflate = new InflateImage();
			inflate.inflateImageBar(array[position], icon);

			return row;
		}
	}
	@SuppressLint("NewApi")
	public class backgroundLoadListView extends AsyncTask<Void, Void, Void> {
		@Override
		protected void onPostExecute(Void result) {
			setListAdapter(new MyCustomAdapter(BarActivity.this, R.layout.riga_lista_bar, array));
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
