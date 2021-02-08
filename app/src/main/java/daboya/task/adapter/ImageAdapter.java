package daboya.task.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

import java.util.Objects;

import daboya.task.R;
import daboya.task.rest.ImageResponse;

public class ImageAdapter extends PagedListAdapter<ImageResponse, ImageAdapter.ImageHolder> {

    private Context context;

    public ImageAdapter(Context context) {
        super(diffCallback);
        this.context = context;
    }

    private static DiffUtil.ItemCallback<ImageResponse> diffCallback =
            new DiffUtil.ItemCallback<ImageResponse>() {
                @Override
                public boolean areItemsTheSame(@NonNull ImageResponse oldItem, @NonNull ImageResponse newItem) {
                    return oldItem.getId().equals(newItem.getId());
                }

                @SuppressLint("DiffUtilEquals")
                @Override
                public boolean areContentsTheSame(@NonNull ImageResponse oldItem, @NonNull ImageResponse newItem) {
                    return oldItem.getId().equals(newItem.getId());
                }
            };


    @NonNull
    @Override
    public ImageHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_images,parent,false);
        return new ImageHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageHolder holder, int position) {
        ImageResponse currentItem = getItem(position);
        Glide.with(context)
                .asBitmap()
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .load(currentItem.getDownload_url())
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(new BitmapImageViewTarget(holder.imageView));
    }

    class ImageHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        ImageHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.item_image);
        }
    }

}
