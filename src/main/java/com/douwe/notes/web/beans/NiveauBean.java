/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.douwe.notes.web.beans;

import com.douwe.notes.entities.Cycle;
import com.douwe.notes.entities.Niveau;
import com.douwe.notes.service.IInsfrastructureService;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.inject.Named;

/**
 *
 * @author root
 */
@Named(value = "niveauBean")
@RequestScoped
public class NiveauBean {

    @EJB
    private IInsfrastructureService service;
    private Niveau niveau = new Niveau();    
    private List<Niveau> niveaux;
    private List<Cycle> cycles;
    private Cycle cycle;

    public NiveauBean() {
        niveau.setCycle(new Cycle());
    }

    public List<Niveau> findAll() {
        return service.getAllNiveaux();
    }

    public void saveOrUpdateNiveaux() {        
        System.err.println(" 4 "+niveau);
        if (niveau != null) {
//            niveau.setCycle(new Cycle());
//            niveau.setCycle(cycle);
            System.err.println("1 " + niveau.getCycle());
            service.saveOrUpdateNiveau(niveau);
            niveau=new Niveau();
        }
    }

    public void deleteNiveau() {
        if (niveau != null && niveau.getId() > 0) {
            service.deleteNiveau(niveau.getId());
            niveau = new Niveau();
        }
    }

    public Converter getCycleConverter() {
        return cycleConverter;
    }

    public void setCycleConverter(Converter cycleConverter) {
        this.cycleConverter = cycleConverter;
    }

    private Converter cycleConverter = new Converter() {

        @Override
        public  Cycle getAsObject(FacesContext context, UIComponent component, String value) {            
            int id = Integer.parseInt(value.trim().substring(0,value.trim().indexOf("-")));
            Cycle c = service.findCycleById(id);
            System.err.print(" 2 "+value + " --> "+c);
            return c;
        }

        @Override
        public String getAsString(FacesContext context, UIComponent component, Object value) {
            Cycle c = (Cycle) value;
            System.err.println(" 3 "+value +" --> "+c.getId() + "-" + c.getNom());
            return c.getId() + "-" + c.getNom();

        }
    };

    public IInsfrastructureService getService() {
        return service;
    }

    public void setService(IInsfrastructureService service) {
        this.service = service;
    }

    public Niveau getNiveau() {
        return niveau;
    }

    public void setNiveau(Niveau niveau) {
        this.niveau = niveau;
    }

    public List<Niveau> getNiveaux() {
        niveaux = findAll();
        return niveaux;
    }

    public void setNiveaux(List<Niveau> niveaux) {
        this.niveaux = niveaux;
    }

    public List<Cycle> getCycles() {
        cycles = service.getAllCycles();
        return cycles;
    }

    public void setCycles(List<Cycle> cycles) {
        this.cycles = cycles;
    }

    public Cycle getCycle() {
        cycle = niveau.getCycle();
        return cycle;
    }

    public void setCycle(Cycle cycle) {
        System.err.println(" 6 "+ cycle);
        this.cycle = cycle;
    }
    

}
