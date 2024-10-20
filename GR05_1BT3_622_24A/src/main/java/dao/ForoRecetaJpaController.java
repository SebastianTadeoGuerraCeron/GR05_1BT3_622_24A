package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.Persistence;
import modelo.ForoReceta;
import dao.exceptions.NonexistentEntityException;

import java.util.List;

public class ForoRecetaJpaController {

    private EntityManagerFactory emf = null;

    // Constructor con EntityManagerFactory
    public ForoRecetaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    // Constructor que crea el EntityManagerFactory
    public ForoRecetaJpaController() {
        this.emf = Persistence.createEntityManagerFactory("JavaWebLasHuequitas");
    }

    // Obtener el EntityManager
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    // Crear un nuevo foro de recetas
    public void create(ForoReceta foroReceta) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            if (foroReceta.getListaReceta() != null) {
                foroReceta.getListaReceta().forEach(receta -> {
                    receta.setForoReceta(foroReceta); // Asegurarse de que cada Receta esté asociada al ForoReceta
                });
            }
            em.persist(foroReceta);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    // Editar un foro de recetas
    public void edit(ForoReceta foroReceta) throws NonexistentEntityException, Exception {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            ForoReceta foroRecetaPersistente = em.find(ForoReceta.class, foroReceta.getId());
            if (foroRecetaPersistente == null) {
                throw new NonexistentEntityException("El foro de recetas con id " + foroReceta.getId() + " no existe.");
            }

            // Actualizar la lista de recetas
            if (foroReceta.getListaReceta() != null) {
                foroReceta.getListaReceta().forEach(receta -> {
                    receta.setForoReceta(foroReceta); // Asegurarse de que cada receta esté asociada al foro editado
                });
            }

            em.merge(foroReceta); // Guardar los cambios
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

    // Eliminar un foro de recetas
    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            ForoReceta foroReceta;
            try {
                foroReceta = em.getReference(ForoReceta.class, id);
                foroReceta.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("El foro de recetas con id " + id + " no existe.", enfe);
            }
            em.remove(foroReceta);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    // Obtener todos los foros de recetas
    public List<ForoReceta> findForoRecetaEntities() {
        return findForoRecetaEntities(true, -1, -1);
    }

    // Obtener un rango de foros de recetas (para paginación)
    public List<ForoReceta> findForoRecetaEntities(int maxResults, int firstResult) {
        return findForoRecetaEntities(false, maxResults, firstResult);
    }

    // Método auxiliar para obtener todos los foros o una parte
    private List<ForoReceta> findForoRecetaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            jakarta.persistence.criteria.CriteriaQuery<ForoReceta> cq = em.getCriteriaBuilder().createQuery(ForoReceta.class);
            cq.select(cq.from(ForoReceta.class));
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

    // Buscar un foro de recetas por ID
    public ForoReceta findForoReceta(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ForoReceta.class, id);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    // Obtener la cantidad de foros de recetas
    public int getForoRecetaCount() {
        EntityManager em = getEntityManager();
        try {
            jakarta.persistence.criteria.CriteriaQuery<Long> cq = em.getCriteriaBuilder().createQuery(Long.class);
            jakarta.persistence.criteria.Root<ForoReceta> rt = cq.from(ForoReceta.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            jakarta.persistence.Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}
