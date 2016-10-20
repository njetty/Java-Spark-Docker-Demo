package com.omega;

import com.google.gson.Gson;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import spark.Spark;
import spark.utils.IOUtils;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Created by NaveenJetty on 10/14/2016.
 */
public class UserAcceptanceTest {
    @BeforeClass
    public static void beforeClass(){
        Api.main(null);
        Spark.awaitInitialization();
    }

    @AfterClass
    public static void afterClass(){
        Spark.stop();
    }

    @Test
    public void aNewLogShouldBeAdded(){
        Map<String, String> data = new HashMap<String, String>();
        data.put("username","naveenjetty");
        data.put("timestamp","2016-10-14-08:04:12:00:00:00");
        data.put("description","Test log");
        Gson gson = new Gson();
        String jdata = gson.toJson(data);
        TestResponse res =  request("POST", "/add-log",jdata);
        assertEquals("\"Added log\"",res.body);
        assertEquals(200,res.status);

    }

    private TestResponse request(String method, String path, String data) {
        try {
            URL url = new URL("http://localhost:4567" + path);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(method);
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type","application/json");
            connection.connect();

            OutputStream os = connection.getOutputStream();
            os.write(data.getBytes());
            os.flush();

            String body = IOUtils.toString(connection.getInputStream());
            return new TestResponse(connection.getResponseCode(), body);
        } catch (IOException e) {
            e.printStackTrace();
            fail("Sending request failed: " + e.getMessage());
            return null;
        }
    }

    private static class TestResponse {

        public final String body;
        public final int status;

        public TestResponse(int status, String body) {
            this.status = status;
            this.body = body;
        }
    }
}
