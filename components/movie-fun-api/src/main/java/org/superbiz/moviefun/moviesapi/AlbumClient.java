package org.superbiz.moviefun.moviesapi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestOperations;
import org.springframework.web.util.UriComponentsBuilder;

import javax.xml.ws.Response;
import java.util.List;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.PUT;

public class AlbumClient {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private static ParameterizedTypeReference<List<AlbumInfo>> albumsType = new ParameterizedTypeReference<List<AlbumInfo>>() {
    };

    private RestOperations restOperations;
    private String httpUrl;

    public AlbumClient(RestOperations restOperations, String httpUrl) {
        this.restOperations = restOperations;
        this.httpUrl = httpUrl;
    }

    public void addAlbum(AlbumInfo album) {
        restOperations.postForEntity(httpUrl,album,AlbumInfo.class);
    }

    public AlbumInfo find(long id) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(httpUrl+"/"+id);
        logger.info("url "+builder.toUriString());
        return  restOperations.exchange(builder.toUriString(), GET, null, AlbumInfo.class).getBody();
    }

    public List<AlbumInfo> getAlbums() {
        return restOperations.exchange(httpUrl, GET, null, albumsType).getBody();

    }


    public void deleteAlbum(AlbumInfo album) {
       restOperations.delete(httpUrl+"/"+album.getId());
    }


    public void updateAlbum(AlbumInfo album)
    {
       restOperations.put(httpUrl,album);
    }
}
