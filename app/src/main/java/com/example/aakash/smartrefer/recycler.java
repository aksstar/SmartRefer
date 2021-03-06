package com.example.aakash.smartrefer;

/**
 * Created by User on 1/2/2017.
 */

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

public class recycler extends RecyclerView.Adapter<recycler.ViewHolder> {

    private String[] titles = {"Scan",
            "Recipies",
            "Detect Veg",
            "Shopping List",
            "New Food",
            "Dashboard",
            "Joy of Giving",
            "Kids Section",
            "Waste Management",
            "Nutrition Chart"
    };

    private String[] details = {"Item one details",
            "Item two details", "Item three details",
            "Item four details", "Item file details",
            "Item six details", "Item seven details",
            "Item eight details","Item eight details",
            "Item nine details",
            "Item Ten Details"};

    private int[] images = {R.drawable.scan,
            R.drawable.pp2,
            R.drawable.pp3,
            R.drawable.pp4,
            R.drawable.pp6,
            R.drawable.messages_512,
            R.drawable.ngo,
            R.drawable.kids,
            R.drawable.waste,
            R.drawable.nutritionchart};

    class ViewHolder extends RecyclerView.ViewHolder{

        public int currentItem;
    //    public ImageView itemImage;
        public TextView itemTitle;
        public TextView itemDetail;
        public ImageView itemImage;
        public ViewHolder(View itemView) {
            super(itemView);
            itemImage = (ImageView)itemView.findViewById(R.id.item_image);
            itemTitle = (TextView)itemView.findViewById(R.id.item_title);
            itemDetail =
                    (TextView)itemView.findViewById(R.id.item_detail);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), Scanactivty.class);;
                    String url;
                    int position = getAdapterPosition();

                    switch (position)
                    {
                        case 0:
                            intent = new Intent(v.getContext(), Scanactivty.class);
                            break;
                        case 1:
                            intent = new Intent(v.getContext(), Recipie.class);
                            intent.putExtra("url","http://projectrefrigerator.pe.hu/rindex.php");
                            break;
                        case 2:
                            intent = new Intent(v.getContext(), FoodUiActivity.class);
                            break;
                        case 3:
                            intent = new Intent(v.getContext(), shop.class);
                            intent.putExtra("url","http://projectrefrigerator.pe.hu/add-record-form.php");
                            break;
                        case 4:
                            intent = new Intent(v.getContext(), addthings.class);
                            break;
                        case 5:
                            intent = new Intent(v.getContext(), dashboardaddd.class);
                            break;
                        case 6:
						intent = new Intent(v.getContext(), MapsActivity.class);
                           // url = "http://www.karmayog.org/nonprofits/npogriddisplay.asp?r=476";
                            //intent = new Intent(Intent.ACTION_VIEW);
                            //intent.setData(Uri.parse(url));
                            break;
                        case 7:
                            intent=new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/results?search_query=healthy+recipes+for+kids"));
                            break;
                        case 8:
                            intent = new Intent(v.getContext(), wastein.class);
                           // url = "http://www.biogas-india.com/contact.php";
                            //intent = new Intent(Intent.ACTION_VIEW);
                            //intent.setData(Uri.parse(url));
                            break;
                        case 9:
                            intent = new Intent(v.getContext(), nutrients.class);
                            break;
                    }
                    v.getContext().startActivity(intent);
                    Snackbar.make(v, "Click detected on item " + titles[position],
                            Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            });
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.cardlayout, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.itemTitle.setText(titles[i]);
        viewHolder.itemDetail.setText(details[i]);
       viewHolder.itemImage.setImageResource(images[i]);
    }

    @Override
    public int getItemCount() {
        return titles.length;
    }
}