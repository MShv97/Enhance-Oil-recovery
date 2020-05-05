/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OilImproveMethods;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author MSh
 */
public class MethodResult {

    private final SimpleStringProperty methodName;
    private final SimpleStringProperty res;

    public MethodResult(String methodName, String res) {
        this.methodName = new SimpleStringProperty(methodName);
        this.res = new SimpleStringProperty(res);
    }

    public String getMethodName() {
        return methodName.get();
    }

    public String getRes() {
        return res.get();
    }
}
