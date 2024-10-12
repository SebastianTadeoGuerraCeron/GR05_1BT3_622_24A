package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.Persistence;
import java.util.List;
import modelo.Foro;
import dao.exceptions.NonexistentEntityException;

public class ForoJpaController {

    private EntityManagerFactory emf = null;

    // Constructor con EntityManagerFactory
    public ForoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    // Constructor que crea el EntityManagerFactory
    public ForoJpaController() {
        this.emf = Persistence.createEntityManagerFactory("JavaWebPoliParkPU");
    }

    // Obtener el EntityManager
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    // Crear un nuevo foro
    public void create(Foro foro) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            if (foro.getListaResena() != null) {
                foro.getListaResena().forEach(resena -> {
                    resena.setForo(foro); // Asegurarse de que cada Resena esté asociada al Foro
                });
            }
            em.persist(foro);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    // Editar un foro
    public void edit(Foro foro) throws NonexistentEntityException, Exception {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Foro foroPersistente = em.find(Foro.class, foro.getId());
            if (foroPersistente == null) {
                throw new NonexistentEntityException("El foro con id " + foro.getId() + " no existe.");
            }

            // Actualizar la lista de reseñas
            if (foro.getListaResena() != null) {
                foro.getListaResena().forEach(resena -> {
                    resena.setForo(foro); // Asegurarse de que cada reseña esté asociada al foro editado
                });
            }

            Foro foroMerged = em.merge(foro); // Utilizamos una variable temporal
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

    // Eliminar un foro
    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Foro foro;
            try {
                foro = em.getReference(Foro.class, id);
                foro.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("El foro con id " + id + " no existe.", enfe);
            }
            em.remove(foro);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    // Obtener todos los foros
    public List<Foro> findForoEntities() {
        return findForoEntities(true, -1, -1);
    }

    // Obtener un rango de foros (para paginación)
    public List<Foro> findForoEntities(int maxResults, int firstResult) {
        return findForoEntities(false, maxResults, firstResult);
    }

    // Método auxiliar para obtener todos los foros o una parte
    private List<Foro> findForoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            jakarta.persistence.criteria.CriteriaQuery<Foro> cq = em.getCriteriaBuilder().createQuery(Foro.class);
            cq.select(cq.from(Foro.class));
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

    // Buscar un foro por ID
    public Foro findForo(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Foro.class, id);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    // Obtener la cantidad de foros
    public int getForoCount() {
        EntityManager em = getEntityManager();
        try {
            jakarta.persistence.criteria.CriteriaQuery<Long> cq = em.getCriteriaBuilder().createQuery(Long.class);
            jakarta.persistence.criteria.Root<Foro> rt = cq.from(Foro.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            jakarta.persistence.Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}
