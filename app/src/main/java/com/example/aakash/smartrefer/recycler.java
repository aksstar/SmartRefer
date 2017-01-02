package com.example.aakash.smartrefer;

/**
 * Created by User on 1/2/2017.
 */

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.StringDef;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class recycler extends RecyclerView.Adapter<recycler.ViewHolder> {

    private String[] titles = {"Scan",
            "Recipies",
            "Shopping List",
            "New Food",
            "Dashboard",
            "Joy of Giving",
            "Kids Section",
            "Waste Management",
            "Nutrition Chart"};

    private String[] details = {"Item one details",
            "Item two details", "Item three details",
            "Item four details", "Item file details",
            "Item six details", "Item seven details",
            "Item eight details","Item eight details"};

 /*   private int[] images = { R.drawable.android_image_1,
            R.drawable.android_image_2,
            R.drawable.android_image_3,
            R.drawable.android_image_4,
            R.drawable.android_image_5,
            R.drawable.android_image_6,
            R.drawable.android_image_7,
            R.drawable.android_image_8 };
*/
    class ViewHolder extends RecyclerView.ViewHolder{

        public int currentItem;
    //    public ImageView itemImage;
        public TextView itemTitle;
        public TextView itemDetail;

        public ViewHolder(View itemView) {
            super(itemView);
         //   itemImage = (ImageView)itemView.findViewById(R.id.item_image);
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
                        case 4:
                            intent = new Intent(v.getContext(), Dashboard.class);
                            break;
                        case 5:
                            url = "http://www.karmayog.org/nonprofits/npogriddisplay.asp?r=476";
                            intent = new Intent(Intent.ACTION_VIEW);
                            intent.setData(Uri.parse(url));
                            break;
                        case 3:
                            intent = new Intent(v.getContext(), addthings.class);
                            break;
                        case 2:
                             intent = new Intent(v.getContext(), shoppinglist.class);
                            break;
                        case 6:
                            v.getContext().startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/results?search_query=healthy+recipes+for+kids")));
                            break;
                        case 1:
                            url = "http://myfridgefood.com/";
                            intent = new Intent(Intent.ACTION_VIEW);
                            intent.setData(Uri.parse(url));
                            //startActivity(i);
                            break;
                        case 7:
                            url = "http://www.biogas-india.com/contact.php";
                            intent = new Intent(Intent.ACTION_VIEW);
                            intent.setData(Uri.parse(url));
                            break;
                        case 8:
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
      //  viewHolder.itemImage.setImageResource(images[i]);
    }

    @Override
    public int getItemCount() {
        return titles.length;
    }
}