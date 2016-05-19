package recycler;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.anna.retrofit.R;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import model.Forecast;

/**
 * Created by anna on 5/18/16.
 */
public class ForecastHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.day)
    TextView day;
    @BindView(R.id.date)
    TextView date;
    @BindView(R.id.high)
    TextView high;
    @BindView(R.id.low)
    TextView low;
    @BindView(R.id.text)
    TextView text;

    @BindString(R.string.high)
    String highPlaceholder;
    @BindString(R.string.low)
    String lowPlaceholder;

    public ForecastHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bindData(Forecast forecast) {
        day.setText(String.format("%s, ", forecast.getDay()));
        date.setText(forecast.getDate());
        high.setText(String.format(highPlaceholder, forecast.getHigh()));
        low.setText(String.format(lowPlaceholder, forecast.getLow()));
        text.setText(forecast.getText());
    }
}
