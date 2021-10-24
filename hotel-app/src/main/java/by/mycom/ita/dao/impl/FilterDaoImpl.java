package by.mycom.ita.dao.impl;

import by.mycom.ita.dao.FilterDao;
import by.mycom.ita.model.Hotel;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class FilterDaoImpl implements FilterDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Hotel> coincidencesFiltering(Hotel hotel) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Hotel> criteriaQuery = criteriaBuilder.createQuery(Hotel.class);
        Root<Hotel> hotelRoot = criteriaQuery.from(Hotel.class);
        Predicate nameHotel = criteriaBuilder.equal(hotelRoot.get("name"), hotel.getName());
        Predicate rankingHotel = criteriaBuilder.equal(hotelRoot.get("avgMark"), hotel.getAvgMark());
        Predicate locationHotel = criteriaBuilder.equal(hotelRoot.get("location"), hotel.getLocation());
        Predicate convenienceHotel = criteriaBuilder.equal(hotelRoot.get("convenience"), hotel.getConvenience());
        criteriaQuery.select(hotelRoot).where(criteriaBuilder.or(nameHotel,locationHotel,rankingHotel,convenienceHotel));
        TypedQuery<Hotel> typedQuery = entityManager.createQuery(criteriaQuery);
        return typedQuery.getResultList();
    }

    @Override
    public List<Hotel> exactFiltering(Hotel hotel) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Hotel> criteriaQuery = criteriaBuilder.createQuery(Hotel.class);
        Root<Hotel> hotelRoot = criteriaQuery.from(Hotel.class);
        Predicate nameHotel = criteriaBuilder.equal(hotelRoot.get("name"), hotel.getName());
        Predicate rankingHotel = criteriaBuilder.equal(hotelRoot.get("avgMark"), hotel.getAvgMark());
        Predicate locationHotel = criteriaBuilder.equal(hotelRoot.get("location"), hotel.getLocation());
        Predicate convenienceHotel = criteriaBuilder.equal(hotelRoot.get("convenience"), hotel.getConvenience());
        criteriaQuery.select(hotelRoot).where(criteriaBuilder.and(nameHotel,locationHotel,rankingHotel,convenienceHotel));
        TypedQuery<Hotel> typedQuery = entityManager.createQuery(criteriaQuery);
        return typedQuery.getResultList();
    }
}
