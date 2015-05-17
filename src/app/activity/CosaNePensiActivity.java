package app.activity;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import app.isole.R;

public class CosaNePensiActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		setContentView(R.layout.valuta_isole);
		final RatingBar ratingBarPulizia = (RatingBar)findViewById(R.id.ratingBarPulizia);
		final RatingBar ratingBarCordialita = (RatingBar)findViewById(R.id.ratingBarCordialita);
		final RatingBar ratingBarPrezzi = (RatingBar)findViewById(R.id.ratingBarPrezzi);
		final RatingBar ratingBarPunteggio = (RatingBar)findViewById(R.id.ratingBarPunteggio);
		final EditText editTextAltro = (EditText)findViewById(R.id.editTextAltro);
		final EditText editTextEmail = (EditText)findViewById(R.id.editTextEmail);
		coloraEformattaTextView();
		Button bottoneInvia = (Button)findViewById(R.id.buttonInvia);
		bottoneInvia.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				try {
					Thread thread = new Thread(new Runnable(){
						@Override
						public void run() {
							try {
								GMailSender sender = new GMailSender("ing.giuseppematrella@gmail.com", "RM339877#");
								String emailFrom = editTextEmail.getText().toString();
								if (emailFrom.equals(""))
									emailFrom = "ND";
									sender.sendMail("VALUTAZIONE ISOLE TREMITI",   
											"Pulizia: " + ratingBarPulizia.getRating() +
											"\nCordialità: " + ratingBarCordialita.getRating() +
											"\nPrezzi: " + ratingBarPrezzi.getRating() +
											"\nPunteggio generale: " + ratingBarPunteggio.getRating() +
											"\nAltro: " + editTextAltro.getText().toString() + 
											"\n\neMail: " + emailFrom,   
											"giusee@hotmail.it",   
											"giusee@hotmail.it");
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					});

					thread.start();
				} catch (Exception e) {
					Log.e("SendMail", e.getMessage(), e); 
				} 
				metodo();
			}
		});

	}

	private void coloraEformattaTextView() {
		TextView pulizia = (TextView)findViewById(R.id.pulizia);
		TextView prezzi = (TextView)findViewById(R.id.prezzi);
		TextView cordialita = (TextView)findViewById(R.id.cordialita);
		TextView punteggioGenerale = (TextView)findViewById(R.id.punteggioGenerale);
		TextView email = (TextView)findViewById(R.id.email);
		TextView altro = (TextView)findViewById(R.id.altro);
		TextView testo = (TextView)findViewById(R.id.testoValutaApp);
		Typeface typeScritte = Typeface.createFromAsset(getAssets(),"fonts/eurof35.ttf");
		Typeface typeBold = Typeface.createFromAsset(getAssets(),"fonts/eurof75bold.ttf"); 
		pulizia.setTypeface(typeBold);
		prezzi.setTypeface(typeBold);
		cordialita.setTypeface(typeBold);
		punteggioGenerale.setTypeface(typeBold);
		email.setTypeface(typeScritte);
		altro.setTypeface(typeScritte);
		testo.setTypeface(typeScritte);
	}

	protected void metodo() {
		Toast.makeText(this, "Recensione inviata!", Toast.LENGTH_LONG).show();
		super.onBackPressed();		
	}
}
