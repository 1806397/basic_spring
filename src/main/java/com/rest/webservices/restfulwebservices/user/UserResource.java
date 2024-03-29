package com.rest.webservices.restfulwebservices.user;
import static  org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import com.rest.webservices.restfulwebservices.jpa.PostRepository;
import com.rest.webservices.restfulwebservices.jpa.UserRepository;
import jakarta.validation.Valid;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class UserResource {
//    private UserDAOService service;
    private UserRepository userRepository;
    private PostRepository postRepository;
    public UserResource(UserRepository userRepository,PostRepository postRepository) {
        this.userRepository = userRepository;
        this.postRepository=postRepository;
    }

    // GET users
    @GetMapping(path = "/users")
    public List<User> retrieveAllUsers() {

        return userRepository.findAll();
    }

    @GetMapping(path = "/users/{id}")
    public EntityModel<User> retrieveUser(@PathVariable int id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty())
            throw new UserNotFoundException("id:" + id);
        EntityModel<User> entityModel=EntityModel.of(user.get());
        WebMvcLinkBuilder link=linkTo(methodOn(this.getClass()).retrieveAllUsers());
        entityModel.add(link.withRel("all-users"));
        return entityModel;
    }

    @DeleteMapping(path = "/users/{id}")
    public void deleteUser(@PathVariable int id) {
        userRepository.deleteById(id);
    }
    @PostMapping(path = "/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        User savedUser = userRepository.save(user);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }
    @GetMapping(path = "/users/{id}/posts")
    public List<Post> RetrievePostForUser(@PathVariable int id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            ResponseEntity.status(HttpStatus.NOT_FOUND);
            throw new UserNotFoundException("id:" + id);
        }
        if(user.get().getPost().isEmpty()){
            ResponseEntity.status(HttpStatus.NOT_FOUND);
            throw new UserNotFoundException("id:" + id);
        }
        return user.get().getPost();
    }
    @PostMapping(path = "/users/{id}/posts")
    public ResponseEntity<Object> retrievePost(@PathVariable int id,@Valid @RequestBody Post post) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty())
            throw new UserNotFoundException("id:" + id);
        post.setUser(user.get());
        Post savedPost=postRepository.save(post);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedPost.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }
}
