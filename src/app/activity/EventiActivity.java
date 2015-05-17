package app.activity;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.Window;
import android.widget.ArrayAdapter;
import app.entity.Evento;
import app.isole.R;
import app.realtaAumentata.DataSourceEventi;

public class EventiActivity extends ListActivity {
	private static List<String> listEventiString = new ArrayList<String>();
	private static List<Evento> listaTuttiEventi;
	private String[] array;
	private ArrayAdapter<String> adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.eventi);
		listaTuttiEventi = getAllEvents();
		for (Evento e : EventiActivity.listaTuttiEventi)
			listEventiString.add(e.getNome());
		this.array = listEventiString.toArray(new String[listaTuttiEventi.size()]);
		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, array);
		
		setListAdapter(adapter);
	}
	public List<Evento> getAllEvents(){
		DataSourceEventi dataSourceEventi = new DataSourceEventi();
		try {
			InputStream stream = getResources().openRawResource(R.raw.eventi);
			listaTuttiEventi = dataSourceEventi.getEventiByURL(stream);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listaTuttiEventi;
	}

//	@Override
//	public void onBackPressed() {
//		Intent intent = new Intent(this, HomeActivity.class);
//		startActivity(intent);
//	}
}