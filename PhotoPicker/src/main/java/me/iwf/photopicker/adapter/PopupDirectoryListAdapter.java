package me.iwf.photopicker.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.RequestManager;
import com.facebook.drawee.view.SimpleDraweeView;
import com.hss01248.image.ImageLoader;

import java.util.ArrayList;
import java.util.List;
import me.iwf.photopicker.R;
import me.iwf.photopicker.entity.PhotoDirectory;

/**
 * Created by donglua on 15/6/28.
 */
public class PopupDirectoryListAdapter extends BaseAdapter {


  private List<PhotoDirectory> directories = new ArrayList<>();
  //private RequestManager glide;

  public PopupDirectoryListAdapter( List<PhotoDirectory> directories) {
    this.directories = directories;
    //this.glide = glide;
  }


  @Override public int getCount() {
    return directories.size();
  }


  @Override public PhotoDirectory getItem(int position) {
    return directories.get(position);
  }


  @Override public long getItemId(int position) {
    return directories.get(position).hashCode();
  }


  @Override public View getView(int position, View convertView, ViewGroup parent) {
    ViewHolder holder;
    if (convertView == null) {
      LayoutInflater mLayoutInflater = LayoutInflater.from(parent.getContext());
      convertView = mLayoutInflater.inflate(R.layout.__picker_item_directory, parent, false);
      holder = new ViewHolder(convertView);
      convertView.setTag(holder);
    } else {
      holder = (ViewHolder) convertView.getTag();
    }

    holder.bindData(directories.get(position));

    return convertView;
  }

  private class ViewHolder {

    public SimpleDraweeView ivCover;
    public TextView tvName;
    public TextView tvCount;

    public ViewHolder(View rootView) {
      ivCover = (SimpleDraweeView) rootView.findViewById(R.id.iv_dir_cover);
      tvName  = (TextView)  rootView.findViewById(R.id.tv_dir_name);
      tvCount = (TextView)  rootView.findViewById(R.id.tv_dir_count);
    }

    public void bindData(PhotoDirectory directory) {
      ImageLoader.with(null).content(directory.getCoverPath())
          .widthHeight(100,100)
          .into(ivCover);
      tvName.setText(directory.getName());
      tvCount.setText(tvCount.getContext().getString(R.string.__picker_image_count, directory.getPhotos().size()));
    }
  }

}
