package com.app.preparatec2;

import com.app.preparatec2.R;

import android.app.Activity;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import static com.app.preparatec2.R.id.btnPrepara;

public class Splash extends Activity {
	TextView t;

	/** Called when the activity is first created. */

	@Override
	public void onAttachedToWindow() {
		super.onAttachedToWindow();
		Window window = getWindow();
		window.setFormat(PixelFormat.RGBA_8888);
	}

	private static String TAG = Splash.class.getName();
	private static long SLEEP_TIME = 3;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_splash);
		StartAnimations();
		IntentLauncher launcher = new IntentLauncher();
		launcher.start();

	}

	private void StartAnimations() {

		Animation anim = AnimationUtils.loadAnimation(this,R.anim.alpha);
		anim.reset();
		LinearLayout l=(LinearLayout) findViewById(R.id.la_Main);
		l.clearAnimation();
		l.startAnimation(anim);

		anim = AnimationUtils.loadAnimation(this, R.anim.translate);
		anim.reset();
		ImageView iv = (ImageView) findViewById(R.id.logo);
		iv.clearAnimation();
		iv.startAnimation(anim);

	}
	
	@Override
	public void onBackPressed() {
	  // Log.d("CDA", "onBackPressed Called");
	   Intent setIntent = new Intent(Intent.ACTION_MAIN);
	   setIntent.addCategory(Intent.CATEGORY_HOME);
	   setIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	   startActivity(setIntent);
	}

	private class IntentLauncher extends Thread {

		@Override
		public void run() {
			try {

				Thread.sleep(SLEEP_TIME*1000);

			} catch (Exception e) {

				Log.e(TAG, e.getMessage());

			}

			Intent intent = new Intent(Splash.this, Menu.class);
			Splash.this.startActivity(intent);
			Splash.this.finish();
		}
	}    
}