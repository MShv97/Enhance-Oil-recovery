/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OilFieldsList;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author MSh
 */
public class OilField {

    private final SimpleIntegerProperty id;
    private final SimpleStringProperty name;
    private final SimpleFloatProperty api;
    private final SimpleFloatProperty vicosity;
    private final SimpleFloatProperty k;
    private final SimpleFloatProperty saturation;
    private final SimpleFloatProperty depth;
    private final SimpleFloatProperty thickness;
    private final SimpleFloatProperty temprature;
    private final SimpleStringProperty lithology;
    private final SimpleStringProperty gasCap;

    public OilField(int id, String name, float api, float vicosity, float k, float saturation, float depth, float thickness, float temprature, String lithology, String gasCap) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.api = new SimpleFloatProperty(api);
        this.vicosity = new SimpleFloatProperty(vicosity);
        this.k = new SimpleFloatProperty(k);
        this.saturation = new SimpleFloatProperty(saturation);
        this.depth = new SimpleFloatProperty(depth);
        this.thickness = new SimpleFloatProperty(thickness);
        this.temprature = new SimpleFloatProperty(temprature);
        this.lithology = new SimpleStringProperty(lithology);
        this.gasCap = new SimpleStringProperty(gasCap);
    }

    public int getId() {
        return id.get();
    }

    public String getName() {
        return name.get();
    }

    public float getApi() {
        return api.get();
    }

    public float getVicosity() {
        return vicosity.get();
    }

    public float getK() {
        return k.get();
    }

    public float getSaturation() {
        return saturation.get();
    }

    public float getDepth() {
        return depth.get();
    }

    public float getThickness() {
        return thickness.get();
    }

    public float getTemprature() {
        return temprature.get();
    }

    public String getLithology() {
        return lithology.get();
    }

    public String getGasCap() {
        return gasCap.get();
    }

}
