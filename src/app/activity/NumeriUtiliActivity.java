package app.activity;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import app.isole.R;

public class NumeriUtiliActivity extends ListActivity {
	private String[] arrayNomi;
	private LinkedHashMap<String, String> nome2numeri;
	private LinkedList<String> listaNumeri = new LinkedList<String>();
	private ArrayAdapter<String> adapter;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.numeri_utili);
		this.nome2numeri = new LinkedHashMap<String, String>();
		nome2numeri.put("Tirrenia (Tremiti)", "0882463008");
		nome2numeri.put("Tirrenia (Termoli)", "0875870301");
		nome2numeri.put("Navigazione Libera Del Golfo (Tremiti)", "3356485899");
		nome2numeri.put("Navigazione Libera Del Golfo (Termoli)", "0875704859");
		nome2numeri.put("Navigazione Libera Del Golfo (Vieste)", "0884707489");
		nome2numeri.put("Gargano Viaggi (Vieste)", "0884708501");
		nome2numeri.put("MS&G Navigazioni (Peschici)", "0884962732");
		nome2numeri.put("LMA - Linee Marittime Adriatico (Peschici)", "0884964023");
		nome2numeri.put("LMA - Linee Marittime Adriatico (Rodi Garganico)", "0884964023");
		nome2numeri.put("LMA - Linee Marittime Adriatico (Peschici)", "0884964023");
		nome2numeri.put("Alidaunia, servizio elicotteri", "899325292");
		nome2numeri.put("Pronto soccorso", "0882463234");
		nome2numeri.put("Farmacia", "0882463327");
		nome2numeri.put("Guardia medica San Domino", "0882463752");
		nome2numeri.put("Guardia medica San Nicola", "0882463061");
		nome2numeri.put("Carabinieri", "0882463010");
		nome2numeri.put("Guardia di Finanza", "0882463015");
		nome2numeri.put("Guardia Costiera", "0882707489");
		nome2numeri.put("Parrocchia", "0882463084");
		nome2numeri.put("Poste", "0882463259");
		nome2numeri.put("Municipio", "0882463063");

		for (String s : this.nome2numeri.values())
			listaNumeri.add(s);
		int i = 0;
		this.arrayNomi = new String[nome2numeri.size()];
		for (String s : this.nome2numeri.keySet()) {
			arrayNomi[i] = s;
			i++;
		}
		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayNomi);
		setListAdapter(adapter);
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		String numero = this.listaNumeri.get(position);
		String nomeSelezionato = this.arrayNomi[position];
		System.out.println("nome " + nomeSelezionato + " numero: " + numero);
		mostraAlertDialog(numero, nomeSelezionato);
	}
	private void mostraAlertDialog(final String numero, String nomeSelezionato) {
		AlertDialog.Builder alertDialog = new AlertDialog.Builder(NumeriUtiliActivity.this);
		alertDialog.setTitle("Avviso");
		alertDialog.setMessage("Chiamare: " + nomeSelezionato + "?");
		alertDialog.setPositiveButton("Chiama", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				Intent callIntent = new Intent(Intent.ACTION_CALL);
	            callIntent.setData(Uri.parse("tel:" + numero));
	            startActivity(callIntent);
			}
		});
		alertDialog.setNegativeButton("Indietro",
				new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				dialog.cancel();
			}
		});
		alertDialog.show();

	}

}