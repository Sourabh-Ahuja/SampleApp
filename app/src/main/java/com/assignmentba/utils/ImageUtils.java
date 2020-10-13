package com.assignmentba.utils;

import android.graphics.drawable.PictureDrawable;
import android.text.TextUtils;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.assignmentba.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;


public class ImageUtils {

    private static final String SVG_IMAGE = ".svg";

    @BindingAdapter("imageUrl")
    public static void setImage(ImageView image, String imageUrl) {

        if (image == null) {
            return;
        }

        if (TextUtils.isEmpty(imageUrl)) {
            image.setImageResource(R.drawable.sample_image);
            return;
        }

        /**
         * Replace Image Library of your choice like Picasso, ImageLoading Library, Fresco etc...
         */

        if (imageUrl.contains(SVG_IMAGE)) {
            //This blocks helps in loading svg images
            RequestBuilder<PictureDrawable> requestBuilder = Glide.with(image.getContext())
                    .as(PictureDrawable.class)
                    .placeholder(R.drawable.sample_image)
                    .error(R.drawable.sample_image)
                    .transition(withCrossFade());

            requestBuilder.load(imageUrl).into(image);
        } else {
            Glide.with(image.getContext()).load(imageUrl).override(1080, 600).apply(
                    new RequestOptions().diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                            .placeholder(R.drawable.sample_image)).into(image);
        }
    }


}
