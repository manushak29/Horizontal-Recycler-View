package recycler;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.anna.retrofit.R;

import java.util.List;

import model.Forecast;

/**
 * Created by anna on 5/18/16.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<ForecastHolder> {
    private Context mContext;
    private List<Forecast> mForecastList;

    public RecyclerAdapter(Context context, List<Forecast> forecastList) {
        mContext = context;
        mForecastList = forecastList;
    }

    @Override
    public ForecastHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View view = layoutInflater.inflate(R.layout.recycler_item, parent, false);
        return new ForecastHolder(view);
    }

    @Override
    public void onBindViewHolder(ForecastHolder holder, int position) {
        holder.bindData(mForecastList.get(position));
    }

    @Override
    public int getItemCount() {
        return mForecastList.size();
    }
}
