package app.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import app.helper.HelperDateFormat;
import app.isole.R;

public class RichiediDisponibilitaActivity extends Activity {
	private int yearArrivo;
	private int monthArrivo;
	private int dayArrivo;
	private int yearPartenza;
	private int monthPartenza;
	private int dayPartenza;
	
	protected void onCreate(Bundle savedInstanceState) {
		final HelperDateFormat hdf = new HelperDateFormat();
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.richiedi_disponibilita);
		Button bottoneInviaRichiesta = (Button)findViewById(R.id.buttonRichiediDisponibilita);
		final DatePicker dpArrivo = (DatePicker)findViewById(R.id.dateAndata);
		final DatePicker dpPartenza = (DatePicker)findViewById(R.id.datePartenza);

		bottoneInviaRichiesta.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				dayArrivo = dpArrivo.getDayOfMonth();
				monthArrivo = dpArrivo.getMonth();
				yearArrivo = dpArrivo.getYear();
				dayPartenza = dpPartenza.getDayOfMonth();
				monthPartenza = dpPartenza.getMonth();
				yearPartenza = dpPartenza.getYear();
				
				if (hdf.checkDateFormat(dayArrivo, monthArrivo, yearArrivo, dayPartenza, monthPartenza, yearPartenza)) {
					TextView tipologiaCamera = (TextView)findViewById(R.id.tipologiaCamera);
					EditText numeroOspiti = (EditText)findViewById(R.id.numeroOspiti);
					Intent intent = new Intent(Intent.ACTION_SEND);
					monthArrivo++;
					monthPartenza++;
					String[] destinatario = new String[1];
					destinatario[0] = getIntent().getExtras().getString("POI_EMAIL");;
					intent.setType("message/rfc822");
					intent.putExtra(Intent.EXTRA_SUBJECT, "Richiesta disponidilità camera");
					intent.putExtra(Intent.EXTRA_TEXT, "Salve, vorrei avere informazioni " +
							"circa la disponibilità di una camera dal " + dayArrivo + "/" + monthArrivo + "/" + yearArrivo + 
							" al " + dayPartenza + "/" + monthPartenza + "/" + yearPartenza + ".\n\n" +
							"Camera richiesta: \n\t" + tipologiaCamera.getText() + "\n\n" + 
							"n° persone: \n\t" + numeroOspiti.getText() + "\n\n\n" + 
							"------------------------------------\n\nMessaggio proveniente da \"Isole Tremiti\" app Android. \n\n");
					intent.putExtra(Intent.EXTRA_EMAIL, destinatario);
					startActivity(Intent.createChooser(intent, "Invia l'email usando:"));
				}
				else {
					messaggioErroreData();
				}
			}
		});

	}

	protected void messaggioErroreData() {
		AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
		alertDialog.setTitle("Attenzione");
		alertDialog.setMessage("La data inserita non è valida!");
		alertDialog.show();		
	}
}
