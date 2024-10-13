package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import modelo.Reaccion;

public class ReactionJpaController {

    private EntityManagerFactory emf = null;

    public ReactionJpaController() {
        this.emf = Persistence.createEntityManagerFactory("JavaWebLasHuequitas");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Reaccion reaccion) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(reaccion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public Reaccion findReaccion(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Reaccion.class, id);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Reaccion reaccion) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(reaccion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
}

