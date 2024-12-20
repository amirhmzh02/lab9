package edu.my.utem.ftmk.lab9.ui.main;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import edu.my.utem.ftmk.lab9.R;
import edu.my.utem.ftmk.lab9.databinding.FragmentGetUniversityBinding;

public class GetUniversityFrag extends Fragment {

    FragmentGetUniversityBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentGetUniversityBinding.inflate(inflater, container, false);

        // Set up a click listener for the search button
        binding.btnSearchU.setOnClickListener(this::fnSearchUni);

        // Return the root view of the binding
        return binding.getRoot();
    }

    private void fnSearchUni(View view) {
        // Get the country entered by the user in the EditText field
        String country = binding.edtFindUniversity.getText().toString().trim();

        if (country.isEmpty()) {
            Toast.makeText(getContext(), "Please enter a country name", Toast.LENGTH_SHORT).show();
            return;
        }

        // Construct the URL for the API request
        String strURL = "http://universities.hipolabs.com/search?country=" + country;

        // Create a request queue
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());

        // Create a JsonArrayRequest to get the university data
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                strURL,
                null,  // No request body
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        // Initialize a StringBuilder to hold the formatted country names
                        StringBuilder resultText = new StringBuilder();

                        // Loop through the response and extract university names
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                // Get each university object in the response
                                JSONObject university = response.getJSONObject(i);

                                // Get the name of the university
                                String universityName = university.optString("name");

                                // Add the university name to the result string (with a separator, like a space)
                                if (i != 0) {
                                    resultText.append(" \n "); // Use a separator to keep them in one row
                                }
                                resultText.append(universityName);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        // Display the result in the results TextView
                        binding.results.setText(resultText.toString());

                        // Enable scrolling for the results TextView if the text is long
                        binding.results.setMovementMethod(new ScrollingMovementMethod());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Display a toast message if there's an error with the request
                        Toast.makeText(getContext(), "Unable to connect to the university list: " + error.getMessage(),
                                Toast.LENGTH_SHORT).show();
                    }
                });

        // Add the request to the request queue
        requestQueue.add(jsonArrayRequest);
    }
}
