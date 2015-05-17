package app.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageButton;
import app.isole.R;

public class InfoTrasportiNaveActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.infotrasportinave);
		ImageButton tirrenia = (ImageButton)findViewById(R.id.tirrenia);
		ImageButton nlg = (ImageButton)findViewById(R.id.nlg);
		tirrenia.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.tirrenia.it/it/Pagine/home.aspx"));
				startActivity(browserIntent);
			}

		});
		nlg.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.navlib.it/ita/linee/index.asp"));
				startActivity(browserIntent);
			}

		});
		
	}
}
