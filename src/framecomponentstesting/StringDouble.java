/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package framecomponentstesting;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

public class StringDouble implements Comparable{

    String category;
    Double percentage; 
    
    public StringDouble(String s, Double d) {
        category = s;
        percentage = d;
    }
    public String getCategory() {
        return category;
    }
    public Double getPercentage() {
        return percentage;
    }
    @Override
    public int compareTo(Object o) {
        //Descending order
        StringDouble that = (StringDouble) o;
       if (this.percentage == that.percentage) {
           return 0;
       } else
           return this.percentage < that.percentage? 1:-1;
    }

    
}
