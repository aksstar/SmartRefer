package com.example.aakash.smartrefer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ViewFlipper;

public class nutrients extends AppCompatActivity {
    private int[] images = {R.drawable.a1,
            R.drawable.a2,R.drawable.a3,
            R.drawable.a4,R.drawable.a5,
            R.drawable.a6,R.drawable.a7,
            R.drawable.a8,R.drawable.a9,
            R.drawable.a12,R.drawable.a13,
            R.drawable.a14,R.drawable.a15
    };
    ViewFlipper viewFlipper;
    Button next,previous;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutrients);
        viewFlipper = (ViewFlipper) findViewById(R.id.flipper);
        next=(Button)findViewById(R.id.buttonnxt);
        previous=(Button)findViewById(R.id.buttonprv);
        for(int i=0;i<images.length;i++)
        {
            //  This will create dynamic image view and add them to ViewFlipper
            setFlipperImage(images[i]);
        }
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewFlipper.showNext();
            }
        });
        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewFlipper.showPrevious();
            }
        });
    }
    private void setFlipperImage(int res) {
        Log.i("Set Filpper Called", res+"");
        ImageView image = new ImageView(getApplicationContext());
        image.setBackgroundResource(res);
        viewFlipper.addView(image);
    }
}
