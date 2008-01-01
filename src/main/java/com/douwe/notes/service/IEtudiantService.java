package com.douwe.notes.service;

import com.douwe.notes.entities.Etudiant;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Kenfack Valmy-Roi <roykenvalmy@gmail.com>
 */
@Local
public interface IEtudiantService {
    
    public Etudiant saveOrUpdateEtudiant(Etudiant etudiant);
    
    public void deleteEtudiant(Long id);
    
    public Etudiant findEtudiantById(long id);
    
    public List<Etudiant> getAllEtudiant();
    
}
