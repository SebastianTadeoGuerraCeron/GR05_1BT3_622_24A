package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.Persistence;
import java.util.List;
import modelo.Resena;
import dao.exceptions.NonexistentEntityException;

public class ResenaJpaController {

    private EntityManagerFactory emf = null;

    // Constructor con EntityManagerFactory
    public ResenaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    // Constructor que crea el EntityManagerFactory
    public ResenaJpaController() {
        this.emf = Persistence.createEntityManagerFactory("JavaWebPoliParkPU");
    }

    // Obtener el EntityManager
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    // Crear una nueva reseña
    public void create(Resena resena) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            // Asegurarse de que la reseña esté asociada a un foro
            if (resena.getForo() != null) {
                resena.setForo(em.getReference(resena.getForo().getClass(), resena.getForo().getId()));
            }
            // Asegurarse de que cada comentario esté asociado a la reseña
            if (resena.getListaComentarios() != null) {
                resena.getListaComentarios().forEach(comentario -> comentario.setResena(resena));
            }
            em.persist(resena);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    // Editar una reseña
    public void edit(Resena resena) throws NonexistentEntityException, Exception {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Resena resenaPersistente = em.find(Resena.class, resena.getId());
            if (resenaPersistente == null) {
                throw new NonexistentEntityException("La reseña con id " + resena.getId() + " no existe.");
            }

            // Actualizar la relación con el foro
            if (resena.getForo() != null) {
                resena.setForo(em.getReference(resena.getForo().getClass(), resena.getForo().getId()));
            }

            // Actualizar la lista de comentarios
            if (resena.getListaComentarios() != null) {
                resena.getListaComentarios().forEach(comentario -> comentario.setResena(resena));
            }

            // Utilizar una variable temporal para el resultado del merge
            Resena resenaMerged = em.merge(resena);
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback(); // Hacer rollback en caso de error
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }


    // Eliminar una reseña
    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Resena resena;
            try {
                resena = em.getReference(Resena.class, id);
                resena.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("La reseña con id " + id + " no existe.", enfe);
            }
            em.remove(resena);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    // Obtener todas las reseñas
    public List<Resena> findResenaEntities() {
        return findResenaEntities(true, -1, -1);
    }

    // Obtener un rango de reseñas (para paginación)
    public List<Resena> findResenaEntities(int maxResults, int firstResult) {
        return findResenaEntities(false, maxResults, firstResult);
    }

    // Método auxiliar para obtener todas las reseñas o una parte
    private List<Resena> findResenaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            jakarta.persistence.criteria.CriteriaQuery<Resena> cq = em.getCriteriaBuilder().createQuery(Resena.class);
            cq.select(cq.from(Resena.class));
            jakarta.persistence.Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    // Buscar una reseña por ID
    public Resena findResena(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Resena.class, id);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    // Obtener la cantidad de reseñas
    public int getResenaCount() {
        EntityManager em = getEntityManager();
        try {
            jakarta.persistence.criteria.CriteriaQuery<Long> cq = em.getCriteriaBuilder().createQuery(Long.class);
            jakarta.persistence.criteria.Root<Resena> rt = cq.from(Resena.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            jakarta.persistence.Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}
