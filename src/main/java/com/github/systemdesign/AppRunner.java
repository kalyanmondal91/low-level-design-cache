package com.github.systemdesign;

import com.github.systemdesign.cache.Cache;
import com.github.systemdesign.cache.factory.CacheFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AppRunner implements CommandLineRunner {

    private final CacheFactory cacheFactory;

    public AppRunner(CacheFactory cacheFactory) {
        this.cacheFactory = cacheFactory;
    }

    @Override
    public void run(String... args) throws Exception {
        Cache cache = cacheFactory.cache(5, "LRU");

        cache.put(1, "One");
        cache.put(2, "Two");
        cache.put(3, "Three");
        log.info("Fetching {}", cache.get(1));
        log.info("Fetching {}", cache.get(2));
        cache.put(4, "Four");
        cache.put(5, "Five");
        cache.put(6, "Six");
        log.info("Fetching {}", cache.get(4));
        cache.put(7, "Seven");
        log.info("Fetching {}", cache.get(6));
        log.info("Fetching {}", cache.get(7));
        cache.put(8, "Eight");
    }
}
