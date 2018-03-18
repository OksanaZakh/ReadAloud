package com.example.administrator.readaloud.api.AvatarCreator;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.administrator.readaloud.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Administrator on 02.03.2018.
 */

public class AvatarAdapter extends BaseAdapter {
    private static final String TAG = "AvatarAdapter";
    List<Avatar> avatarList;
    Context context;
    private LayoutInflater mInflater;

    @Override
    public int getCount() {
        return avatarList.size();
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public AvatarAdapter(Context context, List<Avatar> objects) {
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
        avatarList = objects;
    }

    @Override
    public Avatar getItem(int position) {
        return avatarList.get(position);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder vh;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_avatar_img_dialog_fragm_welc_main, parent, false);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }

        Avatar item = getItem(position);
        Picasso.with(context).load(item.getMedia().getImageUrl()).placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).into(vh.imageView);

        return convertView;
    }

    private static class ViewHolder {
        public final ImageView imageView;


        private ViewHolder(View view) {
            imageView = view.findViewById(R.id.WelcomeUserAvatarDialogFragment_Item_imageView);
        }
    }
}
