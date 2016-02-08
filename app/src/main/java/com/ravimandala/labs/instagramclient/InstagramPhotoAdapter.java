package com.ravimandala.labs.instagramclient;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.List;

public class InstagramPhotoAdapter extends ArrayAdapter<InstagramPhoto> {
    private static class ViewHolder {
        ImageView ivProfilePic;
        TextView tvUsername;
        TextView tvCreatedTime;
        ImageView ivPhoto;
        TextView tvCaption;
        TextView tvLikes;
    }

    public InstagramPhotoAdapter(Context context, List<InstagramPhoto> objects) {
        super(context, android.R.layout.simple_list_item_1, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        InstagramPhoto iPhoto = getItem(position);

        ViewHolder viewHolder;
        if(convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_photo, parent, false);
            viewHolder.ivProfilePic = (ImageView) convertView.findViewById(R.id.ivProfilePic);
            viewHolder.tvUsername = (TextView) convertView.findViewById(R.id.tvUsername);
            viewHolder.tvCreatedTime = (TextView) convertView.findViewById(R.id.tvCreatedTime);
            viewHolder.ivPhoto = (ImageView) convertView.findViewById(R.id.ivPhoto);
            viewHolder.tvCaption = (TextView) convertView.findViewById(R.id.tvCaption);
            viewHolder.tvLikes = (TextView) convertView.findViewById(R.id.tvLikes);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Picasso.with(getContext()).load(iPhoto.profilePicUrl).transform(new CircleTransform()).into(viewHolder.ivProfilePic);
        viewHolder.tvUsername.setText(iPhoto.username);
        viewHolder.tvCreatedTime.setText(iPhoto.createdTime);
        viewHolder.ivPhoto.setImageResource(0);
        Picasso.with(getContext()).load(iPhoto.mediaUrl).fit().centerInside().into(viewHolder.ivPhoto);
        viewHolder.tvCaption.setText(iPhoto.caption);
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        viewHolder.tvLikes.setText(formatter.format(iPhoto.likes) + " likes");

        return convertView;
    }
}
