package com.example.thomas.gymclubapp.API;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class api {
    private Context context = null;

    public api(Context _context) {
        context = _context;
    }


    /**
     *
     * @param onSuccess
     * @param url
     * @param auth
     * @param mParams
     * @param bBody
     * @param method
     */
    public void call(Response.Listener<String> onSuccess, Response.ErrorListener onError, String url, final String auth, final Map<String, String> mParams, final byte[] bBody, int method) {
        RequestQueue queue = Volley.newRequestQueue(context);

        StringRequest stringRequest = new StringRequest(method, url,
                onSuccess, onError){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Authorization", auth);
                return params;
            }
            @Override
            public Map<String, String> getParams() {
                return mParams;
            }

            @Override
            public byte[] getBody() {
                return bBody;
            }
        };
        queue.add(stringRequest);
    }
}
