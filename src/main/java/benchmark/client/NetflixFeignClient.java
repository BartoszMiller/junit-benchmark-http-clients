package benchmark.client;

import benchmark.api.HttpBinClient;
import benchmark.api.HttpBinClientNetflixFeign;
import benchmark.model.httpbin.IpAddress;
import benchmark.model.httpbin.UserAgent;
import feign.Feign;
import feign.Response;
import feign.jackson.JacksonDecoder;
import feign.okhttp.OkHttpClient;

import java.io.IOException;
import java.io.InputStream;

public class NetflixFeignClient implements HttpBinClient {

    HttpBinClientNetflixFeign httpBinClientNetflixFeign = Feign.builder()
            .client(new OkHttpClient())
            .decoder(new JacksonDecoder())
            .target(HttpBinClientNetflixFeign.class, HttpBinClient.BASE_URL);

    @Override
    public IpAddress findIp() throws IOException {
        return httpBinClientNetflixFeign.findIp();
    }

    @Override
    public UserAgent findUserAgent() throws IOException {
        return httpBinClientNetflixFeign.findUserAgent();
    }

    @Override
    public InputStream streamLines(int lines) throws IOException {
        Response response = httpBinClientNetflixFeign.streamLines(lines);
        return response.body().asInputStream();
    }
}
