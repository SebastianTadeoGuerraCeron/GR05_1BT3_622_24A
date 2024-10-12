package dao;


import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.Persistence;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import modelo.Comentario;
import modelo.Resena;
import dao.exceptions.NonexistentEntityException;

public class ComentarioJpaController {

    private EntityManagerFactory emf = null;

    // Constructor con EntityManagerFactory
    public ComentarioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    // Constructor que crea el EntityManagerFactory
    public ComentarioJpaController() {
        this.emf = Persistence.createEntityManagerFactory("JavaWebPoliParkPU");
    }

    // Obtener el EntityManager
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    // Crear un nuevo comentario
    @Transactional
    public void create(Comentario comentario) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Resena resena = comentario.getResena();
            if (resena != null) {
                resena = em.getReference(resena.getClass(), resena.getId());
                comentario.setResena(resena);
            }
            em.persist(comentario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    // Editar un comentario
    @Transactional
    public void edit(Comentario comentario) throws NonexistentEntityException, Exception {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Comentario comentarioPersistente = em.find(Comentario.class, comentario.getId());
            Resena resenaAntigua = comentarioPersistente.getResena();
            Resena resenaNueva = comentario.getResena();
            if (resenaNueva != null) {
                resenaNueva = em.getReference(resenaNueva.getClass(), resenaNueva.getId());
                comentario.setResena(resenaNueva);
            }
            comentario = em.merge(comentario);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findComentario(comentario.getId()) == null) {
                throw new NonexistentEntityException("El comentario con id " + comentario.getId() + " no existe.");
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    // Eliminar un comentario
    @Transactional
    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Comentario comentario;
            try {
                comentario = em.getReference(Comentario.class, id);
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
    public List<Comentario> findComentarioEntities() {
        return findComentarioEntities(true, -1, -1);
    }

    // Obtener un rango de comentarios (para paginación)
    public List<Comentario> findComentarioEntities(int maxResults, int firstResult) {
        return findComentarioEntities(false, maxResults, firstResult);
    }

    // Método auxiliar para obtener todos los comentarios o una parte
    private List<Comentario> findComentarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            var cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Comentario.class));
            var q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList().stream().map(result -> (Comentario) result).collect(Collectors.toList());
        } finally {
            em.close();
        }
    }

    // Buscar un comentario por ID
    public Comentario findComentario(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Comentario.class, id);
        } finally {
            em.close();
        }
    }

    // Obtener la cantidad de comentarios
    public int getComentarioCount() {
        EntityManager em = getEntityManager();
        try {
            var cq = em.getCriteriaBuilder().createQuery();
            var rt = cq.from(Comentario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            var q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}

