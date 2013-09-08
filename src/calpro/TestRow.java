/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package calpro;
/**
 *
 * @author Dell
 */
public class TestRow {

    String[] S;

    public TestRow() {
        S = new String[]{"AAA", "BBB"};
    }

    public TestRow(String[] s) {
        S = s;
    }

    public String get(int i) {
        return S[i];
    }
    
    public String[] getArray(){
        return S;
    }

    public void set(int i, String s) {
        S[i] = s;
    }
}
