package co.jh.popupsample;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

public class Pop extends Activity {

    private static LinearLayout linearLayout;
    private static LinearLayout popupWindowLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.popwindow);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int)(width*.75), (int)(height*.70));

        Button b = (Button) findViewById(R.id.close_btn);
        linearLayout = (LinearLayout) findViewById(R.id.popup_view);
        popupWindowLayout = (LinearLayout) findViewById(R.id.popup_window);
        popupWindowLayout.setBackgroundColor(Color.parseColor("#00000000"));
        b.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_UP) {
                    linearLayout.animate()
                            .translationY(linearLayout.getHeight())
                            .alpha(0.0f)
                            .setDuration(800)
                            .setListener(new AnimatorListenerAdapter() {
                                @Override
                                public void onAnimationEnd(Animator animation) {
                                    super.onAnimationEnd(animation);
                                    closePopup();
                                }
                            });
                    return true;
                }
                return false;
            }
        });
    }

    public void closePopup() {
        SharedPreferences pref = Pop.this.getSharedPreferences("my_settings", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("stringValue", "팝업이유");
        editor.commit();
        popupWindowLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
        Pop.this.finish();
    }
}

