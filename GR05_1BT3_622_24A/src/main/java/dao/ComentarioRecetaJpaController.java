package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.Persistence;
import modelo.ComentarioReceta;
import dao.exceptions.NonexistentEntityException;

import java.util.List;

public class ComentarioRecetaJpaController {

    private EntityManagerFactory emf = null;

    // Constructor con EntityManagerFactory
    public ComentarioRecetaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    // Constructor que crea el EntityManagerFactory
    public ComentarioRecetaJpaController() {
        this.emf = Persistence.createEntityManagerFactory("JavaWebLasHuequitas");
    }

    // Obtener el EntityManager
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    // Crear un nuevo comentario para una receta
    public void create(ComentarioReceta comentarioReceta) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(comentarioReceta);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    // Editar un comentario
    public void edit(ComentarioReceta comentarioReceta) throws NonexistentEntityException, Exception {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            ComentarioReceta comentarioPersistente = em.find(ComentarioReceta.class, comentarioReceta.getId());
            if (comentarioPersistente == null) {
                throw new NonexistentEntityException("El comentario con id " + comentarioReceta.getId() + " no existe.");
            }
            em.merge(comentarioReceta);  // Guardar los cambios
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();  // Hacer rollback si hay un error
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    // Eliminar un comentario
    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            ComentarioReceta comentario;
            try {
                comentario = em.getReference(ComentarioReceta.class, id);
                comentario.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("El comentario con id " + id + " no existe.", enfe);
            }
            em.remove(comentario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    // Obtener todos los comentarios
    public List<ComentarioReceta> findComentarioRecetaEntities() {
        return findComentarioRecetaEntities(true, -1, -1);
    }

    // Obtener un rango de comentarios (para paginación)
    public List<ComentarioReceta> findComentarioRecetaEntities(int maxResults, int firstResult) {
        return findComentarioRecetaEntities(false, maxResults, firstResult);
    }

    // Método auxiliar para obtener todos los comentarios o una parte
    private List<ComentarioReceta> findComentarioRecetaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            jakarta.persistence.criteria.CriteriaQuery<ComentarioReceta> cq = em.getCriteriaBuilder().createQuery(ComentarioReceta.class);
            cq.select(cq.from(ComentarioReceta.class));
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

    // Buscar un comentario por ID
    public ComentarioReceta findComentarioReceta(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ComentarioReceta.class, id);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    // Obtener la cantidad de comentarios
    public int getComentarioRecetaCount() {
        EntityManager em = getEntityManager();
        try {
            jakarta.persistence.criteria.CriteriaQuery<Long> cq = em.getCriteriaBuilder().createQuery(Long.class);
            jakarta.persistence.criteria.Root<ComentarioReceta> rt = cq.from(ComentarioReceta.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            jakarta.persistence.Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}
