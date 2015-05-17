package app.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import app.isole.R;
//
//import android.app.Activity;
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.os.Bundle;
//import android.os.Handler;
//import android.preference.PreferenceManager;
//import android.view.View;
//import android.view.View.OnClickListener;
//import android.view.Window;
//import android.view.WindowManager;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//
//public class InizioActivity extends Activity {
//	// Set the display time, in milliseconds (or extract it out as a configurable parameter)
//	private final int SPLASH_DISPLAY_LENGTH = 4000;
//	@Override
//	protected void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		requestWindowFeature(Window.FEATURE_NO_TITLE);
//		this.getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN );
//		setContentView(R.layout.home);
//		TextView tvSkip = (TextView)findViewById(R.id.textViewSkip);
//		LinearLayout layoutPrincipale = (LinearLayout)findViewById(R.id.layoutPrincipale);
//		layoutPrincipale.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				skipMethod();
//			}
//		});
//		tvSkip.setOnClickListener(new OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				skipMethod();
//			}
//		});
//	}
//	protected void skipMethod() {
//		Intent intent = new Intent(this, HomeActivity.class);
//		
//		startActivity(intent);
//	}
//	@Override
//	protected void onResume() {
//		super.onResume();
//		SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
//		boolean isSplashEnabled = sp.getBoolean("isSplashEnabled", true);
//		if (isSplashEnabled) {
//			new Handler().postDelayed(new Runnable() {
//				@Override
//				public void run() {
//					InizioActivity.this.finish();
//					Intent mainIntent = new Intent(InizioActivity.this, HomeActivity.class);
//					InizioActivity.this.startActivity(mainIntent);
//					overridePendingTransition(R.anim.alphain, R.anim.alphaout);
//				}
//			}, SPLASH_DISPLAY_LENGTH);
//		}
//		else {
//			finish();
//			Intent mainIntent = new Intent(InizioActivity.this, HomeActivity.class);
//			InizioActivity.this.startActivity(mainIntent);
//		}
//	}
//}


public class InizioActivity extends Activity {
    // Set the display time, in milliseconds (or extract it out as a configurable parameter)
    private final int SPLASH_DISPLAY_LENGTH = 2000;
 
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
    }
 
    @Override
    protected void onResume()
    {
        super.onResume();
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        // Obtain the sharedPreference, default to true if not available
        boolean isSplashEnabled = sp.getBoolean("isSplashEnabled", true);
 
        if (isSplashEnabled)
        {
            new Handler().postDelayed(new Runnable()
            {
                @Override
                public void run()
                {
                    //Finish the splash activity so it can't be returned to.
                    InizioActivity.this.finish();
                    // Create an Intent that will start the main activity.
                    Intent mainIntent = new Intent(InizioActivity.this, HomeActivity.class);
                    InizioActivity.this.startActivity(mainIntent);
                    /* Animazione dissolvenza in ingresso per HomeActivity */
                    overridePendingTransition(R.anim.alphain, R.anim.alphaout);
                }
            }, SPLASH_DISPLAY_LENGTH);
        }
        else
        {
            // if the splash is not enabled, then finish the activity immediately and go to main.
            finish();
            Intent mainIntent = new Intent(InizioActivity.this, HomeActivity.class);
            InizioActivity.this.startActivity(mainIntent);
        }
    }
}








