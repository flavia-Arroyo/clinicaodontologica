/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import logica.Turno;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import logica.Odontologo;
import persistencia.exceptions.NonexistentEntityException;

/**
 *
 * @author LENOVO
 */
public class OdontologoJpaController implements Serializable {

    public OdontologoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
     public OdontologoJpaController(){
        emf= Persistence.createEntityManagerFactory("ConsultorioOdontologico_PU");
        
    }

    public void create(Odontologo odontologo) {
        if (odontologo.getListaTurno() == null) {
            odontologo.setListaTurno(new ArrayList<Turno>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Turno> attachedListaTurno = new ArrayList<Turno>();
            for (Turno listaTurnoTurnoToAttach : odontologo.getListaTurno()) {
                listaTurnoTurnoToAttach = em.getReference(listaTurnoTurnoToAttach.getClass(), listaTurnoTurnoToAttach.getId_turno());
                attachedListaTurno.add(listaTurnoTurnoToAttach);
            }
            odontologo.setListaTurno(attachedListaTurno);
            em.persist(odontologo);
            for (Turno listaTurnoTurno : odontologo.getListaTurno()) {
                Odontologo oldOdontoOfListaTurnoTurno = listaTurnoTurno.getOdonto();
                listaTurnoTurno.setOdonto(odontologo);
                listaTurnoTurno = em.merge(listaTurnoTurno);
                if (oldOdontoOfListaTurnoTurno != null) {
                    oldOdontoOfListaTurnoTurno.getListaTurno().remove(listaTurnoTurno);
                    oldOdontoOfListaTurnoTurno = em.merge(oldOdontoOfListaTurnoTurno);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Odontologo odontologo) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Odontologo persistentOdontologo = em.find(Odontologo.class, odontologo.getId());
            List<Turno> listaTurnoOld = persistentOdontologo.getListaTurno();
            List<Turno> listaTurnoNew = odontologo.getListaTurno();
            List<Turno> attachedListaTurnoNew = new ArrayList<Turno>();
            for (Turno listaTurnoNewTurnoToAttach : listaTurnoNew) {
                listaTurnoNewTurnoToAttach = em.getReference(listaTurnoNewTurnoToAttach.getClass(), listaTurnoNewTurnoToAttach.getId_turno());
                attachedListaTurnoNew.add(listaTurnoNewTurnoToAttach);
            }
            listaTurnoNew = attachedListaTurnoNew;
            odontologo.setListaTurno(listaTurnoNew);
            odontologo = em.merge(odontologo);
            for (Turno listaTurnoOldTurno : listaTurnoOld) {
                if (!listaTurnoNew.contains(listaTurnoOldTurno)) {
                    listaTurnoOldTurno.setOdonto(null);
                    listaTurnoOldTurno = em.merge(listaTurnoOldTurno);
                }
            }
            for (Turno listaTurnoNewTurno : listaTurnoNew) {
                if (!listaTurnoOld.contains(listaTurnoNewTurno)) {
                    Odontologo oldOdontoOfListaTurnoNewTurno = listaTurnoNewTurno.getOdonto();
                    listaTurnoNewTurno.setOdonto(odontologo);
                    listaTurnoNewTurno = em.merge(listaTurnoNewTurno);
                    if (oldOdontoOfListaTurnoNewTurno != null && !oldOdontoOfListaTurnoNewTurno.equals(odontologo)) {
                        oldOdontoOfListaTurnoNewTurno.getListaTurno().remove(listaTurnoNewTurno);
                        oldOdontoOfListaTurnoNewTurno = em.merge(oldOdontoOfListaTurnoNewTurno);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = odontologo.getId();
                if (findOdontologo(id) == null) {
                    throw new NonexistentEntityException("The odontologo with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(int id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Odontologo odontologo;
            try {
                odontologo = em.getReference(Odontologo.class, id);
                odontologo.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The odontologo with id " + id + " no longer exists.", enfe);
            }
            List<Turno> listaTurno = odontologo.getListaTurno();
            for (Turno listaTurnoTurno : listaTurno) {
                listaTurnoTurno.setOdonto(null);
                listaTurnoTurno = em.merge(listaTurnoTurno);
            }
            em.remove(odontologo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Odontologo> findOdontologoEntities() {
        return findOdontologoEntities(true, -1, -1);
    }

    public List<Odontologo> findOdontologoEntities(int maxResults, int firstResult) {
        return findOdontologoEntities(false, maxResults, firstResult);
    }

    private List<Odontologo> findOdontologoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Odontologo.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Odontologo findOdontologo(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Odontologo.class, id);
        } finally {
            em.close();
        }
    }

    public int getOdontologoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Odontologo> rt = cq.from(Odontologo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
