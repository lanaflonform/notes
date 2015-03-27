package com.douwe.notes.service.impl;

import com.douwe.generic.dao.DataAccessException;
import com.douwe.notes.dao.ITypeCoursDao;
import com.douwe.notes.entities.TypeCours;
import com.douwe.notes.service.ITypeCoursService;
import com.douwe.notes.service.ServiceException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Kenfack Valmy-Roi <roykenvalmy@gmail.com>
 */
@Stateless
public class TypeCourServiceImpl implements ITypeCoursService {

    @Inject
    private ITypeCoursDao typeCoursDao;

    public ITypeCoursDao getTypeCoursDao() {
        return typeCoursDao;
    }

    public void setTypeCoursDao(ITypeCoursDao typeCoursDao) {
        this.typeCoursDao = typeCoursDao;
    }

    @Override
    public TypeCours saveOrUpdateTpyeCours(TypeCours typeCours) throws ServiceException {
        try {
            if (typeCours.getId() == null) {
                typeCours.setActive(1);
                return typeCoursDao.create(typeCours);
            } else {
                return typeCoursDao.update(typeCours);
            }
        } catch (DataAccessException dae) {
            Logger.getLogger(DepartementServiceImpl.class.getName()).log(Level.SEVERE, null, dae);
            throw new ServiceException("La ressource demandée est introuvable");
        }
    }

    @Override
    public void deleteTypeCours(Long id) throws ServiceException {
        try {
            TypeCours typeCours = typeCoursDao.findById(id);
            typeCoursDao.delete(typeCours);
        } catch (DataAccessException ex) {
            Logger.getLogger(TypeCourServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new ServiceException("La ressource demandée est introuvable");
        }
    }

    @Override
    public TypeCours findTypeCoursById(long id) throws ServiceException {
        try {
            return typeCoursDao.findById(id);
        } catch (DataAccessException ex) {
            Logger.getLogger(TypeCourServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new ServiceException("La ressource demandée est introuvable");
        }
    }

    @Override
    public List<TypeCours> getAllTypeCours() throws ServiceException {
        try {
            return typeCoursDao.findAll();
        } catch (DataAccessException ex) {
            Logger.getLogger(TypeCourServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new ServiceException("La ressource demandée est introuvable");
        }
    }
}
