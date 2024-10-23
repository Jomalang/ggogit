package io.ggogit.ggogit.api.tree;

import io.ggogit.ggogit.api.tree.dto.SeedResponse;
import io.ggogit.ggogit.domain.tree.entity.Seed;
import io.ggogit.ggogit.domain.tree.service.SeedService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/seeds")
@RequiredArgsConstructor
public class SeedController {

    private final SeedService seedService;

    @GetMapping
    public ResponseEntity<SeedResponse> getSeeds() {
        List<Seed> seeds = seedService.list();
        return new ResponseEntity<>(SeedResponse.of(seeds), HttpStatus.OK);
    }
}
