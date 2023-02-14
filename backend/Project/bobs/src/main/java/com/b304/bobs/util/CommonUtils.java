package com.b304.bobs.util;

import org.springframework.data.jpa.repository.JpaRepository;

public class CommonUtils {
    public static void saveIfNullId(Long id, JpaRepository repository, Object entity) {
        if(id == null) {

            repository.save(entity);
        }
    }
}
