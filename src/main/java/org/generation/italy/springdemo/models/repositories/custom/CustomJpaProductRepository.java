package org.generation.italy.springdemo.models.repositories.custom;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import org.generation.italy.springdemo.models.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CustomJpaProductRepository implements CustomProductRepository {
    @Autowired
    private EntityManager em;
    @Override
    public Optional<Product> findById(int id) {
        return Optional.ofNullable(em.find(Product.class, id));
    }

    @Override
    public List<Product> findAll() {
        // prendi l'entity manager fai ritornare questa query su questa classe e ritorna la lista di risultati
        return em.createQuery("SELECT p FROM Product p", Product.class).getResultList(); // Product è maiuscolo perché chiama la classe
    }

    @Override
    public Product save(Product product) {
        em.persist(product); // ha l'id automaticamente settato
        return product;
    }

    @Override
    public boolean deleteById(int id) {
        Product found = em.find(Product.class, id);
        if(found == null){
            return false;
        }
        em.remove(found);
        return true;
    }

    public boolean deleteById2(int id){
        try{
            Product proxy = em.getReference(Product.class, id);
            em.remove(proxy);
            return true;
        } catch (EntityNotFoundException e){
            return false;
        }
    }

    @Override
    public boolean update(Product newProduct) {
        Product p = em.find(Product.class, newProduct.getProductId());
        if(p == null){
            return false;
        }
        em.persist(p);
        return true;
    }


}
