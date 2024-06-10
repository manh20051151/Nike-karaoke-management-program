package dao.impl;

import dao.DaoDonVi;
import entity.DonVi;
import init.Init;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

import java.io.Serial;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class DaoImplDonVi extends UnicastRemoteObject implements DaoDonVi {

    @Serial
    private static final long serialVersionUID = 3366923262163851901L;
    private EntityManager em;
    public DaoImplDonVi()throws RemoteException {
        em = Init.Init().createEntityManager();
    }

    public List<DonVi> getALLDonVi() throws RemoteException{
        try {
            TypedQuery<DonVi> query = em.createQuery("SELECT dv FROM DonVi dv", DonVi.class);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public DonVi getDonViTheoMa(String maDonVi) throws RemoteException{
        try {
            TypedQuery<DonVi> query = em.createQuery("SELECT dv FROM DonVi dv WHERE dv.maDonVi = :maDonVi", DonVi.class);
            query.setParameter("maDonVi", maDonVi);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public DonVi getDonViTheoTen(String tenDonVi) throws RemoteException{
        try {
            TypedQuery<DonVi> query = em.createQuery("SELECT dv FROM DonVi dv WHERE dv.tenDonVi = :tenDonVi", DonVi.class);
            query.setParameter("tenDonVi", tenDonVi);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean addDonVi(DonVi donVi) throws RemoteException{
        try {
            em.getTransaction().begin();
            em.persist(donVi);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateDonVi(DonVi donVi) throws RemoteException{
        try {
            em.getTransaction().begin();
            em.merge(donVi);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
            return false;
        }
    }
    public boolean deleteDonVi(String maDonVi) throws RemoteException{
        try {
            em.getTransaction().begin();
            DonVi donVi = em.find(DonVi.class, maDonVi);
            if (donVi != null) {
                em.remove(donVi);
                em.getTransaction().commit();
                return true;
            } else {
                System.out.println("Không tìm thấy đơn vị với mã: " + maDonVi);
                return false;
            }
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
            return false;
        }
    }





}
