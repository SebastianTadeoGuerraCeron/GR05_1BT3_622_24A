package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.Persistence;
import modelo.Receta;
import dao.exceptions.NonexistentEntityException;

import java.util.List;

public class RecetaJpaController {

    private EntityManagerFactory emf = null;

    // Constructor con EntityManagerFactory
    public RecetaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    // Constructor que crea el EntityManagerFactory
    public RecetaJpaController() {
        this.emf = Persistence.createEntityManagerFactory("JavaWebLasHuequitas");
    }

    // Obtener el EntityManager
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    // Crear una nueva receta
    public void create(Receta receta) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();

            // Asegurarse de que cada comentario esté asociado a la receta
            if (receta.getComentarios() != null) {
                receta.getComentarios().forEach(comentario -> comentario.setReceta(receta));
            }

            // Manejar las reacciones de la receta
            if (receta.getReacciones() != null) {
                receta.getReacciones().setReceta(receta); // Asignar la receta en ReaccionReceta
            }

            em.persist(receta);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    // Editar una receta
    public void edit(Receta receta) throws NonexistentEntityException, Exception {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Receta recetaPersistente = em.find(Receta.class, receta.getId());
            if (recetaPersistente == null) {
                throw new NonexistentEntityException("La receta con id " + receta.getId() + " no existe.");
            }

            // Actualizar los comentarios
            if (receta.getComentarios() != null) {
                receta.getComentarios().forEach(comentario -> comentario.setReceta(receta));
            }

            // Actualizar las reacciones
            if (receta.getReacciones() != null) {
                receta.getReacciones().setReceta(receta);
            }

            em.merge(receta); // Guardar los cambios
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback(); // Hacer rollback si hay un error
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    // Eliminar una receta
    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Receta receta;
            try {
                receta = em.getReference(Receta.class, id);
                receta.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("La receta con id " + id + " no existe.", enfe);
            }
            em.remove(receta);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    // Obtener todas las recetas
    public List<Receta> findRecetaEntities() {
        return findRecetaEntities(true, -1, -1);
    }

    // Obtener un rango de recetas (para paginación)
    public List<Receta> findRecetaEntities(int maxResults, int firstResult) {
        return findRecetaEntities(false, maxResults, firstResult);
    }

    // Método auxiliar para obtener todas las recetas o una parte
    private List<Receta> findRecetaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            jakarta.persistence.criteria.CriteriaQuery<Receta> cq = em.getCriteriaBuilder().createQuery(Receta.class);
            cq.select(cq.from(Receta.class));
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

    // Buscar una receta por ID
    public Receta findReceta(Long id) {
        EntityManager em = getEntityManager();
        try {
            // Usamos fetch join para cargar los comentarios y las reacciones de la receta de forma inmediata
            return em.createQuery("SELECT r FROM Receta r LEFT JOIN FETCH r.comentarios LEFT JOIN FETCH r.reacciones WHERE r.id = :id", Receta.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    // Obtener la cantidad de recetas
    public int getRecetaCount() {
        EntityManager em = getEntityManager();
        try {
            jakarta.persistence.criteria.CriteriaQuery<Long> cq = em.getCriteriaBuilder().createQuery(Long.class);
            jakarta.persistence.criteria.Root<Receta> rt = cq.from(Receta.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            jakarta.persistence.Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}
