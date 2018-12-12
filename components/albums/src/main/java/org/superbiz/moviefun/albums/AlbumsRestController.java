package org.superbiz.moviefun.albums;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.superbiz.moviefun.BlobStore;

import java.util.List;

@RestController
@RequestMapping("/album")
public class AlbumsRestController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final AlbumsRepository albumsRepository;


    public AlbumsRestController(AlbumsRepository albumsRepository) {
        this.albumsRepository = albumsRepository;

    }

    @PostMapping
    public void addAlbum(@RequestBody Album album) {
        albumsRepository.addAlbum(album);
    }

    @GetMapping("/{albumId}")
    public Album findAlbum(@PathVariable long albumId){
        return albumsRepository.find(albumId);
    }

    @GetMapping
    public List<Album> findAll(){
        return albumsRepository.getAlbums();
    }

    @DeleteMapping("/{albumId}")
    public void deleteAlbum(@PathVariable long albumId){
        albumsRepository.deleteAlbum(albumsRepository.find(albumId));
    }

    @PutMapping
    public void updateAlbum(@RequestBody Album album){
        albumsRepository.updateAlbum(album);
    }

   }
