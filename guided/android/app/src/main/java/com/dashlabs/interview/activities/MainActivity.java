package com.dashlabs.interview.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.dashlabs.interview.R;
import com.dashlabs.interview.adapters.RecyclerViewAdapter;
import com.dashlabs.interview.models.Poster;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Poster> mPosters;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPosters = new ArrayList<>();
        mRecyclerView = findViewById(R.id.recyclerview);

        try {
            JSONObject jsonObject = new JSONObject(loadJSONFromAsset());
            JSONArray jsonArry = jsonObject.getJSONArray("results");

            for (int i = 0; i < jsonArry.length(); i++) {
                Poster poster = new Poster();
                StringBuilder URLBuilder = new StringBuilder(Constants.BASE_URL);

                JSONObject jsonData = jsonArry.getJSONObject(i);

                poster.setMovieTitle(jsonData.getString("title"));
                poster.setVoteAverage(jsonData.getString("vote_average"));
                poster.setReleaseDate(jsonData.getString("release_date"));
                poster.setMovieOverview(jsonData.getString("overview"));

                URLBuilder.append(jsonData.getString("poster_path"));
                String posterPath = URLBuilder.toString();

                poster.setPosterURL(posterPath);
                mPosters.add(poster);
            }
            setupRecyclerview(mPosters);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    private String loadJSONFromAsset() {
        String json = null;
        InputStream stream = null;

        try {
            stream = getAssets().open("data.json");
            int size = stream.available();
            byte[] buffer = new byte[size];
            stream.read(buffer);
            stream.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        } finally {
            if (stream != null ) {
                try {
                    stream.close();
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                    return null;
                }
            }
        }
        return json;
    }

    private void setupRecyclerview(List<Poster> posters) {
        RecyclerViewAdapter myadapter = new RecyclerViewAdapter(this, mPosters);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mRecyclerView.setAdapter(myadapter);
    }
}
