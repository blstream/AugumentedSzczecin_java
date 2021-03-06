package com.bls.rdbms.dao;

import com.bls.core.IdentifiableEntity;
import com.bls.core.geo.Location;
import com.bls.core.place.Place;
import com.bls.core.user.User;
import com.bls.dao.CommonDao;
import com.google.common.base.Optional;
import com.google.common.collect.Lists;
import io.dropwizard.hibernate.AbstractDAO;
import io.dropwizard.util.Generics;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;

import java.util.Collection;
import java.util.List;

public abstract class CommonJpaDao<J, E extends IdentifiableEntity> extends AbstractDAO<J> implements CommonDao<E> {

    private final Class<?> entityClass;

    public CommonJpaDao(final SessionFactory sessionFactory) {
        super(sessionFactory);
        entityClass = Generics.getTypeParameter(getClass());
    }

    protected abstract J convert2jpa(E coreEntity);

    protected abstract E convert2core(J jpaEntity);

    @Override
    public E create(final E entity) {
        return convert2core(persist(convert2jpa(entity)));
    }

    @Override
    public E update(final E entity) {
        return create(entity);
    }

    @Override
    public void delete(final E entity) {
        currentSession().delete(convert2jpa(entity));
    }

    @Override
    public void deleteById(final String id) {
        final Object entity = currentSession().load(entityClass, Long.valueOf(id));
        currentSession().delete(entity);
    }

    @Override
    public void deleteAll() {
        throw new IllegalStateException("Not implemented");
    }

    @Override
    public Optional<E> findById(final String id) {
        return Optional.fromNullable(convert2core((J) currentSession().get(entityClass, Long.valueOf(id))));
    }

    @Override
    public Collection<E> findAll(final User owner) {
        final List<J> list = currentCriteria().list();
        final List<E> result = Lists.newArrayListWithCapacity(list.size());
        for (J entity : list) {
            result.add(convert2core(entity));
        }
        return result;
    }

    public Collection<E> find(Location location,
            Long radius,
            Collection<String> tags,
            Optional<String> name,
            Optional<String> street,
            Collection<Place.Subcategory> subcat,
            Optional<Boolean> paid,
            Optional<Boolean> open,
            Optional<User> user,
            Optional<Integer> page,
            Integer pageSize) {
        throw new IllegalStateException("Unimplemented");
    }

    protected Criteria currentCriteria() {return currentSession().createCriteria(entityClass);}
}
