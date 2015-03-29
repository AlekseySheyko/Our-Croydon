package com.lbcinternal.sensemble.rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.OkHttpClient;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.RestAdapter.LogLevel;
import retrofit.client.OkClient;
import retrofit.converter.GsonConverter;

public class RestClient {

    private static final String BASE_URL =
            "https://lbc-shapecroydon-ci-dev.azurewebsites.net";

    private ApiService mApiService;

    public RestClient() {
        buildRestClient(null);
    }

    public RestClient(String dateFormat) {
        buildRestClient(dateFormat);
    }

    private void buildRestClient(String dateFormat) {

        RestAdapter.Builder builder = new RestAdapter.Builder()
                .setLogLevel(LogLevel.FULL)
                .setClient(new OkClient(new OkHttpClient()))
                .setEndpoint(BASE_URL);


        if (dateFormat != null) {
            Gson gson = new GsonBuilder()
                    .setDateFormat(dateFormat)
                    .create();

            builder.setConverter(new GsonConverter(gson));
        }

        builder.setRequestInterceptor(new RequestInterceptor() {
            @Override public void intercept(RequestFacade request) {
                request.addHeader("Accept", "application/json");
                request.addHeader("Content-Type", "application/json");
                request.addHeader("Cookie",
                        "ARRAffinity=4a6693a5405e04c54f09470ae89e2b2e1cc413e698c14d2bdbefbc7dbcd395a1; " +
                                "ASP.NET_SessionId=2vguy1mlsh1wxc5loqaixwul; " +
                                ".AUXBLOGENGINE=6BC6A0514DC81CF07BFBA808E66B936D1E9349852395270B418E1A2C2ECC58D7D936FD9BCF71CBF70AF2FFB0173F6FCD604D3725DA0D3C100A27D43240AB13D3077E650B6A50680E0A8A7C9AEDD4291EABB0A0FDF524036575C71E45C47009CCEEFF0705FB4541E7AE08BFB3FBA45DA9F0121E5878AB247920EEF19674E1D83BFD5BB25210C772B53796041451831C89BCF86FC1D602EC58935E157FE2796293BADEC6C941C62D054F41108DCE32AB300999B09E87C488F8F56A67F52493808CA57993B185A0013A08B58605BBB50BF950B6000A298A58D76DFC302CD1E36A5FF142FC3A7EE1783602FEE0E3B9525DB8CF292098F49C152A432830185713E3DC3936020D296F2121368D7E0A545A8BB0B89F20E4C81F6515974A749A8D8F4BD12D05FF5B041EB1DE98450C0076573C654637736393AE17B56103F8C88797EB1CB985B22D0A520F9D3D3CA1DDCC55D6EB79FF5E689372E5777FC97DF22DE288C6F916D4286E9808113039786766265D5DA2DC7F7A775DE5D99B9D8B4A1BC3DDC51FEF887BE1C3E92DE17F6341BFBF5A737EDC00BA; " +
                                "ai_user=C4EDCB2D-284F-4E71-B907-4CACD76B5D70; ai_session=704841FB-305B-417D-A613-FB24084148A7|2015-03-28T21:52:22.052Z|2015-03-28T21:52:24.934Z");
            }
        });

        RestAdapter adapter = builder.build();

        mApiService = adapter.create(ApiService.class);
    }

    public ApiService getApiService() {
        return mApiService;
    }
}
