package com.dgd.earthquakes;

import com.dgd.earthquakes.data.network.callback.IQuakesCallbackListener;
import com.dgd.earthquakes.data.network.callback.QuakesCallback;
import com.dgd.earthquakes.data.network.infra.QuakeData;
import com.dgd.earthquakes.data.network.infra.QuakesResponse;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Protocol;
import okhttp3.Request;
import retrofit2.Response;

/**
 * Created by max on 06/10/17.
 */
@RunWith(JUnit4.class)
public class QuakeCallbackTest {

    @Test
    public void testWithoutListener(){
        QuakesCallback qc = Mockito.mock(QuakesCallback.class);

        Exception e = new Exception("TestException");
        qc.onFailure(null, e);

        QuakesResponse body = Mockito.mock(QuakesResponse.class);
        okhttp3.Response raw = new okhttp3.Response.Builder()
                .request(new Request.Builder()
                        .url("https://earthquake.usgs.gov/fdsnws/event/1/")
                        .build())
                .protocol(Protocol.HTTP_1_0)
                .code(200)
                .build();
        Response<QuakesResponse> r = Response.success(body, raw);
//        Mockito.spy(raw); // final class
        Mockito.spy(body);
//        Mockito.spy(r); // final class

        qc.onResponse(null, r);
//        Mockito.verify(r).raw(); // final class
//        Mockito.verify(r).code(); // final class
//        Mockito.verify(r).body(); // final class
    }

    @Test
    public void testWithListener(){
        IQuakesCallbackListener listener = Mockito.mock(IQuakesCallbackListener.class);
        QuakesCallback qc = new QuakesCallback(listener); //new QuakesCallback(null);
//        qc.setListener(listener);

        String msg = "TestException";
        Exception e = new Exception(msg);
        qc.onFailure(null, e);
        Mockito.verify(listener).onNetworkError(msg, 400);

        List<QuakeData> list = new ArrayList<>();
        QuakesResponse body = Mockito.mock(QuakesResponse.class);
        body.setQuakes(list);
        okhttp3.Response raw = new okhttp3.Response.Builder()
                .request(new Request.Builder()
                        .url("https://earthquake.usgs.gov/fdsnws/event/1/")
                        .build())
                .protocol(Protocol.HTTP_1_0)
                .code(200)
                .build();
        Response<QuakesResponse> r = Response.success(body, raw);
//        Mockito.spy(raw); // final class
        Mockito.spy(body);
//        Mockito.spy(r); // final class

        qc.onResponse(null, r);
        Mockito.verify(listener).onNetworkSuccess(list);
//        Mockito.verify(r).raw(); // final class
//        Mockito.verify(r).code(); // final class
//        Mockito.verify(r).body(); // final class
    }
}
