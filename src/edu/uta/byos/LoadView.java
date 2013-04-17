package edu.uta.byos;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
* **************************** [ ByOS ] *****************************
* @Description A solitaire Game
* @class    | LoadView
*           | This activity starts before game activity to show our
*           | logo
* @authors ruby_
* @version 1.0
* *******************************************************************
*/

public class LoadView extends Activity implements OnTouchListener {

	private TextView touchToStart = null;
	private ImageView logo = null;
	private RelativeLayout startPage;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		setContentView(R.layout.activity_load_view);
		startPage = (RelativeLayout) findViewById(R.id.start_page);
		startPage.setOnTouchListener(this);

		final Animation alpha = AnimationUtils.loadAnimation(LoadView.this,
				R.anim.touch_to_start);
		final AnimationSet logoShow = (AnimationSet) AnimationUtils
				.loadAnimation(LoadView.this, R.anim.logo_show);
		logoShow.setFillAfter(true);

		/*
		 * AnimationListener settings
		 */
		logoShow.setAnimationListener(new AnimationListener() {

			@Override
			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationEnd(Animation animation) {
				touchToStart = (TextView) findViewById(R.id.touch_to_start);
				touchToStart.setVisibility(View.VISIBLE);
				touchToStart.setAnimation(alpha);
			}
		});

		logo = (ImageView) findViewById(R.id.logo);
		logo.setImageResource(R.drawable.logo);
		logo.setAnimation(logoShow);

	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {

		Intent intent = new Intent(LoadView.this, GameActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
		startActivity(intent);
		/* Close load game view */
		finish();
		return true;
	}

}
