package remote;

import model.Weather;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by anna on 5/17/16.
 */
public class RemoteFactory {
    private static final String BASE_URL = "https://query.yahooapis.com/v1/public/";
    private static WeatherAPI weatherAPI;

    public static WeatherAPI getInstance() {
        if (weatherAPI == null) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            weatherAPI = retrofit.create(WeatherAPI.class);
        }
        return weatherAPI;
    }

}
