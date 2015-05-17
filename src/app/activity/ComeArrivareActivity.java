package app.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import app.isole.R;

public class ComeArrivareActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.comearrivare);
		ImageView ivElicottero = (ImageView)findViewById(R.id.elicottero);
		TextView tvElicottero = (TextView)findViewById(R.id.textElicottero);
		Typeface fontScritte = Typeface.createFromAsset(getAssets(),"fonts/eurof35.ttf"); 
		TextView tvMacchina = (TextView)findViewById(R.id.textMacchina);
		ImageView ivNave = (ImageView)findViewById(R.id.nave);
		TextView tvNave = (TextView)findViewById(R.id.textNave);
		ImageView ivTreno = (ImageView)findViewById(R.id.treno);
		TextView tvTreno = (TextView)findViewById(R.id.textTreno);
		tvElicottero.setTypeface(fontScritte);
		tvNave.setTypeface(fontScritte);
		tvTreno.setTypeface(fontScritte);
		tvMacchina.setTypeface(fontScritte);
		ivElicottero.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.alidaunia.it/"));
				startActivity(browserIntent);
			}

		});
		tvElicottero.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.alidaunia.it/"));
				startActivity(browserIntent);
			}

		});
		ivTreno.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.trenitalia.com/"));
				startActivity(browserIntent);
			}

		});
		tvTreno.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.trenitalia.com/"));
				startActivity(browserIntent);
			}

		});
		tvNave.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startInfoTrasportiNaveActivity();
			}
		});
		ivNave.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startInfoTrasportiNaveActivity();
			}
		});
	}
	private void startInfoTrasportiNaveActivity() {
		Intent browserIntent = new Intent(this, InfoTrasportiNaveActivity.class);
		startActivity(browserIntent);
	}
}
