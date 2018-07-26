package sg.edu.np.s10171369.assignment;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MainPageCustomAdapter extends RecyclerView.Adapter<MainPageCustomAdapter.ViewHolder> {

    private List<MainPageDataModel> dataSet;

    public MainPageCustomAdapter(List<MainPageDataModel> data) {
        dataSet = data;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView textViewHeaderName;
        ImageView imageViewHeaderIcon;

        public ViewHolder(View itemView) {
            super(itemView);
            this.textViewHeaderName = itemView.findViewById(R.id.textView);
            this.imageViewHeaderIcon = itemView.findViewById(R.id.imageView);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cards_layout, parent, false);

        view.setOnClickListener(Guides.myOnClickListener);

        ViewHolder ViewHolder = new ViewHolder(view);
        return ViewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder,final int listPosition) {
        TextView textViewHeaderName = holder.textViewHeaderName;
        ImageView imageViewHeaderIcon = holder.imageViewHeaderIcon;

        textViewHeaderName.setText(dataSet.get(listPosition).getHeader());
        imageViewHeaderIcon.setImageBitmap(dataSet.get(listPosition).getImage());
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}

