package com.b304.bobs.repository;

import com.b304.bobs.entity.Community;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CommunityRepository {

    private final EntityManager em;

    public void write(Community community){
        em.persist(community);
    }

    public void commentWrite(){

    }


    public List<Community> findAllById(Long user_id){
        return em.createQuery("select c from Community c where m.user_id =: userId" , Community.class)
               .setParameter("userId", user_id)
                .getResultList();

    }

    public List<Community> findAll(){
        return em.createQuery("select c from Community c", Community.class)
                .getResultList();
    }


}
