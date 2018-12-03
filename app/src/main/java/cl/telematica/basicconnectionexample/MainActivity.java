package cl.telematica.basicconnectionexample;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import cl.telematica.basicconnectionexample.connection.HttpServerConnection;
import cl.telematica.basicconnectionexample.models.Libro;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Url;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    public interface download {
        @GET
        Call<String> get_data(@Url String url);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.recyclerView);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(ScalarsConverterFactory.create())
                .baseUrl("http://www.mocky.io/")
                .build();

        download service = retrofit.create(download.class);

        Call<String> data = service.get_data("http://www.mocky.io/v2/5bfc6aa9310000780039be36");

        data.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {
                    String responseString = response.body();
                    System.out.println(responseString);
                    mAdapter = new UIAdapter(Libro.getLibros(responseString));
                    mRecyclerView.setAdapter(mAdapter);
                }

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });

    }
}
